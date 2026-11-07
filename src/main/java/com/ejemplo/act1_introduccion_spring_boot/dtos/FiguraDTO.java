package com.ejemplo.act1_introduccion_spring_boot.dtos;

public class FiguraDTO {
    private Long id;
    private String personaje;
    private String anime;
    private Double precio;

    public FiguraDTO() {}

    public FiguraDTO(Long id, String personaje, String anime, Double precio) {
        this.id = id;
        this.personaje = personaje;
        this.anime = anime;
        this.precio = precio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPersonaje() { return personaje; }
    public void setPersonaje(String personaje) { this.personaje = personaje; }

    public String getAnime() { return anime; }
    public void setAnime(String anime) { this.anime = anime; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}