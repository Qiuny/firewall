/*
 * Copyright © 2016 Copyright(c) Qiuny, Inc and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.controller.firewall.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ProviderContext;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.RpcRegistration;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.controller.sal.binding.api.NotificationProviderService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.FirewallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirewallProvider implements BindingAwareProvider, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(FirewallProvider.class);

//    private final DataBroker dataBroker;
//
//    public FirewallProvider(final DataBroker dataBroker) {
//        this.dataBroker = dataBroker;
//    }

    private RpcRegistration<FirewallService> firewallService;
    private DataBroker dataBroker;
    private NotificationProviderService notificationProviderService;

//    public void init() {
//        LOG.info("Firewall Session Initiated");
//        if(notificationService != null) {
//            LOG.info("NotificationService is: " + notificationService.toString());
//            FirewallHandler firewallHandler = new FirewallHandler();
//            notificationService.registerNotificationListener(firewallHandler);
//        }
//    }

    @Override
    public void onSessionInitiated(ProviderContext session) {
        LOG.info("FirewallProvider Session Initiated");
        firewallService = session.addRpcImplementation(FirewallService.class, new FirewallImpl());
        notificationProviderService = session.getSALService(NotificationProviderService.class);
//        notificationProviderService.publish();
    }

    /**
     * Method called when the blueprint container is created.
     */
//    public void init() {
//        LOG.info("FirewallProvider Session Initiated");
//    }

    /**
     * Method called when the blueprint container is destroyed.
     */
    @Override
    public void close() {
        LOG.info("FirewallProvider Closed");
        if(firewallService != null) {
            firewallService.close();
        }
    }
}