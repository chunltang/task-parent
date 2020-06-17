package day200601文件系统;


import day200601文件系统.nodes.Directory;
import day200601文件系统.nodes.Files;
import day200601文件系统.nodes.Node;

import java.io.*;
import java.util.*;

public class FileSystem {

    /**
     * 根节点
     */
    private Directory root;

    /**
     * 已有路径和节点映射
     */
    private Map<String, Node> paths = new HashMap<>();

    public FileSystem() {

    }

    /**
     * 是否已有路径，存在则返回value，不存在则返回null
     */
    private Node getNode(String path) {
        if (path.startsWith("f:") || path.startsWith("d:")) {
            path = path.substring(2, path.length());
        }
        if (paths.keySet().contains(path)) {
            return paths.get(path);
        }
        return null;
    }

    /**
     * 保存路径额对象映射
     */
    private void putNode(String path, Node node) {
        this.paths.put(path, node);
    }


    /**
     * 查找字符串
     */
    public void find(String findStr) {
        Set<Node> nodes = this.root.getNodeList();
        HashSet<String> result = new HashSet<>();
        for (Node node : nodes) {
            result.addAll(node.find(findStr));
        }
        for (String s : result) {
            System.out.println(s);
        }
        if(result.size()==0){
            System.out.println("empty list");
        }
    }

    /**
     * import方法
     *
     * @param filePath 文件的相对路径
     */
    public void imports(String filePath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
        if (null == inputStream) {
            System.out.println("文件不存在，导入失败!!!");
            return;
        }
        System.out.println("import方法导入所有文件路径!");
        try {
            //BufferedReader可以一行行读取数据，方便操作
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                create(line);
            }
            showTree();
            show();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在:" + e);
        } catch (IOException e) {
            System.out.println("文件读取异常:" + e);
        }
    }

    public void create(String path) {
        if (path.length() > 2) {
            //前缀
            String prefix = "";
            //去掉前缀匹配
            String str = path;
            if (path.startsWith("f:") || path.startsWith("d:")) {
                prefix = str.substring(0, 2);
                str = str.substring(2, path.length());
            }
            Node node = getNode(str);
            //不存在的路径,需要递归创建
            if (null == node) {
                String dir = str;
                //去掉文件名称
                if (prefix.equals("f:")) {
                    int index = dir.lastIndexOf("/");
                    dir = dir.substring(0, index);
                }
                //递归创建目录
                createDir("", dir);
            }
            //是文件
            if (prefix.equals("f:")) {
                int index = path.lastIndexOf("/");
                if (index > 0) {
                    String fileName = path.substring(index + 1, path.length());
                    String dir = path.substring(0, index);
                    //获取父节点
                    Directory parentDir = (Directory) getNode(dir);
                    Files files = new Files(parentDir, fileName);
                    parentDir.addNode(files);
                    putNode(str, files);
                }
            }

        }
    }

    /**
     * 递归创建目录
     */
    public void createDir(String beforeDir, String path) {
        //去掉最后的斜杠
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        int index = path.indexOf("/");
        String dir = path;
        String afterDir = "";
        //有下级目录
        if (index > 0) {
            dir = path.substring(0, index);
            afterDir = path.substring(index + 1, path.length());
            createDir(beforeDir.length() > 0 ? (beforeDir + "/" + dir) : dir, afterDir);
            return;
        }
        //判断是否是已有路径
        String str = beforeDir.length() > 0 ? (beforeDir + "/" + dir) : dir;
        Node node = getNode(str);
        if (null == node) {
            if (beforeDir.length() > 0) {
                //前缀目录不为空，表示非根目录，有父级目录
                Directory parentDir = (Directory) getNode(beforeDir);
                Directory directory = new Directory(parentDir, dir);
                parentDir.addNode(directory);
                putNode(beforeDir + "/" + dir, directory);
            } else {
                //前缀目录为空，表示是根目录
                this.root = new Directory(null, dir);
                putNode(dir, this.root);
            }
        }
    }

    public void showTree() {
        System.out.println("showTree显示文件系统结构：");
        SortedSet<String> sortedSet = createSortSet();
        Set<String> paths = new HashSet<>();
        findNode(this.root, sortedSet);
        for (String s : sortedSet) {
            System.out.println(s);
        }
    }

    public void findNode(Node node, Set<String> nodes) {
        String fullPath = node.getFullPath(node.getParentNode(), node.getName());
        nodes.add(fullPath);
        if (node instanceof Directory) {
            Directory directory = (Directory) node;
            for (Node n : directory.getNodeList()) {
                findNode(n, nodes);
            }
        }
    }

    /**
     * 打印所有文件
     */
    public void show() {
        System.out.println("show方法打印文件系统中的所有文件路径:");
        Set<Node> nodes = this.root.getNodeList();
        //排序，按路径长度从小到大打印
        SortedSet<String> sortedSet = createSortSet();
        for (Node node : nodes) {
            if (node instanceof Files) {
                sortedSet.add("root/" + node.getName());
            }
            if (node instanceof Directory) {
                sortedSet.add(getNodePath((Directory) node, "root"));
            }
        }
        for (String s : sortedSet) {
            System.out.println(s);
        }
    }

    private SortedSet<String> createSortSet() {
        return new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                }else{
                    return o1.compareTo(o2);
                }
            }
        });
    }

    /**
     * 获取节点全路径
     */
    private String getNodePath(Directory directory, String path) {
        for (Node node : directory.getNodeList()) {
            if (node instanceof Directory) {
                return getNodePath((Directory) node, path + "/" + directory.getName());
            }
            if (node instanceof Files) {
                return path + "/" + directory.getName() + "/" + node.getName();
            }
        }
        return path + "/" + directory.getName();
    }
}
