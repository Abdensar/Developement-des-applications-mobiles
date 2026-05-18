package com.example.starsgallery.dao;

import java.util.List;

/**
 * IDao - Interface générique pour les opérations CRUD.
 * Définit les méthodes de base pour la gestion des données.
 *
 * @param <T> Le type d'entité géré par le DAO
 */
public interface IDao<T> {

    /**
     * Créer un nouvel élément.
     *
     * @param o L'objet à créer
     * @return true si la création a réussi, false sinon
     */
    boolean create(T o);

    /**
     * Mettre à jour un élément existant.
     *
     * @param o L'objet à mettre à jour
     * @return true si la mise à jour a réussi, false sinon
     */
    boolean update(T o);

    /**
     * Supprimer un élément.
     *
     * @param o L'objet à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    boolean delete(T o);

    /**
     * Rechercher un élément par son identifiant.
     *
     * @param id L'identifiant de l'élément
     * @return L'objet trouvé ou null
     */
    T findById(int id);

    /**
     * Récupérer tous les éléments.
     *
     * @return La liste de tous les éléments
     */
    List<T> findAll();
}
