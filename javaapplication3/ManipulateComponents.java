/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author caoductin
 */
public class ManipulateComponents {
    
    
    public void hideJPanel(JPanel jpanel){
          Component[] components = jpanel.getComponents(); // get a list of child components
         for (Component component : components) {
        component.setVisible(false); // hide each child component
        
        }
    }
    public void visibleJpanel(JPanel jpanel){
        Component[] components = jpanel.getComponents(); // get a list of child components
         for (Component component : components) {
        component.setVisible(true); // hide each child component
        
        }
        
    }
}
