package com.ericlara.flightBooker.Models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@JacksonXmlRootElement(localName = "users")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UsersXML {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "user")
	@XmlElement(name = "user")
	private List<UserXML> users = null;

	public List<UserXML> getUsers() {
		return users;
	}

	public void setUsers(List<UserXML> users) {
		this.users = users;
	}
}