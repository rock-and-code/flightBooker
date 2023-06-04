package com.ericlara.flightBooker.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ericlara.flightBooker.Models.UserXML;
import com.ericlara.flightBooker.Models.UsersXML;
import com.ericlara.flightBooker.Services.UserXMLService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserXMLUtil {
    
    private UserXMLService userXMLService;

    /*
     * Method to read all the users from the users.xml file.
     */
    public List<UserXML> readUsers() {


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
    public void registerUser(UserXML user) throws IOException {

        try {
            List<UserXML> users = readUsers();

            if (users == null) {
                users = userXMLService.findAllUsers();
            } else {
                users.add(0, user);
            }

            JAXBContext context;
            BufferedWriter writer = null;
            writer = new BufferedWriter(new FileWriter(new File(System.getProperty("java.io.tmpdir") + "users.xml")));
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

