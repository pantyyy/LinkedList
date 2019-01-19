public class LinkedList<E> {
    //定义一个节点类
    private class Node{
        //真正的数据元素
        public E e;
        //指向下一个节点的指针
        public Node next;

        public Node(E e , Node next)
        {
            this.e = e;
            this.next = next;
        }

        public Node(E e){this(e , null);}
        public Node(){this(null , null);}

        @Override
        public String toString(){
            return e.toString();
        }
    }



    //链表只需要一个头结点 , 即可找到其他的元素
    //所以需要一个头结点
    private Node head;
    //链表中的有效元素
    int size;

    public LinkedList(){
        head = null;
        size = 0;
    }

    //获取链表中的元素
    public int getSize(){return size;}

    //判断链表是否为空
    public boolean isEmpty(){return size == 0;}

    //在链表头添加元素
    public void addFirst(E e){
        //创建需要添加的节点对象
        Node node = new Node(e);
        //节点挂到头结点上
        node.next = head;
        //修正头指针
        head = node;

        //有效元素加1
        size++;
    }



    //在index位置添加节点
    public void add(int index , E e){
        //判断index位置的合法性
        //index = [0 , size]
        //为啥可以取到size?
        //因为size位置的上一个元素为最后一个有效元素
        //可以找到prev节点 , 所以 , 我们可以添加元素
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        //插入元素 , 需要找到index位置的前一个节点
        //但是 , 头结点没有前一个节点 , 所以 , 对于index = 0的情况 , 需要特殊处理
        if(index == 0)
            addFirst(e);
        else
        {
            //找到index的前一个节点
            Node prev = head;
            //此时prev指向了0下标的元素
            //进行一次for循环移动 , prev指向的下标比i大1
            //所以当i遍历到index - 1的时候 , prev其实已经指向了index元素
            //所以想要prev指向index - 1的元素 , i的条件需要修改为index - 1
            for(int i = 0 ; i < index - 1 ; i++){
                prev = prev.next;
                //创建需要添加的节点
                Node node = new Node(e);
                //新创建的节点指向index位置节点
                node.next = prev.next;
                //新创建的节点挂在prev节点上
                prev.next = node;
                size++;
            }

        }

    }


    //在链表尾添加元素e
    public void addLast(E e){add(size , e);};

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node cur = head;
        while(cur.next != null){
            res.append(cur.e + "->");
            cur = cur.next;
        }

        res.append("NULL");

        return res.toString();
    }


    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

    }































}
