/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrangepuzzle;

import java.awt.Graphics2D;

/**
 * Represents any object which is drawn to the screen.
 *
 * Possible subclasses: -> Tile -> Particle -> Player -> Square
 *
 * ALT + SHIFT + F = auto re-factor
 *
 * 16/05/16
 *
 * @author Mo
 */
public interface GameObject {

    abstract void gameUpdate(float deltaTime);

    abstract void gameRender(Graphics2D g);
}
