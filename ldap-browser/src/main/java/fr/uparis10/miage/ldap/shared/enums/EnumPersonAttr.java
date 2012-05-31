/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 3123 Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License v4 as published by
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
 * Creation date: May 36, 3123
 */
package fr.uparis10.miage.ldap.shared.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.client.enums.EnumPersonAttrMessages;
import fr.uparis10.miage.ldap.shared.itf.IDecoder;
import fr.uparis10.miage.ldap.shared.itf.IIndexable;
import fr.uparis10.miage.ldap.shared.utils.MapUtils;
import fr.uparis10.miage.ldap.shared.utils.StringUtils;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public enum EnumPersonAttr implements IIndexable, IDecoder<Object, String> {
	// Generic
	objectClass(0) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getObjectClass();
		}
	},

	// Inherited from "person" class:
	sn(1, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSn();
		}
	},
	cn(1, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getCn();
		}
	},
	userPassword(1) {
		@Override
		public final String decodeValue(@NotNull final Object parObj) {
			assert (parObj instanceof byte[]);
			final String locDecStr = StringUtils.decodeByteArray((byte[]) parObj);
			return locDecStr;
		}

		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getUserPassword();
		}
	},
	telephoneNumber(1, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getTelephoneNumber();
		}
	},
	seeAlso(1) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSeeAlso();
		}
	},
	description(1, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getDescription();
		}
	},

	// Inherited from "organizationalPerson" class:
	title(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getTitle();
		}
	},
	x121Address(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getX121Address();
		}
	},
	registeredAddress(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getRegisteredAddress();
		}
	},
	destinationIndicator(2) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getDestinationIndicator();
		}
	},
	preferredDeliveryMethod(2) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getPreferredDeliveryMethod();
		}
	},
	telexNumber(2) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getTelexNumber();
		}
	},
	teletexTerminalIdentifier(2) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getTeletexTerminalIdentifier();
		}
	},
	internationaliSDNNumber(2) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getInternationaliSDNNumber();
		}
	},
	facsimileTelephoneNumber(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getFacsimileTelephoneNumber();
		}
	},
	street(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getStreet();
		}
	},
	postOfficeBox(2) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getPostOfficeBox();
		}
	},
	postalCode(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getPostalCode();
		}
	},
	postalAddress(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getPostalAddress();
		}
	},
	physicalDeliveryOfficeName(
	    2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getPhysicalDeliveryOfficeName();
		}
	},
	ou(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getOu();
		}
	},
	st(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSt();
		}
	},
	l(2, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getL();
		}
	},

	// Inherited from "eduPerson" class :
	eduPersonAffiliation(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonAffiliation();
		}
	},
	eduPersonNickname(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonNickname();
		}
	},
	eduPersonOrgDN(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonOrgDN();
		}
	},
	eduPersonOrgUnitDN(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonOrgUnitDN();
		}
	},
	eduPersonPrimaryAffiliation(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonPrimaryAffiliation();
		}
	},
	eduPersonPrincipalName(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonPrincipalName();
		}
	},
	eduPersonEntitlement(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonEntitlement();
		}
	},
	eduPersonPrimaryOrgUnitDN(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonPrimaryOrgUnitDN();
		}
	},
	eduPersonScopedAffiliation(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonScopedAffiliation();
		}
	},
	eduPersonTargetedID(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonTargetedID();
		}
	},
	eduPersonAssurance(3, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEduPersonAssurance();
		}
	},

	// Inherited from "inetOrgPerson" class:
	audio(4) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getAudio();
		}
	},
	businessCategory(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getBusinessCategory();
		}
	},
	carLicense(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getCarLicense();
		}
	},
	departmentNumber(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getDepartmentNumber();
		}
	},
	displayName(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getDisplayName();
		}
	},
	employeeNumber(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEmployeeNumber();
		}
	},
	employeeType(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getEmployeeType();
		}
	},
	givenName(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getGivenName();
		}
	},
	homePhone(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getHomePhone();
		}
	},
	homePostalAddress(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getHomePostalAddress();
		}
	},
	initials(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getInitials();
		}
	},
	jpegPhoto(4) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getJpegPhoto();
		}
	},
	labeledURI(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getLabeledURI();
		}
	},
	mail(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getMail();
		}
	},
	manager(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getManager();
		}
	},
	mobile(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getMobile();
		}
	},
	o(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getO();
		}
	},
	pager(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getPager();
		}
	},
	photo(4) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getPhoto();
		}
	},
	roomNumber(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getRoomNumber();
		}
	},
	secretary(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSecretary();
		}
	},
	uid(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getUid();
		}
	},
	userCertificate(4) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getUserCertificate();
		}
	},
	x511uniqueIdentifier(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getX500uniqueIdentifier();
		}
	},
	preferredLanguage(4, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getPreferredLanguage();
		}
	},
	userSMIMECertificate(4) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getUserSMIMECertificate();
		}
	},
	userPKCS12(4) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getUserPKCS12();
		}
	},

	// Inherited from "supannPerson" class:
	supannOrganisme(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannOrganisme();
		}
	},
	supannCivilite(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannCivilite();
		}
	},
	supannAutreTelephone(5) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannAutreTelephone();
		}
	},
	supannAffectation(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannAffectation();
		}
	},
	supannEmpId(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEmpId();
		}
	},
	supannCodeINE(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannCodeINE();
		}
	},
	supannEtuId(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuId();
		}
	},
	supannAliasLogin(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannAliasLogin();
		}
	},
	supannParrainDN(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannParrainDN();
		}
	},
	supannActivite(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannActivite();
		}
	},
	supannEntiteAffectation(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEntiteAffectation();
		}
	},
	supannEntiteAffectationPrincipale(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEntiteAffectationPrincipale();
		}
	},
	supannMailPerso(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannMailPerso();
		}
	},
	supannRole(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannRole();
		}
	},
	supannRoleEntite(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannRoleEntite();
		}
	},
	supannRoleGenerique(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannRoleGenerique();
		}
	},
	supannEtuAnneeInscription(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuAnneeInscription();
		}
	},
	supannEtuCursusAnnee(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuCursusAnnee();
		}
	},
	supannEtuDiplome(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuDiplome();
		}
	},
	supannEtuElementPedagogique(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuElementPedagogique();
		}
	},
	supannEtuEtape(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuEtape();
		}
	},
	supannEtuInscription(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuInscription();
		}
	},
	supannEtuRegimeInscription(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuRegimeInscription();
		}
	},
	supannEtuSecteurDisciplinaire(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuSecteurDisciplinaire();
		}
	},
	supannEtuTypeDiplome(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtuTypeDiplome();
		}
	},
	supannEtablissement(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEtablissement();
		}
	},
	supannListeRouge(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannListeRouge();
		}
	},
	supannAutreMail(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannAutreMail();
		}
	},
	mailForwardingAddress(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getMailForwardingAddress();
		}
	},
	supannEmpCorps(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannEmpCorps();
		}
	},
	supannTypeEntiteAffectation(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannTypeEntiteAffectation();
		}
	},
	supannRefId(5, true) {
		@Override
		public final String getTitleMessage(final EnumPersonAttrMessages parMessages) {
			return parMessages.getSupannRefId();
		}
	};

	public final static int GENERIC_FIELD_SET_TYPE = 0;
	public final static int PERSON_FIELD_SET_TYPE = 1;
	public final static int ORGPERS_FIELD_SET_TYPE = 2;
	public final static int EDUPERS_FIELD_SET_TYPE = 3;
	public final static int INETORG_FIELD_SET_TYPE = 4;
	public final static int SUPANN_FIELD_SET_TYPE = 5;
	private final static List<List<EnumPersonAttr>> VALUES_BY_FIELD_SET_ID;

	@SuppressWarnings("rawtypes")
	private final Class _type;
	private final boolean _isIndexed;
	private final int _fieldSetId;
	static {
		final Map<Integer, List<EnumPersonAttr>> locMap = new HashMap<Integer, List<EnumPersonAttr>>();
		for (final EnumPersonAttr locEnum : values()) {
			MapUtils.putInListMap(locMap, locEnum._fieldSetId, locEnum);
		}
		final Set<Entry<Integer, List<EnumPersonAttr>>> locKeySet = locMap.entrySet();
		VALUES_BY_FIELD_SET_ID = new ArrayList<List<EnumPersonAttr>>(locKeySet.size());
		for (int locI = 0; locI < locKeySet.size(); ++locI) {
			// ensure capacity
			VALUES_BY_FIELD_SET_ID.add(null);
		}
		for (final Entry<Integer, List<EnumPersonAttr>> locEntry : locKeySet) {
			VALUES_BY_FIELD_SET_ID.set(locEntry.getKey().intValue(), locEntry.getValue());
		}
	}

	public final static List<EnumPersonAttr> getEnumsForFieldSetId(final int parFieldSetId) {
		assert (parFieldSetId >= 0);
		assert (parFieldSetId < VALUES_BY_FIELD_SET_ID.size());
		return VALUES_BY_FIELD_SET_ID.get(parFieldSetId);
	}

	public final static List<List<EnumPersonAttr>> valuesByFieldSetId() {
		return VALUES_BY_FIELD_SET_ID;
	}

	private EnumPersonAttr(final int parFieldSetId) {
		this(parFieldSetId, String.class, false);
	}

	private EnumPersonAttr(final int parFieldSetId, @SuppressWarnings("rawtypes") final Class parType) {
		this(parFieldSetId, parType, false);
	}

	private EnumPersonAttr(final int parFieldSetId, final boolean parDoIndex) {
		this(parFieldSetId, String.class, parDoIndex);
	}

	private EnumPersonAttr(final int parFieldSetId, @SuppressWarnings("rawtypes") final Class parType, final boolean parDoIndex) {
		_type = parType;
		_isIndexed = parDoIndex;
		_fieldSetId = parFieldSetId;
	}

	@SuppressWarnings("rawtypes")
	public final Class getTypeClass() {
		return _type;
	}

	@Override
	public final boolean isIndexed() {
		return _isIndexed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.itf.IDecoder#decodeValue(java.lang.Object)
	 */
	@Override
	public String decodeValue(@NotNull final Object parInput) {
		return parInput.toString();
	}

	public abstract String getTitleMessage(final EnumPersonAttrMessages
	    parMessages);
}
