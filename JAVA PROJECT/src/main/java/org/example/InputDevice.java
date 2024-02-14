package org.example;
import org.json.JSONObject;
import java.io.*;
import java.util.*;

public class InputDevice {

    private final Scanner scanner;
    public InputDevice(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public int inputInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            return 0;
        }
    }

    public String inputString() {
        return scanner.nextLine();
    }

    public static ArrayList<JSONObject> readJSONObjectFromFile(String filePath) {
        ArrayList<JSONObject> jsonObjectList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line.trim());
                if (line.trim().endsWith("}")) {
                    jsonObjectList.add(new JSONObject(content.toString()));
                    content.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObjectList;
    }
}
