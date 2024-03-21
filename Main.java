// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.ArrayList;
import java.util.Random;

class HelloWorld {
    public static void main(String[] args) {
        RBTree new_tree = new RBTree();
        new_tree.insert(0, 5, -1);
        new_tree.insert(1, 8, 0);
        new_tree.insert(2, 3, 0);
        int get_value = new_tree.get(2);
        System.out.println(get_value);
        int height_val = new_tree.height();
        System.out.println(height_val);
        int remove_val = new_tree.remove(1);
        System.out.println(remove_val);
    }
}
class Node{

    int key;
    int value;
    Node rightchild, leftchild, parent;

    enum Color {RED, BLACK}

    Color color;

    
    
    public Node(Color c, int k, int v{
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
            
            newnode = new Node(Node.Color.RED, key, value); //przy wstawianiu zawsze ustawiamy kolor na czerwony, potem poprawimy
            lastnode = root;
        while(!inserted){
            if(lastnode.value < value && lastnode.rightchild == null){
                lastnode.rightchild = newnode;
            }
            else if(lastnode.value < value && lastnode.rightchild != null){
                lastnode = lastnode.rightchild;
            }
            else if(lastnode.value > value && lastnode.leftchild == null){
                lastnode.leftchild = newnode;
            }
            else if(lastnode.value > value && lastnode.leftchild != null){
                lastnode = lastnode.leftchild;
            }
        }    
            
        }

    }
   int get(int key){
        Node currentNode = root;
        while (currentNode != null){
            if(currentNode.key == key){
                return curNode.value;
                break;
            }
            else if(key < currentNode.key){
                if(currentNode.leftchild != null){
                    currentNode = currentNode.leftchild;
                }
                else{
                    return -1;
                    break;
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
    int remove(int key){
        boolean is_found = false;
        int found_position = -1;
        for(int i = 0; i < nodes_in_tree.size(); i++){
            Node current_node = nodes_in_tree.get(i);
            if(key == current_node.key){
                is_found = true;
                found_position = i;
            }

        }
        if(is_found){
            int value_to_return;
            Node node_to_remove;
            node_to_remove = nodes_in_tree.get(found_position);
            value_to_return = node_to_remove.value;
            nodes_in_tree.remove(found_position);
            for(int i = 0; i < nodes_in_tree.size(); i++){
                Node current_node = nodes_in_tree.get(i);
                if(current_node.key_of_parent == key){
                    nodes_in_tree.remove(i);
                }
            }
            return value_to_return;

        }
        else{

            return -1;
        }
    }

    int height(){
        int last_layer = 0;
        for (Node loopnode : nodes_in_tree) {
            if (loopnode.layer_in_tree > last_layer) {
                last_layer = loopnode.layer_in_tree;
            }
        }
        return last_layer;
    }

}
