import java.util.*;

public class ChainedList {
    private Node head;

    //kell neki lennie:


    //print elem mentesehez
    //kell utcso es elso elem

    //kell elagazas |es| |vagy| vagy nal plusz egy szoveges feltetel


    private static class Node {
        int _ident;//azonosito
        //String _color;//szin
        String _name;//kell nev (tevekenyseg)

        //Node next;//rakov elem (next)
        List<Node> next; //tobb next is kell a branch eseteben


        void addNext(Node nextNode) {

            this.next.add(0,nextNode);
            this.next.add(1,nextNode);


        }

        Node(int ident,String _name) {
            this._ident = ident;

            this.next = new ArrayList<>();
        }



    }

    public void add(int ident,String name) {
        Node newNode = new Node(ident,name);

        if (head == null) {

            head = newNode;

        }
        else {

            Node current = head;

            while (!current.next.isEmpty()) {
                current = current.next.get(0); //elso elem nextje


            }

            current.addNext(newNode);

        }
    }
    public void remove(){

    }
    public void modify(){

    }



    private void showNode(Node node) {
        if (node != null) {

            System.out.print( node.next.size() + " ");
            System.out.println( node._ident + " ");

            for (Node nextNode : node.next) {
                showNode(nextNode);
            }
        }


        /*
        while (node.next.get(0) != null) {

            System.out.print(node._ident + " ");
            System.out.println(node.next.size());
            node = node.next.get(0);


        }

         */
    }
    public void show() {
        showNode(head);
    }




}