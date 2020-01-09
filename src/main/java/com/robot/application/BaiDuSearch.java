package com.robot.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 描述：百度自动搜索
 * @author sunpeng
 */
public class BaiDuSearch extends JDialog {

    private JButton ok;

    private Toolkit tk=this.getToolkit();
    private Dimension screen = tk.getScreenSize();

    private int width = 163;
    private int height = 86;

    private Robot robot;

    public static void main(String[] args) throws AWTException {
        new BaiDuSearch(args);
    }

    public BaiDuSearch(String[] args) throws AWTException {
        robot = new Robot();

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
            robot.mouseMove(60, 20);
            click();
            robot.delay(500);
            robot.mouseMove(170, 125);
            click();
            allChose();
            paste();
            enter();
            robot.mouseMove(453, 221);
        } catch (Exception e) {
            System.out.println("kuaiShouScript error：" + e);
        }
    }

    private void click() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private void allChose() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
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