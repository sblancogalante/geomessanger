package uy.edu.um.laboratoriotic.ui.ABM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import uy.edu.um.laboratoriotic.services.factory.general.GeneralFactory;
import uy.edu.um.laboratoriotic.services.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;
import uy.edu.um.laboratoriotic.ui.ErrorDialog;



public class NewDocument extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField newDocumentText;
	private ArrayList<TypeVO> documentsArray;



	/**
	 * Create the dialog.
	 */
	public NewDocument() {
		
		this.setTitle("Crear tipo de documento");
		setBounds(100, 100, 450, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension d = new Dimension(450,373);
		this.setMaximumSize(d);
		this.setMinimumSize(d);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCreateLocation = new JLabel("Crear tipo de documento");
		lblCreateLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JSeparator separator = new JSeparator();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLocation = new JLabel("Nuevo tipo de documento: ");
		
		final GeneralMgt generalMgt =  GeneralFactory.getInstance()
				.getGeneralMgt();
		
		newDocumentText = new JTextField();
		newDocumentText.setEditable(true);
		newDocumentText.setColumns(10);
		
		
		
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
							.addComponent(newDocumentText)
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
						.addComponent(newDocumentText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
				
				JLabel label = new JLabel(value.getValue());
				
				
				if(isSelected){
					label.setForeground(Color.BLUE);
					
				}
				
				return label;
			}
		});
		//Agrega las locations  conocidas
		try {
			documentsArray = generalMgt.getTypes("Documento");
			DefaultListModel<TypeVO> typesListModel; 
			typesListModel = new DefaultListModel<TypeVO>();
			fillDefaultListModelFromArray(generalMgt.getTypes("Documento"), typesListModel);
			typesList.setModel(typesListModel); 
			
		} catch (RemoteException | NotBoundException e) {
			ErrorDialog errorDialog = new ErrorDialog("Se ha producido un error al intentar buscar los tipos de documentos conocidas."
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
				JButton addLocationButton = new JButton("Añadir Documento");
				addLocationButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						int nvalue = JOptionPane.showConfirmDialog(NewDocument.this, "Estas seguro que desea agregar el siguiente tipo de Documento: \n" 
								+ newDocumentText.getText() + " ?");
					
								if (nvalue == 0){
									try {
										TypeVO oType = new TypeVO(0,"Documento",newDocumentText.getText());
										generalMgt.addType(oType);
										dispose();
									} catch (RemoteException | NotBoundException e) {
										ErrorDialog errorDialog = new ErrorDialog("Se ha producido un error al intentar agregar el siguiente tipo de documento "
												+ newDocumentText.getText() + "."
												+ " \n \n ERROR: " + e.getMessage());
										errorDialog.setVisible(true);
										e.printStackTrace();
									}
									
								}else{
									
									
								}
						
					}
				});
				
					
					
					
				buttonPane.add(addLocationButton);
				getRootPane().setDefaultButton(addLocationButton);
			
			
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});

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
