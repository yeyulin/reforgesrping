package org.practice.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/28 9:52
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationContextTestV2.class, BeanDefinitionTestV2.class, BeanDefinitionValueResolverTest.class,
        CustomBooleanEditorTest.class, CustomNumberEditorTest.class, TypeConverterTest.class
})
public class AllTestV2 {

}
