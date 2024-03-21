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
    Node right, left, parent;

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
    void insert(int key, int value){
        if(root == null){
            root = new Node(Node.Color.BLACK , key, value);
        }

    }
    int get(int key){


        int found_value = -1;

        for (Node current_node : nodes_in_tree) {
            if (key == current_node.key) {

                found_value = current_node.value;

            }
        }
        return found_value;
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
