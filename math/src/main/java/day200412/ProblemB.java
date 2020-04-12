package day200412;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemB {

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
        Pattern pattern = Pattern.compile("((0|[1-9][0-9]*)\\s+){2}(0|[1-9][0-9]*)");
        List<int[]> capacities = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.length() > 0) {
                Matcher matcher = pattern.matcher(line);
                //校验输入数据的合法性
                if (!matcher.matches()) {
                    System.out.println("bad input!");
                    continue;
                }
                //数据转为int数组存储
                String[] split = line.split("\\s+");
                int[] jug = new int[3];
                jug[0] = Integer.valueOf(split[0]);
                jug[1] = Integer.valueOf(split[1]);
                jug[2] = Integer.valueOf(split[2]);
                capacities.add(jug);
                if (n == capacities.size()) {
                    break;
                }
            }
        }
        checkCapacity(capacities);
    }

    /**
     * 检查方案是否可行，可查参考链接：https://www.jianshu.com/p/2be990a4da44
     */
    private static void checkCapacity(List<int[]> capacities) {
        for (int[] jug : capacities) {
            int x = jug[0];
            int y = jug[1];
            int z = jug[2];
            if (z > x + y) {
                System.out.println("False");
            }
            if (z == x || z == y || z == 0) {
                System.out.println("True");
                break;
            }
            int min = Math.min(x, y);
            int divisor = 0;
            //求得到最大公约数
            for (int i = min; i >= 1; i--) {
                if (x % i == 0 && y % i == 0) {
                    divisor = i;
                    break;
                }
            }
            if (divisor != 0 && z % divisor == 0) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }

}