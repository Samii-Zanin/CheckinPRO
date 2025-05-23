package clientWindow;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import objects.Client;
import javax.swing.ImageIcon;
import java.awt.Window.Type;

public class AddClientWindow extends JFrame  {

	private JFrame frameAddClient;
	private JTextField textNomeCliente;
	private JTextField textSobrenomeCliente;
	private JTextField textCpfCliente;
	private JTextField textEmailCliente;
	private JTextField textTelefoneCliente;
	private JTextField textIdCliente;
	public JLabel lblModoEdicao;
	public JButton btnConcluirCadastro;
	public static boolean isEditMode;


	/**

	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddClientWindow window = new AddClientWindow();
					window.frameAddClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	
	 */
	public AddClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("Label reference: " + lblModoEdicao);
		

		frameAddClient = new JFrame();
		frameAddClient.setBounds(100, 100, 617, 704);
		frameAddClient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameAddClient.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(19, 19, 19));
		panel.setBounds(10, 11, 584, 661);
		frameAddClient.getContentPane().add(panel);
		
		JPanel customBar = new JPanel();
		customBar.setBackground(new Color(17, 193, 123));
		customBar.setBounds(0, 0, 584, 5);
		panel.add(customBar);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setLayout(null);
		panelCadastro.setBackground(new Color(55, 55, 55));
		panelCadastro.setBounds(0, 0, 584, 661);
		panel.add(panelCadastro);
		
		lblModoEdicao = new JLabel("Edição de Cadastros");
		lblModoEdicao.setIcon(new ImageIcon(AddClientWindow.class.getResource("/img/EditarC.png")));
		lblModoEdicao.setForeground(Color.ORANGE);
		lblModoEdicao.setBounds(213, 28, 159, 32);
		panelCadastro.add(lblModoEdicao);
		
		JPanel panelCustomHeader = new JPanel();
		panelCustomHeader.setLayout(null);
		panelCustomHeader.setBackground(Color.DARK_GRAY);
		panelCustomHeader.setBounds(0, 0, 584, 28);
		panelCadastro.add(panelCustomHeader);
		
		JLabel lblCadastroDeClientes = new JLabel("Cadastro de Clientes");
		lblCadastroDeClientes.setForeground(new Color(17, 193, 120));
		lblCadastroDeClientes.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCadastroDeClientes.setBounds(202, 0, 158, 24);
		panelCustomHeader.add(lblCadastroDeClientes);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(17, 193, 120));
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNome.setBounds(63, 103, 169, 16);
		panelCadastro.add(lblNome);
		
		JLabel lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setForeground(new Color(17, 193, 120));
		lblSobrenome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSobrenome.setBounds(301, 103, 136, 16);
		panelCadastro.add(lblSobrenome);
		
		JPanel panelCustomFooter = new JPanel();
		panelCustomFooter.setLayout(null);
		panelCustomFooter.setBackground(Color.DARK_GRAY);
		panelCustomFooter.setBounds(0, 579, 584, 64);
		panelCadastro.add(panelCustomFooter);
		
		JButton btnConcluirCadastro = new JButton("Salvar Cadastro");
		btnConcluirCadastro.setVerticalAlignment(SwingConstants.BOTTOM);
		btnConcluirCadastro.setIcon(new ImageIcon(AddClientWindow.class.getResource("/img/Ok.png")));
		btnConcluirCadastro.setBounds(82, 14, 174, 37);
		panelCustomFooter.add(btnConcluirCadastro);
		
		JButton btnCancelarCadastro = new JButton("Cancelar");
		btnCancelarCadastro.setIcon(new ImageIcon(AddClientWindow.class.getResource("/img/Cancelar.png")));
		btnCancelarCadastro.setBounds(317, 14, 174, 37);
		panelCustomFooter.add(btnCancelarCadastro);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(new Color(17, 193, 120));
		lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCpf.setBounds(63, 188, 183, 16);
		panelCadastro.add(lblCpf);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(new Color(17, 193, 120));
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEmail.setBounds(62, 297, 183, 16);
		panelCadastro.add(lblEmail);
		
		JPanel panelAdicionais = new JPanel();
		panelAdicionais.setLayout(null);
		panelAdicionais.setBackground(new Color(70, 73, 75));
		panelAdicionais.setBounds(307, 191, 211, 169);
		panelCadastro.add(panelAdicionais);
		
		JLabel lblCamposObrigatrios = new JLabel("A definir");
		lblCamposObrigatrios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCamposObrigatrios.setHorizontalAlignment(SwingConstants.CENTER);
		lblCamposObrigatrios.setForeground(new Color(17, 193, 120));
		lblCamposObrigatrios.setBounds(49, 75, 126, 16);
		panelAdicionais.add(lblCamposObrigatrios);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(new Color(17, 193, 120));
		lblTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTelefone.setBounds(63, 368, 183, 16);
		panelCadastro.add(lblTelefone);
		
		//TextBox
		textSobrenomeCliente = new JTextField();
		textSobrenomeCliente.setColumns(10);
		textSobrenomeCliente.setBounds(308, 132, 211, 22);
		panelCadastro.add(textSobrenomeCliente);
		
		textNomeCliente = new JTextField();
		textNomeCliente.setColumns(10);
		textNomeCliente.setBounds(63, 132, 206, 22);
		panelCadastro.add(textNomeCliente);
		
		textCpfCliente = new JTextField();
		textCpfCliente.setColumns(10);
		textCpfCliente.setBounds(63, 235, 206, 22);
		panelCadastro.add(textCpfCliente);
		
		textEmailCliente = new JTextField();
		textEmailCliente.setColumns(10);
		textEmailCliente.setBounds(63, 322, 206, 22);
		panelCadastro.add(textEmailCliente);
		
		textTelefoneCliente = new JTextField();
		textTelefoneCliente.setColumns(10);
		textTelefoneCliente.setBounds(63, 395, 206, 22);
		panelCadastro.add(textTelefoneCliente);
		
		textIdCliente = new JTextField();
		textIdCliente.setForeground(Color.GRAY);
		textIdCliente.setEditable(false);
		textIdCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textIdCliente.setBounds(307, 396, 211, 20);
		panelCadastro.add(textIdCliente);
		textIdCliente.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(309, 372, 22, 16);
		panelCadastro.add(lblId);
		lblId.setForeground(new Color(17, 193, 120));
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		
		
;

		
		//declaração de variáveis
		
		
		
		btnConcluirCadastro.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		        try {
		            String nome = textNomeCliente.getText().trim();
		            String sobrenome = textSobrenomeCliente.getText().trim();
		            String cpf = textCpfCliente.getText().trim();
		            String email = textEmailCliente.getText().trim();
		            String telefone = textTelefoneCliente.getText().trim();
		
		            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
		                return;
		            }
		
		            if (isEditMode) {
		                int clientId = Integer.parseInt(textIdCliente.getText().trim());
		                int updatedRows = Client.atualizarCliente(clientId, nome, sobrenome, cpf, telefone, email);
		
		                if (updatedRows > 0) {
		                    JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente.");
		                }
		            } else {
		                int resultado = Client.cadastrarCliente(nome, sobrenome, cpf, email, telefone);
		
		                if (resultado > 0) {
		                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente!");
		                }
		            }
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // ✅ NOW it shows error inside the Frame!
		        }
		    }
		});
		
		btnCancelarCadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
			
			frameAddClient.dispose();
			}
		});

		
		
	}

	public JFrame getFrame() {
	    return frameAddClient;
	}

	public void preencherCampos(Client cliente) {
		if (isEditMode) { // Only fill fields if editin
			textNomeCliente.setText(cliente.getNome());
			textSobrenomeCliente.setText(cliente.getSobrenome());
		    textCpfCliente.setText(cliente.getCpf());
		    textEmailCliente.setText(cliente.getEmail());
		    textTelefoneCliente.setText(cliente.getTelefone());
		    textIdCliente.setText(String.valueOf(cliente.getId()));
		    


		}

	    

	}	
}



