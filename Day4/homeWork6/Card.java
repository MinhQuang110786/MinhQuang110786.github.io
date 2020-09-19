package homeWork6;

public class Card {
    private final int[] ranks = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    private final String[] suits = {"Heart","Diamond","Club","Spade"};
    private int rank;
    private String suit;
    public Card(){
        rank= ranks[(int)(Math.random()*13)];
        suit = suits[(int)(Math.random()*4)];
    }
    public String displayCard(){
        String str = "";
        if(rank==1)
            str+="Ace "+suit;
        else if(rank==11)
            str+="Jack "+suit;
        else if(rank==12)
            str+="Queen "+suit;
        else if(rank==13)
            str+="King "+suit;
        else
            str+=rank + " " + suit;
        return str;
    }
}
