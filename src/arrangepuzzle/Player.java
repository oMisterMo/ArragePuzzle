package arrangepuzzle;

import java.awt.Graphics2D;

/**
 * 07-Sep-2016, 01:56:02.
 *
 * @author Mo
 */
public class Player extends Tile implements GameObject{
    private World world;

    public Player(int x, int y, int width, int height, int id, World world) {
        super(x, y, width, height, id);
        this.world = world;

        loadImages();
    }

    private void loadImages() {
    }

    public void handleInput() {

    }

    /* ************UPDATE & RENDER************** */
    
    /**
     * 
     * @param deltaTime 
     */
    @Override
    public void gameUpdate(float deltaTime) {

    }

    /**
     * 
     * @param g 
     */
    @Override
    public void gameRender(Graphics2D g) {

    }
}
