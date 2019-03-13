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

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Tile class represents the building blocks to the sliding puzzle game. The
 * {@link #tileNum tileNum} field represents a unique value ranging from 1 - N
 * where N represents the number of tiles.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class Tile extends GameObject {

    public static int TILE_WIDTH = World.BOARD_WIDTH / World.NO_OF_TILES_X;
    public static int TILE_HEIGHT = World.BOARD_HEIGHT / World.NO_OF_TILES_Y;

    //Tile types
    public static final int TILE_EMPTY = 0;
    public static final int TILE_DIGIT = 1;
    public static final int TILE_IMAGE = 2;
    public int tileType = TILE_EMPTY;

    //Unique value representing the position
    public int tileNum = -1;

    public int x, y, width, height;

    /**
     * Constructs a new tile at the position given. The type represents what to
     * overlay the tile with (empty/number/image).
     *
     * @param x the x position
     * @param y the y position
     * @param width the width of the tile
     * @param height the height of the tile
     * @param type the tile overlay
     */
    public Tile(int x, int y, int width, int height, int type, int tileNum) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.tileType = type;
        this.tileNum = tileNum;
    }

    @Override
    public void gameUpdate(float deltaTime) {

    }

    @Override
    public void gameRender(Graphics2D g) {
        //Draw background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x + World.xShift, y + World.yShift, width, height);
        //Draw border
        g.setColor(Color.GRAY);
        g.drawRect(x + World.xShift, y + World.yShift, width, height);
    }
}
