package com.example.ungdungdatlichcattoc.model;

public class camket {
    int icon;
    String day;
    String content;

    public camket(int icon, String day, String content) {
        this.icon = icon;
        this.day = day;
        this.content = content;
    }

    public camket() {
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
