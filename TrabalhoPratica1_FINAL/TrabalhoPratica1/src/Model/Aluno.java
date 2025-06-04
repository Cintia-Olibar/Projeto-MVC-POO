package Model;

public class Aluno extends Pessoa {
    private int matricula;
    private Curso curso;

    public Aluno(int matricula, String nome, int idade, long cpf, Curso curso) {
        super(nome, idade, cpf);
        this.matricula = matricula;
        this.curso = curso;


    }
    public int getMatricula() {
        return matricula;

    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;

    }
    public Curso getCurso() {
        return curso;

    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
