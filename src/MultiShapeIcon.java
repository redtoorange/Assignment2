/**
 * MultiShapeIcon.java - Description
 *
 * @author Andrew McGuiness
 * @version 10/10/2017
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * An icon that contains a moveable shape.
 */
public class MultiShapeIcon implements Icon {
    private int width;
    private int height;

    private ArrayList< MovableShape > shapes;
    private int count = 0;

    public MultiShapeIcon( int width, int height ) {
        shapes = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }

    public void paintIcon( Component c, Graphics g, int x, int y ) {
        Graphics2D g2 = ( Graphics2D ) g;

        for ( int i = 0; i < count; i++ ) {
            System.out.println( "Drawing shape " + i );
            shapes.get( i ).draw( g2 );
        }
    }


    public void addShape( MovableShape shape ) {
        shapes.add( shape );
        count++;
    }

    public void removeShape() {
        if ( count > 0 ) {
            count--;
            shapes.remove( count );
        }
    }

    public void update() {
        for ( int i = 0; i < count; i++ ) {
            MovableShape ms = shapes.get( i );

            if ( ms instanceof Physics ) {
                Physics physObj = ( Physics ) ms;

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

    public void clear() {
        shapes.clear();
        count = 0;
    }
}


