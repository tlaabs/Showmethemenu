package io.tlaabs.github;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.tlaabs.github.Adapter.ScreenSlidePagerAdapter;
import io.tlaabs.github.Enum.Foods;
import io.tlaabs.github.domain.StoreVO;
import io.tlaabs.github.fake.FakeLocation;
import io.tlaabs.github.model.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabActivity extends AppCompatActivity {
    private final double MY_LAT = FakeLocation.MY_LATITUDE;
    private final double MY_LNG = FakeLocation.MY_LONGITUDE;
    private final int DISTANCE = FakeLocation.NEAR_METER; //30KM
    private final int PAGE_NUM = 6;

    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.distance_btn)
    ImageView distanceBtn;

    ArrayList<StoreVO> storeList = new ArrayList();

    private int selectedType = -1;
    private int preselectOption = -1;

    //Pager
    @BindView(R.id.pager)
    public ViewPager mPager;
    public PagerAdapter mPagerAdapter;
    @BindView(R.id.sliding_tabs)
    public TabLayout tabLayout;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        mContext = this;
        ButterKnife.bind(this);
        initPagerAndTab();
        dataBind();
    }

    public void dataBind(){
        ApiService apiService = ((ApiReposApplication) getApplication()).getApiService();
        apiService.getStores(MY_LAT,MY_LNG,DISTANCE).enqueue(new Callback<List<StoreVO>>() {
            @Override
            public void onResponse(Call<List<StoreVO>> call, Response<List<StoreVO>> response) {
                Log.d("testpage",response.body().size()+"");
                storeList = (ArrayList<StoreVO>)response.body();
                Log.d("pzo",storeList.size()+"");
                Log.d("pzo","PK: " + storeList.get(0).getSido()+"");
                initPagerAndTab();
            }

            @Override
            public void onFailure(Call<List<StoreVO>> call, Throwable t) {
                Log.d("testpage",t.getMessage());
            }
        });
    }

    public void initPagerAndTab(){
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),this,storeList,PAGE_NUM);
        mPager.setAdapter(mPagerAdapter);

        tabLayout.setTabTextColors(getResources().getColor(R.color.tabNormal), getResources().getColor(R.color.tabSelected));

        tabLayout.setupWithViewPager(mPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                appTitle.setText(tab.getText());
                if(tab.getPosition() != 0)
                    selectedType = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void initTabLayout(int selected) {
        TabLayout.Tab tab = tabLayout.getTabAt(selected);
        tab.select();
    }
}
