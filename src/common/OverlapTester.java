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
 * The OverlapTester class provides operations to produce narrow phase collision
 * detection on basic Rectangle and Circle objects.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class OverlapTester {

    public static boolean overlapCircles(Circle c1, Circle c2) {
        float distance = c1.center.distanceSqr(c2.center);
        float radiusSum = c1.radius + c2.radius;
        return distance <= radiusSum * radiusSum;
    }

    public static boolean overlapRectangles(Rectangle r1, Rectangle r2) {
        return r1.topLeft.x < r2.topLeft.x + r2.width
                && r1.topLeft.x + r1.width > r2.topLeft.x
                && r1.topLeft.y < r2.topLeft.y + r2.height
                && r1.topLeft.y + r1.height > r2.topLeft.y;
    }

    public static boolean overlapCircleRectangle(Circle c, Rectangle r) {
        float nearestX = c.center.x;
        float nearestY = c.center.y;

        //circle is on the left
        if (c.center.x < r.topLeft.x) {
            nearestX = r.topLeft.x;
        } else if (c.center.x > r.topLeft.x + r.width) {
            //circle is on the right
            nearestX = r.topLeft.x + r.width;
        }
        //circle is above
        if (c.center.y < r.topLeft.y) {
            nearestY = r.topLeft.y;
        } else if (c.center.y > r.topLeft.y + r.height) {
            //circle in below
            nearestY = r.topLeft.y + r.height;
        }

        return c.center.distanceSqr(nearestX, nearestY) < c.radius * c.radius;
    }

    public static boolean pointInCircle(Circle c, Vector2D p) {
        return c.center.distanceSqr(p) < c.radius * c.radius;
    }

    public static boolean pointInCircle(Circle c, float x, float y) {
        return c.center.distanceSqr(x, y) < c.radius * c.radius;
    }

    public static boolean pointInRectangle(Rectangle r, Vector2D p) {
        return r.topLeft.x <= p.x && r.topLeft.x + r.width >= p.x
                && r.topLeft.y <= p.y && r.topLeft.y + r.height >= p.y;
    }

    public static boolean pointInRectangle(Rectangle r, float x, float y) {
        return r.topLeft.x <= x && r.topLeft.x + r.width >= x
                && r.topLeft.y <= y && r.topLeft.y + r.height >= y;
    }
}
