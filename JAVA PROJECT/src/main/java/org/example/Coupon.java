package org.example;

public class Coupon extends Product implements Buyable {
    boolean bIsSold = false;
    public Coupon(int id, String name, int price) {
        super(id, name, price, "", "", "");
    }
    public boolean Buy() {
        if(!bIsSold) {
            bIsSold = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean GetIsSold() {
        return bIsSold;
    }
}
