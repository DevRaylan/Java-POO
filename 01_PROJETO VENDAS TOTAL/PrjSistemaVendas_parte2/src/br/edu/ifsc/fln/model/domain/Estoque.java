/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 *
 * @author mpisc
 */
public class Estoque {
    private int quantidade;
    private int qtdMaxima;
    private int qtdMinima;
    private ESituacao situacao = ESituacao.INATIVO;
    
    private Modelo Modelo;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQtdMaxima() {
        return qtdMaxima;
    }

    public void setQtdMaxima(int qtdMaxima) {
        this.qtdMaxima = qtdMaxima;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public Modelo getModelo() {
        return Modelo;
    }

    public void setModelo(Modelo Modelo) {
        this.Modelo = Modelo;
    }

    public ESituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(ESituacao situacao) {
        this.situacao = situacao;
    }
    
    public void repor(int qtd) throws Exception {
        if (situacao != ESituacao.ATIVO) {
            throw new Exception("Não é possível movimentar o estoque,\npois a situação do mesmo se encontra " + situacao.getDescricao());
        }
        if (this.quantidade + qtd <= this.qtdMaxima) {
            this.quantidade += qtd;
        } else {
            throw new Exception("A quantidade de reposição não pode ser maior do que a capacidade do estoque."); 
        }
    }
    
    public void retirar(int qtd) throws Exception {
        if (situacao != ESituacao.ATIVO) {
            throw new Exception("Não é possível movimentar o estoque,\npois a situação do mesmo se encontra " + situacao.getDescricao());
        }
        if (this.quantidade - qtd >= 0) {
            this.quantidade -= qtd;
        } else {
            throw new Exception("Não há estoque suficiente para essa transação.");
        }
    }

    @Override
    public String toString() {
        return Integer.toString(this.quantidade);
    }
    
    
}
