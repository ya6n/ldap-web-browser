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
 * Creation date: May 26, 2012
 */
package fr.uparis10.miage.ldap.enums;

import fr.uparis10.miage.ldap.itf.IIndexable;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public enum EnumPersonAttr implements IIndexable {
	// Generic
	objectClass,
	
	// Inherited from "person" class:
	sn(true), cn(true), userPassword, telephoneNumber(true), seeAlso, description(true),

	// Inherited from "organizationalPerson" class:
	title(true), x121Address(true), registeredAddress(true), destinationIndicator, preferredDeliveryMethod, telexNumber, teletexTerminalIdentifier, internationaliSDNNumber, facsimileTelephoneNumber(
	    true), street(true), postOfficeBox, postalCode(true), postalAddress(true), physicalDeliveryOfficeName(true), ou(true), st(true), l(true),

	// Inherited from "eduPerson" class :
	eduPersonAffiliation(true), eduPersonNickname(true), eduPersonOrgDN(true), eduPersonOrgUnitDN(true), eduPersonPrimaryAffiliation(true), eduPersonPrincipalName(
	    true), eduPersonEntitlement(true), eduPersonPrimaryOrgUnitDN(true), eduPersonScopedAffiliation(true), eduPersonTargetedID(true), eduPersonAssurance(true),

	// Inherited from "inetOrgPerson" class:
	audio, businessCategory(true), carLicense(true), departmentNumber(true), displayName(true), employeeNumber(true), employeeType(true), givenName(true), homePhone(
	    true), homePostalAddress(true), initials(true), jpegPhoto, labeledURI(true), mail(true), manager(true), mobile(true), o(true), pager(true), photo, roomNumber(
	    true), secretary(true), uid(true), userCertificate, x500uniqueIdentifier(true), preferredLanguage(true), userSMIMECertificate, userPKCS12,

	// Inherited from "supannPerson" class:
	supannOrganisme(true), supannCivilite(true), supannAutreTelephone(), supannAffectation(true), supannEmpId(true), supannCodeINE(true), supannEtuId(true), supannAliasLogin(
	    true), supannParrainDN(true), supannActivite(true), supannEntiteAffectation(true), supannEntiteAffectationPrincipale(true), supannMailPerso(true), supannRole(
	    true), supannRoleEntite(true), supannRoleGenerique(true), supannEtuAnneeInscription(true), supannEtuCursusAnnee(true), supannEtuDiplome(true), supannEtuElementPedagogique(
	    true), supannEtuEtape(true), supannEtuInscription(true), supannEtuRegimeInscription(true), supannEtuSecteurDisciplinaire(true), supannEtuTypeDiplome(true), supannEtablissement(
	    true), supannListeRouge(true), supannAutreMail(true), mailForwardingAddress(true), supannEmpCorps(true), supannTypeEntiteAffectation(true), supannRefId(
	    true);

	@SuppressWarnings("rawtypes")
	private final Class _type;
	private final boolean _isIndexed;

	private EnumPersonAttr() {
		this(String.class, false);
	}

	private EnumPersonAttr(@SuppressWarnings("rawtypes") final Class parType) {
		this(parType, false);
	}

	private EnumPersonAttr(final boolean parDoIndex) {
		this(String.class, parDoIndex);
	}

	private EnumPersonAttr(@SuppressWarnings("rawtypes") final Class parType, final boolean parDoIndex) {
		_type = parType;
		_isIndexed = parDoIndex;
	}

	@SuppressWarnings("rawtypes")
	public final Class getTypeClass() {
		return _type;
	}

	@Override
	public final boolean isIndexed() {
		return _isIndexed;
	}

}
