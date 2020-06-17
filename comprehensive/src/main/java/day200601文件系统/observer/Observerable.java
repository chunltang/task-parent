package day200601文件系统.observer;

/**
 * 抽象被观察者接口
 */
public interface Observerable {

    /**
     * 文件写入方法
     * */
    void write(String message);

    /**
     * 添加监听器
     * */
    void addListener(Listener listener);

}
