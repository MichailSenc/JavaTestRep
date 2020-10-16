package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) {
//        exampleRuntimeException();
        exampleUserException();
        exampleIOExceptionSimple();
        exampleIOException();
        exampleTryWithResources();
    }

    public static void exampleRuntimeException(){
        int d = 0;
        int a = 42 / d;
        String str = "5," + "6";
        double r = Double.parseDouble(str);
        String []data = "D1 D2 D3".split(" +");
        System.out.println(data[3]);
    }
    public static void exampleUserException() {
        try {
            Person p = new Person("Vasya", 10);
            p.setAge(-5);

        } catch (PersonAgeException e) {
            System.out.println("Error in value " + e.getValueError());
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            StackTraceElement elements[] = e.getStackTrace();
//            for (int i = 0; i < elements.length; i++) {
//                System.err.println(elements[i].getFileName()
//                        + ":" + elements[i].getLineNumber()
//                        + ">> "
//                        + elements[i].getMethodName() + "()");
//            }
        }
    }

    public  static void exampleIOExceptionSimple(){
//        byte[] bytesToWrite = new byte[100];
//
//            OutputStream os = new FileOutputStream("output.file");
//            os.write(bytesToWrite);
//            os.close();
        byte[] bytesToWrite = new byte[100];
        try {
            OutputStream os = new FileOutputStream("output.file");
            os.write(bytesToWrite);
            os.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find the file.");
        } catch (IOException ioex) {
            System.out.println("Error writing file: " + ioex.getMessage());
        }
    }
    public static void exampleIOException(){
        byte[] bytesToWrite = new byte[100];
        OutputStream os = null;
        try {
            os = new FileOutputStream("output.file");
            os.write(bytesToWrite);
            System.out.println("end try");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find the file.");
        } catch (IOException ioex) {
            System.out.println("Error writing file: " + ioex.getMessage());
        } finally {
            System.out.println("finally.");
            if (os != null) {
                try {
                    os.close();
                } catch (IOException closeException) {
                    System.out.println("closeException: "
                            + closeException.getMessage());
                }
            }
        }
    }
    public static void exampleTryWithResources(){
        byte[] bytesToWrite = new byte[100];

        try (OutputStream os = new FileOutputStream("output.file")) {
            os.write(bytesToWrite);
            System.out.println("end try");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find the file.");
        } catch (IOException ioex) {
            System.out.println("Error writing file: " + ioex.getMessage());
        }
    }
}
