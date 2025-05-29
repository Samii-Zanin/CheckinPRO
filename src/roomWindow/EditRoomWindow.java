package roomWindow;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import objects.Room;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;


public class EditRoomWindow {

    public JFrame frameEditRoom;
    private JTextField textFieldIdentificacaoQuarto;
    private JTextField textFieldAndarQuarto;
    private JSpinner spinnerValorQuarto;
    private JSpinner spinnerMaximoHospede;
    private JTextArea textAreaDescricaoQuarto;
    private JSpinner spinnerNmrCamas;
    private JComboBox<String> comboBoxTipoQuarto;
    private JTextField textFieldIdQuarto;
    
    

    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditRoomWindow window = new EditRoomWindow();
                    window.frameEditRoom.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EditRoomWindow() {
        initialize();
    }

    public void initialize() {
    	
        frameEditRoom = new JFrame();
        frameEditRoom.setResizable(false);
        frameEditRoom.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameEditRoom.setBounds(100, 100, 600, 700);
        frameEditRoom.setLocationRelativeTo(null);
        frameEditRoom.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(19, 19, 19));
        panel.setBounds(0, 0, 584, 661);
        frameEditRoom.getContentPane().add(panel);
        panel.setLayout(null);
        
        JPanel customBar = new JPanel();
        customBar.setBackground(new Color(17, 193, 123));
        customBar.setBounds(0, 0, 584, 5);
        panel.add(customBar);
        
        JPanel MainPanelEditRoom = new JPanel();
        MainPanelEditRoom.setBackground(new Color(55, 55, 55));
        MainPanelEditRoom.setBounds(6, 12, 572, 643);
        panel.add(MainPanelEditRoom);
        MainPanelEditRoom.setLayout(null);
        
        JPanel panelCustomHeader = new JPanel();
        panelCustomHeader.setBackground(Color.DARK_GRAY);
        panelCustomHeader.setBounds(0, 0, 572, 28);
        MainPanelEditRoom.add(panelCustomHeader);
        panelCustomHeader.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Editar  Quartos");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(207, 0, 158, 24);
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblNewLabel.setForeground(new Color(17, 193, 120));
        panelCustomHeader.add(lblNewLabel);
        
        JLabel lblNewLabelIdentificacaoQuarto = new JLabel("Identificação do Quarto");
        lblNewLabelIdentificacaoQuarto.setForeground(new Color(17, 193, 120));
        lblNewLabelIdentificacaoQuarto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelIdentificacaoQuarto.setBounds(64, 158, 183, 16);
        MainPanelEditRoom.add(lblNewLabelIdentificacaoQuarto);
        
        textFieldIdentificacaoQuarto = new JTextField();
        textFieldIdentificacaoQuarto.setBounds(64, 184, 206, 22);
        MainPanelEditRoom.add(textFieldIdentificacaoQuarto);
        textFieldIdentificacaoQuarto.setColumns(10);
        
        JLabel lblNewLabelAndar = new JLabel("Andar do Quarto");
        lblNewLabelAndar.setForeground(new Color(17, 193, 120));
        lblNewLabelAndar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelAndar.setBounds(302, 71, 136, 16);
        MainPanelEditRoom.add(lblNewLabelAndar);
        
        textFieldAndarQuarto = new JTextField();
        textFieldAndarQuarto.setColumns(10);
        textFieldAndarQuarto.setBounds(302, 100, 211, 22);
        MainPanelEditRoom.add(textFieldAndarQuarto);
        
        JPanel panelCustomFooter = new JPanel();
        panelCustomFooter.setBackground(Color.DARK_GRAY);
        panelCustomFooter.setBounds(0, 579, 572, 64);
        MainPanelEditRoom.add(panelCustomFooter);
        panelCustomFooter.setLayout(null);
        
        JButton btnConcluir = new JButton("Adicionar");
        btnConcluir.setIcon(new ImageIcon(EditRoomWindow.class.getResource("/img/Adicionar.png")));
        btnConcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RoomUpdate();
            }
        });
        btnConcluir.setBounds(82, 14, 174, 37);
        panelCustomFooter.add(btnConcluir);
        
        JButton btnApagar = new JButton("Limpar");
        btnApagar.setIcon(new ImageIcon(EditRoomWindow.class.getResource("/img/icons8-pencil-eraser-30.png")));
        btnApagar.setSelectedIcon(new ImageIcon(EditRoomWindow.class.getResource("/img/icons8-pencil-eraser-30.png")));
        btnApagar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cleanFields();
        	}
        });
        btnApagar.setBounds(317, 14, 174, 37);
        panelCustomFooter.add(btnApagar);
        
        JLabel lblNewLabelValor = new JLabel("Valor do Quarto");
        lblNewLabelValor.setForeground(new Color(17, 193, 120));
        lblNewLabelValor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelValor.setBounds(64, 243, 183, 16);
        MainPanelEditRoom.add(lblNewLabelValor);
        
        spinnerValorQuarto = new JSpinner();
        spinnerValorQuarto.setName("");
        spinnerValorQuarto.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(10)));
        spinnerValorQuarto.setBounds(64, 271, 206, 22);
        MainPanelEditRoom.add(spinnerValorQuarto);
        
        JLabel lblNewLabelDescricao = new JLabel("Descrição");
        lblNewLabelDescricao.setForeground(new Color(17, 193, 120));
        lblNewLabelDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelDescricao.setBounds(64, 411, 183, 16);
        MainPanelEditRoom.add(lblNewLabelDescricao);
        
        textAreaDescricaoQuarto = new JTextArea();
        textAreaDescricaoQuarto.setLineWrap(true);
        textAreaDescricaoQuarto.setBounds(64, 439, 449, 76);
        MainPanelEditRoom.add(textAreaDescricaoQuarto);
        
        JLabel lblNewLabelMaxHospedes = new JLabel("Maximo de hóspedes");
        lblNewLabelMaxHospedes.setForeground(new Color(17, 193, 120));
        lblNewLabelMaxHospedes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelMaxHospedes.setBounds(64, 326, 183, 16);
        MainPanelEditRoom.add(lblNewLabelMaxHospedes);
        
        spinnerMaximoHospede = new JSpinner();
        spinnerMaximoHospede.setName("");
        spinnerMaximoHospede.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerMaximoHospede.setBounds(64, 354, 206, 22);
        MainPanelEditRoom.add(spinnerMaximoHospede);
        
        spinnerNmrCamas = new JSpinner();
        spinnerNmrCamas.setName("");
        spinnerNmrCamas.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerNmrCamas.setBounds(302, 271, 206, 22);
        MainPanelEditRoom.add(spinnerNmrCamas);
        
        JLabel lblNmeroDeCamas = new JLabel("Número de Camas");
        lblNmeroDeCamas.setForeground(new Color(17, 193, 120));
        lblNmeroDeCamas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNmeroDeCamas.setBounds(302, 239, 183, 16);
        MainPanelEditRoom.add(lblNmeroDeCamas);
        
        
        
        JLabel lblTipo = new JLabel("Tipo do Quarto");
        lblTipo.setForeground(new Color(17, 193, 120));
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblTipo.setBounds(302, 158, 183, 16);
        MainPanelEditRoom.add(lblTipo);
        
        

        comboBoxTipoQuarto = new JComboBox<>();
        comboBoxTipoQuarto.setModel(new DefaultComboBoxModel<>(new String[] {"Standard", "Deluxe", "Suíte"}));
        comboBoxTipoQuarto.setBounds(302, 184, 211, 22);
        MainPanelEditRoom.add(comboBoxTipoQuarto);
        
        textFieldIdQuarto = new JTextField();
        textFieldIdQuarto.setEditable(false);
        textFieldIdQuarto.setColumns(10);
        textFieldIdQuarto.setBounds(64, 101, 211, 22);
        MainPanelEditRoom.add(textFieldIdQuarto);
        
        JLabel lblIdDoQuarto = new JLabel("ID do Quarto");
        lblIdDoQuarto.setForeground(new Color(17, 193, 120));
        lblIdDoQuarto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblIdDoQuarto.setBounds(64, 71, 136, 16);
        MainPanelEditRoom.add(lblIdDoQuarto);
    }

    public void setVisible(boolean b) {
        frameEditRoom.setVisible(b);
    }
    
    public void  insertDataInFields(Room room) {
    	
    	textFieldIdQuarto.setText(String.valueOf(room.getId()));
		textFieldIdentificacaoQuarto.setText(String.valueOf(room.getNumero()));
		textFieldAndarQuarto.setText(String.valueOf(room.getAndar()));
		spinnerValorQuarto.setValue(room.getPreco_diaria());
		spinnerMaximoHospede.setValue(room.getCapacidade());
		spinnerNmrCamas.setValue(room.getCamas());
		textAreaDescricaoQuarto.setText(room.getDescricao());
		comboBoxTipoQuarto.setSelectedItem(room.getTipo());
    	
    	
    	
    }
      
    private void RoomUpdate() {
    	
    	 try {
    	
    		 int id = Integer.parseInt(textFieldIdQuarto.getText());
	    	 int nmrQuarto = Integer.parseInt(textFieldIdentificacaoQuarto.getText());
	         int andar = Integer.parseInt(textFieldAndarQuarto.getText());
	         int valorDiaria = ((Number) spinnerValorQuarto.getValue()).intValue();
	         int maxHospedes = ((Number) spinnerMaximoHospede.getValue()).intValue();
	         int nmrCamas = ((Number) spinnerNmrCamas.getValue()).intValue();
	         String descricao = textAreaDescricaoQuarto.getText();
	         String tipo = (String) comboBoxTipoQuarto.getSelectedItem();
	        
       
       
        int result = Room.editRoom(id, nmrQuarto, tipo, descricao, maxHospedes, valorDiaria, nmrCamas, andar);
        
       
        
        if (result > 0) {
            JOptionPane.showMessageDialog(frameEditRoom, "Quarto editado com sucesso!");
      
            frameEditRoom.dispose();
            
        } else {
            JOptionPane.showMessageDialog(frameEditRoom, "Falha ao editar quarto.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
	        
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(frameEditRoom, "Por favor, insira valores numéricos válidos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(frameEditRoom, "Erro ao editar quarto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		    }
		
    }
		
    public void cleanFields() {
		
		textFieldIdentificacaoQuarto.setText(null);;
        textFieldAndarQuarto.setText(null);
        spinnerValorQuarto.setValue(0);
        spinnerMaximoHospede.setValue(1);
        spinnerNmrCamas.setValue(1);
        textAreaDescricaoQuarto.setText(null);		
        comboBoxTipoQuarto.setSelectedItem("Standard");
    }
}
