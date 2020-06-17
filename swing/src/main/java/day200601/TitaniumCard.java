/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day200601;

import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author admin,钛卡
 */
public class TitaniumCard extends PlatinumCard{
   // private int years;

    public TitaniumCard(  String i, String n, Address a) {
        this( i, n, a, 0, LocalDate.now());
    }

    //经济舱的折扣为2%，商务舱或头等舱的折扣为10%
    protected TitaniumCard(  String i, String n, Address a, int b, LocalDate d) {
        super( i, n, a, b,d);
        super.setDiscountEconomy(0.02);
        super.setDiscountFirst(0.1);
        super.setDiscountBusiness(0.1);
    }
    
    private int getYears()
    {
        return Calendar.getInstance().getTime().getYear()-this.getDate().getYear();
    }
         
    @Override
    public Coupon calYearlyCoupon() {
      String cid = super.getDiscountID()+count;   
      if (this.getPoints()<100000)
        return new Coupon(cid,0.015*this.getPoints());
      else if ((this.getPoints()>100000) && (this.getPoints()<300000))
          if(this.getYears()>5)      
           return new Coupon(cid,0.03*this.getPoints()); 
          else
           return new Coupon(cid,0.02*this.getPoints());   
      else 
        return new Coupon(cid,0.03*this.getPoints());      
    }

   public String getDataToSaveToTextFile() {
      return    super.getDataToSaveToTextFile().replace(",PlatinumCard","")+ ",TitaniumCard";
   }
     // 21414,Pairat Thorn  28,keira St., ....., 2500, 120,TitaniumCard 
     public static TitaniumCard getInstanceFromStringArray(String[] input)
     {
       if (!input[10].trim().equalsIgnoreCase("TitaniumCard"))
       {
           throw new IllegalArgumentException("String array doesn't match the type");
       }
         
       Address a = new Address(input[2],input[3],input[4],input[5],input[6],Integer.valueOf(input[7]));    
         
       return new TitaniumCard(  input[0],  input[1], 
            a, Integer.valueOf(input[8]), LocalDate.parse(input[9])  );  
     }
    
}
