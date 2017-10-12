import javax.swing.*;
import java.util.ArrayList;

/**
 * ShapeCollection.java - Description
 *
 * @author Andrew McGuiness
 * @version 10/9/2017
 */
public class ShapeCollection {
    private ArrayList< JLabel > labels;
    private ArrayList< ShapeIcon > icons;
    private int count;

    public ShapeCollection() {
        labels = new ArrayList<>();
        icons = new ArrayList<>();
        count = 0;
    }


    public JLabel pushIconShape( ShapeIcon icon ) {
        JLabel temp = new JLabel( icon );

        icons.add( icon );
        labels.add( temp );
        count++;

        return temp;
    }

    public JLabel popIconShape() {
        JLabel temp = null;
        if ( count > 0 ) {
            count--;
            temp = labels.remove( count );
        }
        return temp;
    }

    public int getCount() {
        return count;
    }

    public void update(){
        for( int i = 0; i < count; i++){
            ShapeIcon ic = icons.get(i);

            MoveableShape ms = ic.getShape();
            ms.translate( 1, 0 );

            JLabel l = labels.get( i );
            l.setBounds( ms.getX(), ms.getY(), ic.getIconWidth(), ic.getIconHeight());
        }
    }
}
