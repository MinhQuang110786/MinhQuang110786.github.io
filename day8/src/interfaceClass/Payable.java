package interfaceClass;

import java.util.ArrayList;

public interface Payable {
    int getValue();
    void adjustValue();
    void deduct(int fee);
    void setOrigin(Gate gate);
    ArrayList<Gate> getOrigin();
    boolean isValid();
}
