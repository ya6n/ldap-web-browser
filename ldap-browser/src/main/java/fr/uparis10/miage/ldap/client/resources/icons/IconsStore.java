/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 gorodenco
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
 * Creation date: 27 mai 2012
 */
package fr.uparis10.miage.ldap.client.resources.icons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author gorodenco
 * 
 */
public interface IconsStore extends ClientBundle {

	IconsStore INSTANCE = GWT.create(IconsStore.class);

	@Source("search64.png")
	ImageResource searchImg();

	@Source("logout64.png")
	ImageResource logoutImg();

	@Source("login64.png")
	ImageResource loginImg();
	
	@Source("search16bis.png")
	ImageResource searchIcon();

	@Source("personIcon16.png")
	ImageResource personIcon();

	@Source("propertyIcon64.png")
	ImageResource propertyIconBig();

	@Source("propertyIcon16.png")
	ImageResource propertyIconSmall();

}
