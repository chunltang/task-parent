package day200601;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主页面
 * */
public class MainPage extends JFrame {

    private MainSystem main;

    public MainPage(MainSystem main) {
        super(Constant.TITLE);
        this.main = main;
        mainMenu();
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * 添加主页面按钮菜单
     */
    public void mainMenu() {
        //创建主面板
        JPanel mainPanel = new JPanel(null);
        JLabel label = new JLabel();
        label.setText(Constant.MAIN_LABEL);
        label.setFont(Constant.FONT25);
        JButton registerBtn = new JButton("register");
        JButton searchBtn = new JButton("search");
        JButton exitBtn = new JButton("exit");
        //添加组件
        mainPanel.add(label);
        mainPanel.add(registerBtn);
        mainPanel.add(searchBtn);
        mainPanel.add(exitBtn);
        //设置组件位置和大小
        label.setBounds(300, 50, 200, 100);
        registerBtn.setBounds(100, 200, 100, 50);
        searchBtn.setBounds(300, 200, 100, 50);
        exitBtn.setBounds(500, 200, 100, 50);
        add(mainPanel);
        setVisible(true);

        //添加按钮监听
        registerNewCard(registerBtn);
        queryCard(searchBtn);

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    /**
     * 注册新卡
     */
    private void registerNewCard(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //隐藏主界面
                MainPage.this.setVisible(false);
                new CardRegisterPage(main.getCardSys());
            }
        });
    }

    /**
     * 查询卡
     */
    private void queryCard(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.this.setVisible(false);
                new CardQueryPage(main.getCardSys());
            }
        });
    }
}
