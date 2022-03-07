package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private boolean isTextDefault = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}