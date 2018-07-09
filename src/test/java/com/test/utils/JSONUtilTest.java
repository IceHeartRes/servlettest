package com.test.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JSONUtilTest {
    private static final String TESTCLASS_JSON = "{\"field\":\"test\"}";
    private static final String TEST = "test";
    private TestClass testClass;

    @Before
    public void setUp() throws Exception {
        testClass = new TestClass();
        testClass.setField(TEST);
    }

    @Test
    public void toJSON() throws Exception {
        final String json = JSONUtil.toJSON(testClass);
        assertEquals(json, TESTCLASS_JSON);
    }

    @Test
    public void toJSONNull() throws Exception {
        final String json = JSONUtil.toJSON(null);
        assertNull(json);
    }

    @Test
    public void fromJSON() throws Exception {
        final TestClass testClass = JSONUtil.fromJSON(TESTCLASS_JSON, TestClass.class);
        assertEquals(testClass, this.testClass);
    }

    @Test
    public void fromJSONNull() throws Exception {
        final TestClass testClass = JSONUtil.fromJSON(null, TestClass.class);
        assertNull(testClass);
    }

    @Test
    public void fromJSONWrong() throws Exception {
        final TestClass testClass = JSONUtil.fromJSON("", TestClass.class);
        assertNull(testClass);
    }

    private static class TestClass {
        private String field;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        @Override
        public boolean equals(Object o) {
            final TestClass testClass = (TestClass) o;
            return field.equals(testClass.getField());
        }
    }
}
