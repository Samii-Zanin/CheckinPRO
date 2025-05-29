import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import objects.Room;

import roomWindow.AddRoomWindow;
import roomWindow.EditRoomWindow;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoomFrame extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtQuartos;
    private JTextField textFieldSearch;
    private JTable tabela;
    private DefaultTableModel model;
    private JButton btnNewButtonDeleteRoom;
    public Object selectedRoomNumber = null;
   
    public Object getSelectedRoomNumber() {
        return selectedRoomNumber;
    }
        
        
    public RoomFrame() {
        setBackground(new Color(55, 55, 55));
        setBounds(0, 0, 1304, 845);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(6, 6, 1292, 28);
        add(panel);
        panel.setLayout(null);

        txtQuartos = new JTextField();
        txtQuartos.setBounds(576, 5, 140, 24);
        txtQuartos.setText("Quartos");
        txtQuartos.setHorizontalAlignment(SwingConstants.CENTER);
        txtQuartos.setForeground(new Color(17, 193, 123));
        txtQuartos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
        txtQuartos.setEditable(false);
        txtQuartos.setColumns(10);
        txtQuartos.setBorder(null);
        txtQuartos.setBackground(Color.DARK_GRAY);
        panel.add(txtQuartos);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(1288, 119, 10, 620);
        add(scrollBar);

        JComboBox<String> comboBoxFilter = new JComboBox<>();
        comboBoxFilter.setForeground(Color.WHITE);
        comboBoxFilter.setModel(new DefaultComboBoxModel<>(new String[] {"TODOS", "NÚMERO", "ANDAR"}));
        comboBoxFilter.setBounds(1178, 73, 108, 34);
        add(comboBoxFilter);

        JButton btnNewButtonSearch = new JButton("");
        btnNewButtonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String op = comboBoxFilter.getSelectedItem().toString();
                if (!op.equals("TODOS")) {
                	filterSearch(op);
                } else {
                    loadData();
                }
            }
        });
        btnNewButtonSearch.setIcon(new ImageIcon(RoomFrame.class.getResource("/img/icons8-pesquisar-30.png")));
        btnNewButtonSearch.setBounds(837, 73, 64, 34);
        add(btnNewButtonSearch);

        btnNewButtonDeleteRoom = new JButton("Apagar Quarto");
        btnNewButtonDeleteRoom.setIcon(new ImageIcon(RoomFrame.class.getResource("/img/Cancelar.png")));
        btnNewButtonDeleteRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRoomDB();
            }
        });
        btnNewButtonDeleteRoom.setForeground(Color.WHITE);
        btnNewButtonDeleteRoom.setBounds(190, 770, 172, 34);
        add(btnNewButtonDeleteRoom);

        JButton btnNewButtonAddRoom = new JButton("Adicionar Quarto");
        btnNewButtonAddRoom.setIcon(new ImageIcon(RoomFrame.class.getResource("/img/Adicionar.png")));
        btnNewButtonAddRoom.setSelectedIcon(new ImageIcon(RoomFrame.class.getResource("/img/Adicionar.png")));
        btnNewButtonAddRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddRoomWindow addRoom = new AddRoomWindow();
                addRoom.frameAddRoom.setVisible(true);
            }
        });
        btnNewButtonAddRoom.setForeground(Color.WHITE);
        btnNewButtonAddRoom.setBounds(6, 770, 172, 34);
        add(btnNewButtonAddRoom);

        JButton btnNewButtonRefresh = new JButton("");
        btnNewButtonRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        btnNewButtonRefresh.setIcon(new ImageIcon(RoomFrame.class.getResource("/img/icons8-actualizar-30 (1).png")));
        btnNewButtonRefresh.setForeground(Color.WHITE);
        btnNewButtonRefresh.setBounds(1212, 770, 74, 34);
        add(btnNewButtonRefresh);

        model = new DefaultTableModel(
            new Object[][] {}, 
            new String[] {"Id","Número", "Andar", "Max Hóspedes", "Camas", "Valor","Tipo","Status"}
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
                    selectedRoomNumber = tabela.getValueAt(row, 0);
                    
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(6, 119, 1280, 620);
        add(scrollPane);

        textFieldSearch = new JTextField();
        textFieldSearch.setForeground(Color.WHITE);
        textFieldSearch.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textFieldSearch.setColumns(10);
        textFieldSearch.setBounds(913, 73, 253, 34);
        add(textFieldSearch);

        JButton btnNewButtonEditRoom = new JButton("Editar Quarto");
        btnNewButtonEditRoom.setIcon(new ImageIcon(RoomFrame.class.getResource("/img/EditarC.png")));
        btnNewButtonEditRoom.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int id = (int) selectedRoomNumber;
        		Room room = Room.searchRoomById(id);
        
        		EditRoomWindow editRoom = new EditRoomWindow();
        	    editRoom.insertDataInFields(room);
        		editRoom.frameEditRoom.setVisible(true);
        		
        	}
        });
        btnNewButtonEditRoom.setForeground(Color.WHITE);
        btnNewButtonEditRoom.setBounds(374, 770, 172, 34);
        add(btnNewButtonEditRoom);

        loadData();
    }

        
        
        private void loadData() {
            model.setRowCount(0);
            
            List<Room> rooms = Room.filter("TODOS", null); 
            
            for (Room room : rooms) {
                model.addRow(new Object[]{
                		room.getId(),
                        room.getNumero(),
                        room.getAndar(),
                        room.getCapacidade(),
                        room.getCamas(),
                        room.getPreco_diaria(),
                        room.getTipo(),
                        room.getStatus(),
                });
            }
       
    }

        private void filterSearch(String op) {
            String parametersSearch = textFieldSearch.getText();
            model.setRowCount(0);
            


            List<Room> rooms = Room.filter(op, parametersSearch);
            
            for (Room room : rooms) {
                model.addRow(new Object[]{
                    room.getId(),
                    room.getNumero(),
                    room.getAndar(),
                    room.getCapacidade(),
                    room.getCamas(),
                    room.getPreco_diaria(),
                    room.getTipo(),
                    room.getStatus(),
                    
                });
            }
        }
    
    	

    public void deleteRoomDB() {
        if (selectedRoomNumber == null) {
            JOptionPane.showMessageDialog(this, "Selecione um quarto para apagar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Deseja realmente apagar o quarto com Id " + selectedRoomNumber + "?", 
            "Confirmar exclusão", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
        	
        	int id = (int) selectedRoomNumber;
            
            int result = Room.deleteData(id);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Quarto apagado com sucesso!");
                loadData();
                selectedRoomNumber = null;
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao apagar quarto, reservas registradas no quarto", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
}