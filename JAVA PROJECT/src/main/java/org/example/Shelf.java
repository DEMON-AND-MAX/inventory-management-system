package org.example;
import java.util.*;
import java.util.stream.*;

public class Shelf implements Comparable<Shelf> {
    private final ArrayList<Product> productList = new ArrayList<>();

    // FUNCTIONS
    // ADD, DELETE

    public void addProduct(Product newProduct) {
        productList.add(newProduct);
    }

    public boolean delProduct(Product delProduct) {
        if(productList.contains(delProduct)) {
            productList.remove(delProduct);
            return true;
        }
        return false;
    }

    // SEARCH, SORT, FILTER

    public Product searchByID(int srcProductID) {
        ArrayList<Product> srcProductListID = productList.stream().filter(x -> x.id == srcProductID).collect(Collectors.toCollection(ArrayList::new));
        if(srcProductListID.isEmpty()) {
            return null;
        }
        return srcProductListID.get(0);
    }

/*    public Product searchByName(String srcProductName) {
        ArrayList<Product> srcProductList = productList.stream().filter(x -> Objects.equals(x.name, srcProductName)).collect(Collectors.toCollection(ArrayList::new));
        if(srcProductList.isEmpty()) {
            return null;
        }
        return srcProductList.get(0);
    } */

    public ArrayList<Product> sortByPrice() {
        return productList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Product> filterByPrice(float price) {
        return productList.stream().filter(x -> x.price <= price).collect(Collectors.toCollection(ArrayList::new));
    }

    // GET, SET, CONSTRUCTOR, ETC

    public Shelf() {}
    public int compareTo(Shelf shelf) {
        if(this.productList.size() > shelf.productList.size()) return 1;
        else if(this.productList.size() < shelf.productList.size()) return -1;
        return 0;
    }
    public ArrayList<Product> getProductList() { return productList; }

}
