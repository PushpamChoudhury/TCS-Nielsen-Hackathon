/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class Session {
    private final ArrayList<String> errorDesc = new ArrayList<>();
    private final ArrayList<String> appErrorDesc = new ArrayList<>();
    private boolean hasError = false;
    private boolean hasAppError = false;
    
    public Session() {}
    
    public void addError(String error) {
        this.hasError = true;
        errorDesc.add(error);
        System.out.println("*********** Error ***************");
        System.out.println(error);
        System.out.println("**** Stack ****");
        System.out.println(util.generic.StackTrace.getStackTrace());
    }
    
    public void addAppError(String error) {
        this.hasAppError = true;
        appErrorDesc.add(error);
        System.out.println("*********** Application Error ***************");
        System.out.println(error);
        System.out.println("**** Stack ****");
        System.out.println(util.generic.StackTrace.getStackTrace());
    }
    
    public boolean hasError() {
        return this.hasError;
    }
    
    public boolean hasAppError() {
        return this.hasAppError;
    }
    
    public ArrayList<String> getErrorList() {
        return this.errorDesc;
    }
    
    public ArrayList<String> getAppErrorList() {
        return this.appErrorDesc;
    }
    
    public String getApiKey() {
        return ApiKey.getApiKey();
    }
}
