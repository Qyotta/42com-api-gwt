/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client.auth;

import com.google.gwt.core.client.JavaScriptObject;

public class CryptoJS extends JavaScriptObject {
   protected CryptoJS() {
   }

   public static native String base64_enc(final String str) /*-{
      var words = $wnd.CryptoJS.enc.Utf8.parse(str);
      return $wnd.CryptoJS.enc.Base64.stringify(words);
   }-*/;

   public static native String hmacSHA1_base64(final String message, final String passphrase) /*-{
      var hash = $wnd.CryptoJS.HmacSHA1(message, passphrase);
      hash = $wnd.CryptoJS.enc.Utf8.parse(hash.toString());
      return hash.toString($wnd.CryptoJS.enc.Base64);
   }-*/;

   public static native String hmacSHA256_base64(final String message, final String passphrase) /*-{
      var hash = $wnd.CryptoJS.HmacSHA256(message, passphrase);
      hash = $wnd.CryptoJS.enc.Utf8.parse(hash.toString());
      return hash.toString($wnd.CryptoJS.enc.Base64);
   }-*/;

   public static native String sha256(final String message) /*-{
      var hash = $wnd.CryptoJS.SHA256(message);
      return hash.toString();
   }-*/;

   public static native String MD5(final String message) /*-{
      var hash = $wnd.CryptoJS.MD5(message);
      return hash.toString();
   }-*/;
}
