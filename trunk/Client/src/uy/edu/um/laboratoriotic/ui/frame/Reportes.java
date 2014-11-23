package uy.edu.um.laboratoriotic.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListCellRenderer;

import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.ui.ErrorDialog;
import uy.edu.um.laboratoriotic.ui.panel.DisplayUserReport;

public class Reportes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reportes frame = new Reportes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reportes() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		EmployeeMgt employeeMgt = EmployeeFactory.getInstance().getEmployeeMgt();
		
		JLabel reportsLabel = new JLabel("Reports");
		reportsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		
		JSeparator separator = new JSeparator();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		JList<EmployeeVO> list = new JList<EmployeeVO>();
		
		
		DefaultListModel<EmployeeVO> employeeListModel;
		try {
			if (employeeMgt.getEmployees() != null && employeeMgt.getEmployees().size() > 0) {
				 
				employeeListModel = new DefaultListModel<EmployeeVO>(); 
				fillDefaultListModelFromArray(employeeMgt.getEmployees(), employeeListModel);
				list.setModel(employeeListModel);
			}
		} catch (RemoteException | NotBoundException e) {
			ErrorDialog error = new ErrorDialog("There has been an error, retrieving data from data Base. \n\n ERROR: "
					+ e.getMessage());
			error.setVisible(true);
			
			e.printStackTrace();
		}
		
		
		list.setCellRenderer(new ListCellRenderer<EmployeeVO>() {

			@Override
			public Component getListCellRendererComponent(
					JList<? extends EmployeeVO> list, EmployeeVO value,
					int index, boolean isSelected, boolean cellHasFocus) {
			
				DisplayUserReport userReportPanel = new DisplayUserReport(value);
				
				if(isSelected){
					userReportPanel.setBackground(Color.LIGHT_GRAY);
					
				}
				
				return userReportPanel;
			
			}
		});
		
		
		JButton exportButton = new JButton("Export to Excel");
		
		JLabel lblNewLabel = new JLabel("Name");
		
		JLabel lblMessageCount = new JLabel("Message Count");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(197)
							.addComponent(reportsLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(exportButton)
					.addPreferredGap(ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
					.addComponent(cancelButton)
					.addGap(19))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
					.addComponent(lblMessageCount)
					.addGap(107))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(reportsLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblMessageCount))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(exportButton)
						.addComponent(cancelButton))
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(list);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	private void fillDefaultListModelFromArray(ArrayList<EmployeeVO> arrayList,DefaultListModel<EmployeeVO> lModel){
		
		for(EmployeeVO employee : arrayList){

			lModel.add(lModel.getSize(),employee);

		}
		
	}
}
