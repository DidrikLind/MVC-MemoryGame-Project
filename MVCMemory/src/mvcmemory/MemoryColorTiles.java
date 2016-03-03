/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmemory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author didrik
 */
public class MemoryColorTiles 
{
    private ArrayList<Color> colorList;
    private Color[][] colorArray;
    private int numOfColors;
    private int failedPairs, tappedPairs;
    private Random rand;
    
    public MemoryColorTiles()
    {
        rand = new Random();
    }
    
    public void setNumOfColors(int numOfColors)
    {
        this.numOfColors = numOfColors;
    }
    
    public Color[][] getColorArray()
    {
        return colorArray;
    }
    
    public Color getTheColor(int r, int c)
    {
       return colorArray[r][c];
    }
    
    public void pickColors()
    {
        colorList = new ArrayList<>(); 
        int count=0;
        Color color;
        while(count < numOfColors)
        {
            //Problem here is if the colors will both be very close each other.
            //r=20,g=107,b=216 and r=10,g=128,b=192 are both blue and very
            // alike each other!(Though they are not the same, as my eye thinks >_<)

            color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            if(!colorList.contains(color)  && color!=Color.WHITE)
            {
                colorList.add(color);
                count=count+1;
            }
        }
    }
    
    public void randomizeColorPairs()
    {
        int rows=getNumOfRowColons();
        int colons = getNumOfRowColons();
        
        colorArray = new Color[rows][colons];
        printColorArray(); // checka hur den ser ut innan!
        int count=0, randomPick=0;
        System.out.println("#");
        while(count < rows*colons)// "mindre än antalet rutor tagna, vilket är radgångercolon."
        {
            
            for(int r=0; r<rows; r=r+1)
            {
                for(int c=0; c<colons; c=c+1)
                {
                    randomPick = rand.nextInt(colorList.size()); // 0 till size-1       
                    //System.out.println("randomPICK: " + randomPick );
                    if(colorArray[r][c]==null && !isDoubleColor(colorList.get(randomPick)))
                    {
                        colorArray[r][c] = colorList.get(randomPick);
                        count=count+1;
                        System.out.println("randomPICK: " + randomPick + " to row,col: " + r +"," + c );
                    }
                }
            }
            //System.out.println("coutn är i randomizemetod: " + count);
        }
        System.out.println("coutn är i randomizemetod: " + count);
        //printColorArray(); Gör där uppe just nu!;)
            
    }
    
    public int getNumOfRowColons()
    {//Half of the even squares!! 2^2/2=2, 4^2/2=8, 6^2/2=18,...
        if(numOfColors==2)
            return 2;
        if(numOfColors==8)
            return 4;
        if(numOfColors==18)
            return 6;
        if(numOfColors==32)
            return 8;
        if(numOfColors==50)
            return 10;
        if(numOfColors==72)
            return 12;
        if(numOfColors==98)
            return 14;
        return 0;        
    }

    private boolean isDoubleColor(Color color)
    {
        int count=0;
        for(int r=0; r<colorArray.length; r=r+1)
        {
            for(int c=0; c<colorArray.length; c=c+1)
            {
                if(colorArray[r][c]==color)
                {
                    count = count + 1;
                }
            }
        }
        return count==2;
    }
    
    public boolean isDoubleColorList(Color color)
    {
        int count=0;
        for(int i=0; i<colorList.size(); i=i+1)
        {
            if(colorList.get(i) == color)
            {
                count = count + 1;
            }
        }
        return count==2;
    }
    
    public void printColorArray()
    {
        System.out.println("print board says: colons: " + colorArray.length);
        for(int r=0; r<colorArray.length; r=r+1)
        {
            for(int c=0; c<colorArray.length; c=c+1)
            {
                if( c%colorArray.length==0)
                    System.out.println(" ");
                System.out.print(colorArray[r][c] + "");
            }
        }
    }
    
    public void printColorList()
    {
        for(int i=0; i<colorList.size(); i=i+1)
        {
            System.out.println(colorList.get(i));
        }
    }
    
    public int getFailedPairs()
    {
        return failedPairs;
    }
    
    public void setFailedPairs(int failedPairs)
    {
        this.failedPairs = failedPairs;
    }
    
    public int getTappedPairs()
    {
        return tappedPairs;
    }
    
    public void setTappedPairs(int tappedPairs)
    {
        this.tappedPairs = tappedPairs;
    }
    
}
