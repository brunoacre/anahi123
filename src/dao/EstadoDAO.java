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
import model.Curso;
import model.Estado;

/**
 *
 * @author bruno
 */
public class EstadoDAO {
    
     public List<Estado> recuperaTodos() {
        try {
            Connection conn = Conexao.getConexao();
            String sql = "SELECT * FROM estados ORDER BY nome ASC;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Estado> lista = new ArrayList();
            while (rs.next()) {
                Estado x = new Estado();
                x.setIdEstados(rs.getInt(1));
                x.setNome(rs.getString(2));
                x.setUf(rs.getString(3));
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
    
}
