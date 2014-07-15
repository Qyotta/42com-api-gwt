/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.i18n;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

import de.qyotta.fourtytwocom.api.client.error.ErrorCodes;

@SuppressWarnings("nls")
public class ErrorsTest {

   private static final String ERRORS = "errors";

   @Test
   public void testEnglish() throws IOException {
      checkResource("ErrorMessages.properties");
   }

   @Test
   public void testGerman() throws IOException {
      checkResource("ErrorMessages_de.properties");
   }

   private void checkResource(final String propertiesResource) throws IOException {
      final Properties prop = new Properties();
      final InputStream in = getClass().getResourceAsStream(propertiesResource);
      prop.load(in);
      in.close();

      final ErrorCodes[] values = ErrorCodes.values();

      for (final ErrorCodes errorCode : values) {
         final String code = errorCode.name();
         assertTrue("ErrorMessages.properties contains key for '" + errorCode.name() + "'", prop.get(code) != null);
         final String flexkomErrors = (String) prop.get(ERRORS);
         assertTrue("errors map contains '" + code + "'", flexkomErrors.contains(code));
      }

      for (final Entry<Object, Object> entry : prop.entrySet()) {
         final String string = (String) entry.getKey();
         if (string.equals(ERRORS)) {
            continue;
         }
         final ErrorCodes errorCode = ErrorCodes.valueOf(string);
         assertTrue("ErrorMessages.properties contains key that does not exist in ErrorCodes '" + entry.getKey() + "'", errorCode != null);
      }
   }
}
