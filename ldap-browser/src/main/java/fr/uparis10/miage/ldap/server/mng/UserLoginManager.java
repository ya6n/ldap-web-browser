/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License v3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Creation date: May 30, 2012
 */
package fr.uparis10.miage.ldap.server.mng;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;

import fr.uparis10.miage.ldap.server.utils.ServerStringUtils;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.exc.InvalidPasswordException;
import fr.uparis10.miage.ldap.shared.exc.NoSuchUserException;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * This class is used for user authentication over an LDAP server. No cache is
 * done therefore in the manager.
 * 
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class UserLoginManager extends ABasicLdapManager<EnumPersonAttr, String, Person> {
	private static UserLoginManager theInst;

	public final static UserLoginManager getInstance() {
		if (null == theInst) {
			theInst = new UserLoginManager();
		}

		return theInst;
	}

	public final Person login(@NotNull final String parLogin, @NotNull final String parPass) throws NoSuchUserException, InvalidPasswordException {
		final List<Person> locLoginList = getFreshList(String.format("uid=%s", parLogin));
		assert (null != locLoginList);
		if (locLoginList.isEmpty()) {
			throw new NoSuchUserException(parLogin);
		}
		assert (1 == locLoginList.size());
		final Person locPerson = locLoginList.get(0);
		assert (null != locPerson);
		assert (parLogin.equals(locPerson.get(EnumPersonAttr.uid)));
		if (!checkUserPassword(locPerson, parPass)) {
			throw new InvalidPasswordException(String.format("Invalid password [%s] for user [%s]", parPass, parLogin));
		}

		return locPerson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.mng.ABasicLdapManager#getDNPrefix()
	 */
	@Override
	protected final String getDNPrefix() {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.mng.ABasicLdapManager#createNewObject()
	 */
	@Override
	protected final Person createNewObject() {
		return new Person();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.mng.ABasicLdapManager#valueOfIndex(java.lang
	 * .String)
	 */
	@Override
	protected final EnumPersonAttr valueOfIndex(@NotNull final String parName) {
		return EnumPersonAttr.valueOf(parName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.mng.ABasicLdapManager#decodeAttribute(javax
	 * .naming.directory.Attribute, fr.uparis10.miage.ldap.shared.itf.IIndexable)
	 */
	@Override
	protected final String decodeAttribute(@NotNull final Attribute parInput, @NotNull final EnumPersonAttr parType) throws NamingException {
		return ServerStringUtils.decodeAttribute(parInput, parType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.mng.ABasicLdapManager#getCtxManager()
	 */
	@Override
	protected final ALdapCtxManager getCtxManager() throws FileNotFoundException, IOException, NamingException {
		return AuthLdapCtxManager.getInstance();
	}

	private final boolean checkUserPassword(@NotNull final Person parUser, @NotNull final String parPassword) {
		final String locLdapPass = parUser.get(EnumPersonAttr.userPassword);
		if (locLdapPass == null) {
			return parPassword.isEmpty();
		} // sinon...

		final LdapShaPasswordEncoder locPassEncoder = new LdapShaPasswordEncoder();
		return locPassEncoder.isPasswordValid(locLdapPass, parPassword, null);
	}
}
