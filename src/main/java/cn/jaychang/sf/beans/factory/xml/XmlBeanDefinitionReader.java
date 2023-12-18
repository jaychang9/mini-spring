package cn.jaychang.sf.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.PropertyValue;
import cn.jaychang.sf.beans.PropertyValues;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;
import cn.jaychang.sf.beans.factory.config.BeanReference;
import cn.jaychang.sf.beans.factory.support.AbstractBeanDefinitionReader;
import cn.jaychang.sf.beans.factory.support.BeanDefinitionRegistry;
import cn.jaychang.sf.core.io.Resource;
import cn.jaychang.sf.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * 读取配置在xml文件中的bean定义信息
 *
 * @author jaychang
 * @since 2023/12/15
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }


    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {

        try (
                InputStream inputStream = resource.getInputStream();
        ) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            Element beanElement = (Element) childNodes.item(i);
            if (!BEAN_ELEMENT.equals(beanElement.getNodeName())) {
                continue;
            }
            String id = beanElement.getAttribute(ID_ATTRIBUTE);
            String name = beanElement.getAttribute(NAME_ATTRIBUTE);
            String beanName = StrUtil.isNotBlank(id) ? id : name;
            String className = beanElement.getAttribute(CLASS_ATTRIBUTE);
            BeanDefinition beanDefinition = createBeanDefinition(beanElement, className);
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private BeanDefinition createBeanDefinition(Element beanElement, String className) {
        BeanDefinition beanDefinition;
        try {
            beanDefinition = new BeanDefinition(Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new BeansException(String.format("Class[%s] not found", className));
        }
        PropertyValues propertyValues = new PropertyValues();
        for (int j = 0; j < beanElement.getChildNodes().getLength(); j++) {
            if (!(beanElement.getChildNodes().item(j) instanceof Element)) {
                continue;
            }
            Element propertyElement = (Element) beanElement.getChildNodes().item(j);
            if (!PROPERTY_ELEMENT.equals(propertyElement.getNodeName())) {
                continue;
            }
            String proName = propertyElement.getAttribute(NAME_ATTRIBUTE);
            String proValue = propertyElement.getAttribute(VALUE_ATTRIBUTE);
            String proRef = propertyElement.getAttribute(REF_ATTRIBUTE);
            PropertyValue propertyValue;
            if (StrUtil.isNotBlank(proValue)) {
                propertyValue = new PropertyValue(proName, proValue);
            } else {
                propertyValue = new PropertyValue(proName, new BeanReference(proRef));
            }
            propertyValues.addPropertyValue(propertyValue);
            beanDefinition.setPropertyValues(propertyValues);
        }
        return beanDefinition;
    }

}
