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
    
    private ECategoria ecategoria;

    public ECategoria getEcategoria() {
        return ecategoria;
    }

    public void setEcategoria(ECategoria ecategoria) {
        this.ecategoria = ecategoria;
    }
    private Motor motor;
    private Marca marca;

    public Modelo() {
        this.createMotor();
    }
    
    private void createMotor() {
        this.motor = new Motor();
        //atribui o produto ao estoque
        this.motor.setModelo(this);
    }

    public Modelo(String descricao, Marca marca) {
        this.descricao = descricao;
        this.marca = marca;
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

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }
    

    @Override
    public String toString() {
        return this.descricao;
    }
    
    
    
}
