package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Categoria;
import br.edu.ifsc.fln.model.domain.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ModeloDAO{

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Modelo Modelo) {
        final String sql = "INSERT INTO Modelo(nome, descricao, preco, id_categoria) VALUES(?,?,?,?);";
        final String sqlEstoque = "INSERT INTO estoque(id_Modelo) (SELECT max(id) FROM Modelo);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //registra o Modelo
            stmt.setString(1, Modelo.getNome());
            stmt.setString(2, Modelo.getDescricao());
            stmt.setBigDecimal(3, Modelo.getPreco());
            stmt.setInt(4, Modelo.getCategoria().getId());
            stmt.execute();
            //registra o estoque do Modelo imediatamente
            stmt = connection.prepareStatement(sqlEstoque);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Modelo Modelo) {
        String sql = "UPDATE Modelo SET nome=?, descricao=?, preco=?, id_categoria=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, Modelo.getNome());
            stmt.setString(2, Modelo.getDescricao());
            stmt.setBigDecimal(3, Modelo.getPreco());
            stmt.setInt(4, Modelo.getCategoria().getId());
            stmt.setInt(5, Modelo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Modelo Modelo) {
        String sql = "DELETE FROM Modelo WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Modelo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Modelo> listar() {
        String sql =  "SELECT p.id as Modelo_id, p.nome as Modelo_nome, p.descricao as Modelo_descricao, p.preco as Modelo_preco, "
                + "c.id as categoria_id, c.descricao as categoria_descricao "
                + "FROM Modelo p INNER JOIN categoria c ON c.id = p.id_categoria;";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo Modelo = populateVO(resultado);
                retorno.add(Modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Modelo> listarPorCategoria(Categoria categoria) {
        String sql =  "SELECT p.id as Modelo_id, p.nome as Modelo_nome, p.descricao as Modelo_descricao, p.preco as Modelo_preco, "
                + "c.id as categoria_id, c.descricao as categoria_descricao "
                + "FROM Modelo p INNER JOIN categoria c ON c.id = p.id_categoria WHERE c.id = ?;";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo Modelo = populateVO(resultado);
                retorno.add(Modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Modelo buscar(Modelo Modelo) {
        String sql =  "SELECT p.id as Modelo_id, p.nome as Modelo_nome, p.descricao as Modelo_descricao, p.preco as Modelo_preco, "
                + "c.id as categoria_id, c.descricao as categoria_descricao "
                + "FROM Modelo p INNER JOIN categoria c ON c.id = p.id_categoria WHERE p.id = ?;";
        Modelo retorno = new Modelo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Modelo.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    private Modelo populateVO(ResultSet rs) throws SQLException {
        Modelo Modelo = new Modelo();
        Categoria categoria = new Categoria();
        Modelo.setCategoria(categoria);
        
        Modelo.setId(rs.getInt("Modelo_id"));
        Modelo.setNome(rs.getString("Modelo_nome"));
        Modelo.setDescricao(rs.getString("Modelo_descricao"));
        Modelo.setPreco(rs.getBigDecimal("Modelo_preco"));
        categoria.setId(rs.getInt("categoria_id"));
        categoria.setDescricao(rs.getString("categoria_descricao"));
        return Modelo;
    }   
    

}
