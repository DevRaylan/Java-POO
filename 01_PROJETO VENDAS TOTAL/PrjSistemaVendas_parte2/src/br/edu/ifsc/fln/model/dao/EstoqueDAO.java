package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.ESituacao;
import br.edu.ifsc.fln.model.domain.Estoque;
import br.edu.ifsc.fln.model.domain.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EstoqueDAO{

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean atualizar(Estoque estoque) {
        String sql = "UPDATE estoque SET quantidade=?, qtd_minima=?, qtd_maxima=?, situacao=? WHERE id_Modelo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setInt(2, estoque.getQtdMinima());
            stmt.setInt(3, estoque.getQtdMaxima());
            stmt.setString(4, estoque.getSituacao().name());
            stmt.setInt(5, estoque.getModelo().getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Modelo> listar() {
        String sql = "SELECT * FROM estoque e INNER JOIN Modelo p ON p.id = e.id_Modelo";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo Modelo = populateVO(resultado);
                retorno.add(Modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Modelo> listarPorEstoque(Estoque estoque) {
        String sql = "SELECT * FROM estoque e INNER JOIN Modelo p ON p.id = e.id_Modelo WHERE e.id_Modelo = ?";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, estoque.getModelo().getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo Modelo = populateVO(resultado);
                retorno.add(Modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    private Modelo populateVO(ResultSet rs) throws SQLException {
        Modelo Modelo = new Modelo();
        //Categoria categoria = new Categoria();
        //Modelo.setCategoria(categoria);
        Modelo.setId(rs.getInt("id"));
        Modelo.setNome(rs.getString("nome"));
        Modelo.setDescricao(rs.getString("descricao"));
        Modelo.setPreco(rs.getBigDecimal("preco"));
        Modelo.getEstoque().setQuantidade(rs.getInt("quantidade"));
        Modelo.getEstoque().setQtdMaxima(rs.getInt("qtd_maxima"));
        Modelo.getEstoque().setQtdMinima(rs.getInt("qtd_minima"));
        Modelo.getEstoque().setSituacao(Enum.valueOf(ESituacao.class, rs.getString("situacao")));
        return Modelo;
    }   
    

}
