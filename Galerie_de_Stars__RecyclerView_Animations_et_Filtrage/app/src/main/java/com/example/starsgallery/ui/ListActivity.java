package com.example.starsgallery.ui;

// Importation des classes nécessaires
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starsgallery.R;
import com.example.starsgallery.adapter.StarAdapter;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.service.StarService;

import java.util.List;

/**
 * ListActivity - Activité affichant la galerie de stars.
 * Utilise un RecyclerView avec un adaptateur personnalisé.
 * Propose la recherche et le partage via le menu.
 */
public class ListActivity extends AppCompatActivity {

    // Déclaration des composants
    private RecyclerView recyclerView;      // Liste déroulante
    private StarAdapter starAdapter;        // Adaptateur personnalisé
    private StarService starService;        // Service de gestion des stars

    /**
     * Méthode appelée à la création de l'activité.
     * Initialise le RecyclerView et charge les données.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Associer le layout XML à cette activité
        setContentView(R.layout.activity_list);

        // Initialiser le service singleton
        starService = StarService.getInstance();

        // Liaison du RecyclerView avec l'élément XML
        recyclerView = findViewById(R.id.recycle_view);

        // Configurer le layout manager (liste verticale)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Récupérer la liste de toutes les stars
        List<Star> stars = starService.findAll();

        // Initialiser l'adaptateur avec le contexte et la liste des stars
        starAdapter = new StarAdapter(this, stars);

        // Associer l'adaptateur au RecyclerView
        recyclerView.setAdapter(starAdapter);
    }

    /**
     * Création du menu avec recherche et partage.
     *
     * @param menu Le menu à créer
     * @return true pour afficher le menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Gonfler le fichier menu.xml
        getMenuInflater().inflate(R.menu.menu, menu);

        // Récupérer l'élément de recherche
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        // Récupérer la SearchView associée
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Configurer l'écouteur de texte de recherche
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * Appelée lors de la soumission de la recherche.
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true; // Ne rien faire à la soumission
            }

            /**
             * Appelée à chaque changement de texte.
             * Filtre la liste en temps réel.
             *
             * @param newText Le nouveau texte de recherche
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                // Appliquer le filtre sur l'adaptateur
                starAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    /**
     * Gestion des clics sur les éléments du menu.
     *
     * @param item L'élément de menu sélectionné
     * @return true si l'événement a été traité
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Vérifier si c'est le bouton de partage
        if (item.getItemId() == R.id.app_bar_share) {
            // Créer un intent de partage
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Stars");
            // Ouvrir le sélecteur d'applications de partage
            startActivity(Intent.createChooser(shareIntent, "Partager via"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
