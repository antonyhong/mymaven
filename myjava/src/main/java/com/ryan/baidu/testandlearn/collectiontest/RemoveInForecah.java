package com.ryan.baidu.testandlearn.collectiontest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class RemoveInForecah {
    public static void main(String args[]) {
        List<String> listOfPhones = new ArrayList<String>(Arrays.asList("iPhone 6S", "iPhone 6", "iPhone 5", "Samsung Galaxy 4", "Lumia Nokia"));
        System.out.println("list of phones: " + listOfPhones);

        // Iterating and removing objects from list
        // This is wrong way, will throw ConcurrentModificationException
        for (String phone : listOfPhones) {
            if (phone.startsWith("iPhone")) {
                // listOfPhones.remove(phone);
                // will throw exception
            }
        }

        for (Iterator<String> itr = listOfPhones.iterator(); itr.hasNext(); ) {
            String phone = itr.next();
            if (phone.startsWith("iPhone")) {
                 listOfPhones.remove(phone);// wrong again
                itr.remove();// right call

            }
        }
        System.out.println("list after removal: " + listOfPhones);


    }
}
