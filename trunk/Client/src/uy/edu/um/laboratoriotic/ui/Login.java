package uy.edu.um.laboratoriotic.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeFilterVO;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userText;
	private JPasswordField passwordText;
	private JButton loginButton;
	JButton quitButton;


	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 147, 273);
		
		
		final EmployeeMgt employeeMgr =  EmployeeFactory.getInstance().getEmployeeMgt();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		JLabel loginLabel = new JLabel("Log in");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		final JLabel errorLabel = new JLabel("Incorrect Password!");
		errorLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		errorLabel.setForeground(Color.RED);
		
		userText = new JTextField("User name");
		userText.setForeground(Color.LIGHT_GRAY);
		userText.setColumns(10);
		// User name text field,changes of color when foucs
				userText.addFocusListener(new FocusListener() {

					public void focusGained(FocusEvent e) {
						if (userText.getText().equals("User name")) {
							userText.setText("");
							userText.setForeground(Color.BLACK);
						}
						errorLabel.setVisible(false);
					}

					public void focusLost(FocusEvent e) {
						if (userText.getText().equals("")) {
							userText.setText("User name");
							userText.setForeground(Color.LIGHT_GRAY);
						}
					}
				});
		
		passwordText = new JPasswordField("Password");
		passwordText.setForeground(Color.LIGHT_GRAY);
		// Password text field,changes of color when foucs
				passwordText.addFocusListener(new FocusListener() {

					public void focusGained(FocusEvent e) {
						if (String.valueOf(passwordText.getPassword()).equals(
								"Password")) {
							passwordText.setText("");
							passwordText.setForeground(Color.BLACK);
						}
						errorLabel.setVisible(false);
					}

					public void focusLost(FocusEvent e) {

						if (String.valueOf(passwordText.getPassword()).equals("")) {
							passwordText.setText("Password");
							passwordText.setForeground(Color.LIGHT_GRAY);
						}

					}

				});
				
		passwordText.setColumns(10);
		
		loginButton = new JButton("Log in");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow wind;
				try {
					
					String inputUserName = userText.getText();
					String inputPassword = String.valueOf(passwordText.getPassword());
					EmployeeFilterVO inputEmployeeVO = new EmployeeFilterVO(inputUserName, inputPassword);
		
					if(employeeMgr.checkLogin(inputEmployeeVO)){
						wind = new MainWindow(inputEmployeeVO );
						wind.setVisible(true);
						dispose();
					}else{
						errorLabel.setVisible(true);
					}
				} catch (RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("User name: " + userText.getText());
				System.out.println("Password: "
						+ String.valueOf(passwordText.getPassword()));
				

			}
		});
		
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JSeparator separator = new JSeparator();
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(quitButton, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(userText, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(errorLabel)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(loginLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(userText, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(quitButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(errorLabel)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	public JFrame getFrame(){
		return this;
	}
}
