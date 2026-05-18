package com.example.projetws.beans;

/**
 * Classe modèle représentant un Etudiant.
 * Contient les informations nécessaires qui seront échangées avec le Web Service PHP via JSON.
 */
public class Etudiant {
    // Propriétés de l'étudiant
    private int id;
    private String nom;
    private String prenom;
    private String ville;
    private String sexe;

    /**
     * Constructeur vide (nécessaire pour la désérialisation Gson).
     */
    public Etudiant() {
    }

    /**
     * Constructeur complet.
     *
     * @param id     L'identifiant de l'étudiant
     * @param nom    Le nom de l'étudiant
     * @param prenom Le prénom de l'étudiant
     * @param ville  La ville de l'étudiant
     * @param sexe   Le sexe de l'étudiant (homme/femme)
     */
    public Etudiant(int id, String nom, String prenom, String ville, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.sexe = sexe;
    }

    // ==================== Getters et Setters ====================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    /**
     * Représentation sous forme de chaîne de caractères de l'objet Etudiant.
     */
    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", ville='" + ville + '\'' +
                ", sexe='" + sexe + '\'' +
                '}';
    }
}
