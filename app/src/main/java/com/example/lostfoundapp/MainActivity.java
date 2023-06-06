package com.example.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton create_new, show_all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_new = findViewById(R.id.new_advert);
        show_all = findViewById(R.id.show_adverts);

        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newAdvert = new Intent(MainActivity.this, NewItem.class);
                startActivity(newAdvert);
            }
        });

        show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showItems = new Intent(MainActivity.this, ShowItems.class);
                startActivity(showItems);
            }
        });
    }
}