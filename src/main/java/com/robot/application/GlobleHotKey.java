package com.robot.application;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class GlobleHotKey extends JFrame {

    private JButton msgBtn;
    private JButton exitBtn;

    // 定义热键标识
    public static final int HOT_KEY_MARK_0 = 0;
    public static final int HOT_KEY_MARK_1 = 1;

    public GlobleHotKey() {
        this.setTitle("全局热键设置");
        this.setBounds(500, 200, 600, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        msgBtn = new JButton("弹出框（Alt+S）");
        msgBtn.setMargin(new Insets(0,0,0,0));
        msgBtn.setFocusable(false);
        msgBtn.setBounds(20, 20, 120, 30);
        msgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessage();
            }
        });
        this.add(msgBtn);

        exitBtn = new JButton("退出（Alt+Q）");
        exitBtn.setMargin(new Insets(0,0,0,0));
        exitBtn.setFocusable(false);
        exitBtn.setBounds(160, 20, 120, 30);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(exitBtn);

        // 注册热键，第一个参数表示该热键的标识，第二个参数表示组合键，如果没有则为0，第三个参数为定义的主要热键
        JIntellitype.getInstance().registerHotKey(HOT_KEY_MARK_1, JIntellitype.MOD_ALT, (int)'S');
        JIntellitype.getInstance().registerHotKey(HOT_KEY_MARK_0, JIntellitype.MOD_ALT, (int)'Q');

        // 添加监听热键监听
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            @Override
            public void onHotKey(int markCode) {
                switch (markCode) {
                    case 2:
                    case HOT_KEY_MARK_1:
                        showMessage();
                        break;
                    case HOT_KEY_MARK_0:
                        System.exit(0);
                        break;
                }
            }
        });

        this.setVisible(true);
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(null, "就算把窗口最小化，按快捷键Alt+S也可以弹出提示框哦！", "弹出框标题", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new GlobleHotKey();
    }
}