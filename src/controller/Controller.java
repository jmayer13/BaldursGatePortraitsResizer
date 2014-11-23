/*- 
 * Classname:             Controller.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  27/12/2013 - 13:22:05 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.imgscalr.Scalr;
import view.MainFrame;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class Controller {

    private MainFrame view;
    private File largeFile = null;
    private File smallFile = null;
    private File mediumFile = null;
    private File directory = null;

    public Controller() {
        view = new MainFrame();
        view.addActionListenerGenerateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getNameFieldText();
                Scalr.Mode mode;
                if (view.keepProportion()) {
                    mode = Scalr.Mode.AUTOMATIC;
                } else {
                    mode = Scalr.Mode.FIT_EXACT;
                }
                if (largeFile != null && smallFile != null && mediumFile != null && directory != null && !name.isEmpty()) {
                    try {
                        //large
                        BufferedImage l = Scalr.resize(ImageIO.read(largeFile), Scalr.Method.ULTRA_QUALITY, mode,
                                210, 330, Scalr.OP_ANTIALIAS);
                        BufferedImage combinedL = new BufferedImage(210, 330, BufferedImage.TYPE_INT_RGB);
                        Graphics g = combinedL.getGraphics();
                        g.setColor(Color.black);
                        g.drawRect(0, 0, combinedL.getWidth(), combinedL.getHeight());
                        g.drawImage(l, (combinedL.getWidth() - l.getWidth()) / 2, (combinedL.getHeight() - l.getHeight()) / 2, Color.black, null);

                        ImageIO.write(combinedL, "bmp", new File(directory.getAbsolutePath(), name + "L.bmp"));
                        //medium
                        BufferedImage m = Scalr.resize(ImageIO.read(mediumFile), Scalr.Method.ULTRA_QUALITY, mode,
                                169, 266, Scalr.OP_ANTIALIAS);
                        BufferedImage combinedM = new BufferedImage(169, 266, BufferedImage.TYPE_INT_RGB);
                        Graphics gm = combinedM.getGraphics();
                        gm.setColor(Color.black);
                        gm.drawRect(0, 0, combinedM.getWidth(), combinedM.getHeight());
                        gm.drawImage(m, (combinedM.getWidth() - m.getWidth()) / 2, (combinedM.getHeight() - m.getHeight()) / 2, Color.black, null);

                        ImageIO.write(combinedM, "bmp", new File(directory.getAbsolutePath(), name + "M.bmp"));

                        //small 
                        BufferedImage s = Scalr.resize(ImageIO.read(smallFile), Scalr.Method.ULTRA_QUALITY, mode,
                                54, 84, Scalr.OP_ANTIALIAS);
                        BufferedImage combinedS = new BufferedImage(54, 84, BufferedImage.TYPE_INT_RGB);
                        Graphics gs = combinedS.getGraphics();
                        gs.setColor(Color.black);
                        gs.drawRect(0, 0, combinedS.getWidth(), combinedS.getHeight());
                        gs.drawImage(s, (combinedS.getWidth() - s.getWidth()) / 2, (combinedS.getHeight() - s.getHeight()) / 2, Color.black, null);


                        ImageIO.write(combinedS, "bmp", new File(directory.getAbsolutePath(), name + "S.bmp"));

                        JOptionPane.showMessageDialog(null, "Done!");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Fill it!");
                }
            }
        });
        view.addActionListenerLargeButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = view.add();
                if (file != null) {
                    largeFile = file;
                    view.setLargeFieldText(largeFile.getPath());
                }
            }
        });
        view.addActionListenerMediumButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = view.add();
                if (file != null) {
                    mediumFile = file;
                    view.setMediumFieldText(mediumFile.getPath());
                }
            }
        });
        view.addActionListenerSmallButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = view.add();
                if (file != null) {
                    smallFile = file;
                    view.setSmallFieldText(smallFile.getPath());
                }
            }
        });
        view.addActionListenerfolderPathButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File file = view.addDiretory();
                if (file != null) {
                    directory = file;
                    view.setFolderPathFieldText(directory.getPath());
                }
            }
        });
    }
}//fim da classe Controller 

