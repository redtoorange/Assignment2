import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MultiShapeTest.java - Description
 *
 * @author Andrew McGuiness
 * @version 10/4/2017
 */
public class MultiShapeTest {
    private static ShapeWindow shapeWindow;

    public static void main( String[] args ) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println( "Failed to load Look and Feel" );
        }

        JFrame.setDefaultLookAndFeelDecorated( true );
        final JFrame frame = new JFrame( "Multi Shape Test" );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        JPanel panel = new JPanel(  );
        frame.add( panel );


        JButton showButton = new JButton( "Show" );
        showButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if( shapeWindow == null )
                    shapeWindow = new ShapeWindow();

                shapeWindow.show();
            }
        } );
        panel.add( showButton );

        JButton exitButton = new JButton( "Exit" );
        exitButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if( shapeWindow != null )
                    shapeWindow.close();
                frame.setVisible( false );
                frame.dispose();
            }
        } );
        panel.add( exitButton );


        JButton addButton = new JButton( "Add" );
        panel.add( addButton );

        JButton removeButton = new JButton( "Remove" );
        panel.add( removeButton );

        ButtonGroup group = new ButtonGroup();
        JCheckBox blueCheck = new JCheckBox( "Blue", true );
        JCheckBox redCheck = new JCheckBox( "Red", false );
        JCheckBox yellowCheck = new JCheckBox( "Yellow", false );
        group.add( blueCheck );
        group.add( redCheck );
        group.add( yellowCheck );
        panel.add( blueCheck );
        panel.add( redCheck );
        panel.add( yellowCheck );

        frame.setSize( 300, 300 );
        frame.setVisible( true );
    }
}
