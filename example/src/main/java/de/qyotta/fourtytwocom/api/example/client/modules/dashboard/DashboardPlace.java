/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.example.client.modules.dashboard;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DashboardPlace extends Place {

   public static class Tokenizer implements PlaceTokenizer<DashboardPlace> {

      @Override
      public DashboardPlace getPlace(final String token) {
         return new DashboardPlace();
      }

      @Override
      public String getToken(final DashboardPlace place) {
         return ""; //$NON-NLS-1$
      }

   }

}
