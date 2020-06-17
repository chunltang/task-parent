package day200412;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String  input;
        System.out.println("please input:");
        //check and get n
        while (true) {
            Pattern pattern = Pattern.compile("[0-9|a-zA-Z|\\s]+");
            String line = scanner.nextLine().trim();
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                input = line;
                break;
            }
            System.out.println("input consists of numbers,white-space character and Englist letters");
        }
        //移除所有空格
        String str = input.replaceAll("\\s+", "");
        if(str.length()>0){
            print(str);
        }
    }



    public static void print(String str) {
        int fill = needFill(str.length());
        if(fill>0){
            str=str+str.substring(0,fill);
        }
        int len = str.length();
        char[] array = str.toCharArray();
        int x = len / 2;//x,y为第一个元素位置
        int y = len / 2;
        int right = 1, down = 2, left = 3, up = 4;//分别表示右下左上四个方向
        int direction = up;//改变方向的规律：1 2 3 5 7 10 13 17 21 26 31 37 43 50 57
        int step = 1;                   // 1  1 1 2   2  2  3  3  3  4
        int num = 1;//每两次，step+1     // 0  1 2 0   1  2  0  1  2  0
        int total = 1;//当前数           // 1  2 3 3   5  7  7  10 13 13
        char[][] chars = new char[len][len];//二维数组
        chars[x][y] = array[0];//第一个元素
        int k = 1;//赋值次数
        while (k < len) {
            {
                if (direction == up) {
                    if (k > total) {
                        total += step;
                        num++;
                        direction = right;
                        continue;
                    } else {
                        x--;
                        chars[x][y] = array[k];
                        k++;
                    }
                }
                if (direction == right) {
                    if (k > total) {
                        total += step;
                        num++;
                        direction = down;
                        continue;
                    } else {
                        y++;
                        chars[x][y] = array[k];
                        k++;
                    }
                }
                if (direction == down) {
                    if (k > total) {
                        total += step;
                        num++;
                        direction = left;
                        continue;
                    } else {
                        x++;
                        chars[x][y] = array[k];
                        k++;
                    }
                }
                if (direction == left) {
                    if (k > total) {
                        total += step;
                        num++;
                        direction = up;
                        continue;
                    } else {
                        y--;
                        chars[x][y] = array[k];
                        k++;
                    }
                }
                if (num == 2) {
                    num = 0;
                    step++;
                }
            }
        }

        //输出结果
        for (int i = 0; i < len; i++) {
            boolean flag=false;
            for (int j = 0; j < len; j++) {
                char c = chars[i][j];
                if((int)c!=0){
                    System.out.print(c + " ");
                    flag=true;
                }
            }
            if(flag){
                System.out.println();
            }
        }
    }

    /**
     * 通过规律计算最后需要补充几个元素
     * 规律是：每两次拐点步长+1
     * 1 2 3 5 7 10 13 17 21 26 31 37 43 50 57  这是表示第几个元素需要拐弯
     *  1 1 2 2 3  3  4  4  5  5  6  6  7  7
     *
     */
    private static int needFill(int len) {
        int step = 1;//步长
        int num = 1;//当前第几次
        int total = 1;//当前拐点所在元素是第几个元素
        while (total < len) {
            total += step;
            num++;
            if (num == 2) {
                num = 0;
                step++;
            }
        }
        return total-len;
    }
}
