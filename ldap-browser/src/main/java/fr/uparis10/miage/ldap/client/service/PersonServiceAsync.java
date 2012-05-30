package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.uparis10.miage.ldap.shared.obj.Person;
import fr.uparis10.miage.ldap.shared.obj.SearchRequestModel;

public interface PersonServiceAsync {

	void getPersonsAll(AsyncCallback<List<Person>> callback);

	void searchPersons(String request, AsyncCallback<List<Person>> callback);

	void searchPersons(SearchRequestModel requestModel, AsyncCallback<List<Person>> callback);
}
