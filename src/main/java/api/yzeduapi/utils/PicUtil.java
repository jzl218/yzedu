package api.yzeduapi.utils;

import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PicUtil {
    public static String baseurl(String path) throws IOException {
        byte[] data = null;
        FileInputStream fileInputStream = FileUtils.openInputStream(new File(path));
        data=new byte[fileInputStream.available()];
        fileInputStream.read(data);
        BASE64Encoder encoder=new BASE64Encoder();
        return encoder.encode(data);
    }
}
