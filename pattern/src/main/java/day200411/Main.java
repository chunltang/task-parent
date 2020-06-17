package day200411;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        //创建控制台输入对象
        Scanner scanner = new Scanner(System.in);
        //所有数据行，使用集合保存
        List<String>  lines=new ArrayList<>();
        System.out.println("请输入数据：");
        while(scanner.hasNext()){
            //循环读取一行数据，并去掉前后空格
            String line = scanner.nextLine().trim();
            //空行去除
            if(null!=line&&line.length()>0){
                //读到exit退出读取数据
                if(line.equals("exit")){
                    break;
                }
                lines.add(line);
            }
        }
        //创建数据处理类
        DealData dealData=new DealData(lines);
        //获取处理结果
        dealData.getDealDataResult();
    }
}

/*
2015/8/2 4:00	133.8400	133.070	1.11/1.21	75.780
2015/8/2 6:00	133.840	133.080	11.11/1.11	72.8a0
2015/8/2 8:00	133.830	133.070	1.11/1.11	73.890
2015/8/2 10:00	133.820	133.080	1.11/1.11	78.380
exit
*/
