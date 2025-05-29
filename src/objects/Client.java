package objects;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client {
    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String telefone;

 
    public Client(int id, String nome, String sobrenome, String cpf, String email, String telefone) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        if (!validarCPF(cpf)) {
        	
            throw new IllegalArgumentException("CPF inválido.");
        }
        System.out.println("Email recebido: " + email);
        System.out.println("Telefonae recebido: " + telefone);
        
        if (email == null || !email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[\\w-]+\\.)+[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        telefone = telefone.replaceAll("[^0-9]", ""); 

        if (telefone.isBlank() || telefone.length() < 8) { 
            throw new IllegalArgumentException("Telefone inválido.");
        }
        
        
        
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    

    
    public Client(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}




	public Client(int id) {
		super();
		this.id = id;
	}	
	



	@Override
    public String toString() {
        return "Client [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf +
               ", email=" + email + ", telefone=" + telefone + "]";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
    	return sobrenome;
	}
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public static boolean validarCPF(String cpf) {
        cpf = cpf.replace(".", "").replace("-", "");

        if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
            return false;
        }

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }

            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito > 9) primeiroDigito = 0;

            if (primeiroDigito != Character.getNumericValue(cpf.charAt(9))) {
                return false;
            }

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }

            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito > 9) segundoDigito = 0;

            return segundoDigito == Character.getNumericValue(cpf.charAt(10));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int cadastrarCliente(String nome, String sobrenome, String cpf, String email, String telefone) {
    	    if (!validarCPF(cpf)) {
    	        System.err.println("CPF inválido!");
    	        return 0;
    	    }

    	    String sql = "INSERT INTO cliente (nome, sobrenome, cpf, email, telefone) VALUES (?, ?, ?, ?, ?)";

    	    List<Object> parametros = new ArrayList<>();
    	    parametros.add(nome);
    	    parametros.add(sobrenome);
    	    parametros.add(cpf);
    	    parametros.add(email);
    	    parametros.add(telefone);

    	    try {
    	        int linhasAfetadas = Conexao.executeUpdate(sql, parametros);
    	        System.out.println("Cliente cadastrado com sucesso!");
    	        return linhasAfetadas; 
    	    } catch (Exception e) {
    	    	// ✅ Properly displaying the error in a message dialog
    	        JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	        e.printStackTrace(); 
    	        return 0;

    	    }
    	}

	public static int atualizarCliente(int id, String nome, String sobrenome, String cpf, String telefone, String email) {
		String sql = "UPDATE cliente SET nome = ?, sobrenome = ?, cpf = ?, telefone = ?, email = ? WHERE id_cliente = ?";
		List<Object> parametros = new ArrayList<>();
		parametros.add(nome);
		parametros.add(sobrenome);
		parametros.add(cpf);
		parametros.add(telefone);
		parametros.add(email);
		parametros.add(id);

		try {
			int linhasAfetadas = Conexao.executeUpdate(sql, parametros);
			System.out.println("Cliente atualizado com sucesso!" );
			return linhasAfetadas;
		} catch (Exception e) {
			System.err.println("Erro ao atualizar cliente: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro ao atualizar cadastro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); 
			e.printStackTrace();
			return 0;
		}
		
		
	}
	
	public static int contarTotalClientes() {
	    String sql = "SELECT COUNT(*) FROM cliente";
	    List<Object> parametros = new ArrayList<>();

	    try (ResultSet rs = (ResultSet) Conexao.executeQuery(sql, parametros)) {
	        if (rs.next()) {
	            return rs.getInt(1); // ✅ Retorna o total de clientes
	        }
	    } catch (Exception e) {
	        System.err.println("Erro ao contar clientes: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	public static List<Client> loadGridClientes() {
	    String sql = "SELECT * FROM cliente"; 
	    List<Object> parametros = new ArrayList<>();
	    List<Client> clientes = new ArrayList<>(); 

	    try (ResultSet rs = (ResultSet) Conexao.executeQuery(sql, parametros)) {
	        while (rs.next()) { // ✅ Itera sobre todos os clientes
	            Client cliente = new Client(
	                rs.getInt("id_cliente"), 
	                rs.getString("nome"), 
	                rs.getString("sobrenome"), 
	                rs.getString("cpf"),
	                rs.getString("email"),
	                rs.getString("telefone")
	            );
	            clientes.add(cliente);
	        }
	    } catch (Exception e) {
	        System.err.println("Erro ao carregar clientes: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return clientes;
	}
	
	public static List<Client> buscarClientes(String filtro, String valor, String limitarRegString) {
	    List<Client> clientes = new ArrayList<>();
	    int limitarRegSql = 10; 
	    
	    
	    try {
	        limitarRegSql = Integer.parseInt(limitarRegString);
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid limit value: " + limitarRegString + ". Using default limit.");
	        JOptionPane.showMessageDialog(null, e);
	    }

	    try (Connection conn = Conexao.getConnection()) {
	    	
	    
	    	
	        String sql = "SELECT * FROM cliente WHERE " + filtro + " LIKE ? LIMIT ?";
	        
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, "%" + valor + "%"); 
	            stmt.setInt(2, limitarRegSql); 

	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                clientes.add(new Client(
	                    rs.getInt("id_cliente"),
	                    rs.getString("nome"),
	                    rs.getString("sobrenome"),
	                    rs.getString("cpf"),
	                    rs.getString("email"),
	                    rs.getString("telefone")
	                ));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao buscar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); 
	    }

	    return clientes;
	}
	//public static Client somaRegistrosSql()
		//Client cliente = null
	
	public static Client buscarClientePorId(int idCliente) {
	    Client cliente = null;

	    try (Connection conn = Conexao.getConnection()) {
	        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idCliente);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                cliente = new Client(
	                    rs.getInt("id_cliente"),
	                    rs.getString("nome"),
	                    rs.getString("sobrenome"), 
	                    rs.getString("cpf"),
	                    rs.getString("email"),
	                    rs.getString("telefone")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, e);
	    }

	    return cliente;
	}


	public static int excluirCliente(int clienteId) {
	    
	    String sqlCheck = "SELECT COUNT(*) FROM reserva WHERE id_cliente = ?";
	    List<Object> parametrosCheck = new ArrayList<>();
	    parametrosCheck.add(clienteId);

	    try {
	        ResultSet rs = (ResultSet) Conexao.executeQuery(sqlCheck, parametrosCheck);
	        if (rs.next() && rs.getInt(1) > 0) { 
	            JOptionPane.showMessageDialog(null, "Não é possível excluir. O cliente possui reservas ativas!", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return 0; //rretorna 0 indicando que a exclusão não foi realizada
	        }
	    } catch (Exception e) {
	        System.err.println("Erro ao verificar reservas do cliente: " + e.getMessage());
	        e.printStackTrace();
	        return 0;
	    }

	    //o cliente não tem reservas ativas
	    String sqlDelete = "DELETE FROM cliente WHERE id_cliente = ?";
	    List<Object> parametrosDelete = new ArrayList<>();
	    parametrosDelete.add(clienteId);

	    try {
	        int linhasAfetadas = Conexao.executeUpdate(sqlDelete, parametrosDelete);
	        System.out.println("Cliente excluído com sucesso! Linhas afetadas: " + linhasAfetadas);
	        return linhasAfetadas;
	    } catch (Exception e) {
	        System.err.println("Erro ao excluir cliente: " + e.getMessage());
	        JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return 0;
	    }
	}
	
	public static List<Reserve> pesquisarHistoricoCliente(int clienteId) {
	    String sql = "SELECT c.nome, r.id, r.data_checkin, r.data_checkout, r.status FROM reserva r "
	               + "LEFT JOIN cliente c ON r.id_cliente = c.id_cliente WHERE c.id_cliente = ?";
	    
	    List<Object> parametros = new ArrayList<>();
	    parametros.add(clienteId);
	    List<Reserve> reservas = new ArrayList<>();
	    Client clientName = null;
	    try (ResultSet rs = (ResultSet) Conexao.executeQuery(sql, parametros)) {
	        while (rs.next()) {
	            Reserve reserva = new Reserve(
	            		 rs.getInt("id_reserva"),
	                     rs.getString("data_checkin"),
	                     rs.getString("data_checkout"),
	                     new Room(rs.getInt("id_quarto")),
	                     new Client(rs.getInt("id_cliente")),
	                     rs.getDouble("valor"),
	                     rs.getString("status"),
	                     rs.getInt("quantidade_pessoas"),
	                     clientName
	                
	                
	                
	            );
	            reservas.add(reserva);
	        }
	    } catch (Exception e) {
	        System.err.println("Erro ao buscar histórico do cliente: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return reservas; 
	}



	

	

	
}