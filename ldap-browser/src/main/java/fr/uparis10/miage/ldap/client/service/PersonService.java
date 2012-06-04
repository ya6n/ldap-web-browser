package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Person;
import fr.uparis10.miage.ldap.shared.obj.SearchRequestModel;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("person")
public interface PersonService extends RemoteService {

	List<Person> getPersonsAll() throws IllegalArgumentException, UserNotLoggedException;

	List<Person> searchPersons(String request) throws IllegalArgumentException, UserNotLoggedException;

	List<Person> searchPersons(SearchRequestModel requestModel) throws IllegalArgumentException, UserNotLoggedException;
}
