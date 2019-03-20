package io.tlaabs.github.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import io.tlaabs.github.Enum.Foods;
import io.tlaabs.github.R;
import io.tlaabs.github.domain.StoreVO;
import io.tlaabs.github.fragment.ScreenSlidePageFragment;


public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    Context mContext;
    ArrayList<StoreVO> storeList;
    int pageNum;

    public ScreenSlidePagerAdapter(
            FragmentManager fm, Context context,
            ArrayList<StoreVO> storeList, int pageNum) {
        super(fm);
        this.mContext = context;
        this.storeList = storeList;
        this.pageNum = pageNum;
    }

    @Override
    public Fragment getItem(int position) {
        Foods foodType = Foods.getFood(position);
        return ScreenSlidePageFragment.create(foodType, storeList);
    }

    @Override
    public int getCount() {
        return pageNum;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Foods foodType = Foods.getFood(position);
        switch (foodType) {
            case RICE:
                return mContext.getString(R.string.category_1);
            case NOODLE:
                return mContext.getString(R.string.category_2);
            case CAFE:
                return mContext.getString(R.string.category_3);
            case PIZZA:
                return mContext.getString(R.string.category_4);
            case CHICKEN:
                return mContext.getString(R.string.category_5);
            case FASTFOOD:
                return mContext.getString(R.string.category_6);
            default:
                return null;
        }
    }
}