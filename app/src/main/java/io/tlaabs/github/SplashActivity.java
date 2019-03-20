package io.tlaabs.github;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView titleText;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.mContext = this;
        ButterKnife.bind(this);
        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    startActivity(new Intent(mContext,MainActivity.class));
                    finish();
                }catch(Exception e){e.printStackTrace();}
            }
        }).start();

    }

    public void initView() {
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), getResources().getString(R.string.font_path));
        titleText.setTypeface(typeface);
    }
}
