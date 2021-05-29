package customerUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.InputMethodListener;
import java.util.Arrays;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegistrationMenu {

	JFrame frmMemberRegistration;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField email;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	
	
	public RegistrationMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMemberRegistration = new JFrame();
		frmMemberRegistration.getContentPane().setBackground(Color.WHITE);
		frmMemberRegistration.setTitle("Member Registration");
		Image baricon = new ImageIcon(this.getClass().getResource("/bank-icon.png")).getImage();
		frmMemberRegistration.setIconImage(baricon);
		frmMemberRegistration.setBounds(100, 100, 666, 426);
		frmMemberRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMemberRegistration.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Banking Application");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(109, 44, 315, 29);
		frmMemberRegistration.getContentPane().add(lblNewLabel);
		
		JLabel bankicon = new JLabel("");
		bankicon.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/bank-icon.png")).getImage();
		bankicon.setIcon(new ImageIcon(img));
		bankicon.setBounds(10, 11, 103, 96);
		frmMemberRegistration.getContentPane().add(bankicon);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(551, 353, 89, 23);
		frmMemberRegistration.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(452, 353, 89, 23);
		frmMemberRegistration.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Email: *");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(26, 171, 103, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password: *");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 196, 119, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Confirm Password: *");
		lblNewLabel_3.setVisible(false);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(10, 221, 119, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Account Information");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(20, 146, 302, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_5);
		
		JLabel pswdmatches = new JLabel("");
//		Image deny = new ImageIcon(this.getClass().getResource("/deny-icon.png")).getImage();
//		pswdmatches.setIcon(new ImageIcon(deny));
		pswdmatches.setBounds(332, 221, 23, 14);
		frmMemberRegistration.getContentPane().add(pswdmatches);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 193, 183, 20);
		frmMemberRegistration.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setVisible(false);
		passwordField_1.setBounds(139, 218, 183, 20);
		frmMemberRegistration.getContentPane().add(passwordField_1);
	
		email = new JTextField();
		email.setBounds(138, 168, 184, 20);
		frmMemberRegistration.getContentPane().add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_5_1 = new JLabel("Member Information");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(352, 148, 302, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_4 = new JLabel("First Name:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(352, 168, 72, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Last Name:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(352, 196, 72, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Address:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(352, 221, 72, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("State:");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7_1.setBounds(352, 246, 72, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_2 = new JLabel("Zip Code:");
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7_2.setBounds(352, 271, 72, 14);
		frmMemberRegistration.getContentPane().add(lblNewLabel_7_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(434, 168, 184, 20);
		frmMemberRegistration.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(434, 193, 184, 20);
		frmMemberRegistration.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(434, 218, 184, 20);
		frmMemberRegistration.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(434, 243, 184, 20);
		frmMemberRegistration.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(434, 268, 184, 20);
		frmMemberRegistration.getContentPane().add(textField_4);
		
		
	}
}
