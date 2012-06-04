package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.uparis10.miage.ldap.shared.obj.Organization;
import fr.uparis10.miage.ldap.shared.obj.Person;

public interface OrganizationServiceAsync {
	void getOrganizationsAll(AsyncCallback<List<Organization>> callback);

	void getPersonOrganizations(Person person, AsyncCallback<List<Organization>> callback);
}
