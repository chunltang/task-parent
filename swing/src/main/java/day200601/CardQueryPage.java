package day200601;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 卡片查询页
 * */
public class CardQueryPage extends JFrame {

    private CardAndUserRegisterSystem cardRegister;

    private JTextField id;

    private JTextField name;

    private JTable table=null;

    // 表头（列名）
    private Object[] columnNames = {"id", "name", "addres", "points", "register time"};

    public CardQueryPage(CardAndUserRegisterSystem cardRegister) {
        this.cardRegister = cardRegister;
        setTitle(Constant.TITLE);
        setSize(750, 500);
        openRequestForCard();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * 创建界面组件
     * */
    public void openRequestForCard() {
        //使用绝对定位布局
        JPanel mainPanel = new JPanel(null);
        JLabel jLabel = new JLabel(Constant.QUERY_LABEL);
        jLabel.setFont(Constant.FONT25);
        jLabel.setBounds(300, 0, 200, 100);
        mainPanel.add(jLabel);

        //查询文本框
        JLabel idLabel = new JLabel("by id: ");
        JLabel nameLabel = new JLabel("by name: ");
        idLabel.setFont(Constant.FONT16);
        nameLabel.setFont(Constant.FONT16);
        idLabel.setBounds(20, 100, 80, 30);
        nameLabel.setBounds(370, 100, 80, 30);
        mainPanel.add(idLabel);
        mainPanel.add(nameLabel);

        //查询按钮
        id = new JTextField(8);
        name = new JTextField(8);
        id.setBounds(80, 100, 160, 30);
        name.setBounds(450, 100, 160, 30);
        JButton searchIdBtn = new JButton("query");
        searchIdBtn.setBounds(250, 100, 80, 30);
        JButton searchNameBtn = new JButton("query");
        searchNameBtn.setBounds(620, 100, 80, 30);
        mainPanel.add(id);
        mainPanel.add(name);
        mainPanel.add(searchIdBtn);
        mainPanel.add(searchNameBtn);

        //返回主页按钮
        JButton backHomeBtn = new JButton("back home");
        backHomeBtn.setBounds(620, 10, 100, 40);
        mainPanel.add(backHomeBtn);

        setContentPane(mainPanel);
        setVisible(true);

        backHomeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardQueryPage.this.setVisible(false);
                //本窗口销毁，释放资源
                dispose();
                //跳转主页面
                cardRegister.getMainSystem().getMainPage().setVisible(true);
            }
        });

        //根据卡片id查询
        searchIdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取系统中所有卡片信息
                List<Card> cards = cardRegister.getCards();
                //刷选过滤数据，并返回二维数组，用作在表格中显示
                String[][] rowData = cards.stream().filter(card -> {
                    String text = id.getText();
                    if (text.equals("") || text.equals(card.getId())) {
                        return true;
                    }
                    return false;
                }).map(card -> {
                    return new String[]{card.getId(), card.getName(), card.getAddr().toString(), "" + card.getPoints(), card.getDate().toString()};
                }).toArray(String[][]::new);
                addTable(rowData);
            }
        });

        //根据姓名查询
        searchNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取系统中所有卡片信息
                List<Card> cards = cardRegister.getCards();
                //刷选过滤数据，并返回二维数组，用作在表格中显示
                String[][] rowData = cards.stream().filter(card -> {
                    String text = name.getText();
                    if (text.equals("") || text.equals(card.getName())) {
                        return true;
                    }
                    return false;
                }).map(card -> {
                    return new String[]{card.getId(), card.getName(), card.getAddr().toString(), "" + card.getPoints(), card.getDate().toString()};
                }).toArray(String[][]::new);
                addTable(rowData);
            }
        });


    }

    /**
     * 使用表格展示查询数据
     * */
    private void addTable(String[][] rowData) {
        if (null != table) {
            remove(table);
        }
        // 创建一个表格，指定 所有行数据 和 表头
        table = new JTable(rowData, columnNames);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBounds(100, 150, 550, 30);
        table.setBounds(100, 180, 550, rowData.length * table.getRowHeight());
        add(tableHeader);
        add(table);
        validate();//重新绘制
        repaint();
    }
}
