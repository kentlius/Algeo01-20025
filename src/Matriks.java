import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matriks {
    public int brs;
    public int kol;
    public double m[][];

    Matriks(int baris, int kolom){
        brs=baris;
        kol=kolom;
        m=new double[baris][kolom];
    }

    public void bacaMatriks() {
        Scanner input = new Scanner(System.in);
        int i,j;
        for(i=0;i<brs;i++)
            for(j=0;j<kol;j++)
                m[i][j]=input.nextDouble();
    }
    
    //error line 49 karna nextLine??
    public void bacaMatriksTxt() {
        try 
        {
            File file = new File("test/spl.txt");

            Scanner sizeScanner = new Scanner(file);
            String curr;
            int temp=0, temp2=0;
            while(sizeScanner.hasNextLine())
            {
                curr=sizeScanner.nextLine();
                temp = curr.split(" ").length;
                temp2++;
            }
                
            sizeScanner.close();

            brs = temp;
            kol = temp2;

            Scanner scanner = new Scanner(file);
            m = new double[brs][kol];
            for (int i = 0; i < brs; i++) 
            {
                String[] numbers = scanner.nextLine().split(" ");
                for (int j = 0; j < kol; j++) 
                {
                    m[i][j] = Integer.parseInt(numbers[j]);
                }
            }
            scanner.close();
        }   
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        
    }

    public void tulisMatriks() {
        int i,j;

        for(i=0;i<brs;++i)
        {
            for(j=0;j<kol;++j)
            {
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }

    public double[][] swapBaris(int baris1, int baris2, double m[][]){
        double temp;
        for(int j=0;j<kol;j++)
        {
            temp = m[baris1][j];
            m[baris1][j] = m[baris2][j];
            m[baris2][j] = temp;
        }
        return m;
    }
    
    public double[][] splGauss() {
        // mencari elemen yang bukan 0 untuk penentuan pertukaran baris
        for (int i=0;i<brs;i++)
        {
            int notzeroi = i;
            int notzeroj = i;
            boolean notZero = false;
            for(int j=0;j<kol-1;j++)
            {
                if (m[i][j] == 0)
                {
                    for(int i1=i;i1<brs;i1++)
                    {
                        if(m[i1][j]!=0)
                        // melakukan pertukaran baris
                        {
                            m = swapBaris(i, i1, m);
                            notZero = true;
                            break;
                        }
                    }
                if (notZero)
                {
                    break;
                }
                }
            }

            // membagi baris agar yang paling depan 1
            double head = m[notzeroi][notzeroj];
            if(head!=0)
            {
                for (int j=0;j<kol;j++)
                {
                    m[i][j] = m[i][j]/head;
                }
            }

            // mengurangi baris dibawahnya hingga 0 menjadi eselon baris
            if (i<kol-1)
            {
                for (int i1=i+1;i1<brs;i1++)
                {
                    double konstanta = m[i1][notzeroj];
                    for (int j1=notzeroj;j1<kol;j1++)
                    {
                        m[i1][j1] -= m[notzeroi][j1]*konstanta;
                    }
                }
            }   
        }
        return m;
    }
    
    public String[] solveGauss()
    {
        String[] solution = new String[kol-1];
        String x[] = new String[kol-1];
        int banyakzero = 0;
        boolean[] variabel = new boolean[kol-1];
        // menentukan jika tidak ada solusi (ada baris yang semuanya bernilai 0)
        boolean solvable = true;
        for (int i=0;i<brs;i++){
            banyakzero = 0;
            for (int j=0;j<kol;j++){
                if (m[i][j]==0){
                    banyakzero += 1;
                }
            }
            if(banyakzero == kol-1){
                solvable = false;
                break;
            }
        }
        if(!solvable)
        {
            System.out.println("Sistem persamaan linear tidak ada solusi.");
            solution = null;
        } else{
            boolean hanyaAngka = true;
            for(int i=0;i<brs;i++)
            {
                if (m[i][i]==0)
                {
                    hanyaAngka = false;
                }
            }
            // solusi hanya dalam bentuk angka
            if ((brs == kol-1) && (hanyaAngka==true)){
                for(int i=kol-2;i>=0;i--){
                    if(i==kol-2){
                        x[i] = Double.toString(m[i][kol-1]);
                    } else {
                        double temp = m[i][kol-1];
                        for(int j=i+1;j<kol-1;j++){
                            temp -= m[i][j]*Double.valueOf(x[j]);
                        }
                        x[i] = Double.toString(temp);
                    }
                }
            }
            // solusi dalam bentuk variabel
            else {
                // menentukan elemen yang tidak berbentuk variabel
                for (int i=0; i<(kol-1);i++)
                {
                    variabel[i] = true;
                }

                for (int i=0; i<(brs);i++)
                {
                    for (int j=0; j<(kol-1);j++)
                    {
                        if (m[i][j]==1)
                        {
                            variabel[j] = false;
                            break;
                        }
                    }
                }
                
                for (int i=0;i<(kol-1);i++)
                {
                    if(variabel[i])
                    {
                        x[i] = ("x" + (i+1));
                    }
                    else
                    {
                        x[i] = "0.0";
                    }
                }

                
                // Menjumlahkan hanya yang dalam bentuk angka
                for (int i=brs-1;i>=0;i--)
                {
                    double temp = m[i][kol-1];
                    if(i<brs-1)
                    {
                        for(int j=i+1;j<brs;j++)
                        {
                            temp -= m[i][j]*Double.valueOf(x[j]);
                        }
                        x[i] = Double.toString(temp);
                    }
                    else
                    {
                        x[i] = Double.toString(temp);
                    }
                }

                // Menjumlahkan variabel ke dalam jawaban
                for(int k=0;k<kol-1;k++)
                {
                    if(variabel[k])
                    {
                        double variabelk[] = new double[kol-1];
                        for(int i=brs-1;i>=0;i--)
                        {
                            if(i == brs-1)
                            {
                                variabelk[i] = -1*m[i][k];
                            }
                            else
                            {
                                variabelk[i] = -1*m[i][k];
                                for(int j=i+1;j<brs-1;j++)
                                {
                                    variabelk[i] += (m[i][j] * variabelk[j]);
                                }
                            }
                            if(variabelk[i]>0)
                            {
                                x[i] += (" + " + Double.toString(variabelk[i]) + "  x" + (k+1));                               
                            }
                            if(variabelk[i]<0)
                            {
                                variabelk[i] *= -1;
                                x[i] += (" - " + Double.toString(variabelk[i]) + "  x" + (k+1));
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0; i < x.length; ++i)
        {
            if (variabel[i])
            {
                 System.out.println("x" + (i+1) + " = " + "x" + (i+1));
            }
            else
            {
                 System.out.println("x" + (i + 1) + " = " + x[i]);
            }
        }
        solution = x;
        return solution;
    }

    public double[][] splGaussJordan() {
        for (int i=0;i<brs;i++)
        {
            int notzeroi = i;
            int notzeroj = i;
            boolean notZero = false;
            for (int i1=i; i1<brs;i1++)
            {
                for (int j1=i;j1<kol;j1++)
                {
                    if (m[i1][j1] != 0)
                    {
                        notZero = true;
                        notzeroi = i1;
                        notzeroj = j1;
                        break;
                    }
                }
                if (notZero) {
                    break;
                }
            }
            if (!notZero){
                break;
            }
            double temp;
            // menukar baris jika baris yg seharusnya ada kolom ada 1 berisi 0
            if (notzeroi != i) 
            {
                for (int j = 0; j<kol;j++)
                {
                    temp = m[i][j];
                    m[i][j] = m[notzeroi][j];
                    m[i][j] = temp;
                }
                notzeroi = i;
            }
            
            // membagi baris agar yang paling depan 1
            double head = m[notzeroi][notzeroj];
            if(head!=0)
            {
                for (int j=0;j<kol;j++)
                {
                    m[i][j] = m[i][j]/head;
                }
            }

            // mengurangi baris dibawahnya hingga 0 menjadi eselon baris
            if (i<kol-1)
            {
                for (int i1=i+1;i1<brs;i1++)
                {
                    double konstanta = m[i1][notzeroj];
                    for (int j1=notzeroj;j1<kol;j1++)
                    {
                        m[i1][j1] -= m[notzeroi][j1]*konstanta;
                    }
                }
            }   
        }
        for(int i=kol-2;i>0;i--)
        {
            for(int i1=i-1;i1>=0;i1--)
            {
                double konstanta = m[i1][i];
                for(int j=i;j<kol;j++)
                {
                    m[i1][j] -= konstanta*m[i][j];
                }
            }
        }
        return m;
    }

    public class Determinant {
        static void Kofaktor(double matriks[][], double temp[][], int p, int q,int n){
            int i=0, j=0;
            for (int baris = 0; baris < n; baris++){
                for(int kolom=0; kolom < n; kolom++){
                    if(baris != p && kolom != q){
                        temp[i][j++] = matriks[baris][kolom];
                        if(j == n-1){
                            j=0;
                            i++;
                        }
                    }
                }
            }
        }

        public static double KofaktorDet(double matriks[][], int n){
            if (n == 2){
                return ((matriks[0][0]*matriks[1][1]) - (matriks[0][1]*matriks[1][0]));
            }
            else {
                double determinan = 0;
                double temp[][] = new double[n-1][n-1];
                int tanda = 1;
                for(int baris = 0; baris < n; baris++){
                    Kofaktor(matriks, temp,0,baris,n);
                    determinan += tanda * matriks[0][baris] * KofaktorDet(temp,n-1);
                    tanda = tanda*(-1);
                }
                return determinan;
            }
        }
    }
    
}
