package de.qyotta.fourtytwocom.api.example.client.modules.dashboard;

import de.qyotta.fourtytwocom.api.example.client.ui.View;

public interface DashboardView extends View {
   public interface Presenter {
      public Presenter NULL = new Presenter() {

         @Override
         public void onSaveButton(final String domain, final String username, final String password) {
            // nothing to do
         }

         @Override
         public void onDomainChanged(final String value) {
            // nothing to do
         }
      };

      void onSaveButton(String domain, String usnermae, String password);

      void onDomainChanged(String value);
   }

   void setPresenter(Presenter presenter);

   void updateResultingUri(String uri);

   String getDomain();
}
