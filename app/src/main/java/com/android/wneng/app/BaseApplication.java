package com.android.wneng.app;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.android.wneng.utils.Helper;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;



public class BaseApplication extends MultiDexApplication {
	
	private static final String TAG = BaseApplication.class.getSimpleName();
	
	private static BaseApplication sInstance;
	private List<WeakReference<Activity>> activities = new LinkedList<WeakReference<Activity>>();

	public static BaseApplication getInstance() {

		if (Helper.isNull(sInstance)) {
		}
		return sInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		sInstance = this;
	}



}
