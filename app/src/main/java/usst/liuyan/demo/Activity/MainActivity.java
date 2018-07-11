package usst.liuyan.demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import usst.liuyan.demo.R;
import usst.liuyan.demo.SimpleToolbar;

public class MainActivity extends AppCompatActivity {

    private SimpleToolbar mSimpleToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSimpleToolbar = findViewById(R.id.main_bar);
        mSimpleToolbar.setMainTitle("便签");
        mSimpleToolbar.setLeftTitleText("");
        mSimpleToolbar.setLeftTitleDrawable(R.drawable.outline_arrow_back_ios_white_18dp);


    }
}
