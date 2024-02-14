package org.example;

public class Movie extends Product implements Rentable {
    private boolean bIsRented = false;

    public Movie(int id, String name, int price, String director, String publisher, String genre) {
        super(id, name, price, director, publisher, genre);
    }

    public Movie() {
        super(0, "name", 0, "director", "publisher", "genre");
    }

    public boolean GetIsRented() { return bIsRented; }
    public float GetRentPrice() {
        return (float) price / 6;
    }
    public boolean Rent() {
        if(!bIsRented) {
            bIsRented = true;
            return true;
        }
        return false;
    }
}
