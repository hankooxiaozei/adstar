package com.yundian.star.networkapi;


import com.yundian.star.networkapi.socketapi.SocketAPIFactoryImpl;
import com.yundian.star.networkapi.socketapi.SocketReqeust.SocketAPINettyBootstrap;
import com.yundian.star.utils.ToastUtils;

public class NetworkAPIFactoryImpl {
    private static NetworkAPIFactory networkAPIFactory = null;


    static {
        networkAPIFactory = SocketAPIFactoryImpl.getInstance();
    }


    public static void initConfig(NetworkAPIConfig config) {
        networkAPIFactory.initConfig(config);
    }

    public static NetworkAPIConfig getConfig() {
        return networkAPIFactory.getConfig();
    }

    public static UserAPI getUserAPI() {
        return networkAPIFactory.getUserAPI();
    }


    public static DealAPI getDealAPI() {
        return networkAPIFactory.getDealAPI();
    }

    public static InformationAPI getInformationAPI() {
        return networkAPIFactory.getInformationAPI();
    }
}
