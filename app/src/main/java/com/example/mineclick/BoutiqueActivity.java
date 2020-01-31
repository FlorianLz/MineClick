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
    private int nbpotion1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boutique);

        Context context = BoutiqueActivity.this;
        SharedPreferences sharedPref = BoutiqueActivity.this.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        int defaultValue = getResources().getInteger(R.integer.default_money);
        money = sharedPref.getInt(getString(R.string.saved_money), defaultValue);
        lastpage = sharedPref.getInt(getString(R.string.saved_page), defaultValue);
        nbpotion1 = sharedPref.getInt(getString(R.string.saved_potion1), defaultValue);
        ((TextView) findViewById(R.id.nbargent)).setText("" + money);
        ((TextView) findViewById(R.id.nbpotion1)).setText("x" + nbpotion1);
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
        findViewById(R.id.imgEmerauld).setVisibility(View.VISIBLE);
        findViewById(R.id.prix).setVisibility(View.VISIBLE);
        if(money >= 30) {
            findViewById(R.id.acheterpotion1).setVisibility(View.VISIBLE);
        }
    }

    public void onAchatPotion1(View view){
        money = money-30;
        nbpotion1++;

        if(money <= 30) {
            findViewById(R.id.acheterpotion1).setVisibility(View.INVISIBLE);
        }
        ((TextView) findViewById(R.id.nbargent)).setText("" + money);
        ((TextView) findViewById(R.id.nbpotion1)).setText("x" + nbpotion1);
        savePotion1();
    }

    private void savePotion1(){
        SharedPreferences sharedPref = BoutiqueActivity.this.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_money), money);
        editor.putInt(getString(R.string.saved_potion1), nbpotion1);
        editor.commit();
    }
}
