package karyawan;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class karyawan extends JFrame {

	private JPanel contentPane;
	private JTextField nik_textField;
	private JTextField nama_textField;
	private JTextField alamat_textField;
	private JTextField email_textField;
	private JTextField telpon_textField;
	private JTable karyawan_table;
	
	private void tampilkan_data() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("No");
		model.addColumn("NIK");
		model.addColumn("Nama");
		model.addColumn("Jabatan");
		model.addColumn("Alamat");
		model.addColumn("Email");
		model.addColumn("Telepon");
		
		try {
			int no = 1;
			String sql = "SELECT * FROM karyawan";
			java.sql.Connection conn = config.configDB();
			java.sql.Statement stm = conn.createStatement();
			java.sql.ResultSet res = stm.executeQuery(sql);
			
			while(res.next()) {
				model.addRow(new Object[] {no++, res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6)});
			}
			karyawan_table.setModel(model);
		} catch (SQLException e) {
			System.out.println("Error : "+e.getMessage());
		}
	}

	private void kosongkan_form( ) {
		nik_textField.setText(null);
		nama_textField.setText(null);
		alamat_textField.setText(null);
		email_textField.setText(null);
		telpon_textField.setText(null);
	}
	
	public karyawan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Data Karyawan");
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 470, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NIK");
		lblNewLabel_1.setBounds(29, 70, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		nik_textField = new JTextField();
		nik_textField.setBounds(178, 67, 278, 20);
		contentPane.add(nik_textField);
		nik_textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nama");
		lblNewLabel_2.setBounds(29, 101, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		nama_textField = new JTextField();
		nama_textField.setBounds(178, 98, 278, 20);
		contentPane.add(nama_textField);
		nama_textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Jabatan");
		lblNewLabel_3.setBounds(29, 132, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JComboBox jabatan_comboBox = new JComboBox();
		jabatan_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Owner", "Founder", "Staff IT", "HR"}));
		jabatan_comboBox.setBounds(178, 129, 278, 20);
		contentPane.add(jabatan_comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("Alamat");
		lblNewLabel_4.setBounds(29, 163, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		alamat_textField = new JTextField();
		alamat_textField.setBounds(178, 160, 278, 20);
		contentPane.add(alamat_textField);
		alamat_textField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(29, 194, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		email_textField = new JTextField();
		email_textField.setBounds(178, 191, 278, 20);
		contentPane.add(email_textField);
		email_textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Nomor Telepon");
		lblNewLabel_6.setBounds(29, 225, 80, 14);
		contentPane.add(lblNewLabel_6);
		
		telpon_textField = new JTextField();
		telpon_textField.setBounds(178, 222, 278, 20);
		contentPane.add(telpon_textField);
		telpon_textField.setColumns(10);
		
		JButton tambah_btn = new JButton("Tambah");
		tambah_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kosongkan_form();
			}
		});
		tambah_btn.setBounds(8, 265, 89, 23);
		contentPane.add(tambah_btn);
		
		JButton simpan_btn = new JButton("Simpan");
		simpan_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "INSERT INTO karyawan VALUES ('"+nik_textField.getText()+"','"+nama_textField.getText()+"','"+jabatan_comboBox.getSelectedItem()+"','"+alamat_textField.getText()+"','"+email_textField.getText()+"','"+telpon_textField.getText()+"')";
					java.sql.Connection conn = config.configDB();
					java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
					pstm.execute();
					JOptionPane.showMessageDialog(null, "Berhasil disimpan!");
					tampilkan_data();
					kosongkan_form();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		simpan_btn.setBounds(102, 265, 89, 23);
		contentPane.add(simpan_btn);
		
		JButton edit_btn = new JButton("Edit");
		edit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "UPDATE karyawan SET nik='"+nik_textField.getText()+"', nama='"+nama_textField.getText()+"', jabatan='"+jabatan_comboBox.getSelectedItem()+"', alamat='"+alamat_textField.getText()+"', email='"+email_textField.getText()+"', no_telp='"+telpon_textField.getText()+"' WHERE nik = '"+nik_textField.getText()+"'";
					java.sql.Connection conn = config.configDB();
					java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
					pstm.execute();
					JOptionPane.showMessageDialog(null, "Update data berhasil!");
					kosongkan_form();
					tampilkan_data();
				} catch (SQLException e) {
					JOptionPane.showConfirmDialog(null, "Update data gagal! : "+e.getMessage());
				}
			}
		});
		edit_btn.setBounds(199, 264, 89, 23);
		contentPane.add(edit_btn);
		
		JButton delete_btn = new JButton("Delete");
		delete_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "DELETE FROM karyawan WHERE nik='"+nik_textField.getText()+"' ";
					java.sql.Connection conn = config.configDB();
					java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
					pstm.execute();
					
					JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
					kosongkan_form();
					tampilkan_data();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Data gagal dihapus! : "+e.getMessage());
				}
			}
		});
		delete_btn.setBounds(295, 264, 89, 23);
		contentPane.add(delete_btn);
		
		JButton batal_btn = new JButton("Batal");
		batal_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		batal_btn.setBounds(391, 265, 89, 23);
		contentPane.add(batal_btn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 303, 467, 298);
		contentPane.add(scrollPane);
		
		karyawan_table = new JTable();
		karyawan_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int baris = karyawan_table.rowAtPoint(arg0.getPoint());
				
				String nik = karyawan_table.getValueAt(baris, 1).toString();
				nik_textField.setText(nik);
				
				String nama = karyawan_table.getValueAt(baris, 2).toString();
				nama_textField.setText(nama);
				
				String jabatan = karyawan_table.getValueAt(baris, 3).toString();
				jabatan_comboBox.setSelectedItem(jabatan);
				
				String alamat = karyawan_table.getValueAt(baris, 4).toString();
				alamat_textField.setText(alamat);
				
				String email = karyawan_table.getValueAt(baris, 5).toString();
				email_textField.setText(email);
				
				String telepon = karyawan_table.getValueAt(baris, 6).toString();
				telpon_textField.setText(telepon);
			}
		});
		karyawan_table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"No", "NIK", "Nama", "Jabatan", "Alamat", "Email", "Telepon"
			}
		));
		scrollPane.setViewportView(karyawan_table);
		tampilkan_data();
	}
}
