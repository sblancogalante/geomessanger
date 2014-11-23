package uy.edu.um.laboratoriotic.ui.panel;

import javax.swing.JPanel;

import java.awt.Color;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.factory.message.TextMessageFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.ui.ErrorDialog;

public class DisplayUserReport extends JPanel {
	private JLabel countLabel_1;

	/**
	 * Create the panel.
	 */
	public DisplayUserReport(EmployeeVO employee) {
		setBackground(Color.WHITE);
		
		TextMessageMgt textMgt = TextMessageFactory.getInstance().getTextMessageMgt();
		
		JLabel employeeNameLabel = new JLabel(employee.getName() + " " +  employee.getLastName());
		
		JLabel countLabel = null;
		try {
			countLabel_1 = new JLabel(String.valueOf(textMgt.countTextCharacters(employee)));
			
		} catch (RemoteException | NotBoundException e) {
			ErrorDialog error = new ErrorDialog("There has been an error, in the count messages algorithm. \n\n ERROR: "
					+ e.getMessage());
			error.setVisible(true);
			e.printStackTrace();
		}
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(employeeNameLabel, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
					.addComponent(countLabel_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(employeeNameLabel)
						.addComponent(countLabel_1))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
