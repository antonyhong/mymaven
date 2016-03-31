package com.ming.testandlearn.reflect;

import com.ming.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;

/**
 * Created by hongyongming on 2016/3/31.
 */
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException {

//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter class name(e.g. java.util.Date):");
//        name = in.next();
//
        //printClassInfo("java.util.Date");
        //printClassInfo("java.util.ArrayList");
        printClassInfo(Student.class.getName());

    }


    public static void   printClassInfo(String className) throws ClassNotFoundException {
        Class cl = Class.forName(className);
        Class supClazz = cl.getSuperclass();
        String modifier = Modifier.toString(cl.getModifiers());
        String name = cl.getName();

        System.out.print(String.format("%s class %s", modifier, name));
        if(supClazz !=null && supClazz!= Object.class) System.out.print(" extends " + supClazz.getName() );
        System.out.println("{\n");
        printContructors(cl);
        System.out.println();
        printMethods(cl);
        System.out.println();
        printFields(cl);
        System.out.println("}");




    }

    public static void printContructors(Class clz) {
        Constructor<?>[] constructors = clz.getConstructors();
        for (Constructor constructor : constructors) {
            String modifier = Modifier.toString(constructor.getModifiers());
            String name = constructor.getName();
            Class<?>[]  parameters = constructor.getParameterTypes();

            Optional<String> optional= Arrays.asList(parameters).stream().map(p->p.getName()).reduce((p1,p2)-> p1+","+p2);
            String parametersStr =optional.isPresent()? optional.get(): " ";
            System.out.println(String.format("\t%s %s (%s);",modifier,name,parametersStr));
        }
    }

    public static void printMethods(Class clz) {
        Arrays.asList(clz.getMethods() ).forEach(method -> {
            String modifier =Modifier.toString(method.getModifiers());
            String returnType =method.getReturnType().getName();
            String methodName= method.getName();
            Class<?>[]  parameters = method.getParameterTypes();
            Optional<String> optional = Arrays.asList(parameters).stream().map(p->p.getName()).reduce((p1,p2)-> p1+","+p2);
            String parametersStr =optional.isPresent()? optional.get(): " ";
            System.out.println(String.format("\t%s %s %s(%s);",modifier,returnType,methodName,parametersStr));
        });
    }

    public static void printFields(Class clz) {
        Arrays.asList(clz.getFields() ).forEach(field -> {
            String modifier = Modifier.toString(field.getModifiers());
            String name = field.getName();
            String type = field.getType().getName();
            System.out.println(String.format("\t%s %s %s;", modifier, type, name));
        });
    }

}
