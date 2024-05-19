package com.nathanespejo.blockchaincharityapp;

import java.util.ArrayList;

public class Charity {

    public static ArrayList<Charity> charityArrayList;

    private int id;
    private String name, descS, descL;
    private double holding;

    public Charity(int id, String name, String descS, String descL, double holding) {
        this.id = id;
        this.name = name;
        this.descS = descS;
        this.descL = descL;
        this.holding = holding;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescS() {
        return descS;
    }

    public void setDescS(String descS) {
        this.descS = descS;
    }

    public String getDescL() {
        return descL;
    }

    public void setDescL(String descL) {
        this.descL = descL;
    }

    public double getHolding() {
        return holding;
    }

    public void setHolding(double holding) {
        this.holding = holding;
    }
}
