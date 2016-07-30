package com.joysus.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
/**
 * HTTP工具类
 * @author howso
 *
 */
public class HttpClientUtil {
	//设置超时时间
	private final static int TIMEOUT = 30000;
	//发送post请求
	public static String getHttp(String url, List<BasicNameValuePair> params) {
		String result = "";
		try {
			HttpPost postMethod = new HttpPost(url);
			if (params != null)
				postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 将参数填入POST
			HttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT);
			HttpResponse response = httpClient.execute(postMethod); // 执行POST方法
			int code = response.getStatusLine().getStatusCode();
			if (code != 200) {
				result = "Exception";
			} else {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
			//请求连接超时
		} catch (ConnectTimeoutException e) {
			result = "Timeout";
			//其他异常
		} catch (Exception e) {
			result = "Exception";
		}
		return result;
	}
	public static String getHttp(String url) {
		String result = "";
		try {
			HttpGet geMethod=new HttpGet(url);
			HttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT);
			HttpResponse response = httpClient.execute(geMethod); // 执行POST方法
			int code = response.getStatusLine().getStatusCode();
			if (code != 200) {
				result = "Exception";
			} else {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
			//请求连接超时
		} catch (ConnectTimeoutException e) {
			result = "Timeout";
			//其他异常
		} catch (Exception e) {
			result = "Exception";
		}
		return result;
	}
	//请求byte数据
	public static byte[] getHttpByByte(String url,
			List<BasicNameValuePair> params) {
		byte[] result = null;
		try {
			HttpPost postMethod = new HttpPost(url);
			if (params != null)
				postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 将参数填入POST
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(postMethod); // 执行POST方法
			HttpEntity httpEntity = response.getEntity();
			result = EntityUtils.toByteArray(httpEntity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @param targetfile
	 * @param handler
	 * @param isRunning
	 * @return 0 下载完成，1 下载失败
	 */
	public static int downFile(String url, File targetfile,
			List<BasicNameValuePair> params) {
		if (targetfile == null) {
			return 1;
		}

		HttpClient client = new DefaultHttpClient();
		if (TextUtils.isEmpty(url)) {
			return 1;
		}
		HttpResponse response;
		try {
			HttpPost postMethod = new HttpPost(url);
			if (null != params) {
				postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 将参数填入POST
			}
			response = client.execute(postMethod);
			// 请求成功
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				HttpEntity entity = response.getEntity();

				long length = entity.getContentLength();
				Log.i("legth", length + "");
				InputStream input = entity.getContent();
				FileOutputStream fileOutputStream = null;
				if (input != null) {
					if (targetfile.exists()) {
						targetfile.delete();
					}
					fileOutputStream = new FileOutputStream(targetfile);
					byte[] b = new byte[1024 * 5];
					int charb = -1;
					long count = 0;
					while ((charb = input.read(b)) != -1) {
						fileOutputStream.write(b, 0, charb);
						count += charb;
						// if (length != 0) {
						// int jindu = (int) (count * 100 / length);
						// }
					}
				} else {
					return 1;
				}

				fileOutputStream.flush();
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			}
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}

	/**
	 * 下载图片
	 * 
	 * @param url
	 * @param targetfile
	 * @param handler
	 * @param isRunning
	 * @return 0 下载完成，1 下载失败
	 */
	public static int downImg(String url, File targetfile, Handler handler,
			boolean isRunning) {
		if (targetfile == null) {
			return 1;
		}

		HttpClient client = new DefaultHttpClient();
		if (TextUtils.isEmpty(url)) {
			return 1;
		}

		HttpGet get = new HttpGet(url);

		HttpResponse response;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();

			InputStream input = entity.getContent();
			FileOutputStream fileOutputStream = null;
			if (input != null) {
				if (targetfile.exists()) {
					targetfile.delete();
				}

				fileOutputStream = new FileOutputStream(targetfile);
				byte[] buffer = new byte[1024];
				int len = 0;
				if (isRunning) {
					while ((len = input.read(buffer)) != -1) {
						if (!isRunning) {
							return 1;
						}
						fileOutputStream.write(buffer, 0, len);
					}
				} else {
					return 1;
				}
			} else {
				return 1;
			}

			fileOutputStream.flush();
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}

			return 0;
		} catch (Exception e) {
			return 1;
		}
	}
	/**
	 * 下载文件
	 * @param urlString
	 * @param filename
	 * @param exname
	 */
	public static void DownFile(String urlString, String filename, String exname) {
		InputStream inputStream = null;
		URLConnection connection = null;
		OutputStream outputStream = null;
		try {

			URL url = new URL(
					urlString
							+ "?baseStationDBUpdateAtion={\"DBversionTime\":\"1448337292\"}");
			connection = url.openConnection();
			connection.setRequestProperty("Accept-Encoding", "identity");
			if (connection.getReadTimeout() == 5) {
				Log.i("---------->", "当前网络有问题");
				return;
			}
			inputStream = connection.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/**
		 * 文件的保存路径 如果不存在则创建路径
		 * */
		String savePAth = Environment.getExternalStorageDirectory()
				+ "/SSS/DataBase";
		File file1 = new File(savePAth);
		if (!file1.exists()) {
			file1.mkdirs();
		}
		String savePathString = Environment.getExternalStorageDirectory()
				+ "/SSS/DataBase/" + filename + exname;
		// FileSavePath = savePathString;// 存储当前 文件所保存额SD的位置
		File file = new File(savePathString);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int FileLength = 0;
		int DownedFileLength = 0;
		try {
			outputStream = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			FileLength = connection.getContentLength();
			// message.what = DOWN_START;
			// handler.sendMessage(message);
			int temp = 0;
			while (DownedFileLength < FileLength) {
				temp = inputStream.read(buffer);
				outputStream.write(buffer, 0, temp);
				DownedFileLength += temp;
				Log.i("-------->", DownedFileLength + "");
				// Message message1 = new Message();
				// message1.what = DOWN_HALF;
				// handler.sendMessage(message1);
			}
			inputStream.close();
			outputStream.flush();
			outputStream.close();
			// Message message2 = new Message();
			// message2.what = DOWN_END;
			// handler.sendMessage(message2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
