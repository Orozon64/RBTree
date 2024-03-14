import java.util.ArrayList;
import java.util.random;
import java.util.Scanner;
class Node{
    String color;
    int key;
    int value;
    int key_of_parent;
    int layer_in_tree; //o 1 większe od layer_in_tree rodzica. metoda height zwróci największą wartość.
    int x_position; //pozycja lewo-prawo. jeśli rodzic ma już dzieci, porównujemy wartość value "rodzeństwa". jeśli jest większa od wartości tego węzła, przerzucamy na lewo, w innym przypadku - na prawo.
    public Node(String col, int k, int v, int l){
        color = col;
        key = k;
        value = v;
        layer_in_tree = l;
    }

}
class RBTree{
    Scanner scn = new Scanner(System.in);

    Random rand = new Random();
    ArrayList<node> nodes_in_tree = new ArrayList<node>();
    void insert(int key, int value, int parent_key){
        String insert_col;
        System.out.println("Podaj klucz nowego węzła"); //później treba dodać algorytm który zapewnia, że każdy węzeł ma unikalny klucz
        String key_string = scn.nextLine();

        int key_of_new_node = Integer.parseInt(key_string);

        System.out.println("Podaj wartość nowego węzła");
        String val_string = scn.nextLine();

        int val_of_new_node = Integer.parseInt(val_string);
        int layer_of_new_node = 0;
        if(nodes_in_tree.size() == 0){
            insert_col = "black";
        }
        else{
            for(int i = 0; i < nodes_in_tree.size(); i++){
                if(nodes_in_tree[i].key == parent_key){
                    if(nodes_in_tree[i].color == "red"){
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
                    layer_of_new_node = nodes_in_tree[i].layer_in_tree;
                }
            }
        }
        Node new_node = new Node(insert_col, key_of_new_node, val_of_new_node, layer_of_new_node);
    }
    int get(int key){
        bool is_found = false;
        int found_position;
        for(int i = 0; i < nodes_in_tree.size(); i++){
            node current_node = nodes_in_tree.get(i);
            if(key == current_node.key){
                is_found = true;
                found_position = i;
            }
        }
        if(is_found){
            return found_position;
        }
        else{
            return -1;
        }
    }
    int remove(int key){
        bool is_found = false;
        int found_position;
        for(int i = 0; i < nodes_in_tree.size(); i++){
            node current_node = nodes_in_tree.get(i);
            if(key == current_node.key){
                is_found = true;
                found_position = i;
            }
        }
        if(is_found){
            nodes_in_tree.remove(found_position);
            return found_position;
        }
        else{

            return -1;
        }
    }

    int height(){
        int last_layer = 0;
        for(int i = 0; i < nodes_in_tree.size(); i++){
            if(nodes_in_tree[i].layer_in_tree > last_layer){
                
            }
        }
    }

}

class HelloWorld {
    public static void main(String[] args) {
        RBTree new_tree = new RBTree;
        new_tree.insert(0, 5);
    }
}