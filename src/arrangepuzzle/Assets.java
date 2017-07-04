/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrangepuzzle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Holds all (currently only spikeBlocks)
 * 
 * 1.Store each SpriteSheet once
 * 2.Store each Animation
 * 3.Store each Image
 *
 * 11-Jan-2017, 12:25:37.
 *
 * @author Mo
 */
public class Assets {

    //Mario liker payer sheet
    public static BufferedImage numbers;
    
    public static BufferedImage gameImage;
    public static BufferedImage iroh;
    public static BufferedImage mo;
    public static BufferedImage roundGrass;
    public static BufferedImage random;

    //Background
//    public static BufferedImage cloud;

    public Assets() {
        //Loading all tiles
        System.out.println("Loading tiles...");
        loadImages();
    }

    public static void loadImages() {
        try {
            numbers = ImageIO.read(new File("assets\\numbers.png"));
            gameImage = ImageIO.read(new File("assets\\slowpoke.png"));
            iroh = ImageIO.read(new File("assets\\iroh.png"));
            mo = ImageIO.read(new File("assets\\mo.jpg"));
            roundGrass = ImageIO.read(new File("assets\\roundGrass.jpg"));
            random = ImageIO.read(new File("assets\\img1.jpg"));
//            cloud = ImageIO.read(new File("assets\\cloud1.png"));

        } catch (IOException e) {
            System.out.println("Error loading assets (images)...");
        }
    }
}
