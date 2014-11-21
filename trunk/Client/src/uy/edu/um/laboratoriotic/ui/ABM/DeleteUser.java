package uy.edu.um.laboratoriotic.ui.ABM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.ui.ErrorDialog;

public class DeleteUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField searchUserText;
	JLabel ChooseUserLabel;

	public DeleteUser() throws RemoteException, NotBoundException {
		
		final EmployeeMgt employeeMgt = EmployeeFactory.getInstance()
				.getEmployeeMgt();
		
		
		this.setTitle("Delete User");
		setBounds(100, 100, 450, 410);
		Dimension d = new Dimension(450,410);
		//this.setMaximumSize(d);
		this.setMinimumSize(d);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel deleteUserLabel = new JLabel("Delete User");
		deleteUserLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		JSeparator separator = new JSeparator();
		
		ChooseUserLabel = new JLabel("Choose a user: ");
		
		final JList<EmployeeVO> userList = new JList<EmployeeVO>();
		
		userList.setCellRenderer(new ListCellRenderer<EmployeeVO>() {

			@Override
			public Component getListCellRendererComponent(
					JList<? extends EmployeeVO> list, EmployeeVO value,
					int index, boolean isSelected, boolean cellHasFocus) {
				
				JLabel oLabel = new JLabel();	
				if(isSelected){
					oLabel.setForeground(Color.BLUE);
				}
				oLabel.setText(value.getName()+" "+value.getLastName() + " "+ index);
				return oLabel;
			}
		});
		
		//AGREGA LOS EMPLEADOS CONOCIDOS
		if (employeeMgt.getEmployees() != null && employeeMgt.getEmployees().size() > 0) {
			
			DefaultListModel<EmployeeVO> employeeListModel = new DefaultListModel<EmployeeVO>();
			fillDefaultListFromArray(employeeMgt.getEmployees(), employeeListModel);
			userList.setModel(employeeListModel);
			
		} 
		
		searchUserText = new JTextField();
		searchUserText.setText("");
		searchUserText.setForeground(Color.BLACK);
		searchUserText.setColumns(10);
		// User name text field,changes of color when foucs
		searchUserText.addFocusListener(new FocusListener() {	
			public void focusGained(FocusEvent e) {
				if(searchUserText.getText().equals("Search user")){
					searchUserText.setText("");
					searchUserText.setForeground(Color.BLACK);
				 }
			}

			public void focusLost(FocusEvent e) {
				if(searchUserText.getText().equals("")){
					searchUserText.setText("Search user");
					searchUserText.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		
		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(167)
							.addComponent(deleteUserLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(164))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(44)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(ChooseUserLabel)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(separator, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
									.addGap(51))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(userList, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
									.addGap(18))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(searchUserText, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))))
					.addGap(21))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(deleteUserLabel)
					.addGap(20)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(ChooseUserLabel)
					.addGap(8)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchUserText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(userList, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addGap(15))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton deleteButton = new JButton("DELETE");
				this.getRootPane().setDefaultButton(deleteButton);
				deleteButton.setBackground(UIManager.getColor("Button.foreground"));
				deleteButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {

						int nvalue = JOptionPane.showConfirmDialog(DeleteUser.this, "Are you sure you want to Delete : " 
						+ userList.getSelectedValue().getName() + userList.getSelectedValue().getLastName() + " ?");
			
						if (nvalue == 0){
							
							try {
								System.out.println(userList.getSelectedIndex());
								employeeMgt.removeEmployee(userList.getSelectedValue());
								dispose();
							} catch (RemoteException | NotBoundException e) {
								ErrorDialog errorDialog = new ErrorDialog("Se ha producido un error al intentar eliminar el usuario."
										+ " \n \n ERROR: " + e.getMessage());
								e.printStackTrace();
							}
							
						}else{
							
							
						}
					}
				});
				
				deleteButton.setActionCommand("Delete");
				buttonPane.add(deleteButton);
				getRootPane().setDefaultButton(deleteButton);
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent args0){
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
private void fillDefaultListFromArray(ArrayList<EmployeeVO> arrayList,DefaultListModel<EmployeeVO> lModel){
		
		for(EmployeeVO employee : arrayList){
			lModel.add(lModel.getSize(),employee);
		}
		
	}
}