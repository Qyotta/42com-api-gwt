/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2013 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.auth;

public final class Strings {
   private Strings() {

   }

   public static String format(final String format, final Object... args) {
      String retVal = format;
      for (final Object current : args) {
         retVal = retVal.replaceFirst("[%][s]", current.toString()); //$NON-NLS-1$
      }
      return retVal;
   }
}
