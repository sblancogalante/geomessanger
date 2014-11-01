package uy.edu.um.laboratoriotic.ui.ABM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uy.edu.um.laboratoriotic.services.factory.general.GeneralFactory;
import uy.edu.um.laboratoriotic.services.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;
import uy.edu.um.laboratoriotic.ui.ErrorDialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class NewLocation extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField newLocationText;
	private ArrayList<TypeVO> locationsArray;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewLocation dialog = new NewLocation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewLocation() {
		
		this.setTitle("Create Location");
		setBounds(100, 100, 450, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension d = new Dimension(450,373);
		this.setMaximumSize(d);
		this.setMinimumSize(d);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCreateLocation = new JLabel("Create Location");
		lblCreateLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JSeparator separator = new JSeparator();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLocation = new JLabel("New Location: ");
		
		final GeneralMgt generalMgt =  GeneralFactory.getInstance()
				.getGeneralMgt();
		
		newLocationText = new JTextField();
		newLocationText.setEditable(false);
		newLocationText.setColumns(10);
		
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(160)
							.addComponent(lblCreateLocation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(157))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLocation)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(newLocationText)
							.addGap(195))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
							.addGap(6)))
					.addGap(0))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCreateLocation)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLocation)
						.addComponent(newLocationText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addGap(9))
		);
		
		JList<TypeVO> typesList = new JList<TypeVO>();
		
		typesList.setCellRenderer(new ListCellRenderer<TypeVO>() {

			@Override 
			public Component getListCellRendererComponent(
					JList<? extends TypeVO> list, TypeVO value,
					int index, boolean isSelected, boolean cellHasFocus) {
				
				JLabel label = new JLabel(value.getTypeCountry());
				
				
				if(isSelected){
					label.setForeground(Color.BLUE);
					
				}
				
				return label;
			}
		});
		//Agrega las locations  conocidas
		try {
			locationsArray = generalMgt.getTypes("");
			DefaultListModel<TypeVO> typesListModel; 
			typesListModel = new DefaultListModel<TypeVO>();
			fillDefaultListModelFromArray(generalMgt.getTypes(""), typesListModel);
			typesList.setModel(typesListModel); 
			
		} catch (RemoteException | NotBoundException e) {
			ErrorDialog errorDialog = new ErrorDialog("Se ha producido un error al intentar buscr las locations conocidas."
					+ " \n \n ERROR: " + e.getMessage());
			errorDialog.setVisible(true);
			e.printStackTrace();
		}

		
		
		scrollPane.setViewportView(typesList); 
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addLocationButton = new JButton("Add Location");
				addLocationButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						int nvalue = JOptionPane.showConfirmDialog(NewLocation.this, "Estas seguro que desea agregar la siguiente Localizacion: \n" 
								+ newLocationText.getText() + " ?");
					
								if (nvalue == 0){
									//DEELTE USER
									try {
										TypeVO oType = new TypeVO(1,newLocationText.getText(),null,true);
										generalMgt.addType(oType);
										dispose();
									} catch (RemoteException | NotBoundException e) {
										ErrorDialog errorDialog = new ErrorDialog("Se ha producido un error al intentar eliminar el usuario."
												+ " \n \n ERROR: " + e.getMessage());
										e.printStackTrace();
									}
									
								}else{
									
									
								}
						
					}
				});
				
					
					
					
				buttonPane.add(addLocationButton);
				getRootPane().setDefaultButton(addLocationButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	
private void fillDefaultListModelFromArray(ArrayList<TypeVO> arrayList,DefaultListModel<TypeVO> lModel){
		
		for(TypeVO employee : arrayList){
			lModel.add(lModel.getSize(),employee);	
		}
		
	}
}
