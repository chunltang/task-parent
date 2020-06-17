package day200601文件系统.nodes;

import java.util.Set;

/**
 * 文件系统节点
 * */
public abstract class Node {

    /**
     * 节点名称
     * */
    public String name;

    /**
     * 父节点
     * */
    private Node parentNode;

    public Node(Directory parentNode,String name){
        this.name=name;
        this.parentNode=parentNode;
    }

    /**
     * 获取全路径
     * */
    public String getFullPath(Node node,String path){
        if(null==node){
            return path;
        }
        if(node instanceof Directory){
            Directory directory = (Directory) node;
            path=directory.getName()+"/"+path;
            if(directory.getParentNode()!=null){
                return getFullPath(directory.getParentNode(),path);
            }
        }
        return path;
    }

    /**
     * 抽象find方法
     * */
    public abstract Set<String> find(String findStr);

    /**************************get and set*****************************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
}
