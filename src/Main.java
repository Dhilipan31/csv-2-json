import javax.swing.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(()->{
            new ConverterUI().setVisible(true);
        });
    }
}