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

public class HTML5HistorianFactory {

   public static Html5Historian getHistorian() {
      if (hasHtml5HistorySupport()) {
         return new Html5HistorianImpl();
      }
      return new HTML5HistorianLegacyImpl();
   }

   private native static boolean hasHtml5HistorySupport()/*-{
      return typeof ($wnd.history.pushState) != "undefined";
   }-*/;
}
