package org.example;

import org.json.JSONObject;

import java.util.ArrayList;

public class JSONConverter {

    public static JSONObject convertToJSONObject(Product o) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", o.getId());
        jsonObject.put("name", o.getName());
        jsonObject.put("price", o.getPrice());
        jsonObject.put("director", o.getDirector());
        jsonObject.put("publisher", o.getPublisher());
        jsonObject.put("genre", o.getGenre());
        jsonObject.put("className", o.getClass().getSimpleName());

        switch(o.getClass().getSimpleName()) {
            case "Movie" -> jsonObject.put("rented", ((Movie) o).GetIsRented());
            case "Game" -> jsonObject.put("rented", ((Game) o).GetIsRented());
            case "Coupon" -> jsonObject.put("rented", false);
        }

        return jsonObject;
    }

    public static ArrayList<JSONObject> convertToJSONObjectList(ArrayList<Product> productArrayList) {
        ArrayList<JSONObject> jsonObjectArrayList = new ArrayList<>();
        for(Product product : productArrayList) {
            switch(product.getClass().getSimpleName()) {
                case "Movie":
                    jsonObjectArrayList.add(convertToJSONObject(product));
                    break;
                case "Game":
                    if(!((Game) product).GetIsSold()) {
                        jsonObjectArrayList.add(convertToJSONObject(product));
                    }
                    break;
                case "Coupon":
                    if(!((Coupon) product).GetIsSold()) {
                        jsonObjectArrayList.add(convertToJSONObject(product));
                    }
                    break;
            }
        }
        return jsonObjectArrayList;
    }

    public static Product convertJsonObjectToProduct(JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        int price = jsonObject.getInt("price");
        String director = jsonObject.getString("director");
        String publisher = jsonObject.getString("publisher");
        String genre = jsonObject.getString("genre");
        String className = jsonObject.getString("className");
        boolean isRented = jsonObject.getBoolean("rented");

        Product product = null;

        switch(className) {
            case "Movie":
                product = new Movie(id, name, price, director, publisher, genre);
                if(isRented) ((Movie) product).Rent();
                break;
            case "Game":
                product = new Game(id, name, price, director, publisher, genre);
                if(isRented) ((Game) product).Rent();
                break;
            case "Coupon":
                product = new Coupon(id, name, price);
                break;
        }
        return product;
    }
}
