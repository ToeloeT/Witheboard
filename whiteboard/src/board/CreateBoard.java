package board;


import java.awt.EventQueue;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JOptionPane;


public class CreateBoard {
	
	protected static Server server;
	public static void main(String [] args) {
		
		try {
			if(args.length!=3){
	            JOptionPane.showMessageDialog(null, "Please enter the Server address and port and username", "Wrong argument", JOptionPane.ERROR_MESSAGE);
	            System.exit(0);
	        }
			String username = args[2];
		  String host = args[0];
      	  String port = args[1];
      	  String address = "//" + host + ":" + port + "/" + username;
      	  Server s = new ServerImp();
      	  LocateRegistry.createRegistry(Integer.parseInt(args[1]));
      	  Naming.bind(address, s);
			
      		server = (Server)Naming.lookup(address);
      		
      	  
			whiteboardclient client = new whiteboardclient(server);
			client.setName(username);
			server.register(username, client);
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
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e1) {
    		  System.out.println("error");
    	  }
		
		
	}

}
