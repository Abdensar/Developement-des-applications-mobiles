package com.example.starsgallery.adapter;

// Importation des classes nécessaires
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.service.StarService;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * StarAdapter - Adaptateur pour le RecyclerView des stars.
 * Gère l'affichage des éléments, le filtrage et les interactions utilisateur.
 * Implémente Filterable pour la recherche dynamique.
 */
public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    // Liste complète des stars (non filtrée)
    private List<Star> stars;

    // Liste filtrée des stars (affichée dans le RecyclerView)
    private List<Star> starsFilter;

    // Contexte de l'activité
    private Context context;

    // Filtre personnalisé pour la recherche
    private NewFilter mfilter;

    /**
     * Constructeur de l'adaptateur.
     *
     * @param context Le contexte de l'activité
     * @param stars   La liste initiale des stars
     */
    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars); // Copie de la liste pour le filtrage
        this.mfilter = new NewFilter(this);        // Initialisation du filtre
    }

    /**
     * Création du ViewHolder pour chaque élément de la liste.
     * Gonfle le layout star_item.xml et configure le clic.
     */
    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gonfler le layout de l'élément
        View view = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);

        // Configurer l'écouteur de clic sur l'élément
        view.setOnClickListener(v -> {
            // Récupérer la position de l'élément cliqué
            int position = ((RecyclerView) parent).getChildAdapterPosition(v);
            if (position == RecyclerView.NO_POSITION) return;

            // Récupérer la star à cette position
            Star star = starsFilter.get(position);

            // Gonfler le layout de la popup d'édition
            View popupView = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null);

            // Liaison des composants de la popup
            ImageView popupImg = popupView.findViewById(R.id.img);
            RatingBar popupRating = popupView.findViewById(R.id.rating);
            TextView popupId = popupView.findViewById(R.id.idss);

            // Charger l'image de la star dans la popup avec Glide
            Glide.with(context)
                    .load(star.getImg())
                    .override(100, 100)
                    .into(popupImg);

            // Afficher la notation actuelle et l'identifiant
            popupRating.setRating(star.getRating());
            popupId.setText("ID : " + star.getId());

            // Créer et afficher le dialogue de modification
            new AlertDialog.Builder(context)
                    .setTitle(R.string.notez)
                    .setMessage(R.string.donner_note)
                    .setView(popupView)
                    .setPositiveButton(R.string.valider, (dialog, which) -> {
                        // Récupérer la star depuis le service par son id
                        Star s = StarService.getInstance().findById(star.getId());
                        if (s != null) {
                            // Mettre à jour la notation
                            s.setRating(popupRating.getRating());
                            // Sauvegarder la modification
                            StarService.getInstance().update(s);
                            // Rafraîchir l'élément dans la liste
                            notifyItemChanged(position);
                        }
                    })
                    .setNegativeButton(R.string.annuler, null)
                    .show();
        });

        return new StarViewHolder(view);
    }

    /**
     * Liaison des données d'une star avec les vues du ViewHolder.
     *
     * @param holder   Le ViewHolder contenant les vues
     * @param position La position de l'élément dans la liste
     */
    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        // Récupérer la star à la position donnée
        Star star = starsFilter.get(position);

        // Charger l'image circulaire avec Glide
        Glide.with(context)
                .asBitmap()
                .load(star.getImg())
                .override(100, 100)
                .into(holder.img);

        // Afficher le nom en majuscules
        holder.name.setText(star.getName().toUpperCase());

        // Afficher la notation
        holder.rating.setRating(star.getRating());
    }

    /**
     * Retourne le nombre d'éléments dans la liste filtrée.
     *
     * @return Le nombre de stars affichées
     */
    @Override
    public int getItemCount() {
        return starsFilter.size();
    }

    /**
     * Retourne le filtre personnalisé pour la recherche.
     *
     * @return Le filtre NewFilter
     */
    @Override
    public Filter getFilter() {
        return mfilter;
    }

    // ==================== ViewHolder interne ====================

    /**
     * StarViewHolder - Classe interne représentant un élément de la liste.
     * Contient les références aux vues de star_item.xml.
     */
    public static class StarViewHolder extends RecyclerView.ViewHolder {
        // Composants de l'élément
        CircleImageView img;    // Image circulaire de la star
        TextView name;          // Nom de la star
        RatingBar rating;       // Barre de notation

        /**
         * Constructeur du ViewHolder.
         * Initialise les références aux vues.
         *
         * @param itemView La vue racine de l'élément
         */
        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgStar);
            name = itemView.findViewById(R.id.tvName);
            rating = itemView.findViewById(R.id.rating);
        }
    }

    // ==================== Filtre interne ====================

    /**
     * NewFilter - Classe interne pour le filtrage des stars par nom.
     * Filtre la liste en fonction du texte saisi dans la SearchView.
     */
    public class NewFilter extends Filter {

        // Référence à l'adaptateur pour notifier les changements
        public RecyclerView.Adapter mAdapter;

        /**
         * Constructeur du filtre.
         *
         * @param mAdapter L'adaptateur à notifier après filtrage
         */
        public NewFilter(RecyclerView.Adapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }

        /**
         * Effectue le filtrage en arrière-plan.
         * Filtre les stars dont le nom commence par le texte saisi.
         *
         * @param charSequence Le texte de recherche
         * @return Les résultats du filtrage
         */
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            // Liste pour stocker les résultats filtrés
            List<Star> filteredList = new ArrayList<>();

            // Convertir le filtre en chaîne
            String filterPattern = charSequence.toString().toLowerCase().trim();

            if (filterPattern.isEmpty()) {
                // Si le filtre est vide, afficher toutes les stars
                filteredList.addAll(stars);
            } else {
                // Filtrer les stars dont le nom commence par le pattern
                for (Star star : stars) {
                    if (star.getName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(star);
                    }
                }
            }

            // Créer et retourner les résultats
            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        /**
         * Publie les résultats du filtrage sur le thread principal.
         * Met à jour la liste filtrée et notifie l'adaptateur.
         *
         * @param charSequence Le texte de recherche
         * @param filterResults Les résultats du filtrage
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            // Mettre à jour la liste filtrée
            starsFilter.clear();
            starsFilter.addAll((List<Star>) filterResults.values);
            // Notifier l'adaptateur des changements
            mAdapter.notifyDataSetChanged();
        }
    }
}
