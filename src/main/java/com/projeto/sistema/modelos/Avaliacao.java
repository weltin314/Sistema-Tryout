package com.projeto.sistema.modelos;

import jakarta.persistence.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player player;

    private int comunicacao;
    private int conhecimento;
    private int faseRota;
    private int fight;
    private int championPool;

    @Column(length = 500)
    private String anotacao;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public int getComunicacao() { return comunicacao; }
    public void setComunicacao(int comunicacao) { this.comunicacao = comunicacao; }

    public int getConhecimento() { return conhecimento; }
    public void setConhecimento(int conhecimento) { this.conhecimento = conhecimento; }

    public int getFaseRota() { return faseRota; }
    public void setFaseRota(int faseRota) { this.faseRota = faseRota; }

    public int getFight() { return fight; }
    public void setFight(int fight) { this.fight = fight; }

    public int getChampionPool() { return championPool; }
    public void setChampionPool(int championPool) { this.championPool = championPool; }

    public String getAnotacao() { return anotacao; } // NOVO
    public void setAnotacao(String anotacao) { this.anotacao = anotacao; } // NOVO
}