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
                    newnode.parent = lastnode;
                    inserted = true;
                    Node.Color parent_color = lastnode.color;
                    if(parent_color == Node.Color.RED){
                        newnode.color = Node.Color.BLACK;
                    }
                }
                else if(lastnode.value < value && lastnode.rightchild != null){
                    lastnode = lastnode.rightchild;
                }
                else if(lastnode.value > value && lastnode.leftchild == null){
                    lastnode.leftchild = newnode;
                    newnode.parent = lastnode;
                    inserted = true;
                    Node.Color parent_color = lastnode.color;
                    if(parent_color == Node.Color.RED){
                        newnode.color = Node.Color.BLACK;
                    }
                }
                else if(lastnode.value > value && lastnode.leftchild != null){
                    lastnode = lastnode.leftchild;
                }
                else if(lastnode.value == value){
                    if(lastnode.leftchild == null){ //wartości równe będziemy wstawiać po lewej
                        lastnode.leftchild = newnode;
                        newnode.parent = lastnode;
                        inserted = true;
                    }
                    else{
                        lastnode = lastnode.leftchild;
                    }
                }
            }

        }

    }
    void fixcolors(){ //ta funkcja sprawdza, czy ścieżki od węzła do pustych dzieci mają po tyle samo czarnych węzłów
        lastnode = root;
        int number_of_black_nodes = 0;

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
        Node.Color original_color;
        Node x;
        Node y;
        boolean removed = false;
        boolean foundrcsmallest = false;
        while (!removed){
            if(currentNode.key == key){ //gdy znaleźliśmy już węzeł do usunięcia
                if(currentNode.leftchild == null){
                    original_color = currentNode.color;
                    x = currentNode.rightchild;
                    transplant(currentNode, x);
                    delete_fixup(x);
                    removed = true;
                }
                else if (currentNode.rightchild == null) {
                    original_color = currentNode.color;
                    x = currentNode.leftchild;
                    transplant(currentNode, x);
                    delete_fixup(x);
                    removed = true;
                }
                else{
                    y = currentNode.rightchild;
                    while (!foundrcsmallest){
                        if (y.leftchild == null){
                            original_color = y.color;
                            x = y.rightchild;
                            transplant(y, x);
                            y.rightchild = x.parent;
                            x.parent.parent = y;
                            transplant(currentNode, y);
                            currentNode.rightchild.parent = y;
                            currentNode.leftchild.parent = y;

                            foundrcsmallest = true; //keep this at the bottom

                        }
                        else{
                            y = y.leftchild;
                        }
                    }

                    removed = true;
                }


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
    }
    void transplant(Node u, Node v){ //u - węzeł, który usuwamy, v - węzeł, którym zastąpimy usunięty węzeł.
        if(u.parent == null){
            root = v;
        }
        else if(u ==  u.parent.leftchild){
            u.parent.leftchild = v;
        }
        else{
            u.parent.rightchild = v;
        }
        v.parent = u.parent;
    }
    void delete_fixup(Node x_fix){
        Node w;
        if(x_fix == x_fix.parent.leftchild){
            w = x_fix.parent.rightchild;
        }
        else {
            w = x_fix.parent.leftchild;
        }
        if(w.color == Node.Color.RED){
            w.color = Node.Color.BLACK;

        }
    }
    void left_rotate(Node rnode){
        Node former_child = rnode.rightchild;
        rnode.parent = former_child;
        rnode.rightchild = former_child.leftchild;
    }
    void right_rotate(Node rnode){
        Node former_parent = rnode.parent;
        Node former_rc = rnode.rightchild;
        former_parent.leftchild = former_rc;
        rnode.rightchild = former_parent;
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

        }
        return last_layer;
    }

}
