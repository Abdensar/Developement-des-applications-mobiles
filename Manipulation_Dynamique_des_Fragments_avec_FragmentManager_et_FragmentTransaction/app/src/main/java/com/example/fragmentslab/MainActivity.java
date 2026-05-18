package com.example.fragmentslab;

// Importation des classes nécessaires
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * MainActivity - Activité principale de l'application FragmentsLab.
 * Gère la navigation entre les fragments via deux boutons.
 * Utilise FragmentManager et FragmentTransaction pour le remplacement dynamique.
 */
public class MainActivity extends AppCompatActivity {

    // Déclaration des boutons de navigation
    private Button btn1;  // Bouton pour afficher Fragment 1
    private Button btn2;  // Bouton pour afficher Fragment 2

    /**
     * Méthode appelée à la création de l'activité.
     * Initialise les boutons et affiche le premier fragment par défaut.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Associer le layout XML à cette activité
        setContentView(R.layout.activity_main);

        // Liaison des boutons avec les composants XML via findViewById
        btn1 = findViewById(R.id.btnFragment1);
        btn2 = findViewById(R.id.btnFragment2);

        // Afficher FragmentOne par défaut uniquement au premier lancement
        // (pas lors d'une rotation d'écran)
        if (savedInstanceState == null) {
            replaceFragment(new FragmentOne(), false);
        }

        // Écouteur de clic sur le bouton Fragment 1
        btn1.setOnClickListener(v -> replaceFragment(new FragmentOne(), true));

        // Écouteur de clic sur le bouton Fragment 2
        btn2.setOnClickListener(v -> replaceFragment(new FragmentTwo(), true));
    }

    /**
     * Méthode privée pour remplacer le fragment affiché dans le conteneur.
     *
     * @param fragment      Le fragment à afficher
     * @param addToBackStack Si vrai, ajoute la transaction à la pile de retour
     *                       pour permettre la navigation avec le bouton retour
     */
    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        // Obtenir le FragmentManager pour gérer les fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Démarrer une transaction de fragment avec réordonnancement autorisé
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, fragment);

        // Ajouter à la pile de retour si demandé
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        // Valider la transaction
        transaction.commit();
    }
}
