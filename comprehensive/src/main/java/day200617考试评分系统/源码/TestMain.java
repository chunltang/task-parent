package day200617考试评分系统.源码;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//运行main方法，在控制台输入题目答案即可
public class TestMain {

    public static void main(String[] args) throws IOException {
        ////解析文件，生成题库，test.txt为保存题目的文件
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.txt");
        if (null == inputStream) {
            System.out.println("题库不存在");
            return;
        }
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, bytes.length);//读取文件
        inputStream.close();

        Problem problem;
        List<Problem> problems = new ArrayList<>();//题库集合
        String[] split = new String(bytes).split("(\r\n)+");//按换行符拆分
        for (int i = 0; i < split.length; i++) {
            problem = new Problem();
            if (i % 4 == 0) {//每四行为一题
                String[] arr = split[i].split("\\s+");//按空格拆分每个题的第一行
                problem.setId(Integer.valueOf(arr[0]));
                problem.setAnswer(arr[1].charAt(0));
            }
            problem.setDesc(arrToStr(split, i));//每个题的后三行为题目描述
            problems.add(problem);
            i += 3;
        }

        Collections.shuffle(problems);//打乱排序，随机获取题库

        int index = 0;//当前题目个数
        Scanner in = new Scanner(System.in);
        StringBuilder sb=new StringBuilder();
        System.out.println("请阅读题目，并在A、B、C、D四个选项中选择出正确答案:");
        for (Problem pro : problems) {
            String str = ++index + "." + pro.getDesc();
            System.out.println(str);
            System.out.println("请输入正确答案：");
            String next = in.next();
            char at = next.toUpperCase().charAt(0);
            pro.setResponse(at);
            sb.append(str).append("\r\n");//保存题目和回答，用于输出文件
            sb.append("回答: ").append(at).append("\r\n").append("\r\n");
            if(index==10){//只生成10个题目
                break;
            }
        }
        //计算正确回答的题目数
        long sum = problems.subList(0, 10).stream().filter(pro -> pro.getAnswer() == pro.getResponse()).count();
        System.out.println("总分数为: "+sum*10+"分");
        sb.append("总分数为: ").append(sum*10).append("分");

        FileWriter writer = new FileWriter("pro.txt");//输出文件
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    /**
     * 生成题目描述
     */
    private static String arrToStr(String[] arr, int i) {
        StringBuilder sb = new StringBuilder();
        if (arr.length > i + 3) {
            sb.append(arr[i + 1]).append("\r\n");
            sb.append(arr[i + 2]).append("\r\n");
            sb.append(arr[i + 3]);
        }
        return sb.toString();
    }


    /**
     * 题目对象
     */
    static class Problem {

        /**
         * 题编号
         */
        private int id;

        /**
         * 答案
         */
        private char answer;

        /**
         * 题描述
         */
        private String desc;

        /**
         * 回答
         */
        private char response;

        public char getResponse() {
            return response;
        }

        public void setResponse(char response) {
            this.response = response;
        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public char getAnswer() {
            return answer;
        }

        public void setAnswer(char answer) {
            this.answer = answer;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
