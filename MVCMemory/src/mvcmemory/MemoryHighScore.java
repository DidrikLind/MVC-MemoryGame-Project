/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmemory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author didrik
 */
public class MemoryHighScore 
{
    private String[] highScores;
    private String[] names;
    
    public MemoryHighScore()
    {//Maximum 5 in the highscore!
        highScores = new String[35]; // 5*7 = 35, we have 7 different alternatives
        names = new String[4];
    }
    
    public void tryToAddScore(int failedPairs, String name, int whichList) throws IOException
    {
        Scanner scan;
        String nameInList;
        int currentDigitInList, whichSpot=0;
        boolean isGoodEnough=false;
        for(int i=whichList*5; i<whichList*5+5; i=i+1) // i may be: 0,1,2,3,4,5,6
        {
            scan = new Scanner(highScores[i]);
            nameInList = scan.next();
            currentDigitInList = scan.nextInt();
            /*
             * yada ydad yda!!
             */
            if(currentDigitInList >= failedPairs)
            {
                isGoodEnough = true;
                break;
            }
            whichSpot = whichSpot + 1; // I want to hålla koll på vilken nivå vi e i.
        }
        
        if(isGoodEnough) // if the failedPairs are low enough!
        {
            if(whichSpot==0)
            {
               highScores[whichList*5+4] = highScores[whichList*5+3];
               highScores[whichList*5+3] = highScores[whichList*5+2];
               highScores[whichList*5+2] = highScores[whichList*5+1];
               highScores[whichList*5+1] = highScores[whichList*5+0];
               highScores[whichList*5] = name + " " + failedPairs;
            }
            else if(whichSpot==1)
            {
               highScores[whichList*5+4] = highScores[whichList*5+3];
               highScores[whichList*5+3] = highScores[whichList*5+2];
               highScores[whichList*5+2] = highScores[whichList*5+1];
               highScores[whichList*5+1] = name + " " + failedPairs;
            }
            else if(whichSpot==2)
            {
               highScores[whichList*5+4] = highScores[whichList*5+3];
               highScores[whichList*5+3] = highScores[whichList*5+2];
               highScores[whichList*5+2] = name + " " + failedPairs;
                
            }
            else if(whichSpot==3)
            {
               highScores[whichList*5+4] = highScores[whichList*5+3];
               highScores[whichList*5+3] = name + " " + failedPairs;
                
            }
            else // plats 4.
            {
               highScores[whichList*5+4] = name + " " + failedPairs;
                
            }
            //makes a new file, with same current name. (overwriting current file)
            PrintWriter newFile =
                    new PrintWriter(new BufferedWriter(
                    new FileWriter("C:\\Users\\didrik\\Google Drive\\"
                    + "JavaProjekt\\NetBeansProjects\\MVCMemory\\"
                    + "src\\mvcmemory\\highScore"+whichList+".txt")));
            
            String sCurrentLine;  
            for(int i=0; i<5; i=i+1)
            {
                newFile.println(highScores[whichList*5 + i]);
                //newFile.write(highScores[whichList*5 + i]);
                
            }
            newFile.close();
        }
        
            
        //return true;
    }
    
//    private boolean isOkayScore()
//    {
//        return true;
//    }
    
    public String[] getHighScoresCopy()
    { // just sending a copy back, and not the original array from this database :O
        String[] highScoresCopy = new String[35];
        for(int i=0; i<highScoresCopy.length; i=i+1)
        {
            highScoresCopy[i] = highScores[i]; 
        }
        return highScoresCopy;
    }
    
    public void scanTextScoreFile() // checking how the files is now, current files in the directory.
    {
        int count = 0;
        for(int i=0; i<7; i=i+1)
        {
            try (BufferedReader activeFilebr = new BufferedReader(new FileReader("C:\\Users\\didrik\\Google Drive\\"
                    + "JavaProjekt\\NetBeansProjects\\MVCMemory\\"
                    + "src\\mvcmemory\\highScore"+i+".txt")))
            {
                String sCurrentLine;
                
                while ((sCurrentLine = activeFilebr.readLine()) != null) 
                {
                    highScores[count] = sCurrentLine;
                    count = count + 1;
                }

            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            } 
            
        }
        

        for(int i=0; i<highScores.length; i=i+1)
        {
            System.out.println(highScores[i] + "");
        }
        
    }
    
 // NOT IN USE*********************************************   
    public void printTextScoreFile()
    {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\didrik\\Google Drive\\"
                + "JavaProjekt\\NetBeansProjects\\MVCMemory\\"
                + "src\\mvcmemory\\highScore.txt")))
        {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) 
            {
                    System.out.println(sCurrentLine);
            }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
    }
// NOT IN USE*********************************************
    
}
