/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arrangepuzzle;

import javax.swing.JFrame;

/**
 * 06-Sep-2016, 23:03:23.
 *
 * @author Mo
 */
public class GameMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Variables
        JFrame window = new JFrame("Arrange Me!");
        GamePanel game = new GamePanel();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(game);
        window.pack();
//        window.setLocation(70, 50);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);

        window.setAlwaysOnTop(true);   
    }
    
}
