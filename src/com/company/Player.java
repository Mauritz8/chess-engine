package com.company;

public class Player {
    private String name;
    private boolean whiteSide;

    public Player(String name, boolean whiteSide) {
        this.name = name;
        this.whiteSide = whiteSide;
    }

    public boolean isWhiteSide() {
        return whiteSide;
    }
}
