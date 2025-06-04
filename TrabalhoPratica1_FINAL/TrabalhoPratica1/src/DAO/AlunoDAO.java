package DAO;

import Database.DatabaseConnection;
import Model.Aluno;
import Model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public static boolean insert(Aluno aluno) {
        String comandoSQL = "INSERT INTO alunos (nome, idade, cpf, curso_codigo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = DatabaseConnection.getConnection()
                .prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getIdade());
            ps.setLong(3, aluno.getCpf());
            ps.setInt(4, aluno.getCurso().getCodigo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                aluno.setMatricula(rs.getInt(1));
            }
            return true;

        }
        catch (SQLIntegrityConstraintViolationException excecao) {
            System.out.println("Erro: CPF duplicado.");
            return false;

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao inserir aluno: " + excecao.getMessage());
            return false;
        }

    }
    public static List<Aluno> getAll() {
        List<Aluno> listaAlunos = new ArrayList<>();
        String comandoSQL = "SELECT * FROM alunos";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso curso = CursoDAO.findById(rs.getInt("curso_codigo"));

                Aluno aluno = new Aluno(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getLong("cpf"),
                        curso
                );

                listaAlunos.add(aluno);
            }

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao listar alunos: " + excecao.getMessage());
        }

        return listaAlunos;

    }
    public static Aluno findById(int matricula) {
        String comandoSQL = "SELECT * FROM alunos WHERE matricula = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setInt(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Curso curso = CursoDAO.findById(rs.getInt("curso_codigo"));

                return new Aluno(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getLong("cpf"),
                        curso
                );
            }

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao buscar aluno: " + excecao.getMessage());
        }

        return null;

    }
    public static void update(Aluno aluno) {
        String comandoSQL = "UPDATE alunos SET nome = ?, idade = ?, cpf = ?, curso_codigo = ? WHERE matricula = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getIdade());
            ps.setLong(3, aluno.getCpf());
            ps.setInt(4, aluno.getCurso().getCodigo());
            ps.setInt(5, aluno.getMatricula()); // Correção aqui
            ps.executeUpdate();

        } catch (SQLException excecao) {
            System.out.println("Erro ao atualizar aluno: " + excecao.getMessage());
        }
    }
    public static boolean delete(Aluno aluno) {
        String comandoSQL = "DELETE FROM alunos WHERE matricula = ?";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(comandoSQL)) {
            ps.setInt(1, aluno.getMatricula());
            return ps.executeUpdate() > 0;

        } catch (SQLException excecao) {
            System.out.println("Erro ao remover aluno: " + excecao.getMessage());
        }

        return false;
    }
}