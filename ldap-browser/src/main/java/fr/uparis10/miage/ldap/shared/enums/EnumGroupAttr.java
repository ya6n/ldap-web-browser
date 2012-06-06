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

import fr.uparis10.miage.ldap.client.enums.EnumGroupAttrMessages;
import fr.uparis10.miage.ldap.shared.itf.IDecoder;
import fr.uparis10.miage.ldap.shared.itf.IIndexable;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public enum EnumGroupAttr implements IIndexable, IDecoder<Object, String> {
	// Generic
	objectClass {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getObjectClass();
		}
	},

	// Inherited from "groupOfNames" class:
	member {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getMember();
		}
	},
	cn(true) {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getCn();
		}
	},
	businessCategory(true) {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getBusinessCategory();
		}
	},
	seeAlso {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getSeeAlso();
		}
	},
	owner(true) {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getOwner();
		}
	},
	ou(true) {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getOu();
		}
	},
	o(true) {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getO();
		}
	},
	description {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getDescription();
		}
	},

	// Inherited from "supannGroupe" class:
	supannGroupeDateFin {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getSupannGroupeDateFin();
		}
	},
	supannGroupeAdminDN(true) {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getSupannGroupeAdminDN();
		}
	},
	supannGroupeLecteurDN(true) {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getSupannGroupeLecteurDN();
		}
	},
	supannRefId(true) {
		@Override
		public final String getTitleMessage(final EnumGroupAttrMessages parMessages) {
			return parMessages.getSupannRefId();
		}
	};

	private final Class<?> dataType;
	private final boolean isIndexed;

	private EnumGroupAttr() {
		this(String.class, false);
	}

	private EnumGroupAttr(final Class<?> parType) {
		this(parType, false);
	}

	private EnumGroupAttr(final boolean parDoIndex) {
		this(String.class, parDoIndex);
	}

	private EnumGroupAttr(final Class<?> parType, final boolean parDoIndex) {
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

	public abstract String getTitleMessage(final EnumGroupAttrMessages
	    parMessages);
}
