package uy.edu.um.laboratoriotic.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import uy.edu.um.laboratoriotic.services.factory.message.FileMessageFactory;
import uy.edu.um.laboratoriotic.services.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;
import uy.edu.um.laboratoriotic.ui.ErrorDialog;

public class SelectFileToSend extends JDialog {

	private final JPanel contentPanel = new JPanel();

	String pathToFile;
	JLabel fileNameLabel;
	File fileToSend;
	JLabel urlLabel;
	byte[] fileBytes;

	public SelectFileToSend(final EmployeeVO sender, final EmployeeVO receiver) {
		setBounds(100, 100, 501, 164);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblSelectAFile = new JLabel("Seleccione un archivo:");
		final FileMessageMgt fileMgr = FileMessageFactory.getInstance()
				.getFileMessageMgt();

		JButton selectFileButton = new JButton("Seleccionar");
		selectFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser jFileChooser = new JFileChooser();

				if (pickPath(jFileChooser) != null) {

					pathToFile = pickPath(jFileChooser);
				}

				fileToSend = new File(pathToFile);
				fileNameLabel.setText(fileToSend.getName());
				urlLabel.setText("URL: " + pathToFile);
				fileBytes = FileToBytes(fileToSend);

			}

		});

		fileNameLabel = new JLabel("--- Ningun archivo seleccionado. ---");

		urlLabel = new JLabel("URL: ---- ");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				fileNameLabel)
																		.addContainerGap(
																				327,
																				Short.MAX_VALUE))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblSelectAFile)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				selectFileButton)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				urlLabel,
																				GroupLayout.PREFERRED_SIZE,
																				302,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(5)))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_contentPanel
								.createSequentialGroup()
								.addGap(20)
								.addGroup(
										gl_contentPanel
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(lblSelectAFile)
												.addComponent(selectFileButton)
												.addComponent(urlLabel))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(fileNameLabel)
								.addContainerGap(16, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton sendButton = new JButton("Send");
				sendButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (fileBytes == null) {
							ErrorDialog error = new ErrorDialog(
									"El archivo seleccionado no pudo convertirse a bytes, o  "
											+ "no eligio ningun archivo.");
							error.setVisible(true);
							dispose();

						} else {
							FileMessageVO fileMessage = new FileMessageVO(0,
									fileBytes, fileToSend.getName(), sender,
									receiver);
							try {
								fileMgr.addFileMessage(fileMessage);
								dispose();
							} catch (RemoteException | NotBoundException e1) {
								ErrorDialog error = new ErrorDialog(
										"Ha ocurrido un error en la conexion a la base de datos. \n\n ERROR: "
												+ e1.getMessage());
								error.setVisible(true);
								e1.printStackTrace();
							}

						}

					}
				});

				buttonPane.add(sendButton);
				getRootPane().setDefaultButton(sendButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}

	private static String pickPath(JFileChooser fileChooser) {
		String path = null;
		JDialog dialog = new JDialog();
		int returnVal = fileChooser.showOpenDialog(dialog);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = fileChooser.getSelectedFile().getPath();
		}
		return path;
	}

	private byte[] FileToBytes(File fileInput) {

		FileInputStream fileInputStream = null;

		File file = fileInput;

		byte[] bFile = new byte[(int) file.length()];

		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			
		} catch (Exception e) {
			ErrorDialog error = new ErrorDialog(
					"Ha ocurrido un error. \n\n ERROR: " + e.getMessage());
			error.setVisible(true);
			e.printStackTrace();
		}

		return bFile;
	}

}
