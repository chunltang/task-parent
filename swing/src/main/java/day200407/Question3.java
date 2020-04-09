package day200407;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//测试时先在文本域中输入数据，点击FileWrite写入文件，
// 然后删除文本域中的内容，点击FileRead按钮读入文件，之前写入的文件内容将显示在文本域中
public class Question3 {

    //文件保存在根目录下的files文件夹里
    private final static String FILE_LOCATION = "/files";

    //文件名称
    private final static String FILE_NAME = "test.txt";

    //文本域对象
    private static JTextArea textArea = null;


    public static void main(String[] args) {
        //创建底层窗口
        final JFrame jf = new JFrame("I/O应用");
        jf.setSize(500, 400);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建主面板，指定使用边界布局
        JPanel mainPanel = new JPanel(new BorderLayout());
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

        //创建底部面板，使用流式布局
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton readBtn = new JButton("FileRead读文件");
        JButton writeBtn = new JButton("FileWrite写文件");
        bottomPanel.add(readBtn);
        bottomPanel.add(writeBtn);

        //读按钮添加监听
        readBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("触发读文件");
                //读文件test.txt文件的内容，并显示到文本域中
                read();
            }
        });

        //写按钮添加监听
        writeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("触发写文件");
                //将文本域中的内容写入文件
                write(textArea.getText());
            }
        });

        //主窗口添加组件
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        jf.setContentPane(mainPanel);
        //最后再设置为可显示(绘制), 所有添加的组件才会显示
        jf.setVisible(true);
    }

    /**
     * 写文件,只对同一个文件读写操作,在项目根目录创建文件夹保存写入的文件
     */
    private static void write(String content) {
        File newFile = createFile();
        //自动关闭流,需要流实现了Closeable接口
        try (FileWriter fileWriter = new FileWriter(newFile)) {
            //将字符串写入文件
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读文件
     */
    private static void read() {
        File file = createFile();
        //如果文件还没有写入，则直接退出
        if(!file.exists()){
            System.out.println("文件还没有写入内容");
            return;
        }
        //在try中的流对象在代码块结束时自动关闭流，需要流实现了Closeable接口
        try (FileReader fileReader = new FileReader(file)) {
            StringBuilder sb = new StringBuilder();
            //创建字符数组
            char[] chars = new char[128];
            //循环判断文件是否可读
            while (fileReader.ready()) {
                //将文件内容读到数组中
                fileReader.read(chars);
                //将数组转为字符串保存
                sb.append(new String(chars));
            }
            //文本域显示文件内容
            textArea.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建文件对象，代码复用
    private static File createFile() {
        //获取项目根目录路径
        String dir = System.getProperty("user.dir");
        //创建保存文件的文件夹
        File file = new File(dir + FILE_LOCATION);
        //文件夹不存在或文件对象不是一个目录
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        //创建要读或者要写的文件对象
        return new File(file.getPath() + File.separator + FILE_NAME);
    }
}

