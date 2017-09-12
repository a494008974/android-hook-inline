package com.vst.player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by Administrator on 2016/10/11.
 */
public class ClassRefUtils {

    public static void displayClass(Class clazz){
        System.out.println("===============本类属性========================");
        // 取得本类的全部属性
        Field[] field = clazz.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + field[i].getName() + ";");
        }
        System.out.println("===============实现的接口或者父类的属性========================");
        // 取得实现的接口或者父类的属性
        Field[] filed1 = clazz.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // 权限修饰符
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = filed1[j].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + filed1[j].getName() + ";");
        }

        System.out.println("===============本类方法========================");

        Method method[]=clazz.getDeclaredMethods();
        for(int i=0;i<method.length;++i){
            Class<?> returnType=method[i].getReturnType();
            Class<?> para[]=method[i].getParameterTypes();
            int temp=method[i].getModifiers();
            System.out.print(Modifier.toString(temp)+" ");
            System.out.print(returnType.getName()+"  ");
            System.out.print(method[i].getName()+" ");
            System.out.print("(");
            for(int j=0;j<para.length;++j){
                System.out.print(para[j].getName()+" "+"arg"+j);
                if(j<para.length-1){
                    System.out.print(",");
                }
            }
            Class<?> exce[]=method[i].getExceptionTypes();
            if(exce.length>0){
                System.out.print(") throws ");
                for(int k=0;k<exce.length;++k){
                    System.out.print(exce[k].getName()+" ");
                    if(k<exce.length-1){
                        System.out.print(",");
                    }
                }
            }else{
                System.out.print(")");
            }
            System.out.println();
        }
    }
}
