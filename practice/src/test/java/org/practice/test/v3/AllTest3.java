package org.practice.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yeyulin
 * @description:
 * @date 2020/8/14 11:05
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationContextTestV3.class, BeanDefinitionTestV3.class, ConstructorResolverTestV3.class
})
public class AllTest3 {
}
