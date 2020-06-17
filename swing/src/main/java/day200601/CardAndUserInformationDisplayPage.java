package day200601;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 展示注册的信息
 * */
public class CardAndUserInformationDisplayPage extends JFrame{

    private CardAndUserRegisterSystem cardRegister;

    private String cardId;

    public CardAndUserInformationDisplayPage(CardAndUserRegisterSystem cardRegister,String cardId){
        this.cardRegister=cardRegister;
        this.cardId=cardId;
        //设置窗口标题和大小
        setTitle(Constant.TITLE);
        setSize(750, 500);
        showListInfo();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * 创建界面组件
     * */
    public void showListInfo(){
        JPanel mainPanel = new JPanel(null);
        JLabel jLabel = new JLabel(Constant.INFO_LABEL);
        jLabel.setFont(Constant.FONT25);
        jLabel.setBounds(300, 0, 200, 100);
        mainPanel.add(jLabel);
        Card card = cardRegister.getCardsByCardId(cardId);
        JTextArea textArea = new JTextArea();
        textArea.setBounds(180,100,400,250);
        textArea.setFont(Constant.FONT25);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.black);
        if(null!=card){
            StringBuilder sb=new StringBuilder();
            sb.append("id: ").append(card.getId()).append("\r\n");
            sb.append("name: ").append(card.getName()).append("\r\n");
            sb.append("address: ").append(card.getAddr().toString()).append("\r\n");
            sb.append("points: ").append(card.getPoints()).append("\r\n");
            sb.append("register time: ").append(card.getDate().toString()).append("\r\n");
            textArea.setText(sb.toString());
            mainPanel.add(textArea);
        }

        JButton backHomeBtn = new JButton("back home");
        backHomeBtn.setBounds(620, 10, 100, 40);
        mainPanel.add(backHomeBtn);

        add(mainPanel);
        setVisible(true);

        //添加返回主页面按钮监听
        backHomeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardAndUserInformationDisplayPage.this.setVisible(false);
                //本窗口销毁，释放资源
                dispose();
                //跳转主页面
                cardRegister.getMainSystem().getMainPage().setVisible(true);
            }
        });
    }


    public void displayInformationToTextArea(String str){

    }

    public void displayInformationToList(String[] strs){

    }

    private void clear(){

    }

    private void backToPreviousPage(){

    }
}
