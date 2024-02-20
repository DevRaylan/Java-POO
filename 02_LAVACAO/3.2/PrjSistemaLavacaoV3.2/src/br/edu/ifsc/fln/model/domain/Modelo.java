/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 *
 * @author d.raylan
 */
public class Modelo {
    private int id;
    private String descricao;
    private Marca marca;
    
    public Modelo()
    {
           this.id = 0;
           this.descricao = null;
           this.marca= null;
    }
    
    public Modelo(String descricao, Marca marca)
    {
           this.id = 0;
           this.descricao = descricao;
           this.marca= marca;
    }
    
    public Modelo(int id, String descricao, Marca marca)
    {
           this.id = id;
           this.descricao = descricao;
           this.marca= marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return marca +" "+ descricao;//retorna para veiculo
    }
}

