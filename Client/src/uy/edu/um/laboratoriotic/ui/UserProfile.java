package uy.edu.um.laboratoriotic.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

public class UserProfile extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	

	/**
	 * Create the dialog.
	 */
	public UserProfile(EmployeeVO employee) {
		
		this.setTitle("User Profile");
		setBounds(100, 100, 475, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		ImageIcon employeePhotoImage;
		if(employee.getProfilePicture() == null){	
			
			employeePhotoImage = rescaleImage(new File("Images/Manolo.jpg"), 120,120);
			
		}else{
			
			employeePhotoImage = rescaleImage(new File("Images/Manolo.jpg"), 120, 120);
			//userPhotoImage = new ImageIcon("Images/luisFoto.jpg");
			
		}
		
		
		JLabel lblNewLabel = new JLabel(employeePhotoImage);
		JLabel lblNewLabel_1 = new JLabel(employee.getName());
		JLabel lblNewLabel_2 = new JLabel(employee.getLastName());
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(81)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(7)
							.addComponent(lblNewLabel_2)))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(4))
		);
		JLabel idLabel = new JLabel("ID: " + employee.getID());
		JLabel userNameLabel = new JLabel("User Name: " + employee.getUserName());
		JLabel eMailLabel = new JLabel("EMail: " + employee.getMail());
		JLabel locationLabel = new JLabel("Loaction: " + employee.getLocation());
		JLabel sectorLabel = new JLabel("Sector: " + employee.getSector());
		JLabel positionLabel = new JLabel("Position: " + employee.getPosition());
		JLabel workingHoursLabel = new JLabel("Working Hours: " + employee.getWorkingHour());
		JLabel statusLabel = new JLabel("Actual Status: " + employee.getStatus());
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(idLabel, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
						.addComponent(userNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(eMailLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(locationLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(sectorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(positionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(workingHoursLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(statusLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(119))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(idLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userNameLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(eMailLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(locationLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sectorLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(positionLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(workingHoursLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(statusLabel)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
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
		

}
