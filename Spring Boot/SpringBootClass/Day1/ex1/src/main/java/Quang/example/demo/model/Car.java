package Quang.example.demo.model;

public class Car {
    static int id=0;
    String name;
    String manufacturer;
    int price;
    int photo;

    public Car(String name, String manufacturer, int price, int photo) {
        id++;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.photo = photo;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Car.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
