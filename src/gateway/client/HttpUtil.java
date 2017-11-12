package gateway.client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
 
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import session.Session;
 
public class HttpUtil {
    
    private static HttpUtil httpUtil;
    static {
         try {
            TrustManager[] trustAllCerts = {new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
             
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                 
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }};
	     	
            SSLContext sc = SSLContext.getInstance("SSL");
             
            HostnameVerifier hv = new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {return true;}
            };
            sc.init(null, trustAllCerts, new SecureRandom());
             
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
	    System.out.println("NoSuchAlgorithmException/KeyManagementException: " + e);
        }
    }
 
    private HttpUtil() {}
 
    public static HttpUtil getHttpUtil() {
        if (httpUtil == null) {httpUtil = new HttpUtil();}
        return httpUtil;
    }

    public String doGet(Session ses, String url) {
        try {
            URL urlObj;
            HttpURLConnection conn;
            if(ValuesFinal.PROXY_REQUIRED) {
                SocketAddress addr = new InetSocketAddress(ValuesFinal.PROXY_HOST, ValuesFinal.PROXY_PORT);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
                urlObj = new URL(url);
                conn = (HttpURLConnection) urlObj.openConnection(proxy);
            } else {
                urlObj = new URL(url);
                conn = (HttpURLConnection) urlObj.openConnection();
            }
            conn.setDoOutput(true);
            String line;
            StringBuilder buffer = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
            }
            conn.disconnect();
            return buffer.toString();
        } catch (MalformedURLException e) {
            ses.addError("MalformedURLException: " + e);
        } catch (IOException e) {
            ses.addError("IOException: " + e);
        }
        return "";
    }
 
    public String doPost(String url, String data) throws Exception {
        URL urlObj;
        HttpURLConnection conn;
        if(ValuesFinal.PROXY_REQUIRED) {
            SocketAddress addr = new InetSocketAddress(ValuesFinal.PROXY_HOST, ValuesFinal.PROXY_PORT);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
            urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection(proxy);
        } else {
            urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
        }
        conn.setDoOutput(true);
        conn.addRequestProperty("Content-Type", "application/xml");
        BufferedReader reader;
        try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
            writer.write(data);
            writer.flush();
            String line;
            StringBuilder buffer = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }
        reader.close();
        int status = conn.getResponseCode();
        conn.disconnect();
        return status + "";
    }
}