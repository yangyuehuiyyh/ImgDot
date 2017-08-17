package com.punuo.sys.app.imgdot.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.punuo.sys.app.imgdot.R;
import com.punuo.sys.app.imgdot.bean.ImgSimple;
import com.punuo.sys.app.imgdot.bean.PointSimple;
import com.punuo.sys.app.imgdot.view.ImageLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 17/8/12.
 */

public class ImgBrowsePagerAdapter extends PagerAdapter implements ImageLayout.ClickListener, ImageLayout.VideoPlayListener{
    List<ImgSimple> imgSimples;

    List<View> views;

    Activity mContext;

    private int width;

    private TagListener tagListener;

    private PlayListener playListener;

    public ImgBrowsePagerAdapter(Activity context, List<ImgSimple> imgSimples) {

        this.mContext = context;
        this.imgSimples = imgSimples;

        this.views = new ArrayList<>();

        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);// 获取手机屏幕宽高分辨率

        width = dm.widthPixels;

    }

    @Override
    public int getCount() { // 获得size
        return imgSimples.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_browser, null);
        ImageLayout layoutContent = (ImageLayout) view.findViewById(R.id.layoutContent);
        layoutContent.setClickListener(this);
        layoutContent.setVideoPlayListener(this);

        try {

            String imgUrl = imgSimples.get(position).url;
            float scale = imgSimples.get(position).scale;
            ArrayList<PointSimple> pointSimples = imgSimples.get(position).pointSimples;

            layoutContent.setPoints(pointSimples);

            int height = (int) (width * scale);

            layoutContent.setImgBg(width, height, imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ((ViewPager) container).addView(view);

        return view;
    }

    @Override
    public void onPointClickListener(int tag) {
        System.out.println("-----tag" + tag);
        if (tagListener != null){
            tagListener.onTagListener(tag);
        }
    }

    public void setTagListener(TagListener taglistener){
        this.tagListener = taglistener;
    }

    public interface TagListener{
       void onTagListener(int tag);
    }

    @Override
    public void onVideoPlayListener(int tag) {
        if (playListener != null){
            playListener.onPlayListener(tag);
        }
    }

    public void setPlayListener(PlayListener playListener){
        this.playListener = playListener;
    }
    public interface PlayListener{
        void onPlayListener(int tag);
    }
}
