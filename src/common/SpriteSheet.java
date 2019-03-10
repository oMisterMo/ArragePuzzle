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
package common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Spritesheet class stores a single BufferedImage which is used to draw
 * various sprites to the screen.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class SpriteSheet {

    private BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage image) {
        this.spriteSheet = image;
    }

    public SpriteSheet(String path) {
        try {
//            spriteSheet = ImageIO.read(new File(path));
            spriteSheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            System.out.println("Can not open spritesheet");
        }
    }

    public BufferedImage getTile(int x, int y, int width, int height) {
        BufferedImage tile = spriteSheet
                .getSubimage((x * width) - width, (y * height) - height,
                        width, height);
        return tile;
    }

    public BufferedImage getPosition(int x, int y, int width, int height) {
        BufferedImage tile = spriteSheet
                .getSubimage(x, y,
                        width, height);
        return tile;
    }

    public static BufferedImage getPosition(BufferedImage sheet,
            int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }

    /* **** GETTERS & SETTERS **** */
    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }
}
