package DAO;

import Database.DatabaseConnection;
import Model.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public static void insert(Disciplina disciplina) {
        String comandoSQL = "INSERT INTO disciplinas (nome, carga_horaria, professor) VALUES (?, ?, ?)";

        try (PreparedStatement ps = DatabaseConnection.getConnection()
                .prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, disciplina.getNome());
            ps.setInt(2, disciplina.getCargaHoraria());
            ps.setString(3, disciplina.getProfessor());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                disciplina.setCodigo(rs.getInt(1));
            }

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao inserir disciplina: " + excecao.getMessage());
        }

    }
    public static List<Disciplina> getAll() {
        List<Disciplina> listaDisciplinas = new ArrayList<>();
        String comandoSQL = "SELECT * FROM disciplinas";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getInt("carga_horaria"),
                        rs.getString("professor")
                );
                listaDisciplinas.add(disciplina);
            }

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao listar disciplinas: " + excecao.getMessage());

        }
        return listaDisciplinas;

    }
    public static Disciplina findById(int codigo) {
        String comandoSQL = "SELECT * FROM disciplinas WHERE codigo = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Disciplina(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getInt("carga_horaria"),
                        rs.getString("professor")
                );
            }

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao buscar disciplina: " + excecao.getMessage());

        }
        return null;

    }
    public static void update(Disciplina disciplina) {
        String comandoSQL = "UPDATE disciplinas SET nome = ?, carga_horaria = ?, professor = ? WHERE codigo = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setString(1, disciplina.getNome());
            ps.setInt(2, disciplina.getCargaHoraria());
            ps.setString(3, disciplina.getProfessor());
            ps.setInt(4, disciplina.getCodigo());
            ps.executeUpdate();

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao atualizar disciplina: " + excecao.getMessage());
        }

    }
    public static boolean delete(Disciplina disciplina) {
        String comandoSQL = "DELETE FROM disciplinas WHERE codigo = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setInt(1, disciplina.getCodigo());
            return ps.executeUpdate() > 0;

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao deletar disciplina: " + excecao.getMessage());

        }
        return false;

    }
    public static void removerVinculosComCursos(int codigoDisciplina) {
        String comandoSQL = "DELETE FROM curso_disciplinas WHERE disciplina_codigo = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setInt(1, codigoDisciplina);
            ps.executeUpdate();

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao remover vínculos da disciplina com cursos: " + excecao.getMessage());
        }
    }
}