/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 *
 * @author mpisc
 */
public class Motor {
    private int Potencia;
    private ETipoCombustivel etipocombustivel;
    private Modelo modelo;

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public int getPotencia() {
        return Potencia;
    }

    public void setPotencia(int Potencia) {
        this.Potencia = Potencia;
    }

    public ETipoCombustivel getEtipocombustivel() {
        return etipocombustivel;
    }

    public void setEtipocombustivel(ETipoCombustivel etipocombustivel) {
        this.etipocombustivel = etipocombustivel;
    }

    @Override
    public String toString() {
        return "Motor{" + "Potencia=" + Potencia + ", etipocombustivel=" + etipocombustivel + '}';
    }
    
}
