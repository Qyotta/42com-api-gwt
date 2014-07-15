/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.fourtytwocom.api.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import com.google.gwt.http.client.Request;

import de.qyotta.fourtytwocom.api.client.dtos.AccountResponse;

@Options(timeout = 60000)
public interface Api extends RestService {
   @GET
   @Path("account")
   Request getdealer(MethodCallback<AccountResponse> callback);
}
