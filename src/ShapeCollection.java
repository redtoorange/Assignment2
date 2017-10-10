import java.util.ArrayList;

/**
 * ShapeCollection.java - Description
 *
 * @author Andrew McGuiness
 * @version 10/9/2017
 */
public class ShapeCollection {
    private ArrayList< ShapeIcon > icons;
    private int count;

    public ShapeCollection() {
        icons = new ArrayList<>();
        count = 0;
    }


    public void addShapeIcon( /*ShapeIcon icon*/ ) {
        //icons.add( icon );
        count++;
    }

    public void removeShapeIcon() {
        if ( count > 0 ) {
            count--;
            //icons.remove( count );
        }
    }

    public int getCount() {
        return count;
    }

    public void dispose() {
        icons.clear();
        count = 0;
    }
}
