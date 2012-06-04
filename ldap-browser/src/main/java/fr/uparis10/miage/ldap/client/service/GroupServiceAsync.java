package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.uparis10.miage.ldap.shared.obj.Group;
import fr.uparis10.miage.ldap.shared.obj.Person;

public interface GroupServiceAsync {
	void getGroupsAll(AsyncCallback<List<Group>> callback);

	void getPersonGroups(Person person, AsyncCallback<List<Group>> callback);
}
