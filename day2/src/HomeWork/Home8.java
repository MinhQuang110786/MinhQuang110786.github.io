package HomeWork;
import java.util.Scanner;
public class Home8 {
    public static void main(String args[]){
        int[] set1 = new int[16];
        int[] set2 = {2,3,6,7,10,11,14,15,18,19,22,23,26,27,30,31};
        int[] set3 = {4,5,6,7,12,13,14,15,20,21,22,23,28,29,30,31};
        int[] set4 = {8,9,10,11,12,13,14,15,24,25,26,27,28,29,30,31};
        int[] set5 = {16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,21};
        for(int i=0;i<set1.length;i++){
            set1[i] = 2*i+1;
        }
        int[][] sets = {set1,set2,set3,set4,set5};
        int birthDay = 0;
        for(int i=0;i<sets.length;i++){
            birthDay+=question(sets[i],i+1);
        }
        System.out.println("Your birthday is: " + birthDay);
    }
    public static int question(int[] set,int index){
        String ans;
        Scanner input = new Scanner(System.in);

        System.out.print("Yor birthday in set " + index + ">>");
        ans = input.nextLine();
        if(Character.toUpperCase(ans.charAt(0))=='Y')
            return set[0];
        else
            return 0;
    }

}
