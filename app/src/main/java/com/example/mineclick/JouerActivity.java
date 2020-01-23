package com.example.mineclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class JouerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jouer);
    }

    public void onClickBoutique(View view) {
        Intent intent = new Intent(this, BoutiqueActivity.class);
        startActivity(intent);
    }

    public void onClickRetour(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
