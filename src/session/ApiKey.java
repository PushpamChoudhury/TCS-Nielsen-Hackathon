/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

/**
 *
 * @author 510720
 */
public final class ApiKey {
    
    private static int lastApiKeyIndx = 0;
    
    ApiKey() {}
    
    public synchronized static String getApiKey() {
        lastApiKeyIndx = (lastApiKeyIndx < ValuesFinal.API_KEY.length-1) ? lastApiKeyIndx+1 : 0;
        return ValuesFinal.API_KEY[lastApiKeyIndx];
    }
}
