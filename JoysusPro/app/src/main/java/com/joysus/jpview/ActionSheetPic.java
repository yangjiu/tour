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
import android.widget.TextView;

import com.joysus.R;

public class ActionSheetPic {

    public interface OnActionSheetSelected {
        void onClick(int whichButton);
    }

    private ActionSheetPic() {
    }

    public static Dialog showSheet(Context context, final OnActionSheetSelected actionSheetSelected,
                                   OnCancelListener cancelListener) {
        final Dialog dlg = new Dialog(context, R.style.ActionSheet);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.actionsheetpic, null);
        final int cFullFillWidth = 10000;
        layout.setMinimumWidth(cFullFillWidth);
        TextView tv_camera = (TextView) layout.findViewById(R.id.tv_camera);
        TextView tv_photo = (TextView) layout.findViewById(R.id.tv_photo);
        TextView mCancel = (TextView) layout.findViewById(R.id.cancel);
        tv_camera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                actionSheetSelected.onClick(6);
                dlg.dismiss();
            }
        });
        tv_photo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                actionSheetSelected.onClick(7);
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
