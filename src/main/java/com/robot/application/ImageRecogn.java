package com.robot.application;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * 描述：图片识别
 * @author sunpeng
 */
public class ImageRecogn extends JDialog {

    private JButton ok;

    private Toolkit tk=this.getToolkit();
    private Dimension screen = tk.getScreenSize();

    private int width = 163;
    private int height = 86;

    private Robot robot;
    private ITesseract instance;

    private int leftTopX = 0;
    private int leftTopY = 0;
    private int rightDownX = 500;
    private int rightDownY = 500;

    public static void main(String[] args) throws AWTException {
        new ImageRecogn(args);
    }

    public ImageRecogn(String[] args) throws AWTException {
        if (args != null && args.length == 4) {
            leftTopX = Integer.valueOf(args[0]);
            leftTopY = Integer.valueOf(args[1]);
            rightDownX = Integer.valueOf(args[2]);
            rightDownY = Integer.valueOf(args[3]);
        }

        robot = new Robot();
        instance = new Tesseract();
        //instance.setDatapath("D:\\Tesseract\\tessdata");
        instance.setLanguage("chi_sim");

        this.setLayout(null);
        this.setBounds((screen.width-width)/2, (screen.height-height)/2, width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        ok = new JButton("运行");
        this.add(ok);
        ok.setBounds(14, 15, 68, 30);
        ok.setFocusable(false);

        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                baiduSearchScript();
            }
        });
    }

    public void baiduSearchScript(){
        try {
            String content = getContent(leftTopX, leftTopY, rightDownX, rightDownY);
            if (content != null && content != "") {
                System.out.println("========================================");
                System.out.println("获取到的内容是：\n" + content);

                StringSelection stringSelection = new StringSelection(content);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

                robot.mouseMove(60, 20);
                click();
                robot.delay(500);
                robot.mouseMove(170, 125);
                click();
                click();
                click();
                paste();
                enter();
            } else {
                System.out.println("未获取到内容！");
            }
        } catch (Exception e) {
            System.out.println("kuaiShouScript error：" + e);
        }
    }

    private String getContent(int leftTopX, int leftTopY, int rightDownX, int rightDownY) {
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRectangle = new Rectangle(screenSize);
            BufferedImage image = robot.createScreenCapture(screenRectangle);
            BufferedImage subimage = image.getSubimage(leftTopX, leftTopY, rightDownX, rightDownY);
            return instance.doOCR(subimage);
        } catch (Exception e) {
            System.out.println("获取内容失败！");
            return "";
        }
    }

    private void click() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private void paste() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    private void enter() {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}