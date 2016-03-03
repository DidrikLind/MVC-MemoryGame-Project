/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmemory;

/**
 *
 * @author didrik
 */
public class MemoryModel 
{
    private MemoryColorTiles colorTiles;
    private MemoryHighScore highScore;
    
    public MemoryModel()
    {
        colorTiles = new MemoryColorTiles();
        highScore = new MemoryHighScore();
    }
    
    public MemoryColorTiles getColorTiles()
    {
        return colorTiles;
    }
    
    public MemoryHighScore getHighScore()
    {
        return highScore;
    }
    
//Något liknande vill jag ha om jag skall ha viewn som lyssnare till
//modellen, så jag kan kasta propertychange här!   
//    public void setNumOfColors(int numOfColors)
//    {
//        colorTiles.setNumOfColors(numOfColors);
//    }
//    
//    public Color[][] getColorArray()
//    {
//        return colorTiles.getColorArray();
//    }
//    
//    public Color getTheColor(int r, int c)
//    {
//       return colorTiles.getTheColor(r, c);
//    }   
//Något liknande vill jag ha om jag skall ha viewn som lyssnare till
//modellen, så jag kan kasta propertychange här!    
}
    
    
 
