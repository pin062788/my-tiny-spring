package top.wj2yx.tinyioc.beans.io;



import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * author: wang
 * date: 2018/5/5
 * time: 13:56
 */
//对应于一个具体的Url资源
public class UrlResource implements Resource{
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
