package com.mundoeducativo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String continente;

    @NotBlank
    private String nombre;

    @NotBlank
    @Column(name = "bandera_url")
    private String banderaUrl;

    @NotBlank
    private String valorEducativo;

    @NotBlank
    private String emojiEducativo;



    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getContinente() {
        return continente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getBanderaUrl() {
        return banderaUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBanderaUrl(String banderaUrl) {
        this.banderaUrl = banderaUrl;
    }

    public String getValorEducativo() {
        return valorEducativo;
    }

    public void setValorEducativo(String valorEducativo) {
        this.valorEducativo = valorEducativo;
    }

    public String getEmojiEducativo() {
        return emojiEducativo;
    }

    public void setEmojiEducativo(String emojiEducativo) {
        this.emojiEducativo = emojiEducativo;
    }

    // 🔧 Método que devuelve la clase CSS según el continente
    public String getCssClass() {
        if (continente == null) return "";
        return switch (continente) {
            case "América" -> "america";
            case "Europa" -> "europa";
            case "Asia" -> "asia";
            case "África" -> "africa";
            case "Oceanía" -> "oceania";
            default -> "";
        };
    }

}