package com.example.converttabsjava;

// Importation des classes nécessaires
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * MainActivity - Activité principale de l'application ConverterTabsJava.
 * Gère la navigation par onglets entre les fragments de conversion.
 * Utilise TabLayout + ViewPager2 pour la synchronisation onglet-fragment.
 */
public class MainActivity extends AppCompatActivity {

    // Déclaration des composants de l'interface
    private TabLayout tabLayout;        // Barre d'onglets
    private ViewPager2 viewPager;       // Conteneur de pages
    private ViewPagerAdapter adapter;   // Adaptateur pour les fragments

    /**
     * Méthode appelée à la création de l'activité.
     * Initialise les composants et configure la navigation par onglets.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Associer le layout XML à cette activité
        setContentView(R.layout.activity_main);

        // Liaison des composants avec les éléments XML via findViewById
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Initialisation de l'adaptateur avec cette activité
        adapter = new ViewPagerAdapter(this);

        // Associer l'adaptateur au ViewPager2
        viewPager.setAdapter(adapter);

        // Synchroniser le TabLayout avec le ViewPager2 via TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Définir le texte de chaque onglet selon sa position
            if (position == 0) {
                tab.setText(R.string.tab_temperature);  // Premier onglet : Température
            } else {
                tab.setText(R.string.tab_distance);     // Deuxième onglet : Distance
            }
        }).attach(); // Attacher le médiateur pour activer la synchronisation
    }

    /**
     * Méthode appelée lorsque l'utilisateur appuie sur le bouton retour.
     * Affiche un dialogue de confirmation avant de quitter l'application.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
        // Créer et afficher un dialogue de confirmation
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_titre_quitter)      // Titre : "Quitter"
                .setMessage(R.string.dialog_message_quitter)  // Message de confirmation
                .setPositiveButton(R.string.dialog_oui, (dialog, which) -> {
                    // Si l'utilisateur confirme, fermer l'activité
                    finish();
                })
                .setNegativeButton(R.string.dialog_non, null) // Si annulé, ne rien faire
                .show(); // Afficher le dialogue
    }
}
