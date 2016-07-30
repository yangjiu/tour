package com.joysus.acitivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joysus.R;
import com.joysus.adapter.SortAdapter;
import com.joysus.constant.ContantsUrl;
import com.joysus.entity.CityModel;
import com.joysus.entity.SortModel;
import com.joysus.fragment.HomeFragment;
import com.joysus.jpview.SideBar;
import com.joysus.okhttp.OkHttpUtil;
import com.joysus.util.CharacterParser;
import com.joysus.util.PinyinComparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * 城市选择
 */

public class CitySelectedActivity extends BaseActivity implements View.OnClickListener {
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog, tv_title;
    private SortAdapter adapter;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private ImageButton btnclose;
    private final static int TRIP_SUCCESS = 0;
    private final static int TRIP_CODE = 1;
    private final static int TRIP_SERVER = 2;
    private final static int TRIP_NET = 3;
    // handler 统一处理消息
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case TRIP_SUCCESS:
                    ArrayList<CityModel> cms = (ArrayList<CityModel>) msg.obj;
                    SourceDateList = filledData(cms);
                    // 根据a-z进行排序源数据
                    Collections.sort(SourceDateList, pinyinComparator);
                    adapter = new SortAdapter(CitySelectedActivity.this, SourceDateList);
                    sortListView.setAdapter(adapter);
                    break;
                case TRIP_CODE:
                case TRIP_SERVER:
                    Toast.makeText(context, "服务器错误,代码：" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case TRIP_NET:
                    Toast.makeText(context, "数据获取失败，请检查网络连接情况", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_selected);
        initView();
        getCityList();
    }

    private void initView() {
        btnclose = (ImageButton) findViewById(R.id.btnclose);
        btnclose.setOnClickListener(this);
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("当前城市-" + this.getIntent().getStringExtra("city"));
        sideBar.setTextView(dialog);

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });
        sortListView = (ListView) findViewById(R.id.lv_city);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                if (null != HomeFragment.self) {
                    HomeFragment.self.setCity(((SortModel) adapter.getItem(position)).getName());
                    finish();
                }
//              Toast.makeText(CitySelectedActivity.this, ((SortModel) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCityList() {
        OkHttpUtil.get(ContantsUrl.URL_CITYLIST, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(TRIP_NET);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    JSONObject object = JSONObject.parseObject(result);
                    String pString = object.getString("data");
                    JSONObject object1 = JSONObject.parseObject(pString);
                    String pString1 = object1.getString("list");
                    JSONArray obj = new JSONObject().parseArray(pString1);
                    ArrayList<CityModel> cm = new ArrayList<CityModel>();
                    for (int i = 0; i < obj.size(); i++) {
                        JSONObject jo = (JSONObject) obj.get(i);
                        cm.add(obj.toJavaObject(jo, CityModel.class));
                    }
                    Message msg = new Message();
                    msg.what = TRIP_SUCCESS;
                    msg.obj = cm;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = TRIP_CODE;
                    msg.obj = response.code();
                    handler.sendMessage(msg);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnclose:
                finish();
                break;
        }
    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(ArrayList<CityModel> date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.get(i).getName());
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }
}
