/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day200601;

/**
 *
 * @author Pairat Thorncharoensri 折扣
 */
public interface Discount {

    /**
     * 获取折扣值
     * @param amount 机票购买数量
     * @param ticketclass 机票类型
     * */
    double getDiscountAmount(double amount, TicketType ticketclass);
    /**
     * 获取折扣id
     * */
    String getDiscountID();
}
