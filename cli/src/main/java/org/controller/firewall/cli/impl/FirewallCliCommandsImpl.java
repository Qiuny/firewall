/*
 * Copyright © 2016 Copyright(c) Qiuny, Inc and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.controller.firewall.cli.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.controller.firewall.cli.api.FirewallCliCommands;

public class FirewallCliCommandsImpl implements FirewallCliCommands {

    private static final Logger LOG = LoggerFactory.getLogger(FirewallCliCommandsImpl.class);
    private final DataBroker dataBroker;

    public FirewallCliCommandsImpl(final DataBroker db) {
        this.dataBroker = db;
        LOG.info("FirewallCliCommandImpl initialized");
    }

    @Override
    public Object testCommand(Object testArgument) {
        return "This is a test implementation of test-command";
    }
}