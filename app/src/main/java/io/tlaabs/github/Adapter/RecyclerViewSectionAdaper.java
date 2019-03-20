package io.tlaabs.github.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.afollestad.sectionedrecyclerview.SectionedViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import io.tlaabs.github.Enum.Sections;
import io.tlaabs.github.R;
import io.tlaabs.github.domain.StoreVO;

/**
 * Created by devsimMe on 2017-11-15.
 */

public class RecyclerViewSectionAdaper extends SectionedRecyclerViewAdapter<SectionedViewHolder> {
    ArrayList<StoreVO> premiumStoreList;
    ArrayList<StoreVO> normalStoreList;

    Context parent;

    public RecyclerViewSectionAdaper(Context context,
                                     ArrayList<StoreVO> premiumList,
                                     ArrayList<StoreVO> normalList) {
        this.premiumStoreList = premiumList;
        this.normalStoreList = normalList;
        this.parent = context;
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    @Override
    public int getSectionCount() {
        return 2;
    }

    @Override
    public int getItemCount(int section) {
        Sections sec = Sections.getSection(section);
        if(sec == Sections.PREMIUM)
            return premiumStoreList.size();
        else
            return normalStoreList.size();
    }

    @Override
    public void onBindHeaderViewHolder(SectionedViewHolder holder, int section, boolean expanded) {
        ItemHeaderHolder sectionViewHolder = (ItemHeaderHolder) holder;
        String sectionName = null;

        Sections sec = Sections.getSection(section);
        switch (sec){
            case PREMIUM:
                sectionName = "Premium";
                Glide
                        .with(parent)
                        .load(R.drawable.premium)
                        .into(sectionViewHolder.icon);
                break;
            case NORMAL:
                sectionName = "Normal";
                Glide
                        .with(parent)
                        .load(R.drawable.normal)
                        .into(sectionViewHolder.icon);
                break;

        }
        sectionViewHolder.title.setText(sectionName);
    }

    @Override
    public void onBindViewHolder(SectionedViewHolder holder, int section, int relativePosition, int absolutePosition) {
        StoreVO store = null;

        Sections sec = Sections.getSection(section);
        switch(sec) {
            case PREMIUM:
                store = premiumStoreList.get(relativePosition);
                break;
            case NORMAL:
                store = normalStoreList.get(relativePosition);
                break;
        }

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.store = store;
        itemViewHolder.title.setText(store.getSubject());
        itemViewHolder.distance.setText(store.getDistnace()+"");

        if(store.getImg_url() == null){
            Glide
                    .with(parent)
                    .load(R.drawable.store)
                    .into(itemViewHolder.icon);
        }else{
            Glide
                    .with(parent)
                    .load(store.getImg_url())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(itemViewHolder.icon);
        }

        //view reset
        if(store.getNum_review() < 1) {
            itemViewHolder.rating.setText("-");
            itemViewHolder.review.setText("reviews 0");
        }else{
            itemViewHolder.rating.setText(store.getAvg_rating()+"");
            itemViewHolder.review.setText("reviews " + store.getNum_review());
        }
    }

    @Override
    public void onBindFooterViewHolder(SectionedViewHolder holder, int section) {}

    @Override
    public SectionedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_header, parent, false);
                return new ItemHeaderHolder(v);
            case VIEW_TYPE_ITEM:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_main, parent, false);
                return new ItemViewHolder(v);
            case VIEW_TYPE_FOOTER:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_footer, parent, false);
                return new ItemFooterHolder(v);
        }
        return null;
    }

    public static class ItemHeaderHolder extends SectionedViewHolder{
        final TextView title;
        final ImageView icon;
        public ItemHeaderHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.sectionTitle);
            icon = itemView.findViewById(R.id.header_icon);
        }
    }
    public class ItemViewHolder extends SectionedViewHolder {
        final TextView title;
        final ImageView icon;
        final TextView distance;
        final TextView rating;
        final TextView review;
        StoreVO store;

        public ItemViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            icon = itemView.findViewById(R.id.icon);
            distance = itemView.findViewById(R.id.distance);
            rating = itemView.findViewById(R.id.ratings);
            review = itemView.findViewById(R.id.reviews);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(parent, Details2Activity.class);
//                    i.putExtra("store",store);
//                    Log.i("staa","staa" + store.getContact());
//                    parent.startActivity(i);
//                }
//            });
        }
    }
    public static class ItemFooterHolder extends SectionedViewHolder{

        public ItemFooterHolder(View itemView) {
            super(itemView);
        }
    }
}
