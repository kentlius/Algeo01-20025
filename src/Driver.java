import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int pilihan = 0;
        int pilihanspl = 0;
        String filename = "";
        
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
                    System.out.print("\nMasukkan baris: ");
                    int baris1 = input.nextInt();
                    System.out.print("Masukkan kolom: ");
                    int kolom1 = input.nextInt();
                    Matriks m1 = new Matriks(baris1, kolom1);

                    System.out.println("Membaca text(1) atau input(2) : ");
                    int pembaca = input.nextInt();
                    if(pembaca == 2){
                        System.out.println("Isi matriks:");
                        m1.bacaMatriks();
                    }
                    else if(pembaca == 1){
                        System.out.println("Nama text: ");
                        filename = input.next();
                        m1.bacaMatriksfile(filename, baris1, kolom1);

                    }
                    m1.splGauss();
                    
                    System.out.println("Hasil matriks:");
                    m1.tulisMatriks();

                    System.out.println(m1.solveGauss());
                    System.out.println("\nMensave solution(1) atau tidak(0)?");
                    int save = input.nextInt();
                    if (save==1){
                        System.out.println("Nama file: ");
                        String saveas = input.next();
                        m1.saveSplSolution(m1.solveGauss(), saveas);
                    }
                    System.out.print("\n");
                }
                if(pilihanspl==2)
                {
                    System.out.print("Masukkan baris: ");
                    int baris2 = input.nextInt();
                    System.out.print("Masukkan kolom: ");
                    int kolom2 = input.nextInt();
                    Matriks m2 = new Matriks(baris2, kolom2);

                    System.out.println("Membaca text(1) atau input(2) : ");
                    int pembaca = input.nextInt();
                    if(pembaca == 2){
                        System.out.println("Isi matriks:");
                        m2.bacaMatriks();
                    }
                    else if(pembaca == 1){
                        System.out.println("Nama text: ");
                        filename = input.next();
                        m2.bacaMatriksfile(filename, baris2, kolom2);

                    }
                    m2.splGaussJordan();
                    
                    System.out.println("Hasil matriks:");
                    m2.tulisMatriks();

                    System.out.println(m2.solveGauss());
                    System.out.println("\nMensave solution(1) atau tidak(0)?");
                    int save = input.nextInt();
                    if (save==1){
                        System.out.println("Nama file: ");
                        String saveas = input.next();
                        m2.saveSplSolution(m2.solveGauss(), saveas);
                    }
                    System.out.print("\n");
                }
            }
            if (pilihan==2)
            {
                int pilihandet = 0;
                System.out.print("\n1. Metode Ekspansi Kofaktor");
                System.out.print("\n2. Metode Reduksi Baris");
                System.out.print("\n-> Pilihan: ");
                pilihandet = input.nextInt();
                if(pilihandet == 1)
                {
                    System.out.print("\nMasukkan besar matriks: ");
                    int besar = input.nextInt();
                    Matriks m1 = new Matriks(besar, besar);

                    System.out.println("Membaca text(1) atau input(2) : ");
                    int pembaca = input.nextInt();
                    if(pembaca == 2){
                        System.out.println("Isi matriks:");
                        m1.bacaMatriks();
                    }
                    else if(pembaca == 1){
                        System.out.println("Nama text: ");
                        filename = input.next();
                        m1.bacaMatriksfile(filename, besar, besar);

                    }
                    double m2[][] = new double[besar][besar]; 
                    for (int i=0;i<besar;i++)
                    {
                        for (int j=0;j<besar;j++)
                        {
                            m2[i][j] = m1.getElm(i,j);
                        }
                    }
                    double det = m1.KofaktorDet(besar);
                    String detsolution = ("Determinan adalah "+det);
                    System.out.println(detsolution);
                    System.out.println("\nMensave solution(1) atau tidak(0)?");
                    int save = input.nextInt();
                    if (save==1){
                        System.out.println("Nama file: ");
                        String saveas = input.next();
                        m1.saveStringSolution(detsolution, saveas);
                    }
                    System.out.print("\n");
                }
                if(pilihandet == 2)
                {
                    System.out.print("\nMasukkan besar matriks: ");
                    int besar = input.nextInt();
                    Matriks m1 = new Matriks(besar, besar);

                    System.out.println("Membaca text(1) atau input(2) : ");
                    int pembaca = input.nextInt();
                    if(pembaca == 2){
                        System.out.println("Isi matriks:");
                        m1.bacaMatriks();
                    }
                    else if(pembaca == 1){
                        System.out.println("Nama text: ");
                        filename = input.next();
                        m1.bacaMatriksfile(filename, besar, besar);

                    }
                    double m2[][] = new double[besar][besar]; 
                    for (int i=0;i<besar;i++)
                    {
                        for (int j=0;j<besar;j++)
                        {
                            m2[i][j] = m1.getElm(i,j);
                        }
                    }
                    double det = m1.ReduksiDet(besar);
                    String detsolution = ("Determinan adalah "+det);
                    System.out.println(detsolution);
                    System.out.println("\nMensave solution(1) atau tidak(0)?");
                    int save = input.nextInt();
                    if (save==1){
                        System.out.println("Nama file: ");
                        String saveas = input.next();
                        m1.saveStringSolution(detsolution, saveas);
                    }
                    System.out.print("\n");
                }
            }
            if (pilihan==3)
            {
                System.out.print("Masukkan baris: ");
                int baris3 = input.nextInt();
                System.out.print("Masukkan kolom: ");
                int kolom3 = input.nextInt();
                Matriks m3 = new Matriks(baris3, kolom3);

                m3.bacaMatriks();
                m3.inverseMatriks();
                m3.tulisMatriks();

            }
            if (pilihan==4)
            {
                System.out.print("Banyak persamaan: ");
                int n = input.nextInt();
                Matriks mpol = new Matriks(n,2);
                System.out.println("Membaca text(1) atau input(2) : ");
                    int pembaca = input.nextInt();
                    if(pembaca == 2){
                        System.out.println("Isi matriks:");
                        mpol.bacaMatriks();
                    }
                    else if(pembaca == 1){
                        System.out.println("Nama text: ");
                        filename = input.next();
                        mpol.bacaMatriksfile(filename, n, 2);
                    }
                mpol.polinomMatriks();
                System.out.println("Persamaan matriks dalam SPL:");
                mpol.tulisMatriks();
                mpol.splGauss();
                System.out.println("Hasil matriks:");
                mpol.tulisMatriks();

                System.out.print("Dicari f(x) untuk x = ");
                double x = input.nextInt();
                System.out.println(mpol.printPolinomsolution());
                String polSolution = "f(x) = " + mpol.solvePolinom(x) + " untuk x = " + x;
                System.out.println(polSolution);
                String Result = mpol.printPolinomsolution();
                Result += ("\n"+polSolution);
                System.out.println("\nMensave solution(1) atau tidak(0)?");
                int save = input.nextInt();
                if (save==1){
                    System.out.println("Nama file: ");
                    String saveas = input.next();
                    mpol.saveStringSolution(Result, saveas);
                }
                System.out.print("\n");
            }
        } while (pilihan!=6);
        System.out.println("Aplikasi dimatikan.");
        input.close();
    }
}
