package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Marca;
import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.PessoaFisica;
import br.edu.ifsc.fln.model.domain.PessoaJuridica;
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

    public boolean inserir(Modelo modelo) {
        final String sql = "INSERT INTO modelo(descricao, id_marca, id_cliente) VALUES(?,?,?);";
        final String sqlEstoque = "INSERT INTO motor(id_modelo) (SELECT max(id) FROM modelo);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //registra o modelo
            stmt.setString(1, modelo.getDescricao());
            stmt.setInt(4, modelo.getMarca().getId());
            stmt.setInt(5, modelo.getCliente().getId());
            stmt.execute();
            //registra o estoque do modelo imediatamente
            stmt = connection.prepareStatement(sqlEstoque);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Modelo modelo) {
        String sql = "UPDATE modelo SET descricao=?, id_marca=?, id_cliente=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, modelo.getDescricao());
            stmt.setInt(2, modelo.getMarca().getId());
            stmt.setInt(3, modelo.getCliente().getId());
            stmt.setInt(4, modelo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Modelo modelo) {
        String sql = "DELETE FROM modelo WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, modelo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Modelo> listar() {
        String sql =  "SELECT m.id as modelo_id, m.nome as modelo_nome, m.descricao as modelo_descricao, "
                + "m1.id as marca_id, m1.descricao as marca_descricao, c.id as cliente_id "
                + "FROM modelo m INNER JOIN marca m1 ON m1.id = m.id_marca "
                + "INNER JOIN cliente c on c.id = m.id_cliente;";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVO(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Modelo> listagem() {
        String sql = "select * from modelo m "
                +       "inner join marca m1 on m1.id = m.id_marca " 
                +       "inner join cliente c on c.id = m.id_cliente " 
                +       "left join juridico j on j.id_cliente = c.id "
                +       "left join fisico f on f.id_cliente = f.id;";
                List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVOFull(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Modelo> listarPorMarca(Marca marca) {
        String sql =  "SELECT m.id as modelo_id, m.nome as modelo_nome, m.descricao as modelo_descricao,"
                + "m1.id as marca_id, m1.descricao as marca_descricao "
                + "FROM modelo m INNER JOIN marca m1 ON m1.id = m.id_marca WHERE m1.id = ?;";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, marca.getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVO(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Modelo buscar(Modelo modelo) {
        String sql =  "SELECT m.id as modelo_id, m.nome as modelo_nome, m.descricao as modelo_descricao, "
                + "m1.id as marca_id, m1.descricao as marca_descricao "
                + "FROM modelo m INNER JOIN marca m1 ON m1.id = m.id_marca WHERE m.id = ?;";
        Modelo retorno = new Modelo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, modelo.getId());
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
        Modelo modelo = new Modelo();
        Marca marca = new Marca();
        modelo.setMarca(marca);
        
        modelo.setId(rs.getInt("modelo_id"));
        modelo.setDescricao(rs.getString("modelo_descricao"));
        marca.setId(rs.getInt("marca_id"));
        int idCliente = rs.getInt("cliente_id");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.setConnection(connection);
        Cliente cliente = clienteDAO.buscar(idCliente);
        modelo.setCliente(cliente);
        return modelo;
    }   
    
    
    private Modelo populateVOFull(ResultSet rs) throws SQLException {
        Modelo modelo = new Modelo();
        Marca marca = new Marca();
        modelo.setMarca(marca);
        
        modelo.setId(rs.getInt(1));
        modelo.setDescricao(rs.getString(2));
        marca.setId(rs.getInt(3));
        Cliente cliente;
        if (rs.getString("cpf") == null) {
            cliente = new PessoaJuridica();
            ((PessoaJuridica)cliente).setCnpj(rs.getString(14));
        } else {
            cliente = new PessoaFisica();
            ((PessoaFisica)cliente).setCpf(rs.getString(16));
           // ((PessoaFisica)cliente).setdNasc(rs.getData(17));
        }
        cliente.setId(rs.getInt(6));
        cliente.setNome(rs.getString(10));
        cliente.setEmail(rs.getString(11));
        cliente.setFone(rs.getString(12));
        modelo.setCliente(cliente);
//        int idCliente = rs.getInt("cliente_id");
//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.buscar(idCliente);
//        modelo.setCliente(cliente);
        return modelo;
    } 

}
