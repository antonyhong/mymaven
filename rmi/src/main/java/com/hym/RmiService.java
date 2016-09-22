package com.hym;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by hongyongming on 2016/9/22.
 */
public class RmiService {
    public static void main(String[] args) {
        try {
            PersonService personService=new PersonServiceImp();
//注册通讯端口
            LocateRegistry.createRegistry(6600);
//注册通讯路径
            Naming.rebind("rmi://127.0.0.1:6600/PersonService", personService);
            System.out.println("Service Start!");

        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
