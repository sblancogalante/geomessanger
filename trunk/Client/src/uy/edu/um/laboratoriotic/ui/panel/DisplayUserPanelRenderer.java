package uy.edu.um.laboratoriotic.ui.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

public class DisplayUserPanelRenderer extends JPanel {
	private ImageIcon userPhotoImage;
	/**
	 * Create the panel.
	 */
	public DisplayUserPanelRenderer(EmployeeVO employee) {
		
		boolean status = employee.getStatus();
		
		if(status){
			userPhotoImage = new ImageIcon("Images/OnlineImage.png");
		}else{
			userPhotoImage = new ImageIcon("Images/OfflineImage.png");
		}
		
		
		JLabel onLinePhotoLabel = new JLabel(userPhotoImage);
		
		this.setBackground(Color.WHITE);
		
		JLabel employeeNameLabel = new JLabel(employee.getName() + " " + employee.getLastName());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(onLinePhotoLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(employeeNameLabel, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addGap(105))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(onLinePhotoLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(employeeNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(4)))
					.addGap(8))
		);
		setLayout(groupLayout);

	}
}
