package com.futurebytedance.hashtab.student;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2021/2/21 - 22:09
 * @Description 哈希表的实现---数组+链表方式
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(10);

        Emp emp = new Emp(40, "赵云");
        Emp emp1 = new Emp(30, "关羽");
        Emp emp2 = new Emp(20, "张飞");
        Emp emp3 = new Emp(10, "马超");
        Emp emp4 = new Emp(10, "黄忠");
        Emp emp5 = new Emp(70, "魏延");
        Emp emp6 = new Emp(10, "周瑜");
        Emp emp7 = new Emp(100, "诸葛亮");

        //增
        hashTab.add(emp);
        hashTab.add(emp1);
        hashTab.add(emp2);
        hashTab.add(emp3);
        hashTab.add(emp4);
        hashTab.add(emp5);
        hashTab.list();

        //删
        hashTab.del(70);
        hashTab.del(80);
        hashTab.list();

        //改
        hashTab.modify(emp6);
        hashTab.modify(emp7);
        hashTab.list();

    }
}

class HashTab {
    private final EmpLinkedList[] EmpLinkedListArray;
    private final int size;

    //初始化
    public HashTab(int size) {
        this.size = size;
        EmpLinkedListArray = new EmpLinkedList[this.size];
        for (int i = 0; i < this.size; i++) {
            EmpLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //增
    public void add(Emp emp) {
        int id = hashFunction(emp.id);
        EmpLinkedListArray[id].add(emp);
    }

    //删
    public void del(int id) {
        int i = hashFunction(id);
        EmpLinkedListArray[i].del(id);
    }

    //改
    public void modify(Emp emp) {
        int i = hashFunction(emp.id);
        EmpLinkedListArray[i].modify(emp);
    }

    //查
    public void search(int no) {
        int i = hashFunction(no);
        EmpLinkedListArray[i].search(no);
    }

    //遍历
    public void list() {
        for (int i = 0; i < size; i++) {
            if (!EmpLinkedListArray[i].isEmpty()) {
                EmpLinkedListArray[i].list();
            }
        }
    }

    public int hashFunction(int id) {
        return id % size;
    }
}

//链表---实现增删改查功能
class EmpLinkedList {
    Emp head = null;

    //按顺序增加操作
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        if (head.next == null) {
            if (emp.compareTo(head) > 0) {
                head.next = emp;
                return;
            } else if (emp.compareTo(head) == 0) {
                System.out.println(emp + " 该节点id已经存在，不能重复插入!");
                return;
            } else {
                Emp s = head;
                head = emp;
                emp.next = s;
                return;
            }
        }
        Emp curEmp = head;
        Emp curEmpNext = head.next;
        if (emp.compareTo(head) > 0) {
            while (curEmpNext != null) {
                if (emp.compareTo(curEmpNext) > 0) {
                    curEmp = curEmp.next;
                    curEmpNext = curEmpNext.next;
                } else if (emp.compareTo(curEmpNext) == 0) {
                    System.out.println(emp + " 该节点id已经存在，不能重复插入!");
                } else {
                    curEmp.next = emp;
                    emp.next = curEmpNext;
                    return;
                }
            }
            curEmp.next = emp;
        } else if (emp.compareTo(head) == 0) {
            System.out.println(emp + " 该节点id已经存在，不能重复插入!");
        } else {
            Emp s = head;
            head = emp;
            head.next = s;
        }
    }

    //删
    public void del(int no) {
        if (head == null) {
            System.out.println("链表为空，不能删除!");
            return;
        }
        //如果是头结点
        if (head.id == no) {
            head = head.next;
            System.out.println("删除成功!");
            return;
        }
        //中间结点或尾结点
        Emp curEmp = head;
        Emp curEmpNext = head.next;
        boolean flag = false;
        while (curEmpNext != null) {
            if (curEmpNext.id == no) {
                curEmp.next = curEmpNext.next;
                System.out.println("删除成功!");
                flag = true;
                break;
            }
            curEmp = curEmp.next;
            curEmpNext = curEmpNext.next;
        }
        if (!flag) {
            System.out.println("未查找到该节点!");
        }
    }

    //改
    public void modify(Emp emp) {
        if (head == null) {
            System.out.println("链表为空，无法修改!");
            return;
        }
        if (head.id == emp.id) {
            head.name = emp.name;
            return;
        }
        Emp temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.id == emp.id) {
                temp.name = emp.name;
                System.out.println("修改成功!");
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("未找到该节点，请重新确定该节点是否存在!");
        }
    }

    //查找
    public void search(int no) {
        if (head == null) {
            System.out.println("链表为空，请勿查找!");
            return;
        }
        if (head.id == no) {
            System.out.println("该节点信息为:" + head);
            return;
        }
        Emp temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.id == no) {
                flag = true;
                System.out.println("该节点信息为:" + head);
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("未找到该节点!");
        }
    }

    //遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("链表为空!");
            return;
        }
        Emp emp = head;
        while (emp != null) {
            System.out.print(emp + "\t");
            emp = emp.next;
        }
        System.out.println();
    }

    //判断链表元素是否为空
    public boolean isEmpty() {
        return head == null;
    }
}

//Node类
class Emp implements Comparable<Emp> {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Emp o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}