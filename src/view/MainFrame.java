/*- 
 * Classname:             MainFrame.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  27/12/2013 - 13:22:40 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class MainFrame {

    private JFrame frame;
    private JLabel largeLabel;
    private JLabel mediumLabel;
    private JLabel smallLabel;
    private JTextField largeField;
    private JTextField mediumField;
    private JTextField smallField;
    private JButton largeButton;
    private JButton mediumButton;
    private JButton smallButton;
    private JLabel folderPathLabel;
    private JTextField folderPathField;
    private JButton folderPathButton;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton generateButton;
    private JLabel keepLabel;
    private JCheckBox keepRadioButton;
    private String lastDirectory = null;
    private String lastFileFolder = null;

    public MainFrame() {
        frame = new JFrame("Baldur's Gate EE Portraits Resizer");
        largeLabel = new JLabel("Large:");
        mediumLabel = new JLabel("Medium:");
        smallLabel = new JLabel("Small:");
        largeField = new JTextField();
        mediumField = new JTextField();
        smallField = new JTextField();
        largeButton = new JButton("SELECT");
        mediumButton = new JButton("SELECT");
        smallButton = new JButton("SELECT");
        folderPathLabel = new JLabel("Folder:");
        folderPathField = new JTextField();
        folderPathButton = new JButton("SELECT");
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(9);
        generateButton = new JButton("GENERATE");
        keepLabel = new JLabel("Keep Proportion");
        keepRadioButton = new JCheckBox();
        keepRadioButton.setSelected(true);

        largeField.setEditable(false);
        mediumField.setEditable(false);
        smallField.setEditable(false);
        folderPathField.setEditable(false);

        frame.setLayout(null);
        frame.setSize(500, 430);
        largeLabel.setBounds(10, 20, 100, 30);
        mediumLabel.setBounds(10, 70, 100, 30);
        smallLabel.setBounds(10, 120, 100, 30);
        largeField.setBounds(110, 20, 200, 30);
        mediumField.setBounds(110, 70, 200, 30);
        smallField.setBounds(110, 120, 200, 30);
        largeButton.setBounds(350, 20, 100, 30);
        mediumButton.setBounds(350, 70, 100, 30);
        smallButton.setBounds(350, 120, 100, 30);
        folderPathLabel.setBounds(10, 250, 100, 30);
        folderPathField.setBounds(110, 250, 200, 30);
        folderPathButton.setBounds(350, 250, 100, 30);
        nameLabel.setBounds(10, 300, 100, 30);
        nameField.setBounds(110, 300, 100, 30);
        generateButton.setBounds(350, 350, 100, 30);
        frame.setLocationRelativeTo(null);
        keepLabel.setBounds(270, 300, 100, 30);
        keepRadioButton.setBounds(240, 300, 30, 30);

        frame.add(largeLabel);
        frame.add(mediumLabel);
        frame.add(smallLabel);
        frame.add(largeField);
        frame.add(mediumField);
        frame.add(smallField);
        frame.add(largeButton);
        frame.add(mediumButton);
        frame.add(smallButton);
        frame.add(folderPathLabel);
        frame.add(folderPathField);
        frame.add(folderPathButton);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(generateButton);
        JSeparator s = new JSeparator();
        s.setBounds(10, 200, 460, 5);
        frame.add(s);
        frame.add(keepLabel);
        frame.add(keepRadioButton);

        frame.getContentPane().setBackground(Color.white);
        keepRadioButton.setBackground(Color.WHITE);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public boolean keepProportion() {
        return keepRadioButton.isSelected();
    }

    public void setLargeFieldText(String text) {
        largeField.setText(text);
    }

    public void setMediumFieldText(String text) {
        mediumField.setText(text);
    }

    public void setSmallFieldText(String text) {
        smallField.setText(text);
    }

    public void setFolderPathFieldText(String text) {
        folderPathField.setText(text);
    }

    public String getNameFieldText() {
        return nameField.getText();
    }

    public void addActionListenerLargeButton(ActionListener listener) {
        largeButton.addActionListener(listener);
    }

    public void addActionListenerMediumButton(ActionListener listener) {
        mediumButton.addActionListener(listener);
    }

    public void addActionListenerSmallButton(ActionListener listener) {
        smallButton.addActionListener(listener);
    }

    public void addActionListenerfolderPathButton(ActionListener listener) {
        folderPathButton.addActionListener(listener);
    }

    public void addActionListenerGenerateButton(ActionListener listener) {
        generateButton.addActionListener(listener);
    }

    public File add() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDragEnabled(false);
        fileChooser.setFileHidingEnabled(true);

        //adiciona filtro para imagens
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {

                //de for diretório permite
                if (f.isDirectory()) {
                    return true;
                }

                //pega extenção
                String extension = null;
                try {
                    extension = f.getName().substring(f.getName().lastIndexOf('.') + 1);
                } catch (Exception ex) {
                    extension = null;
                }

                //verifica se é uma imagem
                if (extension != null) {
                    if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("gif") || extension.equalsIgnoreCase("jpeg")
                            || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("bmp")) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
            }

            //define descrição do filtro
            @Override
            public String getDescription() {
                return "Apenas imagens";
            }
        });
        //cria componente para visualização de imagens
        ImagePreview ip = new ImagePreview(fileChooser);
        fileChooser.setAccessory(ip.getPreview());
        if (lastFileFolder != null) {
            fileChooser.setCurrentDirectory(new File(lastFileFolder));

        }

        //inicia filechosser e analisa resposta
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            lastFileFolder = file.getAbsolutePath();
            return file;
        }
        return null;
    }

    public File addDiretory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDragEnabled(false);
        fileChooser.setFileHidingEnabled(true);

        //adiciona filtro para imagens
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {

                //de for diretório permite
                if (f.isDirectory()) {
                    return true;
                }
                return false;
            }

            //define descrição do filtro
            @Override
            public String getDescription() {
                return "Apenas diretórios";
            }
        });
        //cria componente para visualização de imagens
        ImagePreview ip = new ImagePreview(fileChooser);
        fileChooser.setAccessory(ip.getPreview());

        if (lastDirectory != null) {
            fileChooser.setCurrentDirectory(new File(lastDirectory));
        }

        //inicia filechosser e analisa resposta
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            lastDirectory = file.getAbsolutePath();
            return file;
        }
        return null;
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}//fim da classe MainFrame 

