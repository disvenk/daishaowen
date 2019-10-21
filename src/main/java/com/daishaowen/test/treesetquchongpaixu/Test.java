package com.daishaowen.test.treesetquchongpaixu;

import javax.xml.transform.Source;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        Set set = new HashSet();//treeset必须是爱你comparable接口,如果不重写equals方法，默认和compareTo比较字段一致
        Student stu1 = new Student("貂蝉", 19);
        Student stu2 = new Student("西施", 25);
        Student stu3 = new Student("王昭君", 22);
        Student stu4 = new Student("貂蝉", 19);
        set.add(stu1);
        set.add(stu2);
        set.add(stu3);
        set.add(stu4);
        System.out.println(set);
        Test test = new Test();
        test.hashCode();
        Test test1 = new Test();
        System.out.println(test);
    }
}
