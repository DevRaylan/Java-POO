/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

import java.io.Serializable;

/**
 *
 * @author d.raylan
 */
public class Modelo implements Serializable {
    private int id;
    private String descricao;
    private Marca marca;
    
    private Motor motor;
    
    private Cliente cliente;
    
    public Modelo() {
        this.createMotor();
    }
    
    public Modelo(int id, String descricao){
        this();
        this.id = id;
        this.descricao = descricao;
    }
    
    public Modelo(int id, String descricao, Marca marca, Cliente cliente) {
        this(id, descricao );
        this.marca = marca;
        this.cliente = cliente;
    }
    
    private void createMotor() {
        this.motor = new Motor();
        this.motor.setModelo(this);
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
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Motor getMotor() {
        return motor;
    }
    
    @Override
    public String toString() {
        return this.descricao;
    }
}
