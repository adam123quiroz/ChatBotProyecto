package edu.com.chatbotsoftI.dto;

public enum Category {
    MOVIE("Movie"), MUSIC("Music"), MUSEUM("Museo");

    Category(String category) {
        this.category = category;
    }

    private String category;

    public String getCategory() {
        return category;
    }
}