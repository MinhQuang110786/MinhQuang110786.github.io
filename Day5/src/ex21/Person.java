package ex21;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person {
    private String name;
    private GregorianCalendar birthDate;
    public Person(String name, int year, int month, int day){
        this.name = name;
        updateDate(year,month,day);
    }

    public void updateDate(int year, int month, int day){
        birthDate = new GregorianCalendar(year, month, day);
    }


    public GregorianCalendar getBirthDate(){
        return birthDate;
    }
    public String toString(){
        return "Name: " + name + "\n" + "BirthDate: "
                +birthDate.get(Calendar.DATE)+"/" +
                (birthDate.get(Calendar.MONTH)+1) +"/"+
                 birthDate.get(Calendar.YEAR);
    }
}
