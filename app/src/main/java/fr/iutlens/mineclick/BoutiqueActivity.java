package fr.iutlens.mineclick;

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
    private int nbpotion2 = 0;
    private int nbpotion3 = 0;

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
        nbpotion2 = sharedPref.getInt(getString(R.string.saved_potion2), defaultValue);
        nbpotion3 = sharedPref.getInt(getString(R.string.saved_potion3), defaultValue);
        ((TextView) findViewById(R.id.nbargent)).setText("" + money);
        ((TextView) findViewById(R.id.nbpotion1)).setText("x" + nbpotion1);
        ((TextView) findViewById(R.id.nbpotion2)).setText("x" + nbpotion2);
        ((TextView) findViewById(R.id.nbpotion3)).setText("x" + nbpotion3);
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
        ((TextView) findViewById(R.id.description)).setText("Cette potion te permets de cliquer 4 fois plus vite durant 1 minute !");
        findViewById(R.id.imgEmerauld).setVisibility(View.VISIBLE);
        findViewById(R.id.prix).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.prix)).setText("coût : 30");
        if(money >= 30) {
            findViewById(R.id.acheterpotion1).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.acheterpotion1).setVisibility(View.INVISIBLE);
        }
    }

    public void onClickPotion2(View view) {
        ((TextView) findViewById(R.id.description)).setText("Cette potion te permet d'infliger entre 300 et 600 clics d'un coup !");
        findViewById(R.id.imgEmerauld).setVisibility(View.VISIBLE);
        findViewById(R.id.prix).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.prix)).setText("coût : 50");
        if(money >= 50) {
            findViewById(R.id.acheterpotion2).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.acheterpotion2).setVisibility(View.INVISIBLE);
        }
    }

    public void onClickPotion3(View view) {
        ((TextView) findViewById(R.id.description)).setText("Cette potion te permet d'utiliser l'autoclick pendant 30 secondes");
        findViewById(R.id.imgEmerauld).setVisibility(View.VISIBLE);
        findViewById(R.id.prix).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.prix)).setText("coût : 80");
        if(money >= 80) {
            findViewById(R.id.acheterpotion3).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.acheterpotion3).setVisibility(View.INVISIBLE);
        }
    }

    public void achatPotion1(View view){
        money = money-30;
        nbpotion1++;
        savePotions();
        if(money <= 30) {
            findViewById(R.id.acheterpotion1).setVisibility(View.INVISIBLE);
        }
        ((TextView) findViewById(R.id.nbargent)).setText("" + money);
        ((TextView) findViewById(R.id.nbpotion1)).setText("x" + nbpotion1);
    }

    public void achatPotion2(View view){
        money = money-50;
        nbpotion2++;
        savePotions();
        if(money <= 50) {
            findViewById(R.id.acheterpotion2).setVisibility(View.INVISIBLE);
        }
        ((TextView) findViewById(R.id.nbargent)).setText("" + money);
        ((TextView) findViewById(R.id.nbpotion2)).setText("x" + nbpotion2);
    }

    public void achatPotion3(View view){
        money = money-80;
        nbpotion3++;
        savePotions();
        if(money <= 80) {
            findViewById(R.id.acheterpotion3).setVisibility(View.INVISIBLE);
        }
        ((TextView) findViewById(R.id.nbargent)).setText("" + money);
        ((TextView) findViewById(R.id.nbpotion3)).setText("x" + nbpotion3);
    }

    private void savePotions(){
        SharedPreferences sharedPref = BoutiqueActivity.this.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_money), money);
        editor.putInt(getString(R.string.saved_potion1), nbpotion1);
        editor.putInt(getString(R.string.saved_potion2), nbpotion2);
        editor.putInt(getString(R.string.saved_potion3), nbpotion3);
        editor.commit();
    }
}
