package uy.edu.um.laboratoriotic.ui.ABM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.factory.general.GeneralFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;
import uy.edu.um.laboratoriotic.ui.ErrorDialog;

public class ModifyUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField eMailTextField;
	private JTextField documentTextField;
	private JTextField positionTextField;
	private String photoPath;
	private JLabel testPhotoLabel;
	JComboBox locationComboBox;
	JComboBox sectorComboBox;
	JComboBox documentComboBox;
	private byte[] oProfilePicture;
	private JComboBox<String> firstHour;
	private JComboBox<String> secondHour;


	public ModifyUser(final EmployeeVO employee) {
		setBounds(100, 100, 470, 508);
		
		final EmployeeMgt employeeMgt = EmployeeFactory.getInstance()
				.getEmployeeMgt();

		final GeneralMgt generalMgt = GeneralFactory.getInstance()
				.getGeneralMgt();
		
		photoPath = "Images/Foto.png";
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblModifyUser = new JLabel("Modificar usuario");
		lblModifyUser.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		JSeparator separator = new JSeparator();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(165)
							.addComponent(lblModifyUser))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblModifyUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
					.addContainerGap())
		);
		{
			JPanel panel = new JPanel();
			tabbedPane.addTab("Cuenta", null, panel, null);
			JLabel lblEmail = new JLabel("EMail: ");
			JLabel documentLabel = new JLabel("Documento: ");
			
			String[] locationVector = {};
			String[] sectorVector = {};
			String[] documentVector = {};
			
			try {
				ArrayList<TypeVO> locationTypeArray = generalMgt.getTypes("Pais");
				ArrayList<TypeVO> sectorTypeArray = generalMgt.getTypes("Sector");
				ArrayList<TypeVO> documentTypeArray = generalMgt.getTypes("Documento");
				locationVector = arrayTypeToString(locationTypeArray);
				sectorVector = arrayTypeToString(sectorTypeArray);
				documentVector = arrayTypeToString(documentTypeArray);

				
			} catch (NotBoundException | RemoteException e1) {
				ErrorDialog typeError = new ErrorDialog("Se ha producido un error al comunicarse con la base de datos."
						+ " No se consiguieron los paises, secotres ni documentos"
						+ "existentes. \n \n ERROR: " + e1.getMessage());
				typeError.setVisible(true);
				e1.printStackTrace();
			}
			
			
			locationComboBox = new JComboBox(locationVector);
			
			sectorComboBox = new JComboBox(sectorVector);
			
			documentComboBox = new JComboBox(documentVector);
			
			
			
			JLabel lblPosition = new JLabel("Posicion: ");
			
			eMailTextField = new JTextField(employee.getMail());
			eMailTextField.setColumns(10);
			
			documentTextField = new JTextField(employee.getID());
			documentTextField.setColumns(10);
			
			positionTextField = new JTextField(employee.getPosition());
			positionTextField.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Pais");
			
			JLabel lblSector = new JLabel("Sector");
			
			JLabel lblDocumentType = new JLabel("Tipo de documento: ");
			
			JLabel lblHorarioDeTrabajo = new JLabel("Horario de Trabajo:");
			
			String[] hours = {"00","01","02","03","04","05","06","07","08","09","10","11","12",
					"13","14","15","16","17","18","19","20","21","22","23"};
			
			firstHour = new JComboBox(hours);
			
			JLabel lblHasta = new JLabel("hasta");
			
			secondHour = new JComboBox(hours);
			
			
			
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(42)
								.addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addGap(75)
								.addComponent(sectorComboBox, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(documentLabel)
									.addComponent(lblPosition)
									.addComponent(lblEmail)
									.addComponent(lblNewLabel_1)
									.addComponent(lblDocumentType))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(positionTextField, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
											.addComponent(eMailTextField, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
											.addComponent(documentTextField, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
											.addComponent(documentComboBox, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblSector)
										.addGap(38)))))
						.addGap(72))
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(19)
						.addComponent(lblHorarioDeTrabajo)
						.addGap(18)
						.addComponent(firstHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblHasta)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(secondHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(105, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(30)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblEmail)
							.addComponent(eMailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblDocumentType)
							.addComponent(documentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(documentLabel)
							.addComponent(documentTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPosition)
							.addComponent(positionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(lblSector))
						.addGap(2)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(sectorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblHorarioDeTrabajo)
							.addComponent(firstHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblHasta)
							.addComponent(secondHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(34, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}
		{
			JPanel panel = new JPanel();
			tabbedPane.addTab("Otros", null, panel, null);
			
			JLabel lblAddPhoto = new JLabel("Añadir foto: ");
			
			JPanel panel_1 = new JPanel();
			panel_1.setForeground(Color.GRAY);
			panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
			
			JButton selectPhotoButton = new JButton("Seleccionar foto");
			testPhotoLabel = new JLabel(rescaleImage(new File(photoPath), 384, 256));
			final ImageIcon testPhoto = rescaleImage(new File(photoPath), 384, 256);
			
			
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
			
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(53)
								.addComponent(lblAddPhoto)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(selectPhotoButton))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(9, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(23)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblAddPhoto)
							.addComponent(selectPhotoButton))
						.addGap(18)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
						.addContainerGap())
			);
			
			
			
			
			
			
			
			
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addComponent(testPhotoLabel, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
						.addContainerGap())
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addComponent(testPhotoLabel, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
						.addContainerGap())
			);
			panel_1.setLayout(gl_panel_1);
			panel.setLayout(gl_panel);
		}
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveChangesButton = new JButton("Guardar cambios");
				saveChangesButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent evt) {
						
						employee.setPosition(positionTextField.getText());
						
						
						TypeVO oTypeVOLocation = new TypeVO("Location",
								(String) locationComboBox.getSelectedItem());
						TypeVO oTypeVOSector = new TypeVO("Sector",
								(String) sectorComboBox.getSelectedItem());
						TypeVO oTypeVODocument = new TypeVO("Documento",
								(String) documentComboBox.getSelectedItem());
						
						String workingHours = "Desde "+ firstHour.getSelectedItem() + " hasta " + secondHour.getSelectedItem();
						

						employee.setLocation(oTypeVOLocation);
						employee.setSector(oTypeVOSector);
						employee.setDocument(oTypeVODocument);
						employee.setID(documentTextField.getText());
						oProfilePicture = convertImageToBytes(new File(photoPath));
						employee.setProfilePicture(oProfilePicture);
						employee.setMail(eMailTextField.getText());
						employee.setWorkingHour(workingHours);
						
						try {
							employeeMgt.modifyEmployee(employee);
							System.out.println("Se ha modificado: "
									+ employee.getUserName());
							dispose();
						}  catch (RemoteException e) {
							ErrorDialog error = new ErrorDialog("Ha ocurrido un error al intentar conectarse con la base de datos. \n\n ERROR: "
									+ e.getMessage());
							error.setVisible(true);
							e.printStackTrace();
						}catch(NotBoundException e){
							ErrorDialog error = new ErrorDialog("Ha ocurrido un error al intentar conectarse con el servidor. \n\n ERROR: "
									+ e.getMessage());
							error.setVisible(true);
							e.printStackTrace();
							
						} catch (EmployeeDoesNotExist e) {
							ErrorDialog error = new ErrorDialog("Ha ocurrido un error, no se encontro al empleado. \n\n ERROR: "
									+ e.getMessage());
							error.setVisible(true);
							e.printStackTrace();
						} catch (EmployeeAlreadyExists e) {
							// TODO Auto-generated catch block
							ErrorDialog error = new ErrorDialog("Ha ocurrido un error. \n\n ERROR: "
									+ e.getMessage());
							error.setVisible(true);
							e.printStackTrace();
						}
						
						System.out.println("Se ha modificado: "
								+ employee.getUserName());
						dispose();
						
				
					}
					
				});
				
				
				buttonPane.add(saveChangesButton);
				getRootPane().setDefaultButton(saveChangesButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}
	
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
	
	
	
	private byte[] convertImageToBytes(File fileInput) {
		
		FileInputStream fileInputStream=null;
		 
        File file = fileInput;
 
        byte[] bFile = new byte[(int) file.length()];
 
        try {
            //convert file into array of bytes
	    fileInputStream = new FileInputStream(file);
	    fileInputStream.read(bFile);
	    fileInputStream.close(); 
	    
        }catch(Exception e){
        	ErrorDialog error = new ErrorDialog("Ha ocurrido un error. \n\n ERROR: "+ e.getMessage());
			error.setVisible(true);
        	e.printStackTrace();
        }
        
        
        return bFile;
	}
}
