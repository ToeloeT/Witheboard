package board;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface Server extends Remote{
	public void register(String name, Client c) throws RemoteException;
	public void boardcast(shape s) throws RemoteException;
	public ArrayList<shape> getShapeList() throws RemoteException;
	public void boardcastMessage(String message) throws RemoteException;
	public void leave(String name) throws RemoteException;
	public ConcurrentHashMap<String,Client> getClient() throws RemoteException;
	public void boardcastClient(String text) throws RemoteException;
//	public ArrayList<String> getMessage() throws RemoteException;
	public void newButton() throws RemoteException;
	public boolean remove(String name) throws RemoteException;
	public void managerRemove() throws RemoteException;
	public boolean agree(String name) throws RemoteException;
	public void openButton(ArrayList<shape> lists) throws RemoteException;
}
