package ex16;

public class Stock {
    private String code;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;
    public Stock(String code, String name){
        this.code = code;
        this.name = name;
    }

    public void setPreviousClosingPrice(double previousClosingPrice) {
        this.previousClosingPrice = previousClosingPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getPreviousClosingPrice() {
        return previousClosingPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getChangePercent(){
        return previousClosingPrice/currentPrice*100;
    }
}
