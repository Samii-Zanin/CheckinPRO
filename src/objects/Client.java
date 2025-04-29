package objects;

import java.util.List;

import conexao.Conexao;

import java.util.ArrayList;

public class Client {

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Client(int id, String nome, String cpf, String email, String telefone) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        if (!validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        if (email == null || !email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone inválido.");
        }

    	this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", telefone=" + telefone + "]";
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

    public static int cadastrarCliente(String nome, String cpf, String email, String telefone) {
    	if(!validarCPF(cpf)) {
			System.err.println("CPF inválido!");
			return 0;
    	}
    	
    	String sql = "INSERT INTO cliente (nome, cpf, email, telefone) VALUES (?, ?, ?, ?)";

        List<Object> parametros = new ArrayList<>();
        parametros.add(nome);
        parametros.add(cpf);
        parametros.add(email);
        parametros.add(telefone);

        try {
            int linhasAfetadas = Conexao.executeUpdate(sql, parametros);
            System.out.println("Cliente cadastrado com sucesso!");
            return linhasAfetadas; // retorna quantas linhas foram afetadas
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

	public static int atualizarCliente(String cpf, String email, String telefone) {
		String sql = "UPDATE cliente SET email = ?, telefone = ? WHERE cpf = ?";
		List<Object> parametros = new ArrayList<>();
		parametros.add(email);
		parametros.add(telefone);
		parametros.add(cpf);

		try {
			int linhasAfetadas = Conexao.executeUpdate(sql, parametros);
			System.out.println("Cliente atualizado com sucesso!" );
			return linhasAfetadas;
		} catch (Exception e) {
			System.err.println("Erro ao atualizar cliente: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
		
		
	}

	public static int excluirCliente(String cpf) {
		String sql = "DELETE FROM cliente WHERE cpf = ?";
		List<Object> parametros = new ArrayList<>();
		parametros.add(cpf);

		try {
			int linhasAfetadas = Conexao.executeUpdate(sql, parametros);
			System.out.println("Cliente excluído com sucesso! Linhas afetadas: " + linhasAfetadas);
			return linhasAfetadas;
		} catch (Exception e) {
			System.err.println("Erro ao excluir cliente: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
}
