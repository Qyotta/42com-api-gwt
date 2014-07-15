/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.dtos;

import java.util.LinkedList;
import java.util.List;

//CHECKSTYLE:OFF
public class AccountResponse extends BasicResponse<AccountResponse> {

   public static class AccountsDto {
      private final List<AccountDto> accounts = new LinkedList<AccountDto>();

      public List<AccountDto> getAccounts() {
         return accounts;
      }
   }

   public static class AccountDto {
      private String customername;
      private String customernumber;
      private String accountnumber;
      private String amount;
      private String accountname;

      public String getCustomername() {
         return customername;
      }

      public String getCustomernumber() {
         return customernumber;
      }

      public String getAccountnumber() {
         return accountnumber;
      }

      public String getAmount() {
         return amount;
      }

      public String getAccountname() {
         return accountname;
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + (accountnumber == null ? 0 : accountnumber.hashCode());
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
         final AccountDto other = (AccountDto) obj;
         if (accountnumber == null) {
            if (other.accountnumber != null) {
               return false;
            }
         } else if (!accountnumber.equals(other.accountnumber)) {
            return false;
         }
         return true;
      }

   }

}
