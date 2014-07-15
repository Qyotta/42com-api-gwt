/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2013 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.example.client.init;

import com.google.gwt.place.shared.Place;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

import de.qyotta.fourtytwocom.api.example.client.event.MenuEntrySelectedEvent;
import de.qyotta.fourtytwocom.api.example.client.init.history.HandlerRegistrationCollection;
import de.qyotta.fourtytwocom.api.example.client.init.history.HistoryHandler;
import de.qyotta.fourtytwocom.api.example.client.init.history.HistoryObserver;
import de.qyotta.fourtytwocom.api.example.client.modules.account.AccountPlace;
import de.qyotta.fourtytwocom.api.example.client.modules.dashboard.DashboardPlace;

@SuppressWarnings("nls")
public class AppHistoryObserver implements HistoryObserver {
   private HistoryHandler historyHandler;

   @Override
   public void onPlaceChange(final Place place, final HistoryHandler pHistoryHandler) {
      // nothing to do
   }

   @Override
   public void onHistoryChanged(final Place place, final HistoryHandler pHistoryHandler) {
      // nothing to do
   }

   @Override
   public void onAppStarted(final Place place, final HistoryHandler pHistoryHandler) {
      // nothing to do
   }

   @Override
   public HandlerRegistration bind(final EventBus eventBus, final HistoryHandler pHistoryHandler) {
      this.historyHandler = pHistoryHandler;
      final HandlerRegistrationCollection col = new HandlerRegistrationCollection();
      col.addHandlerRegistration(eventBus.addHandler(MenuEntrySelectedEvent.getType(), new MenuEntrySelectedEvent.Handler() {
         @Override
         public void onEvent(final MenuEntrySelectedEvent event) {
            switch (event.getName()) {
               case DASHBOARD:
                  goTo(new DashboardPlace());
                  break;
               case ACCOUNT:
                  goTo(new AccountPlace());
                  break;

               default:
                  throw new IllegalArgumentException("Missing switch for event.getName()::" + event.getName().name());
            }
         }
      }));

      return col;
   }

   private void goTo(final Place p) {
      historyHandler.goTo(p);
   }
}
