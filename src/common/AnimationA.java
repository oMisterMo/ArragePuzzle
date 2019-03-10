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
 * The AnimationA class is mainly used by Android applications
 *
 * @author Mohammed Ibrahim
 */
public final class AnimationA {

    public static final int ANIMATION_LOOPING = 0;
    public static final int ANIMATION_NON_LOOPING = 1;

    final BufferedImage[] keyFrames;
    final float frameDuration;

    public AnimationA(float frameDuration, BufferedImage... keyFrames) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
    }

    public BufferedImage getKeyFrame(float startTime, int mode) {
        int frameNumber = (int) (startTime / frameDuration);
        if (mode == ANIMATION_NON_LOOPING) {
            frameNumber = Math.min(keyFrames.length - 1, frameNumber);
        } else {
            frameNumber = frameNumber % keyFrames.length;
        }
        return keyFrames[frameNumber];
    }
}
