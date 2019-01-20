
public class LinkedListQueue<E> implements MyQueue<E> {

    //节点类
    private class Node{
        //数据
        E e;
        //指向下一个节点的指针
        Node next;

        public Node(E e , Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e , null);
        }

        public Node(){
            this(null , null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }


    //对应队列来说 , 需要有一端进(队尾) , 一端出(队首)
    //对于链表来说 , 头部容易出 , 尾部容易进
    //所以把链表的头部作为队首 , 尾部作为队尾 , 并用指针维护这两个位置
    private Node head , tail;
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }




    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
//    public void enqueue(E e) {
//        //入队 , 判断队列是否为空
//        if(isEmpty()){
//            //为空
//            tail = new Node(e);
//            head = tail;
//        }else{
//            //不为空
//            Node temp = new Node();
//            tail.next = temp;
//            tail = temp;
//        }
//
//        size++;
//    }
    public void enqueue(E e){
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }
        else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }






    @Override
    public E dequeue() {
        //判断队列是否为空
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        //出队元素
        Node temp = head;
        head = head.next;
        temp.next = null;

        //判断出队完 , 队列是否空
        if (head == null)
            tail = null;

        size--;

        return temp.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");

        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue : front ");

        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
        }

        res.append("NULL tail");

        return res.toString();
    }

    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                //System.out.println(queue);
            }

            System.out.println("test");
        }
    }
}
