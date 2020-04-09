package day200407;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class Question1 {

    //文本框
    private static JTextArea textArea = null;

    //选中的性别
    private static String gender = "";

    //选中的兴趣，多个用HashSet保存去重
    private static Set<String> interest = new HashSet<>();

    public static void main(String[] args) {
        //创建窗口
        JFrame jf = new JFrame("兴趣");
        //设置窗体大小
        jf.setSize(400, 400);
        //设置关闭窗体操作
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        // 创建主面板，指定使用边界布局
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 创建上部面板，指定使用流式布局
        JPanel topPanel = new JPanel(new FlowLayout());

        //创建“兴趣”标签
        JLabel interest = new JLabel();
        interest.setText("兴趣");

        //创建“性别”标签
        JLabel gender = new JLabel();
        gender.setText("性别");

        //创建爱好复选框
        JCheckBox badminton = new JCheckBox("羽毛球");
        JCheckBox tableTennis = new JCheckBox("乒乓球");
        JCheckBox sing = new JCheckBox("唱歌");

        // 创建性别单选按钮
        JRadioButton male = new JRadioButton("男");
        JRadioButton female = new JRadioButton("女");

        // 创建按钮组，把两个单选按钮添加到该组
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        //上部区域添加组件
        topPanel.add(interest);
        topPanel.add(badminton);
        topPanel.add(tableTennis);
        topPanel.add(sing);
        topPanel.add(gender);
        topPanel.add(male);
        topPanel.add(female);

        //创建中间区域带滚动条的文本域组件
        textArea = new JTextArea();
        // 自动换行
        textArea.setLineWrap(true);
        // 设置字体
        textArea.setFont(new Font(null, Font.PLAIN, 18));
        // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条文字多时显示, 水平滚动条从不显示
        JScrollPane scrollPane = new JScrollPane(
                textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        //创建下方按钮组件
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton confirm = new JButton("确定");
        bottomPanel.add(confirm);

        //设置动作命令，使用枚举保存命令字符串，方便扩展实用
        badminton.setActionCommand(Command.BADMINTON.name());
        tableTennis.setActionCommand(Command.TABLETENNIS.name());
        sing.setActionCommand(Command.SING.name());
        male.setActionCommand(Command.MALE.name());
        female.setActionCommand(Command.FEMALE.name());
        confirm.setActionCommand(Command.CONFIRM.name());

        //添加监听
        ActionListener listener = createActionListener();
        badminton.addActionListener(listener);
        tableTennis.addActionListener(listener);
        sing.addActionListener(listener);
        male.addActionListener(listener);
        female.addActionListener(listener);
        confirm.addActionListener(listener);

        //主窗口添加组件
        //添加上方流式布局窗体
        mainPanel.add(topPanel, BorderLayout.NORTH);
        //添加下方组件按钮
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        //添加中部组件文本框
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        //顶层父窗口添加组件
        jf.setContentPane(mainPanel);
        //最后再设置为可显示(绘制), 所有添加的组件才会显示
        jf.setVisible(true);
    }

    /**
     * 创建事件监听器
     */
    private static ActionListener createActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                // 获取动作命令
                String value = e.getActionCommand();
                //获取字符串对应的枚举值
                Command command = Command.valueOf(value);
                switch (command) {
                    //触发性别单选按钮
                    case MALE:
                    case FEMALE:
                        JRadioButton radioButton = (JRadioButton) source;
                        //为选中状态时赋值
                        if (radioButton.isSelected()) {
                            gender = radioButton.getText();
                        } else {
                            //取消选中时清空
                            gender = "";
                        }
                        break;
                    //触发兴趣复选框
                    case BADMINTON:
                    case TABLETENNIS:
                    case SING:
                        JCheckBox checkBox = (JCheckBox) source;
                        //为选中状态时，添加到集合中
                        if (checkBox.isSelected()) {
                            interest.add(checkBox.getText());
                        } else {
                            //取消选中时删除
                            interest.remove(checkBox.getText());
                        }
                        break;
                    //触发确认按钮
                    case CONFIRM:
                        //创建文本框显示对象
                        StringBuilder sb = new StringBuilder();
                        //将兴趣集合转为数组
                        String[] array = interest.toArray(new String[0]);
                        //将数组已逗号连接为字符串
                        String join = String.join(",", array);
                        //连接兴趣
                        sb.append("你的爱好为：").append(join);
                        //换行
                        sb.append("\r\n");
                        //连接性别
                        sb.append("你的性别为：").append(gender);
                        //设置文本域显示字符串
                        textArea.setText(sb.toString());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    /**
     * 动作命令枚举
     */
    public enum Command {

        BADMINTON,
        TABLETENNIS,
        SING,
        MALE,
        FEMALE,
        CONFIRM
    }

}
