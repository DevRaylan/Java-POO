package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Veiculo;
import br.edu.ifsc.fln.model.domain.Modelo;
import br.edu.ifsc.fln.model.domain.Cor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeiculoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

     public boolean inserir(Veiculo veiculo) {
        final String sql = "INSERT INTO Veiculo(placa, observacoes, id_modelo, id_cor) VALUES(?,?,?,?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getObservacoes());
            stmt.setInt(3, veiculo.getModelo().getId());
            stmt.setInt(4, veiculo.getCor().getId()); 
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

        public boolean alterar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET placa=?, observacoes=?, id_modelo=?, id_cor=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getObservacoes());
            stmt.setInt(3, veiculo.getModelo().getId());
            stmt.setInt(4, veiculo.getCor().getId()); // Adicione a cor aqui
            stmt.setInt(5, veiculo.getId()); // Adicione o ID do veículo
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Veiculo veiculo) {
        String sql = "DELETE FROM veiculo WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Veiculo> listar() {
        String sql = "SELECT * FROM VEICULO";
        List<Veiculo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Veiculo veiculo = populateVO(resultado);
                retorno.add(veiculo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }


    public Veiculo buscar(Veiculo veiculo) {
        String sql = "SELECT * FROM VEICULO WHERE id = ?;";
        Veiculo retorno = new Veiculo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Método para mapear os dados de um produto (relacional) para objeto, contemplando ou não a categoria.
     * @param rs
     * @param comModelo

     * @return
     * @throws SQLException 
     */
    private Veiculo populateVO(ResultSet rs, boolean comModelo) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(rs.getInt("id"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setObservacoes(rs.getString("observacoes"));

        if (comModelo) {
            Modelo modelo = new Modelo();
            modelo.setId(rs.getInt("id_modelo"));
            ModeloDAO modeloDAO = new ModeloDAO();
            modeloDAO.setConnection(connection);
            modelo = modeloDAO.buscar(modelo);
            veiculo.setModelo(modelo);
        }

        return veiculo;
    }

 private Veiculo populateVO(ResultSet rs) throws SQLException {
    Veiculo veiculo = new Veiculo();
    veiculo.setId(rs.getInt("id"));
    veiculo.setPlaca(rs.getString("placa"));
    veiculo.setObservacoes(rs.getString("observacoes"));

    // Popule o modelo do veículo
    Modelo modelo = new Modelo();
    modelo.setId(rs.getInt("id_modelo"));
    ModeloDAO modeloDAO = new ModeloDAO();
    modeloDAO.setConnection(connection);
    modelo = modeloDAO.buscar(modelo);
    veiculo.setModelo(modelo);

    // Popule a cor do veículo
    Cor cor = new Cor();
    cor.setId(rs.getInt("id_cor"));
    CorDAO corDAO = new CorDAO();
    corDAO.setConnection(connection);
    cor = corDAO.buscar(cor);
    veiculo.setCor(cor);

    return veiculo;
}


}
