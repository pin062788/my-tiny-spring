package top.wj2yx.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * author: wang
 * date: 2018/5/5
 * time: 13:54
 */
//对应于一个外部资源
public interface Resource {
    InputStream getInputStream() throws IOException; //得到资源的输入流
}
