package uy.edu.um.laboratoriotic.ui.ABM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Blob;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.exceptions.employee.MissingArguments;
import uy.edu.um.laboratoriotic.exceptions.employee.PasswordTooShort;
import uy.edu.um.laboratoriotic.exceptions.employee.UserNameAlreadyExists;
import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.factory.general.GeneralFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;
import uy.edu.um.laboratoriotic.ui.ErrorDialog;

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
	private JPasswordField passwordText;
	private JLabel passwordLabel;
	private String photoPath;
	private JLabel testPhotoLabel;
	private ImageIcon testPhoto;
	private byte[] photoBytes;

	public CreateUser() {

		this.setTitle("Crear User");
		setBounds(100, 100, 600, 635);

		Dimension d = new Dimension(600, 635);
		
		this.setMinimumSize(d);
		this.setMaximumSize(d);

		photoPath = "Images/Foto.png";

		final EmployeeMgt employeeMgt = EmployeeFactory.getInstance()
				.getEmployeeMgt();
		
		final GeneralMgt generalMgr = GeneralFactory.getInstance().getGeneralMgt();
			
		
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

		
		String[] comboBoxLocations;
		
		String[] comboBoxSectors;
		
		String[] comboBoxTypeDocument;
		
		JLabel workingHoursLabel = new JLabel("Working Hours: ");
		
		String[] hours = {"00","01","02","03","04","05","06","07","08","09","10","11","12",
				"13","14","15","16","17","18","19","20","21","22","23"};
		
		final JComboBox firstHour = new JComboBox(hours);
		
		JLabel toLabel = new JLabel("to");
		
		String[] locationVector = {};
		String[] sectorVector = {};
		String[] documentVector = {};
		try {
			ArrayList<TypeVO> locationTypeArray = generalMgr.getTypes("Pais");
			ArrayList<TypeVO> sectorTypeArray = generalMgr.getTypes("Sector");
			ArrayList<TypeVO> documentTypeArray = generalMgr.getTypes("Documento");
			locationVector = arrayTypeToString(locationTypeArray);
			sectorVector = arrayTypeToString(sectorTypeArray);
			documentVector = arrayTypeToString(documentTypeArray);
			
			
			
			
		} catch (RemoteException | NotBoundException e1) {
			ErrorDialog typeError = new ErrorDialog("Se ha producido un error al comunicarse con la base de datos."
					+ " No se consiguieron los paises, secotres ni documentos"
					+ "existentes. \n \n ERROR: " + e1.getMessage());
			typeError.setVisible(true);
			e1.printStackTrace();
		}
		
		
		final JComboBox<String> secondHour = new JComboBox(hours);

		final JComboBox<String> locationComboBox = new JComboBox(locationVector);

		final JComboBox<String> sectorComboBox = new JComboBox(sectorVector);

		final JCheckBox isAdminCheckBox = new JCheckBox("is Admin");

		final JLabel documentTypeLabel = new JLabel("Type of Document: ");

		final JComboBox<String> typeDocumentComboBox = new JComboBox(
				documentVector);

		JButton createUserButton = new JButton("Create User");
		this.getRootPane().setDefaultButton(createUserButton);
		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Chequear contraseñas y tomar medidas.

				String workingHours = "From "+ firstHour.getSelectedItem() + " to " + secondHour.getSelectedItem();
				
				photoBytes = convertImageToBytes(new File(photoPath));

				TypeVO oTypeVODocument = new TypeVO("Document",
						(String) typeDocumentComboBox.getSelectedItem());
				TypeVO oTypeVOLocation = new TypeVO("Location",
						(String) locationComboBox.getSelectedItem());
				TypeVO oTypeVOSector = new TypeVO("Sector",
						(String) sectorComboBox.getSelectedItem());
				
				

				EmployeeVO oEmployee = new EmployeeVO(oTypeVODocument,
						documentText.getText(), nameText.getText(),
						lastNameText.getText(), userNameText.getText(), String
								.valueOf(repeatPasswordText.getPassword()),
						oTypeVOLocation, oTypeVOSector, eMailText.getText(),
						positionText.getText(), workingHours, photoBytes,
						false, isAdminCheckBox.isSelected());

				

				try {
					String pass1 = String.valueOf(passwordText.getPassword());
					String pass2 = String.valueOf(repeatPasswordText
							.getPassword());
					
					EmployeeVO verifyUserName = employeeMgt.searchEmployee(oEmployee.getUserName());
					
					if (pass1.equals(pass2) && verifyUserName == null && pass1.length() <= 5) {
						try {
							employeeMgt.addEmployee(oEmployee);
						} catch (PasswordTooShort e) {
							e.printStackTrace();
						} catch (UserNameAlreadyExists e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MissingArguments e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Se ha creado: "
								+ oEmployee.getUserName());
						dispose();
					} else if(!pass1.equals(pass2)) {
						ErrorDialog errorDialogPassword = new ErrorDialog(
								"Se ha detectado un error, las contraseñas ingresadas deben ser iguales. Porfavor intente nuevamente.");
						errorDialogPassword.setVisible(true);
						passwordText.setText("");
						repeatPasswordText.setText("");
					}else if (!(verifyUserName == null)){
						ErrorDialog errorDialogUserName = new ErrorDialog(
								"Se ha detectado un error, el nombre de usuario ingresado ya existe. Porfavor intente nuevamente.");
						userNameText.setText("");
						errorDialogUserName.setVisible(true);
					}else if(pass1.length() <= 5){
						ErrorDialog errorDialog = new ErrorDialog(
								"La constreña es muy corta intente nuevamente.");
						errorDialog.setVisible(true);
						passwordText.setText("");
						repeatPasswordText.setText("");
						
					}
				} catch (RemoteException e) {
					ErrorDialog errorDialog = new ErrorDialog(
							"Se ha producido un error al intentar guardar el usuario. \n \n ERROR: "
									+ e.getMessage());
					errorDialog.setVisible(true);
					e.printStackTrace();
				} catch (EmployeeDoesNotExist e) {
					ErrorDialog error = new ErrorDialog("Ha ocurrido un error, no se encontro al empleado. \n\n ERROR: "
							+ e.getMessage());
					error.setVisible(true);
					e.printStackTrace();
				} catch (EmployeeAlreadyExists e) {
					ErrorDialog errorDialogUserName = new ErrorDialog(
							"Se ha detectado un error, el nombre de usuario ingresado ya existe. Porfavor intente nuevamente.");
					userNameText.setText("");
					errorDialogUserName.setVisible(true);
					e.printStackTrace();
				} catch (NotBoundException e1) {
					ErrorDialog error = new ErrorDialog("Ha ocurrido un error al intentar conectarse con el servidor. \n\n ERROR: "
							+ e1.getMessage());
					error.setVisible(true);
					e1.printStackTrace();
				} 

			}

			private byte[] convertImageToBytes(File fileInput) {
				
				FileInputStream fileInputStream=null;
				 
		        File file = fileInput;
		 
		        byte[] bFile = new byte[(int) file.length()];
		 
		        try {
		            //convert file into array of bytes
			    fileInputStream = new FileInputStream(file);
			    fileInputStream.read(bFile);
			    fileInputStream.close();
		 
			    System.out.println("The file has been converted to bytes, sucesfully.");
		        }catch(Exception e){
		        	ErrorDialog error = new ErrorDialog("Theres has been an error. \n\n ERROR: "+ e.getMessage());
					error.setVisible(true);
		        	e.printStackTrace();
		        }
		        
		        
		        return bFile;
			}

		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
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
										.addComponent(documentTypeLabel)
										.addGap(18)
										.addComponent(typeDocumentComboBox,
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
																documentTypeLabel)
														.addComponent(
																typeDocumentComboBox,
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

		JButton addLocationButton = new JButton("Add Location");
		addLocationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewLocation newLocation = new NewLocation();
				newLocation.setVisible(true);
			}
		});

		JButton addSectorButton = new JButton("Add Sector");

		passwordLabel = new JLabel("Password: ");

		passwordText = new JPasswordField();
		passwordText.setColumns(10);

		isAdminCheckBox.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		
		GroupLayout gl_panel_1 = new GroupLayout(accountPanel);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(locationLabel)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(addLocationButton)))
					.addPreferredGap(ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(addSectorButton))
						.addComponent(sectorLabel)
						.addComponent(sectorComboBox, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
					.addGap(47))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(73)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(repeatPassLabel)
						.addComponent(userNameLabel)
						.addComponent(passwordLabel))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(repeatPasswordText, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
						.addComponent(passwordText, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
						.addComponent(userNameText, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
					.addGap(35))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNoteTheFields, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(242, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addGap(124)
					.addComponent(isAdminCheckBox)
					.addGap(49)
					.addComponent(workingHoursLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(firstHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(toLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(secondHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameLabel)
						.addComponent(userNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(repeatPassLabel)
						.addComponent(repeatPasswordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(isAdminCheckBox)
						.addComponent(workingHoursLabel)
						.addComponent(firstHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(toLabel)
						.addComponent(secondHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(locationLabel)
						.addComponent(sectorLabel))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sectorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(addLocationButton)
						.addComponent(addSectorButton))
					.addGap(42)
					.addComponent(lblNoteTheFields)
					.addContainerGap())
		);
		accountPanel.setLayout(gl_panel_1);

		JPanel otherPanel = new JPanel();
		tabbedPane.addTab("Other", null, otherPanel, null);

		JLabel label_1 = new JLabel(
				"<<NOTE: The fields with (*) are obligatory.>>");

		JLabel addPhotoLabel = new JLabel("Add Photo: ");
		testPhotoLabel = new JLabel(rescaleImage(new File(photoPath), 384, 256));
		testPhoto = rescaleImage(new File(photoPath), 384, 256);
		
		JButton selectPhotoButton = new JButton("Select photo ");
		selectPhotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				photoPath = pickPath(jFileChooser);
				if (testPhoto != null) {
					if (photoPath != null) {
						final ImageIcon testPhoto = rescaleImage(new File(
								photoPath), 384, 256);
						testPhotoLabel.setIcon(testPhoto);
					} else {
						photoPath = "Images/Foto.png";
						final ImageIcon testPhoto = rescaleImage(new File(
								photoPath), 384, 256);
						testPhotoLabel.setIcon(testPhoto);
					}

				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));

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
																		.addGap(73)
																		.addGroup(
																				gl_panel_3
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								panel,
																								GroupLayout.PREFERRED_SIZE,
																								400,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_panel_3
																										.createSequentialGroup()
																										.addComponent(
																												addPhotoLabel)
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												selectPhotoButton)))))
										.addContainerGap(78, Short.MAX_VALUE)));
		gl_panel_3
				.setVerticalGroup(gl_panel_3
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_3
										.createSequentialGroup()
										.addGap(50)
										.addGroup(
												gl_panel_3
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																addPhotoLabel)
														.addComponent(
																selectPhotoButton))
										.addGap(18)
										.addComponent(panel,
												GroupLayout.DEFAULT_SIZE, 272,
												Short.MAX_VALUE).addGap(18)
										.addComponent(label_1)
										.addContainerGap()));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(testPhotoLabel, GroupLayout.DEFAULT_SIZE,
								384, Short.MAX_VALUE).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(testPhotoLabel, GroupLayout.DEFAULT_SIZE,
								256, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel);
		otherPanel.setLayout(gl_panel_3);

		JLabel createUserLabel = new JLabel("Create User");
		createUserLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 28));

		JSeparator separator_1 = new JSeparator();
		GroupLayout gl_panel1 = new GroupLayout(titlePanel);
		gl_panel1
				.setHorizontalGroup(gl_panel1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel1
										.createSequentialGroup()
										.addGroup(
												gl_panel1
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_panel1
																		.createSequentialGroup()
																		.addGap(191)
																		.addComponent(
																				createUserLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				182,
																				Short.MAX_VALUE))
														.addGroup(
																gl_panel1
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				separator_1,
																				GroupLayout.DEFAULT_SIZE,
																				523,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		gl_panel1.setVerticalGroup(gl_panel1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel1
						.createSequentialGroup()
						.addGap(5)
						.addComponent(createUserLabel,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addGap(16)));
		titlePanel.setLayout(gl_panel1);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	// METODOS


	private String[] arrayTypeToString(ArrayList<TypeVO> typeArray) {
		
		String[] oReturn = new String[typeArray.size()];
		int index=0;
		for(TypeVO oType : typeArray){
			oReturn[index]=(oType.getValue());
			index++;
		}
		return oReturn;
	}

	private static String pickPath(JFileChooser fileChooser) {
		String path = null;
		JDialog dialog = new JDialog();
		int returnVal = fileChooser.showOpenDialog(dialog);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = fileChooser.getSelectedFile().getPath();
		}
		return path;
	}

	// resize image
	private ImageIcon rescaleImage(File source, int maxHeight, int maxWidth) {
		int newHeight = 0, newWidth = 0; // Variables for the new height and
											// width
		int priorHeight = 0, priorWidth = 0;
		BufferedImage image = null;
		ImageIcon sizeImage;

		try {
			image = ImageIO.read(source); // get the image
		} catch (Exception e) {
			ErrorDialog error = new ErrorDialog(
					"Se ha producido un error, al intentar cargar una imagen. \n\n ERROR: "
							+ e.getMessage());
			error.setVisible(true);
			e.printStackTrace();

		}

		sizeImage = new ImageIcon(image);

		if (sizeImage != null) {

			priorHeight = sizeImage.getIconHeight();
			priorWidth = sizeImage.getIconWidth();
		}

		// Calculate the correct new height and width
		if ((float) priorHeight / (float) priorWidth > (float) maxHeight
				/ (float) maxWidth) {

			newHeight = maxHeight;
			newWidth = (int) (((float) priorWidth / (float) priorHeight) * (float) newHeight);
		} else {

			newWidth = maxWidth;
			newHeight = (int) (((float) priorHeight / (float) priorWidth) * (float) newWidth);
		}

		// Resize the image

		// 1. Create a new Buffered Image and Graphic2D object
		BufferedImage resizedImg = new BufferedImage(newWidth, newHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImg.createGraphics();

		// 2. Use the Graphic object to draw a new image to the image in the
		// buffer
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, 0, 0, newWidth, newHeight, null);
		g2.dispose();

		// 3. Convert the buffered image into an ImageIcon for return
		return (new ImageIcon(resizedImg));
	}
}
	
