/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.events;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class NetworkRequestStoppedEvent extends Event<NetworkRequestStoppedEvent.Handler> {

   public interface Handler {
      void onEvent(NetworkRequestStoppedEvent event);
   }

   private static final Type<NetworkRequestStoppedEvent.Handler> TYPE = new Type<NetworkRequestStoppedEvent.Handler>();

   @Override
   public com.google.web.bindery.event.shared.Event.Type<Handler> getAssociatedType() {
      return TYPE;
   }

   @Override
   protected void dispatch(final Handler handler) {
      handler.onEvent(this);
   }

   public static Type<NetworkRequestStoppedEvent.Handler> getType() {
      return TYPE;
   }

   public static HandlerRegistration register(final EventBus eventBus, final String sourceName, final Handler handler) {
      return eventBus.addHandlerToSource(TYPE, sourceName, handler);
   }
}
