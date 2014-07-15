/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.auth;

import java.util.Date;

import org.fusesource.restygwt.client.Dispatcher;
import org.fusesource.restygwt.client.Method;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

public class AuthenticationDispatcher implements Dispatcher {
   private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat(PredefinedFormat.ISO_8601);

   public static final AuthenticationDispatcher INSTANCE = new AuthenticationDispatcher();

   private String username;

   private String password;

   @Override
   public Request send(final Method method, final RequestBuilder builder) throws RequestException {
      if (username == null || password == null) {
         return builder.send();
      }

      final Date date = new Date();
      final String formattedDate = DATE_FORMAT.format(date);
      builder.setHeader("x-date", formattedDate); //$NON-NLS-1$

      final String md5Password = CryptoJS.MD5(password);

      final String hmac = calculateHmac(md5Password, formattedDate);
      builder.setHeader("x-authorization", username + ":" + hmac); //$NON-NLS-1$ //$NON-NLS-2$ 

      return builder.send();
   }

   private String calculateHmac(final String pPassword, final String formattedDate) {
      final String message = password + "\n" + formattedDate; //$NON-NLS-1$
      final String hmacSHA1 = CryptoJS.hmacSHA1_base64(message, pPassword);
      return hmacSHA1;
   }

   public void setUsername(final String username) {
      this.username = username;
   }

   public void setPassword(final String password) {
      this.password = password;
   }

}
