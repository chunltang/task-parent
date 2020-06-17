package day200601文件系统.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * 观察者代理
 * */
public class Listener implements Observer{

    /**
     * 观察者集合
     * */
    private Set<Observer> observers=new HashSet<>();

    /**
     * 构造观察者代理对象
     * */
    public Listener(){
    }

    /**
     * 通知所有观察者
     * */
    private void notifyAllObserver(Observerable observerable,String message){
        for (Observer observer : observers) {
            observer.update(observerable,message);
        }
    }

    /**
     * 被观察对象发生改变时，通知所有观察者
     * */
    @Override
    public void update(Observerable observerable,String message) {
        notifyAllObserver(observerable,message);
    }

    /**
     * 新增观察者
     * */
    public void addObserver(Observer observer){
        this.observers.add(observer);
    }

    /**
     * 删除观察者
     * */
    public void removeObserver(Observer observer){
        this.observers.remove(observer);
    }

    /**********************get and set**************************/

    public Set<Observer> getObservers() {
        return observers;
    }

    public void setObservers(Set<Observer> observers) {
        this.observers = observers;
    }
}
