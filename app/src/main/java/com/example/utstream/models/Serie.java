package com.example.utstream.models;

public class Serie {

    private String caratula;
    private String clasificacion;
    private String cover;
    private String sinopsis;
    private String director;
    private String duracion;
    private String fecha;
    private String genero;
    private String numerCap;
    private String tipo;
    private String titulo;
    private Object id;

    public Serie(String caratula, String clasificacion, String cover, String sinopsis, String director, String duracion, String fecha, String genero, String numerCap, String tipo, String titulo, Object id) {
        this.caratula = caratula;
        this.clasificacion = clasificacion;
        this.cover = cover;
        this.sinopsis = sinopsis;
        this.director = director;
        this.duracion = duracion;
        this.fecha = fecha;
        this.genero = genero;
        this.numerCap = numerCap;
        this.tipo = tipo;
        this.titulo = titulo;
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
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

    public void setNumerCap(String numerCap) {
        this.numerCap = numerCap;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCaratula() {
        return caratula;
    }

    public String getClasificacion() {
        return clasificacion;
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

    public String getNumerCap() {
        return numerCap;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }
}
