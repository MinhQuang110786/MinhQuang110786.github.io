package interfaceClass;


public final class Line {
    Gate A,B,C,D;
    public static int getFare(int distance){
        int res = 120;
        if(distance<=4)
            return res;
        else{
            int extra = distance-4;
            if(extra%2==0)
                res+=30*(extra/2);
            else
                res+=30*((extra+1)/2);
            return res;
        }
    }

    public Line(Gate a, Gate b, Gate c, Gate d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }
}
