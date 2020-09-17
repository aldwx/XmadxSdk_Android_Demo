package com.xmadx.xmadxsdktestdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.xmadx.constant.SdkConstant;

public class MainActivity extends AppCompatActivity {
    private Button adSmall;
    private Button adMiddle;
    private Button adLarge;


    private String asidSmall = "xma461d3e2a531020a49ff5c0b1ad9ac";//替换你自己申请的广告位ID
    private String AdSmall=SdkConstant.BANNER_SMALL_582x166;
    private String asidLarge = "xm9de8c0238a05c9670b37119f6533b0";//替换你自己申请的广告位ID
    private String AdLarge = SdkConstant.BANNER_LARGE_960x540;
    private String asidMiddle = "xmede4da0e74db8c0b75eca18a3cc6d9";//替换你自己申请的广告位ID
    private String AdMiddle=SdkConstant.BANNER_MIDOLE_676x250;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adSmall = findViewById(R.id.ad_Small);
        adMiddle = findViewById(R.id.ad_Middle);
        adLarge = findViewById(R.id.ad_Large);
    }

    public void OnSmallClick(View view) {
        Intent intent = new Intent(this, AdBannerActivity.class);
        intent.putExtra("asid", asidSmall);
        intent.putExtra("adSize",AdSmall);
        intent.putExtra("isDev",true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void OnMiddleClick(View view) {
        Intent intent = new Intent(this, AdBannerActivity.class);
        intent.putExtra("asid", asidMiddle);
        intent.putExtra("adSize",AdMiddle);
        intent.putExtra("isDev",true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void OnLargeClick(View view) {
        Intent intent = new Intent(this, AdBannerActivity.class);
        intent.putExtra("asid", asidLarge);
        intent.putExtra("adSize",AdLarge);
        intent.putExtra("isDev",false);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}