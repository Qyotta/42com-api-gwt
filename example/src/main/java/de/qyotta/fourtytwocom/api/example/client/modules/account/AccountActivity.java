/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.example.client.modules.account;

import de.qyotta.fourtytwocom.api.example.client.ApiExampleActivity;
import de.qyotta.fourtytwocom.api.example.client.modules.account.AccountView.Presenter;

public class AccountActivity extends ApiExampleActivity<AccountView, AccountPlace> implements Presenter {

   @Override
   public void start() {
      view.setPresenter(this);
   }

   @Override
   public void onSendButton() {

   }
}
