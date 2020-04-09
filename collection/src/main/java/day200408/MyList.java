package day200408;

import java.lang.reflect.Array;

public class MyList<E> implements Collection<E> {

    //集合大小
    private int size = 0;

    //列表的第一个节点
    private Node<E> first;


    public MyList() {

    }

    /**
     * 添加元素
     * */
    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(null, e, null);
        if (null == first) {
            first = newNode;
        } else {
            //将新元素放到首位
            final Node<E> f = first;
            newNode.next = f;
            first = newNode;
            f.prev = newNode;
            if(e instanceof Comparable){
                //对新元素排序
                Node<E> curNode = newNode;
                Node<E> nextNode = newNode.next;
                Comparable curItem = (Comparable) (curNode.item);
                while (curItem.compareTo(nextNode.item) > 0) {
                    //交换两个节点的元素
                    E item = curNode.item;
                    curNode.item = nextNode.item;
                    nextNode.item = item;
                    //重新赋值
                    curNode = nextNode;
                    nextNode = nextNode.next;
                    if (null == nextNode) {
                        break;
                    }
                }
            }
        }
        size++;
    }

    /**
     * 移除元素
     * */
    @Override
    public void remove(E e) {
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if(node.item.equals(e)){
                Node<E> prev = node.prev;
                Node<E> next = node.next;
                //将查找到的元素删除，并将该元素的前后节点进行关联
                if(null!=prev){
                    prev.next=next;
                }
                if(null!=next){
                    next.prev=prev;
                }
                node=null;
                node=next;
                size--;
            }else{
                node=node.next;
            }
        }
    }

    //清除
    @Override
    public void clear() {
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            Node<E> next = node.next;
            //将关联节点引设置为null
            node.prev=null;
            node.next=null;
            node=null;
            node=next;
        }
        first=null;
        size=0;
    }

    /**
     * 获取第一个元素
     * */
    @Override
    public E getFirst() {
        return null!=first?first.item:null;
    }

    /**
     * 包含
     * */
    @Override
    public boolean contains(E e) {
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            //包含目标元素则返回true
            if(node.item.equals(e)){
                return true;
            }
            node=node.next;
        }
        return false;
    }

    /**
     * 返回集合大小
     * */
    @Override
    public int size() {
        return size;
    }

    /**
     * 返回object数组
     * */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            //数组循环赋值
            arr[i] =node.item;
            node=node.next;
        }
        return arr;
    }

    /**
     * 返回泛型数组
     * */
    @Override
    public E[] toArray(E[] arr) {
        Class<? extends Object[]> aClass = arr.getClass();
        //创建泛型数组
        E[] result = (E[]) Array.newInstance(aClass.getComponentType(), size);
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            result[i] =node.item;
            node=node.next;
        }
        return result;
    }

    /**
     * 转为字符串输出
     * */
    @Override
    public String toString() {
        Node<E> f = first;
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append("[").append("\r\n");
        ;

        while (null != f) {
            sb.append(f.item.toString()).append("\r\n");
            f = f.next;
        }
        String s = sb.toString();
        if (size > 0) {
            s = s.substring(0, s.length() - 1);
        }
        return s + "]";
    }

    /**
     * 内部静态节点类
     * */
    private static class Node<E> {
        //节点保存的元素
        E item;
        //当前节点的下个节点
        Node<E> next;
        //当前节点的前一个节点
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
