package com.example.blocnotastematicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = findViewById(R.id.grid);

        for (int i = 0; i < grid.getChildCount(); i++) {
            grid.getChildAt(i).setOnClickListener(v -> {
                Intent intent = new Intent(this, NotaActivity.class);
                intent.putExtra("id", v.getId());
                startActivity(intent);
            });
        }
    }
}