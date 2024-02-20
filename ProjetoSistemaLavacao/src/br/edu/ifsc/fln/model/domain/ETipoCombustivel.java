/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 *
 * @author d.raylan
 */
public enum ETipoCombustivel {
    GASOLINA(1), ETANOL(2), FLEX(3), DIESEL(4), GNV(5), OUTRO(6);
        private int id;
        
        private ETipoCombustivel(int id) {
            this.id = id;
        }

    public int getId() {
        return id;
    }
}
