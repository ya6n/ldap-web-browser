package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.uparis10.miage.ldap.shared.obj.Person;

public interface PersonServiceAsync {
	void getPersonsAll(AsyncCallback<List<Person>> callback);
}
