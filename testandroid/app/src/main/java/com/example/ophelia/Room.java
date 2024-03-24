package com.example.ophelia;

public class Room {
    private int id;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;

    public Room(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}