/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util.generic;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author 510720
 */
public final class StackTrace {
    
    public synchronized static String getStackTrace() {
        StringBuilder sb = new StringBuilder(1024); 
        for (StackTraceElement stackTrace : Thread.currentThread().getStackTrace()) {
            sb.append(stackTrace.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }    
}
