package com.joysus.constant;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.joysus.okhttp.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/3.
 */
public class ContantsUrl {
    public static String BASE_URL = "http://121.42.174.46/yueyuehui/";

    public static String URL_LOGIN = BASE_URL + "mem/loginUser";

    public static String URL_REGISTER = BASE_URL + "mem/add";

    public static String URL_RESERPWD = BASE_URL + "mem/resetPwd";

    public static String URL_BANNER = BASE_URL + "banner/getBannerList";
    //获取城市列表接口
    public static String URL_CITYLIST = BASE_URL + "city/getCityList";
    //发送验证码接口
    public static String URL_SENDSMS = BASE_URL + "mem/sendSms";
    //第一次登陆获取奖励金额
    public static String URL_PROMOTION = BASE_URL + "config/getConfigByName/promotion";
    //获取首页行程列表
    public static String URL_TRIPLIST = BASE_URL + "trip/getTripList";
    //排行榜
    public static String URL_TRIPORDER = BASE_URL + "trip/getTripOrderList";
    //获取所有线路
    public static String URL_LINELIST = BASE_URL + "line/getLineList";
    //发起众筹
    public static String URL_CROWDFUNDING = BASE_URL + "trip/add";
    //获取所有的线路详情【众筹详情】
    public static String URL_LINE = BASE_URL + "line/getLineById";
    //线路详情页面
    public static String URL_LINE_DETAIL = BASE_URL + "admin/pages/showLine.jsp";
    //注册协议界面
    public static String URL_LICENSE = BASE_URL + "admin/pages/license.jsp";

    //新手入门界面
    public static String URL_HELPNEW = BASE_URL + "admin/pages/helpNew.jsp";

    //退出登录
    public static String URL_LOGOUT = BASE_URL + "line/getLineList";

    /**
     *
     *  private void LoginUser() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("username", "123");
     params.put("password", "123");
     String url = "http://121.42.174.46/yueyuehui/mem/loginUser";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    CommonResult emm = JSONObject.toJavaObject(object,
    CommonResult.class);
    Log.d("LOGIN", "登录");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     private void register() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("mobile", "123");
     params.put("vcode", "123");
     params.put("password", "123");
     String url = "http://121.42.174.46/yueyuehui/mem/add";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    CommonResult emm = JSONObject.toJavaObject(object,
    CommonResult.class);
    Log.d("LOGIN", "注册");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 有问题 http500 更新资料
     private void updateMember() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("headpic", "");
     params.put("username", "123");
     params.put("personal_desc", "123");
     params.put("sex", "男");
     params.put("address", "江苏南京");
     params.put("id_card", "");
     params.put("passport", "");
     String url = "http://121.42.174.46/yueyuehui/mem/updateMember";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    CommonResult emm = JSONObject.toJavaObject(object,
    CommonResult.class);
    Log.d("LOGIN", "编辑");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     private void resetpwd() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("mobile", "123");
     params.put("vcode", "123");
     params.put("password", "123");
     String url = "http://121.42.174.46/yueyuehui/mem/resetPwd";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    CommonResult emm = JSONObject.toJavaObject(object,
    CommonResult.class);
    Log.d("LOGIN", "重置密码");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     private void CitySelect() {
     String url = "http://121.42.174.46/yueyuehui/city/getCityList";
     OkHttpUtil.post(url, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
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

    Log.d("LOGIN", "城市选择");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {
    Log.d("LOGIN", e.getMessage());
    }
    });
     }

     private void BannerList() {
     String url = "http://121.42.174.46/yueyuehui/banner/getBannerList";
     OkHttpUtil.post(url, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
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
    ArrayList<Banner> cm = new ArrayList<Banner>();
    for (int i = 0; i < obj.size(); i++) {
    JSONObject jo = (JSONObject) obj.get(i);
    cm.add(obj.toJavaObject(jo, Banner.class));
    }

    Log.d("LOGIN", "城市选择");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {
    Log.d("LOGIN", e.getMessage());
    }
    });
     }

     private void TripList() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("orderby", "create_time");
     params.put("pageSize", "3");
     params.put("pageNo", "1");
     String url = "http://121.42.174.46/yueyuehui/trip/getTripList";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
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
    ArrayList<TripModel> cm = new ArrayList<TripModel>();
    for (int i = 0; i < obj.size(); i++) {
    JSONObject jo = (JSONObject) obj.get(i);
    cm.add(obj.toJavaObject(jo, TripModel.class));
    }
    Log.d("LOGIN", "首页行程");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 有问题 http500 上传图片
     private void picupload() {
     // 图片上传
     File f = new File("/sdcard/wifi_config.log");
     Map<String, String> params = new HashMap<String, String>();
     params.put("pathType", "类型");
     // 图片上传接口
     OkHttpUtil.postFile(
     "http://121.42.174.46/yueyuehui/file/uploadImages/user", f,
     params, "pathType", new Callback() {

    @Override public void onResponse(Call arg0, Response arg1)
    throws IOException {
    if (arg1.isSuccessful()) {
    if (arg1.code() == 200) {
    Log.d("UPLOAD", arg1.body().toString());
    }
    }
    }

    @Override public void onFailure(Call arg0, IOException arg1) {
    Log.d("UPLOAD", arg1.getMessage().toString());
    }
    });
     }

     // 发送验证码
     private void sendcode() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("mobile", "123");
     params.put("vtype", "1");
     String url = "http://121.42.174.46/yueyuehui/ mem/sendSms";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    CommonResult emm = JSONObject.toJavaObject(object,
    CommonResult.class);
    Log.d("LOGIN", "发送验证码");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 退出登录 405
     private void logout() {
     String url = "http://121.42.174.46/yueyuehui/mem/logout";
     OkHttpUtil.post(url, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    CommonResult emm = JSONObject.toJavaObject(object,
    CommonResult.class);
    Log.d("LOGIN", "退出登录");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {
    Log.d("ERROR", "退出登录");
    }
    });
     // 注册协议界面 http://121.42.174.46/yueyuehui/admin/pages/license.jsp

     // 新手入门界面 http://121.42.174.46/yueyuehui/admin/pages/helpNew.jsp
     }

     // 获取每天第一次登录奖励金额
     private void promotion() {
     String url = "http://121.42.174.46/yueyuehui/config/getConfigByName/promotion";
     OkHttpUtil.post(url, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    String data = object.getString("data");
    JSONObject object1 = JSONObject.parseObject(data);
    try {
    EveryMoneyModel emm = JSONObject.toJavaObject(object1,
    EveryMoneyModel.class);
    Log.e("meitianyici", emm.toString());
    } catch (Exception e) {
    Log.e("meitianyici", e.getMessage().toString());
    }
    Log.d("LOGIN", "每天第一次登录奖励金额");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 获取所有线路
     private void getLineList() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("pageSize", "5");
     params.put("pageNo", "1");
     String url = "http://121.42.174.46/yueyuehui/line/getLineList";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
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
    ArrayList<RounteLineModel> cm = new ArrayList<RounteLineModel>();
    for (int i = 0; i < obj.size(); i++) {
    JSONObject jo = (JSONObject) obj.get(i);
    cm.add(obj.toJavaObject(jo, RounteLineModel.class));
    }
    Log.d("LOGIN", "获取所有线路");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 获取所有的线路详情【众筹详情】

     private void getLineById() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("id", "31");
     String url = "http://121.42.174.46/yueyuehui/line/getLineById";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    String pString = object.getString("data");
    JSONObject object1 = JSONObject.parseObject(pString);
    RounteLineModel rmm = JSONObject.toJavaObject(object1,
    RounteLineModel.class);

    Log.d("LOGIN", "线路详情【众筹详情】");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });

     }

     // 线路项目详情页面
     private void LineProDetail() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("id", "31");
     params.put("col", "project_desc");
     String url = "http://121.42.174.46/yueyuehui/admin/pages/showLine.jsp";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();

    Log.d("LOGIN", "线路详情【众筹详情】");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });

     }

     // 发起众筹 HTTP500
     private void Crowd_funding() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("username", "31");
     params.put("departure", "project_desc");
     params.put("destination", "31");
     params.put("price", "10");
     params.put("startTime", System.currentTimeMillis() + "");
     params.put("endTime", (System.currentTimeMillis() + 500000) + "");
     params.put("lineId", "31");
     params.put("memberId", "123");
     params.put("status", "1");
     params.put("createTime", System.currentTimeMillis() + "");
     String url = "http://121.42.174.46/yueyuehui/trip/add";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    CommonResult emm = JSONObject.toJavaObject(object,
    CommonResult.class);
    Log.d("LOGIN", "发起众筹");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 我要报名
     private void EnterForMe() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("lineId", "31");
     params.put("memberId", "123");
     String url = "http://121.42.174.46/yueyuehui/joinline/add";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    CommonResult emm = JSONObject.toJavaObject(object,
    CommonResult.class);
    Log.d("LOGIN", "我要报名");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 19意见反馈 http500
     private void feedback() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("memberId", "123");
     params.put("content", "测试意见");
     params.put("createtime", "2016-07-07");
     String url = "http://121.42.174.46/yueyuehui/feedback/add";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    CommonResult emm = JSONObject.toJavaObject(object,
    CommonResult.class);
    Log.d("LOGIN", "意见反馈");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 20上传证件 (涉及到文件 先放一放)
     private void uploadzhengjian() {

     }

     // 21排行榜
     private void TripOrderList() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("startTime", "2015-09-01");
     params.put("endTime", "2015-12-01");
     params.put("pageSize ", "3");
     params.put("pageNo ", "1");
     String url = "http://121.42.174.46/yueyuehui/trip/getTripOrderList";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
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
    ArrayList<TripOrder> cm = new ArrayList<TripOrder>();
    for (int i = 0; i < obj.size(); i++) {
    JSONObject jo = (JSONObject) obj.get(i);
    cm.add(obj.toJavaObject(jo, TripOrder.class));
    }
    Log.d("LOGIN", "排行榜");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 22.查询会员发起的众筹 http500
     private void SearchMemberFunding() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("username", "123");
     params.put("pageSize", "3");
     params.put("pageNo ", "1");
     String url = "http://121.42.174.46/yueyuehui/trip/getTripList";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
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
    ArrayList<MemberFund> cm = new ArrayList<MemberFund>();
    for (int i = 0; i < obj.size(); i++) {
    JSONObject jo = (JSONObject) obj.get(i);
    cm.add(obj.toJavaObject(jo, MemberFund.class));
    }
    Log.d("LOGIN", "排行榜");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 23.评价列表【收到的评论和做出的评论】
     private void commentFromTo() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("MemberId", "373");
     params.put("authId", "185");
     params.put("pageSize ", "3");
     params.put("pageNo ", "1");
     String url = "http://121.42.174.46/yueyuehui/comment/getCommentList";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
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
    ArrayList<Comment> cm = new ArrayList<Comment>();
    for (int i = 0; i < obj.size(); i++) {
    JSONObject jo = (JSONObject) obj.get(i);
    cm.add(obj.toJavaObject(jo, Comment.class));
    }
    Log.d("LOGIN", "评价列表");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }

     // 24．根据用户id获取获取用户详情
     private void getUserById() {
     Map<String, String> params = new HashMap<String, String>();
     params.put("id", "373");
     String url = "http://121.42.174.46/yueyuehui/comment/getCommentList";
     OkHttpUtil.postFile(url, null, params, null, new Callback() {
    @Override public void onResponse(Call call, Response response)
    throws IOException {
    // 失败
    if (!response.isSuccessful())
    throw new IOException("Unexpected code " + response);
    // 获取code
    if (response.code() == 200) {
    String result = response.body().string();
    JSONObject object = JSONObject.parseObject(result);
    String pString = object.getString("data");
    JSONObject object1 = JSONObject.parseObject(pString);
    User emm = JSONObject.toJavaObject(object1, User.class);

    Log.d("LOGIN", "评价列表");
    } else {

    }
    }

    @Override public void onFailure(Call call, IOException e) {

    }
    });
     }
     */
    //25收藏列表【收藏的项目，会员】
    public void getCollectList()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("memberid", "1");
        params.put("ctype","1");
//        params.put("ctype","2");
        params.put("pageSize","3");
        params.put("pageNo","1");
        String url = "http://121.42.174.46/yueyuehui/action/getCollectList";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "收藏列表【收藏的项目，会员】");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //26.参与支持
    public void getJoinAndSupport()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("tripId", "1");
        params.put("money","3");
        params.put("tripMemeberId","3");
        params.put("memberId","1");
        String url = "http://121.42.174.46/yueyuehui/support/add";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "参与支持");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //27.众筹详情页面（从首页列表的详情）
    public void CrundFundingForHomePage()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        String url = "http://121.42.174.46/yueyuehui/trip/getTripById";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "众筹详情页面（从首页列表的详情）");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //28.爱心天使
    public void LoveAngle()
    {
        String url = "http://121.42.174.46/yueyuehui/admin/pages/support_list.jsp?id=122";
        OkHttpUtil.get(url, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "爱心天使");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //29.发表评价
    public void SubmitEvalute()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("memberId", "1");
        params.put("authId", "2");
        params.put("content", "测试测试");
        params.put("star", "122");
        params.put("time", "2016-07-01");
        String url = "http://121.42.174.46/yueyuehui/support/add";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "发表评价");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //30.众筹评论列表
    public void CrundFundingList()
    {
        String url = "http://域名/admin/pages/comment_list.jsp?id=123";
        OkHttpUtil.get(url, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "众筹评论列表");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //31.全额付款
    public void AllMoneypay()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("tripId", "1");
        params.put("memberId", "2");
        params.put("name", "测试");
        params.put("username", "测试");
        //params.put("payWay", "2");
        params.put("payWay", "1");
        params.put("payCount", "1");
        params.put("money", "0.1");
        params.put("status", "1");
        params.put("lotterytime", "2016-07-01");
        params.put("pay_flag", "1");
        params.put("myNumber", "46525");
        params.put("createTime", "2016-07-01");
        String url = "http://121.42.174.46/yueyuehui/order/add";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "全额付款");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //32.添加收藏
    public void addCollect()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("collectId", "1");
//        params.put("collectType", "1");
        params.put("collectType", "2");
        params.put("memberId", "1");
        String url = "http://121.42.174.46/yueyuehui/ action/addCollect";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "添加收藏");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //33.确认付款
    public void MakeSurePay()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Ordered", "1");
        params.put("payFlag", "1");
        String url = "http://121.42.174.46/yueyuehui/order/add";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "确认付款");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //34.获取好友列表
    public void getFriendList()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", "1");
        params.put("pageSize", "5");
        params.put("pageNo", "1");
        String url = "http://121.42.174.46/yueyuehui/friend/getFriendListById";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "好友列表");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //35.添加好友
    public void AddFriend()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("memberId", "1");
        params.put("friendMemberId", "5");
        String url = "http://121.42.174.46/yueyuehui/friend/add";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "添加好友");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //36.验证双方是否是好友
    public void isFriend()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("memberId", "1");
        params.put("friendMemberId", "5");
        String url = "http://121.42.174.46/yueyuehui/friend/isFriend";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Log.d("LOGIN", "验证双方是否是好友");
                } else {

                }
            }

            @Override public void onFailure(Call call, IOException e) {

            }
        });
    }
    //36,37接口不需要 已经保存到本地

}
