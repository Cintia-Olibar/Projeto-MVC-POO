package Controller;

import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import Model.Curso;
import Model.Disciplina;
import View.DisciplinaView;
import View.FuncaoUtilView;

import java.util.List;


public class DisciplinaController {

    public static void cadastrar() {
        String nome = DisciplinaView.NomeDisciplina();
        int cargaHoraria = DisciplinaView.CargaHoraria();
        String professor = DisciplinaView.NomeProfessor();

        for (Disciplina disciplina : DisciplinaDAO.getAll()) {
            if (disciplina.getNome().equalsIgnoreCase(nome) &&
                    disciplina.getProfessor().equalsIgnoreCase(professor)) {
                DisciplinaView.Mensagem("Já existe uma disciplina com esse nome e professor.");
                return;
            }

        }
        Disciplina novaDisciplina = new Disciplina(0, nome, cargaHoraria, professor);
        DisciplinaDAO.insert(novaDisciplina);
        DisciplinaView.Mensagem("Disciplina cadastrada com sucesso.");

    }
    public static void atualizar() {
        int codigo = FuncaoUtilView.lerNumero("Informe o código da disciplina: ");
        Disciplina disciplina = null;

        for (Disciplina d : DisciplinaDAO.getAll()) {
            if (d.getCodigo() == codigo) {
                disciplina = d;
                break;
            }

        }
        if (disciplina == null) {
            DisciplinaView.Mensagem("Disciplina com código " + codigo + " não encontrada.");
            return;

        }
        int opcao;
        do {
            DisciplinaView.MenuAtualizar();
            opcao = DisciplinaView.Opcao();

            if (opcao == 1) {
                disciplina.setNome(DisciplinaView.NomeDisciplina());

            }
            if (opcao == 2) {
                disciplina.setCargaHoraria(DisciplinaView.CargaHoraria());

            }
            if (opcao == 3) {
                disciplina.setProfessor(DisciplinaView.NomeProfessor());

            }
            if (opcao != 4) {
                DisciplinaDAO.update(disciplina);
                DisciplinaView.Mensagem("Dados da disciplina atualizados com sucesso.");
            }

        } while (opcao != 4);

    }
    public static void remover() {
        int codigo = FuncaoUtilView.lerNumero("Informe o código da disciplina: ");
        Disciplina disciplina = null;

        for (Disciplina disciplinaAtual : DisciplinaDAO.getAll()) {
            if (disciplinaAtual.getCodigo() == codigo) {
                disciplina = disciplinaAtual;
                break;
            }

        }
        if (disciplina == null) {
            DisciplinaView.Mensagem("Disciplina com código " + codigo + " não encontrada.");
            return;

        }
        for (Curso curso : CursoDAO.getAll()) {
            List<Disciplina> disciplinasDoCurso = curso.getDisciplinas();
            boolean disciplinaEstaNoCurso = false;

            for (Disciplina disciplinaDoCurso : disciplinasDoCurso) {
                if (disciplinaDoCurso.getCodigo() == codigo) {
                    disciplinaEstaNoCurso = true;
                    break;
                }

            }
            if (disciplinaEstaNoCurso && disciplinasDoCurso.size() == 1) {
                DisciplinaView.Mensagem("Não é possível remover esta disciplina. O curso \"" + curso.getNome() + "\" ficaria sem nenhuma disciplina cadastrada.");
                return;
            }

        }
        DisciplinaDAO.removerVinculosComCursos(codigo);

        boolean disciplinaRemovida = DisciplinaDAO.delete(disciplina);
        if (disciplinaRemovida) {
            DisciplinaView.Mensagem("Disciplina removida com sucesso.");

        }
        else {
            DisciplinaView.Mensagem("Erro ao remover a disciplina.");
        }

    }
    public static void consultar() {
        DisciplinaView.Mensagem("\n=== CONSULTA DE DISCIPLINAS ===");
        DisciplinaView.Mensagem("1 - Consultar todas as disciplinas");
        DisciplinaView.Mensagem("2 - Consultar uma disciplina específica");
        int opcao = DisciplinaView.Opcao();

        if (opcao == 1) {
            DisciplinaView.ListarDisciplinas();

        }
        else if (opcao == 2) {
            int codigo = FuncaoUtilView.lerNumero("Informe o código da disciplina: ");
            Disciplina disciplina = null;

            for (Disciplina d : DisciplinaDAO.getAll()) {
                if (d.getCodigo() == codigo) {
                    disciplina = d;
                    break;
                }

            }
            if (disciplina != null) {
                DisciplinaView.MostrarDisciplina(disciplina);

            }
            else {
                DisciplinaView.Mensagem("Disciplina com código " + codigo + " não encontrada.");
            }
        }
        else {
            DisciplinaView.Mensagem("Opção inválida.");
        }

    }
}
