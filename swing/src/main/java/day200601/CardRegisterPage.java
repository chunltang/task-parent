package day200601;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 卡片注册页
 * */
public class CardRegisterPage extends JFrame {

    private CardAndUserRegisterSystem cardRegister;

    private JTextField name;

    private JTextField address;

    public CardRegisterPage(CardAndUserRegisterSystem cardRegister) {
        this.cardRegister = cardRegister;
        //设置窗口标题、大小
        setTitle(Constant.TITLE);
        setSize(750, 500);
        openCardRegister();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

    }

    /**
     * 创建界面组件
     * */
    public void openCardRegister() {
        //设置绝对定位
        JPanel mainPanel = new JPanel(null);
        JLabel jLabel = new JLabel(Constant.REGISTER_LABEL);
        jLabel.setFont(Constant.FONT25);
        JButton backHomeBtn = new JButton("back home");
        jLabel.setBounds(300, 50, 200, 100);
        backHomeBtn.setBounds(620, 10, 100, 40);
        mainPanel.add(jLabel);
        mainPanel.add(backHomeBtn);

        //注册文本框
        JLabel nameLabel = new JLabel("name: ");
        JLabel addressLabel = new JLabel("address：");
        nameLabel.setFont(Constant.FONT16);
        addressLabel.setFont(Constant.FONT16);
        name = new JTextField(8);
        address = new JTextField(50);
        nameLabel.setBounds(250, 150, 80, 30);
        addressLabel.setBounds(230, 200, 80, 30);
        name.setBounds(300, 150, 200, 30);
        address.setBounds(300, 200, 200, 30);
        mainPanel.add(nameLabel);
        mainPanel.add(addressLabel);
        mainPanel.add(name);
        mainPanel.add(address);

        //注册按钮
        JButton registerBtn = new JButton("register");
        registerBtn.setBounds(420, 250, 80, 30);
        mainPanel.add(registerBtn);
        add(mainPanel);
        setVisible(true);

        //添加返回主页按钮监听
        backHomeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置隐藏本页面
                CardRegisterPage.this.setVisible(false);
                //本窗口销毁，释放资源
                dispose();
                //跳转主页面
                cardRegister.getMainSystem().getMainPage().setVisible(true);
            }
        });

        //添加注册按钮监听
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //注册姓名校验
                if(name.getText().equals("")){
                    JOptionPane.showMessageDialog(CardRegisterPage.this, "name not empty!","error tip",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                //地址校验street number
                //-street name
                //-suburb
                //-city
                //-state
                //-postcode
                String sep="\\s";//分隔符
                String addressText = address.getText();
                if(address.getText().equals("")||(addressText.split(sep).length)!=6||!addressText.split(sep)[5].matches("[0-9]+")){
                    JOptionPane.showMessageDialog(CardRegisterPage.this, "address format:state city suburb street-name street-number postcode(Number)","error tip",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                //设置隐藏本页面
                CardRegisterPage.this.setVisible(false);
                //本窗口销毁，释放资源
                dispose();
                String[] split = addressText.split(sep);
                Address address = new Address(split[0], split[1], split[2], split[3], split[4], Integer.valueOf(split[5]));
                PlatinumCard card = new PlatinumCard(cardRegister.generatorId().toString(), name.getText(), address);
                cardRegister.registerCard(card);
                //注册完成跳转信息页面
                new CardAndUserInformationDisplayPage(cardRegister,card.getId());
            }
        });
    }
}
