import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class ConverterUI extends JFrame {
    JTextField inputPath;
    JTextField outputPath;

    ConverterUI(){

        setTitle("Csv-2-Json Converter");
        setSize(500,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10,10));

        JPanel panel = new JPanel(new GridLayout(3,3,5,5));

        panel.add(new JLabel("Csv File :"));
        inputPath = new JTextField();
        panel.add(inputPath);

        JButton browseInput = new JButton("Browse");
        panel.add(browseInput);

        panel.add(new JLabel("Output Folder:"));
        outputPath = new JTextField();
        panel.add(outputPath);

        JButton browseOutput = new JButton("Browse");
        panel.add(browseOutput);

        JButton convertBtn = new JButton("Convert");
        panel.add(new JLabel());
        panel.add(convertBtn);

        add(panel, BorderLayout.CENTER);

        //Event Listeners

        browseInput.addActionListener( e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                inputPath.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        browseOutput.addActionListener( e ->{
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = chooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                outputPath.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        //convert btn

        convertBtn.addActionListener( e -> {
            convertFile();
        });

        new DropTarget(inputPath, new FileDropListener());

    }

    private void convertFile(){
        String input = inputPath.getText();
        String outputDir = outputPath.getText();

        if (input.isEmpty() || outputDir.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select both input file and output folder.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            String outputPath = outputDir + File.separator + "output.txt";

            CsvParser csv = new CsvParser();
            csv.parser(input,outputPath);

            JOptionPane.showMessageDialog(this,
                    "Conversion Successful!\nSaved at:\n" + outputPath,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Failure",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Drag & Drop handler
    class FileDropListener extends DropTargetAdapter {
        public void drop(DropTargetDropEvent event) {
            try {
                event.acceptDrop(DnDConstants.ACTION_COPY);
                List<File> droppedFiles =
                        (List<File>) event.getTransferable()
                                .getTransferData(DataFlavor.javaFileListFlavor);

                if (!droppedFiles.isEmpty()) {
                    inputPath.setText(droppedFiles.get(0).getAbsolutePath());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
