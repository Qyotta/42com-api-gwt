/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.events;

import java.util.LinkedList;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.web.bindery.event.shared.EventBus;

import de.qyotta.fourtytwocom.api.client.dtos.BasicResponse;
import de.qyotta.fourtytwocom.api.client.dtos.BasicResponse.Code;

public class ErrorAndNetworkPropagatingCallback<R extends BasicResponse<?>> implements MethodCallback<R> {
   private final EventBus eventBus;
   private final MethodCallback<R> delegate;
   private final boolean suppressErrors;
   private final boolean userInvoked;

   public ErrorAndNetworkPropagatingCallback(final EventBus eventBus, final MethodCallback<R> callback, final boolean suppressErrors, final boolean userInvoked) {
      this.eventBus = eventBus;
      this.delegate = callback;
      this.suppressErrors = suppressErrors;
      this.userInvoked = userInvoked;
   }

   @Override
   public void onFailure(final Method method, final Throwable exception) {
      handleNetworkStopped();
      final List<Code> errors = new LinkedList<Code>();
      propagateErrors(errors);
   }

   @Override
   public void onSuccess(final Method method, final R response) {
      handleNetworkStopped();
      if (response.error) {
         propagateErrors(response.codes);
      }
      delegate.onSuccess(method, response);
   }

   private void propagateErrors(final List<Code> errors) {
      if (!suppressErrors) {
         eventBus.fireEvent(new NetworkRequestFailedEvent(errors));
      }
   }

   private void handleNetworkStopped() {
      if (userInvoked) {
         eventBus.fireEvent(new NetworkUserRequestStoppedEvent());
      } else {
         eventBus.fireEvent(new NetworkRequestStoppedEvent());
      }
   }

}
