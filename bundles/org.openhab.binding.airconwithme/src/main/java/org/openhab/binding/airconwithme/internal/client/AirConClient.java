/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.airconwithme.internal.client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.openhab.binding.airconwithme.internal.client.gson.Error;
import org.openhab.binding.airconwithme.internal.client.gson.JSONData;
import org.openhab.binding.airconwithme.internal.client.gson.capabilities.Capabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * The {@link AirConClient} is responsible for all communication with the rest api
 * handlers.
 *
 * @author SkyRaVeR - initial contribution
 */
public class AirConClient {

    private final Logger logger = LoggerFactory.getLogger(AirConClient.class);

    private HttpClient httpClient;
    private String sessionid;
    private String hostname;
    private boolean loginstatus = false;

    private String json_req_login = "{\"command\":\"login\",\"data\":{\"username\":\"operator\",\"password\":\"operator\"}}";
    private String json_req_getcapabilities = "{\"command\":\"getavailabledatapoints\",\"data\":{\"sessionID\":\"%s\"}}";
    private String json_req_status = "{\"command\":\"getdatapointvalue\",\"data\":{\"sessionID\":\"%s\",\"uid\":%s}}";
    private String json_req_set = "{\"command\":\"setdatapointvalue\",\"data\":{\"sessionID\":\"%s\",\"uid\":%d,\"value\":%s}}";

    private Gson gson;

    private ResponseCallback mycallb;

    public AirConClient(HttpClient client) {
        logger.debug("Aircon client started");
        httpClient = client;
        setHostname("");
        sessionid = "";
        gson = new Gson();
    }

    /***
     * Register the callback function
     *
     * @param callback callback to register
     */
    public void registerCallback(ResponseCallback callback) {
        logger.info("AirConClient callback registered!");
        mycallb = callback;
    }

    public void unregisterCallback() {
        logger.info("AirConClient callback un-registered!");
        mycallb = null;
    }

    /***
     * Returns the hostname / ip
     *
     * @return Hostname / ip as string
     */
    public String getHostname() {
        return hostname;
    }

    /***
     * Sets the Hostname
     *
     * @param hostname as string
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /***
     * Returns the current login status
     *
     * @return true is client is logged in; false otherwise
     */
    public boolean isLoginstatus() {
        return loginstatus;
    }

    /***
     * Sets the login status
     *
     * @param loginstatus true is logged in; false otherwise
     */
    public void setLoginstatus(boolean loginstatus) {
        this.loginstatus = loginstatus;

        if (false == loginstatus) {
            sessionid = "";
        }

        if (null != mycallb) {
            mycallb.responseCallbackLoggedIn(this.loginstatus);
        }
    }

    private String createEndpoint() {
        return String.format("http://%s/api.cgi", getHostname());
    }

    public void retrieveCapabilities() {
        //
        String response = "";
        ContentResponse res = null;

        Capabilities jsonresponse = null;

        try {
            res = httpClient.GET(String.format("http://%s/js/data/data.json", getHostname()));

            response = res.getContentAsString();
            response = response.replaceAll("\\{\\}", "\"\" ");
            // logger.debug(response);
            jsonresponse = gson.fromJson(response, Capabilities.class);

            if (null == jsonresponse) {
                jsonresponse = new Capabilities();
            }

            if (null != mycallb) {
                mycallb.responseCallbackCapabilities(jsonresponse);
            }
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
        }
    }

    /***
     * tries to send a rest request to the ac unit
     *
     * @param requesturi enpoint to request
     * @param payload json payload
     * @return JSONData containing all informations
     */
    private JSONData performRequest(String requesturi, String payload) {

        String response = "";
        ContentResponse res = null;
        Request req = null;
        JSONData jsonresponse = null;

        logger.debug("Request: {}", requesturi);

        req = httpClient.POST(requesturi);
        req.method(HttpMethod.POST);
        req.header(HttpHeader.CONTENT_TYPE, "application/json");
        req.content(new StringContentProvider(payload, "utf-8"));

        try {
            res = req.send();
            response = res.getContentAsString();

            // create an "array" from a single reponse in order to get gson working..
            if (response.contains("\"dpval\":{")) {
                response = response.replace("\"dpval\":{", "\"dpval\":[{");
                response = response.replace("}}}", "}]}}");
            }
            logger.debug("response\n{}", response);
            jsonresponse = gson.fromJson(response, JSONData.class);
        } catch (Exception e) {
            logger.debug("{}", response);
            logger.error("{}", e.getMessage());
            if (null == jsonresponse) {
                jsonresponse = new JSONData();
                jsonresponse.setSuccess(false);
                jsonresponse.setError(new Error("unknown error occured"));
            }
        }

        return jsonresponse;
    }

    /***
     * tries to login to the AC-Device and and sets the status accordingly
     */
    public void Login() {

        JSONData loginresponse = null;
        loginresponse = performRequest(createEndpoint(), json_req_login);

        if (true == loginresponse.getSuccess()) {
            sessionid = loginresponse.getData().getId().getSessionID();
        }

        setLoginstatus(loginresponse.getSuccess());
    }

    public void getCapabilities() {
        JSONData statusdata = null;

        if (false == isLoginstatus()) {
            return;
        }

        statusdata = performRequest(createEndpoint(), String.format(json_req_getcapabilities, sessionid));

        if (true == statusdata.getSuccess()) {
            if (null != mycallb) {
                mycallb.responseCallbackData(statusdata);
            }
        } else {
            setLoginstatus(false);
        }
    }

    public void getData(String uid) {

        JSONData statusdata = null;

        if (false == isLoginstatus()) {
            return;
        }

        String uid2 = uid;
        if (uid2.equals("all")) {
            uid2 = String.format("\"%s\"", uid);
        }
        statusdata = performRequest(createEndpoint(), String.format(json_req_status, sessionid, uid2));

        if (true == statusdata.getSuccess()) {
            if (null != mycallb) {
                mycallb.responseCallbackData(statusdata);
            }
        } else {
            setLoginstatus(false);
        }
    }

    public void setData(int uid, String value) {
        if (true == isLoginstatus()) {
            {
                try {
                    performRequest(createEndpoint(), String.format(json_req_set, sessionid, uid, value));
                } catch (Exception e) {
                    logger.error("{}", e.getMessage());
                }
            }
        }
    }
}
