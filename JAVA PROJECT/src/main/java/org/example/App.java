package org.example;
import org.json.JSONObject;

public class App {

    private final InputDevice inputDevice;
    private final OutputDevice outputDevice;

    public App(InputDevice inputDevice, OutputDevice outputDevice) {
        this.inputDevice = inputDevice;
        this.outputDevice = outputDevice;
    }
    private final Shelf shelf = new Shelf();
    public void run(boolean bIsAdmin) {
        shelfLoad(shelf);

        boolean run = true;
        int choice;
        if(bIsAdmin) outputDevice.write("Logged in as admin.\n");
        while(run) {
            outputDevice.write(
                    """
                            0. Save and close
                            1. Display products
                            2. Search for product
                            3. Filter by price
                            4. Buy or rent product
                            """);
            if(bIsAdmin) outputDevice.write(
                    """
                            Admin commands
                            5. Add product
                            6. Delete product
                            """);
            outputDevice.write("::: ");
            choice = inputDevice.inputInt();
            outputDevice.write("\n");
            switch(choice){
                case 1:     // Display products
                    outputDevice.write("STORE || SHELF || MOVIES\n");
                    for(Product product : shelf.sortByPrice().stream().filter(x -> x.getClass().getSimpleName().equals("Movie")).toList()) {
                        outputDevice.write(product);
                    }
                    outputDevice.newLine();

                    outputDevice.write("STORE || SHELF || GAMES\n");
                    for(Product product : shelf.sortByPrice().stream().filter(x -> x.getClass().getSimpleName().equals("Game")).toList()) {
                        outputDevice.write(product);
                    }
                    outputDevice.newLine();

                    outputDevice.write("STORE || SHELF || COUPONS\n");
                    for(Product product : shelf.sortByPrice().stream().filter(x -> x.getClass().getSimpleName().equals("Coupon")).toList()) {
                        outputDevice.write(product);
                    }
                    outputDevice.newLine();
                    break;
                case 2:     // Search for product (by ID)
                    outputDevice.write("ID ::: ");
                    Product srcMovieItem = shelf.searchByID(inputDevice.inputInt());
                    outputDevice.newLine();
                    if(srcMovieItem != null) {
                        outputDevice.write(srcMovieItem); }
                    else outputDevice.write("No item with this ID.");
                    outputDevice.newLine();
                    break;
                case 3:     // Filter by price
                    outputDevice.write("Max price ::: ");
                    int filterPrice = inputDevice.inputInt();
                    outputDevice.newLine();
                    for(Product product : shelf.filterByPrice(filterPrice)) {
                        outputDevice.write(product);
                    }
                    outputDevice.newLine();
                    break;
                case 4:     // Buy or rent product
                    outputDevice.write("ID ::: ");
                    Product buyItem = shelf.searchByID(inputDevice.inputInt());
                    outputDevice.newLine();

                    outputDevice.write(buyItem);
                    switch(buyItem.getClass().getSimpleName()) {
                        case "Movie":
                            Movie buyMovie = (Movie) buyItem;
                            if(buyMovie.Rent()) {
                                outputDevice.write("Rented movie!");
                            } else {
                                outputDevice.write("Movie is already rented.");
                            }
                            outputDevice.newLine();
                            break;
                        case "Game":
                            Game buyGame = (Game) buyItem;
                            outputDevice.write("""
                                                1. Buy
                                                2. Rent
                                                """);
                            int b = inputDevice.inputInt();
                            if(b == 1) {
                                if(buyGame.Buy()) {
                                    outputDevice.write("Purchased game!");
                                } else {
                                    outputDevice.write("Game has already been sold or rented.");
                                }
                            } else if(b == 2) {
                                if(buyGame.Rent()) {
                                    outputDevice.write("Rented game!");
                                } else {
                                    outputDevice.write("Game has already been sold or rented.");
                                }
                            } else {
                                break;
                            }
                            outputDevice.newLine();
                            break;
                        case "Coupon":
                            Coupon buyCoupon = (Coupon) buyItem;
                            if(buyCoupon.Buy()) {
                                outputDevice.write("Purchased coupon!");
                            } else {
                                outputDevice.write("Coupon has already been sold.");
                            }
                            outputDevice.newLine();
                            break;
                    }
                    outputDevice.newLine();
                    break;
                case 5:     // Add product
                    if(!bIsAdmin) break;
                    outputDevice.write(
                            """
                                    1. Movie
                                    2. Game
                                    3. Coupon
                                    """);
                    outputDevice.write("::: ");
                    int type = inputDevice.inputInt();
                    inputDevice.inputString();
                    outputDevice.write("\n");

                    Product newProduct = null;

                    outputDevice.write("ID ::: ");
                    int id = inputDevice.inputInt();
                    inputDevice.inputString();
                    outputDevice.write("\n");
                    outputDevice.write("Name ::: ");
                    String name = inputDevice.inputString();
                    outputDevice.write("\n");
                    outputDevice.write("Price ::: ");
                    int price = inputDevice.inputInt();
                    inputDevice.inputString();
                    outputDevice.write("\n");

                    String director = "";
                    String publisher = "";
                    String genre = "";

                    if(type != 3) {
                        outputDevice.write("Director ::: ");
                        director = inputDevice.inputString();
                        outputDevice.newLine();
                        outputDevice.write("Publisher ::: ");
                        publisher = inputDevice.inputString();
                        outputDevice.newLine();
                        outputDevice.write("Genre ::: ");
                        genre = inputDevice.inputString();
                        outputDevice.newLine();
                    }

                    newProduct = switch (type) {
                        case 1 -> new Movie(id, name, price, director, publisher, genre);
                        case 2 -> new Game(id, name, price, director, publisher, genre);
                        case 3 -> new Coupon(id, name, price);
                        default -> new Movie(id, name, price, director, publisher, genre);
                    };
                    shelf.addProduct(newProduct);
                    break;
                case 6:     // Delete products
                    if(!bIsAdmin) break;
                    if(shelf.delProduct(shelf.searchByID(inputDevice.inputInt()))) outputDevice.write("Product was deleted.");
                    else outputDevice.write("Could not delete product.");
                    outputDevice.newLine();
                    break;
                case 0:     // Save and close
                    outputDevice.write("Saving changes to file...\n");
                    outputDevice.writeJsonObjectsToFile(JSONConverter.convertToJSONObjectList(shelf.getProductList()), "out.json");
                    outputDevice.write("Changes successfully saved, closing App.");
                    run = false;
                    break;
            }
        }
    }

    // LOAD SHELF FROM FILE
    private void shelfLoad(Shelf shelf) {
        for(JSONObject jsonObject : InputDevice.readJSONObjectFromFile("out.json")) {
            shelf.addProduct(JSONConverter.convertJsonObjectToProduct(jsonObject));
        }
    }

}
