package reserves;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import objects.Room;

import conexao.Conexao;
import objects.Client;

public class Reserve {
	private int id;
	private String checkinDate;
	private String checkoutDate;
	private Room nmrQuarto;
	private Client clientCpf;
	private boolean cafeIncluso;
	private boolean servicoQuarto;
	private boolean faxineira;
	private double valorTotal;
	private String status;

	public Reserve(int id, String checkinDate, String checkoutDate, Room nmrQuarto, Client clientCpf,
			boolean cafeIncluso, boolean servicoQuarto, boolean faxineira, double valorTotal, String status) {

		if (checkinDate == null || checkoutDate == null || nmrQuarto == null || clientCpf == null) {
			throw new IllegalArgumentException("Campos obrigatórios não podem ser nulos.");
		}

		if (checkinDate.compareTo(checkoutDate) >= 0) {
			throw new IllegalArgumentException("A data de check-out deve ser posterior à data de check-in.");
		}

		if (valorTotal < 0) {
			throw new IllegalArgumentException("O valor total não pode ser negativo.");
		}

		this.id = id;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.nmrQuarto = nmrQuarto;
		this.clientCpf = clientCpf;
		this.cafeIncluso = cafeIncluso;
		this.servicoQuarto = servicoQuarto;
		this.faxineira = faxineira;
		this.valorTotal = valorTotal;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Reserve [id=" + id + ", checkinDate=" + checkinDate + ", checkoutDate=" + checkoutDate + ", nmrQuarto="
				+ nmrQuarto + ", clientCpf=" + clientCpf + ", cafeIncluso=" + cafeIncluso + ", servicoQuarto="
				+ servicoQuarto + ", faxineira=" + faxineira + ", valorTotal=" + valorTotal + "" + ", status=" + status + "]";
	}

	public int getId() {
		return id;
	}
	public String getCheckinDate() {
		return checkinDate;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public Room getNmrQuarto() {
		return nmrQuarto;
	}

	public void setNmrQuarto(Room nmrQuarto) {
		this.nmrQuarto = nmrQuarto;
	}

	public Client getClientCpf() {
		return clientCpf;
	}

	public void setClientCpf(Client clientCpf) {
		this.clientCpf = clientCpf;
	}

	public boolean isCafeIncluso() {
		return cafeIncluso;
	}

	public void setCafeIncluso(boolean cafeIncluso) {
		this.cafeIncluso = cafeIncluso;
	}

	public boolean isServicoQuarto() {
		return servicoQuarto;
	}

	public void setServicoQuarto(boolean servicoQuarto) {
		this.servicoQuarto = servicoQuarto;
	}

	public boolean isFaxineira() {
		return faxineira;
	}

	public void setFaxineira(boolean faxineira) {
		this.faxineira = faxineira;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
	    if (valorTotal < 0) {
	        throw new IllegalArgumentException("Valor total não pode ser negativo.");
	    }
	    this.valorTotal = valorTotal;
	}

	public void setCheckinDate(String checkinDate) {
	    if (checkinDate == null || (this.checkoutDate != null && checkinDate.compareTo(this.checkoutDate) >= 0)) {
	        throw new IllegalArgumentException("Data de check-in inválida.");
	    }
	    this.checkinDate = checkinDate;
	}

	public void setCheckoutDate(String checkoutDate) {
	    if (checkoutDate == null || (this.checkinDate != null && checkoutDate.compareTo(this.checkinDate) <= 0)) {
	        throw new IllegalArgumentException("Data de check-out inválida.");
	    }
	    this.checkoutDate = checkoutDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public static boolean quartoDisponivel(int nmrQuarto, String checkinDate, String checkoutDate) {
		String sql = "SELECT COUNT(*) FROM reserva WHERE nmr_quarto = ? AND NOT (data_checkout <= ? OR data_checkin >= ?)";
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
	
	
	
	public static double valorTotal(double valor_quarto, boolean cafeIncluso, boolean servicoQuarto,
			boolean faxineira, int dias) {
		double valorTotal = valor_quarto * dias;
		if (cafeIncluso) {
			valorTotal += 15.00 * dias;
		}
		if (servicoQuarto) {
			valorTotal += 10.00 * dias ;
		}
		if (faxineira) {
			valorTotal += 10.00 * dias;
		}
		return valorTotal;
	
		
		
	}
	
	public static boolean clienteExistente(String cpf) {
		String sql = "SELECT COUNT(*) FROM cliente where cpf = ?";
		List<Object> params = new ArrayList<>();
		params.add(cpf);
		try {
			ResultSet rs = (ResultSet) Conexao.executeQuery(sql, params);
			if(rs.next()) {
				int count = rs.getInt(1);
                return count > 0;
            } else {
                System.err.println("Erro: Resultado vazio na verificação de cliente existente.");
                return false;
			}
			
		} catch (Exception e) {
			System.err.println("Erro ao verificar cliente existente: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static int efetuarReserva(Room nmrQuarto, Client clientCpf, String checkinDate, String checkoutDate,
			boolean cafeIncluso, boolean servicoQuarto, boolean faxineira, double valorTotal) {
		
		if (!quartoDisponivel(nmrQuarto.getNumero(), checkinDate, checkoutDate)) {
			System.err.println("Quarto não disponível.");
	        return 0;	
		}
		if (!clienteExistente(clientCpf.getCpf())) {
			System.err.println("Cliente não existente.");
			return 0;
		}
		valorTotal = valorTotal(nmrQuarto.getValorDiaria(), cafeIncluso, servicoQuarto, faxineira,
				(int) ((java.sql.Date.valueOf(checkoutDate).getTime() - java.sql.Date.valueOf(checkinDate).getTime())
						/ (1000 * 60 * 60 * 24)));
		String sql = "INSERT INTO reserva (nmr_quarto , id_cliente, data_checkin, data_checkout, cafe_incluso, "
				+ "servico_quarto, faxineira, valor_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		List<Object> params = new ArrayList<>();
		params.add(nmrQuarto.getNumero());
		params.add(clientCpf.getCpf());
		params.add(checkinDate);
		params.add(checkoutDate);
		params.add(cafeIncluso);
		params.add(servicoQuarto);
		params.add(faxineira);
		params.add(valorTotal);
		
		try {
            int linhasAfetadas = Conexao.executeUpdate(sql, params);
            System.out.println("Reserva efetuada com sucesso!");
            return linhasAfetadas;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar reserva: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
	}


	public void confirmarEntrada(String status, int id) {
		String sql = "UPDATE reserva SET status = 'Entrada Confirmada' WHERE id = ?";
		List<Object> params = new ArrayList<>();
		params.add(status);
		params.add(id);
		try {
			int linhasAfetadas = Conexao.executeUpdate(sql, params);
			if (linhasAfetadas > 0) {System.out.println("Entrada confirmada com sucesso!");
			}
		} catch (Exception e) {
			System.err.println("Erro ao confirmar entrada: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	



}