/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


/**
 *
 * @author caio
 */
public class SequenceManager {
        
    public int getValue(String sequencia)throws Exception{
        int resultado = 0;
        Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost\\MSSQLSERVER2019E:1433;databaseName=Loja;encrypt=true;trustServerCertificate=true",
        "loja", "loja");
        String sql = "SELECT NEXT VALUE FOR "+sequencia+" as proximoId";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
            resultado = rs.getInt("proximoId");
        return resultado;
    } 
}
