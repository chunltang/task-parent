import java.util.Scanner;

/**
 * 编译时指定文件编码为utf8，不然文件中有中文，编译异常
 * 正确编译命令为：javac -encoding utf8 MyStudent.java
 * 执行：java MyStudent
 */
public class MyStudent {

    /**
     * 第一种方式
     * */
    public static void main(String[] args) {
        MyStudent myStudent = new MyStudent();
        myStudent.setGrade("B15");
        myStudent.setClassName("1班");
        myStudent.setName("刘明");
        myStudent.setCollege("信息技术学院");
        myStudent.setMajor("软件");
        myStudent.setDatabase(90.0f);
        myStudent.setNetworkPrinciples(95.0f);
        myStudent.setObjectOrientedProgramming(91.0f);
        System.out.println("第一种方式，通过在程序中直接为成员变量赋值,输出:");
        System.out.println(myStudent.toString());
    }

    /**
     * 第二种方式
     * */
    /*public static void main(String[] args) {
        MyStudent myStudent = new MyStudent("B15","信息技术学院","软件","1班","刘明",91.0f,95.0f,90.0f);
        System.out.println("第二种方式，通过构造函数为成员变量赋值,输出:");
        System.out.println(myStudent.toString());
    }*/

    /**
     * 第三种方式,命令行参数按照输入顺序，保存在args数组中
     * 命令行执行命令：java MyStudent B15 信息技术学院 软件 1班 刘明 91.0 95.0  90.0
     * */
    /*public static void main(String[] args) {
        //输入参数个数不为8
        if(args.length!=8){
            System.out.println("命令行输入参数异常!");
            return;
        }
        MyStudent myStudent = getMyStudent(args);
        if(null!=myStudent){
            System.out.println("第三种方式，通过命令行为成员变量赋值,输出:");
            System.out.println(myStudent.toString());
        }
    }*/


    /**
     * 第四种方式
     * 控制台输入: B15 信息技术学院 软件 1班 刘明 91.0 95.0  90.0
     */
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入参数：");
        int i = 0;
        String[] params = new String[8];
        while (i < 8) {
            String next = scanner.next();
            params[i] = next;
            i++;
        }
        MyStudent myStudent = getMyStudent(params);
        if(null!=myStudent){
            System.out.println("第四种方式，通过键盘输入为各成员变量赋值,输出:");
            System.out.println(myStudent.toString());
        }
    }*/

    /**
     * 使用外部参数创建MyStudent对象
     * */
    private static MyStudent getMyStudent(String[] args){
        MyStudent myStudent = new MyStudent();
        myStudent.setGrade(args[0]);
        myStudent.setCollege(args[1]);
        myStudent.setMajor(args[2]);
        myStudent.setClassName(args[3]);
        myStudent.setName(args[4]);
        //下面的分数要做类型转换
        String objectOrientedProgramming = args[5];
        String networkPrinciples = args[6];
        String database = args[7];
        try {
            myStudent.setObjectOrientedProgramming(Float.valueOf(objectOrientedProgramming));
            myStudent.setNetworkPrinciples(Float.valueOf(networkPrinciples));
            myStudent.setDatabase(Float.valueOf(database));
        }catch (NumberFormatException e){
            System.out.println("课程分数输入异常！");
            return null;
        }
        return myStudent;
    }


    /**
     * 年级、
     */
    private String Grade;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private String className;

    /**
     * 姓名
     */
    private String name;

    /**
     * 面向对象程序设计成绩
     */
    private float objectOrientedProgramming;

    /**
     * 网络原理成绩
     */
    private float networkPrinciples;

    /**
     * 数据库成绩
     */
    private float database;

    /**
     * 总成绩
     */
    private float totalScore;

    /**
     * 构造函数
     */
    public MyStudent() {

    }

    public MyStudent(String grade, String college, String major, String className, String name, float objectOrientedProgramming, float networkPrinciples, float database) {
        Grade = grade;
        this.college = college;
        this.major = major;
        this.className = className;
        this.name = name;
        this.objectOrientedProgramming = objectOrientedProgramming;
        this.networkPrinciples = networkPrinciples;
        this.database = database;
    }

    /**
     * 计算总成绩
     */
    public float calculateTotalScore() {
        totalScore = objectOrientedProgramming + networkPrinciples + database;
        return totalScore;
    }

    /**
     * 输出打印toString
     */
    @Override
    public String toString() {
        return this.college + "\t" + this.major + "\t" + Grade + "-" + this.className + name + "\t总成绩:" + calculateTotalScore();
    }

    /************************get/set方法***************************/
    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getObjectOrientedProgramming() {
        return objectOrientedProgramming;
    }

    public void setObjectOrientedProgramming(float objectOrientedProgramming) {
        this.objectOrientedProgramming = objectOrientedProgramming;
    }

    public float getNetworkPrinciples() {
        return networkPrinciples;
    }

    public void setNetworkPrinciples(float networkPrinciples) {
        this.networkPrinciples = networkPrinciples;
    }

    public float getDatabase() {
        return database;
    }

    public void setDatabase(float database) {
        this.database = database;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }
}
