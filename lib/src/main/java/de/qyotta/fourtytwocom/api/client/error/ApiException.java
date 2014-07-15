/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.error;

import java.util.LinkedList;
import java.util.List;

import de.qyotta.fourtytwocom.api.client.dtos.BasicResponse.Code;

public class ApiException extends Exception {
   private static final long serialVersionUID = 1795149428298238658L;

   private final List<Code> codes;

   public ApiException(final Code... codes) {
      this.codes = new LinkedList<Code>();
      for (final Code error : codes) {
         this.codes.add(error);
      }
   }

   public ApiException(final List<Code> codes) {
      this.codes = codes;
   }

   public List<Code> getCodes() {
      return codes;
   }

}
