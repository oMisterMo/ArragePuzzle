package arrangepuzzle;

import common.Vector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * 06-Jun-2017, 20:18:03.
 *
 * @author Mo
 */
public class Tile implements GameObject {

    //Fixed size or shall I make it dynamic?
    public static int TILE_WIDTH = World.BOARD_WIDTH / World.NO_OF_TILES_X;
    public static int TILE_HEIGHT = World.BOARD_HEIGHT / World.NO_OF_TILES_Y;

    //All type of tiles
    public static final int TILE_EMPTY = 0;
    public static final int TILE_DIGIT = 1;
    public static final int TILE_IMAGE = 2;
    public int tileType = TILE_EMPTY;

    //Unique value representing the position
    public int tileNum = -1;

    //Tile position and size
    public int x, y, width, height;

    //Tile hitbox
    public Rectangle hitbox;

    public Tile(int x, int y, int width, int height, int id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.hitbox = new Rectangle(x, y, width, height);

        this.tileType = id;
        loadImage();
    }

    /**
     * Depending on the id given, load the correct image
     *
     * @param path image path
     */
    private void loadImage() {
//        drawText("8", 0, 0);
    }

    private void drawText(String line, int x, int y) {
//        if(line is not a number){
//
//            return;
//        }
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
            if (character == ' ') {
                x += 20;
                continue;
            }
            int srcX;
            int srcWidth;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            BufferedImage num;
            num = Assets.numbers.getSubimage(srcX, 0, srcWidth, 32);
//            tileImg = num;

            x += srcWidth;
        }
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
