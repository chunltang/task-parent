package day200615.源码;

import java.util.Scanner;

public class printMain {

    public static void main(String[] args) {
        String desc="%d年10月1日是中华人民共和国成立%d周年的日子";
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入:");
        while (scanner.hasNext("[0-9]+")){
            int year = scanner.nextInt();
            System.out.println(String.format(desc,year,year-1949));
            System.out.println("输入:");
        }
    }
}
