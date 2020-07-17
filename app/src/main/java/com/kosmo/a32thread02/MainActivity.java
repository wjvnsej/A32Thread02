package com.kosmo.a32thread02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO61";

    TextView textView1;
    Handler handler;
    //프로그레스바 사용
    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //핸들러 객체 생성
        handler = new Handler();

        textView1 = findViewById(R.id.textView1);
        progressBar1 = findViewById(R.id.progressBar1);
    }

    public void onBtn1Clicked(View v) {
        RequestThread thread = new RequestThread();
        thread.start();
    }

    class RequestThread extends Thread {
        @Override
        public void run() {
            for(int i = 0; i < 100; i++) {
                Log.d(TAG, "Request Thread... " + i);

                /*
                별도의 핸들러 클래스를 생성하지 않고 내부 클래스 형태로 생성하여
                바로 처리한다. for 문의 변수 i는 내부클래스에서 사용할 수 없으므로
                final 로 선언한 index 를 사용해야 한다.
                 */
                final int index = i;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView1.setText("Request Thread... " + index);

                        //프로그레스의 진행상황을 증가시키는 메소드
                        progressBar1.incrementProgressBy(1);
                    }
                });

                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
