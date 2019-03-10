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
 * A Vector is used to represent quantities that have both magnitude and
 * direction
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class Vector2D {

    public static final float PI = (float) Math.PI;

    public static float TO_DEGREES = (1 / PI) * 180;
    public static float TO_RADIANS = (1 / 180.0f) * PI;

    //Magnitude
    public float x;
    public float y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v2) {
        this.x = v2.x;
        this.y = v2.y;
    }

    /*Getters & Setters*/
    public Vector2D cpy() {
        return new Vector2D(x, y);
    }

    public Vector2D set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2D set(Vector2D v2) {
        this.x = v2.x;
        this.y = v2.y;
        return this;
    }

    public Vector2D add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D add(Vector2D v2) {
        this.x += v2.x;
        this.y += v2.y;
        return this;
    }

    public Vector2D sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2D sub(Vector2D v2) {
        this.x -= v2.x;
        this.y -= v2.y;
        return this;
    }

    public Vector2D mult(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float lengthSqr() {
        //Quicker to remove square root for distance comparison
        return this.x * this.x + this.y * this.y;
    }

    /**
     * When normalised, a vector keeps the same direction but its length is 1.0.
     *
     * @return vector
     */
    public Vector2D normalize() {
        //Unit vector has a length of 1
        float length = length();
        if (length != 0) {
            this.x /= length;
            this.y /= length;
        }
        return this;
    }

    public float angle() {
        //Can find angle if we know x and y components of Vector

        //System.out.println("degrees:"+ (Math.atan2(y, x) * 180)/Math.PI);
        float angle = (float) Math.atan2(this.y, this.x) * TO_DEGREES;
        //Ensures range is in 0 -> 360
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public Vector2D rotate(float angle) {
        //Rotates around the origin by the given angle
        float rad = angle * TO_RADIANS;
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        float newX = this.x * cos - this.y * sin;
        float newY = this.x * sin + this.y * cos;
        this.x = newX;
        this.y = newY;

        return this;
    }

    public float distance(Vector2D v2) {
        //Gets the distance between this vector and another point
        float dx = this.x - v2.x;
        float dy = this.y - v2.y;

        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float distance(float x, float y) {
        float dx = this.x - x;
        float dy = this.y - y;

        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float distanceSqr(Vector2D v2) {
        //For efficient calculations
        float dx = x - v2.x;
        float dy = y - v2.y;

        return dx * dx + dy * dy;
    }

    public float distanceSqr(float x, float y) {
        float dx = this.x - x;
        float dy = this.y - y;

        return dx * dx + dy * dy;
    }   //end book methods

    /* My methods */
    public void setAngle(float angle) {
        //If we have hypotenuse and angle, we can find x and y components of Vector
        float length = this.length();
        this.x = (float) Math.cos(angle) * length;
        this.y = (float) Math.sin(angle) * length;
    }

    public void setLength(float length) {
        float angle = this.angle();
        this.x = (float) Math.cos(angle) * length;
        this.y = (float) Math.sin(angle) * length;
    }

    public float dotProduct(Vector2D vec) {
        return this.x * vec.x + this.y * vec.y;
    }

    //-----------------Working with other vectors-------------------------
//    /* Returns a new vector so we can override methods
//     e.g.
//     v3 = v1 + v2
//     instead of
//     v3 = v1.add(v2)
//     */
//    public Vector2D add(Vector2D v2) {
////        x += v2.x;
////        y += v2.y;
//        Vector2D v3 = new Vector2D(x + v2.x, y + v2.y);
//        return v3;
//    }
//
//    public static Vector2D add(Vector2D v1, Vector2D v2) {
//        Vector2D temp = new Vector2D();
//
//        temp.x = v1.x + v2.x;
//        temp.y = v2.x + v2.y;
//        return temp;
//    }
//
//    public Vector2D sub(Vector2D v2) {
//        Vector2D v3 = new Vector2D(x - v2.x, y - v2.y);
//        return v3;
//    }
//
//
//    public Vector2D mult(float val) {
//        Vector2D v3 = new Vector2D(x * val, y * val);
//        return v3;
//    }
//
//
//    public Vector2D div(float val) {
//        Vector2D v3 = new Vector2D(x / val, y / val);
//        return v3;
//    }
    /*               EXTRAS                         */
    public static Vector2D add(Vector2D v1, Vector2D v2) {
        Vector2D temp = new Vector2D();
        temp.x = v1.x + v2.x;
        temp.y = v2.x + v2.y;
        return temp;
    }

    public static Vector2D sub(Vector2D v1, Vector2D v2) {
        Vector2D temp = new Vector2D();
        temp.x = v1.x - v2.x;
        temp.y = v2.x - v2.y;
        return temp;
    }

    public static Vector2D mult(Vector2D vec, float val) {
        Vector2D v3 = new Vector2D(vec.x * val, vec.y * val);
        return v3;
    }

    public static float distance(Vector2D v1, Vector2D v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        float dis = (float) Math.sqrt(dx * dx + dy * dy);
        return dis;
    }

    public static Vector2D nomalize(Vector2D vec) {
        Vector2D temp = new Vector2D();
        float length = vec.length();
        temp.x = (vec.x / length);
        temp.y = (vec.y / length);
        return temp;
    }

    public static Vector2D momentum(Vector2D vel, float mass) {
        /*
         * Calculates the momentum of an object
         * F = MA
         * F = M(vf - vi)/t
         * F = (mvf - mvi)/t
         */
        Vector2D temp;
        temp = mult(vel, mass);
        return temp;
    }

    /**
     * Impulse between two vectors
     *
     * @param finalVel in meters per second
     * @param initialVel in meters per second
     * @param mass in kilograms
     * @return final momentum - initial momentum
     */
    public Vector2D impulse(Vector2D finalVel, Vector2D initialVel, float mass) {
        /*
         * Finds out the impulse given a mass and change in velocity
         * A force delivered in a very small amount of time
         * F = (changeOf P)/t
         * Ft = (changeOf P)
         * F = net force t = very small time
         */
        Vector2D impulse;
        Vector2D finalMomentum, initialMomentum;
        //Get final and initial momentum
        finalMomentum = momentum(finalVel, mass);
        initialMomentum = momentum(initialVel, mass);

        impulse = sub(finalMomentum, initialMomentum);
        return impulse;
    }

    public static float dotProduct(Vector2D v1, Vector2D v2) {
        /*
         * 1. v1 dot v2 = 0, the two vectors are perpendicular 2. v1 dot v2 <
         * 0(negative), Θ > 90 degree 3. v1 dot v2 > 0(positive), Θ < 90 degree
         * ..................>
         * Θ is the angle between vectors v1 and v2
         */
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static Vector2D nonAxisAlignedCollision(Vector2D v1, Vector2D v2) {
        Vector2D temp;
        Vector2D temp2;
        Vector2D temp3;
        Vector2D length, reflection;
        float projection;

        temp = nomalize(v1);
        temp2 = mult(v2, -1);

        projection = dotProduct(temp2, temp);

        length = mult(temp, projection);

        reflection = mult(length, 2);

        temp3 = add(mult(reflection, -1), temp2);

        return temp3;
    }

    @Override
    public String toString() {
        /*Remove int*/
        return "x: " + this.x + ",  y: " + this.y;
    }
}
