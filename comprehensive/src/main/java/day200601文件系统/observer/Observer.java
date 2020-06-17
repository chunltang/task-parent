package day200601文件系统.observer;

/**
 * 抽象观察者
 */
public interface Observer {

    //对观察到的文件写操作做对应改变
    void update(Observerable observerable, String message);
}
