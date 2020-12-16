package board;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote{
	public boolean makeDraw(shape s) throws RemoteException;
	public void makeMessage(String s) throws RemoteException;
	public void makeMtoClient(String s1) throws RemoteException;
	public void makeNewFile() throws RemoteException;
	public void clientLeave(String s2) throws RemoteException;
	public void beKicked() throws RemoteException;
	public void beKickedS(String s) throws RemoteException;
	public void managerLeave() throws RemoteException;
	public boolean newUser(String s) throws RemoteException;
	public void openFile() throws RemoteException;

}
