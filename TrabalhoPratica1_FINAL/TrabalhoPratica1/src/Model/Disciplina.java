package Model;

public class Disciplina {
    private int codigo;
    private String nome;
    private int cargaHoraria;
    private String professor;

    public Disciplina(int codigo, String nome, int cargaHoraria, String professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;

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
    public int getCargaHoraria() {
        return cargaHoraria;

    }
    public String getProfessor() {
        return professor;

    }
    public void setNome(String nome) {
        this.nome = nome;

    }
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;

    }
    public void setProfessor(String professor) {
        this.professor = professor;
    }

}
