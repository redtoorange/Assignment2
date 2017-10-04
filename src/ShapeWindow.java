import javax.swing.*;

/**
 * ShapeWindow.java - Description
 *
 * @author Andrew McGuiness
 * @version 10/4/2017
 */
public class ShapeWindow {
    private JFrame shapeFrame;

    public ShapeWindow(){
        shapeFrame = new JFrame( "Shape Window" );
        shapeFrame.setSize( 300, 300 );
    }

    public void show(){
        shapeFrame.setVisible( true );
    }

    public void hide(){
        shapeFrame.setVisible( false );
    }

    public void close(){
        hide();
        shapeFrame.dispose();
    }
}
