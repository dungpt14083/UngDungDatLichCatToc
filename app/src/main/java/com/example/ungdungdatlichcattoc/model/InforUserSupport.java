package com.example.ungdungdatlichcattoc.model;

public class InforUserSupport {
    private int icon;
    private String content;
    private int icon1;

    public InforUserSupport() {
    }

    public InforUserSupport(int icon, String content, int icon1) {
        this.icon = icon;
        this.content = content;
        this.icon1 = icon1;
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

    public int getIcon1() {
        return icon1;
    }

    public void setIcon1(int icon1) {
        this.icon1 = icon1;
    }
}
