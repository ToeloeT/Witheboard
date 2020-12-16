package board;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.rmi.*;

public class whiteboardclient extends ClientImp implements MouseListener, MouseMotionListener{


	private int x1,x2,y1,y2;
	private Color newcolor_p = Color.black;
	private String type = "pencil";
	private JTextArea chat_area;
	protected Server server;
	private JTextArea list2;

	private ButtonGroup group;
	private String username;
	protected Draw canvas;
	private JLabel edit;
	private JTextArea wt_list;
	
	public whiteboardclient(Server s) throws RemoteException {
		super();
		this.server = s;
		
	}
	public void setName(String s) {
		this.username = s;
	}
	

	
public void initialize(){
	 JFrame frame = new JFrame("Client");
    frame.setBounds(100,100,1500,1500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel left = new JPanel();  
    Dimension leftDim = new Dimension(200, 1);  
    left.setPreferredSize(leftDim);  
    left.setBackground(Color.GRAY); 
    group = new ButtonGroup();  
    
    String[] strs = { "easer","rect", "oval" ,"line","circle","text" ,"pencil"};  
 
       for (int i = 0; i < strs.length; i++) {  
           JRadioButton btn1 = new JRadioButton(strs[i]);  
           left.add(btn1);
           group.add(btn1);
           btn1.setActionCommand(strs[i]);
           btn1.setSelected(true);
       }  
    frame.add(left,BorderLayout.WEST);
    
    
    JPanel panel = new JPanel();
    frame.add(panel);

    panel.setLayout(null);
    
    
    
    Label label = new Label("Your name: " + username);
    label.setSize(300,30);
    panel.add(label);
    
    edit = new JLabel();
    edit.setBounds(800, 200, 200, 20);
    panel.add(edit);
    
    canvas = new Draw(server,edit);
    canvas.setBounds(100,100,600,600);
    canvas.setBackground(Color.white);
    panel.add(canvas);
    
    
    
    Label label3 = new Label("Online list");
    label3.setBounds(800, 470, 100, 20);
    panel.add(label3);
    
    list2 = new JTextArea();
    JScrollPane ScrollPane1 = new JScrollPane(list2);
    try {
		for(Entry<String,Client> entry : server.getClient().entrySet()) {
			list2.append(entry.getKey() + "\n");
		}
	} catch (RemoteException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
    list2.setLineWrap(true);
    list2.setWrapStyleWord(true);
    list2.setEditable(false);
    list2.setBounds(800, 500, 150, 150);
    ScrollPane1.setBounds(800, 500, 150, 150);
    panel.add(ScrollPane1);
    
    JTextField rm = new JTextField();
    rm.setBounds(800, 660, 150, 20);
    panel.add(rm);
    
    Label label4 = new Label("Chat Window");
    label4.setBounds(1000, 70, 100, 20);
    panel.add(label4);
    
    chat_area = new JTextArea();
    JScrollPane ScrollPane = new JScrollPane(chat_area);
    chat_area.setBounds(1000,100,200,200);
    chat_area.setLineWrap(true);
    chat_area.setWrapStyleWord(true);
    chat_area.setEditable(false);
    ScrollPane.setBounds(1000,100,200,200);
    panel.add(ScrollPane);
    
    
    
    JButton remove_menu = new JButton("remove");
    remove_menu.setFont(new Font("Times New Roman", Font.PLAIN,15));
    remove_menu.setBounds(820,680,100,30);
    panel.add(remove_menu);
    
    remove_menu.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
           	String name = rm.getText();
           	try {
				if(server.remove(name) == false) {
					rm.setText("Wrong name.");
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           }});
    
    Label label5 = new Label("Enter the word:");
    label5.setBounds(1000, 320, 100, 20);
    panel.add(label5);
    JTextArea send_area = new JTextArea();
    send_area.setBounds(1000,350,200,100);
    send_area.setLineWrap(true);
    send_area.setWrapStyleWord(true);
    send_area.setEditable(true);
    panel.add(send_area);
    
    
    JButton send_menu = new JButton("send");
    send_menu.setFont(new Font("Times New Roman", Font.PLAIN,15));
    send_menu.setBounds(1050,450,100,30);
    panel.add(send_menu);
    
    send_menu.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
           	String word = send_area.getText();
           	if(word.isEmpty()) {
           		send_area.setText("Please enter a word to send");
           	}else {
           		Date d = new Date();
           		String message = d + " " + username + " : " + word;
           		try {
					server.boardcastMessage(message);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
           	}
           	send_area.setText("");
           }});
    
    
    
    
    
    
    
    
    
    
    
    
    
	JPanel foot = new JPanel();  
	Dimension footDim = new Dimension(1, 100);  
	foot.setPreferredSize(footDim);
	foot.setBackground(Color.GRAY); 
	frame.add(foot, BorderLayout.SOUTH);
	
	JButton jb = new JButton("color");  
	jb.setBounds(30, 30, 100, 80);  

	jb.setFont(new Font("Times New Roman", Font.PLAIN,50));
	jb.addActionListener(new ActionListener() {  
	    public void actionPerformed(ActionEvent e) {
	    	
	    	Color newcolor = JColorChooser.showDialog(canvas,"color map",Color.black);
	    	newcolor_p = newcolor;
	  
	    }  
	});
    foot.add(jb);
    
    
    
    

    JMenuBar bar = new JMenuBar();
    JButton new_menu = new JButton("new");
    new_menu.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
       	   try {
			server.newButton();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	       }
               
       });
   bar.add(new_menu);
   JButton open_menu = new JButton("open");
   open_menu.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  try {
				open_file(canvas);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       }
               
       });
   bar.add(open_menu);
   JButton save_menu = new JButton("save");
   save_menu.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  try {
  				save(canvas);
  			} catch (IOException e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			} catch (AWTException e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
	       }
               
       });
   bar.add(save_menu);
   JButton save_as = new JButton("saveAs");
   save_as.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  try {
				save_as(frame,canvas);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       }
               
       });
   bar.add(save_as);
   JButton close_menu = new JButton("close");
   close_menu.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
        	  try {
				server.managerRemove();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
       	   System.exit(0);
	       }
               
       });
   bar.add(close_menu);
   frame.setJMenuBar(bar); 
   frame.setLocation(400, 200);

    frame.setVisible(true);
    canvas.addMouseListener(this);
    canvas.addMouseMotionListener(this);
    
    
    
    
    
	}
	public boolean makeDraw(shape s) throws RemoteException{
		
		new Thread() {
			@Override
			public void run() {
				canvas.draw(s);
			}
		}.start();
		
		return true;
		
	}
	public void makeNewFile() throws RemoteException{
		canvas.repaint();
		
		
	}
	
	
	public void makeMtoClient(String s1) throws RemoteException {
		// TODO Auto-generated   stub
		new Thread() {
			@Override
			public void run() {
				Date d = new Date();
				chat_area.append(d + " Welcome, " +s1 + "\n");
				list2.setText("");
				try {
					for(Entry<String, Client> entry:server.getClient().entrySet()) {
						list2.append(entry.getKey() + "\n");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	public void clientLeave(String s2) throws RemoteException {
		// TODO Auto-generated   stub
		new Thread() {
			@Override
			public void run() {
				Date d = new Date();
				chat_area.append(d + " " +s2 + " leave the room"+ "\n");
				list2.setText("");
				try {
					for(Entry<String, Client> entry:server.getClient().entrySet()) {
						list2.append(entry.getKey() + "\n");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	public void beKickedS(String s) throws RemoteException {
		// TODO Auto-generated   stub
		new Thread() {
			@Override
			public void run() {
				Date d = new Date();
				chat_area.append(d + " " +s + " be kicked from the room"+ "\n");
				list2.setText("");
				try {
					for(Entry<String, Client> entry:server.getClient().entrySet()) {
						list2.append(entry.getKey() + "\n");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	public void makeMessage(String s) throws RemoteException {
		new Thread() {
			@Override
			public void run() {
				chat_area.append(s + "\n");
			}
		}.start();
		
	}
	public boolean newUser(String s) throws RemoteException {
		if((JOptionPane.showConfirmDialog(null, s + " want to connect to your board.", "Decision", JOptionPane.YES_NO_OPTION)) == 1) {
			return false;
		}
		return true;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Point evpt = e.getPoint();
			//boardcasrt end
		    x2 = e.getX();
		    y2 = e.getY();
		    if(type.equals("pencil")) {
		    	shape s = new shape(type,x1,y1,x2,y2,newcolor_p,null,username); 
		    	try {
				server.boardcast(s);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		    x1 = x2;
    		    y1 = y2;
		    }else if (type.equals("easer")) {  
		    	shape s = new shape(type,x1,y1,x2,y2,newcolor_p,null,username);
		    	try {
				server.boardcast(s);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  
	            x1 = x2;  
	            y1 = y2; 
	        }
		
	}
	public void openFile() throws RemoteException {
		canvas.repaint();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Point evpt = e.getPoint();
			ButtonModel model = group.getSelection();
	        type = model.getActionCommand();
			x1 = e.getX();
			y1 = e.getY();
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		Point evpt = e.getPoint();
			//boardcasrt end
		    x2 = e.getX();
		    y2 = e.getY(); 
	        int width = x1 < x2 ? x2 - x1 : x1 - x2;  
	        int height = y1 < y2 ? y2 - y1 : y1 - y2;
		    if(type.equals("line")) {
		    	shape s = new shape(type,x1,y1,x2,y2,newcolor_p,null,username);
		    	try {
				server.boardcast(s);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	} else if (type.equals("rect")) { 
	    		shape s = new shape(type,x1,y1,width,height,newcolor_p,null,username);
	    		try {
					server.boardcast(s);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        } else if (type.equals("oval")) { 
	        	shape s = new shape(type,x1,y1,width,height,newcolor_p,null,username);
	        	try {
					server.boardcast(s);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	          
	        }else if (type.equals("easer")) {  
	        	shape s = new shape(type,x1,y1,x2,y2,newcolor_p,null,username);
	        	try {
					server.boardcast(s);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        else if (type.equals("pencial")) {  
	        	shape s = new shape(type,x1,y1,x2,y2,newcolor_p,null,username);
	        	try {
					server.boardcast(s);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }else if(type.equals("circle")) {
	        	shape s = new shape(type,x1,y1,width,width,newcolor_p,null,username);
	        	try {
					server.boardcast(s);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }else if(type.equals("text")) {
	        	String input;
                input = JOptionPane.showInputDialog("Please input the text you want!");
                shape s = new shape(type,x1,y1,x2,y2,newcolor_p,input,username);
                try {
					server.boardcast(s);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	        }
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    

	@SuppressWarnings("unchecked")
	public void open_file(Draw canvas2) throws IOException {
	//	BufferedImage image = null;
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file =chooser.getSelectedFile();
		if(file==null){
			JOptionPane.showMessageDialog(null, "No such a file");
			
		}else {
			FileInputStream ifs = new FileInputStream(file);
			ObjectInputStream input = new ObjectInputStream(ifs);
			ArrayList<shape> list;
			try {
				list = (ArrayList<shape>) input.readObject();
				server.openButton(list);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			input.close();
		}
	
		
		 
		 
		 
	}
	public void save_as(Frame frame, Draw canvas2) throws IOException, AWTException{
		FileDialog fDialog = new FileDialog(frame, "Save", FileDialog.SAVE);
	    fDialog.setVisible(true);
	    String path = fDialog.getDirectory() + fDialog.getFile();
	    File file = new File(path);
	    file.createNewFile();
	    FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream output = new ObjectOutputStream(fos);
		ArrayList<shape> list = server.getShapeList();
		output.writeObject(list);
		output.close();
		fos.close();
		 
		 
		 
	}
	
	public void save(Draw canvas2) throws IOException, AWTException{
	    File f=new File("1.jpg");
		if( !f.exists() )
		{
			f.createNewFile();
		}
			
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream output = new ObjectOutputStream(fos);
		ArrayList<shape> list = server.getShapeList();
		output.writeObject(list);
		output.close();
		fos.close();
	    
	}

}