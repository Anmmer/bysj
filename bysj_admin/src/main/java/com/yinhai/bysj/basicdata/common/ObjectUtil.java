package com.yinhai.bysj.basicdata.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName ObjectUtil
 * @Description: 对象工具类
 */
public class ObjectUtil {

    /**
     * @param objects
     * @return
     * @Description: 判断对象是否为空, 只有一个对象为空，则返回true
     * @update: 2014年4月23日 by huangyh
     */
    public static boolean isEmpty(Object... objects) {
        if (objects == null) {
            return true;
        }
        for (Object object : objects) {
            if (!isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param object
     * @return
     * @Description: 判断对象是否为空
     * @update: 2014年4月23日 by huangyh
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        /**
         * if(object instanceof JSONNull){ return true; }
         */
        if (object instanceof StringBuffer) {
            object = object.toString();
        }
        if (object.getClass().isArray()) {//20191217 by huangyh新增数组对象判断
            if ("byte".equals(object.getClass().getComponentType().toString())) {
                return ((byte[]) object).length == 0;
            } else if ("short".equals(object.getClass().getComponentType().toString())) {
                return ((short[]) object).length == 0;
            } else if ("int".equals(object.getClass().getComponentType().toString())) {
                return ((int[]) object).length == 0;
            } else if ("long".equals(object.getClass().getComponentType().toString())) {
                return ((long[]) object).length == 0;
            } else if ("float".equals(object.getClass().getComponentType().toString())) {
                return ((float[]) object).length == 0;
            } else if ("double".equals(object.getClass().getComponentType().toString())) {
                return ((double[]) object).length > 0;
            } else if ("char".equals(object.getClass().getComponentType().toString())) {
                return ((char[]) object).length == 0;
            } else if ("boolean".equals(object.getClass().getComponentType().toString())) {
                return ((boolean[]) object).length == 0;
            } else {
                return ((Object[]) object).length == 0;
            }
        }
        if ((object instanceof String)) {
            if (((String) object).trim().length() == 0 || "null".equals(((String) object).trim().toLowerCase()) || "undefined".equals(((String) object).trim().toLowerCase())) {
                return true;
            }
        } else if ((object instanceof Collection)) {
            if (((Collection) object).size() == 0) {//NOSONAR
                return true;
            }
        } else if ((object instanceof Map) && ((Map) object).size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 对象是否支持序列化
     *
     * @param value
     * @return
     */
    public static boolean isSupportSerializable(Object value) {
        if (value == null) {
            return true;
        }
        return Serializable.class.isAssignableFrom(value.getClass());
    }

    /**
     * 判断一个对象是否是基本类型或基本类型的封装类型
     */
    public static boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对象转换为Byte[]
     *
     * @param o
     * @return
     */
    public static byte[] ObjectToByte(Object o) {
        if (o == null) {
            return null;
        }
        byte[] bytes = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Java对象不能序列化！'" + o + "'", e);
        }
        return bytes;
    }


    public static String ClobToString(Clob clob) {
        String reString = "";
        if (clob == null)
            return reString;
        Reader is = null;
        try {
            is = clob.getCharacterStream();
            // 得到流
            BufferedReader br = new BufferedReader(is);
            String s = null;
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuffer sb = new StringBuffer();
            while (s != null) {
                // 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
                sb.append(s);
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            reString = sb.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reString;
    }

    /**
     * byte[]转换为Object
     *
     * @param bytes
     * @return
     */
    public static Object ByteToObject(byte[] bytes) {
        try {
            if (bytes == null) {
                return null;
            }
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);
            Object object = oi.readObject();
            bi.close();
            oi.close();
            return object;
        } catch (Exception e) {
            throw new RuntimeException("不能反序列化！", e);
        }
    }

    /**
     * @param resource
     * @Description: Map对象内的Clob对象全部转换为String
     * @update: 2014年4月23日 by huangyh
     */
    public static void convertClobToString(Map<String, Object> resource) {
        if (!isEmpty(resource)) {
            for (String key : resource.keySet()) {
                Object value = resource.get(key);
                if (value instanceof Clob) {
                    resource.put(key, ClobToString((Clob) value));
                }
            }
        }
    }

    public static void convertClobToString(List resources) {
        if (!isEmpty(resources)) {
            for (Object resource : resources) {
                if (resource instanceof Map) {
                    convertClobToString((Map) resource);
                } else {
                    break;
                }
            }
        }
    }


    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param type 要转化的类型
     * @param map  包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     */
    public static Object map2Bean(Class type, Map<String, Object> map) {
        if (isEmpty(map)) {
            return null;
        }
        // Domain预处理
        map.remove("PK");
        map.remove("key");
        map.remove("domainObjectName");
        map.remove("metadata");
        map.remove("objid");
        String propertyName = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
            Object obj = type.newInstance(); // 创建 JavaBean 对象
            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    if (isEmpty(map.get(propertyName))) {
                        continue;
                    }
                    Object[] params = descriptor.getWriteMethod().getParameterTypes();
                    Object[] args = new Object[1];
                    String clazz = ((Class) params[0]).getName();

                    if (Long.class.getName().equals(clazz) || "long".equals(clazz)) {//NOSONAR 字符串比较用equals正确，Classes should not be compared by name
                        args[0] = Long.valueOf(String.valueOf(map.get(propertyName)));
                    } else if (Integer.class.getName().equals(clazz) || "int".equals(clazz)) {//NOSONAR
                        args[0] = Integer.valueOf(String.valueOf(map.get(propertyName)));
                    } else if (Date.class.getName().equals(clazz)) {//NOSONAR
                        args[0] = DateUtil.str2Timestamp(String.valueOf(map.get(propertyName)));
                    } else {
                        args[0] = String.valueOf(map.get(propertyName));
                    }
                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("属性值" + propertyName + "转换出错");
        }
        return null;
    }

    /**
     * 将一个 JavaBean 对象转化为一个 Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     */
    public static Map<String, Object> bean2Map(Object bean) {
        if (bean == null) {
            return null;
        }
        String propertyName = null;
        try {
            Class type = bean.getClass();
            Map<String, Object> returnMap = new HashMap<String, Object>();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                propertyName = descriptor.getName();
                if (!"class".equals(propertyName)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
            return returnMap;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("属性值" + propertyName + "转换出错");
        }
        return null;
    }

    /**
     * 获取指定对象的指定属性值（去除private,protected的限制）
     *
     * @param obj       属性名称所在的对象
     * @param fieldName 属性名称
     * @return
     * @throws Exception
     */
    public static Object getFieldValue(Object obj, String fieldName) throws Exception {
        if (isEmpty(obj)) {
            return null;
        }
        if (obj instanceof Map) {
            return ((Map) obj).get(fieldName);
        } else {
            Field field = obj.getClass().getDeclaredField(fieldName);
            Object object = null;
            boolean accessible = field.isAccessible();
            if (!accessible) {
                // 如果是private,protected修饰的属性，需要修改为可以访问的
                field.setAccessible(true);
                object = field.get(obj);
                // 还原private,protected属性的访问性质
                field.setAccessible(accessible);
                return object;
            }
            object = field.get(obj);
            return object;
        }
    }

    public static Object deepClone(Object src) {
        Object dest = null;
        try {
            // 将对象写到流里
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(src);
            // 从流里读回来
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            dest = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return dest;
    }

    /**
     * 比较两个实体属性值，返回一个boolean,true则表时两个对象中的属性值无差异
     * 必须确保新旧对象不为null
     *
     * @param oldObject 进行属性比较的对象1
     * @param newObject 进行属性比较的对象2
     * @return 属性差异比较结果boolean
     */
    public static boolean compareObject(Object oldObject, Object newObject) {
        Map<String, Map<String, Object>> resultMap = compareFields(oldObject, newObject);
        if (resultMap != null && resultMap.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较两个实体属性值，返回一个map以有差异的属性名为key，value为一个Map分别存oldObject,newObject此属性名的值
     * 必须确保新旧对象不为null
     *
     * @param oldObject 进行属性比较的对象1
     * @param newObject 进行属性比较的对象2
     * @return 属性差异比较结果map
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Map<String, Object>> compareFields(Object oldObject, Object newObject) {
        Map<String, Map<String, Object>> map = null;
        try {
            /**
             * 只有两个对象都是同一类型的才有可比性
             */
            if (oldObject.getClass() == newObject.getClass()) {
                Class clazz = oldObject.getClass();
                map = new HashMap<String, Map<String, Object>>();
                //获取object的所有属性
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();

                for (PropertyDescriptor pd : pds) {
                    //遍历获取属性名
                    String name = pd.getName();

                    //获取属性的get方法
                    Method readMethod = pd.getReadMethod();

                    // 在oldObject上调用get方法等同于获得oldObject的属性值
                    Object oldValue = readMethod.invoke(oldObject);
                    // 在newObject上调用get方法等同于获得newObject的属性值
                    Object newValue = readMethod.invoke(newObject);

                    if (oldValue instanceof List) {
                        continue;
                    }

                    if (newValue instanceof List) {
                        continue;
                    }

                    if (oldValue instanceof Timestamp) {
                        oldValue = new Date(((Timestamp) oldValue).getTime());
                    }

                    if (newValue instanceof Timestamp) {
                        newValue = new Date(((Timestamp) newValue).getTime());
                    }

                    if (oldValue == null && newValue == null) {
                        continue;
                    } else if (oldValue == null && newValue != null) {
                        Map<String, Object> valueMap = new HashMap<String, Object>();
                        valueMap.put("oldValue", oldValue);
                        valueMap.put("newValue", newValue);
                        map.put(name, valueMap);
                        continue;
                    }

                    if (!oldValue.equals(newValue)) {// 比较这两个值是否相等,不等就可以放入map了
                        Map<String, Object> valueMap = new HashMap<String, Object>();
                        valueMap.put("oldValue", oldValue);
                        valueMap.put("newValue", newValue);
                        map.put(name, valueMap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static boolean compareMap(Map<String, Object> oldMap, Map<String, Object> newMap) {
        Map<String, Map<String, Object>> resultMap = compareKeys(oldMap, newMap);
        if (resultMap != null && resultMap.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public static Map<String, Map<String, Object>> compareKeys
            (Map<String, Object> oldMap, Map<String, Object> newMap) {
        Map<String, Map<String, Object>> difMap = null;
        Map<String, Object> valueMap = null;
        if (isEmpty(oldMap)) {
            if (isNotEmpty(newMap)) {
                difMap = new HashMap<String, Map<String, Object>>();
                for (Map.Entry<String, Object> entry : newMap.entrySet()) {
                    valueMap = new HashMap<String, Object>();
                    valueMap.put("oldValue", null);
                    valueMap.put("newValue", entry.getValue());
                    difMap.put(entry.getKey(), valueMap);
                }
            }
        } else {
            difMap = new HashMap<String, Map<String, Object>>();
            if (isEmpty(newMap)) {
                for (Map.Entry<String, Object> entry : oldMap.entrySet()) {
                    valueMap = new HashMap<String, Object>();
                    valueMap.put("oldValue", entry.getValue());
                    valueMap.put("newValue", null);
                    difMap.put(entry.getKey(), valueMap);
                }
            } else {
                for (Map.Entry<String, Object> entry : oldMap.entrySet()) {
                    Object oldValue = entry.getValue();
                    Object newValue = newMap.get(entry.getKey());
                    if (isEmpty(oldValue) && isEmpty(newValue)) {
                        continue;
                    }
                    if ((isNotEmpty(newValue) && !newValue.equals(oldValue)) || (isNotEmpty(oldValue) && !oldValue.equals(newValue))) {//NOSONAR
                        valueMap = new HashMap<String, Object>();
                        valueMap.put("oldValue", oldValue);
                        valueMap.put("newValue", newValue);
                        difMap.put(entry.getKey(), valueMap);
                    }
                }
            }
        }
        return difMap;
    }

    /**
     * 关闭流对象
     * 参考IOUtils.closeQuietly
     *
     * @param o
     */
    public static void close(Object o) {
        if (o != null) {
            if (o instanceof Closeable) {//流对象
                try {
                    ((Closeable) o).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (o instanceof AutoCloseable) {//db
                try {
                    ((AutoCloseable) o).close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (o instanceof HttpURLConnection) {//http
                ((HttpURLConnection) o).disconnect();
            }
        }
    }
}
