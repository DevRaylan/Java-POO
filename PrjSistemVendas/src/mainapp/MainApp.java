/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainapp;

import domain.Categoria;
import domain.Produto;
import java.math.BigDecimal;

/**
 *
 * @author mpisc
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Categoria c1 = new Categoria();
        Categoria c2 = new Categoria();
        c1.setId(1);
        c1.setDescricao("Vestuario");
        c2.setId(2);
        c2.setDescricao("Acessórios");
        
        Produto p1 = new Produto();
        p1.setId(1);
        p1.setNome("Bermuda");
        p1.setDescricao("Bermuda Jeans");
        p1.setPreco(new BigDecimal(100.00));
        p1.setCategoria(c1);
        
        Produto p2 = new Produto();
        p2.setId(2);
        p2.setNome("Camisa");
        p2.setDescricao("Camisa de linho");
        p2.setPreco(new BigDecimal(300.00));
        p2.setCategoria(c1);
        
        Produto p3 = new Produto();
        p3.setId(3);
        p3.setNome("Colar");
        p3.setDescricao("Colar de pérolas");
        p3.setPreco(new BigDecimal(3000.00));
        p3.setCategoria(c2);
        
        
//        System.out.println("Categoria 1: " + c1.getId() + " - " + c1.getDescricao()); 
//        System.out.println("Categoria 2: " + c2.getId() + " - " + c2.getDescricao());
        
        System.out.println("Categoria: " + c1);
        System.out.println("Categoria: " + c2);
        System.out.println("Produto: " + p1);
        System.out.println("Produto: " + p2);
        System.out.println("Produto: " + p3);
        
        System.out.println("Nome do produto: " + p1.getNome() + "  Categoria: " + p1.getCategoria().getDescricao());
    }
    
}
