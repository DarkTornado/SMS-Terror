package com.darktornado.smsterror;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);

        TextView txt1 = new TextView(this);
        txt1.setText("전화번호 : ");
        layout.addView(txt1);
        final EditText txt2 = new EditText(this);
        txt2.setHint("수신자의 전화번호를 입력하세요...");
        txt2.setSingleLine();
        layout.addView(txt2);

        TextView txt3 = new TextView(this);
        txt3.setText("\n문자 내용 : ");
        layout.addView(txt3);
        final EditText txt4 = new EditText(this);
        txt4.setHint("문자 내용을 입력하세요...");
        layout.addView(txt4);

        final TextView txt5 = new TextView(this);
        txt5.setText("\n전송 횟수 : 1회");
        layout.addView(txt5);
        final SeekBar amount = new SeekBar(this);
        amount.setMax(4);
        amount.setPadding(dip2px(8), dip2px(12), dip2px(8), dip2px(12));
        amount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt5.setText("\n전송 횟수 : " + (progress + 1) + "회");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        layout.addView(amount);

        Button send = new Button(this);
        send.setText("전송");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phone = txt2.getText().toString();
                final String value = txt4.getText().toString();
                final int count = amount.getProgress() + 1;
                new Thread(()->sendSMS(phone, value, count)).start();
            }
        });
        layout.addView(send);
        Button info = new Button(this);
        info.setText("앱 정보 / 도움말");
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layout.addView(info);

        TextView maker = new TextView(this);
        maker.setText("\n© 2016-2024 Dark Tornado, All rights reserved.\n");
        maker.setTextSize(13);
        maker.setGravity(Gravity.CENTER);
        layout.addView(maker);

        int pad = dip2px(16);
        layout.setPadding(pad, pad, pad, pad);
        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        setContentView(scroll);
    }

    private void sendSMS(String phone, String value, int count) {
        try {
            //문자 전송
            for (int n = 0; n < count; n++) {
                if (n > 0) Thread.sleep(2000); //2초 정도 딜레이를 주어야 문자 전송이 누락되지 않음

            }
        } catch (Exception e) {
            toast("문자 전송 실패: " + e.toString());
        }
    }

    private void toast(final String msg) {
        runOnUiThread(() -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());
    }

    private int dip2px(int dips) {
        return (int) Math.ceil(dips * getResources().getDisplayMetrics().density);
    }
}