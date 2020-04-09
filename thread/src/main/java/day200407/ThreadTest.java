package day200407;

import java.util.concurrent.*;

public class ThreadTest {

    //main方法
    public static void main(String[] args) {

        //同步测试
        //new day200407.ThreadTest(true);

        //异步测试
        new ThreadTest(false);
    }

    //共享成员变量并初始化为0
    private  Integer sum = 0;

    //构造函数,sync=true为同步，sync=false为不同步
    public ThreadTest(boolean sync) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1000,//线程池维护线程的最少数量
                1000,//线程池维护线程的最大数量
                60,//线程最大空闲时间
                TimeUnit.SECONDS,//时间单位，秒
                new SynchronousQueue<>(true),//线程池所使用的缓冲队列,因为要测试1000个线程,这里SynchronousQueue，该队列大小为1，true表示使用公平锁
                Executors.defaultThreadFactory(),//指定线程创建策略
                new ThreadPoolExecutor.AbortPolicy());//线程池对拒绝任务的处理策略，AbortPolicy为拒绝任务时报异常
        SumTask sumTask;
        for (int i = 0; i < 1000; i++) {
            //创建任务
            sumTask = new SumTask(){//使用匿名内部类，复写run方法
                //实现父类方法
                @Override
                public void run() {
                    if (sync) {//同步执行
                        synchronized (sum) {//使用synchronized关键字对代码块加锁，防止并发访问
                            //共享变量+1
                            sum++;
                            System.out.println("sum=" + sum);
                        }
                    }else{//异步执行
                        sum++;
                        System.out.println("sum=" + sum);
                    }
                }
            };
            //线程池提交任务
            executor.execute(sumTask);
        }
        //关闭线程池,将在任务都完成后关闭shutdownNow将马上关闭线程池，不管线程池里有没有任务
        executor.shutdown();
        if(sync){
            System.out.println("同步测试结果："+sum);
        }else{
            System.out.println("异步测试结果："+sum);
        }

    }

    //任务内部类
    public class SumTask implements Runnable {

        //实现父类方法,默认实现空
        @Override
        public void run() {
        }
    }
}
