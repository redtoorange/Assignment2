import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * ShapeWindow.java - Description
 *
 * @author Andrew McGuiness
 * @version 10/4/2017
 */
public class ShapeWindow extends JFrame {

    private static final int DELAY = 10;
    private static final int ICON_WIDTH = 100;
    private static final int ICON_HEIGHT = 100;

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    private JPanel uiPanel;
    private JLabel iconCountLabel;
    private Timer updateTimer;

    private JLabel redLabel;
    private MultiShapeIcon reds;

    private JLabel blueLabel;
    private MultiShapeIcon blues;

    private JLabel yellowLabel;
    private MultiShapeIcon yellows;

    private MainView view;

    public ShapeWindow( MainView view ) {
        this.view = view;


        reds = new MultiShapeIcon( WINDOW_WIDTH, WINDOW_HEIGHT );
        redLabel = new JLabel( reds );
        redLabel.setBounds( 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT );
        redLabel.setBorder( new LineBorder( Color.BLACK ) );
        add( redLabel );

        blues = new MultiShapeIcon( WINDOW_WIDTH, WINDOW_HEIGHT );
        blueLabel = new JLabel( blues );
        blueLabel.setBounds( 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT );
        blueLabel.setBorder( new LineBorder( Color.BLACK ) );
        add( blueLabel );

        yellows = new MultiShapeIcon( WINDOW_WIDTH, WINDOW_HEIGHT );
        yellowLabel = new JLabel( yellows );
        yellowLabel.setBounds( 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT );
        yellowLabel.setBorder( new LineBorder( Color.BLACK ) );
        add( yellowLabel );

        initGUI();
        initTimer();
    }

    private void initTimer() {
        updateTimer = new Timer( DELAY, new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                reds.update();
                blues.update();
                yellows.update();
                repaint();
            }
        } );
    }

    private void initGUI() {
        initWindow();

        uiPanel = new JPanel();
        uiPanel.setBounds( 0, 0, 200, 50 );
        add( uiPanel );

        iconCountLabel = new JLabel();
        uiPanel.add( iconCountLabel );
        updateCountLabel();

        JButton hideButton = new JButton( "Hide" );
        uiPanel.add( hideButton );
        hideButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                setVisible( false );
            }
        } );
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


    @Override
    public void setVisible( boolean visible ) {
        if ( visible && !updateTimer.isRunning() ) {
            updateTimer.start();
        }

        super.setVisible( visible );
    }


    public void close() {
        setVisible( false );
        updateTimer.stop();

        view.destroyShapeWindow();
    }

    public void addIcon( IconColor color ) {
        //Do something
        switch ( color ) {
            case RED:
                reds.addShape( new Sprite( "images/red_0.png", 0, 200, ICON_WIDTH, ICON_HEIGHT ) );
                break;
            case BLUE:
                blues.addShape( new Sprite( "images/blue_0.png", 0, 200, ICON_WIDTH, ICON_HEIGHT ) );
                break;
            case YELLOW:
                yellows.addShape( new Sprite( "images/yellow_0.png", 0, 200, ICON_WIDTH, ICON_HEIGHT ) );
                break;
        }
        updateCountLabel();
    }

    public void removeIcon( IconColor color ) {

        switch ( color ) {
            case RED:
                reds.removeShape();
                break;
            case BLUE:
                blues.removeShape();
                break;

            case YELLOW:
                yellows.removeShape();
                break;
        }

    }

    private void updateCountLabel() {
        iconCountLabel.setText(
                "Red: " + reds.shapeCount() +
                        "    Blue: " + blues.shapeCount() +
                        "    Yellow: " + yellows.shapeCount()  );
        repaint();
    }
}
