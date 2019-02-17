package com.example.mcandrii.morsecode.conversion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Encoder {

    private ArrayList<String[]> dictionary;

    public Encoder(String path) {
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
    private BufferedWriter openFileToWrite(String path) throws IOException {
        File file = createFile(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        return writer;
    }

    // method to encrypt file
    public void encode(String text, String path) throws IOException {
        BufferedWriter writer = openFileToWrite(path);
        text = text.toLowerCase();
        int c;
        try {
            for(int i = 0; i < text.length(); i++) {
                c = 0;
                for(int j = 0; j < this.dictionary.size(); j++) {
                    if (this.dictionary.get(j)[0].equals(String.valueOf(text.charAt(i)))) {
                        writer.write(this.dictionary.get(j)[1] + "\n");
                        c = 1;
                        break;
                    }
                }
                if (c == 0) {
                    writer.write("?");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
