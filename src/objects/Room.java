package objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class Room {

    private int numero;
    private int andar;
    private int maxHospedes;
    private int nmrCamas;
    private int nmrSuites;
    private boolean frigobar;
    private boolean tv;
    private double valorDiaria;
    private String descricao;

    public Room(int numero, int andar, int maxHospedes,int nmrCamas, int nmrSuites, boolean frigobar, boolean tv, double valor, String descricao) {
        this.numero = numero;
        this.andar = andar;
        this.maxHospedes = maxHospedes;
        this.nmrCamas = nmrCamas;
        this.nmrSuites = nmrSuites;
        this.frigobar = frigobar;
        this.tv = tv;
        this.valorDiaria = valor;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Room [numero=" + numero + ", andar=" + andar + ", valor=" + valorDiaria + ", maxHospedes=" + maxHospedes + ", nmrCamas=" + nmrCamas
                + ", descricao=" + descricao + ", nmrSuites=" + nmrSuites + ", tv=" + tv + ", frigobar=" + frigobar
                + "]";
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getMaxHospedes() {
        return maxHospedes;
    }

    public void setMaxHospedes(int maxHospedes) {
        this.maxHospedes = maxHospedes;
    }
    
    public int getnmrCamas() {
        return nmrCamas;
    }
    
    public void setnmrCamas(int nmrCamas) {
        this.nmrCamas = nmrCamas;
    }
        
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNmrSuites() {
        return nmrSuites;
    }

    public void setNmrSuites(int nmrSuites) {
        this.nmrSuites = nmrSuites;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isFrigobar() {
        return frigobar;
    }

    public void setFrigobar(boolean frigobar) {
        this.frigobar = frigobar;
    }
    
    

	
	
	
	public class Filter {
	    public static List<Object[]> searchRoom(List<Object> parametersSearch, String op) {
	        String sql;
	        List<Object[]> result = new ArrayList<>();

	        if (op.equals("NÚMERO")) {
	            sql = "SELECT * FROM quarto WHERE nmr_quarto LIKE ?";
	        } else if (op.equals("ANDAR")) {
	            sql = "SELECT * FROM quarto WHERE andar = ?";
	        } else if (op.equals("TODOS")) {
	            sql = "SELECT * FROM quarto";
	            parametersSearch = null; 
	        } else {
	            return result;
	        }

	        try (ResultSet rs = Conexao.executeQuery(sql, parametersSearch)) {
	            while (rs != null && rs.next()) {

	                Object[] linha = {
	                    rs.getInt("nmr_quarto"),
	                    rs.getInt("andar"),
	                    rs.getInt("max_hospedes"),
	                    rs.getInt("nmr_camas"),
	                    ("R$") + rs.getInt("valor_diaria"),
	                    
	                    
	                };
	            
	                
	                result.add(linha);
	               
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return result;
	    }
	}
	
	
	public class CadastrarQuarto {
	    public static int ListAddRoom(List<Object> parameters) {
	        String sql = "INSERT INTO quarto (nmr_quarto, andar, max_hospedes, nmr_camas, frigobar, tv, nmr_suites, valor_diaria, descricao) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        try {
	            int insertRoomRows = Conexao.executeUpdate(sql, parameters);
	            
	         
	            return insertRoomRows;
	        } catch (Exception e) {
	            System.err.println("Erro ao inserir quarto: " + e.getMessage());
	            e.printStackTrace();
	            return 0;
	        }
	    }
	}
}
	