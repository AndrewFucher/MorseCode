package com.example.mcandrii.morsecode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mcandrii.morsecode.conversion.Encoder;
import com.example.mcandrii.morsecode.conversion.Decoder;

public class MainActivity extends AppCompatActivity {

    String text;
    Encoder encoder;
    Decoder decoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = "Упс, не то написал";

        encoder = new Encoder(getFilesDir().getAbsolutePath() + "/Dictionary.txt");
        decoder = new Decoder(getFilesDir().getAbsolutePath() + "/Dictionary.txt");

        try {
            encoder.encode(text, getFilesDir().getAbsolutePath() + "/Writer.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            decoder.decode(getFilesDir().getAbsolutePath() + "/Writer.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
