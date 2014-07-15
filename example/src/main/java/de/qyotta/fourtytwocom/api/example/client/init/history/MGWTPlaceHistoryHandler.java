/*
 * Copyright 2011 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.qyotta.fourtytwocom.api.example.client.init.history;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class MGWTPlaceHistoryHandler {

   protected class DefaultHistoryHandler implements HistoryHandler {

      @Override
      public void replaceCurrentPlace(final Place place) {
         final String tokenForPlace = tokenForPlace(place);
         replaceToken(tokenForPlace);

      }

      @Override
      public void pushPlace(final Place place) {
         final String tokenForPlace = tokenForPlace(place);
         pushToken(tokenForPlace);

      }

      @Override
      public void goTo(final Place place) {
         placeController.goTo(place);

      }

      @Override
      public void goTo(final Place place, final boolean pIgnore) {
         MGWTPlaceHistoryHandler.this.ignore = pIgnore;
         placeController.goTo(place);

      }

   }

   protected final static Logger log = Logger.getLogger(MGWTPlaceHistoryHandler.class.getName());

   private EventBus eventBus;

   private final PlaceHistoryMapper placeHistoryMapper;

   private static final Historian GWT_historian = (Historian) GWT.create(DefaultHistorian.class);

   private static final Html5Historian historian = HTML5HistorianFactory.getHistorian();

   private PlaceController placeController;

   private boolean ignore;

   private final HistoryObserver historyObserver;

   private final DefaultHistoryHandler defaultHistoryHandler;

   public MGWTPlaceHistoryHandler(final PlaceHistoryMapper placeHistoryMapper, final HistoryObserver historyObserver) {

      this.placeHistoryMapper = placeHistoryMapper;
      this.historyObserver = historyObserver;
      defaultHistoryHandler = new DefaultHistoryHandler();

   }

   public HandlerRegistration register(final PlaceController pPlaceController, final EventBus pEventBus, final Place pDefaultPlace) {
      this.placeController = pPlaceController;
      this.eventBus = pEventBus;
      this.defaultPlace = pDefaultPlace;

      final HandlerRegistration bind = bind();

      final HandlerRegistration handlerRegistration = historyObserver.bind(eventBus, defaultHistoryHandler);

      return new HandlerRegistration() {
         @Override
         public void removeHandler() {

            bind.removeHandler();
            handlerRegistration.removeHandler();
         }
      };
   }

   protected HandlerRegistration bind() {

      final HandlerRegistration popHandler = historian.addPopStateHandler(new PopStateHandler() {

         @Override
         public void onPopStateEvent(final PopStateEvent event) {
            onPopStateEventOccured(event.getData());
         }
      });

      final HandlerRegistration placeChangeHandler = eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {

         @Override
         public void onPlaceChange(final PlaceChangeEvent event) {
            onPlaceChangeEvent(event);

         }
      });

      return new HandlerRegistration() {
         @Override
         public void removeHandler() {
            MGWTPlaceHistoryHandler.this.defaultPlace = Place.NOWHERE;
            MGWTPlaceHistoryHandler.this.placeController = null;
            popHandler.removeHandler();
            placeChangeHandler.removeHandler();
         }
      };

   }

   protected void onPlaceChangeEvent(final PlaceChangeEvent event) {

      if (ignore) {
         ignore = false;
         return;
      }

      final Place newPlace = event.getNewPlace();

      historyObserver.onPlaceChange(newPlace, defaultHistoryHandler);

      pushToken(tokenForPlace(newPlace));
   }

   protected void onPopStateEventOccured(final String token) {

      final Place place = getPlaceForToken(token);

      historyObserver.onHistoryChanged(place, defaultHistoryHandler);
      // TODO maybe handle differently?
      ignore = true;
      placeController.goTo(place);
   }

   protected void replaceToken(final String token) {
      if (token.length() > 0) {
         historian.replaceState(token, Window.getTitle(), "#" + token); //$NON-NLS-1$
      } else {
         historian.replaceState(token, Window.getTitle(), ""); //$NON-NLS-1$
      }
   }

   protected void pushToken(final String token) {
      historian.pushState(token, Window.getTitle(), "#" + token); //$NON-NLS-1$
   }

   public void handleCurrentHistory() {
      final Place place = getPlaceForToken(GWT_historian.getToken());

      historyObserver.onAppStarted(place, defaultHistoryHandler);
      if (defaultPlace.equals(place)) {
         ignore = true;
      }

      placeController.goTo(place);
   }

   private Place defaultPlace = Place.NOWHERE;

   protected Place getPlaceForToken(final String token) {

      Place newPlace = null;

      if ("".equals(token)) { //$NON-NLS-1$
         newPlace = defaultPlace;
      }

      if (newPlace == null) {
         newPlace = placeHistoryMapper.getPlace(token);
      }

      if (newPlace == null) {
         log.warning("Unrecognized history token: " + token); //$NON-NLS-1$
         newPlace = defaultPlace;
      }
      return newPlace;

   }

   private String tokenForPlace(final Place newPlace) {

      if (defaultPlace.equals(newPlace)) {
         return ""; //$NON-NLS-1$
      }

      final String token = placeHistoryMapper.getToken(newPlace);
      if (token != null) {
         return token;
      }

      log.warning("Place not mapped to a token: " + newPlace); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
   }
}
