package day200408;

import java.util.Calendar;
import java.util.Date;

public class MyDate implements Comparable<MyDate> {

    private Date date;

    /**
     * 按年月日构造
     */
    public MyDate(int year, int month, int date) {
        Calendar instance = Calendar.getInstance();
        //设置年月日
        instance.set(year,month,date);
        this.date=instance.getTime();
    }


    /**
     * 按日期构造
     */
    public MyDate() {
        this.date = new Date();
    }

    /**
     * 加减日期
     * @param num 需要加减的天数,负数为减天数，正数为加天数
     */
    public void calculateDate(int num) {
        //使用Calendar操作日期
        Calendar instance = Calendar.getInstance();
        instance.setTime(this.date);
        instance.add(Calendar.DATE,num);
        //加减天数后重新赋值
        this.date=instance.getTime();
    }

    /**
     * 计算星期几
     * */
    public String calculateWeek() {
        //星期数组
        String []arr = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        //使用Calendar操作日期
        Calendar instance = Calendar.getInstance();
        instance.setTime(this.date);
        //1.数组下标从0开始；2.国外的第一天是从星期日开始的
        return arr[instance.get(Calendar.DAY_OF_WEEK)-1];
    }


    /****************覆写方法*******************/

    //只根据equals判断两个对象相等
    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        //判空
        if (null == obj) {
            return false;
        }
        //判断类型
        if (obj instanceof MyDate) {
            MyDate myDate = (MyDate) obj;
            //判断字段是否相等
            if(this.getDate()==null&&myDate.getDate()==null){
                return true;
            }
            if (null!=this.getDate()&&this.getDate().equals(myDate.getDate())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.date.toString();
    }

    @Override
    public int compareTo(MyDate myDate) {
        //判空
        if (null == myDate) {
            return -1;
        }
        //比较日期的时间戳大小
        if (this.getDate().getTime() > myDate.getDate().getTime()) {
            return 1;
        } else if (this.getDate().getTime() == myDate.getDate().getTime()) {
            return 0;
        }
        return -1;
    }

    /*************get/set方法***************/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
