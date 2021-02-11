package CommonClass;

import javafx.stage.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkWindow implements ActionListener {

    private  static JPanel panel;
    private static TextArea text;
    private static JFrame frame;

    public static Window Text ()    {

        panel =new JPanel();
        frame = new JFrame();
        frame.setSize( 300, 350 );
        //frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
        frame.add( panel );
        panel.setLayout(  null);
        panel.setLocation( 600,600 );
        frame.setTitle( "OK" );

        text = new TextArea("    Item introdus");
        text.setBounds( 50, 23,90,49 );
        panel.add( text );

        frame.setVisible( true );
        text.setVisible( true );
        return null;
    }


    @Override
    public void actionPerformed ( ActionEvent e ) {

    }

}
