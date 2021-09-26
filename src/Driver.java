import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan = 0;
        int pilihanspl = 0;
        do{
            System.out.print("MENU");
            System.out.print("\n1. Sistem Persamaan Linier");
            System.out.print("\n2. Determinan");
            System.out.print("\n3. Matriks Balikan");
            System.out.print("\n4. Interpolasi Polinom");
            System.out.print("\n5. Regresi Linier berganda");
            System.out.print("\n6. Keluar");
            System.out.print("\n-> Pilihan: ");
            pilihan = input.nextInt();
            if (pilihan==1)
            {
                System.out.print("\n1. Metode Eliminasi Gauss");
                System.out.print("\n2. Metode Eliminasi Gauss Jordan");
                System.out.print("\n3. Metode matriks balikan");
                System.out.print("\n4. Kaidah Cramer");
                System.out.print("\n-> Pilihan: ");
                pilihanspl = input.nextInt();
                if (pilihanspl==1)
                {
                    System.out.print("Masukkan baris: ");
                    int baris1 = input.nextInt();
                    System.out.print("Masukkan kolom: ");
                    int kolom1 = input.nextInt();
                    Matriks m1 = new Matriks(baris1, kolom1);

                    System.out.println("Isi matriks:");
                    m1.bacaMatriks();
                    m1.splGauss();
                    
                    System.out.println("Hasil matriks:");
                    m1.tulisMatriks();

                    System.out.println(m1.solveGauss());
                }
                if(pilihanspl==2)
                {
                    System.out.print("Masukkan baris: ");
                    int baris2 = input.nextInt();
                    System.out.print("Masukkan kolom: ");
                    int kolom2 = input.nextInt();
                    Matriks m2 = new Matriks(baris2, kolom2);

                    System.out.println("Isi matriks:");
                    m2.bacaMatriks();
                    m2.splGaussJordan();
                    
                    System.out.println("Hasil matriks:");
                    m2.tulisMatriks();

                    System.out.println(m2.solveGauss());
                }
            }
        } while (pilihan!=6);
        System.out.println("\nAplikasi dimatikan.");
        input.close();
    }
}
