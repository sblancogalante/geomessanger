package uy.edu.um.laboratoriotic.ui.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import uy.edu.um.laboratoriotic.services.factory.message.TextMessageFactory;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

public class ChatRoom extends JFrame {

	private JPanel contentPane;
	private JTextField scrollPane;


	/**
	 * Create the frame.
	 */
	public ChatRoom(final EmployeeVO receiverEmployee, final EmployeeVO senderEmployee) { 
		
		final JTextArea messageTextArea = new JTextArea("Message...");
		messageTextArea.setLineWrap(true);		
	
		final TextMessageMgt textMgt = TextMessageFactory.getInstance().getTextMessageMgt();
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//HACE UN COSO PARA QE CIERRE BIEN, no esta liberanod los recursos
		this.setTitle("Chat Room");
		setBounds(100, 100, 500, 500);
		Dimension d = new Dimension(500,500);
		this.setMinimumSize(d);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Files");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Send file...");
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ImageIcon receiverPhotoImage;
		
		if(receiverEmployee.getProfilePicture() == null){	
			
			receiverPhotoImage = rescaleImage(new File("Images/Foto.png"), 118,118);
			
		}else{
			
			receiverPhotoImage = rescaleImage(new File("Images/Manolo.jpg"), 118, 118);
			//userPhotoImage = new ImageIcon("Images/luisFoto.jpg");
			
		}
		
		JLabel receiverPhotoLabel = new JLabel(receiverPhotoImage);
		
		ImageIcon senderPhotoImage;
		if(senderEmployee.getProfilePicture() == null){	
			
			senderPhotoImage = rescaleImage(new File("Images/Foto.png"), 118,118);
			
		}else{
			
			senderPhotoImage = rescaleImage(new File("Images/Manolo.jpg"), 118, 118);
			
			
		}
		
		JLabel senderPhotoLabel = new JLabel(senderPhotoImage);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JSeparator separator = new JSeparator();

		final JTextArea converTextArea = new JTextArea();
		converTextArea.setEditable(false);
		converTextArea.setLineWrap(true);
	
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				
				TextMessageVO message = new TextMessageVO(0,messageTextArea.getText(),senderEmployee,receiverEmployee,null,false);
				try {
					textMgt.addTextMessage(message);
					converTextArea.append(message.getTextMessage() + "\n");
					messageTextArea.setText("");
					
				} catch (RemoteException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		});
		
		JLabel chattingWithLabel = new JLabel("Chatting with " + receiverEmployee.getName()+  " " + receiverEmployee.getLastName());
		
		JLabel offLineLabel = new JLabel("<<The user is Offline>>");
		offLineLabel.setForeground(Color.RED);
		offLineLabel.setVisible(!receiverEmployee.getStatus());
		 
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(receiverPhotoLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(senderPhotoLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chattingWithLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(offLineLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
								.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
								.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
								.addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(receiverPhotoLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
							.addComponent(senderPhotoLabel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(offLineLabel)
							.addGap(18)
							.addComponent(chattingWithLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(44)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		
		
		messageTextArea.setForeground(Color.LIGHT_GRAY);
		
		//focus gained
		messageTextArea.addFocusListener(new FocusListener() {
			
		    public void focusGained(FocusEvent e) {
		    	if(messageTextArea.getText().equals("Message...")){
		    		messageTextArea.setText("");
		    		messageTextArea.setForeground(Color.BLACK);
		    	}
		    }

		    public void focusLost(FocusEvent e) {
		        if(messageTextArea.getText().equals("")){
		        	messageTextArea.setText("Message...");
		        	messageTextArea.setForeground(Color.LIGHT_GRAY);
		        }
		    }
		});
		
		
		scrollPane_2.setViewportView(messageTextArea);
		
		scrollPane_1.setViewportView(converTextArea);
		contentPane.setLayout(gl_contentPane);
	}
	
	private Collection<TextMessageVO> actualizarMensajes(TextMessageMgt textMgt,
			JList<TextMessageVO> messageList, EmployeeVO oSender, EmployeeVO oReceiver){
		
			Collection<TextMessageVO> oListMessages = new ArrayList<TextMessageVO>();
			try {
				oListMessages = textMgt.getTextMessages(oSender, oReceiver);
			} catch (RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return oListMessages;
		
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
