/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.dtos;

import java.util.LinkedList;
import java.util.List;

import de.qyotta.fourtytwocom.api.client.error.ErrorCodes;

// CHECKSTYLE:OFF
public abstract class BasicResponse<T> {
   public boolean error;
   public List<Code> codes = new LinkedList<Code>();
   public List<T> body;

   public static class Code {
      public int code;
      public String description;

      public Code() {

      }

      public Code(final int code) {
         this.code = code;
      }

      public Code(final ErrorCodes codes) {
         this.code = codes.code();
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + code;
         return result;
      }

      @Override
      public boolean equals(final Object obj) {
         if (this == obj) {
            return true;
         }
         if (obj == null) {
            return false;
         }
         if (getClass() != obj.getClass()) {
            return false;
         }
         final Code other = (Code) obj;
         if (code != other.code) {
            return false;
         }
         return true;
      }

   }
}
