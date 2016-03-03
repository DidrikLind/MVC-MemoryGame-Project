/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmemory;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author didrik
 */
public class MVCMemory extends JFrame {
    
    public MVCMemory()
    {
        MemoryModel model = new MemoryModel();
        MemoryView view = new MemoryView();
        add(view);
        MemoryController controller = new MemoryController(model, view);
        
    
        setSize(new Dimension(600, 600));
        //pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MVCMemory();
    }
    
}
