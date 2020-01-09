package com.robot.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class KuaiShou extends JDialog {

    private JButton ok;

    private Toolkit tk=this.getToolkit();
    private Dimension screen = tk.getScreenSize();

    private int width = 260;
    private int height = 86;

    private Robot robot;

    public static void main(String[] args) throws AWTException {
        new KuaiShou(args);
    }

    public KuaiShou(String[] args) throws AWTException {
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
                while (true) {
                    kuaiShouScript();
                }
            }
        });
    }

    public void kuaiShouScript(){
        try {
            int interTime = getRandom(6, 8);
            Thread.sleep(interTime);
            robot.mouseMove(200, 15);
            click();
            robot.mouseMove(200, 550);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseMove(200, 400);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.mouseMove(200, 350);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseMove(200, 200);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (Exception e) {
            System.out.println("kuaiShouScript error：" + e);
        }
    }

    private void click() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private int getRandom(int startSecond, int endSecond) {
        return new Random().nextInt(endSecond * 1000 - startSecond * 1000) + startSecond * 1000;
    }
}