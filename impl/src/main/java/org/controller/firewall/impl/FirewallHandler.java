/*
 * Copyright © 2016 Copyright(c) Qiuny, Inc and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.controller.firewall.impl;

import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.FirewallListener;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.Sent;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class FirewallHandler implements FirewallListener {

    private static final Logger LOG = LoggerFactory.getLogger(FirewallHandler.class);

    @Override
    public void onSent(Sent notification) {
        LOG.info(notification.getMessage());
    }
}
