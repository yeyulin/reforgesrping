package org.practice.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.practice.beans.propertyeditors.CustomNumberEditor;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/23 16:06
 **/
public class CustomNumberEditorTest {
    @Test
    public void testConvertString() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class,true);

        editor.setAsText("3");
        Object value = editor.getValue();
        Assert.assertTrue(value instanceof Integer);
        Assert.assertEquals(3, ((Integer)editor.getValue()).intValue());

        editor.setAsText("");
        Assert.assertTrue(editor.getValue() == null);

        try{
            editor.setAsText("3.1");
        }catch(IllegalArgumentException e){
            System.out.println("expect Integer, "+e);
            return ;
        }
        Assert.fail();
    }
}
