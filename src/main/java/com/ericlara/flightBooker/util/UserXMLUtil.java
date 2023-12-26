package com.ericlara.flightBooker.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ericlara.flightBooker.Models.UserXML;
import com.ericlara.flightBooker.Models.UsersXML;

public class UserXMLUtil {
    private UserXMLUtil(){}
    /*
     * Method to read all the users from the users.xml file.
     */
    public static List<UserXML> readUsers() {


        UsersXML users = new UsersXML();

        try {
            File usersFile = new File(System.getProperty("java.io.tmpdir") + "users.xml");
            System.out.println("The users.xml file can be found here: " + System.getProperty("java.io.tmpdir"));
            if (usersFile.exists() && !usersFile.isDirectory()) {

                JAXBContext context = JAXBContext.newInstance(UsersXML.class);
                Unmarshaller um = context.createUnmarshaller();
                users = (UsersXML) um.unmarshal(usersFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users.getUsers();
    }

    /*
     * Method to write a new user to the users.xml file.
     */
    public static void registerUser(UserXML user) throws IOException {

        try {
            List<UserXML> users = readUsers();

            if (users == null) {
                users = new ArrayList<>();
                users.add(user);
            } else {
                users.add(user);
            }

            JAXBContext context;
            BufferedWriter writer = null;
            writer = Files.newBufferedWriter(new File(System.getProperty("java.io.tmpdir") + "users.xml").toPath());
            context = JAXBContext.newInstance(UsersXML.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(new UsersXML(users), writer);
            writer.close();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}

