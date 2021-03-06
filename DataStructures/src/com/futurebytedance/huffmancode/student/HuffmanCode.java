package com.futurebytedance.huffmancode.student;

import java.io.*;
import java.util.*;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/6 - 19:53
 * @Description 哈夫曼编码-压缩与解压
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] huffmanByteCodes = encode(content);
        System.out.println(Arrays.toString(huffmanByteCodes));

        byte[] origin = decode(huffmanCode, huffmanByteCodes);
        System.out.println(new String(origin));

        zip("D:\\tmp_study\\sss.txt", "D:\\tmp_study\\karsa1.jpg");
        System.out.println("压缩成功!");

        unzip("D:\\tmp_study\\karsa1.jpg", "D:\\tmp_study\\sss1.txt");
        System.out.println("解压缩成功!");
    }

    public static void unzip(String srcFile, String destFile) {
        ObjectInputStream ois = null;
        BufferedOutputStream bos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(srcFile));
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            byte[] bytes = (byte[]) ois.readObject();
            Map<Byte, String> map = (Map<Byte, String>) ois.readObject();
            byte[] decode = decode(map, bytes);
            bos.write(decode);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //压缩
    public static void zip(String srcFile, String destFile) {
        FileInputStream fis = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(srcFile);
            oos = new ObjectOutputStream(new FileOutputStream(destFile));
            StringBuilder builder = new StringBuilder();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                builder.append(new String(bytes, 0, len));
            }
            System.out.println(builder.toString());
            byte[] zip = encode(builder.toString());
            oos.writeObject(zip);
            oos.flush();
            oos.writeObject(huffmanCode);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //将编码后的byte数组进行还原
    public static byte[] decode(Map<Byte, String> map, byte[] huffmanByteCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag;
        for (int i = 0; i < huffmanByteCodes.length; i++) {
            flag = (i == huffmanByteCodes.length - 1) && huffmanByteCodes[i] > 0;
            stringBuilder.append(bytesToString(!flag, huffmanByteCodes[i]));
        }

        Map<String, Byte> hashmap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : map.entrySet()) {
            hashmap.put(entry.getValue(), entry.getKey());
        }

        ArrayList<Byte> list = new ArrayList<>();

        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            while (true) {
                String substring = stringBuilder.substring(i, i + count);
                if (hashmap.containsKey(substring)) {
                    list.add(hashmap.get(substring));
                    break;
                }
                count++;
            }
            i = i + count;
        }

        byte[] originByte = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            originByte[i] = list.get(i);
        }
        return originByte;
    }

    public static String bytesToString(boolean flag, byte by) {
        String binaryString;
        int temp = by;
        //需要判断是不是低位
        if (flag) {
            temp |= 256;
        }
        binaryString = Integer.toBinaryString(temp);
        if (flag) {
            return binaryString.substring(binaryString.length() - 8);
        } else {
            return binaryString;
        }
    }


    //将一个字符串压缩为一个byte数组
    public static byte[] encode(String content) {
        byte[] contentBytes = content.getBytes();
        //构建哈夫曼树
        System.out.println("哈夫曼树为:");
        Node huffmanTreeRoot = createHuffmanTree(contentBytes);
        preOrder(huffmanTreeRoot);

        //构建哈夫曼编码表
        System.out.println("哈夫曼编码表为:");
        getCodes(huffmanTreeRoot);
        System.out.println(huffmanCode);

        //将哈夫曼编码表转成一个byte[]数组
        return zip(contentBytes, huffmanCode);
    }


    //哈夫曼编码-->字节数组
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        int length = stringBuilder.length();

        int len = (length + 7) / 8;

        int index = 0;

        byte[] huffmanByteCodes = new byte[len];
        for (int i = 0; i < length; i += 8) {
            String substring;
            if (i + 8 > length) {
                substring = stringBuilder.substring(i);
            } else {
                substring = stringBuilder.substring(i, i + 8);
            }
            huffmanByteCodes[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }
        return huffmanByteCodes;
    }

    static Map<Byte, String> huffmanCode = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    //重载哈夫曼编码-主方法
    public static void getCodes(Node root) {
        if (root == null) {
            System.out.println("根节点为空，无法构建哈夫曼编码！");
        } else {
            getCodes(root.left, "0", stringBuilder);
            getCodes(root.right, "1", stringBuilder);
        }
    }

    //建立哈夫曼编码-具体实现
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder1);
                getCodes(node.right, "1", stringBuilder1);
            } else {
                huffmanCode.put(node.data, stringBuilder1.toString());
            }
        }
    }

    //前序遍历哈夫曼树
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历!");
        }
    }

    //建立哈夫曼树
    public static Node createHuffmanTree(byte[] bytes) {
        List<Node> list = getNodes(bytes);
        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parentNode);
        }
        return list.get(0);
    }

    //将字节数组统计权值并放入ArrayList中
    public static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> hashMap = new HashMap<>();

        for (byte b : bytes) {
            if (hashMap.containsKey(b)) {
                hashMap.put(b, hashMap.get(b) + 1);
            } else {
                hashMap.put(b, 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : hashMap.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

class Node implements Comparable<Node> {
    Byte data;
    int weight;//权值
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
