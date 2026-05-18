package com.example.converttabsjava;

// Importation des classes nécessaires
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * DistanceFragment - Fragment de conversion de distance.
 * Permet de convertir entre Kilomètres et Miles.
 * Formules utilisées :
 *   Km → Miles : valeur × 0.6214
 *   Miles → Km : valeur / 0.6214
 */
public class DistanceFragment extends Fragment {

    // Déclaration des composants de l'interface
    private RadioGroup rgDist;          // Groupe de boutons radio
    private RadioButton rbKmToMiles;    // Option Kilomètres vers Miles
    private RadioButton rbMilesToKm;    // Option Miles vers Kilomètres
    private EditText etDistInput;       // Champ de saisie de la valeur
    private Button btnConvertDist;      // Bouton de conversion
    private TextView tvDistResult;      // Affichage du résultat

    /**
     * Méthode appelée pour créer la vue du fragment.
     * Gonfle le layout fragment_distance.xml et initialise les composants.
     *
     * @param inflater           Le LayoutInflater pour gonfler le layout
     * @param container          Le conteneur parent
     * @param savedInstanceState L'état sauvegardé (null au premier lancement)
     * @return La vue racine du fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Gonfler le layout XML du fragment
        View view = inflater.inflate(R.layout.fragment_distance, container, false);

        // Liaison des composants XML avec les variables Java via findViewById
        rgDist = view.findViewById(R.id.rgDist);
        rbKmToMiles = view.findViewById(R.id.rbKmToMiles);
        rbMilesToKm = view.findViewById(R.id.rbMilesToKm);
        etDistInput = view.findViewById(R.id.etDistInput);
        btnConvertDist = view.findViewById(R.id.btnConvertDist);
        tvDistResult = view.findViewById(R.id.tvDistResult);

        // Configuration de l'écouteur de clic sur le bouton de conversion
        btnConvertDist.setOnClickListener(v -> {
            // Récupérer la valeur saisie par l'utilisateur
            String input = etDistInput.getText().toString();

            // Vérifier si le champ est vide
            if (TextUtils.isEmpty(input)) {
                // Afficher un message d'erreur si aucune valeur n'est saisie
                Toast.makeText(getContext(), R.string.erreur_valeur_vide, Toast.LENGTH_SHORT).show();
                return; // Arrêter l'exécution
            }

            // Convertir la chaîne en nombre décimal
            double val = Double.parseDouble(input);
            double result;

            // Effectuer la conversion selon l'option sélectionnée
            if (rbKmToMiles.isChecked()) {
                // Conversion Kilomètres vers Miles : Km × 0.6214
                result = val * 0.6214;
            } else {
                // Conversion Miles vers Kilomètres : Miles / 0.6214
                result = val / 0.6214;
            }

            // Formater le résultat avec 2 décimales et l'afficher
            String formattedResult = String.format("%.2f", result);
            tvDistResult.setText("Résultat : " + formattedResult);
        });

        // Retourner la vue construite
        return view;
    }
}
