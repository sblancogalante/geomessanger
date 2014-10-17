package uy.edu.um.laboratoriotic.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Blob;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

public class CreateUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField employeeIDText;
	private JTextField userNameText;
	private JTextField passwordText;
	private JTextField eMailText;
	private JTextField nameText;
	private JTextField lastNameText;
	private JTextField iDText;
	private JTextField positionText;



	
	public CreateUser() {
		setBounds(100, 100, 600, 600);
		
		Dimension d = new Dimension(600,600);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panel_4 = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(7)
							.addComponent(tabbedPane)
							.addGap(5))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(7)
							.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
					.addContainerGap())
		);
		
String[] comboBoxDefaultArray = {"<<Default>>"};
		
		final JComboBox locationComboBox = new JComboBox(comboBoxDefaultArray);
		
		final JComboBox sectorComboBox = new JComboBox(comboBoxDefaultArray);
		
		
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date workingHours = new Date();
				Blob profilePic = null;
				EmployeeVO oEmployee = new EmployeeVO(Integer.parseInt(employeeIDText.getText()), iDText.getText(), nameText.getText(),
						lastNameText.getText(), userNameText.getText(), passwordText.getText(), (String)locationComboBox.getSelectedItem() ,
						(String)sectorComboBox.getSelectedItem(), eMailText.getText(), positionText.getText(),profilePic , workingHours,
						false);
				System.out.println(oEmployee.toString());
				EmployeeMgt employeeMgt = EmployeeFactory.getInstance().getEmployeeMgt();
				try {
					employeeMgt.addEmployee(oEmployee); 
					System.out.println("Se ha creado: " + oEmployee.getUserName());
				} catch (RemoteException | NotBoundException e) {
					//HACER UN DIALOGO QUE T DIGA QE NO SE PUDO HACER EL EMPLOYEE
					e.printStackTrace();
				}
				
				dispose();
				
			}
			
		});
		
		
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(15)
					.addComponent(btnCreateUser, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateUser, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("");
		tabbedPane.addTab("Account", null, panel_1, null);
		
		JLabel lblNoteTheFields = new JLabel("<<NOTE: The fields with (*) are obligatory.>>");
		
		employeeIDText = new JTextField();
		employeeIDText.setColumns(10);
		
		userNameText = new JTextField();
		userNameText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setColumns(10);
		
		JLabel lblEmployeeid = new JLabel("EmployeeID: (*)");
		
		JLabel lblName = new JLabel("User name: (*)");
		
		JLabel lblLastName = new JLabel("Password: (*)");
		
		JLabel lblNewLabel = new JLabel("Location: (*)");
		
		JLabel lblNewLabel_1 = new JLabel("Sector: (*)");
		
		
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEmployeeid)
						.addComponent(lblName)
						.addComponent(lblLastName))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(userNameText, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(employeeIDText, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(passwordText, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)))
					.addGap(35))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(sectorComboBox, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(43))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNoteTheFields, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(234, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(employeeIDText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmployeeid))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(userNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastName))
					.addGap(32)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sectorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(77)
					.addComponent(lblNoteTheFields)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("User Details", null, panel_2, null);
		
		JLabel label = new JLabel("<<NOTE: The fields with (*) are obligatory.>>");
		
		eMailText = new JTextField();
		eMailText.setColumns(10);
		
		JLabel lblEmailAdress = new JLabel("Email Adress: (*)");
		
		nameText = new JTextField();
		nameText.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Name:");
		
		lastNameText = new JTextField();
		lastNameText.setColumns(10);
		
		JLabel lblLastName_1 = new JLabel("Last name:");
		
		iDText = new JTextField();
		iDText.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		
		JLabel lblPosition = new JLabel("Position: ");
		
		positionText = new JTextField();
		positionText.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(234, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPosition)
						.addComponent(lblEmailAdress)
						.addComponent(lblName_1)
						.addComponent(lblLastName_1)
						.addComponent(lblId))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(positionText)
						.addComponent(iDText)
						.addComponent(lastNameText)
						.addComponent(nameText)
						.addComponent(eMailText, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
					.addGap(37))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(eMailText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmailAdress))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName_1))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lastNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastName_1))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(iDText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPosition)
						.addComponent(positionText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
					.addComponent(label)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Other", null, panel_3, null);
		
		JLabel label_1 = new JLabel("<<NOTE: The fields with (*) are obligatory.>>");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(199, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap(292, Short.MAX_VALUE)
					.addComponent(label_1)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblCreateUser = new JLabel("Create User");
		lblCreateUser.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		
		JSeparator separator_1 = new JSeparator();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(191)
							.addComponent(lblCreateUser)
							.addPreferredGap(ComponentPlacement.RELATED, 182, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblCreateUser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(16))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
	}
}
