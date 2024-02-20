package PRJ_DB_Alunos;

/**
 *
 * @author d.raylan
 */

public class MainApp{
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();

        // Exemplo  de um aluno
        Aluno novoAluno = new Aluno(1, "raylan", "001.233.554-19", " Web");
        alunoDAO.adicionarAluno(novoAluno);

        // Exemplo de busca de um aluno pelo número de matrícula
        Aluno alunoEncontrado = alunoDAO.buscarAluno(1);
        if (alunoEncontrado != null) {
            System.out.println("Aluno encontrado: " + alunoEncontrado);
        } else {
            System.out.println("Aluno não encontrado.");
        }

        // Exemplo de atualização de informações de um aluno
        Aluno alunoAtualizado = new Aluno(1, "João Silva", "0987654321", "Ciência da Computação");
        alunoDAO.atualizarAluno(alunoAtualizado);

        // Exemplo de exclusão de um aluno
        alunoDAO.excluirAluno(1);

        // Feche a conexão com o banco de dados quando terminar
        alunoDAO.fecharConexao();
    }
}

