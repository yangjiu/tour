package com.joysus.jpview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joysus.R;

public class ActionSheet {

	public interface OnActionSheetSelected {
		void onClick(int whichButton);
	}

	private ActionSheet() {
	}

	public static Dialog showSheet(Context context, final OnActionSheetSelected actionSheetSelected,
			OnCancelListener cancelListener) {
		final Dialog dlg = new Dialog(context, R.style.ActionSheet);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.actionsheet, null);
		final int cFullFillWidth = 10000;
		layout.setMinimumWidth(cFullFillWidth);

		TextView mmale = (TextView) layout.findViewById(R.id.tv_gender_male);
		TextView mfemale = (TextView) layout.findViewById(R.id.tv_gender_female);
		TextView mCancel = (TextView) layout.findViewById(R.id.cancel);
		mmale.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				actionSheetSelected.onClick(0);
				dlg.dismiss();
			}
		});
		mfemale.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				actionSheetSelected.onClick(1);
				dlg.dismiss();
			}
		});

		mCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				actionSheetSelected.onClick(2);
				dlg.dismiss();
			}
		});

		Window w = dlg.getWindow();
		WindowManager.LayoutParams lp = w.getAttributes();
		lp.x = 0;
		final int cMakeBottom = -1000;
		lp.y = cMakeBottom;
		lp.gravity = Gravity.BOTTOM;
		dlg.onWindowAttributesChanged(lp);
		dlg.setCanceledOnTouchOutside(false);
		if (cancelListener != null)
			dlg.setOnCancelListener(cancelListener);

		dlg.setContentView(layout);
		dlg.show();

		return dlg;
	}

}
