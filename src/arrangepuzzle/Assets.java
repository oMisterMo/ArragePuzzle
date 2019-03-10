/* 
 * Copyright (C) 2019 Mohammed Ibrahim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package arrangepuzzle;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
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

    public void loadImages() {
        try {
            numbers = ImageIO.read(getClass().getResource("/assets/numbers.png"));
            gameImage = ImageIO.read(getClass().getResource("/assets/slowpoke.png"));
            iroh = ImageIO.read(getClass().getResource("/assets/iroh.png"));
            mo = ImageIO.read(getClass().getResource("/assets/mo.jpg"));
            roundGrass = ImageIO.read(getClass().getResource("/assets/roundGrass.jpg"));
            random = ImageIO.read(getClass().getResource("/assets/img1.jpg"));
//            cloud = ImageIO.read(getClass().getResource("/assets/cloud1.png"));

        } catch (IOException e) {
            System.out.println("Error loading assets (images)...");
        }
    }
}
