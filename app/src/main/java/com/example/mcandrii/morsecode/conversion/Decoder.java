package com.example.mcandrii.morsecode.conversion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Decoder {

    private ArrayList<String[]> dictionary;

    public Decoder(String path) {
        try {
            this.dictionary = getDictionary(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private ArrayList getDictionary(String path) throws IOException {
        ArrayList<String[]> dict = new ArrayList<>();
        BufferedReader file = openDictionaryFile(path);

        try {
            String c;

            for (; ; ) {

                c = file.readLine();

                if (c == null) {
                    break;
                }

                dict.add(c.split("="));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dict;
    }

    private File createFile(String path) throws IOException {
        File file = new File(path);
        if(!checkFile(file)) {
            createNewFile(file);
        }
        return file;
    }

    private boolean checkFile(File file){
        return file.exists();
    }

    private boolean createNewFile(File file) throws IOException {
        return file.createNewFile();
    }

    // method to open file with Morse code to translate in russian
    private BufferedReader openDictionaryFile(String path) throws IOException {
        File file = createFile(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader;
    }

    // open file to write
    private BufferedReader openFileToRead(String path) throws IOException {
        File file = createFile(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader;
    }

    // method to encrypt file
    public void decode(String path) throws IOException {
        BufferedReader reader = openFileToRead(path);
        StringBuilder text = new StringBuilder();
        int c;
        String currentLine;
        try {
            for(;;) {
                c = 0;
                currentLine = reader.readLine();
                if (currentLine == null) {
                    break;
                }
                for(int j = 0; j < this.dictionary.size(); j++) {
                    if (this.dictionary.get(j)[1].equals(currentLine)) {
                        text.append(this.dictionary.get(j)[0]);
                        System.out.println(this.dictionary.get(j)[0] + "FIND ME");
                        c = 1;
                        break;
                    }
                }
                if (c == 0) {
                    text.append("?");
                }
            }
            System.out.println(text.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            System.out.println(text + "FIND ME");
            System.out.println(text.length() + "FIND ME");

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
