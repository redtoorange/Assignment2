package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * MainView.java - The primary window of the application.  A Special JFrame that has four buttons and 3 checkboxes.  The
 * show button opens a second window, the ShapeWindow.  The Add button adds MovableShapes to the ShapeWindow.  The
 * remove button removes the most recently added shape from the ShapeWindow.  The exit button exits the application.
 *
 * @author Andrew McGuiness
 * @version 10/9/2017
 */
public class MainView extends JFrame {
    private ShapeWindow shapeWindow;
    private JPanel guiPanel;

    //Used to select which Icons to add to the sub window
    private JCheckBox blueCheck;
    private JCheckBox redCheck;
    private JCheckBox yellowCheck;

    /** Create a new MainView window and display it. */
    public MainView() {
        initGUI();
        setVisible( true );
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
        showButton.addActionListener( e -> showSubWindow() );
        guiPanel.add( showButton );


        //Initialize Exit Button
        JButton exitButton = new JButton( "Exit" );
        exitButton.addActionListener( e -> exitApplication() );
        guiPanel.add( exitButton );


        //Initialize Add Button
        JButton addButton = new JButton( "Add" );
        addButton.addActionListener( e -> addIcon() );
        guiPanel.add( addButton );


        //Initialize Remove Button
        JButton removeButton = new JButton( "Remove" );
        removeButton.addActionListener( e -> removeIcon() );
        guiPanel.add( removeButton );
    }

    private void initCheckBoxes() {
        blueCheck = new JCheckBox( "Blue", true );
        yellowCheck = new JCheckBox( "Yellow", false );
        redCheck = new JCheckBox( "Red", false );

        guiPanel.add( blueCheck );
        guiPanel.add( yellowCheck );
        guiPanel.add( redCheck );
    }

    //Initialize the look and feel of the view.MainView JFrame
    private void initWindow() {
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        //Try to set the loop and feel to System default
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
            setDefaultLookAndFeelDecorated( true );
        } catch ( Exception e ) {
            System.out.println( "Failed to load Look and Feel" );
        }

        setTitle( "MultiShape Project" );
        setSize( 300, 100 );

        //Give the window a nice icon
        try {
            setIconImage( ImageIO.read( new File( "images/blue_0.png" ) ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private void createShapeWindow() {
        shapeWindow = new ShapeWindow();
    }

    private void showSubWindow() {
        if ( !shapeWindowExists() )
            createShapeWindow();

        shapeWindow.setVisible( true );
    }

    private void exitApplication() {
        if ( shapeWindowExists() )
            shapeWindow.close();

        dispose();
    }

    private void addIcon() {
        showSubWindow();
        addIconsToSubWindow();
    }

    private void addIconsToSubWindow() {
        if ( blueCheck.isSelected() )
            shapeWindow.addIcon( IconColor.BLUE );

        if ( redCheck.isSelected() )
            shapeWindow.addIcon( IconColor.RED );

        if ( yellowCheck.isSelected() )
            shapeWindow.addIcon( IconColor.YELLOW );
    }

    private void removeIcon() {
        if ( shapeWindowExists() && shapeWindow.isVisible() )
            shapeWindow.removeIcon();
    }

    private boolean shapeWindowExists() {
        return shapeWindow != null;
    }
}
