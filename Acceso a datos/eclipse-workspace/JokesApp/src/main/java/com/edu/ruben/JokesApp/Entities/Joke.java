package com.edu.ruben.JokesApp.Entities;

public class Joke {
    private int id;
    private String category;
    private String type;
    private String setup;
    private String delivery;
    private String joke;
    private Flags flags;
    private String lang;

    public Joke(int id, String category, String type, String setup, String delivery, String joke, Flags flags, String lang) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.setup = setup;
        this.delivery = delivery;
        this.joke = joke;
        this.flags = flags;
        this.lang = lang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public static class Flags {
        private boolean nsfw;
        private boolean religious;
        private boolean political;
        private boolean racist;
        private boolean sexist;
        private boolean explicit;

        public boolean isNsfw() {
            return nsfw;
        }

        public void setNsfw(boolean nsfw) {
            this.nsfw = nsfw;
        }

        public boolean isReligious() {
            return religious;
        }

        public void setReligious(boolean religious) {
            this.religious = religious;
        }

        public boolean isPolitical() {
            return political;
        }

        public void setPolitical(boolean political) {
            this.political = political;
        }

        public boolean isRacist() {
            return racist;
        }

        public void setRacist(boolean racist) {
            this.racist = racist;
        }

        public boolean isSexist() {
            return sexist;
        }

        public void setSexist(boolean sexist) {
            this.sexist = sexist;
        }

        public boolean isExplicit() {
            return explicit;
        }

        public void setExplicit(boolean explicit) {
            this.explicit = explicit;
        }
    }
}
