package de.qyotta.fourtytwocom.api.example.client.init.gin;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import de.qyotta.fourtytwocom.api.example.client.init.history.HistoryObserver;

@GinModules({ ApiExampleGinModule.class })
public interface ApiExampleGinjector extends Ginjector {

   PlaceController getPlaceController();

   EventBus getEventBus();

   ActivityMapper getActivityMapper();

   HistoryObserver getAppHistoryObserver();

}
