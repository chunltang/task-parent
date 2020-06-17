package day200412;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input:");
        int n;
        //check and get n
        while (true) {
            Pattern pattern = Pattern.compile("0|[1-9][0-9]*");
            String line = scanner.nextLine().trim();
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                n = Integer.valueOf(line);
                break;
            }
            System.out.println("please input intger that be equal or greater than 0");
        }
        Pattern pattern = Pattern.compile("((0|[1-9][0-9]*)\\s+){7}(0|[1-9][0-9]*)");
        List<int[]> rectangles = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.length() > 0) {
                Matcher matcher = pattern.matcher(line);
                ////校验输入数据的合法性
                if (!matcher.matches()) {
                    System.out.println("bad input!");
                    continue;
                }
                //数据转为int数组存储
                String[] split = line.split("\\s+");
                int[] rectangle;
                for (int i = 0; i < 2; i++) {
                    rectangle = new int[4];
                    for (int j = 0; j < 4; j++) {
                        rectangle[j] = Integer.valueOf(split[j + i * 4]);
                    }
                    if (validate(rectangle)) {
                        rectangles.add(rectangle);
                    }
                }
                //退出读取数据，每行输入记录，rectangles保存一对矩形数据
                if (n == rectangles.size() / 2) {
                    break;
                }
            }
        }
        checkOverlap(rectangles);
    }

    /**
     * 校验左下位置和右上位置的输入合法性
     * x1,y1,x2,y2
     */
    private static boolean validate(int[] rectangle) {
        int x1 = rectangle[0];
        int y1 = rectangle[1];
        int x2 = rectangle[2];
        int y2 = rectangle[3];
        if (x1 >= x2 || y1 >= y2) {
            System.out.println("bottom-left or top-right coordinates error");
            return false;
        }
        return true;
    }

    /**
     * check overlap
     * 参考链接：https://www.csdndoc.com/article/4331409
     * max(Xa1,Xb1) <= min(Xa2,Xb2)
     * max(Ya1,Yb1) <= min(Ya2,Yb2)
     * */
    private static void checkOverlap(List<int[]> rectangles){
        for (int i = 0; i < rectangles.size() / 2; i++) {
            int[] coordinates1 = rectangles.get(i);
            int[] coordinates2 = rectangles.get(i+1);
            if(Math.max(coordinates1[0],coordinates2[0])<=Math.min(coordinates1[2],coordinates2[2])&&
                Math.max(coordinates1[1],coordinates2[1])<=Math.min(coordinates1[3],coordinates2[3])){
                int width1 = coordinates1[2] - coordinates1[0];
                int height1 = coordinates1[3] - coordinates1[1];
                int width2 = coordinates2[2] - coordinates2[0];
                int height2 = coordinates2[3] - coordinates2[1];
                //检查接触但不覆盖，如果两个矩形的x最小值和最大值之差等于两个矩形宽度之和
                //如果两个矩形的y最小值和最大值之差等于两个矩形高度之和，判断为接触但不覆盖，输出False
                if(Math.min(coordinates1[0],coordinates2[0])+Math.max(coordinates1[2],coordinates1[2])==width1+width2||
                    Math.min(coordinates1[1],coordinates2[1])+Math.max(coordinates1[3],coordinates1[3])==height1+height2){
                    System.out.println("False");
                }else{
                    System.out.println("True");
                }
            }else{
                System.out.println("False");
            }
        }
    }
}