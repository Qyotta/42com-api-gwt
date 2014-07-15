/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;

import de.qyotta.fourtytwocom.api.client.auth.AuthenticationDispatcher;

public class Services {
   public static final String DATE_FORMAT_STR = "yyyy'-'MM'-'dd'-'HH'-'mm"; //$NON-NLS-1$
   public static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat(DATE_FORMAT_STR);

   private Api api;
   private final AuthenticationDispatcher authenticationDispatcher;
   private String domain;

   public Services() {
      authenticationDispatcher = AuthenticationDispatcher.INSTANCE;
   }

   public void setDomain(final String domain) {
      this.domain = domain;
      api = null;
   }

   public void setUsername(final String username) {
      authenticationDispatcher.setUsername(username);
   }

   public void setPassword(final String password) {
      authenticationDispatcher.setPassword(password);
   }

   public Api getApi() {
      if (api != null) {
         return api;
      }
      final Resource resource = new Resource(domain);
      Defaults.setDateFormat(DATE_FORMAT_STR);
      Defaults.setDispatcher(authenticationDispatcher);

      api = GWT.create(Api.class);
      ((RestServiceProxy) api).setResource(resource);
      return api;
   }

}
