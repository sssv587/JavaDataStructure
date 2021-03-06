package com.futurebytedance.huffmancode.teacher;

import java.io.*;
import java.util.*;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/3/6 - 14:52
 * @Description 哈夫曼编码-压缩与解压
 */
public class HuffmanCode {
    public static void main(String[] args) {
        //测试压缩文件
		String srcFile = "D:\\tmp_study\\karsa.jpg";
		String dstFile = "D:\\tmp_study\\karsa.zip";

		zipFile(srcFile, dstFile);
		System.out.println("压缩文件ok~~");


        //测试解压文件
//        String zipFile = "d://Uninstall.zip";
//        String dstFile = "d://Uninstall2.xml";
//        unZipFile(zipFile, dstFile);
//        System.out.println("解压成功!");


//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length); //40
//
//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//        System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodeBytes) + " 长度=" + huffmanCodeBytes.length);
//
//
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
//        System.out.println("原来的字符串为:" + new String(sourceBytes));

//分步操作为:
//
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("nodes=" + nodes);
//
//        //测试一把，创建的哈夫曼树
//        System.out.println("哈夫曼树");
//        Node huffmanTree = createHuffmanTree(nodes);
//        preOrder(huffmanTree);
//
//        //测试一把是否生成了对应的哈夫曼编码
//        getCodes(huffmanTree);
//        System.out.println("生成的哈夫曼编码表" + huffmanCodes);
//
//        //测试
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes)); //17


    }
    //编写一个方法，完成对压缩文件的解压

    /**
     * @param zipFile  准备解压哪个文件
     * @param destFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String destFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取哈夫曼表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(destFile);
            //写数据到文件中
            os.write(bytes);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //编写方法，将一个文件进行压缩

    /**
     * @param srcFile  你传入的希望压缩的文件的全路径
     * @param destFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String destFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建一个文件的输入流
        FileInputStream is = null;
        try {
            //创建一个文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //获取到文件对应的哈夫曼编码表
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(destFile);
            //创建一个和文件输出流相关联的ObjectOutPutStream
            oos = new ObjectOutputStream(os);
            //把哈夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); //我们是把huffmanBytes放进去

            //这里我们以对象流的方式写入哈夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把哈夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //完成数据的解压
    //思路
    //1.将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //   重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
    //2.赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"

    /**
     * @param huffmanTree  哈夫曼编码表map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanTree, byte[] huffmanBytes) {
        //1.先得到huffmanTree对应的二进制的字符串，形式1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        //把字符串按照指定的哈夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为要反向查询 a->100 100=>a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanTree.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
        //i可以理解成一个索引，不能的扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; //小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //1010100010111...
                //递增的取出key
                String key = stringBuilder.substring(i, i + count);//i 不动，让count动，直到匹配到一个字符
                b = map.get(key);
                if (b == null) { //说明没有匹配到
                    count++;
                } else { //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count; //直接向后移动count位置
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 将一个byte 转成一个二进制的字符串
     *
     * @param flag 如果是true表示需要补高位，如果是false表示不补，如果是最后一个字节，无需补高位
     * @param b    传入的byte值
     * @return 是该b对应的二进制字符串(注意是按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b; //将b转成int
        //如果是正数我们还存在补高位的问题
        if (flag) {
            temp |= 256; //按位或 1 0000 0001 | 0000 0001 = 1 0000 0001
        }

        String str = Integer.toBinaryString(temp);

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }

    //使用一个方法，将前面的方法封装起来，便于我们的调用

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 哈夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //创建的哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //生成对应的哈夫曼编码(根据哈夫曼树)
        getCodes(huffmanTreeRoot);
        //根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        return zip(bytes, huffmanCodes);
    }

    //编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个哈夫曼编码 压缩后的byte[] 数组

    /**
     * @param bytes        这是原始的字符串对应的byte[]
     * @param huffmanCodes huffmanCodes生成的哈夫曼编码map
     * @return 返回哈夫曼编码处理后的byte[]
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用赫夫曼编码表将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //System.out.println("测试 stringBuilder~~~=" + stringBuilder.toString());

        //将 "1010100010111111110..." 转成 byte[]

        //统计返回的byte[] huffmanCodeBytes长度
        //一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {//因为是每8位对应一个byte，所以步长 +8
            String strByte;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将strByte放入到by就可以了，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;
    }

    //生成哈夫曼树对应的哈夫曼编码
    //思路:
    //1.将赫夫曼编码表存放在 Map<Byte,String> 形式
    //生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2.在生成哈夫曼编码时，需要去拼接路径，定义一个StringBuilder存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，我们重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        } else {
            //处理root的左子树
            getCodes(root.left, "0", stringBuilder);
            //处理root的右子树
            getCodes(root.right, "1", stringBuilder);
        }
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入到huffmanCode集合
     *
     * @param node          传入节点
     * @param code          路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) { //如果node==null不处理
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) { //非叶子节点
                //递归处理
                //向左
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else { //说明是一个叶子节点
                //就表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }


    private static void preOrder(Node root) {
        if (root == null) {
            System.out.println("二叉树为空，无法遍历!");
        } else {
            root.preOrder();
        }
    }

    /**
     * @param bytes 接收一个字节数组
     * @return 返回的就是 List  [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......]
     */
    private static List<Node> getNodes(byte[] bytes) {
        //1.创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes，统计每一个byte出现的次数->map[key,value]
        HashMap<Byte, Integer> hashMap = new HashMap<>();
        for (byte b : bytes) {
            Integer count = hashMap.get(b);
            if (count == null) { //Map还没有这个字符数据，第一次
                hashMap.put(b, 1);
            } else {
                hashMap.put(b, count + 1);
            }
        }
        //把每一个键值对转成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : hashMap.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //可以通过List创建对应的哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序,从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二课最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树,它的根节点没有data，只有权值
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            //将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将新的二叉树，加入到nodes
            nodes.add(parentNode);
        }
        //nodes 最后的节点，就是哈夫曼树的根节点
        return nodes.get(0);
    }
}

//创建Node，存放数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据本身，比如'a'=>97
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
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
