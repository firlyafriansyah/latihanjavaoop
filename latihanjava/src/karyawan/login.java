package karyawan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField nik_textField;
	private JPasswordField passwordField;
	
	private boolean cek_data() {
		boolean result = false;
		try {
			String sql = "SELECT * FROM user WHERE nik='"+nik_textField.getText()+"' AND password='"+passwordField.getText()+"'";
			java.sql.Connection conn = (Connection)config.configDB();
			java.sql.Statement stm = conn.createStatement();
			java.sql.ResultSet res = stm.executeQuery(sql);
			
			if(res.next()) {
				result = true;
			} else {
				result = false;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error database");
		}
		
		return result;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 414, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NIK");
		lblNewLabel_1.setBounds(55, 87, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		nik_textField = new JTextField();
		nik_textField.setBounds(151, 84, 213, 20);
		contentPane.add(nik_textField);
		nik_textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(55, 134, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 131, 213, 20);
		contentPane.add(passwordField);
		
		JButton login_btn = new JButton("Login");
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nik_textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Kolom NIK tidak boleh kosong!");
				} else if (passwordField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Kolom password tidak boleh kosong!");
				} else {
					if(cek_data()) {
						JOptionPane.showMessageDialog(null, "Login Succes!");
						dispose();
						new karyawan().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Login Gagal!");
					}
				}
			}
		});
		login_btn.setBounds(124, 199, 89, 23);
		contentPane.add(login_btn);
		
		JButton btnNewButton = new JButton("Batal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(224, 199, 89, 23);
		contentPane.add(btnNewButton);
	}
}
