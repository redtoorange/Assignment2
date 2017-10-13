package shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * MultiShapeIcon.java - An Icon that can contain multiple MovableShapes within it.  The MultiShapeIcon is constructed
 * with bounds, and all MovableShapes added to the Icon will wrap within those bounds.
 *
 * @author Andrew McGuiness
 * @version 10/10/2017
 */
public class MultiShapeIcon implements Icon {
    private int width;
    private int height;

    private ArrayList< MovableShape > shapes;
    private int count = 0;

    /**
     * Create a new MultiShapeIcon.
     *
     * @param width  width of the drawing area
     * @param height height of the drawing area
     */
    public MultiShapeIcon( int width, int height ) {
        shapes = new ArrayList<>();
        this.width = width;
        this.height = height;
    }


    /**
     * Add a new MovableShape to this MultiShapeIcon
     *
     * @param shape MovableShape to be added
     */
    public void addShape( MovableShape shape ) {
        shapes.add( shape );
        count++;
    }

    /** Remove the most recently added MovableShape. */
    public void removeShape() {
        if ( count > 0 ) {
            count--;
            shapes.remove( count );
        }
    }

    /** Update the position of all the MovableShapes. */
    public void update() {
        for ( int i = 0; i < count; i++ ) {
            MovableShape ms = shapes.get( i );

            if ( ms instanceof PhysicsShape ) {
                PhysicsShape physObj = ( PhysicsShape ) ms;

                physObj.applyMovement();

                if ( physObj.getVelocityX() > 0 && ms.getX() > width )
                    ms.setPosition( -ms.getWidth(), ms.getY() );

                if ( physObj.getVelocityY() > 0 && ms.getY() > height )
                    ms.setPosition( ms.getX(), -ms.getHeight() );

                if ( physObj.getVelocityX() < 0 && ms.getX() + ms.getWidth() < 0 )
                    ms.setPosition( width, ms.getY() );

                if ( physObj.getVelocityY() < 0 && ms.getY() + ms.getHeight() < 0 )
                    ms.setPosition( ms.getX(), height );
            } else {
                ms.translate( 1, 0 );
            }
        }
    }

    /** Clear all MovableShape from this MultiShapeIcon. */
    public void clear() {
        shapes.clear();
        count = 0;
    }

    /**
     *  Get the Icon's Width, used for bounds and wrapping of shapes.
     * @return  width of the Icon
     */
    @Override
    public int getIconWidth() {
        return width;
    }

    /**
     *  Get the Icon's Height, used for bounds and wrapping of shapes.
     * @return  height of the Icon
     */
    @Override
    public int getIconHeight() {
        return height;
    }

    /**
     * Paint all of the MovableShapes contained inside this MultiShapeIcon
     * @param c Unused.
     * @param g Graphics instance used to draw this Icon.
     * @param x Unused.
     * @param y Unused.
     */
    @Override
    public void paintIcon( Component c, Graphics g, int x, int y ) {
        Graphics2D g2 = ( Graphics2D ) g;

        for ( int i = 0; i < count; i++ )
            shapes.get( i ).draw( g2 );
    }
}


