package com.example.greenbotprikolist;

import com.google.firebase.Timestamp;

public class Note {
    private String deckName;
    private String deckFormat;

    private String creature;
    private String land;
    private String sorcery;
    private String artifact;

    private Timestamp timestamp;

    public Note() {

    }

    public String getCreature() {
        return creature;
    }

    public void setCreature(String creature) {
        this.creature = creature;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getSorcery() {
        return sorcery;
    }

    public void setSorcery(String sorcery) {
        this.sorcery = sorcery;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String title) {
        this.deckName = title;
    }

    public String getDeckFormat() {
        return deckFormat;
    }

    public void setDeckFormat(String deckFormat) {
        this.deckFormat = deckFormat;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
