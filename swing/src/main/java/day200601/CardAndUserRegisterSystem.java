package day200601;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 卡片注册数据存储
 * */
public class CardAndUserRegisterSystem {

    private List<Card> cards = new ArrayList<>();

    private List<Coupon> coupons = new ArrayList<>();

    private MainSystem main;

    private CardRegisterPage cardReg;

    private CardQueryPage query;

    private CardAndUserInformationDisplayPage display;

    //10000000为id的起始值
    private AtomicInteger id=new AtomicInteger(10000000);

    public CardAndUserRegisterSystem(MainSystem main) {
        this.main = main;
        cards.add(new PlatinumCard("1","xiaoming",new Address("jiangsu","changzhou","aaa","bbb","ccc",0)));
        cards.add(new PlatinumCard("2","xiaoming",new Address("jiangsu","changzhou","aaa","bbb","ccc",0)));
        cards.add(new PlatinumCard("3","xiaoda",new Address("jiangsu","changzhou","aaa","bbb","ccc",0)));
    }

    /**
     * 添加注册信息
     * */
    protected void registerCard(Card card) {
        cards.add(card);
    }

    protected DiscountType getDiscountType(String discountId) {
        return null;
    }

    protected void getDiscountAmount(String discountId, double price, TicketType ticketType) {

    }

    protected Discount searchByDiscountID(String discountId) {
        return null;
    }

    public void openCreateCardPae() {

    }

    public void processDataFromCardPage(String[] strs) {

    }

    protected void displayCardInformationToPage(String str) {

    }

    public void displayCardInformationToPage() {

    }

    public void backToPreviousPage() {

    }

    public void backToMainSystemPage() {

    }

    public void searchCardByIDAndDisplayOnPage(String flyerCard) {

    }

    public void searchCardByNameAndDisplayOnPage(String flyerCard) {

    }

    public MainSystem getMainSystem(){
        return main;
    }

    /**
     * 生成递增id
     * */
    public Integer generatorId(){
        return id.incrementAndGet();
    }

    /**
     * 获取所有卡片信息
     * */
    public List<Card> getCards(){
        return cards;
    }

    /**
     * 根据id返回卡片信息
     * */
    public Card getCardsByCardId(String cardId){
        List<Card> cardList = cards.stream().filter(card -> {
            if (card.getId().equals(cardId)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if(cardList!=null&&cardList.size()>0){
            return cardList.get(0);
        }
        return null;
    }
}
