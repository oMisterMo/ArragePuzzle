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

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * The Helper class stores common operations .
 *
 * This class is not meant to be instantiated, all methods are static and must
 * be called via class reference.
 *
 * Optimisation :- Hash the result of all sin operations
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public final class Helper {

    private static final Random r = new Random();

    private Helper() {
    }

    public static float Clamp(float val, float min, float max) {
        if (val < min) {
            return min;
        } else if (val > max) {
            return max;
        }
        //We have a value within the range
        return val;
    }

    public static int Clamp(int val, int min, int max) {
        if (val < min) {
            return min;
        } else if (val > max) {
            return max;
        }
        //We have a value within the range
        return val;
    }

    /**
     * Gets the Sign of the number given
     *
     * @param x float to evaluate
     * @return Returns 1 or -1 depending on state of input
     */
    public static int Sign(float x) {
        return (x < 0) ? -1 : 1;
    }

    /**
     * Returns a random number between 0 and 1
     *
     * @return random decimal number
     */
    public static float Random() {
        return r.nextFloat();
    }

    /**
     * Return a whole number that is greater than or equal to min and is less
     * than or equal to max
     *
     * @param min minimum value (inclusive)
     * @param max maximum value (inclusive)
     * @return random value between the range (min, max)
     */
    public static int Random(int min, int max) {
        /*
         When Math.Random is called():
         Random rnd = randomNumberGenerator;
         if (rnd == null) rnd = initRNG();
         return rnd.nextDouble();
        
         It is slighty more efficient to use own random object
         */

        return (int) Math.floor(r.nextFloat() * (max + 1 - min) + min);
//        return r.nextInt(max - min + 1) + min;
    }

    /**
     * Returns a random decimal number that is within the given range.
     *
     *
     * floor() Returns the largest (closest to positive infinity) double value
     * that is less than or equal to the argument and is equal to a mathematical
     * integer. Special cases:
     *
     * 1. If the argument value is already equal to a mathematical integer, then
     * the result is the same as the argument.
     *
     * 2. If the argument is NaN or an infinity or positive zero or negative
     * zero, then the result is the same as the argument. No error checks
     * (number, range)
     *
     * @param min minimum value (inclusive)
     * @param max maximum value (exclusive)
     * @return random value between min and max
     */
    public static float Random(float min, float max) {
        return (r.nextFloat() * (max - min)) + min;
    }

    /**
     * Scales the BufferedImage up or down depending on the new size.
     *
     * @param img BufferedImage to scale
     * @param newW new width of the image
     * @param newH new height of the image
     * @return the scaled BufferedImage
     */
    public static BufferedImage ResizeImg(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();     //old width
        int h = img.getHeight();    //old height
        BufferedImage desImg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = desImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return desImg;
    }
}
