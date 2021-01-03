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

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.airconwithme.internal.client.gson.JSONData;
import org.openhab.binding.airconwithme.internal.client.gson.capabilities.Capabilities;

/**
 * The {@link ResponseCallback} interface is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author SkyRaVeR - Initial contribution
 */
@NonNullByDefault
public interface ResponseCallback {

    void responseCallbackLoggedIn(boolean isloggedin);

    void responseCallbackData(JSONData statusdata);

    void responseCallbackCapabilities(Capabilities capabilities);
}
