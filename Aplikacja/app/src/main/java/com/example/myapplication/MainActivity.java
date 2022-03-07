package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private boolean isTextDefault = true;

    //potencjalnie do usuniecia
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //potencjalnie do usuniecia
        StrictMode.setThreadPolicy(policy);
        //
    }

    public void changeText(View view){
        TextView tv = findViewById(R.id.textView1);
        Snackbar mySnackbar = Snackbar.make(view, "Tekst zmieniony", 1000);

        if(isTextDefault){
            tv.setText("Inny tekst");
        }else{
            tv.setText("Moja Aplikacja");
        }
        this.isTextDefault = !this.isTextDefault;

        mySnackbar.show();
    }

    public void changeTextServer(View view){
        String newText = "Moja Aplikacja";

        newText = requestText();

        TextView tv = findViewById(R.id.textView1);

        tv.setText(newText);
        this.isTextDefault = true;
    }

    public String requestText(){
        String newText = "Moja Aplikacja";
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL("http://192.168.31.47:8080/test/hello");
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader isReader = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(isReader);
            StringBuffer sb = new StringBuffer();
            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }
            newText = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return newText;
    }

}