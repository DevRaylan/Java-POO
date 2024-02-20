package PRJ_DB_Alunos;

/**
 *
 * @author d.raylan
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {
    private Connection conexao;

    // Construtor para conectar ao banco de dados
    public AlunoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Troque pelo driver JDBC do seu banco de dados
            String url = "jdbc:mysql://localhost:3306/bd_alunos"; // Substitua pela sua URL de conexão
            String usuario = "root";
            String senha = "root1234";
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    // Método para adicionar um aluno
    public void adicionarAluno(Aluno aluno) {
        String sql = "INSERT INTO Aluno (matricula, nome, cpf, curso) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getCpf());
            stmt.setString(4, aluno.getCurso());
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Método para buscar um aluno pelo ID
    public Aluno buscarAluno(int matricula) {
        String sql = "SELECT * FROM Aluno WHERE matricula = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("matricula");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String curso = rs.getString("curso");
                return new Aluno(id, nome, cpf, curso);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // Método para atualizar informações de um aluno
    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE Aluno SET nome = ?, cpf = ?, curso = ? WHERE matricula = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getCurso());  //stmt.setString finalização com banco de dados
            stmt.setInt(4, aluno.getMatricula());
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Método para excluir um aluno
    public void excluirAluno(int matricula) {
        String sql = "DELETE FROM Aluno WHERE matricula = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Fechar a conexão com o banco //
    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
        }
    }
   
}