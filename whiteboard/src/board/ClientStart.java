package board;

import java.awt.EventQueue;
import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class ClientStart {
	protected static Server server;
	public static void main(String [] args) {
		
		try {
			if(args.length!=3){
	            JOptionPane.showMessageDialog(null, "Please enter the Server address and port and username", "Wrong argument", JOptionPane.ERROR_MESSAGE);
	            System.exit(0);
	        }
			String input = JOptionPane.showInputDialog("Please input the username of the whiteboard you want to get in!");
			String username = args[2];
			  String host = args[0];
	      	  String port = args[1];
	      	  String address = "//" + host + ":" + port + "/" + input;
	      		server = (Server)Naming.lookup(address);
	      		Mycanvas client = new Mycanvas(server);
	      		
			if(!server.getClient().containsKey(username)) {
				if(server.agree(username)) {
					
		      		client.setName(username);
					server.register(username, client);
					
					server.boardcastClient(username);
					JOptionPane.showMessageDialog(null, "Welcome to the whiteboard","Welcome",JOptionPane.WARNING_MESSAGE);
					EventQueue.invokeLater(new Runnable(){
			            public void run(){
			                try{
			                	client.initialize();
			                    
			                }
			                catch(Exception e){
			                    e.printStackTrace();
			                }
			            }
			             
			        });
					}else {
						JOptionPane.showMessageDialog(null, "You are been rejected by the manager","Rejected",JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					}
			}else {
				JOptionPane.showMessageDialog(null, "The name has been used, please restart with a new name","Rejected",JOptionPane.WARNING_MESSAGE);
			}
      		


			
			
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Connection failed","Failed",JOptionPane.WARNING_MESSAGE);
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "No such whiteboard","Warning",JOptionPane.WARNING_MESSAGE);
    	  }
		
		
	}
	
	
}
