package com.yundian.star.networkapi.socketapi;


import com.yundian.star.app.SocketAPIConstant;
import com.yundian.star.base.SearchReturnbeen;
import com.yundian.star.been.AdvBeen;
import com.yundian.star.been.AskToBuyReturnBeen;
import com.yundian.star.been.CommentMarketBeen;
import com.yundian.star.been.FansEntrustReturnBean;
import com.yundian.star.been.FansHotBuyReturnBeen;
import com.yundian.star.been.MarketTypeBeen;
import com.yundian.star.been.OptionsStarListBeen;
import com.yundian.star.been.ResultBeen;
import com.yundian.star.been.SrealSendBeen;
import com.yundian.star.been.SrealSendReturnBeen;
import com.yundian.star.been.StarBuyActReferralInfo;
import com.yundian.star.been.StarExperienceBeen;
import com.yundian.star.been.StarListbeen;
import com.yundian.star.been.StarMailListBeen;
import com.yundian.star.been.StarStarAchBeen;
import com.yundian.star.been.SureOrder;
import com.yundian.star.been.TimeLineBeen;
import com.yundian.star.been.TodayDealReturnBean;
import com.yundian.star.been.TodayEntrustReturnBean;
import com.yundian.star.been.TradingStatusBeen;
import com.yundian.star.listener.OnAPIListener;
import com.yundian.star.networkapi.InformationAPI;
import com.yundian.star.networkapi.socketapi.SocketReqeust.SocketDataPacket;
import com.yundian.star.ui.main.model.NewsInforModel;
import com.yundian.star.utils.SharePrefUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by ysl.
 */

public class SocketInformationAPI extends SocketBaseAPI implements InformationAPI {

    @Override
    public void newsinfo(String name, String code, int startnum, int endnum, int all, OnAPIListener<NewsInforModel> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("code", code);
        map.put("startnum", startnum);
        map.put("endnum", endnum);
        map.put("all", all);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.NewInfo,
                SocketAPIConstant.ReqeutType.NewInfos, map);
        requestEntity(socketDataPacket, NewsInforModel.class, listener);
    }

    @Override
    public void advInfo(String code, int all, OnAPIListener<AdvBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("all", all);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Banner,
                SocketAPIConstant.ReqeutType.NewInfos, map);
        requestEntity(socketDataPacket, AdvBeen.class, listener);
    }

    @Override
    public void searchStar(long id, String token, String message, OnAPIListener<SearchReturnbeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        map.put("message", message);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.SearchStar,
                SocketAPIConstant.ReqeutType.Search, map);
        requestEntity(socketDataPacket, SearchReturnbeen.class, listener);
    }

    @Override
    public void getOptionsStarList(String phone, int startnum, int endnum, int sorttype, OnAPIListener<OptionsStarListBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("startnum", startnum);
        map.put("endnum", endnum);
        map.put("sorttype", sorttype);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.OptionStarList,
                SocketAPIConstant.ReqeutType.SearchStar, map);
        requestEntity(socketDataPacket, OptionsStarListBeen.class, listener);
    }

    @Override
    public void getMarketKype(String phone, OnAPIListener<MarketTypeBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.MarketType,
                SocketAPIConstant.ReqeutType.SearchStar, map);
        requestEntity(socketDataPacket, MarketTypeBeen.class, listener);
    }

    @Override
    public void getStarList(long id, String token, int sort, int aType, int start, int count, OnAPIListener<StarListbeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        map.put("sort", sort);
        map.put("aType", aType);
        map.put("start", start);
        map.put("count", count);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.StarList,
                SocketAPIConstant.ReqeutType.Time, map);
        requestEntity(socketDataPacket, StarListbeen.class, listener);
    }


    @Override
    public void getStarBrief(String code, OnAPIListener<StarBuyActReferralInfo> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.StarBrief,
                SocketAPIConstant.ReqeutType.SearchStar, map);
        requestEntity(socketDataPacket, StarBuyActReferralInfo.class, listener);
    }

    @Override
    public void getStarExperience(String code, OnAPIListener<StarExperienceBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.StarExperience,
                SocketAPIConstant.ReqeutType.SearchStar, map);
        requestEntity(socketDataPacket, StarExperienceBeen.class, listener);
    }

    @Override
    public void getStarachive(String code, OnAPIListener<StarStarAchBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Starachive,
                SocketAPIConstant.ReqeutType.SearchStar, map);
        requestEntity(socketDataPacket, StarStarAchBeen.class, listener);
    }

    @Override
    public void getSeekList(String code, int startnum, int endnum, OnAPIListener<FansHotBuyReturnBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("starcode", code);
        map.put("startnum", startnum);
        map.put("endnum", endnum);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.SeekLike,
                SocketAPIConstant.ReqeutType.SearchStar, map);
        requestEntity(socketDataPacket, FansHotBuyReturnBeen.class, listener);
    }

    @Override
    public void getTransferList(String code, int startnum, int endnum, OnAPIListener<FansHotBuyReturnBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("starcode", code);
        map.put("startnum", startnum);
        map.put("endnum", endnum);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.TransferList,
                SocketAPIConstant.ReqeutType.SearchStar, map);
        requestEntity(socketDataPacket, FansHotBuyReturnBeen.class, listener);
    }

    @Override
    public void getFansComments(String starcode, OnAPIListener<Object> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("starcode", starcode);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.FansComments,
                SocketAPIConstant.ReqeutType.NewInfos, map);
        requestJsonObject(socketDataPacket, listener);
    }

    @Override
    public void getStarmaillist(long id, String token, String status, int startPos, int count, OnAPIListener<StarMailListBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        map.put("startPos", startPos);
        map.put("status", status);
        map.put("count", count);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Starmaillist,
                SocketAPIConstant.ReqeutType.History, map);
        requestEntity(socketDataPacket, StarMailListBeen.class, listener);
    }

    @Override
    public void addFriend(String accid, String faccid, String msg, int type, OnAPIListener<Object> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("accid", accid);
        map.put("faccid", faccid);
        map.put("msg", msg);
        map.put("type", type);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.AddFriend,
                SocketAPIConstant.ReqeutType.Wangyi, map);
        requestJsonObject(socketDataPacket, listener);
    }

    @Override
    public void reduceTime(String phone, String starcode, OnAPIListener<Object> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("starcode", starcode);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.ReduceTime,
                SocketAPIConstant.ReqeutType.Wangyi, map);
        requestJsonObject(socketDataPacket, listener);
    }

    @Override
    public void inquiry(String symbol, int startPos, int count, OnAPIListener<CommentMarketBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("token", SharePrefUtil.getInstance().getToken());
        map.put("startPos", startPos);
        map.put("count", count);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Inquiry,
                SocketAPIConstant.ReqeutType.Inquirylist, map);
        requestEntity(socketDataPacket, CommentMarketBeen.class, listener);
    }

    @Override
    public void getTimeLine(long id, String token, String symbol, int aType, OnAPIListener<TimeLineBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        map.put("symbol", symbol);
        map.put("aType", aType);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.TimeLine,
                SocketAPIConstant.ReqeutType.Time, map);
        requestEntity(socketDataPacket, TimeLineBeen.class, listener);
    }


    @Override
    public void getAddComment(String symbol, String fans_id, String nick_name, String comments, String head_url, OnAPIListener<Object> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("fans_id", fans_id);
        map.put("nick_name", nick_name);
        map.put("comments", comments);
        map.put("head_url", head_url);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.AddComment,
                SocketAPIConstant.ReqeutType.Inquirylist, map);
        requestJsonObject(socketDataPacket, listener);
    }

    @Override
    public void getSrealtime(long id, String token, List<SrealSendBeen> symbolInfos, OnAPIListener<SrealSendReturnBeen> listener) {
        JSONArray json = new JSONArray();
        for (SrealSendBeen symbolInfo : symbolInfos) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("symbol", symbolInfo.getSymbol());
                jo.put("aType", symbolInfo.getAType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(jo);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        map.put("symbolInfos", json);
        //LogUtils.loge(symbolInfos+"。。。HashMap"+json.toString()+"MAP....."+map.toString());
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Srealtime,
                SocketAPIConstant.ReqeutType.Time, map);
        requestEntity(socketDataPacket, SrealSendReturnBeen.class, listener);
    }

    @Override
    public void getAskToBuy(long id, String token, int sort, String symbol, int buySell, int amount, double price, OnAPIListener<AskToBuyReturnBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        map.put("sort", sort);
        map.put("symbol", symbol);
        map.put("buySell", buySell);
        map.put("amount", amount);
        map.put("price", price);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.AskToBuy,
                SocketAPIConstant.ReqeutType.BuyOrSell, map);
        requestEntity(socketDataPacket, AskToBuyReturnBeen.class, listener);
    }

    @Override
    public void getTradingStatus(long id, String token, String symbol, OnAPIListener<TradingStatusBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        map.put("symbol", symbol);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.TradingStatus,
                SocketAPIConstant.ReqeutType.BuyOrSell, map);
        requestEntity(socketDataPacket, TradingStatusBeen.class, listener);
    }

    @Override
    public void cancelOrder(long id, String token, long orderId, OnAPIListener<Object> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        map.put("orderId", orderId);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.CancelOrder,
                SocketAPIConstant.ReqeutType.BuyOrSell, map);
        requestJsonObject(socketDataPacket, listener);
    }

    @Override
    public void checkPayPas(long id, String token, String paypwd, OnAPIListener<ResultBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", id);
        map.put("token", token);
        map.put("paypwd", paypwd);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.CheckPayPas,
                SocketAPIConstant.ReqeutType.Pay, map);
        requestEntity(socketDataPacket, ResultBeen.class, listener);
    }

    @Override
    public void sureOrder(long id, String token, long orderId, long positionId, OnAPIListener<SureOrder> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        map.put("orderId", orderId);
        map.put("positionId", positionId);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.SureOrder,
                SocketAPIConstant.ReqeutType.BuyOrSell, map);
        requestEntity(socketDataPacket, SureOrder.class, listener);
    }

    @Override
    public void todayEntrust(int start, int count,Short opcode, OnAPIListener<List<TodayEntrustReturnBean>> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 142);
        map.put("token", "adc28ac69625652b46d5c00b");
        map.put("start", start);
        map.put("count", count);
//        map.put("status", status);
        SocketDataPacket socketDataPacket = socketDataPacket(opcode,
                SocketAPIConstant.ReqeutType.History, map);
        requestEntitys(socketDataPacket, "positionsList", TodayEntrustReturnBean.class, listener);
    }

    @Override
    public void todayDeal(int status,int start, int count,Short opcode, OnAPIListener<List<TodayDealReturnBean>> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 142);
        map.put("token", "adc28ac69625652b46d5c00b");
        map.put("start", start);
        map.put("count", count);
        map.put("status", status);
        SocketDataPacket socketDataPacket = socketDataPacket(opcode,
                SocketAPIConstant.ReqeutType.History, map);
        requestEntitys(socketDataPacket, "ordersList", TodayDealReturnBean.class, listener);
    }

    @Override
    public void fansRntrust(String symbol, int buySell, int start, int count, OnAPIListener<FansEntrustReturnBean> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 142);
        map.put("token", "adc28ac69625652b46d5c00b");
        map.put("start", start);
        map.put("count", count);
        map.put("symbol", symbol);
        map.put("buySell", buySell);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Fans,
                SocketAPIConstant.ReqeutType.History, map);
      requestEntity(socketDataPacket,FansEntrustReturnBean.class,listener);
    }
}
