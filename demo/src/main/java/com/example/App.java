package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        ServerSocket s;
        Socket client = null;
        boolean live = true;
        String receive;


        try {


            s = new ServerSocket(3000);    
            Class class1 = new Class(5,"cia","etc");
            studente studente1 = new studente("ale", "pan", "1231");
            studente studente2 = new studente("mabe", "iri","1231");
            studente studente3 = new studente("alex", "bot","1231");
            studente studente4 = new studente("tho", "morr","1231");
            class1.add(studente1);
            class1.add(studente2);
            class1.add(studente3);
            class1.add(studente4);
            System.out.println("server pronto");
            while (live) {

                client = s.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                
                XmlMapper xml = new XmlMapper();
                String xmlSerial = xml.writeValueAsString(class1);

                System.out.println("---xml---");
                System.out.println(xmlSerial);
                out.writeBytes( xmlSerial + "\n");

                ObjectMapper obj= new ObjectMapper();
                String objSerial = obj.writeValueAsString(class1);
                System.out.println("---json---");
                System.out.println(objSerial);
                out.writeBytes( objSerial + "\n");


                live = false;
            }

            


        } catch (Exception e) {
            // TODO: handle exception
        }


















    }
}
