package com.example.utstream.models;

import java.util.ArrayList;
import java.util.List;

public class Capitulo {

    private String cover;
    private String link;
    private String titulo;

    public Capitulo(String cover, String link, String titulo) {
        this.cover = cover;
        this.link = link;
        this.titulo = titulo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
