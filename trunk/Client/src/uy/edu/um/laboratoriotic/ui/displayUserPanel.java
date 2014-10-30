package uy.edu.um.laboratoriotic.ui;

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

public class displayUserPanel extends JPanel {
	private ImageIcon userPhotoImage;
	/**
	 * Create the panel.
	 */
	public displayUserPanel(EmployeeVO employee) {
		
		boolean status = employee.getStatus();
		if(status){
			userPhotoImage = new ImageIcon("Images/OnlineImage.png");
		}else{
			userPhotoImage = new ImageIcon("Images/OfflineImage.png");
		}
		
		userPhotoImage = new ImageIcon("Images/OnlineImage.png");
		JLabel onLinePhotoLabel = new JLabel(userPhotoImage);
		
		this.setBackground(Color.WHITE);
		
		JLabel employeeNameLabel = new JLabel(employee.getName() + " " + employee.getLastName());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(onLinePhotoLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(employeeNameLabel, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
					.addGap(101))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(9, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(onLinePhotoLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(employeeNameLabel))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
