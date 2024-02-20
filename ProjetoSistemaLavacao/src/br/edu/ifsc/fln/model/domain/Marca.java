/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 *
 * @author d.raylan
 */
public class Marca {
    private int idMarca;
    private String nome;

    public Marca(String nome) {
        this.nome = nome;
    }

    public Marca() {
    }

    public int getId() {
        return idMarca;
    }

    public void setId(int id) {
        this.idMarca = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
