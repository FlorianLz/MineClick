package fr.iutlens.mineclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void onClickBoutique(View view) {
        Intent intent = new Intent(this, BoutiqueActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onClickRetour(View view) {
        Intent intent = new Intent(this, JouerActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onClickInventaire(View view) {
        Intent intent = new Intent(this, InventaireActivity.class);
        startActivity(intent);
        this.finish();
    }
    public void onClickMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
