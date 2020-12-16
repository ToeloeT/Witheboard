package board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImp extends UnicastRemoteObject implements Server{
	
	private ArrayList<shape> list = new ArrayList<shape>();
	ConcurrentHashMap<String,Client> clist = new ConcurrentHashMap<String,Client>();

	protected ServerImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void boardcast(shape s) throws RemoteException {
		// TODO Auto-generated method stub
		list.add(s);
		for(Entry<String,Client> entry: clist.entrySet()) {
			entry.getValue().makeDraw(s);
		}
		
	}

	@Override
	public synchronized ArrayList<shape> getShapeList() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<shape> new_s = list;
		return new_s;
	}

	@Override
	public synchronized void register(String name, Client c) throws RemoteException {
		// TODO Auto-generated method stub
		clist.put(name, c);
		
	}

	@Override
	public synchronized void boardcastMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		for(Entry<String,Client> entry: clist.entrySet()) {
			entry.getValue().makeMessage(message);
			
		}
		
	}

	@Override
	public synchronized void leave(String name) throws RemoteException {
		// TODO Auto-generated method stub
		clist.remove(name);
		for(Entry<String,Client> entry: clist.entrySet()) {
			entry.getValue().clientLeave(name);
			
		}
		
	}

	@Override
	public synchronized ConcurrentHashMap<String, Client> getClient() throws RemoteException {
		// TODO Auto-generated method stub
		ConcurrentHashMap<String,Client> clist_m = clist;
		return clist_m;
	}

	@Override
	public synchronized void boardcastClient(String text) throws RemoteException {
		// TODO Auto-generated method stub
		for(Entry<String,Client> entry: clist.entrySet()) {
			entry.getValue().makeMtoClient(text);
			
		}
		
	}

	@Override
	public synchronized void newButton() throws RemoteException {
		// TODO Auto-generated method stub
		list = new ArrayList<shape>();
		
		for(Entry<String,Client> entry: clist.entrySet()) {
			entry.getValue().makeNewFile();
			
		}
	}

	@Override
	public boolean remove(String name) throws RemoteException {
		// TODO Auto-generated method stub
		ConcurrentHashMap.Entry<String, Client> first = clist.entrySet().iterator().next();
		if(clist.containsKey(name) && !name.equals(first.getKey())) {
			clist.get(name).beKicked();
			clist.remove(name);
			for(Entry<String,Client> entry: clist.entrySet()) {
				entry.getValue().beKickedS(name);
				
			}
			return true;
			
		}
		return false;
	}

	@Override
	public void managerRemove() throws RemoteException {
		// TODO Auto-generated method stub
		for(Entry<String,Client> entry: clist.entrySet()) {
			entry.getValue().managerLeave();
			
		}
		
	}

	@Override
	public boolean agree(String name) throws RemoteException {
		// TODO Auto-generated method stub
		ConcurrentHashMap.Entry<String, Client> first = clist.entrySet().iterator().next();
		if(first.getValue().newUser(name)) {
			return true;
		}
		return false;
	}

	@Override
	public synchronized void openButton(ArrayList<shape> lists) throws RemoteException {
		// TODO Auto-generated method stub
		list = lists;
		for (Entry<String, Client> entry : clist.entrySet()) {
			entry.getValue().openFile();
		}
		
	}

}
