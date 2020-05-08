/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Conexao {

    public static Connection getConexao(){
        //nome do driver do mysql
        String driver = "org.gjt.mm.mysql.Driver";
        //url do banco de dados
        String url = "jdbc:mysql://localhost:3306/ciec?useTimezone=true&serverTimezone=UTC";
        //usuário e senha de acesso ao banco
        String user = "root";
        String password = "";

        try {
            //carregar o Driver
            Class.forName(driver);
            //criar a conexão com o banco, usando um gerente
            Connection conexao = 
                    DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso!");
            //retornar a conexao
            return conexao;
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
