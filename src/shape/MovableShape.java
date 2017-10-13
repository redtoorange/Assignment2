package shape;

import java.awt.*;

/**
 * MovableShape.java - A Shape that has an X and Y position that can be moved and drawn.
 *
 * @author Andrew McGuiness
 * @version 10/12/2017
 */
public interface MovableShape {
    /**
     * Draws the shape.
     *
     * @param g2 the graphics context
     */
    void draw( Graphics2D g2 );

    /**
     * Moves the shape by a given amount.
     *
     * @param dx amount to translate in x-direction
     * @param dy amount to translate in y-direction
     */
    void translate( int dx, int dy );

    /**
     * Get the shape's Y-Position.
     *
     * @return shape's y-position
     */
    int getX();

    /**
     * Get the shape's X-Position.
     *
     * @return shape's x-position
     */
    int getY();

    /**
     * Get the shape's Width.
     *
     * @return shape's width
     */
    int getWidth();

    /**
     * Get the shape's Height.
     *
     * @return shape's height
     */
    int getHeight();

    /**
     * Move the shape directly to this X-Position, Y-Position.
     *
     * @param x new x-position
     * @param y new y-position
     */
    void setPosition( int x, int y );
}
