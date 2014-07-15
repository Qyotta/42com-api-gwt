/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client;

import de.qyotta.fourtytwocom.api.client.error.ApiException;


public abstract class Callback<T> {
   public void onStart() {
      //
   }

   public void onSuccess(final T result) {
      //
   }

   public void onCancel() {
      //
   }

   public void onProgress(final int i) {
      //
   }

   public void onFailure(final ApiException caught) {
      //
   }

}
