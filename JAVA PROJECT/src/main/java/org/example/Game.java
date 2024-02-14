package org.example;

public class Game extends Product implements Buyable, Rentable {
    private boolean bIsRented = false;
    private boolean bIsSold = false;
    public Game(int id, String name, int price, String director, String publisher, String genre) {
        super(id, name, price, director, publisher, genre);
    }

    public boolean Buy() {
        if(!bIsRented && !bIsSold) {
            bIsSold = true;
            return true;
        }
        return false;
    }
    public boolean Rent() {
        if(!bIsRented && !bIsSold) {
            bIsRented = true;
            return true;
        }
        return false;
    }
    public boolean GetIsRented() {
        return bIsRented;
    }
    public float GetRentPrice() {
        return (float) price / 4;
    }
    public boolean GetIsSold() {
        return bIsSold;
    }
}
