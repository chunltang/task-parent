package day200412;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemC {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.println("please input:");
        //check and get n
        while (true) {
            Pattern pattern = Pattern.compile("0|1000|([1-9][0-9]{0,2})");
            String line = scanner.nextLine().trim();
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                n = Integer.valueOf(line);
                break;
            }
            System.out.println("please input intger that between 0 and 1000");
        }
        //double type be convenient to calculate
        List<double[]> points = new ArrayList<>();
        Pattern pattern = Pattern.compile("((0|(-?(1000000|[1-9][0-9]{0,5})))\\s+){2}(0|(-?(1000000|[1-9][0-9]{0,5})))");
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
                double[] point = new double[3];
                point[0] = Double.valueOf(split[0]);
                point[1] = Double.valueOf(split[1]);
                point[2] = Double.valueOf(split[2]);
                points.add(point);
                if (n == points.size()) {
                    break;
                }
            }
        }
        checkTriangles(points);
    }


    private static void checkTriangles(List<double[]> points) {
        double area = 0;
        //Nothing
        if (points.size() <= 2) {
            return;
        } else if (points.size() == 3) {//刚号三个点
            area = getArea(points.get(0), points.get(1), points.get(2));
        }else{
            //indexs中，每三个连续元素为一个三角形组合的索引，列入组合[1,3,0]，表示points中索引为1,3,0的点为一个三角形组合
            List<Integer> indexs = new ArrayList<>();
            compoundMode(indexs,points, 0, new int[3], 3, 3, points.size());
            //保存所有可能的三角形组合面积
            Set<Double> areas = new HashSet<>();
            for (int i = 0; i < indexs.size(); i+=3) {
                Integer index1 = indexs.get(i);
                Integer index2 = indexs.get(i+1);
                Integer index3 = indexs.get(i+2);
                areas.add(getArea(points.get(index1), points.get(index2), points.get(index3)));
            }
            //获取集合中最大元素
            area = Collections.max(areas);
        }

        if (area == 0) {
            return;
        }
        //Round to two decimal
        BigDecimal decimal = new BigDecimal(area).setScale(2, RoundingMode.UP);
        System.out.println(decimal);
    }

    /**
     * 遍历所有组合方式
     * @param indexs 保存所有组合索引值，3个为一组
     * @param points 要遍历的集合
     * @param start 遍历起始位置
     * @param result 保存结果
     * @param count 为result数组的索引值
     * @param num  要选取的元素个数
     * @param len 原始集合大小
     * 参考链接：https://www.cnblogs.com/fengzhongzhuifeng/p/10711608.html
     */
    private static void compoundMode(List<Integer> indexs,List<double[]> points,int start,int[] result,int count,int num,int len) {
         int i=0;
         for(i=start;i<len+1-count;i++){
             result[count-1]=i;
             if(count-1==0){
                 int j;
                 for(j=num-1;j>=0;j--){
                     indexs.add(result[j]);
                 }
             }else{
                  compoundMode(indexs,points,i+1,result,count-1,num,len);
             }
         }
    }

    //使用海伦公式计算三角形面积
    private static double getArea(double[] pa, double[] pb, double[] pc) {
        double[] side = new double[3];
        side[0] = Math.sqrt(Math.pow(pa[0] - pb[0], 2) + Math.pow(pa[1] - pb[1], 2) + Math.pow(pa[2] - pb[2], 2));
        side[1] = Math.sqrt(Math.pow(pa[0] - pc[0], 2) + Math.pow(pa[1] - pc[1], 2) + Math.pow(pa[2] - pc[2], 2));
        side[2] = Math.sqrt(Math.pow(pb[0] - pc[0], 2) + Math.pow(pb[1] - pc[1], 2) + Math.pow(pb[2] - pc[2], 2));
        if (side[0] + side[1] <= side[2] || side[0] + side[2] <= side[1] || side[1] + side[2] <= side[0]) return 0;
        double p = (side[0] + side[1] + side[2]) / 2;
        return Math.sqrt(p * (p - side[0]) * (p - side[1]) * (p - side[2]));
    }
}