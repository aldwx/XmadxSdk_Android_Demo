package com.xmadx.xmadxsdktestdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.xmadx.Interface.AdViewBannerListener;
import com.xmadx.constant.XmadxAdSdkManager;

import java.util.Objects;

public class AdBannerActivity extends AppCompatActivity implements AdViewBannerListener, View.OnClickListener {
    private LinearLayout LiearSdk;
    private XmadxAdSdkManager mAdSdkManager;
    private String WEIXIN_APP_ID = "";//填上自己申请的weixin平台的AppID
    private String appKey = "xm7bdd4ed3b0fec28ecaa40bfbb1ea88";//替换你自己申请的媒体ID
    private String asid;//替换你自己申请的广告位ID
    private String adSize ;
    private Button banner_hide = null;
    private Button nextAd = null;
    private int refreshTime = -1;
    private boolean isDev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_banner);

        adSize= Objects.requireNonNull(getIntent().getExtras()).getString("adSize");
        asid=Objects.requireNonNull(getIntent().getExtras()).getString("asid");
        isDev=getIntent().getExtras().getBoolean("isDev");
        LiearSdk = findViewById(R.id.Liear_sdk);
        nextAd = findViewById(R.id.banner_change);
        banner_hide = findViewById(R.id.banner_hide);
        requestBanner();

        nextAd.setOnClickListener(this);
        banner_hide.setOnClickListener(this);

    }

    private void requestBanner() {

        /*
         *  WEIXIN_APP_ID (自己申请的weixin平台的AppID)
         *  isDev true:为测试开发;false为正式开发
         *  re(传入需要加载广告的控件)
         */
        mAdSdkManager = new XmadxAdSdkManager(this,
                appKey,
                asid,
                adSize,
                isDev,
                WEIXIN_APP_ID
        );

        LiearSdk.addView(mAdSdkManager.getAdViewLayout());//re为加载广告view的容器


        mAdSdkManager.setOnAdViewListener(this);
        if (null != banner_hide)
            banner_hide.setText(R.string.req_hide);
    }

    @Override
    public void onAdClicked() {

    }

    @Override
    public void onAdReceived() {

    }

    @Override
    public void onAdFailedReceived(String s) {

        if (null != mAdSdkManager) {
            if (LiearSdk != null) {
                //停止刷新广告
                mAdSdkManager.setBannerStopRequest();
                //移除广告
                LiearSdk.removeView(mAdSdkManager.getAdViewLayout());

            }}
    }

    @Override
    public void onAdClosed() {
        //尚未实现
    }

    @Override
    public void onAdReady() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (null!= mAdSdkManager){
            mAdSdkManager.setBannerStopRequest();
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdSdkManager =null;
    }

    @Override
    public void onClick(View v) {

        if (null == LiearSdk)
            return;
        switch (v.getId()) {

            case R.id.banner_change:

                    if (null != mAdSdkManager) {
                        mAdSdkManager.setBannerStopRequest();
                        LiearSdk.removeView(mAdSdkManager.getAdViewLayout());
                    }
                    requestBanner();



            break;

            case R.id.banner_hide:

                if (banner_hide.getText().equals(getResources().getText(R.string.req_hide))) {
                    banner_hide.setText(R.string.req_show);
                    LiearSdk.removeView(mAdSdkManager.getAdViewLayout());
                }else {
                    banner_hide.setText(R.string.req_hide);
                    LiearSdk.addView(mAdSdkManager.getAdViewLayout());
                }
                break;
        }
    }
}