package vn.techmaster.topcar.model;

import com.poiji.annotation.ExcelCellName;

public class Car {
  @ExcelCellName("model")
  public String model;

  @ExcelCellName("manufacter")
  public String manufacter;

  @ExcelCellName("price")
  public int price;

  @ExcelCellName("sales")
  public int sales;

  @ExcelCellName("photo")
  public String photo;

  @Override
  public String toString() {
    return "Car [manufacter=" + manufacter + ", model=" + model + ", photo=" + photo + ", price=" + price + ", sales="
        + sales + "]";
  }

}
