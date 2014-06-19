package com.example.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final String TAG = "MainActivity";
	private Button bn1,bn2,bn3;
	private MyService.MyBinder binder;
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d(TAG, "--Service Disconnected--");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Toast.makeText(MainActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
			binder = (MyService.MyBinder) service;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Intent intent = new Intent(MainActivity.this,MyService.class);
		
		bn1 = (Button) findViewById(R.id.button1);
		bn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});
		bn2 = (Button) findViewById(R.id.button2);
		bn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				binder = null;
				unbindService(conn);
			}
		});
		bn3 = (Button) findViewById(R.id.button3);
		bn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(binder!=null){
					Toast.makeText(MainActivity.this, "Count"+binder.getCount(), Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(MainActivity.this, "未绑定", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
}
