/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.example.client.event;

import com.google.web.bindery.event.shared.Event;

public class MenuEntrySelectedEvent extends Event<MenuEntrySelectedEvent.Handler> {
   public enum MenuEntryName {
      DASHBOARD, ACCOUNT
   }

   public interface Handler {
      void onEvent(MenuEntrySelectedEvent event);
   }

   private static final Type<MenuEntrySelectedEvent.Handler> TYPE = new Type<MenuEntrySelectedEvent.Handler>();
   private final MenuEntryName name;

   @Override
   public com.google.web.bindery.event.shared.Event.Type<Handler> getAssociatedType() {
      return TYPE;
   }

   public MenuEntrySelectedEvent(final MenuEntryName name) {
      this.name = name;
   }

   @Override
   protected void dispatch(final Handler handler) {
      handler.onEvent(this);

   }

   public static Type<MenuEntrySelectedEvent.Handler> getType() {
      return TYPE;
   }

   public MenuEntryName getName() {
      return name;
   }

}
