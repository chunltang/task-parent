package com.study.file;

import day200601文件系统.FileSystem;
import day200601文件系统.nodes.Files;
import day200601文件系统.observer.Listener;
import day200601文件系统.process.Eclipse;
import day200601文件系统.process.Word;
import org.junit.Test;

/**
 * 文件系统测试
 */
public class FileSystemTest {

    private FileSystem fileSystem = null;

    /**
     * 测试import方法
     */
    @Test
    public void testImports() {
        fileSystem = new FileSystem();
        fileSystem.imports("example.txt");
    }

    /**
     * 测试find
     */
    @Test
    public void testFind() {
        testImports();
        System.out.println("find方法打印：");
        System.out.println("查找[dir]字符串结果：");
        fileSystem.find("dir");

        System.out.println("查找[dir1]字符串结果：");
        fileSystem.find("dir1");

        System.out.println("查找[file2.pdf]字符串结果：");
        fileSystem.find("file2.pdf");

        System.out.println("查找[file3.png]字符串结果：");
        fileSystem.find("file3.png");

        System.out.println("查找[12345]字符串结果：");
        fileSystem.find("12345");

    }

    /**
     * 测试观察者模式
     */
    @Test
    public void testObserver() {
        //创建代理观察者
        Listener listener = new Listener();
        //创建观察者程序对象
        Word word = new Word();
        Eclipse eclipse = new Eclipse();
        //添加观察者
        listener.addObserver(word);
        listener.addObserver(eclipse);

        //创建文件对象
        Files files = new Files(null, "a.txt");
        //添加监听
        files.addListener(listener);
        String message = "写入第一个字符串";
        //文件写入
        files.write(message);

        message = "写入第二个字符串";
        System.out.println("====关闭word程序,在次写入文件====");
        //移除word观察者
        listener.removeObserver(word);
        //文件写入
        files.write(message);
    }
}
