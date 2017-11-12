/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gateway.client;

import java.util.ArrayList;
import session.Session;

/**
 *
 * @author acer
 */
public class Client {

    public static class Http {
        public static void get (Session ses, String attacherId, String httpScheme, String urlHome, String urlCat, ArrayList urlQueryList) {
            if(!ses.hasError()) {new Thread((new GetHttpHelper(ses, attacherId, httpScheme, urlHome, urlCat, urlQueryList))).start();}
        }
    }
}
