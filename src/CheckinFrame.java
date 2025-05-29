import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import checkinWindow.AddReserveWindow;
import objects.Reserve;
import objects.Room;

import javax.swing.ImageIcon;

public class CheckinFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCheckin;
	private JTable tabela;
	private DefaultTableModel model;
	public Object selectedReserveNumber = null;    
    private JTextField textFieldSearchCheckin;

	    
	    
	/**
	 * Create the panel.
	 */
	public CheckinFrame() {
		
		setBorder(null);
		setBackground(new Color(55, 55, 55));

		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(6, 6, 1292, 28);
		add(panel);
		panel.setLayout(null);
		
		txtCheckin = new JTextField();
		txtCheckin.setBounds(576, 5, 140, 24);
		txtCheckin.setBorder(null);
		txtCheckin.setAutoscrolls(false);
		txtCheckin.setEditable(false);
		txtCheckin.setBackground(Color.DARK_GRAY);
		txtCheckin.setForeground(new Color(17, 193, 123));
		txtCheckin.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		txtCheckin.setText("Check-in");
		panel.add(txtCheckin);
		txtCheckin.setColumns(10);
		
		JButton btnNewButtonConfirmCheckin = new JButton("Confirmar Entrada");
		btnNewButtonConfirmCheckin.setIcon(new ImageIcon(CheckinFrame.class.getResource("/img/icons8-form-30.png")));
		btnNewButtonConfirmCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				confirmReserve();
			}
		});
		btnNewButtonConfirmCheckin.setForeground(new Color(255, 255, 255));
		btnNewButtonConfirmCheckin.setBounds(6, 60, 172, 34);
		add(btnNewButtonConfirmCheckin);
		
		JButton btnNewButtonAddCheckin = new JButton("Cadastrar Entrada");
		btnNewButtonAddCheckin.setIcon(new ImageIcon(CheckinFrame.class.getResource("/img/Adicionar.png")));
		btnNewButtonAddCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				AddReserveWindow addReserve = new AddReserveWindow();
				addReserve.frameAddReserve.setVisible(true);
				
			}
		});
		btnNewButtonAddCheckin.setForeground(new Color(255, 255, 255));
		btnNewButtonAddCheckin.setBounds(198, 60, 172, 34);
		add(btnNewButtonAddCheckin);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(1288, 119, 10, 620);
		add(scrollBar);
		
		textFieldSearchCheckin = new JTextField();
		textFieldSearchCheckin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textFieldSearchCheckin.setForeground(new Color(255, 255, 255));
		textFieldSearchCheckin.setBounds(913, 73, 253, 34);
		add(textFieldSearchCheckin);
		textFieldSearchCheckin.setColumns(10);
		
		
		JComboBox<String> comboBoxFilter = new JComboBox<>();
        comboBoxFilter.setForeground(Color.WHITE);
        comboBoxFilter.setModel(new DefaultComboBoxModel<>(new String[] {"TODOS", "NOME DO CLIENTE", "CPF", "QUARTO"}));
        comboBoxFilter.setBounds(1178, 73, 108, 34);
        add(comboBoxFilter);

        JButton btnNewButtonSearch = new JButton("");
        btnNewButtonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String op = comboBoxFilter.getSelectedItem().toString();
                if (!op.equals("TODOS")) {
                    filter(op);
                } else {
                    loadData();
                }
            }
        });
        btnNewButtonSearch.setIcon(new ImageIcon(RoomFrame.class.getResource("/img/icons8-pesquisar-30.png")));
        btnNewButtonSearch.setBounds(837, 73, 64, 34);
        add(btnNewButtonSearch);
        
		
		JButton btnNewButtonDeleteReserve = new JButton("Cancelar Reserva");
		btnNewButtonDeleteReserve.setIcon(new ImageIcon(CheckinFrame.class.getResource("/img/Cancelar.png")));
		btnNewButtonDeleteReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelReserve();
				
			}
		});
		btnNewButtonDeleteReserve.setForeground(Color.WHITE);
		btnNewButtonDeleteReserve.setBounds(198, 770, 172, 34);
		add(btnNewButtonDeleteReserve);
		
		JButton btnNewButtonEditReserve = new JButton("Editar Reserva");
		btnNewButtonEditReserve.setIcon(new ImageIcon(CheckinFrame.class.getResource("/img/EditarC.png")));
		btnNewButtonEditReserve.setForeground(Color.WHITE);
		btnNewButtonEditReserve.setBounds(6, 770, 172, 34);
		add(btnNewButtonEditReserve);
		
		JButton btnNewButtonRefresh = new JButton("");
		btnNewButtonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		btnNewButtonRefresh.setIcon(new ImageIcon(CheckinFrame.class.getResource("/img/icons8-actualizar-30 (1).png")));
		btnNewButtonRefresh.setForeground(Color.WHITE);
		btnNewButtonRefresh.setBounds(1212, 770, 74, 34);
		add(btnNewButtonRefresh);
		
		
		model = new DefaultTableModel(
	            new Object[][] {}, 
	            new String[] {"ID Reserva","Nome do Cliente","CPF","Quarto","Data de chegada","Data de Sida","Status"}
	        ) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };

	        tabela = new JTable(model);
	        tabela.setShowHorizontalLines(true);
	        

	        tabela.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int row = tabela.getSelectedRow();
	                if (row >= 0) {
	                    selectedReserveNumber = tabela.getValueAt(row, 0);
	                    
	                }
	            }
	        });

	        JScrollPane scrollPane = new JScrollPane(tabela);
	        scrollPane.setBounds(6, 119, 1280, 620);
	        add(scrollPane);
	        
	        loadData();  
	}
		
		 private void loadData() {
		        model.setRowCount(0);
		        
		        List<Reserve> reserves = Reserve.filter("TODOS", null);
		        
		        for (Reserve reserve : reserves) {
	                model.addRow(new Object[]{
	                   
	                		reserve.getId(),
	                        reserve.getClientName().getNome(),  
	                        reserve.getId_client().getCpf(),   
	                        reserve.getId_Room().getId(),  
	                        reserve.getCheckinDate(),
	                        reserve.getCheckoutDate(),
	                        reserve.getStatus()	
	                });
		        }
		        
		    }

		    private void filter(String op) {
		       
		        String parameter = textFieldSearchCheckin.getText().toString();
		        model.setRowCount(0);
		        List<Reserve> reserves = Reserve.filter(op, parameter);
		        
		        for (Reserve reserve : reserves) {
	                model.addRow(new Object[]{
	                   
	                		reserve.getId(),
	                        reserve.getClientName().getNome(),  
	                        reserve.getId_client().getCpf(),   
	                        reserve.getId_Room().getId(),  
	                        reserve.getCheckinDate(),
	                        reserve.getCheckoutDate(),
	                        reserve.getStatus()	
	               
	               
	                });
	                
		        }
		    }
	                
		    private void  confirmReserve() {  
		    	 if (selectedReserveNumber == null) {
		             JOptionPane.showMessageDialog(this, "Selecione uma reserva  para confirmar entrada", "Aviso", JOptionPane.WARNING_MESSAGE);
		             return;
		    	 }
		    	 
		    	int id = (int) selectedReserveNumber;
		    	
		    	
		    	int confirm = JOptionPane.showConfirmDialog(
		                this, 
		                "Deseja confirmar a reserva com o Id " + selectedReserveNumber + "?", 
		                "Confirmar reserva", 
		                JOptionPane.YES_NO_OPTION
		            );
		    	 if (confirm == JOptionPane.YES_OPTION) {
	
		             int result = Reserve.confirmarEntrada(id);
		             
		             if (result > 0) {
		                 JOptionPane.showMessageDialog(this, "Reserva cadastrada com sucesso!");
		                 loadData();
		                 selectedReserveNumber = null;
		             } else {
		                 JOptionPane.showMessageDialog(this, "Falha ao efetuar a reserva", "Erro", JOptionPane.ERROR_MESSAGE);
		             }
		    	
		    	 }
		    }
		    
		    private void  cancelReserve() {  
		    	 if (selectedReserveNumber == null) {
		             JOptionPane.showMessageDialog(this, "Selecione uma reserva para cancelar", "Aviso", JOptionPane.WARNING_MESSAGE);
		             return;
		    	 }
		    	 
		    	int id = (int) selectedReserveNumber;
		    	
		    	
		    	int confirm = JOptionPane.showConfirmDialog(
		                this, 
		                "Deseja cancelar a reserva com o Id " + selectedReserveNumber + "?", 
		                "Confirmar reserva", 
		                JOptionPane.YES_NO_OPTION
		            );
		    	 if (confirm == JOptionPane.YES_OPTION) {
	
		             int result = Reserve.cancelarEntrada(id);
		             
		             if (result > 0) {
		                 JOptionPane.showMessageDialog(this, "Reserva cancelada com sucesso!");
		                 loadData();
		                 selectedReserveNumber = null;
		             } else {
		                 JOptionPane.showMessageDialog(this, "Falha ao efetuar o cancelamento", "Erro", JOptionPane.ERROR_MESSAGE);
		             }
		    	
		    	 }
		    }
		    
}
