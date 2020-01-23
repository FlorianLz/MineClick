package com.example.mineclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BoutiqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boutique);
    }

    public void onClickRetour(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
