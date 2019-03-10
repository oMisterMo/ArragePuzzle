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

/**
 * The Animation class holds a list of BufferedImages and the meta data
 * associated with the current Animation.
 *
 * @author Mohammed Ibrahim
 */
public class Animation {

    private BufferedImage[] frames;    //Sprite array of current animation
    private int currentFrame;

    private long startTime;
    private long delay;
    private boolean playedOnce; //Some animation are only played once

    /**
     * Sets the frame array to null, it must be initialised with the setFrames
     * method before it can be used.
     */
    public Animation() {
        frames = null;
    }

    /**
     * Updates the frame count based on the elapsed time.
     *
     * @param deltaTime time since last tick
     */
    public void update(float deltaTime) {
        long elapsed = (System.nanoTime() - startTime) / 1_000_000; //ns -> milli

        if (elapsed > delay) {
            currentFrame++;
//            currentFrame += delay * deltaTime;
            startTime = System.nanoTime();
        }
        //Resets frame count after full play through
        if (currentFrame >= frames.length) {
            currentFrame = 0;
            playedOnce = true;
        }
    }

    /**
     * Gets an image from the {@link #frames frames} list based on the current
     * frame.
     *
     * @return a sprite from the animation
     */
    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    /**
     * Assigns a bitmap array to this list.
     *
     * @param frames array of images
     */
    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    /**
     * Sets the time delay of the current animation.
     *
     * @param delay delay in milliseconds
     */
    public void setDelay(long delay) {
        this.delay = delay;
    }

    /**
     * Checks whether or not the animation has played once. The animation is
     * played fully when the current frame is greater than or equal to the size
     * of the frames array.
     *
     * @return true if played once
     */
    public boolean playedOnce() {
        return playedOnce;
    }

    /**
     * Resets the current frame to the first index of the BufferedImage array.
     */
    public void resetAnimation() {
        currentFrame = 0;
    }
}
