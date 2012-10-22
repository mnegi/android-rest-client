﻿package com.dg.libs.rest.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.protocol.HTTP;

import com.dg.android.logger.Logger;
import com.dg.libs.rest.exceptions.HttpException;

/**
 * @author darko grozdanovski
 */
public class ParametersRestClient extends BaseRestClient {

    private static final String TAG = ParametersRestClient.class.getSimpleName();

    private RequestMethod method;

    public ParametersRestClient() {
        super();
    }

    public void setMethod(final RequestMethod method) {
        this.method = method;
    }

    @Override
    public void execute() throws HttpException {
        try {
            switch (method) {
                case GET: {
                    final HttpGet request = new HttpGet(getUrl() + generateParametersString(getParams()));
                    executeRequest(request);
                    break;
                }
                case POST: {
                    final HttpPost request = new HttpPost(getUrl());
                    if (!getParams().isEmpty()) {
                        request.setEntity(new UrlEncodedFormEntity(getParams(), HTTP.UTF_8));
                    }
                    executeRequest(request);
                    break;
                }
                case PUT: {
                    final HttpPut request = new HttpPut(getUrl());
                    if (!getParams().isEmpty()) {
                        request.setEntity(new UrlEncodedFormEntity(getParams(), HTTP.UTF_8));
                    }
                    executeRequest(request);
                    break;
                }
                case DELETE: {
                    final HttpDelete request = new HttpDelete(getUrl() + generateParametersString(getParams()));
                    executeRequest(request);
                    break;
                }
            }
        } catch (final UnsupportedEncodingException e) {
            Logger.w(TAG, "", e);
            throw new HttpException(e);
        } catch (final IOException e) {
            Logger.w(TAG, "", e);
            throw new HttpException(e);
        }
    }

}
