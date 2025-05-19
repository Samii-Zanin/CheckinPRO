package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Conexao { // Adicionar senha e trocar a porta
    private static final String URL = "jdbc:mysql://localhost:3306/patp_ads3";
    private static final String USER = "root"; 
    private static final String PASSWORD = "12345"; //2424
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados.");
        }
    }



    public static ResultSet executeQuery(String sql, List<Object> parametros) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            

            // Prepara o statement SQL
            stmt = conn.prepareStatement(sql);
            
            // Define os parâmetros, se houver
            if (parametros != null) {
                for (int i = 0; i < parametros.size(); i++) {
                    stmt.setObject(i + 1, parametros.get(i));
                }
            }
            
            // Executa a consulta 
            rs = stmt.executeQuery();
             
            
            return rs;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // Não fechamos a conexão aqui porque o ResultSet pode ser usado posteriormente
            // O chamador deve fechar a conexão, o statement e o ResultSet
        }
    }

    public static int executeUpdate(String sql, List<Object> parametros) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Conecta ao banco de dados
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // Prepara o statement SQL
            stmt = conn.prepareStatement(sql);
            
            // Define os parâmetros, se houver
            if (parametros != null) {
                for (int i = 0; i < parametros.size(); i++) {
                    stmt.setObject(i + 1, parametros.get(i));
                }
            }
            
            // Executa a atualização (INSERT, UPDATE, DELETE)
            return stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            // Fecha o statement e a conexão
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //seja louvado o gpt e deepseek kkkkkk video de meia hora n tinha dado mt certo
    }}
