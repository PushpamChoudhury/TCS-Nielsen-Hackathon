/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attach;

/**
 *
 * @author acer
 */
public class Response {
    String status = null;
    String data = null;
    String url = null;
    
    public String getStatus() {
        return this.status;
    }
    
    public String getData() {
        return this.data;
    }
    
    public void addStatus(String status) {
        this.status = status;
    }
    
    public void addData(String data) {
        this.data = data;
    }
    
    public void addURL(String url) {
        this.url = url;
    }
}
