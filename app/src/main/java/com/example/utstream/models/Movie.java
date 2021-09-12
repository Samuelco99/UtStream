package com.example.utstream.models;

public class Movie {

    private String imdb;
    private String caratula;
    private String clasificacion;
    private String cover;
    private String sinopsis;
    private String director;
    private String duracion;
    private String fecha;
    private String genero;
    private String link;
    private String tipo;
    private String titulo;

    public Movie(String imdb, String caratula, String clasificacion, String cover, String sinopsis, String director, String duracion, String fecha, String genero, String link, String tipo, String titulo) {
        this.imdb = imdb;
        this.caratula = caratula;
        this.clasificacion = clasificacion;
        this.cover = cover;
        this.sinopsis = sinopsis;
        this.director = director;
        this.duracion = duracion;
        this.fecha = fecha;
        this.genero = genero;
        this.link = link;
        this.tipo = tipo;
        this.titulo = titulo;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImdb() {
        return imdb;
    }

    public String getCaratula() {
        return caratula;
    }

    public String getCover() {
        return cover;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getDirector() {
        return director;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getGenero() {
        return genero;
    }

    public String getLink() {
        return link;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }
}
