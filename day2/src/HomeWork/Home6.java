package HomeWork;

public class Home6 {
    public static void main(String args[]){
        int[] ranks = new int[13];
        for(int i=0;i<ranks.length;i++)
            ranks[i] = i+1;
        String[] suits = {"Cơ","Dô","Bích","Nhép"};
        System.out.println("Lá bài bạn vừa rút là: " + getCard(ranks,suits));

    }
    public static String getCard(int[] ranks, String[] suits){
        int rank = (int)Math.floor(Math.random()*ranks.length+1);
        int index = (int)Math.floor(Math.random()*suits.length);
        String card ="";
        if(rank>1 && rank<=10){
            card+= rank + " " +suits[index];
        }
        else{
            if(rank ==1)
                card+="Át " + suits[index];
            if(rank == 11)
                card+="J " + suits[index];
            if(rank ==12)
                card+="Q " + suits[index];
            if(rank==13)
                card+="K " + suits[index];
        }
        return card;
    }
}
