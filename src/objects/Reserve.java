package objects;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import conexao.Conexao;

public class Reserve {
    
    private int id;
    private String checkinDate;
    private String checkoutDate;
    private Room id_Room;
    private Client id_client;
    private double valorTotal;
    private String status;
    private int numberOfClient;
    private Client clientName; 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Room getId_Room() {
        return id_Room;
    }

    public void setId_Room(Room id_Room) {
        this.id_Room = id_Room;
    }

    public Client getId_client() {
        return id_client;
    }

    public void setId_client(Client id_client) {
        this.id_client = id_client;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfClient() {
        return numberOfClient;
    }

    public void setNumberOfClient(int numberOfClient) {
        this.numberOfClient = numberOfClient;
    }

    public Client getClientName() {
        return clientName;
    }

    public void setClientName(Client clientName) {
        this.clientName = clientName;
    }

    public Reserve(int id, String checkinDate, String checkoutDate, Room id_Room, Client id_client, double valorTotal,
            String status, int numberOfClient, Client clientName) {
        super();
        this.id = id;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.id_Room = id_Room;
        this.id_client = id_client;
        this.valorTotal = valorTotal;
        this.status = status;
        this.numberOfClient = numberOfClient;
        this.clientName = clientName;
    }
    
    @Override
	public String toString() {
		return "Reserve [id=" + id + ", checkinDate=" + checkinDate + ", checkoutDate=" + checkoutDate + ", id_Room="
				+ id_Room + ", id_client=" + id_client + ", valorTotal=" + valorTotal + ", status=" + status
				+ ", numberOfClient=" + numberOfClient + ", clientName=" + clientName + "]";
	}
    
    public static boolean quartoDisponivel(int nmrQuarto, String checkinDate, String checkoutDate) {
        String sql = "SELECT COUNT(*) " +
                 "FROM reserva r " +
                 "JOIN quarto q ON r.id_quarto = q.id " +
                 "WHERE q.id = ? " +
                 "AND NOT (r.data_checkout <= ? OR r.data_checkin >= ?)";
        
        List<Object> params = new ArrayList<>();
        params.add(nmrQuarto);
        params.add(checkinDate);         
        params.add(checkoutDate);        

        try {
            ResultSet rs = (ResultSet) Conexao.executeQuery(sql, params);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            } else {
                System.err.println("Erro: Resultado vazio na verificação de disponibilidade.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar disponibilidade do quarto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public static Integer clienteExistente(String cpf) {
        String sql = "SELECT * FROM cliente where cpf = ?";
        List<Object> params = new ArrayList<>();
        params.add(cpf);
        try {
            ResultSet rs = (ResultSet) Conexao.executeQuery(sql, params);
            if(rs.next()) {
                int clientId = rs.getInt("id_cliente");
                return clientId;
            } else {
                System.err.println("Erro: Resultado vazio na verificação de cliente existente.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar cliente existente: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public static int adicionarReserva(int nmrQuarto, int clientId, String checkinDate, String checkoutDate, int qntPessoas, int valor) {
        Date data_reserva = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        String formattedData_reserva = sdf.format(data_reserva);
        
        String sql = "INSERT INTO reserva (id_cliente, id_quarto, data_checkin, data_checkout, quantidade_pessoas, data_reserva, valor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        List<Object> params = new ArrayList<>();
        params.add(clientId);
        params.add(nmrQuarto);
        params.add(checkinDate);
        params.add(checkoutDate);
        params.add(qntPessoas);
        params.add(formattedData_reserva);
        params.add(valor);
        
        try {
            int linhasAfetadas = Conexao.executeUpdate(sql, params);
            return linhasAfetadas;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar reserva: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static int confirmarEntrada(int id) {
        String sql = "UPDATE reserva SET status = 'Confirmada' WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(id);
        try {
            int linhasAfetadas = Conexao.executeUpdate(sql, params);
            if (linhasAfetadas > 0) {
                return linhasAfetadas;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static int cancelarEntrada(int id) {
        String sql = "DELETE FROM reserva WHERE ID = ?";
        List<Object> params = new ArrayList<>();
        params.add(id);
        try {
            int linhasAfetadas = Conexao.executeUpdate(sql, params);
            if (linhasAfetadas > 0) {
                return linhasAfetadas;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static void pesquisarReservasCheckout(DefaultTableModel modelTable, JTable tabelaReservas) {
        modelTable.setRowCount(0); // Clear previous data

        try (Connection conn = Conexao.getConnection()) {
            String sql = "SELECT r.id, c.nome, r.data_checkin, r.data_checkout, r.id_quarto FROM reserva r "
                       + "LEFT JOIN cliente c ON r.id_cliente = c.id_cliente";

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    modelTable.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nome"), 
                        rs.getDate("data_checkin"),
                        rs.getDate("data_checkout"),
                        rs.getInt("id_quarto")
                    });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        tabelaReservas.setModel(modelTable);
    }
    
    public static List<Reserve> filter(String op, String parametersSearch) {
        String sql = "";
        List<Reserve> result = new ArrayList<>();
        
   
        
       if (op.equals("NOME DO CLIENTE")){
            sql = "SELECT\r\n"
                    + "    r.id AS id_reserva,\r\n"
                    + "    c.nome,\r\n"
                    + "    c.cpf,\r\n"
                    + "    q.nmr_quarto,\r\n"
                    + "    r.data_checkin AS 'data checkin',\r\n"
                    + "    r.data_checkout AS 'data checkout',\r\n"
                    + "    r.status\r\n"
                    + "FROM \r\n"
                    + "    reserva r\r\n"
                    + "INNER JOIN \r\n"
                    + "    cliente c ON r.id_cliente = c.id_cliente\r\n"
                    + "INNER JOIN \r\n"
                    + "    quarto q ON r.id_quarto = q.id\r\n"
                    + "WHERE \r\n"
                    + "    q.status = 'Disponível' AND c.nome LIKE ? ;";
            

        } else if (op.equals("CPF")) {
            sql = "SELECT\r\n"
                    + "    r.id AS id_reserva,\r\n"
                    + "    c.nome,\r\n"
                    + "    c.cpf,\r\n"
                    + "    q.nmr_quarto,\r\n"
                    + "    r.data_checkin AS 'data checkin',\r\n"
                    + "    r.data_checkout AS 'data checkout',\r\n"
                    + "    r.status\r\n"
                    + "FROM \r\n"
                    + "    reserva r\r\n"
                    + "INNER JOIN \r\n"
                    + "    cliente c ON r.id_cliente = c.id_cliente\r\n"
                    + "INNER JOIN \r\n"
                    + "    quarto q ON r.id_quarto = q.id\r\n"
                    + "WHERE \r\n"
                    + "    q.status = 'Disponível' AND c.cpf LIKE ? ;";
            
        } else if (op.equals("QUARTO")) {
            sql = "SELECT\r\n"
                    + "    r.id AS id_reserva,\r\n"
                    + "    c.nome,\r\n"
                    + "    c.cpf,\r\n"
                    + "    q.nmr_quarto,\r\n"
                    + "    r.data_checkin AS 'data checkin',\r\n"
                    + "    r.data_checkout AS 'data checkout',\r\n"
                    + "    r.status\r\n"
                    + "FROM \r\n"
                    + "    reserva r\r\n"
                    + "INNER JOIN \r\n"
                    + "    cliente c ON r.id_cliente = c.id_cliente\r\n"
                    + "INNER JOIN \r\n"
                    + "    quarto q ON r.id_quarto = q.id\r\n"
                    + "WHERE \r\n"
                    + "    q.status = 'Disponível' AND q.nmr_quarto LIKE ?;";


        } else if (op.equals("TODOS")) {
            sql = "SELECT \r\n"
                    + "    r.id AS id_reserva,\r\n"
                    + "    c.nome,\r\n"
                    + "    c.cpf,\r\n"
                    + "    q.nmr_quarto,\r\n"
                    + "    r.data_checkin AS 'data checkin',\r\n"
                    + "    r.data_checkout AS 'data checkout',\r\n"
                    + "    r.status\r\n"
                    + "FROM \r\n"
                    + "    reserva r\r\n"
                    + "INNER JOIN \r\n"
                    + "    cliente c ON r.id_cliente = c.id_cliente\r\n"
                    + "INNER JOIN \r\n"
                    + "    quarto q ON r.id_quarto = q.id\r\n"
                    + "WHERE \r\n"
                    + "    q.status = 'Disponível';";
            		
        }
       
      
       
       
       int quantidade_pessoas = 0;
       Double valor = 0.0;
       
       try {
           ArrayList<Object> params = new ArrayList<>();
           if (!op.equals("TODOS")) {
               params.add(parametersSearch);
           }
           
           ResultSet rs = Conexao.executeQuery(sql, params);
           while (rs != null && rs.next()) {
            	
            	Client client = new Client(rs.getString("nome"),rs.getString("cpf"));
            	Room room = new Room(rs.getInt("nmr_quarto"));
            	
                Reserve reserve = new Reserve(
                	
                    rs.getInt("id_reserva"),
                    rs.getString("data checkin"),
                    rs.getString("data checkout"),
                    room,
                    client,
                    valor,
                    rs.getString("status"),
                    quantidade_pessoas,
                    client
                    
                  
                );
                
                result.add(reserve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

	
}