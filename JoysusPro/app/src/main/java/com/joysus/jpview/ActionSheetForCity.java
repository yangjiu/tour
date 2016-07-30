package com.joysus.jpview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.joysus.R;

/**
 * Created by howso on 2016.06.28.
 */

public class ActionSheetForCity {
    private static final String[] province = new String[]{
            "江苏省", "安徽省", "山东省", "浙江省", "河南省"
    };
    private static final String[] citys = new String[]{
            "南京市", "苏州市", "无锡市", "常州市", "镇江市"
    };
    private static final String[] area = new String[]{
            "鼓楼区", "建邺区", "江宁区", "栖霞区", "秦淮区"
    };
    private static int TYPE = 2;

    private ActionSheetForCity() {
    }

    public static Dialog showSheet(final Context context, final ActionSheet.OnActionSheetSelected actionSheetSelected,
                                   DialogInterface.OnCancelListener cancelListener) {
        final Dialog dlg = new Dialog(context, R.style.ActionSheet);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.action_sheet_city, null);
        final int cFullFillWidth = 10000;
        layout.setMinimumWidth(cFullFillWidth);
        Button btnclose = (Button) layout.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TYPE = 2;
                actionSheetSelected.onClick(0);
                dlg.dismiss();
            }
        });
        LinearLayout provincelayout = (LinearLayout) layout.findViewById(R.id.provincelayout);
        LinearLayout citylayout = (LinearLayout) layout.findViewById(R.id.citylayout);
        LinearLayout arealayout = (LinearLayout) layout.findViewById(R.id.arealayout);
        LinearLayout streetlayout = (LinearLayout) layout.findViewById(R.id.streetlayout);
        final ListView list = (ListView) layout.findViewById(R.id.listcity);
        list.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, province));
        final TextView provincetxt = (TextView) layout.findViewById(R.id.provincetxt);
        final TextView citytxt = (TextView) layout.findViewById(R.id.citytxt);
        final TextView areatxt = (TextView) layout.findViewById(R.id.areatxt);
        final TextView streettxt = (TextView) layout.findViewById(R.id.streettxt);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (TYPE) {
                    case 2:
                        provincetxt.setText(province[position]);
                        break;
                    case 3:
                        citytxt.setText(citys[position]);
                        break;
                    case 4:
                        areatxt.setText(area[position]);
                        break;
                }
            }
        });
        provincelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                list.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, province));
                TYPE = 2;
                actionSheetSelected.onClick(2);
            }
        });
        citylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                list.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, citys));
                TYPE = 3;
                actionSheetSelected.onClick(3);
            }
        });
        arealayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                list.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, area));
                TYPE = 4;
                actionSheetSelected.onClick(4);
            }
        });
        streetlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TYPE = 5;
                actionSheetSelected.onClick(5);
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
