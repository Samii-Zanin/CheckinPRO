package objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class Room {
    private int id;
    private int numero;
    private String tipo;
    private String descricao;
    private int capacidade;
    private int preco_diaria;
    private String status;
    private int camas;
    private int andar;

    public Room(int id, int numero, String tipo, String descricao, int capacidade, int preco_diaria, 
                String status, int camas, int andar) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.preco_diaria = preco_diaria;
        this.status = status;
        this.camas = camas;
        this.andar = andar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getPreco_diaria() {
        return preco_diaria;
    }

    public void setPreco_diaria(int preco_diaria) {
        this.preco_diaria = preco_diaria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCamas() {
        return camas;
    }

    public void setCamas(int camas) {
        this.camas = camas;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    @Override
    public String toString() {
        return "Room [numero=" + numero + ", tipo=" + tipo + ", descricao=" + descricao + 
               ", capacidade=" + capacidade + ", preco_diaria=" + preco_diaria + 
               ", status=" + status + ", camas=" + camas + ", andar=" + andar + "]";
    }

    public static List<Room> filter(String op, String parametersSearch) {
        String sql = "";
        List<Room> result = new ArrayList<>();

        if (op.equals("NÚMERO")) {
            sql = "SELECT * FROM quarto WHERE nmr_quarto LIKE ?";
        } else if (op.equals("ANDAR")) {
            sql = "SELECT * FROM quarto WHERE andar = ?";
        } else if (op.equals("TODOS")) {
            sql = "SELECT * FROM quarto";
        }
    
        try {
            ArrayList<Object> params = new ArrayList<>();
            if (!op.equals("TODOS")) {
                params.add(parametersSearch);
            }
            
            ResultSet rs = Conexao.executeQuery(sql, params);
            while (rs != null && rs.next()) {
                Room room = new Room(
                    rs.getInt("id"),
                    rs.getInt("nmr_quarto"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getInt("capacidade"),
                    rs.getInt("preco_diaria"),
                    rs.getString("status"),
                    rs.getInt("camas"),
                    rs.getInt("andar")
                );
                result.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Room(int id) {
		super();
		this.id = id;
	}

	public static int addRoom(int nmrQuarto, int andar, int valorDiaria, int maxHospedes, 
                            int nmrCamas, String descricao, String tipo) {
        String sql = "INSERT INTO quarto (nmr_quarto, tipo, descricao, capacidade, preco_diaria, camas, andar) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(nmrQuarto);
        parameters.add(tipo);
        parameters.add(descricao);
        parameters.add(maxHospedes);
        parameters.add(valorDiaria);
        parameters.add(nmrCamas);
        parameters.add(andar);
        
        try {
            return Conexao.executeUpdate(sql, parameters);
        } catch (Exception e) {
            System.err.println("Erro ao inserir quarto: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static int deleteData(int id) {
        String sql = "DELETE FROM quarto WHERE id = ?";
        
        ArrayList<Object> parameter = new ArrayList<>();  
        parameter.add(id);
        
        try {
            return Conexao.executeUpdate(sql, parameter);
        } catch (Exception e) {
            System.err.println("Erro ao deletar quarto: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static Room searchRoomById(int id) {
        String sql = "SELECT * FROM quarto WHERE id = ?";
        ArrayList<Object> parameter = new ArrayList<>();
        parameter.add(id);
        
        try {
            ResultSet rs = Conexao.executeQuery(sql, parameter);
            if (rs != null && rs.next()) {
                return new Room(
                    rs.getInt("id"),
                    rs.getInt("nmr_quarto"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getInt("capacidade"),
                    rs.getInt("preco_diaria"),
                    rs.getString("status"),
                    rs.getInt("camas"),
                    rs.getInt("andar")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static int editRoom(int id, int nmrQuarto, String tipo, String descricao, int maxHospedes, 
                             int valorDiaria , int nmrCamas, int andar) {
        ArrayList<Object> parameters = new ArrayList<>();  
        parameters.add(nmrQuarto);
        parameters.add(tipo);
        parameters.add(descricao);
        parameters.add(maxHospedes);
        parameters.add(valorDiaria);
        parameters.add(nmrCamas);
        parameters.add(andar);
        parameters.add(id);

        String sql = "UPDATE quarto SET nmr_quarto = ?, tipo = ?, descricao = ?, capacidade = ?, " +
                     "preco_diaria = ?, camas = ?, andar = ? WHERE id = ?";
        
        try {
            return Conexao.executeUpdate(sql, parameters);
        } catch (Exception e) {
            System.err.println("Erro ao editar quarto: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    
    public static int searchPrice(int id) {
        String sql = "SELECT preco_diaria FROM quarto WHERE id = ?";
        ArrayList<Object> parameter = new ArrayList<>(); 
        parameter.add(id);

        try {
            ResultSet rs = Conexao.executeQuery(sql, parameter);
            if (rs != null && rs.next()) {  
                return rs.getInt("preco_diaria");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar preço: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
    
}