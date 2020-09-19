package homeWork8;
import java.util.ArrayList;
public class Sets {
    private int count = 0;
    private ArrayList<Integer> set = new ArrayList<>();
    public Sets(int size){
       int number = (int)(Math.pow(2,size-1));

       for(int i=number;i<=31;){
           if(count<number){
               set.add(i);
               count++;
               i++;
           }else {
               count=0;
               i+=number;
           }

       }
    }
    public ArrayList<Integer> getSet(){
        return set;
    }
    public void displaySet(){
        for(int i=0;i<set.size();i++){
            System.out.printf("%4d",set.get(i));
            if(i%4==3)
                System.out.println();
        }
    }

}
