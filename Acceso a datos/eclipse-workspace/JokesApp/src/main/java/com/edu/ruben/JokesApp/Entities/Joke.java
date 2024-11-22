package com.edu.ruben.JokesApp.Entities;

public class Joke {
    private int id;  // Added the id field
    private String category;
    private String type;
    private String setup;  // For "twopart" jokes
    private String delivery;  // For "twopart" jokes
    private String joke;  // For "single" jokes
    private Flags flags;
    private String lang;  // Language field

    // Getters and Setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getJoke() {
        return joke;
    }

    public Flags getFlags() {
        return flags;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    // Static nested class for Flags
    public static class Flags {
        private boolean nsfw;
        private boolean religious;
        private boolean political;
        private boolean racist;
        private boolean sexist;
        private boolean explicit;

        // Getters for Flags
        public boolean isNsfw() {
            return nsfw;
        }

        public boolean isReligious() {
            return religious;
        }

        public boolean isPolitical() {
            return political;
        }

        public boolean isRacist() {
            return racist;
        }

        public boolean isSexist() {
            return sexist;
        }

        public boolean isExplicit() {
            return explicit;
        }

        // Setters for Flags
        public void setNsfw(boolean nsfw) {
            this.nsfw = nsfw;
        }

        public void setReligious(boolean religious) {
            this.religious = religious;
        }

        public void setPolitical(boolean political) {
            this.political = political;
        }

        public void setRacist(boolean racist) {
            this.racist = racist;
        }

        public void setSexist(boolean sexist) {
            this.sexist = sexist;
        }

        public void setExplicit(boolean explicit) {
            this.explicit = explicit;
        }
    }
}
