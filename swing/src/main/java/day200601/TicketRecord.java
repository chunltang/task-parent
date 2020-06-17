/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day200601;

import java.time.LocalDate;

/**
 *
 * @author Pairat Thorncharoensri,票记录
 */
public class TicketRecord {
    String Ticketid;//票id
    String discountId;//折扣id
    LocalDate date;//时间

    public TicketRecord(String Ticketid, String discountId, LocalDate date) {
        this.Ticketid = Ticketid;
        this.discountId = discountId;
        this.date = date;
    }

    public String getTicketid() {
        return Ticketid;
    }

    public String getDiscountId() {
        return discountId;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[" + "Ticketid=" + Ticketid + ", discountId=" + discountId + ", date=" + date + ']';
    }
    
    
    
    
}
