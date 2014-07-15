/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.events;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class NetworkUserRequestStartedEvent extends Event<NetworkUserRequestStartedEvent.Handler> {

   public interface Handler {
      void onEvent(NetworkUserRequestStartedEvent event);
   }

   private static final Type<NetworkUserRequestStartedEvent.Handler> TYPE = new Type<NetworkUserRequestStartedEvent.Handler>();

   @Override
   public com.google.web.bindery.event.shared.Event.Type<Handler> getAssociatedType() {
      return TYPE;
   }

   @Override
   protected void dispatch(final Handler handler) {
      handler.onEvent(this);
   }

   public static Type<NetworkUserRequestStartedEvent.Handler> getType() {
      return TYPE;
   }

   public static HandlerRegistration register(final EventBus eventBus, final String sourceName, final Handler handler) {
      return eventBus.addHandlerToSource(TYPE, sourceName, handler);
   }
}
