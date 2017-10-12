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

    private ArrayList <MoveableShape> shapes;
    private int count = 0;

    public MultiShapeIcon( int width, int height ) {
        shapes = new ArrayList<>(  );
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

        for( int i = 0; i < count; i++) {
            System.out.println( "Drawing shape " + i );
            shapes.get( i ).draw( g2 );
        }
    }

    public MoveableShape getShape( int index) {
        return shapes.get( index );
    }


    public void addShape( MoveableShape shape ) {
        System.out.println( "Added shape at: " + shape.getX() + ", " + shape.getY());
        shapes.add( shape );
        count++;
    }

    public void removeShape() {
        if ( count > 0 ) {
            count--;
            shapes.remove( count );
        }
    }

    public int shapeCount() {
        return count;
    }

    public void update(){
        for( int i = 0; i < count; i++){
            MoveableShape ms = shapes.get(i);
            ms.translate( 1, 0 );

            if( ms.getX() > width + ms.getWidth()){
                ms.setPosition( -ms.getWidth(), ms.getY() );
            }
        }
    }
}


