package uy.edu.um.laboratoriotic.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeFilterVO;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private ImageIcon userPhotoImage;
	private JLabel userPhotoLabel;
	private JLabel userStateLabel;
	private JLabel userNameLabel;
	private JTextField searchUserText;
	private JList<EmployeeVO> userList;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu adminMenu;
	private JMenuItem createUserMenuItem;
	private JMenuItem deleteUserMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem mntmProfile;
	private ArrayList<EmployeeVO> listEmployee;

	public MainWindow(EmployeeFilterVO actualUser) throws RemoteException, NotBoundException {

		final EmployeeMgt employeeMgt = EmployeeFactory.getInstance()
				.getEmployeeMgt();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		Dimension d = new Dimension(600, 550);
		this.setMinimumSize(d);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);

		mntmProfile = new JMenuItem("Profile");
		editMenu.add(mntmProfile);

		adminMenu = new JMenu("Admin.");
		/*
		 * VER SI EL USUARIO ES ADMIN O NO
		 */
		boolean esAdmin = true;
		adminMenu.setVisible(esAdmin);
		menuBar.add(adminMenu);

		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				dispose();
			}
		});
		fileMenu.add(exitMenuItem);

		createUserMenuItem = new JMenuItem("Create User");
		createUserMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				CreateUser createUser = new CreateUser();
				createUser.setVisible(true);

			}

		});

		adminMenu.add(createUserMenuItem);

		deleteUserMenuItem = new JMenuItem("Delete User");
		deleteUserMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				DeleteUser deleteUser;
				try {
					deleteUser = new DeleteUser();
					deleteUser.setVisible(true);
				} catch (RemoteException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
		});

		adminMenu.add(deleteUserMenuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		userPhotoImage = new ImageIcon(
				"Images/Foto.png");
		userPhotoLabel = new JLabel(userPhotoImage);
		userNameLabel = new JLabel("Name");
		userStateLabel = new JLabel("State");

		searchUserText = new JTextField();
		searchUserText.setText("Search user");
		searchUserText.setForeground(Color.LIGHT_GRAY);
		searchUserText.setColumns(10);
		// User name text field,changes of color when foucs
		searchUserText.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				if (searchUserText.getText().equals("Search user")) {
					searchUserText.setText("");
					searchUserText.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (searchUserText.getText().equals("")) {
					searchUserText.setText("Search user");
					searchUserText.setForeground(Color.LIGHT_GRAY);
				}
			}
		});

		JSeparator separator = new JSeparator();
		
		
		
		//INICIALIZATION OF THE JLIST 
		
		userList = new JList<EmployeeVO>();
		
		userList.setCellRenderer(new ListCellRenderer<EmployeeVO>() {

			@Override
			public Component getListCellRendererComponent(
					JList<? extends EmployeeVO> list, EmployeeVO value,
					int index, boolean isSelected, boolean cellHasFocus) {

				
				//HACER UN PANEL MEDIO SALADO
				JLabel oLabel = new JLabel();
				if(isSelected){
					oLabel.setForeground(Color.BLUE);
				}
				oLabel.setText(value.getName()+" "+value.getLastName());
				
				
				return oLabel;
			}
		});
		
		//AGREGA LOS EMPLEADOS CONOCIDOS
		if (employeeMgt.getEmployees() != null && employeeMgt.getEmployees().size() > 0) {
			
			DefaultListModel<EmployeeVO> employeeListModel = new DefaultListModel<EmployeeVO>();
			fillDefaultListFromArray(employeeMgt.getEmployees(), employeeListModel);
			userList.setModel(employeeListModel);
			
		} 
		
		// Add a listener for mouse clicks
				userList.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(MouseEvent evt) {
				        JList list = (JList)evt.getSource();
				        if (evt.getClickCount() == 2) {          // Double-click
				            // Get item index
				        	
				            int index = list.locationToIndex(evt.getPoint());
				            ChatRoom2 chatRoom = new ChatRoom2(listEmployee.get(index));
				            chatRoom.setVisible(true);
				           System.out.println(index); 
				        } 
				    }
				});
		
		
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				try {
					listEmployee = actualizarContactos(employeeMgt, userList);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(1)
																		.addComponent(
																				separator,
																				GroupLayout.DEFAULT_SIZE,
																				574,
																				Short.MAX_VALUE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(18)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												userPhotoLabel)
																										.addGap(12)
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																userNameLabel,
																																GroupLayout.PREFERRED_SIZE,
																																61,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																userStateLabel,
																																GroupLayout.PREFERRED_SIZE,
																																237,
																																GroupLayout.PREFERRED_SIZE)))
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												searchUserText,
																												GroupLayout.PREFERRED_SIZE,
																												466,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												searchButton))
																						.addComponent(
																								userList,
																								GroupLayout.DEFAULT_SIZE,
																								557,
																								Short.MAX_VALUE))))
										.addGap(15)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																userPhotoLabel)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(30)
																		.addComponent(
																				userNameLabel)
																		.addGap(12)
																		.addComponent(
																				userStateLabel)))
										.addGap(12)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 12,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																searchUserText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																searchButton))
										.addGap(18)
										.addComponent(userList,
												GroupLayout.DEFAULT_SIZE, 311,
												Short.MAX_VALUE)
										.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

		this.setVisible(false);

	}

	public JFrame getFrame() {
		return this;
	}

	private ArrayList<EmployeeVO>  actualizarContactos(EmployeeMgt employeeMgt, JList<EmployeeVO> userList)
			throws RemoteException, NotBoundException {
		
		ArrayList<EmployeeVO> oListEmployee = employeeMgt.getEmployees();

		if (oListEmployee != null && oListEmployee.size() > 0) {
			
			DefaultListModel<EmployeeVO> lModel = new DefaultListModel<EmployeeVO>();
			fillDefaultListFromArray(oListEmployee,lModel);
			userList.setModel(lModel);
			
			
			
		} else {
			
			System.out.println("Null Employee List");
		}
		
		return oListEmployee;

	}
	
	
	private void fillDefaultListFromArray(ArrayList<EmployeeVO> arrayList,DefaultListModel<EmployeeVO> lModel){
		
		for(EmployeeVO employee : arrayList){
			lModel.add(lModel.getSize(),employee);
		}
		
	}
}
