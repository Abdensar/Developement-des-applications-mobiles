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
 * TempFragment - Fragment de conversion de température.
 * Permet de convertir entre Celsius et Fahrenheit.
 * Formules utilisées :
 *   C → F : (valeur × 1.8) + 32
 *   F → C : (valeur - 32) / 1.8
 */
public class TempFragment extends Fragment {

    // Déclaration des composants de l'interface
    private RadioGroup rgTemp;          // Groupe de boutons radio
    private RadioButton rbCtoF;         // Option Celsius vers Fahrenheit
    private RadioButton rbFtoC;         // Option Fahrenheit vers Celsius
    private EditText etTempInput;       // Champ de saisie de la valeur
    private Button btnConvertTemp;      // Bouton de conversion
    private TextView tvTempResult;      // Affichage du résultat

    /**
     * Méthode appelée pour créer la vue du fragment.
     * Gonfle le layout fragment_temp.xml et initialise les composants.
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
        View view = inflater.inflate(R.layout.fragment_temp, container, false);

        // Liaison des composants XML avec les variables Java via findViewById
        rgTemp = view.findViewById(R.id.rgTemp);
        rbCtoF = view.findViewById(R.id.rbCtoF);
        rbFtoC = view.findViewById(R.id.rbFtoC);
        etTempInput = view.findViewById(R.id.etTempInput);
        btnConvertTemp = view.findViewById(R.id.btnConvertTemp);
        tvTempResult = view.findViewById(R.id.tvTempResult);

        // Configuration de l'écouteur de clic sur le bouton de conversion
        btnConvertTemp.setOnClickListener(v -> {
            // Récupérer la valeur saisie par l'utilisateur
            String input = etTempInput.getText().toString();

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
            if (rbCtoF.isChecked()) {
                // Conversion Celsius vers Fahrenheit : (C × 1.8) + 32
                result = (val * 1.8) + 32;
            } else {
                // Conversion Fahrenheit vers Celsius : (F - 32) / 1.8
                result = (val - 32) / 1.8;
            }

            // Formater le résultat avec 2 décimales et l'afficher
            String formattedResult = String.format("%.2f", result);
            tvTempResult.setText("Résultat : " + formattedResult);
        });

        // Retourner la vue construite
        return view;
    }
}
