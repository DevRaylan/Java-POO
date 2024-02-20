/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author d.raylan
 */
public abstract class Cliente extends Object {
    protected int id;
    protected String nome;
    protected String email;
    protected String fone;
    protected LocalDate data;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    protected List<Modelo> modelos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    
    public List<Modelo> getModelos() {
        return this.modelos;
    }
    
    public void add(Modelo modelo) {
        if (modelos == null) {
            modelos = new ArrayList<>();
        }
        modelos.add(modelo);
        modelo.setCliente(this);
    }
    
    public void remove(Modelo modelo) {
        if (modelos != null) {
            modelos.remove(modelo);
            modelo.setCliente(null);
        }
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }
    
    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dados do Cliente ").append(this.getClass().getSimpleName()).append("\n");
        sb.append("Id: ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Celular: ").append(fone).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Data de Cadastro.: ").append(data).append("\n");
        return sb.toString();
    }


}

