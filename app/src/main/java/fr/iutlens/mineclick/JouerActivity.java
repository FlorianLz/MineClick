package fr.iutlens.mineclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class JouerActivity extends AppCompatActivity implements TimerAction, TimerAction3 {

    private int cpt = 0;
    private int money = 0;
    private int multiplicateur=1;
    private RefreshHandler handler;
    private RefreshHandler3 handler3;
    private int niveauActuel = 1;
    private int page = 2;
    private boolean utiliserpotion1=false;
    private boolean utiliserpotion2=false;
    private boolean utiliserpotion3=false;
    private int tempspotion1 = 0;
    private int tempspotion3 = 0;
    private int tempsaffichage = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jouer);
        handler = new RefreshHandler(this);
        handler3 = new RefreshHandler3(this);

        Context context = JouerActivity.this;
        SharedPreferences sharedPref = JouerActivity.this.getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        int defaultValue = getResources().getInteger(R.integer.default_money);
        cpt = sharedPref.getInt(getString(R.string.saved_clics), defaultValue);
        money = sharedPref.getInt(getString(R.string.saved_money), defaultValue);
        niveauActuel = sharedPref.getInt(getString(R.string.saved_level), 1);
        utiliserpotion1 = sharedPref.getBoolean(getString(R.string.utiliserpotion1), false);
        utiliserpotion2 = sharedPref.getBoolean(getString(R.string.utiliserpotion2), false);
        utiliserpotion3 = sharedPref.getBoolean(getString(R.string.utiliserpotion3), false);
        tempspotion1 = sharedPref.getInt(getString(R.string.saved_tempspotion1), defaultValue);
        tempspotion3 = sharedPref.getInt(getString(R.string.saved_tempspotion3), defaultValue);
        ((TextView) findViewById(R.id.nbargent)).setText("" +money);
        ((TextView) findViewById(R.id.level)).setText("lv :" + niveauActuel);
        //((TextView) findViewById(R.id.cpt)).setText("cpt :" + cpt);
        int reste = (5000 * niveauActuel) - cpt;
        ((TextView) findViewById(R.id.cpt)).setText("Vie : " + reste);
        update();
        save();


        //Log.d("potion", "potion1"+utiliserpotion1+" "+tempspotion1);
        if(utiliserpotion1){
            if(tempspotion1 <= 0){
                tempspotion1 = 20;
                multiplicateur=4;
                findViewById(R.id.force).setVisibility(View.VISIBLE);
                findViewById(R.id.tempspotion1).setVisibility(View.VISIBLE);
                updateTimer();
                save();
            }else{
                updateTimer();
                save();
            }
        }

        if(utiliserpotion2){
            Random r = new Random();
            int nbrandom = r.nextInt(600 - 300) + 300;
            ((TextView) findViewById(R.id.nbdegats)).setText("" + nbrandom);
            findViewById(R.id.degatseffectues).setVisibility(View.VISIBLE);
            findViewById(R.id.nbdegats).setVisibility(View.VISIBLE);
            cpt += nbrandom;
            utiliserpotion2=false;
            save();
            update();
            money();
            //((TextView) findViewById(R.id.level)).setText("lv :" + niveauActuel);
            //((TextView) findViewById(R.id.cpt)).setText("cpt :" + cpt);
            tempsaffichage=5;
            afficherDegats();
        }

        if(utiliserpotion3){
            if(tempspotion3 <= 0){
                tempspotion3 = 1500;
                findViewById(R.id.autoclic).setVisibility(View.VISIBLE);
                findViewById(R.id.tempspotion3).setVisibility(View.VISIBLE);
                updateTimer3();
                save();
            }else{
                updateTimer3();
                save();
            }
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
        this.finish();
    }

    public void onClickRetour(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onClickMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        this.finish();
    }


    public void onClickMob(View view) {
        cpt+= multiplicateur;
        update();
        money();
        ((TextView) findViewById(R.id.level)).setText("lv :" + niveauActuel);
        //((TextView) findViewById(R.id.cpt)).setText("cpt :" + cpt);
        int reste = (5000 * niveauActuel) - cpt;
        ((TextView) findViewById(R.id.cpt)).setText("Vie : " + reste);
        save();
    }

    private void update() {
        int clics = cpt;
        int niveau =niveauActuel;
        int niveauMax = 5;
        if(niveauActuel <= niveauMax) {
            while (clics > niveau * 5000) {
                clics -= 5000 * niveau;
                ++niveau;
                niveauActuel = niveau;

            }

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
            //reset();
            //save();
            ((TextView) findViewById(R.id.level)).setText("lv :" + niveauActuel);
            ((TextView) findViewById(R.id.cpt)).setText("cpt :" + cpt);
            //((TextView) findViewById(R.id.cpt)).setText("Vous venez de tuer l'enderdragon. Félicitations !");
        }
    }

    private void money(){
        if (cpt%10 == 0){
            money=money+1;
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
        editor.putInt(getString(R.string.saved_tempspotion1), tempspotion1);
        editor.putInt(getString(R.string.saved_tempspotion3), tempspotion3);
        editor.putBoolean(getString(R.string.utiliserpotion1), utiliserpotion1);
        editor.putBoolean(getString(R.string.utiliserpotion2), utiliserpotion2);
        editor.putBoolean(getString(R.string.utiliserpotion3), utiliserpotion3);
        editor.commit();
    }

    public void updateTimer() {
        multiplicateur=4;
        findViewById(R.id.force).setVisibility(View.VISIBLE);
        findViewById(R.id.tempspotion1).setVisibility(View.VISIBLE);
        tempspotion1--;
        ((TextView) findViewById(R.id.tempspotion1)).setText("" + tempspotion1);
        save();
        if (tempspotion1 > 0){
            handler.scheduleRefresh(1000);
        }else{
            multiplicateur=1;
            findViewById(R.id.force).setVisibility(View.INVISIBLE);
            findViewById(R.id.tempspotion1).setVisibility(View.INVISIBLE);
            utiliserpotion1=false;
            save();
        }
    }

    public void updateTimer3() {
        findViewById(R.id.autoclic).setVisibility(View.VISIBLE);
        findViewById(R.id.tempspotion3).setVisibility(View.VISIBLE);
        tempspotion3--;
        cpt++;
        update();
        int reste = (5000 * niveauActuel) - cpt;
        ((TextView) findViewById(R.id.cpt)).setText("Vie : " + reste);
        ((TextView) findViewById(R.id.tempspotion3)).setText("" + tempspotion3/50);
        save();
        if (tempspotion3 > 0){
            handler3.scheduleRefresh(20);
        }else{
            findViewById(R.id.autoclic).setVisibility(View.INVISIBLE);
            findViewById(R.id.tempspotion3).setVisibility(View.INVISIBLE);
            utiliserpotion3=false;
            save();
        }
    }

    public void afficherDegats(){
        tempsaffichage--;
        if (tempsaffichage > 0){
            handler.scheduleRefresh(1000);
        }else{
            tempsaffichage=0;
            findViewById(R.id.nbdegats).setVisibility(View.INVISIBLE);
            findViewById(R.id.degatseffectues).setVisibility(View.INVISIBLE);
            utiliserpotion2=false;
            save();
        }
    }

}
