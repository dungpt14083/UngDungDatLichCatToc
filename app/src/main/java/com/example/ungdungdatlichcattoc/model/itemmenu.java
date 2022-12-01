package com.example.ungdungdatlichcattoc.model;

public class itemmenu {
    int icon;
    String content;

    public itemmenu() {
    }

    public itemmenu(int icon, String content) {
        this.icon = icon;
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
