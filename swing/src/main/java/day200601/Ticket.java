/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day200601;



/**
 *
 * @author Pairat Thorncharoensri,飞机票基本信息
 */
public class Ticket {
    private String ticketID;
    private String name;//用户名称
    private String departureCity;//出发城市
    private String destinationCity;//目的地
    private String flightNumber;//航班号
    private TicketType ticketType;//票类型
    private DiscountType discountType;//折扣类型
    private double price;//价格
    private double discountAmount;//折扣数量

    public Ticket(String ticketCode, String name, String departureCity, String destinationCity, String flightNumber, TicketType ticketType,DiscountType discountType, double price, double discountAmount) {
        this.ticketID = ticketCode;
        this.name=name;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.flightNumber = flightNumber;
        this.ticketType = ticketType;
        this.discountType =discountType;
        this.price = price;
        this.discountAmount=discountAmount;
    }

    public String getTicketID() {
        return ticketID;
    }

    public String getName() {
        return name;
    }  

    public double getPrice() {
        return price;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    @Override
    public String toString() {
        return "[" + "ticketID=" + ticketID + ", name=" + name + ", departureCity=" + departureCity + ", destinationCity=" + destinationCity + ", flightNumber=" + flightNumber + ", ticketType=" + ticketType + ", discountType=" + discountType + ", price=" + price + ", discountAmount=" + discountAmount + ']';
    }




}
