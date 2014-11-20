package uy.edu.um.laboratoriotic.ui.panel;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;

public class DisplayDescargasPanelRender extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private ImageIcon flechaPhotoImage;
	
	public DisplayDescargasPanelRender(EmployeeVO sender, EmployeeVO receiver,FileMessageVO file) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		JLabel flechaPhotoLabel;
		
		
		if (file.getSender().getID().equals(sender.getID())){

			flechaPhotoImage = rescaleImage(new File("Images/flecha.png"), 35,25);
			
		}else{
			
			flechaPhotoImage = rescaleImage(new File("Images/flecha2.png"), 35,25);
		}
		
		flechaPhotoLabel= new JLabel(flechaPhotoImage);
		
		JLabel fileName = new JLabel(file.getFileMessageName());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(flechaPhotoLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(fileName, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(flechaPhotoLabel, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addComponent(fileName))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	
	
	//resize image
			private ImageIcon rescaleImage(File source,int maxHeight, int maxWidth){
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
