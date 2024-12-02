package com.edu.ruben.JokesApp.Entities;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class InfoResponse {
    private boolean error;
    private JokesInfo jokes;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public JokesInfo getJokes() {
        return jokes;
    }

    public void setJokes(JokesInfo jokes) {
        this.jokes = jokes;
    }

    public static class JokesInfo {
        private int totalCount;
        private List<String> categories;
        private List<String> flags;
        private List<String> types;
        private Map<String, int[]> idRange;

        public JokesInfo() {
            idRange = new HashMap<>();
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        public List<String> getFlags() {
            return flags;
        }

        public void setFlags(List<String> flags) {
            this.flags = flags;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public Map<String, int[]> getIdRange() {
            return idRange;
        }

        public void setIdRange(Map<String, int[]> idRange) {
            this.idRange = idRange;
        }
    }

    public static class LanguageInfo {
        private String lang;
        private int count;

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
