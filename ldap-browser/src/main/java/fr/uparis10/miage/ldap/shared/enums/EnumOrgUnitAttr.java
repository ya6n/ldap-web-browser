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
package fr.uparis10.miage.ldap.shared.enums;

import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.client.enums.EnumOrgUnitAttrMessages;
import fr.uparis10.miage.ldap.shared.itf.IDecoder;
import fr.uparis10.miage.ldap.shared.itf.IIndexable;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public enum EnumOrgUnitAttr implements IIndexable, IDecoder<Object, String> {
	// Generic
	objectClass {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getObjectClass();
		}
	},

	// Inherited from "organizationalUnit" class:
	ou(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getOu();
		}
	},
	userPassword {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getUserPassword();
		}
	},
	searchGuide(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getSearchGuide();
		}
	},
	seeAlso {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getSeeAlso();
		}
	},
	businessCategory(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getBusinessCategory();
		}
	},
	x121Address(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getX121Address();
		}
	},
	registeredAddress(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getRegisteredAddress();
		}
	},
	destinationIndicator(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getDestinationIndicator();
		}
	},
	preferredDeliveryMethod(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getPreferredDeliveryMethod();
		}
	},
	telexNumber(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getTelexNumber();
		}
	},
	teletexTerminalIdentifier(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getTeletexTerminalIdentifier();
		}
	},
	telephoneNumber(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getTelephoneNumber();
		}
	},
	internationaliSDNNumber(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getInternationaliSDNNumber();
		}
	},
	facsimileTelephoneNumber(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getFacsimileTelephoneNumber();
		}
	},
	street(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getStreet();
		}
	},
	postOfficeBox(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getPostOfficeBox();
		}
	},
	postalCode(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getPostalCode();
		}
	},
	postalAddress(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getPostalAddress();
		}
	},
	physicalDeliveryOfficeName(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getPhysicalDeliveryOfficeName();
		}
	},
	st {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getSt();
		}
	},
	l {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getL();
		}
	},
	description(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getDescription();
		}
	},

	// Inherited from "supannEntite" class:
	supannCodeEntite(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getSupannCodeEntite();
		}
	},
	supannTypeEntite(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getSupannTypeEntite();
		}
	},
	supannCodeEntiteParent(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getSupannCodeEntiteParent();
		}
	},
	supannRefId(true) {
		@Override
		public final String getTitleMessage(final EnumOrgUnitAttrMessages parMessages) {
			return parMessages.getSupannRefId();
		}
	};

	private final Class<?> dataType;
	private final boolean isIndexed;

	private EnumOrgUnitAttr() {
		this(String.class, false);
	}

	private EnumOrgUnitAttr(final Class<?> parType) {
		this(parType, false);
	}

	private EnumOrgUnitAttr(final boolean parDoIndex) {
		this(String.class, parDoIndex);
	}

	private EnumOrgUnitAttr(final Class<?> parType, final boolean parDoIndex) {
		dataType = parType;
		isIndexed = parDoIndex;
	}

	public final Class<?> getTypeClass() {
		return dataType;
	}

	@Override
	public final boolean isIndexed() {
		return isIndexed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.itf.IDecoder#decodeValue(java.lang.Object)
	 */
	@Override
	public final String decodeValue(@NotNull final Object parInput) {
		return parInput.toString();
	}

	public abstract String getTitleMessage(final EnumOrgUnitAttrMessages
	    parMessages);
}
