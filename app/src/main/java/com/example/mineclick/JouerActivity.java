package com.example.mineclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class JouerActivity extends AppCompatActivity implements TimerAction {

    private int cpt = 0;
    private int money = 0;
    private int multiplicateur=1;
    private RefreshHandler handler;
    private int niveauActuel = 1;
    private int page = 2;
    private boolean utiliserpotion1=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jouer);
        handler = new RefreshHandler(this);

        Context context = JouerActivity.this;
        SharedPreferences sharedPref = JouerActivity.this.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        int defaultValue = getResources().getInteger(R.integer.default_money);
        cpt = sharedPref.getInt(getString(R.string.saved_clics), defaultValue);
        money = sharedPref.getInt(getString(R.string.saved_money), defaultValue);
        niveauActuel = sharedPref.getInt(getString(R.string.saved_level), defaultValue);
        utiliserpotion1 = sharedPref.getBoolean(getString(R.string.utiliserpotion1), false);
        ((TextView) findViewById(R.id.nbargent)).setText("" +money);
        ((TextView) findViewById(R.id.level)).setText("lv :" + niveauActuel);
        ((TextView) findViewById(R.id.cpt)).setText("cpt :" + cpt);
        update();
        save();

        if(utiliserpotion1==true){
            handler = new RefreshHandler(this);
            multiplicateur=4;

        }

    }

    public void reset(){
        cpt=1;
        multiplicateur=1;
        niveauActuel=0;
        money=0;
        update();
    }


    public void onClickBoutique(View view) {
        Intent intent = new Intent(this, BoutiqueActivity.class);
        startActivity(intent);
    }

    public void onClickRetour(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickInventaire(View view) {
        Intent intent = new Intent(this, InventaireActivity.class);
        startActivity(intent);
    }

    public void onClickMob(View view) {
        cpt+= multiplicateur;
        update();
        money();
        ((TextView) findViewById(R.id.level)).setText("lv :" + niveauActuel);
        ((TextView) findViewById(R.id.cpt)).setText("cpt :" + cpt);

        save();
    }

    private void update() {
        int clics = cpt;
        int niveau =niveauActuel;
        int niveauMax = 5;
        if(niveauActuel <= niveauMax) {
            while (clics > niveau * 10) {
                clics -= 10 * niveau;
                ++niveau;
                niveauActuel = niveau;

            }
            //int reste = (50 * niveau) - clics;
            //((TextView) findViewById(R.id.clickReste)).setText("Reste :" + reste);
            if(niveau == 1){
                ((ImageView) findViewById(R.id.monstre)).setImageResource(R.mipmap.slime);
            }
            if(niveau == 2){
                ((ImageView) findViewById(R.id.monstre)).setImageResource(R.mipmap.spider);
            }
            if(niveau == 3){
                ((ImageView) findViewById(R.id.monstre)).setImageResource(R.mipmap.skeleton);
            }
            if(niveau == 4){
                ((ImageView) findViewById(R.id.monstre)).setImageResource(R.mipmap.creeper);
            }
            if(niveau == 5){
                ((ImageView) findViewById(R.id.monstre)).setImageResource(R.mipmap.enderdragon);

            }
        }else {
            ((ImageView) findViewById(R.id.monstre)).setImageResource(R.mipmap.enderdragon);
            reset();
            save();
            ((TextView) findViewById(R.id.level)).setText("lv :" + niveauActuel);
            ((TextView) findViewById(R.id.cpt)).setText("cpt :" + cpt);
            //((TextView) findViewById(R.id.cpt)).setText("Vous venez de tuer l'enderdragon. Félicitations !");
        }
    }

    private void money(){
        if (cpt%5 == 0){
            money=money+10;
            ((TextView) findViewById(R.id.nbargent)).setText("" +money);
        }
    }

    private void save(){
        SharedPreferences sharedPref = JouerActivity.this.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_money), money);
        editor.putInt(getString(R.string.saved_clics), cpt);
        editor.putInt(getString(R.string.saved_level), niveauActuel);
        editor.putInt(getString(R.string.saved_page), page);
        editor.commit();
    }

    @Override
    public void updateTimer() {
        ++cpt;
        update();
        /*if (niveauActuel<niveauMaxAuto) handler.scheduleRefresh(100);
        findViewById(R.id.imageAuto).setVisibility(View.INVISIBLE);*/
    }
}
