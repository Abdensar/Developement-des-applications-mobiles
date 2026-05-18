package com.example.starsgallery.service;

import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;

import java.util.ArrayList;
import java.util.List;

/**
 * StarService - Service singleton pour la gestion des stars.
 * Implémente l'interface IDao pour les opérations CRUD.
 * Utilise le pattern Singleton pour garantir une instance unique.
 */
public class StarService implements IDao<Star> {

    // Instance unique du service (pattern Singleton)
    private static StarService instance;

    // Liste des stars en mémoire
    private List<Star> stars;

    /**
     * Constructeur privé (pattern Singleton).
     * Initialise la liste et charge les données de test.
     */
    private StarService() {
        stars = new ArrayList<>();
        seed(); // Charger les données initiales
    }

    /**
     * Obtenir l'instance unique du service.
     *
     * @return L'instance singleton de StarService
     */
    public static StarService getInstance() {
        if (instance == null) {
            instance = new StarService();
        }
        return instance;
    }

    /**
     * Méthode d'initialisation des données de test.
     * Ajoute des stars avec leurs images et notations.
     */
    private void seed() {
        // Ajout des stars avec des images locales depuis les ressources drawable
        String baseUri = "android.resource://com.example.starsgallery/drawable/";
        stars.add(new Star("Emma Watson",
                baseUri + "emma_watson",
                4.5f));
        stars.add(new Star("Tom Cruise",
                baseUri + "tom_cruise",
                4.2f));
        stars.add(new Star("Scarlett Johansson",
                baseUri + "scarlett_johansson",
                4.7f));
        stars.add(new Star("Leonardo DiCaprio",
                baseUri + "leonardo_dicaprio",
                4.8f));
    }

    /**
     * Créer une nouvelle star.
     *
     * @param star La star à ajouter
     * @return true si l'ajout a réussi
     */
    @Override
    public boolean create(Star star) {
        return stars.add(star);
    }

    /**
     * Mettre à jour une star existante.
     * Recherche la star par son id et met à jour ses propriétés.
     *
     * @param star La star avec les nouvelles valeurs
     * @return true si la mise à jour a réussi, false si la star n'est pas trouvée
     */
    @Override
    public boolean update(Star star) {
        // Parcourir la liste pour trouver la star à modifier
        for (int i = 0; i < stars.size(); i++) {
            if (stars.get(i).getId() == star.getId()) {
                // Remplacer la star à la position trouvée
                stars.set(i, star);
                return true;
            }
        }
        return false; // Star non trouvée
    }

    /**
     * Supprimer une star de la liste.
     *
     * @param star La star à supprimer
     * @return true si la suppression a réussi
     */
    @Override
    public boolean delete(Star star) {
        return stars.remove(star);
    }

    /**
     * Rechercher une star par son identifiant.
     *
     * @param id L'identifiant de la star
     * @return La star trouvée ou null si non trouvée
     */
    @Override
    public Star findById(int id) {
        // Parcourir la liste pour trouver la star par son id
        for (Star star : stars) {
            if (star.getId() == id) {
                return star;
            }
        }
        return null; // Star non trouvée
    }

    /**
     * Récupérer toutes les stars.
     *
     * @return La liste complète des stars
     */
    @Override
    public List<Star> findAll() {
        return stars;
    }
}
