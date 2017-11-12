/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author acer
 */
public class ConvertToString {
    
    public synchronized static String begin(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
}
