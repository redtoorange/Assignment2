import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * ShapeWindow.java - Description
 *
 * @author Andrew McGuiness
 * @version 10/4/2017
 */
public class ShapeWindow {
    private static final int DELAY = 10;

    private JFrame shapeFrame;
    private JPanel uiPanel;
    private JLabel iconCountLabel;
    private Timer updateTimer;

    private ShapeCollection redIcons;
    private ShapeCollection blueIcons;
    private ShapeCollection yellowIcons;

    private MainView view;

    public ShapeWindow( MainView view ) {
        this.view = view;
        shapeFrame = new JFrame( "Shape Window" );
        shapeFrame.setSize( 300, 300 );

        uiPanel = new JPanel();
        uiPanel.setSize( 100, 50 );
        shapeFrame.add( uiPanel );

        redIcons = new ShapeCollection();
        blueIcons = new ShapeCollection();
        yellowIcons = new ShapeCollection();

        iconCountLabel = new JLabel();
        uiPanel.add( iconCountLabel );
        updateCountLabel();

        JButton hideButton = new JButton( "Hide" );
        uiPanel.add( hideButton );
        hideButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                hide();
            }
        } );


        updateTimer = new Timer( DELAY, new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                //
                System.out.println( "Iteration" );
            }
        } );


        shapeFrame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                close();
            }
        } );
    }


    public void show() {
        updateTimer.start();
        shapeFrame.setVisible( true );
    }

    public void hide() {
        shapeFrame.setVisible( false );
    }

    public void close() {
        hide();
        updateTimer.stop();
        shapeFrame.dispose();

        view.destroyShapeWindow();
    }

    public void addIcon( IconColor color ) {
        //Do something
        switch ( color ) {
            case RED:
                redIcons.addShapeIcon();
                break;
            case BLUE:
                blueIcons.addShapeIcon();
                break;
            case YELLOW:
                yellowIcons.addShapeIcon();
                break;
        }
        updateCountLabel();
    }

    public void removeIcon( IconColor color ) {
        //Do something
        switch ( color ) {
            case RED:
                redIcons.removeShapeIcon();
                break;
            case BLUE:
                blueIcons.removeShapeIcon();
                break;
            case YELLOW:
                yellowIcons.removeShapeIcon();
                break;
        }
        updateCountLabel();
    }

    private void updateCountLabel() {
        iconCountLabel.setText(
                "Red: " + redIcons.getCount() +
                        "    Blue: " + blueIcons.getCount() +
                        "    Yellow: " + yellowIcons.getCount() );
    }

}
