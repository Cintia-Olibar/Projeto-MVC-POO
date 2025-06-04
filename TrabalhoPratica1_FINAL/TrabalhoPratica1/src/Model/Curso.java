package Model;

import java.util.ArrayList;
import java.util.List;

public class Curso {

    private int codigo;
    private String nome;
    private String turno;
    private List<Disciplina> disciplinas;

    public Curso(int codigo, String nome, String turno) {
        this.codigo = codigo;
        this.nome = nome;
        this.turno = turno;
        this.disciplinas = new ArrayList<>();

    }
    public int getCodigo() {
        return codigo;

    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;

    }
    public String getNome() {
        return nome;

    }
    public void setNome(String nome) {
        this.nome = nome;

    }
    public String getTurno() {
        return turno;

    }
    public void setTurno(String turno) {
        this.turno = turno;

    }
    public List<Disciplina> getDisciplinas() {
        return disciplinas;

    }
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;

    }
    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

}
