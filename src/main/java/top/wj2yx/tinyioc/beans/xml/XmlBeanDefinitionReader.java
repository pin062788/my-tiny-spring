package top.wj2yx.tinyioc.beans.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import top.wj2yx.tinyioc.BeanReference;
import top.wj2yx.tinyioc.beans.AbstractBeanDefinitionReader;
import top.wj2yx.tinyioc.beans.BeanDefinition;
import top.wj2yx.tinyioc.beans.PropertyValue;
import top.wj2yx.tinyioc.beans.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * bean定义读取类，从xml中读取相关信息
 * author: wang
 * date: 2018/5/5
 * time: 16:18
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        //解析bean
        registerBeanDefinitions(document);
        inputStream.close();
    }

    private void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        NodeList nl = root.getChildNodes();
        for(int i = 0; i < nl.getLength(); i++){
            Node node = nl.item(i);
            if(node instanceof Element){
                Element ele = (Element)node;
                processBeanDefinition(ele);
            }
        }
    }

    private void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNodes = ele.getElementsByTagName("property");
        for(int i = 0; i < propertyNodes.getLength(); i++){
            Node node = propertyNodes.item(i);
            if(node instanceof Element){
                Element propertyElement = (Element)node;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if(value != null && value.length() > 0){
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                }else{
                    String ref = propertyElement.getAttribute("ref");
                    if(ref == null || ref.length() == 0){
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
