package org.practice.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.practice.beans.BeanDefinition;
import org.practice.beans.factory.BeanDefinitionStoreException;
import org.practice.beans.factory.support.BeanDefinitionRegister;
import org.practice.beans.factory.support.GenericBeanDefinition;
import org.practice.core.io.Resource;
import org.practice.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class XmlBeanDefinitionReader {
    private BeanDefinitionRegister register;

    public XmlBeanDefinitionReader(BeanDefinitionRegister register) {
        this.register = register;
    }

    public void loadBeanDefinitions(Resource source) {
        InputStream is = null;
        try {
            is = source.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            //<beans>
            Element root = doc.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()) {
                Element ele = (Element) iter.next();
                String id = ele.attributeValue("id");
                String beanClassName = ele.attributeValue("class");
                String scope = ele.attributeValue("scope");
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                bd.setScope(scope);
                register.registerBeanDefinition(id, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + source, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
