package de.qyotta.fourtytwocom.api.example.client.modules.account;

import de.qyotta.fourtytwocom.api.example.client.ui.View;

public interface AccountView extends View {
   public interface Presenter {
      Presenter NULL = new Presenter() {
         @Override
         public void onSendButton() {
            // nothing to do
         }
      };

      void onSendButton();
   }

   void setPresenter(Presenter presenter);
}
