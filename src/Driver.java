import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Matriks obj= new Matriks();
        int n = input.nextInt();
        obj.buatMatriks(n);
        obj.bacaMatriks();
        obj.tulisMatriks();

        input.close();
    }
}
