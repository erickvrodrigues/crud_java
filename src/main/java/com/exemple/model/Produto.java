package com.exemple.model;

public class Produto {

    private int id;
    private String nomePrd;
    private double preco;
    private int qtd;

    public Produto(){


    }

    public Produto(String nomePrd, double preco, int qtd) {
        this.nomePrd = nomePrd;
        this.preco = preco;
        this.qtd = qtd;
    }

    public Produto(int id, String nomePrd, double preco, int qtd) {
        this.id = id;
        this.nomePrd = nomePrd;
        this.preco = preco;
        this.qtd = qtd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(this.id < 0){
            throw new IllegalArgumentException("ID não pode ser um valor vazio");
        }
        this.id = id;
    }

    public String getNomePrd() {
        return nomePrd;
    }

    public void setNomePrd(String nomePrd) {
        if(this.nomePrd == null || this.nomePrd.trim().isBlank()){
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nomePrd = nomePrd.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (this.preco < 0){
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        if(this.qtd < 0){
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        }
        this.qtd = qtd;
    }
}
