package com.example.mineclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int cpt = 0;
    private int money = 0;
    private RefreshHandler handler;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        Context context = MainActivity.this;
        SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        int defaultValue = getResources().getInteger(R.integer.default_money);
        cpt = sharedPref.getInt(getString(R.string.saved_clics), defaultValue);
        money = sharedPref.getInt(getString(R.string.saved_money), defaultValue);
        save();

        if(cpt == 1){
            ((TextView) findViewById(R.id.suivant)).setText("Nouvelle partie");
        }else{
            ((TextView) findViewById(R.id.suivant)).setText("Reprendre la partie");
            ((TextView) findViewById(R.id.money)).setText("" + money);
        }
    }


    public void onClickBoutique(View view) {
        Intent intent = new Intent(this, BoutiqueActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onClickJouer(View view) {
        Intent intent = new Intent(this, JouerActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onClickInventaire(View view) {
        Intent intent = new Intent(this, InventaireActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void save(){
        SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_page), page);
        editor.commit();
    }

    public void pageCredits(View view) {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
        this.finish();
    }
}
