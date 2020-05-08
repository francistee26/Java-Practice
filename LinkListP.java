import java.util.Scanner;

public class LinkListP {

  public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);
            byte input1 = scanner.nextByte();
            byte input2 = scanner.nextByte();
            byte input3 = scanner.nextByte();

           NodeLL list = new NodeLL();
           list.data = input1; 
       System.out.print("[");
        while(input1 != 0){
            list.next = new NodeLL();
            
        }
        System.out.print("]");
        scanner.close();
}
    }