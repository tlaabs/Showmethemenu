package io.tlaabs.github.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.tlaabs.github.Adapter.RecyclerViewSectionAdaper;
import io.tlaabs.github.Enum.Foods;
import io.tlaabs.github.Enum.ServiceType;
import io.tlaabs.github.R;
import io.tlaabs.github.domain.StoreVO;

public class ScreenSlidePageFragment extends Fragment {

    private Context parent;

    ArrayList<StoreVO> premiumStoreList;
    ArrayList<StoreVO> normalStoreList;


    public static ScreenSlidePageFragment create(Foods foodType,
                                                 ArrayList<StoreVO> storeList
                                                 ){
        Bundle args = new Bundle();
        ArrayList<StoreVO> premiumStoreList = new ArrayList<>();
        ArrayList<StoreVO> normalStoreList = new ArrayList<>();

        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Log.d("prinz", storeList.size()+"");
        //type
        for(StoreVO store : storeList){
            Log.d("prinz","b1 : " + foodType.getValue());
            Log.d("prinz","b2 : " + store.getCat_id());
            Log.d("prinz","b3 : " + store.getService_type());
            Log.d("prinz","b4 : " + ServiceType.PREMINUM);

            if(foodType.getValue() == Integer.parseInt(store.getCat_id())){
                if(store.getService_type().equals(ServiceType.PREMINUM.getServiceType())){
                    Log.d("prinz","p1");
                    premiumStoreList.add(store);
                }else if(store.getService_type().equals(ServiceType.NORMAL.getServiceType())){
                    Log.d("prinz","p2");
                    normalStoreList.add(store);
                }
            }
        }

        args.putSerializable("premiumList",premiumStoreList);
        args.putSerializable("normalList",normalStoreList);

        fragment.setArguments(args);

        return fragment;
    }

    public ScreenSlidePageFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        premiumStoreList = (ArrayList<StoreVO>)getArguments().getSerializable("premiumList");
        normalStoreList = (ArrayList<StoreVO>)getArguments().getSerializable("normalList");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(
                R.layout.fragment_tab_list, container, false);

        RecyclerView my_recycler_view = rootView.findViewById(R.id.my_recycler_view);

        RecyclerViewSectionAdaper adapter = new RecyclerViewSectionAdaper(getContext(),premiumStoreList,normalStoreList);
        adapter.shouldShowFooters(true);
        GridLayoutManager manager = new GridLayoutManager(parent,
                getResources().getInteger(R.integer.grid_span));

        my_recycler_view.setLayoutManager(manager);
        adapter.setLayoutManager(manager);
        my_recycler_view.setAdapter(adapter);

        return rootView;
    }



}
