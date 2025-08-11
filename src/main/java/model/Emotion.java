// src/main/java/model/Emotion.java

package model;

public enum Emotion {
    ALEGRIA("Alegría"),
    TRISTEZA("Tristeza"),
    IRA("Ira"),
    ASCO("Asco"),
    MIEDO("Miedo"),
    ANSIEDAD("Ansiedad"),
    ENVIDIA("Envidia"),
    VERGUENZA("Vergüenza"),
    ABURRIMIENTO("Aburrimiento"),
    NOSTALGIA("Nostalgia");

    private String name;

    Emotion(String string) {
        this.name = string;
    }

    public String getName() {
        return name;
    }
}
