package com.example.fragmentslab;

// Importation des classes nécessaires
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * FragmentTwo - Deuxième fragment de l'application.
 * Affiche un SeekBar interactif avec la valeur actuelle.
 * Gère la persistance de l'état lors de la rotation de l'écran.
 */
public class FragmentTwo extends Fragment {

    // Déclaration des composants de l'interface
    private TextView tvValue;   // Affichage de la valeur du SeekBar
    private SeekBar seek;       // Barre de progression interactive

    // Variable pour stocker la progression actuelle
    private int progress = 0;

    // Clé pour sauvegarder/restaurer l'état lors de la rotation
    private static final String KEY_PROGRESS = "progress";

    /**
     * Constructeur : associe le layout fragment_two.xml à ce fragment.
     */
    public FragmentTwo() {
        super(R.layout.fragment_two);
    }

    /**
     * Méthode appelée après la création de la vue du fragment.
     * Initialise les composants, restaure l'état si nécessaire,
     * et configure l'écouteur du SeekBar.
     *
     * @param view               La vue racine du fragment
     * @param savedInstanceState L'état sauvegardé (contient la progression si rotation)
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Liaison des composants XML avec les variables Java
        tvValue = view.findViewById(R.id.tvValue);
        seek = view.findViewById(R.id.seekBar);

        // Restauration de l'état sauvegardé lors d'une rotation d'écran
        if (savedInstanceState != null) {
            progress = savedInstanceState.getInt(KEY_PROGRESS, 0);
            seek.setProgress(progress);
            tvValue.setText("Valeur : " + progress);
        }

        // Configuration de l'écouteur de changement du SeekBar
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * Appelée lorsque la progression du SeekBar change.
             *
             * @param seekBar  Le SeekBar modifié
             * @param i        La nouvelle valeur de progression
             * @param b        Vrai si le changement vient de l'utilisateur
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Mettre à jour la variable de progression
                progress = i;
                // Mettre à jour l'affichage du texte avec la nouvelle valeur
                tvValue.setText("Valeur : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Non utilisé - appelée quand l'utilisateur commence à toucher le SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Non utilisé - appelée quand l'utilisateur arrête de toucher le SeekBar
            }
        });
    }

    /**
     * Sauvegarde de l'état du fragment avant destruction (rotation d'écran).
     * Permet de conserver la valeur du SeekBar lors du changement de configuration.
     *
     * @param outState Le Bundle pour stocker les données à sauvegarder
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Sauvegarder la progression actuelle du SeekBar
        outState.putInt(KEY_PROGRESS, progress);
    }
}
