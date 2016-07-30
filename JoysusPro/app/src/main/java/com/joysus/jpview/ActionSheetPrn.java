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
import android.widget.Button;

import com.joysus.R;
import com.joysus.jpview.wheelview.ArrayWheelAdapter;
import com.joysus.jpview.wheelview.OnWheelScrollListener;
import com.joysus.jpview.wheelview.WheelView;

public class ActionSheetPrn {
    private WheelView mprn;
    private WheelView mcity;
    private String[] prins, citys;
    private OnActionSheetSelected actionSheetSelected;

    public interface OnActionSheetSelected {
        void onClick(int whichButton);

        void onSelected(String value);
    }

    public ActionSheetPrn() {

    }


    public Dialog showSheet(Context context, String[] prn, String[] city, final OnActionSheetSelected actionSheetSelected,
                            OnCancelListener cancelListener) {
        final Dialog dlg = new Dialog(context, R.style.ActionSheet);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.actionsheetprn, null);
        final int cFullFillWidth = 10000;
        layout.setMinimumWidth(cFullFillWidth);
        mprn = (WheelView) layout.findViewById(R.id.wheelprn);
        mcity = (WheelView) layout.findViewById(R.id.wheelcity);
        Button mCancel = (Button) layout.findViewById(R.id.btncancel);
        this.prins = prn;
        this.citys = city;
        this.actionSheetSelected = actionSheetSelected;
        mprn.setAdapter(new ArrayWheelAdapter<String>(prn));
        mprn.addScrollingListener(scrollListener);
        mcity.setAdapter(new ArrayWheelAdapter<String>(city));
        mcity.addScrollingListener(scrollListener);
//        mmale.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                actionSheetSelected.onClick(0);
//                dlg.dismiss();
//            }
//        });
//        mfemale.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                actionSheetSelected.onClick(1);
//                dlg.dismiss();
//            }
//        });

        mCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                actionSheetSelected.onClick(5);
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

    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            String prn = prins[mprn.getCurrentItem()];
            String city = citys[mcity.getCurrentItem()];
            actionSheetSelected.onSelected(prn + city);
        }
    };
}
