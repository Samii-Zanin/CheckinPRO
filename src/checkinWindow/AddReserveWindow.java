package checkinWindow; 

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import conexao.Conexao;
import objects.Reserve;
import objects.Room;
import javax.swing.ImageIcon;

public class AddReserveWindow {

    public JFrame frameAddReserve;
    private JTextField textFieldCpf;
    public JTextField textFieldRoomNumber;
    private JDateChooser dateChooserArrive;
    private JDateChooser dateChooserDeparture;
    private JTable table;
    private DefaultTableModel model;
    private JSpinner spinnerNumeroHospede;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddReserveWindow window = new AddReserveWindow();
                    window.frameAddReserve.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddReserveWindow() {
        initialize();
    }

    public void initialize() {
        frameAddReserve = new JFrame();
        frameAddReserve.setResizable(false);
        frameAddReserve.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAddReserve.setBounds(100, 100, 750, 700);
        frameAddReserve.setLocationRelativeTo(null);
        frameAddReserve.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(19, 19, 19));
        panel.setBounds(0, 0, 734, 661);
        frameAddReserve.getContentPane().add(panel);
        panel.setLayout(null);
        
        JPanel customBar = new JPanel();
        customBar.setBackground(new Color(17, 193, 123));
        customBar.setBounds(0, 0, 750, 5);
        panel.add(customBar);
        
        JPanel mainPanelAddRoom = new JPanel();
        mainPanelAddRoom.setBackground(new Color(55, 55, 55));
        mainPanelAddRoom.setBounds(6, 12, 722, 643);
        panel.add(mainPanelAddRoom);
        mainPanelAddRoom.setLayout(null);
        
        JPanel panelCustomHeader = new JPanel();
        panelCustomHeader.setBackground(Color.DARK_GRAY);
        panelCustomHeader.setBounds(0, 0, 722, 28);
        mainPanelAddRoom.add(panelCustomHeader);
        panelCustomHeader.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Cadastro de Reservas");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 722, 24);
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblNewLabel.setForeground(new Color(17, 193, 120));
        panelCustomHeader.add(lblNewLabel);
        
        JLabel lblCpf = new JLabel("CPF do Cliente");
        lblCpf.setForeground(new Color(17, 193, 120));
        lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCpf.setBounds(57, 545, 183, 16);
        mainPanelAddRoom.add(lblCpf);
        
        textFieldCpf = new JTextField();
        textFieldCpf.setColumns(10);
        textFieldCpf.setBounds(57, 573, 206, 22);
        mainPanelAddRoom.add(textFieldCpf);
        
        JLabel lblRoomNumber = new JLabel("Id do Quarto");
        lblRoomNumber.setForeground(new Color(17, 193, 120));
        lblRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblRoomNumber.setBounds(57, 365, 183, 16);
        mainPanelAddRoom.add(lblRoomNumber);
        
        textFieldRoomNumber = new JTextField();
        textFieldRoomNumber.setColumns(10);
        textFieldRoomNumber.setBounds(57, 393, 206, 22);
        mainPanelAddRoom.add(textFieldRoomNumber);
        
        JLabel lblGuestNumber = new JLabel("Número de hóspedes");
        lblGuestNumber.setForeground(new Color(17, 193, 120));
        lblGuestNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblGuestNumber.setBounds(57, 455, 183, 16);
        mainPanelAddRoom.add(lblGuestNumber);
        
        spinnerNumeroHospede = new JSpinner();
        spinnerNumeroHospede.setName("");
        spinnerNumeroHospede.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerNumeroHospede.setBounds(57, 483, 206, 22);
        mainPanelAddRoom.add(spinnerNumeroHospede);
        
        JPanel panelCheckDate = new JPanel();
        panelCheckDate.setBackground(Color.DARK_GRAY);
        panelCheckDate.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), 
            new TitledBorder(null, "Conferir Datas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(187, 187, 187))));
        panelCheckDate.setBounds(57, 119, 206, 224);
        mainPanelAddRoom.add(panelCheckDate);
        panelCheckDate.setLayout(null);
        
        dateChooserArrive = new JDateChooser();
        dateChooserArrive.setBounds(27, 57, 152, 28);
        panelCheckDate.add(dateChooserArrive);
        
        JLabel lblArrivalDate = new JLabel("Data de entrada");
        lblArrivalDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblArrivalDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrivalDate.setBounds(6, 36, 194, 16);
        panelCheckDate.add(lblArrivalDate);
        
        JLabel lblDepartureDate = new JLabel("Data de Saída");
        lblDepartureDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDepartureDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepartureDate.setBounds(6, 103, 194, 16);
        panelCheckDate.add(lblDepartureDate);
        
        dateChooserDeparture = new JDateChooser();
        dateChooserDeparture.setBounds(27, 123, 152, 28);
        panelCheckDate.add(dateChooserDeparture);
        
        JButton btnCheckDate = new JButton("Pesquisar Quartos");
        btnCheckDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	roomTable();
                
            }
        });
        btnCheckDate.setBounds(27, 180, 152, 22);
        panelCheckDate.add(btnCheckDate);
        
        JLabel lblAvailableRooms = new JLabel("Quartos Disponíveis");
        lblAvailableRooms.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvailableRooms.setForeground(new Color(17, 193, 120));
        lblAvailableRooms.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblAvailableRooms.setBounds(342, 59, 333, 28);
        mainPanelAddRoom.add(lblAvailableRooms);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(342, 119, 333, 401);
        mainPanelAddRoom.add(scrollPane);
        
        model = new DefaultTableModel(
            new Object[][] {}, 
            new String[] {"id","Número", "Andar", "Camas", "Valor", "Tipo"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(Color.LIGHT_GRAY);
        scrollPane.setViewportView(table);
        
        table.addMouseListener(new MouseAdapter() {
            private String selectedNumber;
            
            
			@Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    selectedNumber = table.getValueAt(row, 0).toString();
                    textFieldRoomNumber.setText(selectedNumber);
                    
                }
            }
        });
        
        JButton btnClear = new JButton("Limpar");
        btnClear.setIcon(new ImageIcon(AddReserveWindow.class.getResource("/img/icons8-pencil-eraser-30.png")));
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearFields();
        	}
        });
        btnClear.setBounds(528, 556, 147, 39);
        mainPanelAddRoom.add(btnClear);
        
        JButton btnConfirm = new JButton("Confirmar");
        btnConfirm.setIcon(new ImageIcon(AddReserveWindow.class.getResource("/img/Adicionar.png")));
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createReserve();
               
            }
        });
        btnConfirm.setBounds(342, 556, 147, 39);
        mainPanelAddRoom.add(btnConfirm);
        
        
    }

    public void roomTable() {
    	String sql = "SELECT \r\n"
    			+ "q.id, \r\n"
                + "    q.nmr_quarto, \r\n"
                + "    q.andar, \r\n"
                + "    q.camas, \r\n"
                + "    q.preco_diaria, \r\n"
                + "    q.tipo \r\n"
                + "FROM \r\n"
                + "    quarto q\r\n"
                + "WHERE \r\n"
                + "    NOT EXISTS (\r\n"
                + "        SELECT 1 \r\n"
                + "        FROM reserva r \r\n"
                + "        WHERE \r\n"
                + "            r.id_quarto = q.id\r\n"
                + "            AND (\r\n"
                + "                (r.data_checkin < ? AND r.data_checkout > ?)\r\n"
                + "            )\r\n"
                + "    );";
    	
    	
        ArrayList<Object[]> result = new ArrayList<>();
        
        Date arrivalDate = dateChooserArrive.getDate();
        Date departureDate = dateChooserDeparture.getDate();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedArrival = sdf.format(arrivalDate);
        String formattedDeparture = sdf.format(departureDate);
        
        
        List<Object> params = new ArrayList<>();
        params.add(formattedDeparture);  
        params.add(formattedArrival);
        
        try (ResultSet rs = Conexao.executeQuery(sql, params)) {
            while (rs != null && rs.next()) {
                Object[] row = {
                		rs.getInt("id"),
                		rs.getInt("nmr_quarto"),  
                    rs.getInt("andar"),
                    rs.getInt("camas"),
                    "R$" + rs.getInt("preco_diaria"), 
                    rs.getString("tipo")
                };
                result.add(row);
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        model.setRowCount(0);
        for (Object[] row : result) {
            model.addRow(row);
         	
        } 
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frameAddReserve, " Sem quartos disponível neste período!", "Não disponível", JOptionPane.INFORMATION_MESSAGE);
 
            return;
       } 
    }
    
        public boolean dateChecker() {
        try {
            int nmrQuarto = Integer.parseInt(textFieldRoomNumber.getText());
            Date arrivalDate = dateChooserArrive.getDate();
            Date departureDate = dateChooserDeparture.getDate();
            
            if (arrivalDate == null || departureDate == null) {
                JOptionPane.showMessageDialog(frameAddReserve, "Selecione ambas as datas", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            
            if (departureDate.before(arrivalDate)) {
                JOptionPane.showMessageDialog(frameAddReserve, "Data de saída deve ser após data de entrada", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedArrival = sdf.format(arrivalDate);
            String formattedDeparture = sdf.format(departureDate);
            
            boolean available = Reserve.quartoDisponivel(nmrQuarto, formattedArrival, formattedDeparture);
            
            if (available) {
                JOptionPane.showMessageDialog(frameAddReserve, "Quarto disponível neste período!", "Disponível", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(frameAddReserve, "Quarto não disponível nas datas selecionadas", "Indisponível", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frameAddReserve, "Número do quarto inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public int userChecker() {
        try {
            String cpf = textFieldCpf.getText().trim(); 
            
            if (cpf.isEmpty()) { 
                JOptionPane.showMessageDialog(frameAddReserve, "CPF inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            
            Integer clientResultId = Reserve.clienteExistente(cpf);
            
            if (clientResultId != null && clientResultId > 0) {
                return clientResultId;
            } else {
                JOptionPane.showMessageDialog(frameAddReserve, "Cliente não encontrado", "Erro", JOptionPane.WARNING_MESSAGE);
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frameAddReserve, "Erro ao verificar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void createReserve() {
        if (!dateChecker() || userChecker() == 0) { 
            return;
        }
        
        try {
        	
            int id = userChecker(); 
            int IdQuarto = Integer.parseInt(textFieldRoomNumber.getText());
            Date arrivalDate = dateChooserArrive.getDate();
            Date departureDate = dateChooserDeparture.getDate();
            int roomcapacity = ((Number) spinnerNumeroHospede.getValue()).intValue();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedArrival = sdf.format(arrivalDate);
            String formattedDeparture = sdf.format(departureDate);
            int valorQuarto = Room.searchPrice(IdQuarto);
            
            
            
            int  resultReservation = Reserve.adicionarReserva(IdQuarto, id, formattedArrival, formattedDeparture, roomcapacity, valorQuarto);
            
            if (resultReservation > 0) {
                JOptionPane.showMessageDialog(frameAddReserve, "Reserva cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                frameAddReserve.dispose();
                
            } else {
                JOptionPane.showMessageDialog(frameAddReserve, "Falha ao cadastrar reserva", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frameAddReserve, "Erro ao processar reserva: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

 
    
    private void clearFields() {
        textFieldCpf.setText("");
        textFieldRoomNumber.setText("");
        dateChooserArrive.setDate(null);
        dateChooserDeparture.setDate(null);
        spinnerNumeroHospede.setValue(1);
        model.setRowCount(0);
    }
}