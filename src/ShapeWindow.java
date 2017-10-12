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
import java.util.Random;

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
    private Random rand = new Random();
    private JPanel uiPanel;
    private JLabel iconCountLabel;
    private Timer updateTimer;

    private JLabel iconLabel;
    private MultiShapeIcon iconShapes;

    private MainView view;

    public ShapeWindow( MainView view ) {
        this.view = view;


        iconShapes = new MultiShapeIcon( WINDOW_WIDTH, WINDOW_HEIGHT );
        iconLabel = new JLabel( iconShapes );
        iconLabel.setBounds( 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT );
        iconLabel.setBorder( new LineBorder( Color.BLACK ) );
        add( iconLabel );

        initGUI();
        initTimer();
    }

    private void initTimer() {
        updateTimer = new Timer( DELAY, new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                iconShapes.update();
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

        JButton hideButton = new JButton( "Hide" );
        uiPanel.add( hideButton );
        hideButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                setVisible( false );
            }
        } );

        JButton exitButton = new JButton( "Exit" );
        uiPanel.add( exitButton );
        exitButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                close();
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
        if ( !visible && updateTimer.isRunning() ) {
            updateTimer.stop();
        }

        super.setVisible( visible );
    }


    public void close() {
        setVisible( false );
        updateTimer.stop();

        view.destroyShapeWindow();
    }

    public void addIcon( IconColor color ) {
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
                iconShapes.addShape( new Sprite( "images/red_0.png", startX, startY, velX, velY ) );
                break;
            case BLUE:
                iconShapes.addShape( new Sprite( "images/blue_0.png", startX, startY, velX, velY ) );
                break;
            case YELLOW:
                iconShapes.addShape( new Sprite( "images/yellow_0.png", startX, startY, velX, velY ) );
                break;
        }
    }

    public void removeIcon() {
        iconShapes.removeShape();
    }
}
