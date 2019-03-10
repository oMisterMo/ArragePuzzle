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

/**
 * A Rectangle specifies an area in 2d space that is enclosed by the Rectangles
 * object's upper-left point (x,y), width and height.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class Rectangle {

    public final Vector2D topLeft;
    public float width, height;

    public Rectangle(float x, float y, float width, float height) {
        this.topLeft = new Vector2D(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rect:  x " + topLeft.x + ", y " + this.topLeft.y;
    }
}
