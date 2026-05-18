package com.example.starsgallery.ui;

// Importation des classes nécessaires
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.starsgallery.R;

/**
 * SplashActivity - Écran de démarrage animé de l'application.
 * Affiche le logo avec des animations séquentielles :
 * rotation, mise à l'échelle, translation et fondu.
 * Redirige automatiquement vers ListActivity après 5 secondes.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Méthode appelée à la création de l'activité.
     * Configure les animations et la redirection automatique.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Associer le layout XML à cette activité
        setContentView(R.layout.activity_splash);

        // Récupérer la référence à l'ImageView du logo
        ImageView logo = findViewById(R.id.logo);

        // Animation 1 : Rotation de 360 degrés en 2 secondes
        logo.animate()
                .rotation(360f)
                .setDuration(2000);

        // Animation 2 : Mise à l'échelle à 0.5 en 3 secondes
        logo.animate()
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setDuration(3000);

        // Animation 3 : Translation horizontale de 1000 pixels en 2 secondes
        logo.animate()
                .translationXBy(1000f)
                .setDuration(2000);

        // Animation 4 : Fondu (transparence) en 6 secondes
        logo.animate()
                .alpha(0f)
                .setDuration(6000);

        // Thread pour la redirection automatique après 5 secondes
        new Thread(() -> {
            try {
                // Attendre 5 secondes
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Lancer l'activité ListActivity
            Intent intent = new Intent(SplashActivity.this, ListActivity.class);
            startActivity(intent);
            // Fermer le splash screen
            finish();
        }).start();
    }
}
