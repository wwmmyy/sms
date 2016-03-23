package com.dist.client.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class UtilTool {
	 /**
     * 发送消息到服务端，获取到服务器端返回的字符串
     * 
     * @param params
     *            请求参数
     * @param encode
     *            编码格式
     * @return
     * @throws Exception 
     */
    public static String getInfoFromServer(String PATH, Map<String, String> params) throws Exception {
////      把设备的坐标也传过去
//      params.put("latitude", DistApp.latitude);
//      params.put("longitude", DistApp.longitude);
//      params.put("radius", DistApp.Radius);
//      app标示号
//       params.put("appidentify", "com.dist.iportal"); 
        
        StringBuilder stringBuilder = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                
                    stringBuilder.append(entry.getKey()).append("=")
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
               
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
           

                URL url = new URL(PATH);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(8000);
                urlConnection.setRequestMethod("POST"); // 以post请求方式提交
                urlConnection.setDoInput(true); // 读取数据
                urlConnection.setDoOutput(true); // 向服务器写数据
                //urlConnection.getContentLength();
                // 获取上传信息的大小和长度
                byte[] myData = stringBuilder.toString().getBytes();
                // 设置请求体的类型是文本类型,表示当前提交的是文本数据
                urlConnection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(myData.length));
                // 获得输出流，向服务器输出内容
                OutputStream outputStream = urlConnection.getOutputStream();
                // 写入数据
                outputStream.write(myData, 0, myData.length);
                outputStream.close();
                // 获得服务器响应结果和状态码
                int responseCode = urlConnection.getResponseCode();

                //urlConnection.getContentLength();

                if (responseCode == 200) {
                    // 取回响应的结果
                    //                                  return changeInputStream(urlConnection.getInputStream(),
                    //                                                  encode);

                    //return urlConnection.getInputStream();            

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] data = new byte[1024];
                    int len = 0;
                    String result = null;
                    if (urlConnection.getInputStream() != null) {
                        try {
                            while ((len = urlConnection.getInputStream().read(data)) != -1) {
                                byteArrayOutputStream.write(data, 0, len);
                            }
                            result = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                        return result; 
                }else if(responseCode == 401){
//                说明token已经过期
//                    Log.d("说明token已经过期", "过期");
//                    if(MakeToken.refreshToken()){//重新刷新token
////                        Log.d("刷新token成功", "刷新token成功");
//                        params.put("access_token", DistApp.access_token);
//                       return getInfoFromServer(PATH,params);//重新回调自身
//                    }
                }
        }
        return null;
    }
}
