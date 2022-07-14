package com.jayxu.playground.lang;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.collections.impl.factory.Maps;

import sun.misc.Unsafe; // NOPMD

/**
 * This class could be used for any object contents/memory layout printing.
 * Implementation referenced to
 * http://java-performance.info/memory-introspection-using-sun-misc-unsafe-and-reflection/
 *
 * @author jay
 */
public final class ClassIntrospector {
    /**
     * After _mark section of 8 byte
     */
    private static final int OOP_REF_OFFSET = 8;
    private static final Unsafe UNSAFE;
    /**
     * Size of any Object reference
     */
    private static final int OBJ_REF_SIZE;
    /**
     * Sizes of all primitive values
     */
    private static final Map<Class<?>, Integer> PRIM_TYPE_SIZES;
    //we need to keep track of already visited objects in order to support cycles in the object graphs
    private final IdentityHashMap<Object, Object> visited = new IdentityHashMap<>();

    static {
        try {
            var field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);

            OBJ_REF_SIZE = ClassIntrospector.UNSAFE
                .arrayIndexScale(Object[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        PRIM_TYPE_SIZES = Maps.mutable.withInitialCapacity(7);
        ClassIntrospector.PRIM_TYPE_SIZES.put(byte.class, 1);
        ClassIntrospector.PRIM_TYPE_SIZES.put(char.class, 2);
        ClassIntrospector.PRIM_TYPE_SIZES.put(int.class, 4);
        ClassIntrospector.PRIM_TYPE_SIZES.put(long.class, 8);
        ClassIntrospector.PRIM_TYPE_SIZES.put(float.class, 4);
        ClassIntrospector.PRIM_TYPE_SIZES.put(double.class, 8);
        ClassIntrospector.PRIM_TYPE_SIZES.put(boolean.class, 1);
    }

    /**
     * Get object information for any Java object. Do not pass primitives to
     * this method because they
     * will boxed and the information you will get will be related to a boxed
     * version of your value.
     *
     * @param obj
     *        Object to introspect
     * @param maxOutputLevel
     * @return Object info
     * @throws ReflectiveOperationException
     */
    public static ObjectInfo introspect(Object obj, int maxOutputLevel)
            throws ReflectiveOperationException {
        return new ClassIntrospector().introspect("<ROOT>",
            ClassIntrospector.OOP_REF_OFFSET, obj, obj.getClass(),
            maxOutputLevel);
    }

    public static ObjectInfo introspect(Object obj)
            throws ReflectiveOperationException {
        return new ClassIntrospector().introspect("<ROOT>",
            ClassIntrospector.OOP_REF_OFFSET, obj, null, -1);
    }

    private ClassIntrospector() {
        // EMPTY
    }

    private ObjectInfo introspect(String name, long offset, Object obj,
            Class<?> type, int maxOutputLevel)
            throws ReflectiveOperationException {
        if (obj == null) {
            return ObjectInfo.nullObjectInfo(name, offset, type);
        }

        if (type == null) {
            type = obj.getClass();
        }

        //will be set to true if we have already seen this object
        var isRecursive = this.visited.containsKey(obj);
        this.visited.put(obj, null);

        var arrayLen = 0;
        var arraySize = 0L;
        var baseOffset = 0L;
        if (type.isArray()) {
            arrayLen = Array.getLength(obj);
            arraySize = (long) ClassIntrospector.UNSAFE.arrayIndexScale(type)
                * arrayLen;
            baseOffset = ClassIntrospector.UNSAFE.arrayBaseOffset(type);
        }

        var root = new ObjectInfo(name, type, offset,
            ClassIntrospector.getObjSize(type), baseOffset, arrayLen,
            arraySize);
        root.setMaxOutputLevel(maxOutputLevel);

        if (!isRecursive) {
            if (ClassIntrospector.isObjectArray(type)) {
                //introspect object arrays
                var i = 0;
                for (Object item : (Object[]) obj) {
                    if (item != null) {
                        root.addChild(this.introspect(name + '[' + i + ']',
                            ClassIntrospector.OBJ_REF_SIZE, item,
                            item.getClass(), maxOutputLevel - 1));
                    }

                    i++;
                }
            } else {
                for (Field field : ClassIntrospector.getNonStaticFields(type)) {
                    field.setAccessible(true);
                    var value = field.get(obj);

                    root.addChild(this.introspect(field.getName(),
                        ClassIntrospector.UNSAFE.objectFieldOffset(field),
                        value,
                        value == null || field.getType().isPrimitive()
                            ? field.getType() : value.getClass(),
                        maxOutputLevel - 1));
                }
            }
        }

        root.snapshot();

        return root;
    }

    //get all fields for this class, including all superclasses fields
    private static List<Field> getNonStaticFields(Class<?> type) {
        if (type.isPrimitive()) {
            return Collections.emptyList();
        }

        List<Field> res = new LinkedList<>();
        for (Class<?> cur = type; cur != null
            && cur != Object.class; cur = cur.getSuperclass()) {
            Collections.addAll(res, cur.getDeclaredFields());
        }

        return res.parallelStream()
            .filter(f -> (f.getModifiers() & Modifier.STATIC) == 0).toList();
    }

    //check if it is an array of objects. I suspect there must be a more API-friendly way to make this check.
    private static boolean isObjectArray(Class<?> type) {
        return type.isArray() && !type.getComponentType().isPrimitive();
    }

    //obtain a shallow size of a field of given class (primitive or object reference size)
    private static int getObjSize(Class<?> type) {
        if (type.isPrimitive()) {
            return ClassIntrospector.PRIM_TYPE_SIZES.getOrDefault(type, 0);
        }
        return ClassIntrospector.OBJ_REF_SIZE;
    }
}
