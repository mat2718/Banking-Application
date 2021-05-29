package customerUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen {

	JFrame frmLoginMenu;
	private JTextField email;
	private JPasswordField passwordField;


	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginMenu = new JFrame();
		frmLoginMenu.setTitle("Banking Application");
		Image baricon = new ImageIcon(this.getClass().getResource("/bank-icon.png")).getImage();
		frmLoginMenu.setIconImage(baricon);
		frmLoginMenu.getContentPane().setForeground(Color.GRAY);
		frmLoginMenu.getContentPane().setBackground(Color.WHITE);
		frmLoginMenu.setBounds(100, 100, 450, 300);
		frmLoginMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginMenu.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Banking Application");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(109, 44, 315, 29);
		frmLoginMenu.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(65, 137, 59, 20);
		frmLoginMenu.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login Here");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(65, 112, 87, 20);
		frmLoginMenu.getContentPane().add(lblNewLabel_2);
		
		email = new JTextField();
		email.setBounds(134, 137, 182, 20);
		frmLoginMenu.getContentPane().add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(63, 168, 61, 20);
		frmLoginMenu.getContentPane().add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(134, 168, 182, 20);
		frmLoginMenu.getContentPane().add(passwordField);
		
		JButton submitlogin = new JButton("Login");
		submitlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		submitlogin.setBackground(Color.LIGHT_GRAY);
		submitlogin.setForeground(Color.BLACK);
		submitlogin.setBounds(134, 199, 89, 23);
		frmLoginMenu.getContentPane().add(submitlogin);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginMenu.setVisible(false);    //setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							RegistrationMenu window = new RegistrationMenu();
							window.frmMemberRegistration.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		register.setBackground(Color.LIGHT_GRAY);
		register.setBounds(227, 199, 89, 23);
		frmLoginMenu.getContentPane().add(register);
		
		JLabel bankicon = new JLabel("");
		bankicon.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/bank-icon.png")).getImage();
		bankicon.setIcon(new ImageIcon(img));
		bankicon.setBounds(10, 11, 103, 96);
		frmLoginMenu.getContentPane().add(bankicon);
	}
}
