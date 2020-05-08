/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Curso;

/**
 *
 * @author bruno
 */
public class CursoDAO {

    //método para inserção no BD
    public boolean inserirCurso(Curso c) {
        try {
            //criar conexão com o BD
            Connection conn
                    = Conexao.getConexao();
            //Definir a instrução SQL
            String sql = "INSERT INTO cursos (nomeCurso, descricao) VALUES(?, ?);";
            //criar o statement (transporte)
            PreparedStatement ps
                    = conn.prepareStatement(sql);
            //definir os valores dos parametros
            ps.setString(1, c.getNomeCurso());
            ps.setString(2, c.getDescricao());
            //executar a instrução SQL no BD
            ps.executeUpdate();
            //fechar o transporte e a conexão
            ps.close();
            conn.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //método responsável pela pesquisa de todos os cursos
    public List<Curso> recuperaTodos() {
        try {
            //estabelecer a conexão com o BD
            Connection conn = Conexao.getConexao();
            //instrução SQL de consulta
            String sql = "SELECT * FROM cursos ORDER BY nomeCurso ASC;";
            //criar statement para execução SQL
            PreparedStatement ps = conn.prepareStatement(sql);
            //criar uma variavel de resultado da consulta
            //e executar o SQL no BD
            ResultSet rs = ps.executeQuery();
            //criar uma lista do retorno
            List<Curso> lista = new ArrayList();
            //para cada linha retornada do BD, criar um
            //objeto em java e atribuir valores do BD
            while (rs.next()) {
                //criar um objeto 
                Curso x = new Curso();
                //definir os valores para o objeto
                x.setIdCurso(rs.getInt(1));
                x.setNomeCurso(rs.getString(2));
                x.setDescricao(rs.getString(3));
                //adicionar na lista
                lista.add(x);
            }
            //retornar a lista
            return lista;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Curso> pesquisaCursoPorNome(String z) {
        try {
            Connection conn = Conexao.getConexao();
            String sql = "SELECT * FROM cursos WHERE nomeCurso LIKE ? ORDER BY nomeCurso ASC;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, z + "%");
            ResultSet rs = ps.executeQuery();
            List<Curso> lista = new ArrayList();
            while (rs.next()) {
                Curso x = new Curso();
                x.setIdCurso(rs.getInt(1));
                x.setNomeCurso(rs.getString(2));
                x.setDescricao(rs.getString(3));
                lista.add(x);
            }
            return lista;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //método que recebe o ID e realiza exclusão no BD

    public boolean excluir(int id) {
        try {
            Connection conn
                    = Conexao.getConexao();
            String sql = "DELETE FROM cursos "
                    + "WHERE idCurso = ?;";

            PreparedStatement ps
                    = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            conn.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //método responsável pelo update
    public boolean atualizar(Curso c) {
        try {
            Connection conn
                    = Conexao.getConexao();
            String sql = "UPDATE cursos SET nomeCurso = ?, descricao = ? WHERE idCurso = ?;";

            PreparedStatement ps
                    = conn.prepareStatement(sql);
            ps.setString(1, c.getNomeCurso());
            ps.setString(2, c.getDescricao());
            ps.setInt(3, c.getIdCurso());

            ps.executeUpdate();
            ps.close();
            conn.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
