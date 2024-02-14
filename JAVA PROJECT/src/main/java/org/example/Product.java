package org.example;

public abstract class Product implements Comparable<Product> {
    protected int id;
    protected String name;
    protected int price;
    protected String director;
    protected String publisher;
    protected String genre;

    public Product(int id, String name, int price, String director, String publisher, String genre) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.director = director;
        this.publisher = publisher;
        this.genre = genre;
    }

    @Override
    public int compareTo(Product product) {
        return Integer.compare(price, product.price);
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getDirector() { return director; }
    public String getPublisher() { return publisher; }
    public String getGenre() { return genre; }
//    public IntegerProperty idProperty() {
//
//    }

}
