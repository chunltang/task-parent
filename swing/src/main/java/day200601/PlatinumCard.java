/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day200601;

import java.time.LocalDate;


/**
 *
 * @author admin  白金卡
 */
public class PlatinumCard extends Card{

   protected static int count=0;
    public PlatinumCard( String id, String name, 
            Address addr, int points , LocalDate date) {
        super(id, name, addr, points, date);
    }
    
    public PlatinumCard( String id, String name, 
            Address addr) {
        this(id, name, addr,0,LocalDate.now());
    }



    public double getDiscountPercentage(TicketType ticketclass) {
        if (ticketclass== TicketType.EconomyClass )
        {
        return this.getDiscountPercentEconomy();  
        }
        else if (ticketclass== TicketType.BusinessClass )
        {
        return this.getDiscountPercentBuss();  
        }
        return this.getDiscountPercentFirst();
    }
    @Override
    public double getDiscountAmount(double amount, TicketType ticketclass) {
        if (ticketclass== TicketType.EconomyClass )
        {
          return this.getDiscountPercentEconomy()*amount; 
        }  
        if (ticketclass== TicketType.BusinessClass)
        {
          return this.getDiscountPercentBuss()*amount; 
        }
        return this.getDiscountPercentFirst()*amount;
    }

   
    @Override
    public Coupon calYearlyCoupon() {//根据积分计算优惠值
      String cid = super.getDiscountID()+"00"+count;   
      if (this.getPoints()<10000)
       return new Coupon(cid,0.005*this.getPoints());
      
      return new Coupon(cid,0.01*this.getPoints());      
    }
    
   public String getDataToSaveToTextFile() {
      return    super.getDataToSaveToTextFile()+ ",PlatinumCard";
   } 

     public static PlatinumCard getInstanceFromStringArray(String[] input)
     {
       if (input[10].trim()!="PlatinumCard") 
       {
           throw new IllegalArgumentException("String array doesn't match the type");
       }
         
       Address a = new Address(input[2],input[3],input[4],input[5],input[6],Integer.valueOf(input[7]));    
         
       return new PlatinumCard(  input[0],  input[1], 
            a, Integer.valueOf(input[8]), LocalDate.parse(input[9].trim()));  
     }
    
  
}
