package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Group;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("group")
public interface GroupService extends RemoteService {
	List<Group> getGroupsAll() throws IllegalArgumentException, UserNotLoggedException, ServicePropertiesIOException;
}
