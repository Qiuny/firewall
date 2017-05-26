/*
 * Copyright © 2016 Copyright(c) Qiuny, Inc and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.controller.firewall.impl;

import io.netty.handler.codec.http.HttpUtil;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ProviderContext;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.RpcRegistration;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.controller.sal.binding.api.NotificationProviderService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.firewall.rev150105.FirewallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

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

    }

    public String buildInput_transmit_packet(){
        byte[] rawPacket=getRawByte(xxx);
        String nodeid="openflow:11";
        String egress="openflow:11:2";
        Map<String,Object> input = new TreeMap<String,Object>();
        input.put("connection-cookie", 123456);

        String strNode = "/opendaylight-inventory:nodes/opendaylight-inventory:node[opendaylight-inventory:id='"+nodeid+"']";
        input.put("node",strNode);
        input.put("egress",strNode + "/opendaylight-inventory:node-connector[opendaylight-inventory:id='"+ egress+"']");

        //payload
        input.put("payload", Base64Util.encode(rawPacket));

        Map<String,Object> packet = new TreeMap<String,Object>();
        packet.put("input", (Object)input);
        return Json.serialize(packet);
    }

    public void doTransmitPacket() throws Exception{
        String strUrl = "http://<IP>:8181/restconf/operations/packet-processing:transmit-packet";

        HttpUtil http = new HttpUtil(strUrl);
        http.addBasicAuth("admin","admin");
        http.addHeaderField("Accept","application/json");
        http.addHeaderField("Content-type","application/json");

        String strEntity = buildInput_transmit_packet();

        System.out.println(strEntity);

        http.addEntity(strEntity);
        http.sendRequest(HttpUtil.Method.POST);
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