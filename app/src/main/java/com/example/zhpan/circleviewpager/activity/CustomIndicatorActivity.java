package com.example.zhpan.circleviewpager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.zhpan.circleviewpager.R;
import com.zhpan.bannerview.indicator.DashIndicatorView;
import com.example.zhpan.circleviewpager.viewholder.SlideModeViewHolder;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.utils.DpUtils;
import com.zhpan.idea.utils.ToastUtils;

import java.util.Arrays;
import java.util.List;

public class CustomIndicatorActivity extends AppCompatActivity {
    private String[] picUrls = {"http://pic31.nipic.com/20130801/11604791_100539834000_2.jpg",
            "http://pic37.nipic.com/20140115/7430301_100825571157_2.jpg",
            "http://pic29.nipic.com/20130507/8952533_183922555000_2.jpg",
            "http://b-ssl.duitang.com/uploads/item/201706/10/20170610095055_G5LM8.jpeg"};
    BannerViewPager<String, SlideModeViewHolder> viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator_slide_mode);
        setTitle(getString(R.string.custom_indicator_view));
        setUpViewPager();
    }

    private void setUpViewPager() {
        viewPager = findViewById(R.id.banner_view_dash);
        List<String> list = Arrays.asList(picUrls);
        viewPager.setAutoPlay(false).setCanLoop(true)
                .setRoundCorner(DpUtils.dp2px(5))
                .setIndicatorView(setupIndicatorView(list.size()))
                .setOnPageClickListener(position -> ToastUtils.show(position+""))
                .setHolderCreator(SlideModeViewHolder::new).create(list);
    }

    /**
     * 这里可以是自定义的Indicator，需要继承BaseIndicatorView或者实现IIndicator接口;
     */
    private DashIndicatorView setupIndicatorView(int pageSize) {
        DashIndicatorView indicatorView = new DashIndicatorView(this);
        indicatorView.setPageSize(pageSize);
        indicatorView.setIndicatorWidth(DpUtils.dp2px(8), DpUtils.dp2px(8));
        indicatorView.setSliderHeight(DpUtils.dp2px(4));
        indicatorView.setIndicatorGap(DpUtils.dp2px(5));
        indicatorView.setCheckedColor(getResources().getColor(R.color.colorAccent));
        indicatorView.setNormalColor(getResources().getColor(R.color.colorPrimary));
        return indicatorView;
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewPager.stopLoop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.startLoop();
    }
}
