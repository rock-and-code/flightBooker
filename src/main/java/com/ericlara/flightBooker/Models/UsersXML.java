package com.ericlara.flightBooker.Models;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

// @XmlRootElement(name = "users")
// @XmlAccessorType (XmlAccessType.FIELD)
@JacksonXmlRootElement(localName = "users")
public class UsersXML {
	//@XmlElement(name = "user")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "user")
	private List<UserXML> users = null;

	public List<UserXML> getUsers() {
		return users;
	}

	public void setUsers(List<UserXML> users) {
		this.users = users;
	}
}