package uy.edu.um.laboratoriotic.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.UIManager;

public class DeleteUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField searchUserText;
	JLabel ChooseUserLabel;

	public DeleteUser() {
		
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
		
		JList list = new JList();
		
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
									.addComponent(list, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
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
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addGap(15))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton deleteButton = new JButton("DELETE");
				deleteButton.setBackground(UIManager.getColor("Button.foreground"));
				deleteButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {

						int nvalue = JOptionPane.showConfirmDialog(DeleteUser.this, "Are you sure you want to Delete : " + "userName " + "?");
			
						if (nvalue == 0){
							//DEELTE USER
							
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
}
