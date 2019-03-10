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

import java.awt.Graphics2D;

/**
 * Represents any object which is drawn to the screen.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public abstract class GameObject {

    /**
     * Updates the current game object
     *
     * @param deltaTime time since last frame
     */
    abstract void gameUpdate(float deltaTime);

    /**
     * Given a graphics context, would render basic shape/text/images
     *
     * @param g graphics object
     */
    abstract void gameRender(Graphics2D g);
}
