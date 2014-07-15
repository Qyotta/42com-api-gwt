/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.example.client.modules.account;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AccountPlace extends Place {

   public static class Tokenizer implements PlaceTokenizer<AccountPlace> {

      @Override
      public AccountPlace getPlace(final String token) {
         return new AccountPlace();
      }

      @Override
      public String getToken(final AccountPlace place) {
         return ""; //$NON-NLS-1$
      }

   }

}
