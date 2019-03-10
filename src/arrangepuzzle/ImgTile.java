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
import java.awt.image.BufferedImage;

/**
 * @version 0.1.0
 * @author Mohammed Ibrahim
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
        super.gameRender(g);            //Draws tile border
//        g.drawImage(img, x + World.xShift, y + World.yShift, null);
    }

}
