package com.example.utstream.models;

public class Libro {
    private String autor;
    private String caratula;
    private String clasificacion;
    private String cover;
    private String sinopsis;
    private String editorial;
    private String fecha;
    private String link;
    private String genero;
    private String paginas;
    private String tipo;
    private String titulo;

    public Libro(String autor, String caratula, String clasificacion, String cover, String sinopsis, String editorial, String fecha, String link, String genero, String paginas, String tipo, String titulo) {
        this.autor = autor;
        this.caratula = caratula;
        this.clasificacion = clasificacion;
        this.cover = cover;
        this.sinopsis = sinopsis;
        this.editorial = editorial;
        this.fecha = fecha;
        this.link = link;
        this.genero = genero;
        this.paginas = paginas;
        this.tipo = tipo;
        this.titulo = titulo;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
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

    public String getEditorial() {
        return editorial;
    }

    public String getFecha() {
        return fecha;
    }

    public String getLink() {
        return link;
    }

    public String getGenero() {
        return genero;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }
}
