package com.it.ddadishudemo;

import java.security.PublicKey;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView showTextview;
	int length=2*60*1000;
	int index=0;
	ImageView[] imgs;
	int score=0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showTextview=(TextView) findViewById(R.id.show_tv);
		
		
		new Thread(new Runnable() {	
			@Override
			public void run() {
				while(length>0){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					length=length-1000;
				    runOnUiThread(new Runnable() {
				    	@Override
						public void run() {
						
							showTextview.setText("剩余时间："+length/1000+"秒");
							
						}
					});
				}
			}
		}).start();			
								
		imgs=new ImageView[6];
		imgs[0]= (ImageView) findViewById(R.id.imageView1);
		imgs[1]= (ImageView) findViewById(R.id.imageView2);
		imgs[2]= (ImageView) findViewById(R.id.imageView3);
		imgs[3]= (ImageView) findViewById(R.id.imageView4);
		imgs[4]= (ImageView) findViewById(R.id.imageView5);
		imgs[5]= (ImageView) findViewById(R.id.imageView6);
		
	
	new Thread(new Runnable() {
		@Override	
		public void run() {
			while(length>0){
				index=new Random().nextInt(6);
				runOnUiThread(new Runnable() {
			
					public void run() {
				imgs[index]
						.setImageResource(R.drawable.ic_launcher);
				imgs[index].setTag("dishu");
				}
				});
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			runOnUiThread(new Runnable() {				
				public void run() {
					showTextview.setText("得分："+score);
					
				}
			});
		}
					
	}).start();	
		
	
	}

	public void fun(View view){
		ImageView img = (ImageView) view;
		
		if("dishu".equals(img.getTag().toString()))
			score++;		
		img.setImageResource(R.drawable.bg);
		img.setTag("bg");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
