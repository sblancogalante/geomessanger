package uy.edu.um.laboratoriotic.ui.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import uy.edu.um.laboratoriotic.services.factory.message.FileMessageFactory;
import uy.edu.um.laboratoriotic.services.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;
import uy.edu.um.laboratoriotic.ui.ErrorDialog;
import uy.edu.um.laboratoriotic.ui.dialog.DownloadSuccesful;
import uy.edu.um.laboratoriotic.ui.panel.DisplayDescargasPanelRender;

public class Descargas extends JFrame {

	private JPanel contentPane;

	final FileMessageMgt fileMgt = FileMessageFactory.getInstance().getFileMessageMgt();
	
	public Descargas(final EmployeeVO sender, final EmployeeVO reciver) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblArchivosEnviados = new JLabel("Archivos enviados");
		lblArchivosEnviados.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblDoubleclickToDownload = new JLabel("Doble click para descargar archivo.");
		lblDoubleclickToDownload.setForeground(Color.GRAY);
		lblDoubleclickToDownload.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(159)
							.addComponent(lblArchivosEnviados))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)))
					.addGap(4))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDoubleclickToDownload)
					.addContainerGap(373, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblArchivosEnviados)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDoubleclickToDownload)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		final JList<FileMessageVO> downloadJList = new JList<FileMessageVO>();
		scrollPane.setViewportView(downloadJList);
		contentPane.setLayout(gl_contentPane);
		
		//Changes cursos when enter componenet of jlist
		downloadJList.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
		    public void mouseMoved(MouseEvent e) {
		    	
		        final int x = e.getX();
		        final int y = e.getY();
		        final Rectangle cellBounds = downloadJList.getCellBounds(0, downloadJList.getModel().getSize() - 1);
		        if (cellBounds != null && cellBounds.contains(x, y)) {
		            downloadJList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		        } else {
		            downloadJList.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		        }
		    }

			@Override
		    public void mouseDragged(MouseEvent e) {
		    }
		});
		
		DefaultListModel<FileMessageVO> downloadListModel;
		try {
			if (fileMgt.getFileMessages(sender, reciver) != null && fileMgt.getFileMessages(sender, reciver).size() > 0) {
				 
				downloadListModel = new DefaultListModel<FileMessageVO>();
				fillDefaultListModelFromArray(fileMgt.getFileMessages(sender, reciver), downloadListModel);
				downloadJList.setModel(downloadListModel);
				
			}
		} catch (RemoteException | NotBoundException e1) {
			ErrorDialog error = new ErrorDialog("Ha ocurrido un error obteniendo informacion de la base de datos. \n\n "
					+ "ERROR: " + e1.getMessage());
			error.setVisible(true);
			e1.printStackTrace();
		} 
		
		
		downloadJList.setCellRenderer(new ListCellRenderer<FileMessageVO>() {

			@Override
			public Component getListCellRendererComponent(
					JList<? extends FileMessageVO> list, FileMessageVO value,
					int index, boolean isSelected, boolean cellHasFocus) {

				
				
				DisplayDescargasPanelRender userPanel = new DisplayDescargasPanelRender(sender, reciver, value);
				if(isSelected){
					userPanel.setBackground(Color.LIGHT_GRAY);
					
				}
				
				return userPanel;
			}
		});
		
		
		// Add a listener for mouse clicks
		downloadJList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	
		    	
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {          // Double-click
		          
		        	 
		    	    FileOutputStream fileOuputStream;
					try {
						FileMessageVO file = (FileMessageVO)list.getModel().getElementAt(list.getSelectedIndex());
						String userDir = System.getProperty("user.home") + "/Desktop/" + file.getFileMessageName();
						fileOuputStream = new FileOutputStream(userDir);
			    	    fileOuputStream.write(file.getFileMessage());
			    	    fileOuputStream.close();
			    	    DownloadSuccesful ds = new DownloadSuccesful();
			    	    ds.setVisible(true);
			    	    
					} catch (IOException e) {
						ErrorDialog error = new ErrorDialog("Ha ocurrido un error al descargar el archivo. \n\n "
								+ "ERROR: " + e.getMessage());
						error.setVisible(true);
						e.printStackTrace();
					}    
		    	   
		           
		        } 
		    }
		});
		
		
	}
	
	
	
	
	private void fillDefaultListModelFromArray( ArrayList<FileMessageVO> arrayList,DefaultListModel<FileMessageVO> lModel){
		
		for(FileMessageVO file : arrayList){
			lModel.add(lModel.getSize(), file);
		}
		
	}
}
