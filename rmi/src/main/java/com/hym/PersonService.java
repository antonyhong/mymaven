package com.hym;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 * Created by hongyongming on 2016/9/22.
 */
public interface PersonService extends Remote{
    public List<PersonEntity> GetList() throws RemoteException;
}
