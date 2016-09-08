package com.android.wneng.utils.input;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.wneng.app.BaseApplication;
import com.android.wneng.utils.Helper;



public class InputMethodHelper {


	public static void openInputMethod(View view) {
		view.requestFocus();
		InputMethodManager imm = (InputMethodManager) BaseApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
	}


	public static void closeInputMethod(Activity act) {
		if (Helper.isNotNull(act.getCurrentFocus())) {
			InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(),	0);
		}
	}

	public static void toggleInputMethod() {
		InputMethodManager imm = (InputMethodManager) BaseApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
}