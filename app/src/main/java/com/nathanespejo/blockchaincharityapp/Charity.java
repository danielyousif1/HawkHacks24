package com.nathanespejo.blockchaincharityapp;

import java.util.List;

public class Charity {
    private List<CharityData> data;

    public List<CharityData> getData() {
        return data;
    }

    public void setData(List<CharityData> data) {
        this.data = data;
    }

    public static class CharityData {
        private String CharityDescL;
        private String CharityDescS;
        private int CharityID;
        private String CharityName;
        private int Holding;

        public String getCharityDescL() {
            return CharityDescL;
        }

        public void setCharityDescL(String charityDescL) {
            CharityDescL = charityDescL;
        }

        public String getCharityDescS() {
            return CharityDescS;
        }

        public void setCharityDescS(String charityDescS) {
            CharityDescS = charityDescS;
        }

        public int getCharityID() {
            return CharityID;
        }

        public void setCharityID(int charityID) {
            CharityID = charityID;
        }

        public String getCharityName() {
            return CharityName;
        }

        public void setCharityName(String charityName) {
            CharityName = charityName;
        }

        public int getHolding() {
            return Holding;
        }

        public void setHolding(int holding) {
            Holding = holding;
        }
    }
}
