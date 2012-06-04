package fr.uparis10.miage.ldap.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.OrgUnit;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("orgunit")
public interface OrgUnitService extends RemoteService {
	List<OrgUnit> getOrgUnitsAll() throws IllegalArgumentException, UserNotLoggedException, ServicePropertiesIOException;

	/**
   * @param person
   * @return
   * @throws IllegalArgumentException
   * @throws UserNotLoggedException
   * @throws ServicePropertiesIOException
   */
  List<OrgUnit> getPersonOrgUnits(Person person) throws IllegalArgumentException, UserNotLoggedException, ServicePropertiesIOException;
}
