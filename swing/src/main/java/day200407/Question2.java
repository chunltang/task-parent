package day200407;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Question2 {

    public static void main(String[] args) {
        final JFrame jf = new JFrame("菜单");
        jf.setSize(500, 400);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //建一个菜单栏
        JMenuBar menuBar = new JMenuBar();

        //创建一级菜单
        JMenu fileMenu = new JMenu("文件(F)");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenu helpMenu = new JMenu("帮助(H)");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        // 一级菜单添加到菜单栏
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        //创建 "文件" 一级菜单的子菜单
        JMenuItem newMenuItem = new JMenuItem("新建");
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', ActionEvent.CTRL_MASK));
        JMenuItem openMenuItem = new JMenuItem("打开");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', ActionEvent.CTRL_MASK));
        JMenuItem exitMenuItem = new JMenuItem("退出");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke('E', ActionEvent.CTRL_MASK));
        // 子菜单添加到一级菜单
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        // 添加一条分割线
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        JMenuItem aboutItem = new JMenuItem("关于");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', ActionEvent.CTRL_MASK));
        helpMenu.add(aboutItem);

        // 设置 "新建" 子菜单被点击的监听器
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("新建  被点击");
            }
        });
        // 设置 "打开" 子菜单被点击的监听器
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("打开  被点击");
            }
        });
        // 设置 "退出" 子菜单被点击的监听器
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //创建系统退出弹窗
                int result = JOptionPane.showConfirmDialog(
                        jf,
                        "确认退出系统吗？",
                        "确认退出系统",
                        JOptionPane.YES_NO_OPTION
                );
                //退出系统
                if(result==0){
                    System.exit(0);
                }else{
                   //取消则不需要处理
                }
            }
        });

        // 设置 "关于" 子菜单被点击的监听器
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame innerJf = new JFrame("关于...");
                innerJf.setLayout(new FlowLayout());
                innerJf.setSize(300,100);
                JLabel jLabel = new JLabel("广州工程技术职业...");
                jLabel.setFont(new Font(null, Font.TYPE1_FONT, 25));
                jLabel.setForeground(Color.RED);
                innerJf.add(jLabel);
                innerJf.setLocationRelativeTo(null);
                innerJf.setVisible(true);
            }
        });

        //把菜单栏设置到窗口
        jf.setJMenuBar(menuBar);
        jf.setVisible(true);
    }
}
