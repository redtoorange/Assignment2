import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton showButton = new JButton( "Show" );
        showButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                createShapeWindow();
                shapeWindow.show();
            }
        } );
        guiPanel.add( showButton );

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


        JButton addButton = new JButton( "Add" );
        guiPanel.add( addButton );

        JButton removeButton = new JButton( "Remove" );
        guiPanel.add( removeButton );


        addButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if ( shapeWindow != null ) {
                    if ( blueCheck.isSelected() )
                        shapeWindow.addIcon( IconColor.BLUE );
                    if ( redCheck.isSelected() )
                        shapeWindow.addIcon( IconColor.RED );
                    if ( yellowCheck.isSelected() )
                        shapeWindow.addIcon( IconColor.YELLOW );
                }
            }
        } );

        removeButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if ( shapeWindow != null ) {
                    if ( blueCheck.isSelected() )
                        shapeWindow.removeIcon( IconColor.BLUE );
                    if ( redCheck.isSelected() )
                        shapeWindow.removeIcon( IconColor.RED );
                    if ( yellowCheck.isSelected() )
                        shapeWindow.removeIcon( IconColor.YELLOW );
                }
            }
        } );
    }

    private void initCheckBoxes() {
        ButtonGroup group = new ButtonGroup();

        blueCheck = new JCheckBox( "Blue", true );
        redCheck = new JCheckBox( "Red", false );
        yellowCheck = new JCheckBox( "Yellow", false );

        group.add( blueCheck );
        group.add( redCheck );
        group.add( yellowCheck );

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

        setSize( 300, 100 );
    }

    public void destroyShapeWindow() {
        shapeWindow = null;
    }

    public void createShapeWindow() {
        if ( shapeWindow == null )
            shapeWindow = new ShapeWindow( this );
    }
}
