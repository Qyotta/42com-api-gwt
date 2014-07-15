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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class HTML5HistorianLegacyImpl implements Html5Historian, ValueChangeHandler<String> {

   public static class DefaultHistorian implements Historian {
      @Override
      public com.google.gwt.event.shared.HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> valueChangeHandler) {
         return History.addValueChangeHandler(valueChangeHandler);
      }

      @Override
      public String getToken() {
         return History.getToken();
      }

      @Override
      public void newItem(final String token, final boolean issueEvent) {
         History.newItem(token, issueEvent);
      }
   }

   private final DefaultHistorian defaultHistorian;
   private final EventBus eventBus = new SimpleEventBus();

   public HTML5HistorianLegacyImpl() {
      defaultHistorian = new DefaultHistorian();

      defaultHistorian.addValueChangeHandler(this);
   }

   @Override
   public void forward() {
      History.forward();

   }

   @Override
   public void back() {
      History.back();

   }

   @Override
   public void go(final int number) {
      if (number > 0) {
         History.forward();
      } else {
         History.back();
      }

   }

   @Override
   public int length() {
      return -1;
   }

   @Override
   public void pushState(final String data, final String title, final String url) {
      History.newItem(data, false);

   }

   @Override
   public void replaceState(final String data, final String title, final String url) {
      final UrlBuilder builder = Window.Location.createUrlBuilder();
      builder.setHash(data);
      Window.Location.replace(builder.buildString());

   }

   @Override
   public HandlerRegistration addPopStateHandler(final PopStateHandler handler) {
      return eventBus.addHandler(PopStateEvent.getType(), handler);
   }

   @Override
   public void onValueChange(final ValueChangeEvent<String> event) {
      eventBus.fireEvent(new PopStateEvent(event.getValue(), Window.getTitle(), Window.Location.getHref()));
   }

}
