/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.url;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import session.Session;

/**
 *
 * @author acer
 */
public class QueryBuilder {
    
    public synchronized static URI buildGet(Session ses, String httpScheme, String urlHome, String urlCat, ArrayList urlQueryList) {
        try {
            if(ses.hasError()) {return null;}
            List<BasicNameValuePair> queryList = new ArrayList<>();
            Iterator it = urlQueryList.iterator();
            while(it.hasNext()) {
                Map<String, String> query = (HashMap<String, String>) it.next();          
                for (Map.Entry<String, String> entry : query.entrySet()) {
                    queryList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            httpScheme = (httpScheme.equalsIgnoreCase("def")) ? api.neilsen.Params.URL_SCHEME : httpScheme;
            urlHome = (urlHome.equalsIgnoreCase("def")) ? api.neilsen.Params.URL_HOME : urlHome;
            URI uri = URIUtils.createURI(httpScheme.toLowerCase(), urlHome, -1, urlCat,
                                     URLEncodedUtils.format(queryList, "UTF-8"), null);
            System.out.println(uri.toASCIIString());
            return uri;
        } catch (URISyntaxException ex) {
            ses.addError("URISyntaxException: " + ex);
        }
        return null;
    }   
}