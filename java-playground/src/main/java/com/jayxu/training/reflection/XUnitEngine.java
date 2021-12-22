/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.reflection;

import java.util.Arrays;

import org.apache.commons.io.IOUtils;

public class XUnitEngine {

    public static void main(String[] args) throws Exception {
        Class<?> c = new XUnitClassLoader()
            .loadClass("com/jayxu/training/reflection/MathTest.class");
        var cls = c.getConstructor().newInstance();

        Arrays.stream(c.getMethods())
            .filter(m -> m.isAnnotationPresent(Test.class)).forEach(m -> {
                try {
                    m.invoke(cls);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

    private static class XUnitClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name)
                throws ClassNotFoundException {
            try (var is = this.getResourceAsStream(name);) {
                var array = IOUtils.toByteArray(is);

                return this.defineClass(XUnitClassLoader.convertClassName(name),
                    array, 0,
                    array.length);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        private static String convertClassName(String fileName) {
            return fileName.substring(0, fileName.indexOf(".class"))
                .replace("/", ".");
        }
    }
}
