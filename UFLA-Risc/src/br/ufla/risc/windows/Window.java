/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.risc.windows;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author Jesimar, Luccas, Luiz e Márcio.
 */
public class Window extends JFrame{
    
    private final int SIZE_X = 1020;
    private final int SIZE_Y = 770;
    
    private Panel panel;       
    
    public Window(){        
        init();  
        addPanel();
    }
    
    private void init(){
        this.setTitle("UFLA-RISC");                     
        this.setLocation(100, 0);
        this.setSize(SIZE_X, SIZE_Y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setVisible(true);
    }      
    
    private void addPanel(){
        panel = new Panel(SIZE_X, SIZE_Y);
        panel.build(this);
    }
}
