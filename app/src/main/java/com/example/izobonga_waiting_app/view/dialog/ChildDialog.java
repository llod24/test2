package com.example.izobonga_waiting_app.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.izobonga_waiting_app.FireBaseApi;
import com.example.izobonga_waiting_app.R;

public class ChildDialog  extends Dialog {
    FireBaseApi firebaseApi;
    private ImageView minus,plus;
    public TextView childNumber;
    private Button previous, next;
    private View.OnClickListener nextButtonListener;
    private View.OnClickListener preButtonListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseApi = new FireBaseApi();
        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_child);

        //셋팅
        minus = findViewById(R.id.child_minus);
        plus = findViewById(R.id.child_plus);
        childNumber = findViewById(R.id.child_number);
        previous = findViewById(R.id.child_previous);
        next = findViewById(R.id.child_next);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = Integer.parseInt(childNumber.getText().toString());
                if(n>0){
                    n = n - 1;
                    childNumber.setText(n+"");
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = Integer.parseInt(childNumber.getText().toString());
                n = n + 1;
                childNumber.setText(n+"");
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChildDialog.this.dismiss();
            }
        });
        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        next.setOnClickListener(nextButtonListener);
        previous.setOnClickListener(preButtonListener);
    }
    //생성자 생성
    public ChildDialog(@NonNull Context context, View.OnClickListener positiveListener, View.OnClickListener preButtonListener) {
        super(context);
        this.nextButtonListener = positiveListener;
        this.preButtonListener = preButtonListener;
    }

    public void dismissDialog(){
        childNumber.setText("0");
        dismiss();
    }
}
