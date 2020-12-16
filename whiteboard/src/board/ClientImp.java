package board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImp extends UnicastRemoteObject implements Client{

	protected ClientImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean makeDraw(shape s) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeMessage(String s) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeMtoClient(String s1) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeNewFile() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientLeave(String s2) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beKicked() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beKickedS(String s) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void managerLeave() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean newUser(String s) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openFile() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
