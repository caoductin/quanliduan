/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

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
    class CustomHeaderRenderer extends DefaultTableCellRenderer {
    public CustomHeaderRenderer() {
        setHorizontalAlignment(JLabel.CENTER); // set the horizontal alignment of the label to the center
     }
    }
    
     public void setHeaderTable(JTable name){//set header table
    
//         Font font = new Font("Arial", Font.BOLD, 20); // create a new Font object
//        name.getTableHeader().setFont(font);
        DefaultTableCellRenderer head_render = new CustomHeaderRenderer(); 
        head_render.setBackground(new Color(204,153,255)); 
       // name.getTableHeader().setPreferredSize(new Dimension(name.getTableHeader().getWidth(),50)); //set the with and height for hearder table

        name.getTableHeader().setDefaultRenderer(head_render);//set color for header table
      
         name.getTableHeader().setForeground(new Color(255,255,255));
       
        
        
        
        
    }
     public void setHeaderTableTest(JTable table) {
        
    Font font = new Font("Arial", Font.BOLD, 18); // create a new Font object
    table.getTableHeader().setFont(font); // set the font for the header of the JTable
    table.getTableHeader().setForeground(Color.white); // set the text color of the header
    
    // create a default table cell renderer to set custom properties for the header of the JTable
    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer();
    renderer.setHorizontalAlignment(JLabel.CENTER); // set the horizontal alignment of the label to the center
   renderer.setOpaque(true);             
        table.getTableHeader().setBackground(Color.red);
   renderer.setBackground(Color.BLUE); // set the background color of the header
           table.getTableHeader().setDefaultRenderer(renderer);//set color for header table
      
         table.getTableHeader().setForeground(new Color(7,10,82));
      
    
    table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30)); // set the preferred height of the header
}

    
    
}
