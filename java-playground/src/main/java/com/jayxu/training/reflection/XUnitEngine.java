/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.reflection;

import java.util.Arrays;

public class XUnitEngine {

    private static class XUnitClassLoader extends ClassLoader {
        private static String convertClassName(String fileName) {
            return fileName.substring(0, fileName.indexOf(".class")).replace("/", ".");
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try (var is = this.getResourceAsStream(name)) {
                var array = is.readAllBytes();

                return this.defineClass(XUnitClassLoader.convertClassName(name), array, 0, array.length);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ClassNotFoundException();
            }
        }
    }

    static void main(String[] args) throws Exception {
        var c = new XUnitClassLoader().loadClass("com/jayxu/training/reflection/MathTest.class");
        var cls = c.getConstructor().newInstance();

        Arrays.stream(c.getMethods()).filter(m -> m.isAnnotationPresent(Test.class)).forEach(m -> {
            try {
                m.invoke(cls);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
