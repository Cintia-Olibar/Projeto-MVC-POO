package Controller;

import DAO.AlunoDAO;
import DAO.CursoDAO;
import Model.Aluno;
import Model.Curso;
import View.AlunoView;
import View.FuncaoUtilView;

import java.util.List;

public class AlunoController {


    public static void cadastrar() {
        String nome = AlunoView.Nome();
        int idade = AlunoView.Idade();
        long cpf = AlunoView.Cpf();

        AlunoView.Mensagem("O aluno deve estar cadastrado em um curso.");
        AlunoView.ListarCursos();

        int codigoCurso = AlunoView.CodigoCurso();

        List<Curso> cursos = CursoDAO.getAll();
        Curso cursoEncontrado = null;

        for (Curso curso : cursos) {
            if (curso.getCodigo() == codigoCurso) {
                cursoEncontrado = curso;
                break;
            }
        }

        if (cursoEncontrado == null) {
            AlunoView.Mensagem("Curso com código " + codigoCurso + " não encontrado.");
            return;
        }

        Aluno novoAluno = new Aluno(0, nome, idade, cpf, cursoEncontrado);
        boolean inserido = AlunoDAO.insert(novoAluno);

        if (inserido) {

            AlunoView.Mensagem("Aluno cadastrado com sucesso no curso: " + cursoEncontrado.getNome());

        }
        else {
            AlunoView.Mensagem("Erro: CPF já cadastrado.");
        }

    }
    public static void atualizar() {
        int matriculaAluno = FuncaoUtilView.lerNumero("Informe a matrícula do aluno: ");
        List<Aluno> alunos = AlunoDAO.getAll();
        Aluno aluno = null;

        for (Aluno a : alunos) {
            if (a.getMatricula() == matriculaAluno) {
                aluno = a;
                break;
            }

        }
        if (aluno == null) {
            AlunoView.Mensagem("Aluno com matrícula " + matriculaAluno + " não encontrado.");
            return;

        }
        int opcao;

        do {
            AlunoView.MenuAtualizar();
            opcao = AlunoView.Opcao();

            if (opcao == 1){
                aluno.setNome(AlunoView.Nome());

            }
            if (opcao == 2){
                aluno.setIdade(AlunoView.Idade());
            }

            if (opcao == 3){
                aluno.setCpf(AlunoView.Cpf());
            }

            if (opcao == 4) {
                AlunoView.ListarCursos();

                int novoCodigo = AlunoView.CodigoCurso();
                Curso novoCurso = null;

                for (Curso curso : CursoDAO.getAll()) {
                    if (curso.getCodigo() == novoCodigo) {
                        novoCurso = curso;
                        break;
                    }

                }
                if (novoCurso != null) {
                    aluno.setCurso(novoCurso);
                    AlunoView.Mensagem("Curso atualizado para: " + novoCurso.getNome());

                }
                else {
                    AlunoView.Mensagem("Curso com código " + novoCodigo + " não encontrado.");
                }

            }
            if (opcao < 0 || opcao > 4) {
                AlunoView.Mensagem("Opção inválida. Tente novamente.");
            }

        }
        while (opcao != 0);

        AlunoDAO.update(aluno);
        AlunoView.Mensagem("Dados do aluno atualizados com sucesso.");

    }
    public static void remover() {
        int matriculaAluno = FuncaoUtilView.lerNumero("Informe a matrícula do aluno: ");
        List<Aluno> alunos = AlunoDAO.getAll();
        Aluno aluno = null;

        for (Aluno a : alunos) {
            if (a.getMatricula() == matriculaAluno) {
                aluno = a;
                break;
            }

        }
        if (aluno == null) {
            AlunoView.Mensagem("Aluno com matrícula " + matriculaAluno + " não encontrado.");
            return;

        }
        boolean removido = AlunoDAO.delete(aluno);
        if (removido) {
            AlunoView.Mensagem("Aluno " + aluno.getNome() + " (Matrícula: " + aluno.getMatricula() + ") removido com sucesso.");

        }
        else {
            AlunoView.Mensagem("Erro ao remover o aluno.");
        }

    }
    public static void consultar() {
        AlunoView.Mensagem("\n=== CONSULTA DE ALUNOS: ");
        AlunoView.Mensagem("1 - Consultar todos os alunos");
        AlunoView.Mensagem("2 - Consultar um aluno específico");
        int opcao = AlunoView.Opcao();

        if (opcao == 1) {
            List<Aluno> alunos = AlunoDAO.getAll();
            if (alunos.isEmpty()) {
                AlunoView.Mensagem("Nenhum aluno cadastrado.");
                return;

            }
            AlunoView.Mensagem("\n=== Lista de Alunos Cadastrados ===");
            for (Aluno aluno : alunos) {
                AlunoView.MostrarAluno(aluno);
            }

        }
        else if (opcao == 2) {
            int matriculaAluno = FuncaoUtilView.lerNumero("Informe a matrícula do aluno: ");
            List<Aluno> alunos = AlunoDAO.getAll();
            Aluno aluno = null;

            for (Aluno a : alunos) {
                if (a.getMatricula() == matriculaAluno) {
                    aluno = a;
                    break;
                }
            }
            if (aluno != null) {
                AlunoView.Mensagem("\n=== Dados do aluno: ===");
                AlunoView.MostrarAluno(aluno);

            }
            else {
                AlunoView.Mensagem("Aluno com matrícula " + matriculaAluno + " não encontrado.");
            }
        }
        else {
            AlunoView.Mensagem("Opção inválida.");
        }
    }
}
