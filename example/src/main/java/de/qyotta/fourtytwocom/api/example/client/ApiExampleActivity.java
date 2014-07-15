package de.qyotta.fourtytwocom.api.example.client;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import de.qyotta.fourtytwocom.api.client.Services;
import de.qyotta.fourtytwocom.api.example.client.ui.View;

public abstract class ApiExampleActivity<V extends View, T extends Place> extends AbstractActivity {

   protected V view;
   protected Services services;
   protected EventBus eventBus;
   private T place;

   @Inject
   public void setView(final V view) {
      this.view = view;
   }

   @Inject
   public void setServices(final Services services) {
      this.services = services;
   }

   @Inject
   public void setEventBus(final EventBus eventBus) {
      this.eventBus = eventBus;
   }

   @Override
   public final void start(final AcceptsOneWidget panel, final com.google.gwt.event.shared.EventBus pEventBus) {
      panel.setWidget(view);
      start();
   }

   public abstract void start();

   public void setPlace(final T place) {
      this.place = place;
   }

   public T getPlace() {
      return place;
   }

}
