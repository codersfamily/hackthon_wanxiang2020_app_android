package com.dec.dstar.network.loader;

import com.dec.dstar.config.EnvironmentConfig;
import com.dec.dstar.network.DstarApiManager;
import com.dec.dstar.network.api.LoopringApi;
import com.dec.dstar.network.request.loopring.BaseRequest;
import com.dec.dstar.network.request.loopring.NotifyTransactionSubmitedRequest;
import com.dec.dstar.network.request.loopring.WalletBalanceRequest;

import rx.Observable;

/**
 * 作者：gulincheng
 * 日期:2018/8/3 14:14
 * 文件描述: 路印API
 */
public class LoopringApiLoader extends DstarApiManager<LoopringApi> {

    private static final String TAG = "LoopringApiLoader";

    protected LoopringApiLoader(Class apiService, String baseUrl) {
        super(apiService, baseUrl);
    }

    private static LoopringApiLoader sInstance = null;

    private LoopringApiLoader() {
        super(LoopringApi.class, EnvironmentConfig.getInstance().getBaseUrl());
    }

    public static LoopringApiLoader getInstance() {
        if (sInstance == null) {
            sInstance = new LoopringApiLoader();
        }
        return sInstance;
    }

    /**
     * 获取钱包代币余额
     */
    public Observable getWalletBalance(String owner) {

        WalletBalanceRequest request = new WalletBalanceRequest();
        request.params[0].setOwner(owner);
        return loaderComposeCommon(getApiService().getWalletBalance(request));
    }

    /**
     * 获取交易对列表
     */
    public Observable getTickerList(){

        BaseRequest request = new BaseRequest();
        request.setMethod("loopring_getTicker");
        return loaderComposeCommon(getApiService().getTickerList(request));
    }

    /**
     * 通知中继交易已成功
     * @return
     */
    public Observable notifyTransactionSubmitted(NotifyTransactionSubmitedRequest request){

        return loaderComposeCommon(getApiService().notifyTransactionSubmitted(request));
    }

    @Override
    protected String getBaseUrl() {
        return EnvironmentConfig.getInstance().getBaseUrl();
    }
}












