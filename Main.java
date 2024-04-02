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
                    insert_fixup(newnode);
                    inserted = true;

                }
                else if(lastnode.value < value && lastnode.rightchild != null){
                    lastnode = lastnode.rightchild;
                }
                else if(lastnode.value > value && lastnode.leftchild == null){
                    lastnode.leftchild = newnode;
                    newnode.parent = lastnode;
                    insert_fixup(newnode);
                    inserted = true;


                }
                else if(lastnode.value > value && lastnode.leftchild != null){
                    lastnode = lastnode.leftchild;
                }
                else if(lastnode.value == value){
                    if(lastnode.leftchild == null){ //wartości równe będziemy wstawiać po lewej
                        lastnode.leftchild = newnode;
                        newnode.parent = lastnode;
                        insert_fixup(newnode);
                        inserted = true;
                    }
                    else{
                        lastnode = lastnode.leftchild;
                    }
                }
            }

        }

    }
    void insert_fixup(Node x){ //should call ONLY in case of a problem.
        Node uncle = get_uncle(x);
        if(uncle.color == Node.Color.RED){
            recolor(uncle);
            recolor(x.parent);
            recolor(x.parent.parent);
        }
        else{
            if((x == x.parent.leftchild && x.parent == x.parent.parent.rightchild) || (x == x.parent.rightchild && x.parent == x.parent.parent.leftchild)){
                if(x == x.parent.leftchild){
                    right_rotate(x.parent);
                }
                else{
                    left_rotate(x.parent);
                }
            }
            else{
                Node par = x.parent;
                Node g_par = x.parent.parent;

                if(x == x.parent.leftchild){
                    right_rotate(g_par);
                }
                else{
                    left_rotate(g_par);
                }
                recolor(par);
                recolor(g_par);
            }
        }

    }
    Node get_uncle(Node x){
        if(x.parent == x.parent.parent.leftchild){
            return x.parent.parent.rightchild;
        }
        else {
            return x.parent.parent.leftchild;
        }
    }
    void recolor(Node z){
        if(z.color == Node.Color.BLACK){
            z.color = Node.Color.RED;
        }
        else {
            z.color = Node.Color.BLACK;
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
        Node.Color original_color;
        Node x;
        Node y;
        boolean removed = false;
        boolean foundrcsmallest = false;
        int val_to_return = -1;
        while (!removed){
            if(currentNode.key == key){ //gdy znaleźliśmy już węzeł do usunięcia
                val_to_return = currentNode.value;
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
        return val_to_return;
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
        boolean isright = false;
        boolean isleft = false;
        if(x_fix == x_fix.parent.leftchild){
            w = x_fix.parent.rightchild;
            isright = true;
        }
        else {
            w = x_fix.parent.leftchild;
            isleft = true;
        }
        while(x_fix != root && x_fix.color == Node.Color.BLACK){
            if(w.color == Node.Color.RED){
                w.color = Node.Color.BLACK;
                x_fix.color = Node.Color.RED;
                left_rotate(x_fix.parent);
                if(isleft){
                    w = x_fix.parent.leftchild;
                }
                if(isright){
                    w = x_fix.parent.rightchild;
                }

            }
            if (w.leftchild.color == Node.Color.BLACK && w.rightchild.color == Node.Color.BLACK) {
                w.color = Node.Color.RED;
                x_fix = x_fix.parent;
            }
            else {
                if (w.rightchild.color == Node.Color.BLACK) {
                    w.leftchild.color = Node.Color.BLACK;
                    w.color = Node.Color.RED;
                    right_rotate(w);
                    if(isleft){
                        w = x_fix.parent.leftchild;
                    }
                    if(isright){
                        w = x_fix.parent.rightchild;
                    }
                }

                    w.color = x_fix.parent.color;
                    x_fix.parent.color = Node.Color.BLACK;
                    w.rightchild.color = Node.Color.BLACK;
                    left_rotate(x_fix.parent);
                    x_fix = root;

            }
        }
        x_fix.color = Node.Color.BLACK;
    }
    void left_rotate(Node rnode){
        Node y = rnode.rightchild;
        rnode.rightchild = y.leftchild;
        if(y.leftchild != null){
            y.leftchild.parent = rnode;
        }
        y.parent = rnode.parent;
        if(rnode.parent == null){
            root = y;
        }
        else if(rnode == rnode.parent.leftchild){
            rnode.parent.leftchild = y;
        }
        else{
            rnode.parent.rightchild = y;
        }
        y.leftchild = rnode;
        rnode.parent = y;
    }

    void right_rotate(Node rnode){
        Node y = rnode.leftchild;
        rnode.leftchild = y.rightchild;
        if(y.rightchild != null){
            y.rightchild.parent = rnode;
        }
        y.parent = rnode.parent;
        if(rnode.parent == null){
            root = y;
        }
        else if(rnode == rnode.parent.rightchild){
            rnode.parent.rightchild = y;
        }
        else{
            rnode.parent.rightchild = y;
        }
        y.rightchild = rnode;
        rnode.parent = y;
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
