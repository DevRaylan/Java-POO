/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 *
 * @author d.raylan
 */
public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String iEstadual;

    public String getiEstadual() {
        return iEstadual;
    }

    public void setiEstadual(String iEstadual) {
        this.iEstadual = iEstadual;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    @Override
    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getDados()).append("\n");
        sb.append("CNPJ....................: ").append(cnpj).append("\n");
        sb.append("Inscrição Estadual......: ").append(iEstadual).append("\n");
        return sb.toString();
    }
    
}
