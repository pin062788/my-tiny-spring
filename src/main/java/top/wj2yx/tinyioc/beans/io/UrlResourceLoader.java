package top.wj2yx.tinyioc.beans.io;

import java.net.URL;

/**
 * author: wang
 * date: 2018/5/5
 * time: 19:52
 */
public class UrlResourceLoader implements ResourceLoader{
    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
