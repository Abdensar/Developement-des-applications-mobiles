package com.example.calculateurimpot;

// Importation des classes nécessaires
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity - Activité principale du calculateur d'impôts locaux.
 * Permet à l'utilisateur de saisir la surface, le nombre de pièces,
 * et d'indiquer la présence d'une piscine pour calculer l'impôt total.
 */
public class MainActivity extends AppCompatActivity {

    // Déclaration des variables privées pour les composants de l'interface
    private EditText surfaceInput;      // Champ de saisie pour la surface
    private EditText piecesInput;       // Champ de saisie pour le nombre de pièces
    private CheckBox piscineCheckbox;   // Case à cocher pour la piscine
    private TextView resultView;        // Zone d'affichage du résultat

    /**
     * Méthode appelée à la création de l'activité.
     * Initialise les composants et configure les écouteurs d'événements.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Associer le layout XML à cette activité
        setContentView(R.layout.activity_main);

        // Liaison des composants XML avec les variables Java via findViewById
        surfaceInput = findViewById(R.id.input_surface);
        piecesInput = findViewById(R.id.input_pieces);
        piscineCheckbox = findViewById(R.id.checkbox_piscine);
        resultView = findViewById(R.id.result);

        // Récupération du bouton et ajout de l'écouteur de clic
        Button buttonCalcul = findViewById(R.id.button_calcul);
        buttonCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appeler la méthode de calcul lors du clic
                calculer();
            }
        });
    }

    /**
     * Méthode privée pour calculer l'impôt local.
     * Formule :
     *   impotBase  = surface × 2
     *   supplement = (nombre de pièces × 50) + (piscine ? 100 : 0)
     *   total      = impotBase + supplement
     *
     * Gère les erreurs de saisie (champs vides ou valeurs invalides).
     */
    private void calculer() {
        try {
            // Lecture de la valeur de la surface (en double pour les décimales)
            String surfaceTexte = surfaceInput.getText().toString().trim();
            String piecesTexte = piecesInput.getText().toString().trim();

            // Vérification que les champs ne sont pas vides
            if (surfaceTexte.isEmpty() || piecesTexte.isEmpty()) {
                resultView.setText(getString(R.string.erreur_saisie));
                return;
            }

            // Conversion des valeurs saisies
            double surface = Double.parseDouble(surfaceTexte);
            int pieces = Integer.parseInt(piecesTexte);

            // Lecture de l'état de la case à cocher (piscine)
            boolean piscine = piscineCheckbox.isChecked();

            // Calcul de l'impôt de base : surface × 2
            double impotBase = surface * 2;

            // Calcul du supplément : (nombre de pièces × 50) + bonus piscine
            double supplement = (pieces * 50) + (piscine ? 100 : 0);

            // Calcul du total
            double total = impotBase + supplement;

            // Affichage du résultat dans le TextView
            resultView.setText("Impôt total : " + total + " DH");

        } catch (NumberFormatException e) {
            // Gestion de l'erreur si les valeurs saisies ne sont pas des nombres valides
            resultView.setText(getString(R.string.erreur_saisie));
            Toast.makeText(this, "Erreur : valeurs numériques invalides", Toast.LENGTH_SHORT).show();
        }
    }
}
