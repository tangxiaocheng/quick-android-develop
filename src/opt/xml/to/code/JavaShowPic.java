package opt.xml.to.code;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class JavaShowPic {

  /** @param args */
  public static void main(String[] args) {
    EventQueue.invokeLater(
        new Runnable() {

          @Override
          public void run() {
            JFrame frame = new ImageViewerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
          }
        });
  }
}

class ImageViewerFrame extends JFrame {
  /** */
  private static final long serialVersionUID = -6948195552502352648L;

  public ImageViewerFrame() {
    setTitle("ImageViewer");
    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    label = new JLabel();
    add(label);
    chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("."));
    JMenuBar menubar = new JMenuBar();
    setJMenuBar(menubar);
    JMenu menu = new JMenu("File");
    menubar.add(menu);
    JMenuItem openItem = new JMenuItem("Open");
    menu.add(openItem);
    JMenuItem exitItem = new JMenuItem("Close");
    menu.add(exitItem);
    openItem.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent arg0) {
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
              String name = chooser.getSelectedFile().getPath();
              label.setIcon(new ImageIcon(name));
            }
          }
        });
    exitItem.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
          }
        });
  }

  private JLabel label;
  private JFileChooser chooser;
  private static final int DEFAULT_WIDTH = 300;
  private static final int DEFAULT_HEIGHT = 400;
}
