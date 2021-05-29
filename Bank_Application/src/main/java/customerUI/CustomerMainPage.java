package customerUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.List;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import model.BankPOJO;

import javax.swing.JPasswordField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JToolBar;
import net.miginfocom.swing.MigLayout;

public class CustomerMainPage {

	JFrame MemberRegistration;

	/**
	 * Create the application.
	 */
	public CustomerMainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MemberRegistration = new JFrame();
		MemberRegistration.getContentPane().setBackground(Color.WHITE);
		MemberRegistration.setTitle("Customer Page");
		Image baricon = new ImageIcon(this.getClass().getResource("/bank-icon.png")).getImage();
		MemberRegistration.setIconImage(baricon);
		MemberRegistration.setBounds(100, 100, 666, 426);
		MemberRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MemberRegistration.getContentPane().setLayout(new MigLayout("", "[407.00px]", "[96px][][grow]"));
		
		JLabel bankicon = new JLabel("");
		bankicon.setHorizontalAlignment(SwingConstants.CENTER);
		bankicon.setIcon(new ImageIcon(img));
		MemberRegistration.getContentPane().add(bankicon, "flowx,cell 0 0,alignx left,aligny top");
		
		JLabel lblNewLabel = new JLabel("Banking Application");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		MemberRegistration.getContentPane().add(lblNewLabel, "cell 0 0,alignx right,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		MemberRegistration.getContentPane().add(lblNewLabel_1, "cell 0 1");
		
		List list = new List();
		MemberRegistration.getContentPane().add(list, "cell 0 2");
		
		JMenuBar menuBar = new JMenuBar();
		MemberRegistration.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Account Management");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Account Actions");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Member Account");
		menuBar.add(mnNewMenu_2);
		Image img = new ImageIcon(this.getClass().getResource("/bank-icon.png")).getImage();
		
		BankPOJO pojo = new BankPOJO();
	}
}
