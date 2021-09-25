import java.util.Scanner;

public class Matriks {
    public int n;
    public double m[][]=new double[100][100];

    public void buatMatriks(int n){
        this.n=n;
        for (int i = 0; i < n;i++) 
            for (int j = 0; j < n; j++) 
                this.m[i][j] = 0.0;
    }

    public void bacaMatriks() {
        Scanner input = new Scanner(System.in);
        int i,j;
        for(i=0;i<this.n;i++)
            for(j=0;j<this.n;j++)
                this.m[i][j]=input.nextDouble();
        
        input.close();
    }

    public void tulisMatriks(){
        int i,j;

        for(i=0;i<this.n;++i)
        {
            for(j=0;j<this.n;++j)
            {
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public static double[][] splGauss(double[][] m) {
        int baris = m.length;
        int kolom = m[0].length;
        
        // mencari elemen yang bukan 0 untuk penentuan pertukaran baris
        for (int i=0;i<baris;i++)
        {
            int notzeroi = i;
            int notzeroj = i;
            boolean notZero = false;
            for (int i1=i; i1<baris;i1++)
            {
                for (int j1=i;j1<kolom;j1++)
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
                for (int j = 0; j<kolom;j++)
                {
                    temp = m[i][j];
                    m[i][j] = m[notzeroi][j];
                    m[i][j] = temp;
                }
                notzeroi = i;
            }
            
            // membagi baris agar yang paling depan 1
            double head = m[notzeroi][notzeroj];
            for (int j=0;j<kolom;j++)
            {
                m[i][j] = m[i][j]/head;
            }

            // mengurangi baris dibawahnya hingga 0 menjadi eselon baris
            if (i<kolom-1)
            {
                for (int i1=i+1;i1<baris;i1++)
                {
                    double konstanta = m[i1][notzeroj];
                    for (int j1=notzeroj;j1<kolom;j1++)
                    {
                        m[i1][j1] -= m[notzeroi][j1]*konstanta;
                    }
                }
            }   
        }
        return m;
    }
    
    
    
    public static String[] solveGauss(double[][] m)
    {
        m = splGauss(m);
        int baris = m.length;
        int kolom = m[0].length;
        boolean solvable = true;
        String[] solution = new String[kolom-1];
        String x[] = new String[kolom-1];
        // menentukan jika tidak ada solusi (ada baris yang semuanya bernilai 0)
        for (int i=0;i<baris;i++){
            int banyakzero = 0;
            for (int j=0;j<kolom;j++){
                if (m[i][j]==0){
                    banyakzero +=1;
                }
            }
            if(banyakzero==kolom-1){
                solvable = false;
                break;
            }
        }
        if(solvable = false)
        {
            System.out.println("Sistem persamaan linear tidak ada solusi.");
            solution = null;
        } else{
            // solusi hanya dalam bentuk angka
            if (baris == kolom-1){
                for(int i=kolom-2;i>=0;i--){
                    if(i==kolom-2){
                        x[i] = Double.toString(m[i][kolom-1]);
                    } else {
                        double temp = m[i][kolom-1];
                        for(int j=i+1;j<kolom-1;j++){
                            temp -= m[i][j]*Double.valueOf(x[j]);
                        }
                        x[i] = Double.toString(temp);
                    }
                }
            }
            // solusi dalam bentuk variabel
            else {
                // menentukan elemen yang tidak berbentuk variabel
                boolean[] variabel = new boolean[kolom-1];
                for (int i=0; i<(kolom-1);i++)
                {
                    variabel[i] = true;
                }

                for (int i=0; i<(baris);i++)
                {
                    for (int j=0; j<(kolom-1);j++)
                    {
                        if (m[i][j]==1)
                        {
                            variabel[j] = false;
                            break;
                        }
                    }
                }
                for (int i=0;i<(kolom-1);i++)
                {
                    x[i] = "0.0";
                }

                for (int i=0; i<baris; i++)
                {
                    if(variabel[i])
                    {
                        x[i] = ("x" + (i+1));
                    }
                }
                // Menjumlahkan hanya yang dalam bentuk angka
                for (int i=baris-1;i>=0;i--)
                {
                    double temp = m[i][kolom-1];
                    if(i<baris-1)
                    {
                        for(int j=i+1;j<baris;j++)
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
                for(int k=0;k<kolom-1;k++)
                {
                    if(variabel[k])
                    {
                        double variabelk[] = new double[kolom-1];
                        for(int i=baris-1;i>=0;i--)
                        {
                            if(i == baris-1)
                            {
                                variabelk[i] = m[i][k];
                            }
                            else
                            {
                                variabelk[i] = m[i][k];
                                for(int j=i+1;j<baris-1;j++)
                                {
                                    variabelk[i] += (m[i][j] * variabelk[j]);
                                }
                            }
                            x[i] += (" + " + Double.toString(variabelk[i]) + x[k]);
                        }
                    }
                }
            }
        }
        for(int i = 0; i < x.length; ++i)
        {
            System.out.println("x" + (i + 1) + " = " + x[i]);
        }
        solution = x;
        return solution;
    }
    
    
}
