import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int pilihan = 0;
        int pilihan2 = 0;
        int pembaca = 0;
        int save = 0;
        String filename = "";
        
        do{
            System.out.println("MENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi linier berganda");
            System.out.println("6. Keluar");
            System.out.print("-> Pilihan: ");
            pilihan = input.nextInt();
            switch (pilihan) 
            {
                case 1:
                    System.out.println("\n1. Metode eliminasi Gauss");
                    System.out.println("2. Metode eliminasi Gauss-Jordan");
                    System.out.println("3. Metode matriks balikan");
                    System.out.println("4. Kaidah Cramer");
                    System.out.print("-> Pilihan: ");
                    pilihan2 = input.nextInt();
                    
                    if (pilihan2==1)
                    {
                        System.out.print("\nMasukkan baris: ");
                        int baris1 = input.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int kolom1 = input.nextInt();
                        Matriks m1 = new Matriks(baris1, kolom1);

                        System.out.println("Membaca text(1) atau input(2) : ");
                        pembaca = input.nextInt();
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
                        save = input.nextInt();
                        if (save==1){
                            System.out.println("Nama file: ");
                            String saveas = input.next();
                            m1.saveSplSolution(m1.solveGauss(), saveas);
                        }
                        System.out.print("\n");
                    }
                    if(pilihan2==2)
                    {
                        System.out.print("Masukkan baris: ");
                        int baris2 = input.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int kolom2 = input.nextInt();
                        Matriks m2 = new Matriks(baris2, kolom2);

                        System.out.println("Membaca text(1) atau input(2) : ");
                        pembaca = input.nextInt();
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
                        save = input.nextInt();
                        if (save==1){
                            System.out.println("Nama file: ");
                            String saveas = input.next();
                            m2.saveSplSolution(m2.solveGauss(), saveas);
                        }
                        System.out.print("\n");
                    }
                    if(pilihan2==3)
                    {
                        System.out.print("Masukkan baris: ");
                        int baris3 = input.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int kolom3 = input.nextInt();
                        Matriks m3 = new Matriks(baris3, kolom3);

                        System.out.println("Membaca text(1) atau input(2) : ");
                        pembaca = input.nextInt();
                        if(pembaca == 2){
                            System.out.println("Isi matriks:");
                            m3.bacaMatriks();
                        }
                        else if(pembaca == 1){
                            System.out.println("Nama text: ");
                            filename = input.next();
                            m3.bacaMatriksfile(filename, baris3, kolom3);
                        }
                        m3.splInverse();
                        System.out.println(m3.splInverse());
                    }
                    if(pilihan2==4)
                    {
                        System.out.print("Masukkan baris: ");
                        int baris4 = input.nextInt();
                        Matriks m4 = new Matriks(baris4, baris4+1);

                        System.out.println("Membaca text(1) atau input(2) : ");
                        pembaca = input.nextInt();
                        if(pembaca == 2){
                            System.out.println("Isi matriks:");
                            m4.bacaMatriks();
                        }
                        else if(pembaca == 1){
                            System.out.println("Nama text: ");
                            filename = input.next();
                            m4.bacaMatriksfile(filename, baris4, baris4+1);

                        }

                        System.out.println(m4.splKramer());
                        System.out.println("\nMensave solution(1) atau tidak(0)?");
                        save = input.nextInt();
                        if (save==1){
                            System.out.println("Nama file: ");
                            String saveas = input.next();
                            m4.saveSplSolution(m4.splKramer(), saveas);
                        }
                        System.out.print("\n");
                    }

                case 2:
                    System.out.print("\n1. Metode Ekspansi Kofaktor");
                    System.out.print("\n2. Metode Reduksi Baris");
                    System.out.print("\n-> Pilihan: ");
                    pilihan2 = input.nextInt();

                    if(pilihan2 == 1)
                    {
                        System.out.print("\nMasukkan besar matriks: ");
                        int besar = input.nextInt();
                        Matriks m1 = new Matriks(besar, besar);

                        System.out.println("Membaca text(1) atau input(2) : ");
                        pembaca = input.nextInt();
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
                        double det = m1.KofaktorDet(m1.m,besar);
                        String detsolution = ("Determinan adalah "+det);
                        System.out.println(detsolution);
                        System.out.println("\nMensave solution(1) atau tidak(0)?");
                        save = input.nextInt();
                        if (save==1){
                            System.out.println("Nama file: ");
                            String saveas = input.next();
                            m1.saveStringSolution(detsolution, saveas);
                        }
                        System.out.print("\n");
                    }
                    if(pilihan2 == 2)
                    {
                        System.out.print("\nMasukkan besar matriks: ");
                        int besar = input.nextInt();
                        Matriks m1 = new Matriks(besar, besar);

                        System.out.println("Membaca text(1) atau input(2) : ");
                        pembaca = input.nextInt();
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
                        double det = m1.ReduksiDet(m1.m,besar);
                        String detsolution = ("Determinan adalah "+det);
                        System.out.println(detsolution);
                        System.out.println("\nMensave solution(1) atau tidak(0)?");
                        save = input.nextInt();
                        if (save==1){
                            System.out.println("Nama file: ");
                            String saveas = input.next();
                            m1.saveStringSolution(detsolution, saveas);
                        }
                        System.out.print("\n");
                    }
                case 3:
                    System.out.print("\nMasukkan besar matriks: ");
                    int besar = input.nextInt();
                    Matriks m3 = new Matriks(besar, besar);
                    System.out.println("Membaca text(1) atau input(2) : ");
                    pembaca = input.nextInt();
                    if(pembaca == 2){
                        System.out.println("Isi matriks:");
                        m3.bacaMatriks();
                    }
                    else if(pembaca == 1){
                        System.out.println("Nama text: ");
                        filename = input.next();
                        m3.bacaMatriksfile(filename, besar, besar);
                    }
                    m3.inverseMatriksGauss();
                    m3.tulisMatriks();
                    System.out.println("\nMensave solution(1) atau tidak(0)?");
                    save = input.nextInt();
                    if (save==1){
                        System.out.println("Nama file: ");
                        String saveas = input.next();
                        m3.saveMatrix(saveas);
                    }
                    System.out.print("\n");
                case 4:
                    System.out.print("Banyak persamaan: ");
                    int n = input.nextInt();
                    Matriks mpol = new Matriks(n,2);
                    System.out.println("Membaca text(1) atau input(2) : ");
                        pembaca = input.nextInt();
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
                    //System.out.println("Persamaan matriks dalam SPL:");
                    //mpol.tulisMatriks();
                    mpol.splGauss();
                    //System.out.println("Hasil matriks:");
                    //mpol.tulisMatriks();

                    System.out.print("Dicari f(x) untuk x = ");
                    double x = input.nextDouble();
                    System.out.println(mpol.printPolinomsolution());
                    String polSolution = "f(x) = " + mpol.solvePolinom(x) + " untuk x = " + x;
                    System.out.println(polSolution);
                    String Result = mpol.printPolinomsolution();
                    Result += ("\n"+polSolution);
                    System.out.println("\nMensave solution(1) atau tidak(0)?");
                    save = input.nextInt();
                    if (save==1){
                        System.out.println("Nama file: ");
                        String saveas = input.next();
                        mpol.saveStringSolution(Result, saveas);
                    }
                    System.out.print("\n");
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
                    break;
            }
        } while (pilihan!=6);
        System.out.println("Aplikasi dimatikan.");
        input.close();
    }
}