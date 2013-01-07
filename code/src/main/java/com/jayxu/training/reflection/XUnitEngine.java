/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.reflection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ijay
 * 
 */
public class XUnitEngine {

    public static void main(String[] args) throws ClassNotFoundException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        XUnitClassLoader classLoader = new XUnitClassLoader();
        Class<?> c =
                classLoader.loadClass(
                "/Users/ijay/Documents/workspace/advanced_java_training/bin/com/nazca/training/reflection/MathTest.class");

        Method[] methods = c.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                m.invoke(c.newInstance(), new Object[0]);
            }
        }
    }

    private static class XUnitClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            File file = new File(name);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = null;

            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int read = -1;

                while ((read = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, read);
                }

                byte[] array = baos.toByteArray();

                return this.defineClass(this.convertClassName(name), array, 0,
                        array.length);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ClassNotFoundException();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private String convertClassName(String fileName) {
            int begin = fileName.indexOf("bin/") + 4;
            int end = fileName.indexOf(".class");

            return fileName.substring(begin, end).replace("/", ".");
        }
    }
}
