/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day200601;


import java.time.LocalDate;

/**
 * @author admin 卡片信息
 */
public abstract class Card implements StringForFile, Discount {
    private String id;//卡片id
    private String name;//用户名称
    private Address addr;//客户地址
    private int points;//积分
    private LocalDate date;//最近使用时间

    //折扣百分比
    private static double discountPercentEconomy = 0.01;
    private static double discountPercentBuss = 0.05;
    private static double discountPercentFirst = 0.05;

    public Card(String a, String b, Address c) {
        this(a, b, c, 0);
    }


    public Card(String a, String b, Address c, int d) {
        this(a, b, c, d, LocalDate.now());
    }

    public Card(String a, String b, Address c, int d, LocalDate dd) {
        this.id = a;
        this.name = b;
        this.addr = c;
        this.points = d;
        this.date = dd;
    }

    public static double getDiscountPercentEconomy() {
        return discountPercentEconomy;
    }

    public static double getDiscountPercentBuss() {
        return discountPercentBuss;
    }

    public static double getDiscountPercentFirst() {
        return discountPercentFirst;
    }


    protected void setDiscountEconomy(double discount) {
        this.discountPercentEconomy = discount;
    }

    protected void setDiscountBusiness(double discount) {
        this.discountPercentBuss = discount;
    }

    protected void setDiscountFirst(double discount) {
        this.discountPercentFirst = discount;
    }

    public String getName() {
        return name;
    }

    public Address getAddr() {
        return addr;
    }


    public String getDiscountID() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    @Override
    public String toString() {
        return id + "," + name
                + "," + addr + "," + points + "," + date.toString();
    }

    public String getDataToSaveToTextFile() {
        return id + "," + name + "," + addr.getDataToSaveToTextFile() + "," + points + "," + date.toString();
    }

    //计算优惠值
    public abstract Coupon calYearlyCoupon();

    public abstract double getDiscountPercentage(TicketType ticketclass);

    public abstract double getDiscountAmount(double amount, TicketType ticketclass);

    public String getId() {
        return id;
    }
}
