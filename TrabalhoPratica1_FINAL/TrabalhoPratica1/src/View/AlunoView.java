package View;

import Model.Aluno;

public class AlunoView {

    public static String Nome() {
        return FuncaoUtilView.lerNome("aluno");

    }
    public static int Idade() {
        return FuncaoUtilView.lerIdade();

    }
    public static long Cpf() {
        return FuncaoUtilView.lerCpf();

    }
    public static int CodigoCurso() {
        return FuncaoUtilView.lerNumero("Informe o código do curso: ");

    }
    public static void ListarCursos() {
        CursoView.ListarCursos();

    }
    public static int Opcao() {
        return FuncaoUtilView.lerOpcao("Escolha uma opção: ");

    }
    public static void Mensagem(String mensagem) {
        FuncaoUtilView.mensagem(mensagem);

    }
    public static void MenuAtualizar() {
        System.out.println("\n=== Atualização de Aluno ===");
        System.out.println("1 - Alterar Nome");
        System.out.println("2 - Alterar Idade");
        System.out.println("3 - Alterar CPF");
        System.out.println("4 - Trocar Curso");
        System.out.println("0 - Sair");

    }
    public static void MostrarAluno(Aluno aluno) {
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Idade: " + aluno.getIdade());
        System.out.println("CPF: " + aluno.getCpf());
        System.out.println("Curso: " + aluno.getCurso().getNome());
        System.out.println("----------------------------");
    }

}
