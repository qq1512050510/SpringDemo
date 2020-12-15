package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * @author PF14EBBQ
 */
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlName = url + "?" + param;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "/n" + line;
            }
        } catch (Exception e) {
            logger.error("发送GET请求出现异常！" + e);
			logger.error(e.toString(), e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
    			logger.error(ex.toString(), ex);
                ex.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
           // conn.setRequestProperty("Content-Type", "*/*;charset=UTF-8");
            // 设置通用的请求属性
           conn.setRequestProperty("accept", "*/*;charset=UTF-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("发送POST请求出现异常！" + e);
			logger.error(e.toString(), e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
    			logger.error(ex.toString(), ex);
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
	 * get请求
	 *
	 * @param url
	 * @param pair
	 * @return
	 */
	public static String doGetJson(String url, NameValuePair... pair) {
		logger.info("******doGetJson******:begin");
		String rep = "";
		try {
			HttpClient client = new HttpClient();
			logger.info("******doGetJson******:url="+url);
			GetMethod method = new GetMethod(url);
			method.setQueryString(pair);
			method.setRequestHeader("Content-Type", "application/json;charset=utf-8");
			// 读取超时时间
			int timeout = 10000;

			logger.info("******doGetJson******: timeout=" +timeout);
			// 设置连接超时时间
			client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			//设置读取数据超时时间
			client.getHttpConnectionManager().getParams().setSoTimeout(timeout);

			int status = client.executeMethod(method);
			logger.info("******doGetJson******:status="+status+",url="+url);
			if (status == 200) {
				rep = method.getResponseBodyAsString();
			}
			logger.info("******doGetJson******:rep="+rep+",url="+url);
			method.releaseConnection();
		} catch (Exception e) {
			logger.error("******doGetJson******请求失败：", e+",url="+url);
			e.printStackTrace();

			if(e instanceof SocketTimeoutException || e instanceof ConnectTimeoutException|| e instanceof ConnectException) {
				//如果接口超时
				rep = "";
			}
		}
		logger.info("******doGetJson******:end,rep="+rep);
		return rep;
	}

	/**
	 * Json格式发送POST请求
	 * @param url
	 * @param obj
	 * @return
	 * @date 2017年12月21日 下午1:07:18
	 * @author
	 */
	public static String doPostJson(String url,Object obj) {
		logger.info("******doPostJson******:begin");
		logger.info("******doPostJson******:url="+url);
		String rep = "";
		try {
			HttpClient client = new HttpClient();

			PostMethod method = new PostMethod(url);
			method.setRequestHeader("Content-type", "application/json; charset=UTF-8");
			String parseJSON = mapper.writeValueAsString(obj);
			logger.info("******doPostJson******：JSONUtil.parseJSON(paramsMap)="+parseJSON +",url="+url);
			if(!StringUtils.isEmpty(parseJSON)){
				 RequestEntity requestEntity =  new StringRequestEntity(parseJSON, "text/json; charset=UTF-8", "UTF-8");
				 method.setRequestEntity(requestEntity);
			}else {
				logger.info("******doPostJson******：参数为空");
				return rep;
			}

			int timeout = 30000;

			// 设置连接超时时间
			client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			// 读取超时时间  设置POST方法请求超时
			method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);

			int status = client.executeMethod(method);
			logger.info("******doPostJson******:返回结果status=：" + status +",url="+url);
			if (status == 200) {
				rep = method.getResponseBodyAsString();
			}
			logger.info("******doPostJson******:返回结果rep=：" + rep+",url="+url);
			method.releaseConnection();
		} catch (Exception e) {
			logger.error("******doPostJson******:请求失败：", e +",url="+url);
			e.printStackTrace();
			if(e instanceof SocketTimeoutException || e instanceof ConnectTimeoutException|| e instanceof ConnectException) {
				//如果接口超时
				rep = "";
			}
		}
		logger.info("******doPostJson******:end,rep="+rep);
		return rep;
	}



	private static ObjectMapper mapper = new ObjectMapper();
	public static String parseJSON(Map<String, Object> map){
		String returnStr = "";
        try {
			returnStr =  mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return returnStr;
	}
}
