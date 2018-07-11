package usst.liuyan.demo.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import me.drakeet.materialdialog.MaterialDialog;
import usst.liuyan.demo.DB.DatabaseAccess;
import usst.liuyan.demo.Entity.Memo;
import usst.liuyan.demo.R;
import usst.liuyan.demo.SimpleToolbar;

public class EditActivity extends AppCompatActivity {
    private SimpleToolbar bar;

    private ImageView delete;
    private ImageView alarm;
    private EditText content;

    private Memo memo;
    private MaterialDialog mMaterialDialog;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        bar = findViewById(R.id.edit_bar);
        delete = findViewById(R.id.delete);
        alarm = findViewById(R.id.alarm);
        content = findViewById(R.id.text_content);

        context = this;

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            memo = (Memo) bundle.get("MEMO");
            if(memo != null) {
                this.content.setText(memo.getText());
            }
        }

        initBar();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClicked();
            }
        });
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClock();
            }
        });
    }

    private void initBar() {
        bar.setLeftTitleText(" ");
        bar.setLeftTitleDrawable(R.drawable.outline_arrow_back_ios_white_18dp);
        bar.setRightTitleText("完成");
        bar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
        bar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
    }

    public void onSaveClicked(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        if(memo == null) { //new memo
            Memo temp = new Memo();
            temp.setText(content.getText().toString());
            databaseAccess.save(temp);
        } else {//update memo
            memo.setText(content.getText().toString());
            databaseAccess.update(memo);
        }
        databaseAccess.close();
        this.finish();
    }

    public  void onDeleteClicked(){
        if(memo == null){ //new memo
            this.finish();
        }else{
            mMaterialDialog = new MaterialDialog(this);
            mMaterialDialog.setTitle("删除便签");
            mMaterialDialog.setMessage("确定删除此便签");
            mMaterialDialog.setPositiveButton("确认", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
                    databaseAccess.open();
                    databaseAccess.delete(memo);
                    databaseAccess.close();
                    mMaterialDialog.dismiss();
                    Intent intent = new Intent(EditActivity.this,MainActivity.class);
                    context.startActivity(intent);
                }
            });
            mMaterialDialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMaterialDialog.dismiss();
                }
            });

            mMaterialDialog.show();
        }
    }

    public void setClock(){
        Toast.makeText(this,"Set Clock",Toast.LENGTH_LONG).show();
    }
}
