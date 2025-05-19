import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.event.*;

public class Home extends JFrame {
    private CheckinFrame checkinP;
    private CheckoutFrame checkoutP;
    private JLayeredPane layeredPane;
    private JPanel MainPanel;
	private UserFrame userP;
	private RoomFrame roomP;
	
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    Home frame = new Home();
                    frame.setUndecorated(false);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Home() {
        setResizable(false);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1600, 900);
        
        MainPanel = new JPanel();
        MainPanel.setBackground(new Color(19, 19, 19));
        MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(MainPanel);
        MainPanel.setLayout(null);
        
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(32, 32, 32));
        panelMenu.setBounds(6, 10, 261, 845);
        panelMenu.setLayout(null);
        MainPanel.add(panelMenu);
        
        JLabel logoImg = new JLabel("");
        logoImg.setBackground(new Color(0, 255, 0));
        logoImg.setHorizontalAlignment(SwingConstants.CENTER);
        logoImg.setBounds(0, 0, 257, 235);
        logoImg.setIcon(new ImageIcon(Home.class.getResource("/img/LOGO BRANCO1.png")));
        panelMenu.add(logoImg);
        
        int buttonWidth = 237;
        int buttonHeight = 45;
        
        JButton btnCheckin = new JButton("Check-in");
        btnCheckin.setBorderPainted(false);
        btnCheckin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchScreen(checkinP);
            }
        });
        btnCheckin.setForeground(Color.WHITE);
        btnCheckin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        btnCheckin.setBackground(new Color(17, 193, 123));
        btnCheckin.setBounds(10, 300, buttonWidth, buttonHeight);
        btnCheckin.setIcon(new ImageIcon(Home.class.getResource("/img/icons8-check-in-hotel-30 (1).png")));
        panelMenu.add(btnCheckin);
        
        JButton btnCheckout = new JButton("Check-out");
        btnCheckout.setBorderPainted(false);
        btnCheckout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchScreen(checkoutP);
            }
        });
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        btnCheckout.setBackground(new Color(17, 193, 123));
        btnCheckout.setBounds(10, 380, buttonWidth, buttonHeight);
        btnCheckout.setIcon(new ImageIcon(Home.class.getResource("/img/icons8-hotel-chekc-fora-30 (1).png")));
        panelMenu.add(btnCheckout);
        
        JButton btnQuartos = new JButton("Quartos");
        btnQuartos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		switchScreen(roomP);
        	}
        });
        btnQuartos.setBorderPainted(false);
        btnQuartos.setForeground(Color.WHITE);
        btnQuartos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        btnQuartos.setBackground(new Color(17, 193, 123));
        btnQuartos.setBounds(10, 495, buttonWidth, buttonHeight);
        btnQuartos.setIcon(new ImageIcon(Home.class.getResource("/img/icons8-interior-do-quarto-30.png")));
        panelMenu.add(btnQuartos);
        
        JButton btnClientes = new JButton("Clientes");
        btnClientes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		switchScreen(userP);
        	}
        });
        btnClientes.setBorderPainted(false);
        btnClientes.setForeground(Color.WHITE);
        btnClientes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        btnClientes.setBackground(new Color(17, 193, 123));
        btnClientes.setBounds(10, 576, buttonWidth, buttonHeight);
        btnClientes.setIcon(new ImageIcon(Home.class.getResource("/img/icons8-homem-de-negócios-30.png")));
        panelMenu.add(btnClientes);
        
        JLabel lblVersion = new JLabel("Versão 1.0.1");
        lblVersion.setForeground(new Color(255, 255, 255));
        lblVersion.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
        lblVersion.setBounds(0, 209, 257, 16);
        panelMenu.add(lblVersion);
        
        JLabel lblMenuGerenciamento = new JLabel("Gerenciamento");
        lblMenuGerenciamento.setForeground(Color.LIGHT_GRAY);
        lblMenuGerenciamento.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        lblMenuGerenciamento.setBounds(10, 272, 118, 16);
        panelMenu.add(lblMenuGerenciamento);
        
        JLabel lblMenuCadastro = new JLabel("Cadastro");
        lblMenuCadastro.setForeground(Color.LIGHT_GRAY);
        lblMenuCadastro.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        lblMenuCadastro.setBounds(10, 467, 118, 16);
        panelMenu.add(lblMenuCadastro);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBorderPainted(false);
        btnSair.setIcon(new ImageIcon(Home.class.getResource("/img/icons8-logout-arredondado-20.png")));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        btnSair.setBackground(new Color(236, 66, 9));
        btnSair.setBounds(10, 790, 237, 32);
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panelMenu.add(btnSair);

        JPanel customBar2 = new JPanel();
        customBar2.setBackground(Color.DARK_GRAY);
        customBar2.setBounds(10, 205, 237, 2);
        panelMenu.add(customBar2);
        
        JPanel customBar = new JPanel();
        customBar.setBackground(new Color(17, 193, 123));
        customBar.setBounds(0, 0, 1584, 4);
        MainPanel.add(customBar);
        
        
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(274, 10, 1304, 845);
        MainPanel.add(layeredPane);
        
        checkinP = new CheckinFrame();
        checkoutP = new CheckoutFrame();
        userP = new UserFrame();
        roomP = new RoomFrame();
        
        
        layeredPane.add(checkinP, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(checkoutP, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(userP, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(roomP, JLayeredPane.PALETTE_LAYER);
        
        checkinP.setVisible(true);
        checkoutP.setVisible(false);
        userP.setVisible(false);
        roomP.setVisible(false);
    }
    
    public void switchScreen(JPanel panel) {
        checkinP.setVisible(false);
        checkoutP.setVisible(false);
        userP.setVisible(false);
        roomP.setVisible(false);
        
        panel.setVisible(true);
        panel.revalidate();
        panel.repaint();
    }
}