package mvcmemory;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author didrik
 */
public class MemoryView extends JPanel
{
    
    private ButtonView[][] buttons;
    private JLabel failLabel, tapLabel, textColorLabel, highScoreLabel;
    private JPanel sPanel, wPanel, cPanel, nPanel, nameAddPanel;
    private JButton resetButton, addNameButton;
    private JTextField numberText, titleText; //************************NOT IN USE******************************************* 
    
    private JComboBox<Integer> cbox; //**jobbar med.
    
    private JTextField highScoreNameText;
    private JList<String> jList;
    private JScrollPane scrollPane;
    private DefaultListModel<String> modList;
    
    
    public MemoryView()
    {
        initGUI();
    }
    
    public void initGUI()
    {
        setLayout(new BorderLayout());
        
        makeSouthPanel();
        makeWestPanel();
        makeCenterPanel();
        makeNorthPanel();
    }
    
    private void makeSouthPanel()
    {
        sPanel = new JPanel();
        
        resetButton = new JButton("Reset");
        sPanel.add(resetButton);
        textColorLabel = new JLabel("How many colors?");
        sPanel.add(textColorLabel);
        
        Integer[] numColorItems = {2, 8, 18, 32, 50, 72, 98};
	cbox = new JComboBox<>(numColorItems);
        sPanel.add(cbox);
        
        
        //numberText = new JTextField(5);
        //sPanel.add(numberText);
        
        add(sPanel, BorderLayout.SOUTH);
        
        
    }
//************************NOT IN USE*******************************************   
    public int getNumOfColorsFromUser()
    {
        Scanner scan = new Scanner(numberText.getText());
        int nextInt = 0;
        if(scan.hasNextInt())
            nextInt = scan.nextInt();
        else
        {
            numberText.setText(" ");
            return 0;
        }
        return nextInt;
        
    }
//************************NOT IN USE*******************************************
    
    public int getNumOFColorsFromUserBox()
    {
        return (int) cbox.getSelectedItem();
    }
    
    private void makeWestPanel()
    {
        wPanel = new JPanel();
        wPanel.setLayout(new BoxLayout(wPanel, BoxLayout.Y_AXIS));
        
        failLabel = new JLabel("Failed pairs: ");
        failLabel.setHorizontalAlignment(JTextField.CENTER);
        failLabel.setFont(new Font("Courier", Font.BOLD,15));
        wPanel.add(failLabel);
        
        tapLabel = new JLabel("Tapped pairs: ");
        tapLabel.setHorizontalAlignment(JLabel.CENTER);
        tapLabel.setFont(new Font("Courier", Font.BOLD,15));
        wPanel.add(tapLabel);
        
        
        
        highScoreNameText = new JTextField("NAME***");
        //highScoreText.setMaximumSize(new Dimension(250,50));
        //highScoreText.setPreferredSize(new Dimension(100,50));
        addNameButton = new JButton("Add yourself");
        
        nameAddPanel = new JPanel();
        nameAddPanel.add(highScoreNameText);
        nameAddPanel.add(addNameButton);
        //nameAddPanel.setPreferredSize(new Dimension(200,100));
        wPanel.add(nameAddPanel);
        
        highScoreLabel = new JLabel("Best Memory: NAME *** failed pairs ");
        highScoreLabel.setHorizontalAlignment(JLabel.LEFT);
        highScoreLabel.setFont(new Font("Courier", Font.BOLD,15));
        wPanel.add(highScoreLabel);
        
	modList = new DefaultListModel<String>();
	jList = new JList<String>(modList);
	scrollPane = new JScrollPane(jList);
        wPanel.add(scrollPane);
        
        
        
        add(wPanel, BorderLayout.WEST);
    }
    
    public void updateFailLabel(int fails)
    {
        failLabel.setText("Failed pairs: " + fails);
    }
    
    public void updateTapLabel(int taps)
    {
        tapLabel.setText("Tapped pairs: " + taps);
    }
    
    public void updateJList(String[] highScoresCopy, int whichList)
    {
        modList.clear();
        for(int i=whichList*5; i<whichList*5+5; i=i+1)
        {
            modList.addElement(highScoresCopy[i]);
        }
    }
    
    public String getHighScoreNameText()
    {
        return highScoreNameText.getText();
    }
    
    public int getFails()
    {
//        Scanner scan = new Scanner(failLabel.getText());
//        if(scan.hasNextInt())
//        {
//            return scan.nextInt();
//        }
        //*******FIX***********
        return Integer.parseInt(failLabel.getText().substring(failLabel.getText().length()-1));
        //*******FIX***********
    }  
    
    private void makeCenterPanel()
    {
        cPanel = new JPanel(new GridLayout(100,100,1,1));
        cPanel.setMaximumSize(new Dimension(250,250));
        cPanel.setPreferredSize(new Dimension(250,250));
        
        buttons = new ButtonView[100][100];
        for(int r=0; r<100; r=r+1)
        {
            for(int c=0; c<100; c=c+1)
            {
                buttons[r][c] = new ButtonView(r,c);
                cPanel.add(buttons[r][c]);
                buttons[r][c].setBackground(Color.WHITE);
                buttons[r][c].setEnabled(false);
            }
        }
        
        add(cPanel, BorderLayout.CENTER);
    }
    
    public void updateCenterPanel(int numOfRowsAndColons)
    {
        if(numOfRowsAndColons!=0)
        {
            cPanel.removeAll();
            cPanel.revalidate();
            cPanel.repaint();
            buttons = new ButtonView[numOfRowsAndColons][numOfRowsAndColons];
            cPanel.setLayout(new GridLayout(numOfRowsAndColons, numOfRowsAndColons, 1, 1));
            for(int r=0; r<numOfRowsAndColons; r=r+1)
            {
                for(int c=0; c<numOfRowsAndColons; c=c+1)
                {
                    buttons[r][c] = new ButtonView(r,c);
                    buttons[r][c].setBackground(Color.WHITE);
                    cPanel.add(buttons[r][c]);
                }
            }
        }

    }
    
    public void makeNorthPanel()
    {
        nPanel = new JPanel();
        
        titleText = new JTextField("M E M O R Y");
        titleText.setHorizontalAlignment(JTextField.CENTER);
        titleText.setFont(new Font("Courier", Font.BOLD,50));
        titleText.setEditable(false);
        nPanel.add(titleText);
        
        add(nPanel, BorderLayout.NORTH);
    }
    
    public ButtonView getAButtonView(int r, int c)
    {
        return buttons[r][c];
    }
    
    public void addCenterButtonListener(ActionListener listen)
    {
        for(int r=0; r<buttons.length; r=r+1)
        {
            for(int c=0; c<buttons.length; c=c+1)
            {
                buttons[r][c].addActionListener(listen);
            }
        }
    }
    
    public void addResetButtonListener(ActionListener listen)
    {
        resetButton.addActionListener(listen);
    }
    
    public void addAddNameButtonListener(ActionListener listen)
    {
        addNameButton.addActionListener(listen);
    }

//************************NOT IN USE******************************************* 
    public void addTextListener(KeyListener listen)
    {
        numberText.addKeyListener(listen);
    }
//************************NOT IN USE*******************************************    
}
