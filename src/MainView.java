import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * MainView.java - Description
 *
 * @author Andrew McGuiness
 * @version 10/9/2017
 */
public class MainView extends JFrame {
    private ShapeWindow shapeWindow;
    private JPanel guiPanel;
    private JCheckBox blueCheck;
    private JCheckBox redCheck;
    private JCheckBox yellowCheck;

    public MainView() {
        initGUI();
    }

    private void initGUI() {
        initWindow();
        initPanel();
        initButtons();
        initCheckBoxes();
    }

    private void initPanel() {
        guiPanel = new JPanel();
        add( guiPanel );
    }

    private void initButtons() {
        //Initialize Show Button
        JButton showButton = new JButton( "Show" );
        showButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if ( shapeWindow == null )
                    createShapeWindow();
                shapeWindow.setVisible( true );
            }
        } );
        guiPanel.add( showButton );
        //----------------------


        //Initialize Exit Button
        JButton exitButton = new JButton( "Exit" );
        exitButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if ( shapeWindow != null )
                    shapeWindow.close();
                setVisible( false );
                dispose();
            }
        } );
        guiPanel.add( exitButton );
        //---------------------


        //Initialize Add Button
        JButton addButton = new JButton( "Add" );
        addButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if ( shapeWindow == null ) {
                    createShapeWindow();
                    shapeWindow.setVisible( true );
                }

                if ( blueCheck.isSelected() )
                    shapeWindow.addIcon( IconColor.BLUE );

                if ( redCheck.isSelected() )
                    shapeWindow.addIcon( IconColor.RED );

                if ( yellowCheck.isSelected() )
                    shapeWindow.addIcon( IconColor.YELLOW );

            }
        } );
        guiPanel.add( addButton );
        //------------------------


        //Initialize Remove Button
        JButton removeButton = new JButton( "Remove" );
        removeButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if ( shapeWindow != null )
                    shapeWindow.removeIcon();

            }
        } );
        guiPanel.add( removeButton );
    }

    private void initCheckBoxes() {
        blueCheck = new JCheckBox( "Blue", true );
        redCheck = new JCheckBox( "Red", false );
        yellowCheck = new JCheckBox( "Yellow", false );

        guiPanel.add( blueCheck );
        guiPanel.add( redCheck );
        guiPanel.add( yellowCheck );
    }

    private void initWindow() {
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
            setDefaultLookAndFeelDecorated( true );
        } catch ( Exception e ) {
            System.out.println( "Failed to load Look and Feel" );
        }

        setTitle( "Multishape Project" );
        setSize( 300, 100 );
        try {
            setIconImage( ImageIO.read( new File( "images/blue_0.png" ) ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    protected void destroyShapeWindow() {
        shapeWindow = null;
    }

    private void createShapeWindow() {
        shapeWindow = new ShapeWindow( this );
    }
}
