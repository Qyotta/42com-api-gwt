/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.example.client.init;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

import de.qyotta.fourtytwocom.api.example.client.ApiExampleActivity;
import de.qyotta.fourtytwocom.api.example.client.modules.account.AccountActivity;
import de.qyotta.fourtytwocom.api.example.client.modules.account.AccountPlace;
import de.qyotta.fourtytwocom.api.example.client.modules.dashboard.DashboardActivity;
import de.qyotta.fourtytwocom.api.example.client.modules.dashboard.DashboardPlace;
import de.qyotta.fourtytwocom.api.example.client.ui.View;

public class AppActivityMapper implements ActivityMapper {

   @Inject
   private Provider<DashboardActivity> dashboardActivityProvider;

   @Inject
   private Provider<AccountActivity> accountActivityProvider;

   @Override
   public Activity getActivity(final Place place) {
      Activity result = null;
      if (place instanceof DashboardPlace) {
         result = setPlace(dashboardActivityProvider.get(), (DashboardPlace) place);
      }

      if (place instanceof AccountPlace) {
         result = setPlace(accountActivityProvider.get(), (AccountPlace) place);
      }

      return result;
   }

   private <V extends View, T extends Place> ApiExampleActivity<V, T> setPlace(final ApiExampleActivity<V, T> result, final T place) {
      result.setPlace(place);
      return result;
   }
}
