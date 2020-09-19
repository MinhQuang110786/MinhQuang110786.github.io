package homeWork2;

public class checkYear {
    public static String isYear(int num){
        String res="";
        switch(num){
            case 0:
                res +="Năm " + num + " là năm Tý";
                break;
            case 1:
                res +="Năm " + num + " là năm Sửa";
                break;
            case 2:
                res +="Năm " + num + " là năm Dần";
                break;
            case 3:
                res +="Năm " + num + " là năm Mão";
                break;
            case 4:
                res +="Năm " + num + " là năm Thìn";
                break;
            case 5:
                res +="Năm " + num + " là năm Tỵ";
                break;
            case 6:
                res +="Năm " + num + " là năm Ngọ";
                break;
            case 7:
                res +="Năm " + num + " là năm Mùi";
                break;
            case 8:
                res +="Năm " + num + " là năm Thân";
                break;
            case 9:
                res +="Năm " + num + " là năm Dậu";
                break;
            case 10:
                res +="Năm " + num + " là năm Tuất";
                break;
            case 11:
                res +="Năm " + num + " là năm Hợi";
                break;
        }
        return res;
    }
}
