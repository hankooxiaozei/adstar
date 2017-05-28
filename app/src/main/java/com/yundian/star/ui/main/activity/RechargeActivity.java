package com.yundian.star.ui.main.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yundian.star.R;
import com.yundian.star.alipay.PayResult;
import com.yundian.star.app.AppApplication;
import com.yundian.star.base.BaseActivity;
import com.yundian.star.been.EventBusMessage;
import com.yundian.star.been.WXPayReturnEntity;
import com.yundian.star.listener.OnAPIListener;
import com.yundian.star.networkapi.NetworkAPIFactoryImpl;
import com.yundian.star.ui.view.RoundImageView;
import com.yundian.star.utils.LogUtils;
import com.yundian.star.utils.ToastUtils;
import com.yundian.star.widget.NormalTitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by sll on 2017/5/23.
 */

public class RechargeActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.nt_title)
    NormalTitleBar ntTitle;
    @Bind(R.id.iv_bank_sign)
    RoundImageView ivBankSign;
    @Bind(R.id.tv_bank_name)
    TextView tvBankName;
    @Bind(R.id.choose_recharge_type)
    RelativeLayout chooseRechargeType;
    @Bind(R.id.rb_recharge_money1)
    RadioButton rbRechargeMoney1;
    @Bind(R.id.rb_recharge_money2)
    RadioButton rbRechargeMoney2;
    @Bind(R.id.rb_recharge_money3)
    RadioButton rbRechargeMoney3;
    @Bind(R.id.rb_recharge_money4)
    RadioButton rbRechargeMoney4;
    @Bind(R.id.rb_recharge_money5)
    RadioButton rbRechargeMoney5;
    @Bind(R.id.rb_recharge_money6)
    RadioButton rbRechargeMoney6;
    @Bind(R.id.btn_recharge_sure)
    Button btnRechargeSure;
    @Bind(R.id.ll_user_recharge_type)
    LinearLayout rechargeType;
    @Bind(R.id.tv_recharge_name)
    TextView rechargeName;
    @Bind(R.id.iv_recharge_type)
    ImageView rechargeIcon;
    private WXPayReturnEntity wxPayEntity;
    private boolean flag = true;
    private PopupWindow popupWindow;
    private boolean isAliPay = true;

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        ntTitle.setTitleText(getResources().getString(R.string.user_recharge));
        ntTitle.setBackVisibility(true);
        if (flag) {
            EventBus.getDefault().register(this);
            flag = false;
        }
        initPopupWindow();
    }

    @OnClick({R.id.btn_recharge_sure, R.id.ll_user_recharge_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_recharge_sure:

                applyRecharge();
                break;
            case R.id.ll_user_recharge_type:  //选择充值方式
                popupWindow.showAtLocation(findViewById(R.id.ll_recharge_ui), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    private void applyRecharge() {
        ToastUtils.showShort("充值");
        String title = "微盘-余额充值";
        int price = 10;
        if (isAliPay) {
            requestAliPay();
        } else {
            requestWXPay(title, price);
        }
    }

    private void initPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentview = inflater.inflate(R.layout.layout_dialog_recharge_type, null);
        contentview.setFocusable(true); // 这个很重要
        contentview.setFocusableInTouchMode(true);
        popupWindow = new PopupWindow(contentview, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        contentview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        contentview.findViewById(R.id.recharge_alipay).setOnClickListener(this);
        contentview.findViewById(R.id.recharge_weixin_pay).setOnClickListener(this);
        contentview.findViewById(R.id.recharge_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge_alipay:
                ToastUtils.showShort("支付宝");
                rechargeName.setText(getString(R.string.recharge_ali_pay));
                isAliPay = true;
                //设置图标
                popupWindow.dismiss();
                break;
            case R.id.recharge_weixin_pay:
                isAliPay = false;
                rechargeName.setText(getString(R.string.recharge_wxpay));
                ToastUtils.showShort("weixin");
                popupWindow.dismiss();
                break;
            case R.id.recharge_cancel:
                ToastUtils.showShort("quxiao");
                popupWindow.dismiss();
                break;
        }
    }

    private void requestAliPay() {
        ToastUtils.showShort("请求支付宝支付");
        final String orderInfo = "";   // 订单信息   请求服务端返回payinfo
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargeActivity.this);
                String result = alipay.pay(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);    // 必须异步调用
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }
    };

    /**
     * 请求微信支付
     *
     * @param title title
     * @param price 金额
     */
    private void requestWXPay(String title, double price) {
        NetworkAPIFactoryImpl.getDealAPI().weixinPay(title, price, new OnAPIListener<WXPayReturnEntity>() {
            @Override
            public void onError(Throwable ex) {
                ex.printStackTrace();
                ToastUtils.showShort("微信支付调用失败 ");
            }

            @Override
            public void onSuccess(WXPayReturnEntity wxPayReturnEntity) {
                ToastUtils.showShort("微信支付调用成功 ");
                wxPayEntity = wxPayReturnEntity;
                PayReq request = new PayReq();
                request.appId = wxPayReturnEntity.getAppid();
                request.partnerId = wxPayReturnEntity.getPartnerid();
                request.prepayId = wxPayReturnEntity.getPrepayid();
//                    request.packageValue = wxPayReturnEntity.getPackage();
                request.packageValue = "Sign=WXPay";
                request.nonceStr = wxPayReturnEntity.getNoncestr();
                request.timeStamp = wxPayReturnEntity.getTimestamp();
                request.sign = wxPayReturnEntity.getSign();
                AppApplication.api.sendReq(request);
            }
        });
    }

    /**
     * EventBus接收消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void ReciveMessage(EventBusMessage eventBusMessage) {
        switch (eventBusMessage.Message) {
            case 0:  //成功
                ToastUtils.showShort("支付成功");       //1-成功 2-取消支付
                LogUtils.logd("支付成功,更新余额,进入充值列表");
                break;
            case -2:  //取消支付
                ToastUtils.showShort("用户取消支付");
                break;

            case -10:  //取消支付
                ToastUtils.showShort("用户取消支付-10");
                break;
        }
    }
}
