package com.dianping.puma.pumaserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.HttpObject;

import java.util.List;

/**
 * Dozer @ 6/24/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class HttpRouterHandler extends MessageToMessageDecoder<HttpObject> {
    @Override
    protected void decode(ChannelHandlerContext ctx, HttpObject msg, List<Object> out) throws Exception {
        System.out.println("xxx");
        //todo:
        //转换成特定的对象
    }
}