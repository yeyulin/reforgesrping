package org.practice.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.practice.test.v1.AllTestV1;
import org.practice.test.v2.AllTestV2;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/17 16:25
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({AllTestV1.class, AllTestV2.class,})
public class AllTest {


}
