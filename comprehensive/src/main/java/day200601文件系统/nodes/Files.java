package day200601文件系统.nodes;


import day200601文件系统.observer.Listener;
import day200601文件系统.observer.Observerable;

import java.util.HashSet;
import java.util.Set;

/**
 * 文件继承节点，并实现Observerable接口，表示它是一个被观察对象
 * */
public class Files extends Node implements Observerable {

    /**
     * 监听器对象
     * */
    private Listener listener;

    public Files(Directory parentNode,String name){
        super(parentNode,name);
    }

    /**
     * 查找字符串
     * */
    @Override
    public Set<String> find(String findStr) {
        Set<String> strings = new HashSet<>();
        if(this.name.contains(findStr)){
           String fullPath = getFullPath(this.getParentNode(), this.getName());
           strings.add(fullPath);
       }
        return strings;
    }

    /**
     * 文件写入
     * */
    @Override
    public void write(String message) {
        System.out.println("<"+this.name+">文件写入内容为："+message);
        listener.update(this,message);
    }

    /**
     * 添加监听器
     * */
    @Override
    public void addListener(Listener listener) {
        this.listener=listener;
    }
}
