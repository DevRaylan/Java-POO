package PRJ_DB_Alunos;

/**
 *
 * @author d.raylan
 */
public class Aluno {
    private int matricula;
    private String nome;
    private String cpf;
    private String curso;
    
    public Aluno(int matricula, String nome, String cpf, String curso){
        this.matricula= matricula;
        this.nome=nome;
        this.cpf=cpf;
        this.curso=curso;
        
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return  "\nmatricula:"+ matricula
                +"\nNome:" + nome 
                +"\nCPF:" + cpf 
                +"\nCurso:"+ curso;
    }
    
}
