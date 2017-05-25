/*
 * Copyright © 2016 Copyright(c) Qiuny, Inc and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.controller.firewall.impl;

import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.FirewallService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.ReceiveInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.ReceiveOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.ReceiveOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.RequestOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.RequestInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.RequestOutputBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class FirewallImpl implements FirewallService {

    private static final Logger LOG = LoggerFactory.getLogger(FirewallImpl.class);

    @Override
    public Future<RpcResult<ReceiveOutput>> receive(ReceiveInput input) {
        LOG.info("Receive:", input);
        ReceiveOutputBuilder receiveBuilder = new ReceiveOutputBuilder();
        receiveBuilder.setResult("Hello" + input.getName());
        return RpcResultBuilder.success(receiveBuilder.build()).buildFuture();
    }

    @Override
    public Future<RpcResult<RequestOutput>> request(RequestInput input) {
        LOG.info("request:", input);
        RequestOutputBuilder requestBuilder = new RequestOutputBuilder();
        requestBuilder.setResponse(Long.valueOf("response:" + input.getContent() + ":" + input.getId()));
        return RpcResultBuilder.success(requestBuilder.build()).buildFuture();
    }
}
