import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan baris: ");
        int baris1 = input.nextInt();
        System.out.print("Masukkan kolom: ");
        int kolom1 = input.nextInt();
        Matriks m1= new Matriks(baris1, kolom1);

        System.out.println("Isi matriks:");
        m1.bacaMatriks();
        
        System.out.println("Hasil matriks:");
        m1.tulisMatriks();
        
        input.close();
    }
}
