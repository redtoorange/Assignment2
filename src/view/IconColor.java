package view;

/**
 * IconColor.java - Color possibilities for an Icon.
 *
 * @author Andrew McGuiness
 * @version 10/9/2017
 */
public enum IconColor {
    BLUE( "blue" ), YELLOW( "yellow" ), RED( "red" );


    private String key;

    IconColor( String key ) {
        this.key = key;
    }

    /**
     * Get the enum's name as a lower-cased String
     * @return  String form of the IconColor
     */
    public String toString() {
        return key;
    }
}
