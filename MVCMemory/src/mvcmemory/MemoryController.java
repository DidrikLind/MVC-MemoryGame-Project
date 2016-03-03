/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmemory;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author didrik
 */
public class MemoryController 
{
    private MemoryView view;
    private MemoryModel model;
    private MemoryColorTiles colorTiles;
    private MemoryHighScore highScore;
    private Timer timer1;
    private int whichList; // which list in the highscore :P
    
    public MemoryController(MemoryModel model, MemoryView view)
    {
        this.view = view;
        this.model = model;
        colorTiles = model.getColorTiles();
        highScore = model.getHighScore();
        
        timer1 = new Timer(1000*2, new TimerSleepClass());
        timer1.setRepeats(false);
        //timer1.setInitialDelay(2*1000);
                
        
        view.addCenterButtonListener(new CenterButtonListener());
        view.addResetButtonListener(new ResetListener());
        view.addAddNameButtonListener(new AddNameButtonListener());
        //view.addTextListener(new TextListener()); //************************NOT IN USE******************************************* 
    }
    
    private class CenterButtonListener implements ActionListener
    {
        private int clicks, oldRow, oldCol;
        private JButton tempButton;
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            clicks = clicks + 1; // användare har klickat på 1 knapp.
            System.out.println("u cliocked button");
            
            ButtonView activeButton = (ButtonView) ae.getSource();
            int row = activeButton.getRow();
            int col = activeButton.getCol();
            System.out.println("u cliocked button with row col:" + row + " , " + col);
            if(clicks==1)
            {
                activeButton.setBackground(colorTiles.getTheColor(row, col));
                tempButton = activeButton;
                oldRow = row;
                oldCol = col;
            }
            else if(clicks==2)
            {
                activeButton.setBackground(colorTiles.getTheColor(row, col));
                timer1.start();
                //timer1.stop();

                if((colorTiles.getTheColor(row, col) == colorTiles.getTheColor(oldRow, oldCol)) &&
                    (row!=oldRow || col!=oldCol))
                {
                    colorTiles.setTappedPairs(colorTiles.getTappedPairs() + 1);
                    
                    view.getAButtonView(oldRow, oldCol).setEnabled(false);
//                    view.getAButtonView(oldRow, oldCol)
//                    .setText(colorTiles.getTheColor(oldRow, oldCol).toString());
                    
                    view.getAButtonView(row, col).setEnabled(false);
//                    view.getAButtonView(row, col)
//                    .setText(colorTiles.getTheColor(row, col).toString());
                    
                    view.updateTapLabel(colorTiles.getTappedPairs()); 
                }
                else
                {
                    colorTiles.setFailedPairs(colorTiles.getFailedPairs() + 1);
                    
                    view.getAButtonView(oldRow, oldCol).setBackground(Color.WHITE);
                    view.getAButtonView(row, col).setBackground(Color.WHITE); 
                    
                    view.updateFailLabel(colorTiles.getFailedPairs()); 
                }
                
                clicks = 0;
            }
                
        }
        
    }
    

    private class TimerSleepClass implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
                
        }
        
    }
    
    public void sleepTheGUI(int seconds)
    {
        System.out.println("Thread is sleeping while user clicked on 2"
                        + " buttons " + Thread.currentThread().getName());
        try 
        {
        // thread to sleep for 1000 milliseconds
            Thread.sleep(1000*seconds);
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    
    private class ResetListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            System.out.println("u cliocked resetbutton");
            
            
            highScore.scanTextScoreFile();


            colorTiles.setNumOfColors(view.getNumOFColorsFromUserBox());
            colorTiles.pickColors();
            colorTiles.randomizeColorPairs();
            colorTiles.printColorArray();
            
            colorTiles.setFailedPairs(0);
            colorTiles.setTappedPairs(0);
            
            //update view.
            view.updateCenterPanel(colorTiles.getNumOfRowColons());
            view.updateFailLabel(colorTiles.getFailedPairs());
            view.updateTapLabel(colorTiles.getTappedPairs());
            
            System.out.println("getnumofcolorsfromuserbox lOLOLO :" + view.getNumOFColorsFromUserBox() );
            System.out.println("getnumofcolorsfromuserbox lOLOLO :" + view.getNumOFColorsFromUserBox() );
            System.out.println("getnumofcolorsfromuserbox lOLOLO :" + view.getNumOFColorsFromUserBox() );
            System.out.println("getnumofcolorsfromuserbox lOLOLO :" + view.getNumOFColorsFromUserBox() );
            setWhichHighScoreList(view.getNumOFColorsFromUserBox());
            
            
                    
             //System.out.println("Given size of player:" + view.getNumOfRowsAndColons());
            view.addCenterButtonListener(new CenterButtonListener());
            
            
            
            
        }
        
    }
    
    
    
    private class AddNameButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            System.out.println("U want to add your name dude ;D");
            //System.out.println("Namely you want to be evaluated yby my system!!");
            highScore.scanTextScoreFile();
            
            try 
            {//view.getFails() I use beacuse, the fails are alreadyt updated from the model.
                highScore.tryToAddScore(view.getFails(), view.getHighScoreNameText(), 0);
                //highScore.tryToAddScore(view.getFails() , view.getHighScoreNameText(), 0);*******FIX***********
                
//tryToAddScore(int failedPairs, String name, int whichList)
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(MemoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            setWhichHighScoreList(view.getNumOFColorsFromUserBox());
        }
        
    }
    
    private void setWhichHighScoreList(int numOfColors)
    {
        if(numOfColors==2)
            view.updateJList(highScore.getHighScoresCopy(), 0); //list 0.
        if(numOfColors==8)
            view.updateJList(highScore.getHighScoresCopy(), 1); //list 1.
        if(numOfColors==18)
            view.updateJList(highScore.getHighScoresCopy(), 2); //list 2.
        if(numOfColors==32)
            view.updateJList(highScore.getHighScoresCopy(), 3); //list 3.
        if(numOfColors==50)
            view.updateJList(highScore.getHighScoresCopy(), 4); //list 4.
        if(numOfColors==72)
            view.updateJList(highScore.getHighScoresCopy(), 5); //list 5.
        if(numOfColors==98)
            view.updateJList(highScore.getHighScoresCopy(), 6); //list 6.
    }
    
//    private int whichList(int numOfColors)
//    {
//        
//    }
 //************************NOT IN USE*******************************************   
    private class TextListener extends KeyAdapter
    {
        @Override
        public void keyTyped(KeyEvent ke) 
        {
            
        }

        @Override
        public void keyPressed(KeyEvent ke)
        {
            
        }

        @Override
        public void keyReleased(KeyEvent ke) 
        {
            JTextField text = (JTextField) ke.getSource();
            System.out.println(text.getText());
            try
            {
                int keyNum = Integer.parseInt(text.getText());
            }
            catch(Exception e)
            {
                System.out.println("not a number bro!;");
            }
            
        
        }
    
    }
//************************NOT IN USE*******************************************
    

}
