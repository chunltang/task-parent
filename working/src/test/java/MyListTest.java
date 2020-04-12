import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class MyListTest {

    private MyList<MyDate> myList = null;

    private MyDate myDate=null;

    @Before
    public void before() {
        myList = new MyList<>();
        Random random = new Random();
        //实现添加个100MyDate实列
        for (int i = 0; i < 100; i++) {
            MyDate myDate = new MyDate();
            int nextInt = random.nextInt(50);
            //随机加天数
            myDate.calculateDate(nextInt);
            //获取一个值，方便测试remove和contains方法
            if(i==50){
                this.myDate=myDate;
            }
            myList.add(myDate);
        }
    }

    @Test
    public void testAdd() {
        System.out.println("add+size方法测试结果:" + myList.toString());
    }

    @Test
    public void testClear() {
        myList.clear();
        System.out.println("clear+size方法测试结果:" + myList.toString());
    }

    @Test
    public void testContains() {
        //day200408.MyDate first = myList.getFirst();
        MyDate myDate = this.myDate;
        System.out.println("contains方法测试结果:" + myList.contains(myDate));
        MyDate m = new MyDate();
        System.out.println("contains方法测试结果:" + myList.contains(m));
    }

    @Test
    public void testRemove() {
        //day200408.MyDate first = myList.getFirst();
        MyDate myDate = this.myDate;
        //集合中可能包含多个相等元素，可以一次性删除多个元素
        myList.remove(myDate);
        System.out.println("remove+size方法测试结果:" + myList.toString());
    }


    /*public static void main(String[] args) {


        day200408.MyDate[] myDates2 = myList.toArray(new day200408.MyDate[0]);
        myList.clear();
        day200408.MyDate[] myDates = myList.toArray(new day200408.MyDate[0]);
        System.out.println(myDates.toString());
    }*/
}
