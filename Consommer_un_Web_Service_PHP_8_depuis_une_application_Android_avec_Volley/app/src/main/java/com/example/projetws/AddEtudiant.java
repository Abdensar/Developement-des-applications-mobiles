package com.example.projetws;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.projetws.beans.Etudiant;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Activité pour ajouter un étudiant via un Web Service PHP.
 * Utilise Volley pour les requêtes HTTP et Gson pour la désérialisation.
 */
public class AddEtudiant extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddEtudiant";
    // URL du Web Service PHP pour la création d'étudiant
    private static final String URL = "http://10.0.2.2/projet/ws/createEtudiant.php";

    // Déclaration des vues
    private EditText nom;
    private EditText prenom;
    private Spinner ville;
    private RadioButton m;
    private RadioButton f;
    private Button add;
    
    // File d'attente pour les requêtes Volley
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        // Initialisation de la file d'attente Volley
        requestQueue = Volley.newRequestQueue(this);

        // Liaison des composants avec le layout
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        ville = findViewById(R.id.ville);
        m = findViewById(R.id.m);
        f = findViewById(R.id.f);
        add = findViewById(R.id.add);

        // Configuration du bouton d'ajout
        add.setOnClickListener(this);
    }

    /**
     * Méthode appelée lors du clic sur le bouton.
     */
    @Override
    public void onClick(View v) {
        if (v == add) {
            envoyerEtudiant();
        }
    }

    /**
     * Méthode pour envoyer les données de l'étudiant au serveur.
     * Crée une requête HTTP POST avec Volley.
     */
    private void envoyerEtudiant() {
        // Validation basique
        if (nom.getText().toString().isEmpty() || prenom.getText().toString().isEmpty()) {
            Toast.makeText(this, "Veuillez remplir les champs obligatoires", Toast.LENGTH_SHORT).show();
            return;
        }

        // Création de la requête POST
        StringRequest request = new StringRequest(Request.Method.POST, URL,
                // Listener de succès
                response -> {
                    Log.d(TAG, "Réponse brute : " + response);
                    
                    try {
                        // Utilisation de Gson pour parser la réponse JSON en Collection d'Etudiant
                        Gson gson = new Gson();
                        Type type = new TypeToken<Collection<Etudiant>>() {}.getType();
                        Collection<Etudiant> etudiants = gson.fromJson(response, type);
                        
                        // Si la réponse JSON est un tableau d'étudiants (le Web Service peut renvoyer la liste mise à jour)
                        if (etudiants != null) {
                            Log.d(TAG, "Désérialisation réussie avec Gson.");
                            for (Etudiant etudiant : etudiants) {
                                Log.d(TAG, etudiant.toString());
                            }
                            Toast.makeText(AddEtudiant.this, "Étudiant ajouté avec succès !", Toast.LENGTH_SHORT).show();
                            
                            // Réinitialiser les champs
                            nom.setText("");
                            prenom.setText("");
                            ville.setSelection(0);
                            m.setChecked(true);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Erreur lors du parsing JSON avec Gson : " + e.getMessage());
                        // Si la réponse n'est pas un tableau d'étudiants (par ex: {"success": true})
                        Toast.makeText(AddEtudiant.this, "Requête envoyée. (Erreur parsing JSON)", Toast.LENGTH_SHORT).show();
                    }
                },
                // Listener d'erreur
                error -> {
                    Log.e(TAG, "Erreur de requête Volley : " + error.getMessage());
                    Toast.makeText(AddEtudiant.this, "Erreur de connexion au serveur", Toast.LENGTH_SHORT).show();
                }
        ) {
            /**
             * Cette méthode permet d'ajouter les paramètres de la requête POST.
             * Ces paramètres seront récupérés par le PHP via $_POST['nom'], etc.
             */
            @Override
            protected Map<String, String> getParams() {
                // Création du dictionnaire de paramètres
                Map<String, String> params = new HashMap<>();
                
                // Récupération des valeurs saisies
                params.put("nom", nom.getText().toString());
                params.put("prenom", prenom.getText().toString());
                params.put("ville", ville.getSelectedItem().toString());
                params.put("sexe", m.isChecked() ? "homme" : "femme");
                
                return params;
            }
        };

        // Ajout de la requête à la file d'attente pour exécution
        requestQueue.add(request);
    }
}
