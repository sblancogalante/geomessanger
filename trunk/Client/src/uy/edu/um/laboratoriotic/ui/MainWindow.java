package uy.edu.um.laboratoriotic.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
	private EmployeeVO actualUser;

	public MainWindow(EmployeeFilterVO actualFilterUser) throws RemoteException, NotBoundException {

		final EmployeeMgt employeeMgt = EmployeeFactory.getInstance()
				.getEmployeeMgt();
		
		
		actualUser = employeeMgt.getLoginEmployee(actualFilterUser);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 620);
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
		
		
		if(actualUser.getProfilePicture() == null){	
			
			userPhotoImage = rescaleImage(new File("Images/Foto.png"), 118,118);
			
		}else{
			
			userPhotoImage = rescaleImage(new File("Images/Manolo.jpg"), 118, 118);
			//userPhotoImage = new ImageIcon("Images/luisFoto.jpg");
			
		}
		
		userPhotoLabel = new JLabel(userPhotoImage);
		userNameLabel = new JLabel(actualUser.getName());
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

				
				
				displayUserPanel userPanel = new displayUserPanel(value);
				if(isSelected){
					userPanel.setBackground(Color.LIGHT_GRAY);
					
				}
				
				//HACER UN PANEL MEDIO SALADO
				//JLabel oLabel = new JLabel();
				//	if(isSelected){
				//	oLabel.setForeground(Color.BLUE);
				//}
				//oLabel.setText(value.getName()+" "+value.getLastName());
				
				
				return userPanel;
			}
		});
		DefaultListModel<EmployeeVO> employeeListModel;
		//AGREGA LOS EMPLEADOS CONOCIDOS
		if (employeeMgt.getEmployees() != null && employeeMgt.getEmployees().size() > 0) {
			 
			employeeListModel = new DefaultListModel<EmployeeVO>();
			fillDefaultListModelFromArray(actualUser,employeeMgt.getEmployees(), employeeListModel);
			userList.setModel(employeeListModel);
			listEmployee = actualizarContactos(employeeMgt, userList);
			
		} 
		
		// Add a listener for mouse clicks
				userList.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(MouseEvent evt) {
				    	
				        JList list = (JList)evt.getSource();
				        if (evt.getClickCount() == 2) {          // Double-click
				            // Get item indexçç
				        					        	
				            System.out.println(list.getSelectedIndex());
				            ChatRoom2 chatRoom = new ChatRoom2((EmployeeVO)list.getModel().getElementAt(list.getSelectedIndex()),actualUser);
				            chatRoom.setVisible(true);
				           
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
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(userPhotoLabel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(userNameLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
										.addComponent(userStateLabel, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(221)
									.addComponent(searchUserText, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(searchButton))
								.addComponent(userList, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE))))
					.addGap(15))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addComponent(userNameLabel)
							.addGap(12)
							.addComponent(userStateLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(userPhotoLabel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchUserText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addGap(12)
					.addComponent(userList, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
					.addContainerGap())
		);
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
			fillDefaultListModelFromArray(actualUser, oListEmployee,lModel);
			userList.setModel(lModel);
			
			
			
		} else {
			
			System.out.println("Null Employee List");
		}
		
		return oListEmployee;

	}
	
	
	private void fillDefaultListModelFromArray(EmployeeVO actualUser, ArrayList<EmployeeVO> arrayList,DefaultListModel<EmployeeVO> lModel){
		
		for(EmployeeVO employee : arrayList){
			if(!employee.getUserName().equals( actualUser.getUserName())){
				lModel.add(lModel.getSize(),employee);
			}
		}
		
	}
	
	
	
	//resize image
	public ImageIcon rescaleImage(File source,int maxHeight, int maxWidth){
	     int newHeight = 0, newWidth = 0;        // Variables for the new height and width
	     int priorHeight = 0, priorWidth = 0;
	     BufferedImage image = null;
	     ImageIcon sizeImage;

	     try {
	             image = ImageIO.read(source);        // get the image
	     } catch (Exception e) {

	             e.printStackTrace();
	             System.out.println("Picture upload attempted & failed");
	     }

	     sizeImage = new ImageIcon(image);

	     if(sizeImage != null){
	    	 
	         priorHeight = sizeImage.getIconHeight(); 
	         priorWidth = sizeImage.getIconWidth();
	     }

	     // Calculate the correct new height and width
	     if((float)priorHeight/(float)priorWidth > (float)maxHeight/(float)maxWidth){
	     
	         newHeight = maxHeight;
	         newWidth = (int)(((float)priorWidth/(float)priorHeight)*(float)newHeight);
	     }else{
	    	 
	         newWidth = maxWidth;
	         newHeight = (int)(((float)priorHeight/(float)priorWidth)*(float)newWidth);
	     }


	     // Resize the image

	     // 1. Create a new Buffered Image and Graphic2D object
	     BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
	     Graphics2D g2 = resizedImg.createGraphics();

	     // 2. Use the Graphic object to draw a new image to the image in the buffer
	     g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	     g2.drawImage(image, 0, 0, newWidth, newHeight, null);
	     g2.dispose();

	     // 3. Convert the buffered image into an ImageIcon for return
	     return (new ImageIcon(resizedImg));
	 }
	
	
	
	
}
