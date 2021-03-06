/*******************************************************************************
 * Copyright (c) 2016, 2017 Iotracks, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Saeid Baghbidi
 * Kilton Hopkins
 *  Ashita Nagar
 *******************************************************************************/
package org.eclipse.iofog.local_api;

import java.util.Hashtable;

import io.netty.channel.ChannelHandlerContext;

/**
 * Real-time message and cotrol open websockets map.
 * Unacknowledged messages and control signals map.
 * @author ashita
 * @since 2016
 */
public class WebSocketMap {
	static Hashtable<String, ChannelHandlerContext> controlWebsocketMap;
	static Hashtable<String, ChannelHandlerContext> messageWebsocketMap;
	
	static Hashtable<ChannelHandlerContext, MessageSentInfo> unackMessageSendingMap;
	static Hashtable<ChannelHandlerContext, ControlSignalSentInfo> unackControlSignalsMap;

	private static WebSocketMap instance = null;

	private WebSocketMap(){

	}
	
	/**
	 * Singleton Real-time message and cotrol open websockets map.
	 * Singleton unacknowledged messages and control signals map.
	 * @param None
	 * @return WebSocketMap
	 */
	public static WebSocketMap getInstance(){
		if (instance == null) {
			synchronized (WebSocketMap.class) {
				if(instance == null){
					instance = new WebSocketMap();
					controlWebsocketMap = new Hashtable<String, ChannelHandlerContext>();
					messageWebsocketMap = new Hashtable<String, ChannelHandlerContext>();
					unackMessageSendingMap = new Hashtable<ChannelHandlerContext, MessageSentInfo>();
					unackControlSignalsMap = new Hashtable<ChannelHandlerContext, ControlSignalSentInfo>();
				}
			}
		}
		return instance;
	}
	
	public static void addWebsocket(char ws, String id, ChannelHandlerContext ctx) {
		synchronized (WebSocketMap.class) {
			switch (ws) {
				case 'C':
					controlWebsocketMap.put(id, ctx);
					break;
				case 'M':
					messageWebsocketMap.put(id, ctx);
			}
		}
	}
}