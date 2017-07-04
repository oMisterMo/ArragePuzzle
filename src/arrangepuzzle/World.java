package arrangepuzzle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import common.Vector2D;
import common.Helper;

/**
 * 16/05/2016
 *
 * @author Mo
 */
public class World implements GameObject {
//    public static final int NO_OF_TILES_X
//            = (GamePanel.GAME_WIDTH / Tile.TILE_WIDTH);       //20
//    public static final int NO_OF_TILES_Y
//            = (int) Math.round(GamePanel.GAME_HEIGHT / Tile.TILE_HEIGHT);     //12
//    public static final int NO_OF_TILES_Y = (GamePanel.GAME_HEIGHT / Tile.TILE_HEIGHT);     //12

    public static final int BOARD_WIDTH = 320;
    public static final int BOARD_HEIGHT = 320;

    public static final int NO_OF_TILES_X = 3;
    public static final int NO_OF_TILES_Y = 3;

    public static final int NO_OF_LEVELS = 1;

    //Array holding all tiles
    private Tile[][] tiles;
    //level to load
    private Random r = new Random();

    private Stroke stroke;
    //Moves the world (x, y) units
    public static int xShift = 0;
    public static int yShift = 0;

    //Hash map storing key(tile id),
    private Map<Integer, Point> orderedTiles;
    private Map<Integer, Point> allTiles;
    private Point blankPoint;

    //Time gone by from start of application
    float elapsed = 0;

    public World() {
        //Initial new world here
        tiles = new Tile[NO_OF_TILES_Y][NO_OF_TILES_X];
        System.out.println("No x tiles: " + NO_OF_TILES_X);
        System.out.println("No y tiles: " + NO_OF_TILES_Y);
//        tiles = new Tile[NO_OF_TILES_X][NO_OF_TILES_Y];

        //Initialise each Tile to empty
//        int type = Tile.TILE_DIGIT;     //type of tiles to load
        int type = Tile.TILE_IMAGE;     //type of tiles to load
        clearBoard();   //sets to null
        initTiles(type);     //create empty tiles and sets default position
        setTile(type);       //sets the tile type and tile id

        //Set up map info (to check if a level is completed)
        orderedTiles = new TreeMap<>();
        allTiles = new HashMap<>();

        loadLevel();    //randomise level
//        stroke = new BasicStroke(3);
    }

    /**
     * Sets all spikeBlocks to null
     */
    public void clearBoard() {
        System.out.println("Setting all tiles to null...");
        for (int i = 0; i < NO_OF_TILES_Y; i++) {
            for (int j = 0; j < NO_OF_TILES_X; j++) {
                tiles[i][j] = null;
            }
        }
    }

    /**
     * Called from the constructor, sets the position of all tiles
     */
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
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                tiles[y][x] = new Tile(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT,
                        Tile.TILE_WIDTH, Tile.TILE_HEIGHT,
                        Tile.TILE_EMPTY);
            }
        }
        blankPoint = new Point(NO_OF_TILES_X - 1, NO_OF_TILES_Y - 1);
        System.out.println("blank var: " + blankPoint);
    }

    /**
     * Split up large image into smaller tiles and set
     */
    private void initImageTiles() {
        //Scale image to 320 x 320
        BufferedImage scaledImage = reSizeImage(Assets.iroh);
        //Set smaller tiles
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                //Split up image
//                BufferedImage img = Assets.gameImage
//                BufferedImage img = Assets.mo
//                BufferedImage img = Assets.random
                BufferedImage tile = scaledImage
                        //                                                .getSubimage(x*Tile.TILE_WIDTH, y*Tile.TILE_HEIGHT, 
                        .getSubimage(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT,
                                Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
                tiles[y][x] = new ImgTile(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT,
                        Tile.TILE_WIDTH, Tile.TILE_HEIGHT,
                        Tile.TILE_EMPTY, tile);
            }
        }
        blankPoint = new Point(NO_OF_TILES_X - 1, NO_OF_TILES_Y - 1);
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
        scaledImage = Helper.resizeImg(unscaled, 320, 320);
        return scaledImage;
    }

    /**
     * Tiles are ordered 1 - number of tiles and set to numbered tile
     *
     * @param tileType type of tile
     */
    public void setTile(int tileType) {
        System.out.println("Setting the id and tile type of each tile...");

        int num = 1;
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                //Set all tiles to number
                if (tileType == Tile.TILE_DIGIT) {
                    tiles[y][x].tileType = Tile.TILE_DIGIT;
                } else if (tileType == Tile.TILE_IMAGE) {
                    tiles[y][x].tileType = Tile.TILE_IMAGE;
                }

                //Default position = arrange board inorder 1-n
                tiles[y][x].tileNum = num;
                num++;
            }
        }
        //Set last tile to empty
        tiles[blankPoint.y][blankPoint.x].tileType = Tile.TILE_EMPTY;
        tiles[blankPoint.y][blankPoint.x].tileNum = -1;
    }

    private void resetBoard() {
        System.out.println("Setting all tiles to default position...");
    }

    /**
     * Randomises the board
     */
    public void loadLevel() {
        xShift = GamePanel.GAME_WIDTH / 2 - (NO_OF_TILES_X * Tile.TILE_WIDTH) / 2;
        yShift = GamePanel.GAME_HEIGHT / 2 - (NO_OF_TILES_Y * Tile.TILE_HEIGHT) / 2;

//        randomise();
    }

    private void randomise(float deltaTime) {
        System.out.println("SHUFFLE");
        int n = 0;
        long start = System.currentTimeMillis();
        long elapsed;
        while (n < 100) {
            //Delay movement
            elapsed = System.currentTimeMillis() - start;
            System.out.println("elapsed: " + elapsed);
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

    private void movePiece() {

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

    /**
     * x >= 0, x < 8
     *
     * @param p
     * @return
     */
    public boolean withinBounds(Point p) {
        return p.x >= 0 && p.x < NO_OF_TILES_X && p.y >= 0 & p.y < NO_OF_TILES_Y;
    }

    /*  Getters & Setters */
    public void setTileId(int x, int y, int id) {
        if (id < 0 || id > Tile.TILE_IMAGE) {
            System.out.println("Error! Must be within valid tile range 0 - 2");
            return;
        }
        tiles[y][x].tileType = id;
    }

    /**
     * Gets the actual spikeBlock (if another class needs to access a
     * spikeBlock)
     *
     * @param y position
     * @param x position
     * @return tile at x, y
     */
    public Tile getTile(int y, int x) {
        return tiles[y][x];
    }

//    private void swap(Tile t1, Tile t2) {
//        System.out.println("swapping...");
//        Tile temp = t1; //save t1
//        t1 = t2;        //set t1 = t2
//        t2 = temp;      //set t2 = t1
//    }
    private void swap(Point p1, Point p2) {
//        System.out.println("swapping...");
//        Tile temp = tiles[p1.x][p1.y]; //save t1
//        Point tempPoint = p1;   System.out.println("p1: "+p1);
        Tile temp = tiles[p1.y][p1.x]; //save t1    //8,8
        System.out.println("*********************************");
        System.out.println(p1.x + ", " + p1.y + ": " + temp.tileType);
        System.out.println(p1.x + ", " + p1.y + ": " + temp.tileNum);
        Tile temp2 = tiles[p2.y][p2.x]; //save t1   //8, 7
        System.out.println(p2.x + ", " + p2.y + ": " + temp2.tileType);
        System.out.println(p2.x + ", " + p2.y + ": " + temp2.tileNum);
        System.out.println("*********************************");

        //p1 = P(8,8)   p2 = P(8,7)
        tiles[p1.y][p1.x] = tiles[p2.y][p2.x];        //set t1 = t2
//        tiles[p2.y][p2.x] = tiles[tempPoint.y][tempPoint.x];      //set t2 = t1
        tiles[p2.y][p2.x] = temp;

//        System.out.println("5, 5: "+tiles[5][5].tileNum);
//        Tile temp3 = tiles[8][8]; //save t1
//        System.out.println("*********************************");
//        System.out.println("8, 8: " + temp3.tileType);
//        System.out.println("8, 8: " + temp3.tileNum);
    }

    /* ************ CONTROLLER METHODs, CALLED FROM A PLAYER ************* */
    public void moveUp() {
//        System.out.println("MOVE UP");
        //Get blank and above tiles
        Tile blankTile = tiles[blankPoint.y][blankPoint.x];
        Tile aboveTile = getUpTile(blankPoint);
        //Get blank and above points
        Point abovePoint = getUp(blankPoint);

        if (withinBounds(abovePoint)) {
//            System.out.println("YES");
            //Swap tiles positions
            tiles[blankPoint.y][blankPoint.x].y -= Tile.TILE_HEIGHT;      //blank tile go up
            tiles[abovePoint.y][abovePoint.x].y += Tile.TILE_HEIGHT;    //tile above blank go down
            //Swap tiles 
            tiles[blankPoint.y][blankPoint.x] = aboveTile;
            tiles[abovePoint.y][abovePoint.x] = blankTile;
            //Update blank point
            blankPoint.y -= 1;
        } else {
//            System.out.println("NO");
        }

//        
//        //update blank position
//        blank = new Point(blank.x, blank.y - 1);
//        System.out.println("tile num: "+tiles[blank.y][blank.x].tileNum);
//        Point p = blank;
//        System.out.println("old blank: "+blank);
//        //If tile is on bottom row return
//        if (p.y == 0) {
//            //Do nothing
//            System.out.println("can't move DOWN...");
//        } else {
//            //else move up
//            swap(blank, getUp(p));
//            blank = new Point(blank.x, blank.y - 1);
//            tiles[blank.y][blank.x].tileType = Tile.TILE_EMPTY;
//            tiles[blank.y][blank.x].tileNum = tiles[p.y][p.x].tileNum;
//            System.out.println(tiles[blank.y][blank.x].tileType);
//            System.out.println("new blank: "+blank);
//        }
        //refresh board
//        setTile();
    }

    public void moveDown() {
//        System.out.println("MOVE DOWN");
        //Get blank and below tiles
        Tile blankTile = tiles[blankPoint.y][blankPoint.x];
        Tile belowTile = getDownTile(blankPoint);
        //Get blank and below points
        Point belowPoint = getDown(blankPoint);

        if (withinBounds(belowPoint)) {
//            System.out.println("YES");
            //Swap tiles positions
            tiles[blankPoint.y][blankPoint.x].y += Tile.TILE_HEIGHT;
            tiles[belowPoint.y][belowPoint.x].y -= Tile.TILE_HEIGHT;
            //Swap tiles 
            tiles[blankPoint.y][blankPoint.x] = belowTile;
            tiles[belowPoint.y][belowPoint.x] = blankTile;
            //Update blank point
            blankPoint.y += 1;
        } else {
//            System.out.println("NO");
        }
    }

    public void moveLeft() {
//        System.out.println("MOVE LEFT");
        //Get blank and left tiles
        Tile blankTile = tiles[blankPoint.y][blankPoint.x];
        Tile leftTile = getLeftTile(blankPoint);
        //Get blank and above points
        Point leftPoint = getLeft(blankPoint);

        if (withinBounds(leftPoint)) {
//            System.out.println("YES");
            //Swap tiles positions
            tiles[blankPoint.y][blankPoint.x].x -= Tile.TILE_WIDTH;
            tiles[leftPoint.y][leftPoint.x].x += Tile.TILE_WIDTH;
            //Swap tiles 
            tiles[blankPoint.y][blankPoint.x] = leftTile;
            tiles[leftPoint.y][leftPoint.x] = blankTile;
            //Update blank point
            blankPoint.x -= 1;
        } else {
//            System.out.println("NO");
        }
    }

    public void moveRight() {
//        System.out.println("MOVE RIGHT");
        //Get blank and right tiles
        Tile blankTile = tiles[blankPoint.y][blankPoint.x];
        Tile rightTile = getRightTile(blankPoint);
        //Get blank and right points
        Point rightPoint = getRight(blankPoint);

        if (withinBounds(rightPoint)) {
//            System.out.println("YES");
            //Swap tiles positions
            tiles[blankPoint.y][blankPoint.x].x += Tile.TILE_WIDTH;
            tiles[rightPoint.y][rightPoint.x].x -= Tile.TILE_WIDTH;
            //Swap tiles 
            tiles[blankPoint.y][blankPoint.x] = rightTile;
            tiles[rightPoint.y][rightPoint.x] = blankTile;
            //Update blank point
            blankPoint.x += 1;
        } else {
//            System.out.println("NO");
        }
    }

    public void handleInput() {
        if (Input.isKeyTyped(KeyEvent.VK_W)) {
            moveUp();
        }
        if (Input.isKeyTyped(KeyEvent.VK_S)) {
            moveDown();
        }
        if (Input.isKeyTyped(KeyEvent.VK_A)) {
            moveLeft();
        }
        if (Input.isKeyTyped(KeyEvent.VK_D)) {
            moveRight();
        }
        if (Input.isKeyTyped(KeyEvent.VK_B)) {
            System.out.println("***blank information***");
            System.out.println("tiles[blankPoint.y][blankPoint.x]: " + tiles[blankPoint.y][blankPoint.x]);
            System.out.println("blank x: " + tiles[blankPoint.y][blankPoint.x].x);
            System.out.println("blank y: " + tiles[blankPoint.y][blankPoint.x].y);
            System.out.println("blank num: " + tiles[blankPoint.y][blankPoint.x].tileNum);
            System.out.println("blank type: " + tiles[blankPoint.y][blankPoint.x].tileType);
        }
    }
    
    public void mousePressed(MouseEvent e){
        System.out.println("Pressed");
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                //Go through all tiles
                
                //DO SHIFING FIRST, THEN DRAW TO THE CENTER
                int touchX = e.getX() + World.xShift;
                int touchY = e.getY() + World.yShift;
                
                if(tiles[y][x].hitbox.contains(touchX, touchY)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }
    
    public void mouseReleased(MouseEvent e){
        System.out.println("Released");
    }

    /**
     * *************** UPDATE & RENDER *******************
     */
    /**
     *
     * @param deltaTime
     */
    @Override
    public void gameUpdate(float deltaTime) {
        //If im moving any of the world tiles
        elapsed += deltaTime;
//        System.out.println("elapsed: " + elapsed);
    }

    /**
     * @param g
     */
    @Override
    public void gameRender(Graphics2D g) {
        /* Called every frame */
        drawHitbox(g);
    }

    private void drawHitbox(Graphics2D g) {
        //NAIVE, renders all tiles (including off screen tiles)
        for (int y = 0; y < NO_OF_TILES_Y; y++) {
            for (int x = 0; x < NO_OF_TILES_X; x++) {
                //Draw Border
                tiles[y][x].gameRender(g);

                //Draw tile
                if (tiles[y][x].tileType == Tile.TILE_DIGIT) {
                    g.setColor(Color.lightGray);
                    Tile t = tiles[y][x];
                    int size = 2;
                    g.fillRect(t.x + size / 2 + xShift, t.y + size / 2 + yShift,
                            t.width - size, t.height - size);

                    String tileNum = String.valueOf(tiles[y][x].tileNum);

                    drawText(g, tileNum, tiles[y][x].x + xShift, tiles[y][x].y + yShift);
                } else if (tiles[y][x].tileType == Tile.TILE_IMAGE) {
                    g.setColor(Color.lightGray);    //white also works
                    ImgTile t = (ImgTile) tiles[y][x];
                    int size = 2;
                    g.fillRect(t.x + size / 2 + xShift, t.y + size / 2 + yShift,
                            t.width - size, t.height - size);

                    g.drawImage(t.img, t.x + size / 2 + xShift, t.y + size / 2 + yShift, t.width, t.height, null);
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
