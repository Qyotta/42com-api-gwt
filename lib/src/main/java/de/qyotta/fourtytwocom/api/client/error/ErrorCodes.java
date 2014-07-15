package de.qyotta.fourtytwocom.api.client.error;

@SuppressWarnings("nls")
// @formatter:off
public enum ErrorCodes {
   E_401(401,"Authentication credentials were missing or incorrect."),
   E_403(403,"The request is understood, but it has been refused or access is not allowed."),
   E_404(404,"The URI requested is invalid or the resource requested, such as a user, does not exists. Also returned when the requested format is not supported by the requested method."),
   E_504(504,"The Platform servers are up, but the request couldn't be serviced due to some failure within our stack. Try again later."),
   E_1001(1001,"You try to get a specific result which is not known. Either it was removed or it has never been set."),
   E_1011(1011,"Your given date is timed out. It should be older then three minutes."),
   E_1012(1012,"Your given date is located in the future."),
   E_1200(1200,"Your given Personal Identification Number to charge your account is not valid anymore."),
   E_1201(1201,"Your account is not connected to a specific callingcard."),
   
   // timeout, unknown
   E_99998(99998, "Connection timed out"), 
   E_99999(99999, "Internal error.");

   // @formatter:on

   private final int code;
   private final String description;

   private ErrorCodes(final int code, final String description) {
      this.code = code;
      this.description = description;
   }

   public int code() {
      return code;
   }

   public String getDescription() {
      return description;
   }

}