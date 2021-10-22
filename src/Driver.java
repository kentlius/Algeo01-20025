import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int pilihan = 0;
        int pilihan2 = 0;
        int read = 0;
        int save;
        String filename="";
        
        do{
            System.out.println("================================");
            System.out.println("=----------MENU UTAMA----------=");
            System.out.println("================================");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi linier berganda");
            System.out.println("6. Keluar");
            System.out.print("-> Pilihan: ");
            pilihan = input.nextInt();
            System.out.println("");

            if(pilihan==1)
            {
                System.out.println("================================");
                System.out.println("=---------Pilih Metode---------=");
                System.out.println("================================");
                System.out.println("1. Metode eliminasi Gauss");
                System.out.println("2. Metode eliminasi Gauss-Jordan");
                System.out.println("3. Metode matriks balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.print("-> Pilihan: ");
                pilihan2 = input.nextInt();
                System.out.println("");

                if(pilihan2==1)
                {
                    System.out.print("Masukkan baris: ");
                    int baris1 = input.nextInt();
                    System.out.print("Masukkan kolom: ");
                    int kolom1 = input.nextInt();
                    Matriks m1 = new Matriks(baris1, kolom1);
                    System.out.println("");

                    System.out.println("1. Membaca File");
                    System.out.println("2. Input Matriks");
                    System.out.print("-> Pilihan: ");
                    read = input.nextInt();
                    System.out.println("");
                    if(read == 2){
                        System.out.println("Isi matriks:");
                        m1.bacaMatriks();
                    }
                    else if(read == 1){
                        System.out.print("Nama file: ");
                        filename = input.next();
                        m1.bacaMatriksfile(filename, baris1, kolom1);
                    }

                    m1.splGauss();
                    System.out.println("Hasil matriks:");
                    m1.tulisMatriks();
                    System.out.println(m1.solveGauss());

                    System.out.print("\nSimpan hasil dalam file (1[yes]/0[no])?");
                    save = input.nextInt();
                    if (save==1){
                        System.out.print("Nama file: ");
                        String saveas = input.next();
                        m1.saveSplSolution(m1.solveGauss(), saveas);
                    }
                    System.out.println("");
                }
                if(pilihan2==2)
                {
                    System.out.print("Masukkan baris: ");
                    int baris2 = input.nextInt();
                    System.out.print("Masukkan kolom: ");
                    int kolom2 = input.nextInt();
                    Matriks m2 = new Matriks(baris2, kolom2);
                    System.out.println("");

                    System.out.println("1. Membaca File");
                    System.out.println("2. Input Matriks");
                    System.out.print("-> Pilihan: ");
                    read = input.nextInt();
                    System.out.println("");
                    if(read == 2){
                        System.out.println("Isi matriks:");
                        m2.bacaMatriks();
                    }
                    else if(read == 1){
                        System.out.print("Nama file: ");
                        filename = input.next();
                        m2.bacaMatriksfile(filename, baris2, kolom2);
                    }

                    m2.splGaussJordan();
                    System.out.println("Hasil matriks:");
                    m2.tulisMatriks();
                    System.out.println(m2.solveGauss());

                    System.out.print("\nSimpan hasil dalam file (1[yes]/0[no])?");
                    save = input.nextInt();
                    if (save==1){
                        System.out.print("Nama file: ");
                        String saveas = input.next();
                        m2.saveSplSolution(m2.solveGauss(), saveas);
                    }
                    System.out.println("");
                }
                if(pilihan2==3)
                {
                    System.out.print("Masukkan baris: ");
                    int baris3 = input.nextInt();
                    System.out.print("Masukkan kolom: ");
                    int kolom3 = input.nextInt();
                    Matriks m3 = new Matriks(baris3, kolom3);
                    System.out.println("");

                    System.out.println("1. Membaca File");
                    System.out.println("2. Input Matriks");
                    System.out.print("-> Pilihan: ");
                    read = input.nextInt();
                    System.out.println("");
                    if(read == 2){
                        System.out.println("Isi matriks:");
                        m3.bacaMatriks();
                    }
                    else if(read == 1){
                        System.out.print("Nama file: ");
                        filename = input.next();
                        m3.bacaMatriksfile(filename, baris3, kolom3);
                    }

                    if(m3.splInverse()==null)
                    {
                        System.out.println("Determinan = 0, matriks tidak memiliki balikan dan hasil SPL.");
                    }
                    else
                    {
                        System.out.println("Hasil matriks:");
                        m3.tulisMatriks();
                        System.out.println(m3.splInverse());
                    }

                    System.out.print("\nSimpan hasil dalam file (1[yes]/0[no])?");
                    save = input.nextInt();
                    if (save==1){
                        System.out.print("Nama file: ");
                        String saveas = input.next();
                        m3.saveSplSolution(m3.solveGauss(), saveas);
                    }
                    System.out.println("");
                }
                if(pilihan2==4)
                {
                    System.out.print("Masukkan baris: ");
                    int baris4 = input.nextInt();
                    Matriks m4 = new Matriks(baris4, baris4+1);
                    System.out.println("");

                    System.out.println("1. Membaca File");
                    System.out.println("2. Input Matriks");
                    System.out.print("-> Pilihan: ");
                    read = input.nextInt();
                    System.out.println("");
                    if(read == 2){
                        System.out.println("Isi matriks:");
                        m4.bacaMatriks();
                    }
                    else if(read == 1){
                        System.out.print("Nama file: ");
                        filename = input.next();
                        m4.bacaMatriksfile(filename, baris4, baris4+1);
                    }

                    System.out.println(m4.splKramer());

                    System.out.print("\nSimpan hasil dalam file (1[yes]/0[no])?");
                    save = input.nextInt();
                    if (save==1){
                        System.out.print("Nama file: ");
                        String saveas = input.next();
                        m4.saveSplSolution(m4.splKramer(), saveas);
                    }
                    System.out.println("");
                }
            }
                    
            if(pilihan==2)
            {
                System.out.println("================================");
                System.out.println("=---------Pilih Metode---------=");
                System.out.println("================================");
                System.out.println("1. Metode Ekspansi Kofaktor");
                System.out.println("2. Metode Reduksi Baris");
                System.out.print("-> Pilihan: ");
                pilihan2 = input.nextInt();
                System.out.println("");

                if(pilihan2 == 1)
                {
                    System.out.print("Masukkan besar matriks: ");
                    int besar = input.nextInt();
                    Matriks m1 = new Matriks(besar, besar);
                    System.out.println("");

                    System.out.println("1. Membaca File");
                    System.out.println("2. Input Matriks");
                    System.out.print("-> Pilihan: ");
                    read = input.nextInt();
                    System.out.println("");
                    if(read == 2){
                        System.out.println("Isi matriks:");
                        m1.bacaMatriks();
                    }
                    else if(read == 1){
                        System.out.print("Nama file: ");
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

                    System.out.print("\nSimpan hasil dalam file (1[yes]/0[no])?");
                    save = input.nextInt();
                    if (save==1){
                        System.out.print("Nama file: ");
                        String saveas = input.next();
                        m1.saveStringSolution(detsolution, saveas);
                    }
                    System.out.println("");
                }

                if(pilihan2 == 2)
                {
                    System.out.print("Masukkan besar matriks: ");
                    int besar = input.nextInt();
                    Matriks m1 = new Matriks(besar, besar);
                    System.out.println("");

                    System.out.println("1. Membaca File");
                    System.out.println("2. Input Matriks");
                    System.out.print("-> Pilihan: ");
                    read = input.nextInt();
                    System.out.println("");
                    if(read == 2){
                        System.out.println("Isi matriks:");
                        m1.bacaMatriks();
                    }
                    else if(read == 1){
                        System.out.print("Nama file: ");
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

                    System.out.print("\nSimpan hasil dalam file (1[yes]/0[no])?");
                    save = input.nextInt();
                    if (save==1){
                        System.out.print("Nama file: ");
                        String saveas = input.next();
                        m1.saveStringSolution(detsolution, saveas);
                    }
                    System.out.println("");
                }
            }

            if(pilihan==3)
            {
                System.out.print("Masukkan besar matriks: ");
                int besar = input.nextInt();
                Matriks m = new Matriks(besar, besar);
                System.out.println("");

                System.out.println("1. Membaca File");
                System.out.println("2. Input Matriks");
                System.out.print("-> Pilihan: ");
                read = input.nextInt();
                System.out.println("");
                if(read == 2){
                    System.out.println("Isi matriks:");
                    m.bacaMatriks();
                }
                else if(read == 1){
                    System.out.print("Nama file: ");
                    filename = input.next();
                    m.bacaMatriksfile(filename, besar, besar);
                }

                if (m.inverseMatriksGauss()==null)
                {
                    System.out.println("Determinan = 0, matriks tidak memiliki balikan.");
                }
                else
                {
                    System.out.println("Hasil Inverse:");
                    m.tulisMatriks();
                }
                
                System.out.print("\nSimpan hasil dalam file (1[yes]/0[no])?");
                    save = input.nextInt();
                    if (save==1){
                    System.out.print("Nama file: ");
                    String saveas = input.next();
                    m.saveMatrix(saveas);
                }
                System.out.println("");
            }

            if(pilihan==4)
            {
                System.out.print("Banyak persamaan: ");
                int n = input.nextInt();
                Matriks mpol = new Matriks(n,2);
                System.out.println("");

                System.out.println("1. Membaca File");
                System.out.println("2. Input Matriks");
                System.out.print("-> Pilihan: ");
                read = input.nextInt();
                System.out.println("");
                if(read == 2){
                    System.out.println("Isi matriks:");
                    mpol.bacaMatriks();
                }
                else if(read == 1){
                    System.out.print("Nama file: ");
                    filename = input.next();
                    mpol.bacaMatriksfile(filename, n, 2);
                }

                mpol.polinomMatriks();
                //System.out.println("Persamaan matriks dalam SPL:");
                //mpol.tulisMatriks();
                mpol.splGauss();
                System.out.println("Hasil matriks:");
                mpol.tulisMatriks();

                System.out.print("Dicari f(x) untuk x = ");
                double x = input.nextDouble();
                System.out.println(mpol.printPolinomsolution());
                String polSolution = "f(x) = " + mpol.solvePolinom(x) + " untuk x = " + x;
                System.out.println(polSolution);
                String Result = mpol.printPolinomsolution();
                Result += ("\n"+polSolution);

                System.out.print("\nSimpan hasil dalam file (1[yes]/0[no])?");
                    save = input.nextInt();
                    if (save==1){
                    System.out.print("Nama file: ");
                    String saveas = input.next();
                    mpol.saveStringSolution(Result, saveas);
                }
                System.out.println("");
            }

            if(pilihan==5)
            {
                System.out.print("Banyak peubah: ");
                int peubah = input.nextInt();
                System.out.print("Banyak persamaan: ");
                int persamaan = input.nextInt();
                Matriks mreg = new Matriks(peubah+1,peubah+2);
                Matriks rawdata = new Matriks(persamaan,peubah+1);
                System.out.println("");

                System.out.println("1. Membaca File");
                System.out.println("2. Input Matriks");
                System.out.print("-> Pilihan: ");
                read = input.nextInt();
                System.out.println("");
                if(read == 2){
                    System.out.println("Isi matriks:");
                    rawdata.bacaMatriks();
                }
                else if(read == 1){
                    System.out.print("Nama file: ");
                    filename = input.next();
                    rawdata.bacaMatriksfile(filename, persamaan, peubah+1);
                }     

                mreg.Regresi(peubah, persamaan,rawdata);
                mreg.tulisMatriks();
                mreg.splGauss();
                System.out.println(mreg.printRegresisolution());
                System.out.println("Dicari y untuk: ");
                double x1[] = new double[peubah];
                for(int i=0;i<peubah;i++)
                {
                    System.out.print("x"+(i+1)+": ");
                    x1[i] = input.nextDouble();
                }
                String RegSolution = "y = " + mreg.solveRegresi(x1);
                System.out.println(RegSolution);
                String regResult = mreg.printPolinomsolution();
                regResult += ("\n"+RegSolution);

                System.out.print("\nSimpan hasil dalam file (1[yes]/0[no])?");
                save = input.nextInt();
                if (save==1){
                    System.out.print("Nama file: ");
                    String saveas = input.next();
                    mreg.saveStringSolution(regResult, saveas);
                }
                System.out.println("");
            }

        } while (pilihan!=6);
        System.out.println("Aplikasi dimatikan.");
        input.close();
    }
}