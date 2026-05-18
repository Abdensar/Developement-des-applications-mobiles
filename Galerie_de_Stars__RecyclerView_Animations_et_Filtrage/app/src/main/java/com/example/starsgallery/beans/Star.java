package com.example.starsgallery.beans;

/**
 * Star - Classe modèle représentant une star.
 * Contient les informations : id, nom, image URL et notation.
 * Utilise un compteur statique pour l'auto-incrémentation de l'id.
 */
public class Star {

    // Compteur statique pour l'auto-incrémentation des identifiants
    private static int comp = 0;

    // Propriétés de la star
    private int id;         // Identifiant unique
    private String name;    // Nom de la star
    private String img;     // URL de l'image
    private float rating;   // Notation (entre 0 et 5)

    /**
     * Constructeur de la classe Star.
     * L'id est auto-incrémenté à chaque création.
     *
     * @param name   Le nom de la star
     * @param img    L'URL de l'image de la star
     * @param rating La notation de la star (entre 0 et 5)
     */
    public Star(String name, String img, float rating) {
        this.id = ++comp;       // Auto-incrémentation de l'identifiant
        this.name = name;
        this.img = img;
        this.rating = rating;
    }

    // ==================== Getters et Setters ====================

    /**
     * @return L'identifiant unique de la star
     */
    public int getId() {
        return id;
    }

    /**
     * @param id Le nouvel identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Le nom de la star
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Le nouveau nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return L'URL de l'image
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img La nouvelle URL d'image
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return La notation de la star
     */
    public float getRating() {
        return rating;
    }

    /**
     * @param rating La nouvelle notation
     */
    public void setRating(float rating) {
        this.rating = rating;
    }
}
