package view;

import shape.MultiShapeIcon;
import shape.SpriteShape;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * ShapeWindow.java - A Special JFrame that contains two buttons and a single JLabel that encompasses the entire window.
 * The Exit button will destroy the contents of the window, the hide button will hide the window but keep all of the
 * contents intact.  While the window is visible, it will constantly be updating, which will update the position of all
 * MovableShapes on the window and redraw them.
 *
 * @author Andrew McGuiness
 * @version 10/4/2017
 */
public class ShapeWindow extends JFrame {
    private static final int DELAY = 10;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    private Random rand = new Random();
    private Timer updateTimer;
    private MultiShapeIcon iconShapes;

    /** Create a new ShapeWindow. */
    public ShapeWindow() {
        iconShapes = new MultiShapeIcon( WINDOW_WIDTH, WINDOW_HEIGHT );
        JLabel iconLabel = new JLabel( iconShapes );
        iconLabel.setBounds( 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT );
        iconLabel.setBorder( new LineBorder( Color.BLACK ) );
        add( iconLabel );

        initGUI();
        initTimer();
    }

    /**
     * Set the visibility of the ShapeWindow.  If the window is set to hidden, then the timer will be stopped and all
     * shapes will cease to update.
     *
     * @param visible True will show the window, False will hide it.
     */
    @Override
    public void setVisible( boolean visible ) {
        if ( visible && !updateTimer.isRunning() )
            updateTimer.start();

        else if ( !visible && updateTimer.isRunning() )
            updateTimer.stop();

        super.setVisible( visible );
    }

    /** Close this window and free all of it's assets. */
    public void close() {
        setVisible( false );
        updateTimer.stop();
        iconShapes.clear();
        dispose();
    }

    /**
     * Add a new icon to the ShapeWindow.  This uses the Sprite class heavily to add new icons with random position,
     * velocity and sprites based on the color selected.
     *
     * @param color Color of the icon to be added.
     */
    public void addIcon( IconColor color ) {
        int index = rand.nextInt( 3 );
        int startX = rand.nextInt( WINDOW_WIDTH );
        int startY = rand.nextInt( WINDOW_HEIGHT );

        int velX = rand.nextInt( 6 ) - 3;
        int velY = rand.nextInt( 6 ) - 3;

        while ( velX == 0 )
            velX = rand.nextInt( 6 ) - 3;
        while ( velY == 0 )
            velY = rand.nextInt( 6 ) - 3;

        //Do something
        switch ( color ) {
            case RED:
                iconShapes.addShape( new SpriteShape( "images/red_" + index + ".png", startX, startY, velX, velY ) );
                break;
            case BLUE:
                iconShapes.addShape( new SpriteShape( "images/blue_" + index + ".png", startX, startY, velX, velY ) );
                break;
            case YELLOW:
                iconShapes.addShape( new SpriteShape( "images/yellow_" + index + ".png", startX, startY, velX, velY ) );
                break;
        }
    }

    /** Remove the last icon added to the ShapeWindow. */
    public void removeIcon() {
        iconShapes.removeShape();
    }


    private void initTimer() {
        updateTimer = new Timer( DELAY, event -> {
            iconShapes.update();
            repaint();
        } );
    }

    private void initGUI() {
        initWindow();

        JPanel uiPanel = new JPanel();
        uiPanel.setBounds( 0, 0, 200, 50 );
        add( uiPanel );

        JButton hideButton = new JButton( "Hide" );
        hideButton.addActionListener( e -> setVisible( false ) );
        uiPanel.add( hideButton );

        JButton exitButton = new JButton( "Exit" );
        exitButton.addActionListener( e -> close() );
        uiPanel.add( exitButton );
    }

    private void initWindow() {
        setTitle( "Shape Window" );
        setSize( WINDOW_WIDTH, WINDOW_HEIGHT );
        setLayout( null );

        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                close();
            }
        } );

        try {
            setIconImage( ImageIO.read( new File( "images/red_1.png" ) ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
