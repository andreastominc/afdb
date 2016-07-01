package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bl.AfdbLogin;
import bl.BenutzerInfo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class AfdbLoginFrame extends JFrame {
	
	private static AfdbLoginFrame frame; // als private static definieren, damit spaeter "frame.dispose" aufgerufen werden kann.
	private JPanel contentPane;
	private JTextField tfBenutzer;
	private JPasswordField passwordField;
	private AfdbLogin AfdbBl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AfdbLoginFrame(); 
					frame.setVisible(true);
					frame.setBounds(300, 100, 1000, 600);
					frame.setSize(new Dimension (300,200));
					frame.setMinimumSize(new Dimension(30,20)); // 1100,700
					frame.setMaximumSize(new Dimension (40,30));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public AfdbLoginFrame() {
		
		this.AfdbBl = new AfdbLogin();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String benutzer = tfBenutzer.getText();			
				String passwort = new String (passwordField.getPassword());
								
				try {
					if(AfdbBl.checkUser(benutzer) && AfdbBl.checkPasswort(passwort))
					{
					    frame.StartApplication();
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Benutzername oder Passwort falsch!");
						tfBenutzer.setText("");
						passwordField.setText("");
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnLogin, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblBenutzer = new JLabel("Benutzername");
		panel_1.add(lblBenutzer);
		
		tfBenutzer = new JTextField();
		panel_1.add(tfBenutzer);
		tfBenutzer.setColumns(10);
		
		JLabel lblPasswort = new JLabel("Passwort");
		panel_1.add(lblPasswort);
		
		passwordField = new JPasswordField();
		panel_1.add(passwordField);
		
		
		// Benutzername setzen
		//BenutzerInfo.BenutzerName = tbxUsername.getText();
		
	}
	
	private void StartApplication() {
		frame.dispose(); // aktuelles Frame schliessen
		AfdbZugewiesenFrame zugew_frame = new AfdbZugewiesenFrame();
		zugew_frame.setBounds(300, 100, 1000, 600);
		zugew_frame.setMinimumSize(new Dimension(1100, 700));
		zugew_frame.setVisible(true); // das "Mir zugewiesen"-Frame oeffnen und anzeigen
	}
}
