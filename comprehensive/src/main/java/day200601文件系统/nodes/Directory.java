package day200601文件系统.nodes;

import java.util.HashSet;
import java.util.Set;

public class Directory extends Node {

    /**
     * 目录内部包含多个节点，可以是目录或者文件
     */
    private Set<Node> nodeList = new HashSet<>();

    public Directory(Directory parentNode, String name) {
        super(parentNode, name);
    }

    /**
     * 目录中新增目录或者文件
     */
    public void addNode(Node node) {
        if (null != node) {
            this.nodeList.add(node);
        }
    }

    /**
     * 查找字符串
     */
    @Override
    public Set<String> find(String findStr) {
        Set<String> strings = new HashSet<>();
        if(this.getName().contains(findStr)){
            String fullPath = getFullPath(this.getParentNode(),this.getName());
            strings.add(fullPath);
        }
        for (Node node : nodeList) {
            //节点是目录
            if (node instanceof Files) {
                Files files = (Files) node;
                Set<String> set = files.find(findStr);
                strings.addAll(set);
            }
            //节点是目录
            if (node instanceof Directory) {
                if (this.name.contains(findStr)) {
                    String fullPath = getFullPath(this.getParentNode(),this.getName());
                    strings.add(fullPath);
                }
                //查找所有子目录
                Directory directory = (Directory) node;
                Set<String> set = directory.find(findStr);
                strings.addAll(set);
            }
        }
        return strings;
    }

    public Set<Node> getNodeList() {
        return nodeList;
    }
}
