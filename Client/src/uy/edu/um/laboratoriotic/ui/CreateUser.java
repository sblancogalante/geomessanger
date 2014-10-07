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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import uy.edu.um.laboratoriotic.services.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.EmployeeVO;

public class CreateUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnCancel;
	private EmployeeMgt employeeMgt;

	public CreateUser() {
		
		setBounds(100, 100, 450, 350);
		
		Dimension d = new Dimension(450,350);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCreateUser = new JLabel("Create User");
		lblCreateUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateUser.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JSeparator separator = new JSeparator();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		lblNewLabel = new JLabel("User Name: ");
		
		lblNewLabel_1 = new JLabel("Password:");
		
		lblNewLabel_2 = new JLabel("Email adress:");
		
		lblNewLabel_3 = new JLabel("Sector:");
		
		lblNewLabel_4 = new JLabel("Working Hour:");
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				EmployeeVO employee = new EmployeeVO(textField.getText(),textField_1.getText(),textField_2.getText(),true);
				employeeMgt = EmployeeFactory.getInstance().getEmployeeMgt();
				try {
					employeeMgt.addEmployee(employee);
				} catch (RemoteException | NotBoundException e) {
					//HACER UN DIALOGO QUE T DIGA QE NO SE PUDO HACER EL EMPLOYEE
					e.printStackTrace();
				}
				System.out.println("Se ha creado: " + employee.getUserName());
				dispose();
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				dispose();
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(162)
					.addComponent(lblCreateUser, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(180, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(87)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_4, Alignment.LEADING)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
						.addComponent(textField_1, Alignment.LEADING)
						.addComponent(textField_2, Alignment.LEADING)
						.addComponent(textField_3, Alignment.LEADING))
					.addGap(85))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(41)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
					.addGap(27))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(252, Short.MAX_VALUE)
					.addComponent(btnCreate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCreateUser)
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreate)
						.addComponent(btnCancel))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
