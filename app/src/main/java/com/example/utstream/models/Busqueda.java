package com.example.utstream.models;

public class Busqueda {
    private String caratula;
    private String cover;
    private String tipo;
    private String titulo;
    private String descripcion;
    private String link;
    private String imdb;
    private String duracion;
    private String director;
    private String genero;
    private String clasificacion;
    private String fecha;
    private String autor;
    private String editorial;
    private String paginas;
    private String numerCap;
    private Object id;

    public Busqueda(String caratula, String cover, String tipo, String titulo, String descripcion, String link, String imdb, String duracion, String director, String genero, String clasificacion, String fecha, String autor, String editorial, String paginas, String numerCap, Object id) {
        this.caratula = caratula;
        this.cover = cover;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.link = link;
        this.imdb = imdb;
        this.duracion = duracion;
        this.director = director;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.fecha = fecha;
        this.autor = autor;
        this.editorial = editorial;
        this.paginas = paginas;
        this.numerCap = numerCap;
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

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public void setNumerCap(String numerCap) {
        this.numerCap = numerCap;
    }

    public String getCaratula() {
        return caratula;
    }

    public String getCover() {
        return cover;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLink() {
        return link;
    }

    public String getImdb() {
        return imdb;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getPaginas() {
        return paginas;
    }

    public String getNumerCap() {
        return numerCap;
    }
}
