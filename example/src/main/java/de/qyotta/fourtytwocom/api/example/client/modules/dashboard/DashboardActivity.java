/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.example.client.modules.dashboard;

import de.qyotta.fourtytwocom.api.example.client.ApiExampleActivity;
import de.qyotta.fourtytwocom.api.example.client.event.InfoEvent;
import de.qyotta.fourtytwocom.api.example.client.modules.dashboard.DashboardView.Presenter;

public class DashboardActivity extends ApiExampleActivity<DashboardView, DashboardPlace> implements Presenter {

   @Override
   public void start() {
      view.setPresenter(this);
      view.updateResultingUri(view.getDomain() + "/api/rest/v3/"); //$NON-NLS-1$
   }

   @Override
   public void onSaveButton(final String domain, final String username, final String password) {
      services.setDomain(domain);
      services.setUsername(username);
      services.setPassword(password);
      eventBus.fireEvent(new InfoEvent("Updated account info"));
   }

   @Override
   public void onDomainChanged(final String value) {
      view.updateResultingUri(value + "/api/rest/v3/"); //$NON-NLS-1$
   }
}
