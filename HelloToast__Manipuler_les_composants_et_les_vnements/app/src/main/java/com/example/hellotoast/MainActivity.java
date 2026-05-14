package com.example.hellotoast;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Déclaration des variables
    private int count = 0;
    private TextView textCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison des composants de l'interface utilisateur
        textCount = findViewById(R.id.text_count);
        Button buttonToast = findViewById(R.id.button_toast);
        Button buttonCount = findViewById(R.id.button_count);

        // Ajout d'un écouteur d'événement pour le bouton "Afficher un message"
        buttonToast.setOnClickListener(v -> {
            // Affichage d'un message Toast court
            Toast.makeText(MainActivity.this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show();
        });

        // Ajout d'un écouteur d'événement pour le bouton "Incrémenter le compteur"
        buttonCount.setOnClickListener(v -> {
            // Incrémentation du compteur
            count++;
            // Mise à jour de la valeur affichée dans le TextView
            textCount.setText(String.valueOf(count));
        });
    }
}
