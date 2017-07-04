/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrangepuzzle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 20-Jun-2017, 18:26:42.
 *
 * @author Mo
 */
public class ImgTile extends Tile {
    protected BufferedImage img;

    public ImgTile(int x, int y, int width, int height, int id, BufferedImage img) {
        super(x, y, width, height, id);
        this.img = img;
    }

    @Override
    public void gameUpdate(float deltaTime) {
        super.gameUpdate(deltaTime);    //Does nothing
    }

    @Override
    public void gameRender(Graphics2D g) {
        super.gameRender(g);            //Draws tile
//        g.drawImage(img, x + World.xShift, y + World.yShift, null);
    }

    
}
