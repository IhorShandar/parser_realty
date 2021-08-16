package com.olx;

import java.io.*;
import java.util.List;

public class FileID {

    public static String lastID() throws IOException {
        String lastId = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/Id_Ads.txt"))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                lastId = currentLine;
            }
        }
        return lastId;
    }

    public static void writeId(List<String> newIds) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/Id_Ads.txt", true))) {
            for (int i = newIds.size() - 1; i >= 0; i--) {
                bufferedWriter.write(newIds.get(i));
                bufferedWriter.newLine();
            }
        }
    }
}
