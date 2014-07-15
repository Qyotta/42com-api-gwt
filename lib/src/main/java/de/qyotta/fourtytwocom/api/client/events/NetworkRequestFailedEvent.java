/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

import de.qyotta.fourtytwocom.api.client.dtos.BasicResponse.Code;

public class NetworkRequestFailedEvent extends Event<NetworkRequestFailedEvent.Handler> {

   public interface Handler {
      void onEvent(NetworkRequestFailedEvent event);
   }

   private static final Type<NetworkRequestFailedEvent.Handler> TYPE = new Type<NetworkRequestFailedEvent.Handler>();
   private final List<Code> errors;

   public NetworkRequestFailedEvent(final List<Code> errors) {
      this.errors = errors;
   }

   @Override
   public com.google.web.bindery.event.shared.Event.Type<Handler> getAssociatedType() {
      return TYPE;
   }

   @Override
   protected void dispatch(final Handler handler) {
      handler.onEvent(this);
   }

   public static Type<NetworkRequestFailedEvent.Handler> getType() {
      return TYPE;
   }

   public static HandlerRegistration register(final EventBus eventBus, final String sourceName, final Handler handler) {
      return eventBus.addHandlerToSource(TYPE, sourceName, handler);
   }

   public List<Code> getErrors() {
      return errors;
   }
}
