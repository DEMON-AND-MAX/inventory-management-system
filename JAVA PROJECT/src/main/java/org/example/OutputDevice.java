package org.example;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class OutputDevice {

    private OutputStream outputStream;

    public OutputDevice(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public <T> void write(T mess) {
        try {
            outputStream.write(mess.toString().getBytes());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void write(Product product) {
        write("ID " + product.getId() + " :: " + product.getName() + " - " + product.getPrice() + "$\n");
        if(product.getClass() != Coupon.class) {
            write(
            product.getGenre() + " " + product.getClass().getSimpleName() + "\n" +
            product.getDirector() + ", "+ product.getPublisher() + "\n");
        } else {
            write(product.getClass().getSimpleName() + "\n");
        }
        newLine();
    }

    public void newLine() {
        write("\n");
    }

    public void writeJsonObjectsToFile(ArrayList<JSONObject> jsonObjects, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (JSONObject jsonObject : jsonObjects) {
                fileWriter.write(jsonObject.toString(4));
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
