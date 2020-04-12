import org.junit.Before;
import org.junit.Test;

public class MyDateTest {

    private MyDate myDate1=null;

    private MyDate myDate2=null;

    @Before
    public void before(){
        myDate1=new MyDate();
        myDate2=new MyDate(2021,1,1);
    }

    @Test
    public void testCalculateDate(){
        System.out.println("myDate1加时间前:"+myDate1.getDate());
        myDate1.calculateDate(1);
        System.out.println("myDate1加时间后:"+myDate1.getDate());

        System.out.println("myDate2加时间前:"+myDate2.getDate());
        myDate2.calculateDate(1);
        System.out.println("myDate2加时间后:"+myDate2.getDate());
    }

    @Test
    public void testCalculateWeek(){
        String s1 = myDate1.calculateWeek();
        System.out.println(myDate1.getDate()+" myDate计算星期几:"+s1);

        String s2 = myDate2.calculateWeek();
        System.out.println(myDate2.getDate()+" myDate计算星期几:"+s2);
    }
}
