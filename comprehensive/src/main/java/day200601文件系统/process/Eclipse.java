package day200601文件系统.process;

import day200601文件系统.nodes.Node;
import day200601文件系统.observer.Observer;
import day200601文件系统.observer.Observerable;

/**
 * eclipse程序,观察文件的变化
 */
public class Eclipse implements Observer {

    /**
     * 程序名称
     */
    private String processName;

    public Eclipse() {
        this.processName = "eclipse";
    }

    @Override
    public void update(Observerable observerable, String message) {
        System.out.println(this.processName+"接到<"+((Node)observerable).getName()+">文件内容改变的通知，我要重新加载文件了...");
    }
}
