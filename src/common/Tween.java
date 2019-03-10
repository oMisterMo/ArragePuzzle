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
 * The Tween class is used to represent Robert Penner's easing functions.
 *
 * Class is final so it can not be extended
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public final class Tween {

    /**
     * Can not instantiate tween class
     */
    private Tween() {
    }

    //All methods are static
    /**
     * Linearly interpolate the values passed into b and c
     *
     * b := (start) c := (end - start)
     *
     * @param t current time (0 - duration)
     * @param b beginning value (initial number)
     * @param c change in value (final - initial)
     * @param d duration (how long in ms to run)
     *
     * @return new position at current time
     */
    public static double linearTween(double t, double b, double c, double d) {
        return c * t / d + b;
    }

    /*----------------------------Cubic-------------------------------------*/
    public static double easeInCubic(double t, double b, double c, double d) {
        return c * (t /= d) * t * t + b;
    }

    public static double easeOutCubic(double t, double b, double c, double d) {
        return c * ((t = t / d - 1) * t * t + 1) + b;
    }

    public static double easeInOutCubic(double t, double b, double c, double d) {
        if ((t /= d / 2) < 1) {
            return c / 2 * t * t * t + b;
        }
        return c / 2 * ((t -= 2) * t * t + 2) + b;
    }

    /*----------------------------Quad-------------------------------------*/
    public static double easeInQuad(double t, double b, double c, double d) {
        return c * (t /= d) * t + b;
    }

    public static double easeOutQuad(double t, double b, double c, double d) {
        return -c * (t /= d) * (t - 2) + b;
    }

    public static double easeInOutQuad(double t, double b, double c, double d) {
        if ((t /= d / 2) < 1) {
            return c / 2 * t * t + b;
        }
        return -c / 2 * ((--t) * (t - 2) - 1) + b;
    }

    /*----------------------------Elastic------------------------------------*/
    public static double easeInElastic(double t, double b, double c, double d) {
        if (t == 0) {
            return b;
        }
        if ((t /= d) == 1) {
            return b + c;
        }
        double p = d * .3f;
        double a = c;
        double s = p / 4;
        return -(a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
    }

    public static double easeOutElastic(double t, double b, double c, double d) {
        if (t == 0) {
            return b;
        }
        if ((t /= d) == 1) {
            return b + c;
        }
        double p = d * .3f;
        double a = c;
        double s = p / 4;
        return (a * Math.pow(2, -10 * t) * Math.sin((t * d - s) * (2 * Math.PI) / p) + c + b);
    }

    public static double easeInOutElastic(double t, double b, double c, double d) {
        if (t == 0) {
            return b;
        }
        if ((t /= d / 2) == 2) {
            return b + c;
        }
        double p = d * (.3f * 1.5f);
        double a = c;
        double s = p / 4;
        if (t < 1) {
            return -.5f * (a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
        }
        return a * Math.pow(2, -10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p) * .5f + c + b;
    }

    /*-----------------------------Bounce-------------------------------------*/
    public static double easeInBounce(double t, double b, double c, double d) {
        return c - easeOutBounce(d - t, 0, c, d) + b;
    }

    public static double easeOutBounce(double t, double b, double c, double d) {
        if ((t /= d) < (1 / 2.75f)) {
            return c * (7.5625f * t * t) + b;
        } else if (t < (2 / 2.75f)) {
            return c * (7.5625f * (t -= (1.5f / 2.75f)) * t + .75f) + b;
        } else if (t < (2.5 / 2.75)) {
            return c * (7.5625f * (t -= (2.25f / 2.75f)) * t + .9375f) + b;
        } else {
            return c * (7.5625f * (t -= (2.625f / 2.75f)) * t + .984375f) + b;
        }
    }

    public static double easeInOutBounce(double t, double b, double c, double d) {
        if (t < d / 2) {
            return easeInBounce(t * 2, 0, c, d) * .5f + b;
        } else {
            return easeOutBounce(t * 2 - d, 0, c, d) * .5f + c * .5f + b;
        }
    }

    /*-----------------------------Back-------------------------------------*/
    public static double easeInBack(double t, double b, double c, double d) {
        double s = 1.70158f;
        return c * (t /= d) * t * ((s + 1) * t - s) + b;
    }

    public static double easeOutBack(double t, double b, double c, double d) {
        double s = 1.70158f;
        return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
    }

    public static double easeInOutBack(double t, double b, double c, double d) {
        double s = 1.70158f;
        if ((t /= d / 2) < 1) {
            return c / 2 * (t * t * (((s *= (1.525f)) + 1) * t - s)) + b;
        }
        return c / 2 * ((t -= 2) * t * (((s *= (1.525f)) + 1) * t + s) + 2) + b;
    }
    //End ease functions

}
