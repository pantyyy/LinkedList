public class DummyHeadLinkedList<E> {


    private class Node{
        public E e;
        public Node next;

        public Node(Node node , E e){
            this.next = node ;
            this.e = e;
        }

        public Node(E e){
            this(null , e);
        }

        public Node(){
            this(null , null);
        }

        @Override
        public String toString(){
            return e.toString();
        }

    }

    //维护的虚拟头节点指针
    private Node dummyHead;
    private int size;

    public DummyHeadLinkedList(){
        //虚拟头点击指针 指向 虚拟头结点
        dummyHead = new Node();
        size = 0;
    }

    //获取有效元素的个数
    public int getSize(){return this.size;}

    //判断链表是否为空
    public boolean isEmpty(){return this.size == 0;}

    //向index位置添加元素
    public void add(E e , int index){
        //判断index的合法性
        //index = [0 , size]
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;
        //因为有了虚拟头结点的存在
        //所以dummyHead指针和i保持一致
        //如果dummyHead的位置为-1 , 那么第一次执行完for循环 , prev就指向了为0的位置
        //也就是说i 和 prev指针对应起来了
        for(int i = 0 ; i < index ; i++){
            prev = prev.next;
        }

        //添加节点
        Node newNode = new Node(e);
        newNode.next = prev.next;
        prev.next = newNode;

        size++;
    }


    // 在链表头添加新的元素e
    public void addFirst(E e){
        add(e , 0);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(e , size);
    }

    //根据index , 获取链表中的元素
    public E get(int index){
        //判断index合法性
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        //移动的指针 , 指向当前节点
        Node cur = dummyHead.next;
        //因为cur指向了0下标
        //当cur执行一次for循环的时候 , cur所指向的位置比i大1
        //又因为i只能取到index - 1 , 此时cur指向了index位置的元素
        //合理
        for(int i = 0 ; i < index ; i++){
            cur = cur.next;
        }

        return cur.e;
    }



    //更新链表中index位置的元素
    public void set(int index , E e){
        if(index < 0 || index >=size)
            throw new IllegalArgumentException("Set failed. Illegal index.");

        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i++){
            cur = cur.next;
        }

        cur.e = e;
        return;
    }

    //查找链表中是否有元素e
    public boolean contaits(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    //从index位置删除元素
    public E remove(int index){
        //判断index的合法性
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        //定位到index的前一个元素
        Node prev = dummyHead;

        //prev的指向和i相吻合 , 所以i只能取到index - 1 , cur也就只能指向index - 1
        for(int i = 0 ; i < index ; i++){
            prev = prev.next;
        }

        //删除元素
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }


    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }

        res.append("NULL");
        return res.toString();
    }

    public static void main(String[] args) {
        DummyHeadLinkedList<Integer> linkedList = new DummyHeadLinkedList<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(666 ,2);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
