public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
class Node{

    int key;
    int value;
    Node rightchild, leftchild, parent;

    enum Color {RED, BLACK}

    Color color;



    public Node(Color c, int k, int v){
        color = c;
        key = k;
        value = v;

    }

}
class RBTree{

    Node root;
    Node lastnode;
    void insert(int key, int value){
        boolean inserted = false;
        if(root == null){
            root = new Node(Node.Color.BLACK, key, value);
        }
        else{

            Node newnode = new Node(Node.Color.RED, key, value); //przy wstawianiu zawsze ustawiamy kolor na czerwony, potem poprawimy
            lastnode = root;
            while(!inserted){
                if(lastnode.value < value && lastnode.rightchild == null){
                    lastnode.rightchild = newnode;
                    inserted = true;
                }
                else if(lastnode.value < value && lastnode.rightchild != null){
                    lastnode = lastnode.rightchild;
                }
                else if(lastnode.value > value && lastnode.leftchild == null){
                    lastnode.leftchild = newnode;
                    inserted = true;
                }
                else if(lastnode.value > value && lastnode.leftchild != null){
                    lastnode = lastnode.leftchild;
                }
                else if(lastnode.value == value){
                    if(lastnode.leftchild == null){ //wartości równe będziemy wstawiać po lewej
                        lastnode.leftchild = newnode;
                        inserted = true;
                    }
                    else{
                        lastnode = lastnode.leftchild;
                    }
                }
            }

        }

    }
    int get(int key){
        Node currentNode = root;
        int found_value = 0;
        boolean found = false;
        while (!found){
            if(currentNode.key == key){
                found_value =  currentNode.value;
                found = true;
            }
            else if(key < currentNode.key){
                if(currentNode.leftchild != null){
                    currentNode = currentNode.leftchild;
                }
                else{
                    return -1;

                }
            }
            else{
                if(currentNode.rightchild != null){
                    currentNode = currentNode.rightchild;
                }
                else{
                    return -1;
                }
            }
        }
        return found_value;
    }
    int remove(int key){
        Node currentNode = root;
        if()
    }

    int height(){
        int last_layer = 1;
        Node currentNode = root;
        boolean has_reached_bottom = false;
        while (!has_reached_bottom){
            if(currentNode.leftchild == null && currentNode.rightchild == null){

                has_reached_bottom = true;

            }
            else if(currentNode.leftchild != null && currentNode.rightchild == null){
                last_layer++;
                currentNode = currentNode.leftchild;
            }
            else if(currentNode.leftchild == null && currentNode.rightchild != null){
                last_layer++;
                currentNode = currentNode.rightchild;
            }
            else{
                //jak uniknąć fałszwych wyników?
            }
        }
        return last_layer;
    }

}
