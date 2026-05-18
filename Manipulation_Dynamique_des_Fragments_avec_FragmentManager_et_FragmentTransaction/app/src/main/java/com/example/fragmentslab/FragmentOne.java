package com.example.fragmentslab;

// Importation des classes nécessaires
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * FragmentOne - Premier fragment de l'application.
 * Affiche un texte et un bouton qui change le message au clic.
 * Démontre l'interaction utilisateur basique dans un fragment.
 */
public class FragmentOne extends Fragment {

    /**
     * Constructeur : associe le layout fragment_one.xml à ce fragment.
     */
    public FragmentOne() {
        super(R.layout.fragment_one);
    }

    /**
     * Méthode appelée après la création de la vue du fragment.
     * Initialise les composants et configure les écouteurs d'événements.
     *
     * @param view               La vue racine du fragment
     * @param savedInstanceState L'état sauvegardé (null au premier lancement)
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Liaison des composants XML avec les variables Java
        TextView textOne = view.findViewById(R.id.textOne);
        Button btnHello = view.findViewById(R.id.btnHello);

        // Écouteur de clic : changer le texte du TextView au clic sur le bouton
        btnHello.setOnClickListener(v -> textOne.setText(R.string.bonjour_message));
    }
}
