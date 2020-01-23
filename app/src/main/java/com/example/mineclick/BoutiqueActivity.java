package com.example.mineclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BoutiqueActivity extends AppCompatActivity {

    private int money = 0;
    private int lastpage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boutique);

        Context context = BoutiqueActivity.this;
        SharedPreferences sharedPref = BoutiqueActivity.this.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        int defaultValue = getResources().getInteger(R.integer.default_money);
        money = sharedPref.getInt(getString(R.string.saved_money), defaultValue);
        lastpage = sharedPref.getInt(getString(R.string.saved_page), defaultValue);
        ((TextView) findViewById(R.id.nbargent)).setText("" + money);
    }

    public void onClickRetour(View view) {
        if(lastpage == 2){
            Intent intent = new Intent(this, JouerActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    public void onClickPotion1(View view){
        ((TextView) findViewById(R.id.description)).setText("Cette potion te permet d'utiliser l'autoclick pendant 30 secondes");
        findViewById(R.id.acheter).setVisibility(View.VISIBLE);
        findViewById(R.id.imgEmerauld).setVisibility(View.VISIBLE);
        findViewById(R.id.prix).setVisibility(View.VISIBLE);
    }
}
