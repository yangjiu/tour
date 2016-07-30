package com.joysus.okhttp;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp监听接口
 * 
 * @author qiuheng
 * 
 */
public interface OkhttpListener {
	public void requestOk(Response rsponser);

	public void requestError(Request request, IOException e);
}
