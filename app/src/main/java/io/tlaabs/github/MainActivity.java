package io.tlaabs.github;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.tlaabs.github.Enum.Foods;
import io.tlaabs.github.fake.FakeLocation;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.location)
    public TextView locationText;

    @BindView(R.id.rice_icon)
    public ImageView riceIcon;
    @BindView(R.id.noodle_icon)
    public ImageView noodleIcon;
    @BindView(R.id.cafe_icon)
    public ImageView cafeIcon;
    @BindView(R.id.pizza_icon)
    public ImageView pizzaIcon;
    @BindView(R.id.chicken_icon)
    public ImageView chickenIcon;
    @BindView(R.id.fastfood_icon)
    public ImageView fastfoodIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    public void itemClicked(View v){
        Intent i = new Intent(this,TabActivity.class);
        switch (v.getId()){
            case R.id.rice_icon:
                i.putExtra("type", Foods.RICE);
                break;
            case R.id.noodle_icon:
                i.putExtra("type",Foods.NOODLE);
                break;
            case R.id.cafe_icon:
                i.putExtra("type",Foods.CAFE);
                break;
            case R.id.pizza_icon:
                i.putExtra("type",Foods.PIZZA);
                break;
            case R.id.chicken_icon:
                i.putExtra("type", Foods.CHICKEN);
                break;
            case R.id.fastfood_icon:
                i.putExtra("type",Foods.FASTFOOD);
                break;
        }
        startActivity(i);
    }

    private void initView(){
        locationText.setText(FakeLocation.MY_LOCATION_EN);
        bindImage(this,riceIcon,R.drawable.rice);
        bindImage(this,noodleIcon,R.drawable.noodle);
        bindImage(this,cafeIcon,R.drawable.cafe);
        bindImage(this,pizzaIcon,R.drawable.pizza);
        bindImage(this,chickenIcon,R.drawable.chicken);
        bindImage(this,fastfoodIcon,R.drawable.fast_food);
    }

    private void bindImage(Context context, ImageView view, int res){
        Glide
                .with(this)
                .load(res)
                .into(view);
    }
}
