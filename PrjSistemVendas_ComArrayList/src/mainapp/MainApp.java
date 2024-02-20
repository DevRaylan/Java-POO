/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainapp;

import domain.Categoria;
import domain.Produto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mpisc
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Categoria c1 = new Categoria(1);
        //c1.setId(1);
        c1.setDescricao("Vestuario");
//              
        
        Categoria c2 = new Categoria(2, "Acessórias");
//        c2.setId(2);
//        c2.setDescricao("Acessórios");

        Categoria c3 = new Categoria();
        c3.setId(3);
        c3.setDescricao("Calçados");
        
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
        
        List<Produto> listaProdutos = new ArrayList<>();        
        
        listaProdutos.add(p1);
        listaProdutos.add(p2);
        listaProdutos.add(p3);
        listaProdutos.add(new Produto(4, "Tenis", "Runner", new BigDecimal(500.0), c3));
        
        imprimir(listaProdutos);
        
        System.out.println("\nNova lista...");
        listaProdutos.remove(p3);
        imprimir(listaProdutos);
        
        System.out.println("\nSituacao da lista após remoção por id....");
        remover(listaProdutos, 4);
        imprimir(listaProdutos);
        
        System.out.println("\nPesquisa de produto");
        Produto pPesquisa = pesquisar(listaProdutos, 2);
        if (pPesquisa == null) {
            System.out.println("Produto não encontrado");
        } else {
            System.out.println("Produto: " + pPesquisa);
        }
        
    }
    
    /**
     * O método imprimir é utilizado para apresentar uma lista de Produtos
     * @param listaProdutos do tipo Produto 
     */
    public static void imprimir(List<Produto> listaProdutos) {
        for (Produto p: listaProdutos) {
            System.out.println("Produto: " + p);
        }
    }
    
    /**
     * Método para pesquisar produto por Id na lista de produtos
     * @param listaProdutos
     * @param id
     * @return Produto p
     */
    public static Produto pesquisar(List<Produto> listaProdutos, int id) {
        for (Produto p: listaProdutos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public static void remover(List<Produto> listaProdutos, int id) {
        for (Produto p: listaProdutos) {
            if (p.getId() == id) {
                 listaProdutos.remove(p);
                 return;
            }
        }
    }
    
}
