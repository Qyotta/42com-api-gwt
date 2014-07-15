/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2013 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.utils;

import org.fusesource.restygwt.client.DirectRestService;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.callback.CallbackAware;

import com.google.web.bindery.event.shared.EventBus;

import de.qyotta.fourtytwocom.api.client.dtos.BasicResponse;
import de.qyotta.fourtytwocom.api.client.events.ErrorAndNetworkPropagatingCallback;
import de.qyotta.fourtytwocom.api.client.events.NetworkRequestStartedEvent;
import de.qyotta.fourtytwocom.api.client.events.NetworkUserRequestStartedEvent;

public final class RESTCall<R extends BasicResponse<?>> {
   private static EventBus eventBus;
   private final MethodCallback<R> callback;
   private boolean suppressErrors;
   private boolean userInvoked;

   private RESTCall(final MethodCallback<R> callback) {
      suppressErrors = false;
      userInvoked = true;
      this.callback = callback;
   }

   public static <R extends BasicResponse<?>> RESTCall<R> withCallback(final MethodCallback<R> callback) {
      return new RESTCall<R>(callback);
   }

   public <T extends DirectRestService> T call(final T service) {
      if (eventBus == null) {
         throw new IllegalStateException("No eventBus set. Did you call RESTCall.setEventBus(...)?"); //$NON-NLS-1$
      }

      if (userInvoked) {
         eventBus.fireEvent(new NetworkUserRequestStartedEvent());
      } else {
         eventBus.fireEvent(new NetworkRequestStartedEvent());
      }

      ((CallbackAware) service).setCallback(new ErrorAndNetworkPropagatingCallback<R>(eventBus, callback, suppressErrors, userInvoked));

      return service;
   }

   public RESTCall<R> suppressErrors() {
      this.suppressErrors = true;
      return this;
   }

   public RESTCall<R> notUserInvoked() {
      this.userInvoked = false;
      return this;
   }

   public static void setEventBus(final EventBus eventBus) {
      RESTCall.eventBus = eventBus;
   }

}
