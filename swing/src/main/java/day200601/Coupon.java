/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day200601;


/**
 *
 * @author Pairat Thorncharoensri 优惠券
 */
public class Coupon implements StringForFile, Discount {
        private String id;
        private double values;

    public Coupon(String id, double values) {
        this.id = id;
        this.values = values;
    }

    public String getDiscountID() {
        return id;

    }

    public double getDiscountAmount()
    {
      return values;
    }
    
    public double getDiscountAmount(double amount, TicketType ticketclass) {
    return values;
    }

    @Override
    public String getDataToSaveToTextFile() {
     return id +"," +values;         
    }
    
      public static Coupon getInstanceFromStringArray(String[] input)
     {
        if (!(input.length==2))         
          throw new IllegalArgumentException("Coupon Input: No correct size");
        
        return new Coupon( input[0], Double.parseDouble(input[1]));  
     }
}
