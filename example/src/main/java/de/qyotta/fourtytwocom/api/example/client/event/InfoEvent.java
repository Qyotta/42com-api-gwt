/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.example.client.event;

import com.google.web.bindery.event.shared.Event;

public class InfoEvent extends Event<InfoEvent.Handler> {

   public interface Handler {
      void onEvent(InfoEvent event);
   }

   private static final Type<InfoEvent.Handler> TYPE = new Type<InfoEvent.Handler>();
   private final String info;

   @Override
   public com.google.web.bindery.event.shared.Event.Type<Handler> getAssociatedType() {
      return TYPE;
   }

   public InfoEvent(final String info) {
      this.info = info;
   }

   public String getInfo() {
      return info;
   }

   @Override
   protected void dispatch(final Handler handler) {
      handler.onEvent(this);

   }

   public static Type<InfoEvent.Handler> getType() {
      return TYPE;
   }

}
