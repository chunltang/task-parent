package day200601;

public class MainSystem {

    /**
     * 卡片和用户信息注册
     */
    private CardAndUserRegisterSystem cardSys;

    /**
     * 主页面
     */
    private MainPage main;

    public MainSystem() {
        this.main = new MainPage(this);
        this.cardSys = new CardAndUserRegisterSystem(this);
    }

    protected DiscountType getDiscountType() {
        return null;
    }

    protected double getDiscountAmount(String discountId, double price, TicketType ticketType) {
        return 0;
    }


    public CardAndUserRegisterSystem getCardSys(){
        return cardSys;
    }

    public MainPage getMainPage(){
        return main;
    }
}
