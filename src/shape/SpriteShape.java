package shape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * SpriteShape.java - A special type of MovableShape that contains an BufferedImage (Sprite) and has simulated physics
 * attached to it.  This allows each SpriteShape to have it's own, independent x and y velocity.
 *
 * @author Andrew McGuiness
 * @version 10/10/2017
 */
public class SpriteShape implements MovableShape, PhysicsShape {
    private BufferedImage image;

    private int positionX;
    private int positionY;

    private int velocityX;
    private int velocityY;

    /**
     * Load an image file as a sprite.  Width and Height will be based on the image itself, position will be (0, 0).
     *
     * @param path image location on disk
     */
    public SpriteShape( String path ) {
        this( path, 0, 0, 0, 0 );
    }

    /**
     * Load an image file as a sprite.  Height and Width will be based on the image itself, position will be
     * (startX, startY).
     *
     * @param path      image location on disk
     * @param startX    starting x-position
     * @param startY    starting y-position
     * @param velocityX starting x-velocity
     * @param velocityY starting y-velocity
     */
    public SpriteShape( String path, int startX, int startY, int velocityX, int velocityY ) {
        loadImage( path );

        setPosition( startX, startY );
        setVelocity( velocityX, velocityY );
    }

    //Load an Image file from disc into memory
    private boolean loadImage( String path ) {
        boolean success = true;

        try {
            File f = new File( path );
            image = ImageIO.read( f );
        } catch ( IOException e ) {
            System.err.println( "FAILED TO LOAD IMAGE FILE: " + path );
            success = false;
        }

        return success;
    }


    @Override
    public void draw( Graphics2D g2 ) {
        g2.drawImage( image, positionX, positionY, null );
    }

    @Override
    public void applyMovement() {
        positionX += velocityX;
        positionY += velocityY;
    }

    @Override
    public void translate( int dx, int dy ) {
        positionX += dx;
        positionY += dy;
    }

    @Override
    public int getX() {
        return positionX;
    }

    @Override
    public int getY() {
        return positionY;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public void setPosition( int x, int y ) {
        positionX = x;
        positionY = y;
    }

    @Override
    public void setVelocity( int velocityX, int velocityY ) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    @Override
    public int getVelocityX() {
        return velocityX;
    }

    @Override
    public int getVelocityY() {
        return velocityY;
    }
}
