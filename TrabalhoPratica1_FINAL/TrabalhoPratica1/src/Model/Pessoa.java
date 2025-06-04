package Model;

public class Pessoa {

    private String nome;
    private int idade;
    private long cpf;

    public Pessoa(String nome, int idade, long cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;

    }
    public String getNome() {
        return nome;

    }
    public int getIdade() {
        return idade;

    }
    public long getCpf() {
        return cpf;

    }
    public void setNome(String nome) {
        this.nome = nome;

    }
    public void setIdade(int idade) {
        this.idade = idade;

    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
}