package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.uparis10.miage.ldap.shared.obj.Group;

public interface GroupServiceAsync {
	void getGroupsAll(AsyncCallback<List<Group>> callback);

	void getPersonGroups(String supannRole, AsyncCallback<List<Group>> callback);
}
