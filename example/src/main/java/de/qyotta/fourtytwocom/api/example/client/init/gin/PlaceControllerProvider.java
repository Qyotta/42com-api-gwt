/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.example.client.init.gin;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;

public class PlaceControllerProvider implements Provider<PlaceController> {
   private final EventBus eventBus;

   @Inject
   public PlaceControllerProvider(final EventBus eventBus) {
      this.eventBus = eventBus;
   }

   @Override
   public PlaceController get() {
      return new PlaceController(eventBus);
   }
}
