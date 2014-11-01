package uy.edu.um.laboratoriotic.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Blob;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.io.File;

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
				
				
				
				EmployeeVO oEmployee = new EmployeeVO(documentText.getText(),
						nameText.getText(), lastNameText.getText(),
						userNameText.getText(), String
								.valueOf(repeatPasswordText.getPassword()),
						(String) locationComboBox.getSelectedItem(),
						(String) sectorComboBox.getSelectedItem(), eMailText
								.getText(), positionText.getText(),
						workingHours, profilePic, false);
				
				EmployeeMgt employeeMgt = EmployeeFactory.getInstance()
						.getEmployeeMgt();
				
				
				try {
					String pass1 = String.valueOf(passwordText.getPassword());
					String pass2 = String.valueOf(repeatPasswordText.getPassword());
					if(pass1.equals(pass2)){
						employeeMgt.addEmployee(oEmployee);
						System.out.println("Se ha creado: "
								+ oEmployee.getUserName());
						dispose();
					}else{
						ErrorDialog errorDialog = new ErrorDialog("Se ha detectado un error, las contraseñas ingresadas deben ser iguales. Porfavor intente nuevamente.");
						errorDialog.setVisible(true);
						passwordText.setText("");
						repeatPasswordText.setText("");
					}
				} catch (RemoteException | NotBoundException e) {
					ErrorDialog errorDialog = new ErrorDialog("Se ha producido un error al intentar guardar el usuario. \n \n ERROR: " + e.getMessage());
					errorDialog.setVisible(true);
					e.printStackTrace();
				}

				

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

		JLabel addPhotoLabel = new JLabel("Add Photo: ");

		JButton selectPhotoButton = new JButton("Select photo ");
		selectPhotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				photoPath = pickPath(jFileChooser);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		
		
		GroupLayout gl_panel_3 = new GroupLayout(otherPanel);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(73)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(addPhotoLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(selectPhotoButton)))))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(addPhotoLabel)
						.addComponent(selectPhotoButton))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(label_1)
					.addContainerGap())
		);
		
		//ImageIcon testPhoto = rescaleImage(new File(photoPath),384,256);
		
		testPhotoLabel = new JLabel("");
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(testPhotoLabel, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(testPhotoLabel, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		otherPanel.setLayout(gl_panel_3);

		JLabel createUserLabel = new JLabel("Create User");
		createUserLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 28));

		JSeparator separator_1 = new JSeparator();
		GroupLayout gl_panel1 = new GroupLayout(titlePanel);
		gl_panel1.setHorizontalGroup(gl_panel1
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel1.createSequentialGroup()
								.addGroup(
										gl_panel1.createParallelGroup(
												Alignment.TRAILING)
												.addGroup(
														gl_panel1.createSequentialGroup()
																.addGap(191)
																.addComponent(
																		createUserLabel)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		182,
																		Short.MAX_VALUE))
												.addGroup(
														gl_panel1.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		separator_1,
																		GroupLayout.DEFAULT_SIZE,
																		523,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		gl_panel1.setVerticalGroup(gl_panel1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel1.createSequentialGroup()
						.addGap(5)
						.addComponent(createUserLabel, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addGap(16)));
		titlePanel.setLayout(gl_panel1);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	
	public static String pickPath(JFileChooser fileChooser){
		  String path=null;
		  JDialog dialog=new JDialog();
		  int returnVal=fileChooser.showOpenDialog(dialog);
		  if (returnVal == JFileChooser.APPROVE_OPTION) {
		    path=fileChooser.getSelectedFile().getPath();
		  }
		  return path;
		}
	

	//resize image
	public ImageIcon rescaleImage(File source,int maxHeight, int maxWidth){
	     int newHeight = 0, newWidth = 0;        // Variables for the new height and width
	     int priorHeight = 0, priorWidth = 0;
	     BufferedImage image = null;
	     ImageIcon sizeImage;

	     try {
	             image = ImageIO.read(source);        // get the image
	     } catch (Exception e) {

	             e.printStackTrace();
	             System.out.println("Picture upload attempted & failed");
	     }

	     sizeImage = new ImageIcon(image);

	     if(sizeImage != null){
	    	 
	         priorHeight = sizeImage.getIconHeight(); 
	         priorWidth = sizeImage.getIconWidth();
	     }

	     // Calculate the correct new height and width
	     if((float)priorHeight/(float)priorWidth > (float)maxHeight/(float)maxWidth){
	     
	         newHeight = maxHeight;
	         newWidth = (int)(((float)priorWidth/(float)priorHeight)*(float)newHeight);
	     }else{
	    	 
	         newWidth = maxWidth;
	         newHeight = (int)(((float)priorHeight/(float)priorWidth)*(float)newWidth);
	     }


	     // Resize the image

	     // 1. Create a new Buffered Image and Graphic2D object
	     BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
	     Graphics2D g2 = resizedImg.createGraphics();

	     // 2. Use the Graphic object to draw a new image to the image in the buffer
	     g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	     g2.drawImage(image, 0, 0, newWidth, newHeight, null);
	     g2.dispose();

	     // 3. Convert the buffered image into an ImageIcon for return
	     return (new ImageIcon(resizedImg));
	 }
	
	private void refreshView(){
		
		ImageIcon testPhoto = rescaleImage(new File(photoPath),384,256);
		if(testPhoto != null){
			testPhotoLabel = new JLabel(testPhoto);
		}else{
			testPhotoLabel = new JLabel("");
		}
		
		
		
	}
}
