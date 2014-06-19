package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
	private final static String TAG = "MyService";
	private int count;
	private boolean quit = true;

	private Thread thread;
	private MyBinder binder = new MyBinder();

	public class MyBinder extends Binder {
		public int getCount() {
			return count;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (quit) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
					count++;
				}
			}
		});
		thread.start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

}
