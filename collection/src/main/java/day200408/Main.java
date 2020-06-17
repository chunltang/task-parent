package day200408;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        MyDate myDate=null;
        MyList<MyDate> myList = new MyList<>();
        Random random = new Random();
        //实现添加个100MyDate实列
        for (int i = 0; i < 100; i++) {
            MyDate date = new MyDate();
            System.out.println("未加减天数前："+date);
            int nextInt = random.nextInt(50);
            //随机加天数
            date.calculateDate(nextInt);
            System.out.println("加"+nextInt+"天后的时间："+date);
            String s = date.calculateWeek();
            System.out.println("使用"+date+" 计算获得星期："+s);
            //获取一个值，方便测试remove和contains方法
            if(i==50){
                myDate=date;
            }
            myList.add(date);
            System.out.println("\r\n测试add方法：size="+myList.size()+"\r\n");
        }
        int size = myList.size();
        System.out.println("测试size方法：size="+size);

        boolean contains = myList.contains(myDate);
        System.out.println("测试contains方法："+contains);


        //获取第一个元素
        MyDate first = myList.getFirst();
        //首节点删除
        myList.remove(first);
        System.out.println("\r\n首节点删除测试，删除元素后size="+myList.size());
        System.out.println("删除第一个元素后，第一个元素变为："+myList.getFirst().toString());
        System.out.println("myList打印结果："+myList.toString());

        //使用equals判断相等的元素都删除，可能删除0至多个
        myList.remove(myDate);
        System.out.println("测试remove方法，删除元素后size="+myList.size());

        //测试泛型获取数组
        MyDate[] myDates = myList.toArray(new MyDate[0]);
        System.out.println("测试toArray获取泛型数组方法，获取的数组长度length="+myDates.length);

        //测试获取Object数组
        Object[] array = myList.toArray();
        System.out.println("测试toArray获取Object数组方法，获取的数组长度length="+array.length);

        myList.clear();
        System.out.println("测试clear方法，清除元素后size="+myList.size());
    }
}
