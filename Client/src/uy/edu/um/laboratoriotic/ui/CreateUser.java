package uy.edu.um.laboratoriotic.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Blob;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

import javax.swing.JCheckBox;

public class CreateUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField userNameText;
	private JPasswordField repeatPasswordText;
	private JTextField documentText;
	private JTextField nameText;
	private JTextField lastNameText;
	private JTextField eMailText;
	private JTextField positionText;
	private JLabel documentLabel;
	private JLabel nameLabel;
	private JLabel lastNameLabel;
	private JLabel eMailLabel;
	private JLabel positionLabel;
	private JPanel userDetailsPanel;
	private JPanel titlePanel;
	private JTextField passwordText;
	private JLabel passwordLabel;

	public CreateUser() {
		setBounds(100, 100, 600, 635);

		Dimension d = new Dimension(600, 635);
		this.setMinimumSize(d);
		this.setMaximumSize(d);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		titlePanel = new JPanel();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel_4 = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																titlePanel,
																GroupLayout.DEFAULT_SIZE,
																584,
																Short.MAX_VALUE)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(7)
																		.addComponent(
																				tabbedPane)
																		.addGap(5))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(7)
																		.addComponent(
																				panel_4,
																				GroupLayout.DEFAULT_SIZE,
																				571,
																				Short.MAX_VALUE)
																		.addContainerGap()))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(titlePanel, GroupLayout.PREFERRED_SIZE,
								85, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tabbedPane)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 49,
								Short.MAX_VALUE).addContainerGap()));

		String[] comboBoxDefaultArray = { "<<Default>>" };

		final JComboBox locationComboBox = new JComboBox(comboBoxDefaultArray);

		final JComboBox sectorComboBox = new JComboBox(comboBoxDefaultArray);

		JButton createUserButton = new JButton("Create User");
		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Chequear contraseñas y tomar medidas.

				String workingHours = null;
				Blob profilePic = null;
				// EmployeeID lo puse en 0
				EmployeeVO oEmployee = new EmployeeVO(documentText.getText(),
						nameText.getText(), lastNameText.getText(),
						userNameText.getText(), String
								.valueOf(repeatPasswordText.getPassword()),
						(String) locationComboBox.getSelectedItem(),
						(String) sectorComboBox.getSelectedItem(), eMailText
								.getText(), positionText.getText(),
						workingHours, profilePic, false);
				System.out.println(oEmployee.toString());
				EmployeeMgt employeeMgt = EmployeeFactory.getInstance()
						.getEmployeeMgt();
				try {
					employeeMgt.addEmployee(oEmployee);
					System.out.println("Se ha creado: "
							+ oEmployee.getUserName());
				} catch (RemoteException | NotBoundException e) {
					// HACER UN DIALOGO QUE T DIGA QE NO SE PUDO HACER EL
					// EMPLOYEE
					e.printStackTrace();
				}

				dispose();

			}

		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_4
						.createSequentialGroup()
						.addGap(15)
						.addComponent(createUserButton,
								GroupLayout.PREFERRED_SIZE, 127,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 228,
								Short.MAX_VALUE)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE,
								127, GroupLayout.PREFERRED_SIZE).addGap(24)));
		gl_panel_4
				.setVerticalGroup(gl_panel_4
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								gl_panel_4
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_4
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																createUserButton,
																GroupLayout.DEFAULT_SIZE,
																37,
																Short.MAX_VALUE)
														.addComponent(
																cancelButton,
																GroupLayout.PREFERRED_SIZE,
																37,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		panel_4.setLayout(gl_panel_4);

		userDetailsPanel = new JPanel();
		tabbedPane.addTab("User Details", null, userDetailsPanel, null);

		JLabel obligatoryFieldLabel = new JLabel(
				"<<NOTE: The fields with (*) are obligatory.>>");

		documentText = new JTextField();
		documentText.setColumns(10);

		documentLabel = new JLabel("Document:");

		nameText = new JTextField();
		nameText.setColumns(10);

		nameLabel = new JLabel("Name:");

		lastNameText = new JTextField();
		lastNameText.setColumns(10);

		lastNameLabel = new JLabel("Last name:");

		eMailText = new JTextField();
		eMailText.setColumns(10);

		eMailLabel = new JLabel("EMail Address: ");

		positionLabel = new JLabel("Position: ");

		positionText = new JTextField();
		positionText.setColumns(10);

		JLabel lblTypeOfDocument = new JLabel("Type of Document: ");

		JComboBox isAdmin = new JComboBox(comboBoxDefaultArray);

		GroupLayout gl_panel_2 = new GroupLayout(userDetailsPanel);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(obligatoryFieldLabel,
												GroupLayout.PREFERRED_SIZE,
												303, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(242, Short.MAX_VALUE))
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap(74, Short.MAX_VALUE)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																positionLabel)
														.addComponent(
																documentLabel)
														.addComponent(nameLabel)
														.addComponent(
																lastNameLabel)
														.addComponent(
																eMailLabel))
										.addGap(18)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																positionText)
														.addComponent(eMailText)
														.addComponent(
																lastNameText)
														.addComponent(nameText)
														.addComponent(
																documentText,
																GroupLayout.PREFERRED_SIZE,
																355,
																GroupLayout.PREFERRED_SIZE))
										.addGap(36))
						.addGroup(
								Alignment.LEADING,
								gl_panel_2
										.createSequentialGroup()
										.addGap(198)
										.addComponent(lblTypeOfDocument)
										.addGap(18)
										.addComponent(isAdmin,
												GroupLayout.PREFERRED_SIZE,
												105, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(106, Short.MAX_VALUE)));
		gl_panel_2
				.setVerticalGroup(gl_panel_2
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addGap(58)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblTypeOfDocument)
														.addComponent(
																isAdmin,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED, 53,
												Short.MAX_VALUE)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																documentText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																documentLabel))
										.addGap(18)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																nameText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(nameLabel))
										.addGap(18)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lastNameText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lastNameLabel))
										.addGap(18)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																eMailText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																eMailLabel))
										.addGap(18)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																positionLabel)
														.addComponent(
																positionText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(58)
										.addComponent(obligatoryFieldLabel)
										.addContainerGap()));

		userDetailsPanel.setLayout(gl_panel_2);

		JPanel accountPanel = new JPanel();
		accountPanel.setToolTipText("");
		tabbedPane.addTab("Account", null, accountPanel, null);

		JLabel lblNoteTheFields = new JLabel(
				"<<NOTE: The fields with (*) are obligatory.>>");

		userNameText = new JTextField();
		userNameText.setColumns(10);

		repeatPasswordText = new JPasswordField();
		repeatPasswordText.setColumns(10);

		JLabel userNameLabel = new JLabel("User name: ");

		JLabel repeatPassLabel = new JLabel("Repeat Password: ");

		JLabel locationLabel = new JLabel("Location: ");

		JLabel sectorLabel = new JLabel("Sector: ");

		JSeparator separator = new JSeparator();

		JButton addLoactionButton = new JButton("Add Location");

		JButton addSectorButton = new JButton("Add Sector");

		passwordLabel = new JLabel("Password: ");

		passwordText = new JPasswordField();
		passwordText.setColumns(10);

		JCheckBox chckbxAdmin = new JCheckBox("is Admin");
		chckbxAdmin.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GroupLayout gl_panel_1 = new GroupLayout(accountPanel);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addGap(50)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																locationComboBox,
																GroupLayout.PREFERRED_SIZE,
																133,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																locationLabel)
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				addLoactionButton)))
										.addPreferredGap(
												ComponentPlacement.RELATED,
												191, Short.MAX_VALUE)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				addSectorButton))
														.addComponent(
																sectorLabel)
														.addComponent(
																sectorComboBox,
																GroupLayout.PREFERRED_SIZE,
																130,
																GroupLayout.PREFERRED_SIZE))
										.addGap(47))
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addGap(73)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																repeatPassLabel)
														.addComponent(
																userNameLabel)
														.addComponent(
																passwordLabel))
										.addGap(18)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																repeatPasswordText,
																GroupLayout.DEFAULT_SIZE,
																337,
																Short.MAX_VALUE)
														.addComponent(
																passwordText,
																GroupLayout.DEFAULT_SIZE,
																337,
																Short.MAX_VALUE)
														.addComponent(
																userNameText,
																GroupLayout.DEFAULT_SIZE,
																337,
																Short.MAX_VALUE))
										.addGap(35))
						.addGroup(
								Alignment.TRAILING,
								gl_panel_1
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(separator,
												GroupLayout.DEFAULT_SIZE, 539,
												Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																chckbxAdmin)
														.addComponent(
																lblNoteTheFields,
																GroupLayout.PREFERRED_SIZE,
																303,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(242, Short.MAX_VALUE)));
		gl_panel_1
				.setVerticalGroup(gl_panel_1
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addGap(32)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																userNameLabel)
														.addComponent(
																userNameText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																passwordLabel)
														.addComponent(
																passwordText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																repeatPassLabel)
														.addComponent(
																repeatPasswordText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(chckbxAdmin)
										.addGap(31)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																locationLabel)
														.addComponent(
																sectorLabel))
										.addGap(18)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																locationComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																sectorComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																addLoactionButton)
														.addComponent(
																addSectorButton))
										.addGap(42)
										.addComponent(lblNoteTheFields)
										.addContainerGap()));
		accountPanel.setLayout(gl_panel_1);

		JPanel otherPanel = new JPanel();
		tabbedPane.addTab("Other", null, otherPanel, null);

		JLabel label_1 = new JLabel(
				"<<NOTE: The fields with (*) are obligatory.>>");

		JLabel lblAddPhoto = new JLabel("Add Photo: ");

		JButton btnSelectPhoto = new JButton("Select photo ");
		GroupLayout gl_panel_3 = new GroupLayout(otherPanel);
		gl_panel_3
				.setHorizontalGroup(gl_panel_3
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_3
										.createSequentialGroup()
										.addGroup(
												gl_panel_3
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_3
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				label_1,
																				GroupLayout.PREFERRED_SIZE,
																				303,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panel_3
																		.createSequentialGroup()
																		.addGap(72)
																		.addComponent(
																				lblAddPhoto)
																		.addGap(18)
																		.addComponent(
																				btnSelectPhoto)))
										.addContainerGap(242, Short.MAX_VALUE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(
						gl_panel_3
								.createSequentialGroup()
								.addGap(157)
								.addGroup(
										gl_panel_3
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(lblAddPhoto)
												.addComponent(btnSelectPhoto))
								.addPreferredGap(ComponentPlacement.RELATED,
										213, Short.MAX_VALUE)
								.addComponent(label_1).addContainerGap()));
		otherPanel.setLayout(gl_panel_3);

		JLabel lblCreateUser = new JLabel("Create User");
		lblCreateUser.setFont(new Font("Lucida Grande", Font.PLAIN, 28));

		JSeparator separator_1 = new JSeparator();
		GroupLayout gl_panel = new GroupLayout(titlePanel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(191)
																.addComponent(
																		lblCreateUser)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		182,
																		Short.MAX_VALUE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		separator_1,
																		GroupLayout.DEFAULT_SIZE,
																		523,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addGap(5)
						.addComponent(lblCreateUser, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addGap(16)));
		titlePanel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
	}
}
