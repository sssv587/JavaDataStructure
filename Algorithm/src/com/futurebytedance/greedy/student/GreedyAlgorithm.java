package com.futurebytedance.greedy.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/21 - 17:16
 * @Description 贪心算法
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到Map
        HashMap<String, HashSet<String>> broadCasts = new HashMap<>();
        //将各个电台放入到broadCasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到Map
        broadCasts.put("k1", hashSet1);
        broadCasts.put("k2", hashSet2);
        broadCasts.put("k3", hashSet3);
        broadCasts.put("k4", hashSet4);
        broadCasts.put("k5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义一个maxKey，保存在一次遍历过程中能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey不为null，则会加入到selects中
        String maxKey;

        while (allAreas.size() > 0) {
            maxKey = null;
            for (String key : broadCasts.keySet()) {
                tempSet.clear();
                tempSet.addAll(broadCasts.get(key));
                tempSet.retainAll(allAreas);
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadCasts.get(key).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果是:" + selects);
    }
}
