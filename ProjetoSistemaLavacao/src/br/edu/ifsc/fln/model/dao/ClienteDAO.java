/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.PessoaFisica;
import br.edu.ifsc.fln.model.domain.PessoaJuridica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d.raylan
 */
public class ClienteDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, email, fone) VALUES(?, ?, ?)";
        String sqlPJ = "INSERT INTO nacional(id_cliente, cnpj) VALUES((SELECT max(id) FROM cliente), ?)";
        String sqlPF = "INSERT INTO internacional(id_cliente, nif, pais) VALUES((SELECT max(id) FROM cliente), ?, ?)";
        try {
            //armazena os dados da superclasse
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getFone());
            stmt.execute();
            //armazena os dados da subclasse
            if (!(cliente instanceof PessoaJuridica)) {
                stmt = connection.prepareStatement(sqlPF);
                stmt.setString(1, ((PessoaFisica)cliente).getNif());
                stmt.setString(2, ((PessoaFisica)cliente).getPais());
                stmt.execute();
            } else {
                stmt = connection.prepareStatement(sqlPJ);
                stmt.setString(1, ((PessoaJuridica)cliente).getCnpj());
                stmt.execute();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome=?, email=?, fone=? WHERE id=?";
        String sqlFN = "UPDATE nacional SET cnpj=? WHERE id_cliente = ?";
        String sqlFI = "UPDATE internacional SET nif=?, pais=? WHERE id_cliente = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getFone());
            stmt.setInt(4, cliente.getId());
            stmt.execute();
            if (cliente instanceof PessoaJuridica) {
                stmt = connection.prepareStatement(sqlFN);
                stmt.setString(1, ((PessoaJuridica)cliente).getCnpj());
                stmt.setInt(2, cliente.getId());
                stmt.execute();
            } else {
                stmt = connection.prepareStatement(sqlFI);
                stmt.setString(1, ((PessoaFisica)cliente).getNif());
                stmt.setString(2, ((PessoaFisica)cliente).getPais());
                stmt.setInt(3, cliente.getId());
                stmt.execute();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     public boolean remover(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente f "
                        + "LEFT JOIN nacional n on n.id_cliente = f.id "
                        + "LEFT JOIN internacional i on i.id_cliente = f.id;";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = populateVO(resultado);
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Cliente buscar(Cliente cliente) {
        String sql = "SELECT * FROM cliente f "
                        + "LEFT JOIN nacional n on n.id_cliente = f.id "
                        + "LEFT JOIN internacional i on i.id_cliente = f.id WHERE id=?";
        Cliente retorno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Cliente buscar(int id) {
        String sql = "SELECT * FROM cliente f "
                        + "LEFT JOIN nacional n on n.id_cliente = f.id "
                        + "LEFT JOIN internacional i on i.id_cliente = f.id WHERE id=?";
        Cliente retorno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    private Cliente populateVO(ResultSet rs) throws SQLException {
        Cliente cliente;
        if (rs.getString("nif") == null || rs.getString("nif").length() <= 0) {
            //é um cliente nacional
            cliente = new PessoaJuridica();
            ((PessoaJuridica)cliente).setCnpj(rs.getString("cnpj"));
        } else {
            //é um cliente internacional
            cliente = new PessoaFisica();
            ((PessoaFisica)cliente).setNif(rs.getString("nif"));
            ((PessoaFisica)cliente).setPais(rs.getString("pais"));
        }
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        cliente.setFone(rs.getString("fone"));
        return cliente;
    }
}

