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
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import common.Helper;

/**
 * The World class ties together all classes to simulate the sliding puzzle
 * game.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class World extends GameObject {

    public static final int BOARD_WIDTH = 320;
    public static final int BOARD_HEIGHT = 320;
    public static final int NO_OF_TILES_X = 3;
    public static final int NO_OF_TILES_Y = 3;
    public static final int NO_OF_LEVELS = 1;

    //Array holding all tiles
    private Tile[][] tiles;
    private Point blankPoint;
    private Random r;

    //Moves the world (x, y) units
    public static int xShift = 0;
    public static int yShift = 0;

    //Time gone by from start of application
    float elapsed = 0;

    /**
     * Initialise and create a new 3x3 world on a 320 x 320 pixel board.
     */
    public World() {
        tiles = new Tile[NO_OF_TILES_Y][NO_OF_TILES_X];
        System.out.println("No x tiles: " + NO_OF_TILES_X);
        System.out.println("No y tiles: " + NO_OF_TILES_Y);

//        int type = Tile.TILE_DIGIT;       //type of tiles to load
        int type = Tile.TILE_IMAGE;         //type of tiles to load
        nullTiles();   //sets to null
        initTiles(type);     //create empty tiles and sets default position

        loadLevel();    //randomise level

        //Set the shift amount
        xShift = GamePanel.GAME_WIDTH / 2
                - (NO_OF_TILES_X * Tile.TILE_WIDTH) / 2;
        yShift = GamePanel.GAME_HEIGHT / 2
                - (NO_OF_TILES_Y * Tile.TILE_HEIGHT) / 2;
        System.out.println("xShift: " + xShift);
        System.out.println("yShift: " + yShift);
        r = new Random();
    }

    private void nullTiles() {
        System.out.println("Setting all tiles to null...");
        for (int i = 0; i < NO_OF_TILES_Y; i++) {
            for (int j = 0; j < NO_OF_TILES_X; j++) {
                tiles[i][j] = null;
            }
        }
    }

    private void initTiles(int tileType) {
        System.out.println("Initilising tiles....");
        Tile.TILE_WIDTH = BOARD_WIDTH / NO_OF_TILES_X;
        Tile.TILE_HEIGHT = BOARD_HEIGHT / NO_OF_TILES_Y;

        switch (tileType) {
            case Tile.TILE_DIGIT:
                System.out.println("Numbered tiles....");
                initNumTiles();
                break;
            case Tile.TILE_IMAGE:
                System.out.println("Image tiles....");
                initImageTiles();
                break;
        }
    }

    private void initNumTiles() {
        int id = 1;
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                tiles[y][x] = new Tile(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT,
                        Tile.TILE_WIDTH, Tile.TILE_HEIGHT,
                        Tile.TILE_DIGIT, id);
                id++;
            }
        }
        //Set last tile to empty
        blankPoint = new Point(NO_OF_TILES_X - 1, NO_OF_TILES_Y - 1);
        tiles[blankPoint.y][blankPoint.x].tileType = Tile.TILE_EMPTY;
        System.out.println("blank var: " + blankPoint);
    }

    private void initImageTiles() {
        //Scale image to 320 x 320
        BufferedImage scaledImage = reSizeImage(Assets.iroh);
        //Set smaller tiles
        int id = 1;
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                //Split up image
                BufferedImage tile = scaledImage
                        .getSubimage(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT,
                                Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
                tiles[y][x] = new ImgTile(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT,
                        Tile.TILE_WIDTH, Tile.TILE_HEIGHT,
                        Tile.TILE_IMAGE, id, tile);
                id++;
            }
        }
        //Set last tile to empty
        blankPoint = new Point(NO_OF_TILES_X - 1, NO_OF_TILES_Y - 1);
        tiles[blankPoint.y][blankPoint.x].tileType = Tile.TILE_EMPTY;
        System.out.println("blank var: " + blankPoint);
    }

    private BufferedImage reSizeImage(BufferedImage unscaled) {
        BufferedImage scaledImage;
        //do scale
        //attemp 1
//        scaledImage = Helper.scale(unscaled,
//                BufferedImage.TYPE_INT_ARGB, BOARD_WIDTH, BOARD_HEIGHT,
//                0.5, 0.5);
        //attemp 2
        scaledImage = Helper.ResizeImg(unscaled, 320, 320);
        return scaledImage;
    }

    private void resetBoard() {
        System.out.println("Setting all tiles to default position...");
    }

    /**
     * Randomises the board.
     */
    public void loadLevel() {
//        randomise();
    }

    /**
     * Incomplete. While n less than 100, pick a random direction (up, down,
     * left, right) and moves the empty tile.
     *
     * @param deltaTime time gone by
     */
    private void randomise() {
        System.out.println("SHUFFLE");
        int n = 0;
//        long start = System.currentTimeMillis();
//        long elapsed;
        while (n < 1000) {
            //Delay movement
//            elapsed = System.currentTimeMillis() - start;
//            System.out.println("elapsed: " + elapsed);
            //Move
            int ran = r.nextInt(4);
            switch (ran) {
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveRight();
                    break;
            }
            n++;
        }
    }

    private Tile getUpTile(Point p) {
        Point up = getUp(p);
        if (withinBounds(up)) {
//            System.out.println("UP WITHIN BOUNDS");
            Tile t;
            t = tiles[up.y][up.x];
//            System.out.println("\n\ntile above: " + p + "= " + up);
            return t;
        }
        System.out.println("Returning NULL");
        return null;
    }

    private Tile getDownTile(Point p) {
        Point down = getDown(p);
        if (withinBounds(down)) {
//            System.out.println("DOWN WITHIN BOUNDS");
            Tile t;
            t = tiles[down.y][down.x];
            return t;
        }
        System.out.println("Returning NULL");
        return null;
    }

    private Tile getLeftTile(Point p) {
        Point left = getLeft(p);
        if (withinBounds(left)) {
//            System.out.println("LEFT WITHIN BOUNDS");
            Tile t;
            t = tiles[left.y][left.x];
            return t;
        }
        System.out.println("Returning NULL");
        return null;
    }

    private Tile getRightTile(Point p) {
        Point right = getRight(p);
        if (withinBounds(right)) {
//            System.out.println("RIGHT WITHIN BOUNDS");
            Tile t;
            t = tiles[right.y][right.x];
            return t;
        }
        System.out.println("Returning NULL");
        return null;
    }

    private Point getUp(Point p) {
        return new Point(p.x, p.y - 1);
    }

    private Point getDown(Point p) {
        return new Point(p.x, p.y + 1);
    }

    private Point getLeft(Point p) {
        return new Point(p.x - 1, p.y);
    }

    private Point getRight(Point p) {
        return new Point(p.x + 1, p.y);
    }

    private boolean withinBounds(int x, int y) {
        return x >= 0 && y >= 0
                && x < NO_OF_TILES_X && y < NO_OF_TILES_Y;
    }

    private boolean withinBounds(Point p) {
        return withinBounds(p.x, p.y);
    }

    /*  Getters & Setters */
    private void setTileId(int x, int y, int id) {
        if (id < 0 || id > Tile.TILE_IMAGE) {
            System.out.println("Error! Must be within valid tile range 0 - 2");
            return;
        }
        tiles[y][x].tileType = id;
    }

    private Tile getTile(int x, int y) {
        if (!withinBounds(x, y)) {
            return null;
        }
        //to do : optimize this method and use it instead of the other four
        return tiles[y][x];
    }

    private void moveUp() {
//        System.out.println("MOVE UP");
        //Get blank and above tiles
        Tile blankTile = tiles[blankPoint.y][blankPoint.x];
        Tile aboveTile = getUpTile(blankPoint);
        //Get blank and above points
        Point abovePoint = getUp(blankPoint);

        if (withinBounds(abovePoint)) {
            //Swap tiles positions
            tiles[blankPoint.y][blankPoint.x].y -= Tile.TILE_HEIGHT;    //blank tile goes up
            tiles[abovePoint.y][abovePoint.x].y += Tile.TILE_HEIGHT;    //tile above blank goes down
            //Swap tiles 
            tiles[blankPoint.y][blankPoint.x] = aboveTile;
            tiles[abovePoint.y][abovePoint.x] = blankTile;
            //Update blank point
            blankPoint.y -= 1;
        }
    }

    private void moveDown() {
//        System.out.println("MOVE DOWN");
        //Get blank and below tiles
        Tile blankTile = tiles[blankPoint.y][blankPoint.x];
        Tile belowTile = getDownTile(blankPoint);
        //Get blank and below points
        Point belowPoint = getDown(blankPoint);

        if (withinBounds(belowPoint)) {
            //Swap tiles positions
            tiles[blankPoint.y][blankPoint.x].y += Tile.TILE_HEIGHT;
            tiles[belowPoint.y][belowPoint.x].y -= Tile.TILE_HEIGHT;
            //Swap tiles 
            tiles[blankPoint.y][blankPoint.x] = belowTile;
            tiles[belowPoint.y][belowPoint.x] = blankTile;
            //Update blank point
            blankPoint.y += 1;
        }
    }

    private void moveLeft() {
//        System.out.println("MOVE LEFT");
        //Get blank and left tiles
        Tile blankTile = tiles[blankPoint.y][blankPoint.x];
        Tile leftTile = getLeftTile(blankPoint);
        //Get blank and above points
        Point leftPoint = getLeft(blankPoint);

        if (withinBounds(leftPoint)) {
            //Swap tiles positions
            tiles[blankPoint.y][blankPoint.x].x -= Tile.TILE_WIDTH;
            tiles[leftPoint.y][leftPoint.x].x += Tile.TILE_WIDTH;
            //Swap tiles 
            tiles[blankPoint.y][blankPoint.x] = leftTile;
            tiles[leftPoint.y][leftPoint.x] = blankTile;
            //Update blank point
            blankPoint.x -= 1;
        }
    }

    private void moveRight() {
//        System.out.println("MOVE RIGHT");
        //Get blank and right tiles
        Tile blankTile = tiles[blankPoint.y][blankPoint.x];
        Tile rightTile = getRightTile(blankPoint);
        //Get blank and right points
        Point rightPoint = getRight(blankPoint);

        if (withinBounds(rightPoint)) {
            //Swap tiles positions
            tiles[blankPoint.y][blankPoint.x].x += Tile.TILE_WIDTH;
            tiles[rightPoint.y][rightPoint.x].x -= Tile.TILE_WIDTH;
            //Swap tiles 
            tiles[blankPoint.y][blankPoint.x] = rightTile;
            tiles[rightPoint.y][rightPoint.x] = blankTile;
            //Update blank point
            blankPoint.x += 1;
        }
    }

    /**
     * All input is handled by the World class.
     */
    public void handleInput() {
        if (Input.isKeyPressed(KeyEvent.VK_R)) {
            randomise();
            return;
        }
        if (Input.isKeyTyped(KeyEvent.VK_W) || Input.isKeyTyped(KeyEvent.VK_I)
                || Input.isKeyTyped(KeyEvent.VK_UP)) {
            moveDown();
        }
        if (Input.isKeyTyped(KeyEvent.VK_S) || Input.isKeyTyped(KeyEvent.VK_K)
                || Input.isKeyTyped(KeyEvent.VK_DOWN)) {
            moveUp();
        }
        if (Input.isKeyTyped(KeyEvent.VK_A) || Input.isKeyTyped(KeyEvent.VK_J)
                || Input.isKeyTyped(KeyEvent.VK_LEFT)) {
            moveRight();
        }
        if (Input.isKeyTyped(KeyEvent.VK_D) || Input.isKeyTyped(KeyEvent.VK_L)
                || Input.isKeyTyped(KeyEvent.VK_RIGHT)) {
            moveLeft();
        }
        if (Input.isKeyTyped(KeyEvent.VK_B)) {
            //Print debug info to console 
            System.out.println("***blank information***");
            System.out.println("tiles[blankPoint.y][blankPoint.x]: " + tiles[blankPoint.y][blankPoint.x]);
            System.out.println("blank x: " + tiles[blankPoint.y][blankPoint.x].x);
            System.out.println("blank y: " + tiles[blankPoint.y][blankPoint.x].y);
            System.out.println("blank num: " + tiles[blankPoint.y][blankPoint.x].tileNum);
            System.out.println("blank type: " + tiles[blankPoint.y][blankPoint.x].tileType);
        }
    }

    /**
     * Handles left/right and middle click.
     *
     * @param e mouse event
     */
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");
        //DO SHIFING FIRST, THEN DRAW TO THE CENTER
        int touchX = e.getX() + World.xShift;
        int touchY = e.getY() + World.yShift;

        //1.Get the touch position
        //2.Check if its within a tiles bounding box
        //3.Move appropriate tile
    }

    /**
     * Handles the release of a mouse button.
     *
     * @param e mouse event
     */
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
    }

    /* ********************* UPDATE & RENDER ************************* */
    @Override
    public void gameUpdate(float deltaTime) {
        //If im moving any of the world tiles
        elapsed += deltaTime;
//        System.out.println("elapsed: " + elapsed);
    }

    @Override
    public void gameRender(Graphics2D g) {
        /* Called every frame */
        drawTiles(g);
    }

    private void drawTiles(Graphics2D g) {
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                //Draw (ugly) Border
                tiles[y][x].gameRender(g);

                //Draw tile
                if (tiles[y][x].tileType == Tile.TILE_DIGIT) {
                    g.setColor(Color.lightGray);
                    Tile t = tiles[y][x];
                    int size = 2;
                    g.fillRect(t.x + size / 2 + xShift, t.y + size / 2 + yShift,
                            t.width - size, t.height - size);

                    String tileNum = String.valueOf(tiles[y][x].tileNum);

                    drawText(g, tileNum, tiles[y][x].x + xShift,
                            tiles[y][x].y + yShift);
                } else if (tiles[y][x].tileType == Tile.TILE_IMAGE) {
                    g.setColor(Color.lightGray);    //white also works
                    ImgTile t = (ImgTile) tiles[y][x];
                    int size = 2;
                    g.fillRect(t.x + size / 2 + xShift, t.y + size / 2 + yShift,
                            t.width - size, t.height - size);

                    g.drawImage(t.img, t.x + size / 2 + xShift,
                            t.y + size / 2 + yShift, null);
                }
            }
        }
    }

    private void drawText(Graphics2D g, String line, int x, int y) {
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
                srcWidth = 15;
            }

            BufferedImage num;
            num = Assets.numbers.getSubimage(srcX, 0, srcWidth, 32);
            g.drawImage(num, x + num.getWidth() / 2, y + num.getHeight() / 5, null);

            x += srcWidth;
        }
    }

}
