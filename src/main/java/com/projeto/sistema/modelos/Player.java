package com.projeto.sistema.modelos;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "players")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String role;

    private int comunicacao;
    private int conhecimento;
    private int faseRota;
    private int fight;
    private int championPool;

    public int getComunicacao() {
        return comunicacao;
    }

    public void setComunicacao(int comunicacao) {
        this.comunicacao = comunicacao;
    }

    public int getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(int conhecimento) {
        this.conhecimento = conhecimento;
    }

    public int getFaseRota() {
        return faseRota;
    }

    public void setFaseRota(int faseRota) {
        this.faseRota = faseRota;
    }

    public int getFight() {
        return fight;
    }

    public void setFight(int fight) {
        this.fight = fight;
    }

    public int getChampionPool() {
        return championPool;
    }

    public void setChampionPool(int championPool) {
        this.championPool = championPool;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
