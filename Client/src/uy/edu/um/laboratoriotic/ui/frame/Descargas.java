package uy.edu.um.laboratoriotic.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JSeparator;

import uy.edu.um.laboratoriotic.services.factory.message.FileMessageFactory;
import uy.edu.um.laboratoriotic.services.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;
import uy.edu.um.laboratoriotic.ui.panel.DisplayDescargasPanelRender;

public class Descargas extends JFrame {

	private JPanel contentPane;

	final FileMessageMgt fileMgt = FileMessageFactory.getInstance().getFileMessageMgt();
	
	public Descargas(final EmployeeVO sender, final EmployeeVO reciver) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblArchivosEnviados = new JLabel("Archivos enviados");
		lblArchivosEnviados.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(159)
							.addComponent(lblArchivosEnviados))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)))
					.addGap(4))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblArchivosEnviados)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addContainerGap())
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
			// TODO Auto-generated catch block
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
		           //Guarda la imagen en algun directorio.
		           
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
