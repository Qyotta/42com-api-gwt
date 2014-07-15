package de.qyotta.fourtytwocom.api.example.client.init.gin;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import de.qyotta.fourtytwocom.api.client.Services;
import de.qyotta.fourtytwocom.api.example.client.init.AppActivityMapper;
import de.qyotta.fourtytwocom.api.example.client.init.AppHistoryObserver;
import de.qyotta.fourtytwocom.api.example.client.init.history.HistoryObserver;
import de.qyotta.fourtytwocom.api.example.client.modules.account.AccountView;
import de.qyotta.fourtytwocom.api.example.client.modules.account.AccountViewImpl;
import de.qyotta.fourtytwocom.api.example.client.modules.dashboard.DashboardView;
import de.qyotta.fourtytwocom.api.example.client.modules.dashboard.DashboardViewImpl;

public class ApiExampleGinModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
      bind(PlaceController.class).toProvider(PlaceControllerProvider.class).in(Singleton.class);
      bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);
      bind(HistoryObserver.class).to(AppHistoryObserver.class).in(Singleton.class);
      bind(Services.class).in(Singleton.class);

      // views
      bind(DashboardView.class).to(DashboardViewImpl.class).in(Singleton.class);
      bind(AccountView.class).to(AccountViewImpl.class).in(Singleton.class);
   }

}
