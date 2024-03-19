import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
class Node{
    String color;
    int key;
    int value;
    int key_of_parent;
    int layer_in_tree; //o 1 większe od layer_in_tree rodzica. metoda height zwróci największą wartość.
    int x_position; //pozycja lewo-prawo. jeśli rodzic ma już dzieci, porównujemy wartość value "rodzeństwa". jeśli jest większa od wartości tego węzła, przerzucamy na lewo, w innym przypadku - na prawo.
    public Node(String col, int k, int v, int l, int pk, int xpos){
        color = col;
        key = k;
        value = v;
        layer_in_tree = l;
        key_of_parent = pk;
        x_position = xpos;
    }

}
class RBTree{


    Random rand = new Random();
    ArrayList<Node> nodes_in_tree = new ArrayList<Node>();
    void insert(int key, int value, int parent_key){
        String insert_col = "";
        int xpos = 0;
        int layer_of_new_node = 0;
        if(parent_key == -1){ //jeśli wartość parent_key to -1, uznajmy, że tworzymy root drzewa.
            insert_col = "black";
        }
        else{
            for(int i = 0; i < nodes_in_tree.size(); i++){
                Node loopNode = nodes_in_tree.get(i);
                if(loopNode.key == parent_key){
                    if(loopNode.color.equals("red")){
                        insert_col = "black";
                    }
                    else{
                        int color_decider = rand.nextInt(2); //jeśli rodzicem jest obiekt czarny, dziecko może być czerwone lub czarne, więc możemy to wylosować.
                        switch(color_decider){
                            case 0:
                                insert_col = "red";
                                break;
                            default:
                                insert_col = "black";

                        }
                    }
                    layer_of_new_node = loopNode.layer_in_tree + 1;
                    if(value < loopNode.value){
                        xpos = loopNode.x_position -1;
                    }
                    else {
                        xpos = loopNode.x_position + 1;
                    }
                }

            }
        }
        Node new_node = new Node(insert_col, key, value, layer_of_new_node, parent_key, xpos);
        nodes_in_tree.add(new_node);
    }
    int get(int key){


        int found_value = -1;

        for(int i = 0; i < nodes_in_tree.size(); i++){
            Node current_node = nodes_in_tree.get(i);
            if(key == current_node.key){

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
            nodes_in_tree.remove(found_position);
            for(int i = 0; i < nodes_in_tree.size(); i++){
                Node current_node = nodes_in_tree.get(i);
                if(current_node.key_of_parent == key){
                    remove(current_node.key);
                }
            }//jeśli usuwamy rodzica, trzeba usunąć też jego dzieci.
            return found_position;

        }
        else{

            return -1;
        }
    }

    int height(){
        int last_layer = 0;
        for(int i = 0; i < nodes_in_tree.size(); i++){
            Node loopnode = nodes_in_tree.get(i);
            if(loopnode.layer_in_tree > last_layer){
                last_layer = loopnode.layer_in_tree;
            }
        }
        return last_layer;
    }

}

class HelloWorld {
    public static void main(String[] args) {
        RBTree new_tree = new RBTree();
        new_tree.insert(0, 5, -1);
    }
}
