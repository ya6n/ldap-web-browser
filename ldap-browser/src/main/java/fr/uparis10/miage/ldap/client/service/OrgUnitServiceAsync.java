package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.uparis10.miage.ldap.shared.obj.OrgUnit;
import fr.uparis10.miage.ldap.shared.obj.Person;

public interface OrgUnitServiceAsync {
	void getOrgUnitsAll(AsyncCallback<List<OrgUnit>> callback);

	void getPersonOrgUnits(Person person, AsyncCallback<List<OrgUnit>> callback);
}
