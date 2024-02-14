package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class TableData {
    public static ObservableList<Product> tableData = FXCollections.observableArrayList();

    public static void deleteFromDatabase(Movie del) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "2003");
            Statement statement = connection.createStatement();
            String deleteQuery = "DELETE FROM products WHERE id = " + del.id;
            statement.executeUpdate(deleteQuery);
            loadDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "2003");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            tableData.setAll(convertResultSetToData(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addToDatabase(Movie newMovie) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "2003");
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO products (name, price, director, publisher, genre) " +
                    "VALUES ('" + newMovie.name + "', " + newMovie.price + ", '" + newMovie.director + "', '" + newMovie.publisher + "', '" + newMovie.genre + "')";
            statement.executeUpdate(insertQuery);
            loadDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<Product> convertResultSetToData(ResultSet resultSet) throws SQLException {
        ObservableList<Product> convertedData = FXCollections.observableArrayList();
        Movie convertedMovie = new Movie();
        while(resultSet.next()) {
            convertedMovie.name = resultSet.getString("name");
            convertedMovie.id = resultSet.getInt("id");
            convertedMovie.price = resultSet.getInt("price");
            convertedMovie.genre = resultSet.getString("genre");
            convertedMovie.director = resultSet.getString("director");
            convertedMovie.publisher = resultSet.getString("publisher");
            convertedData.add(convertedMovie);
            convertedMovie = new Movie();
        }
        return convertedData;
    }
}
