package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.uparis10.miage.ldap.shared.obj.OrgUnit;

public interface OrgUnitServiceAsync {
	void getOrgUnitsAll(AsyncCallback<List<OrgUnit>> callback);
}
