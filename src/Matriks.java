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
    
    public double[][] splGauss() {
        // mencari elemen yang bukan 0 untuk penentuan pertukaran baris
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
        return m;
    }
    
    public String[] solveGauss()
    {
        String[] solution = new String[kol-1];
        String x[] = new String[kol-1];
        int banyakzero = 0;
        // menentukan jika tidak ada solusi (ada baris yang semuanya bernilai 0)
        boolean solvable = true;
        for (int i=0;i<brs;i++){
            banyakzero = 0;
            for (int j=0;j<kol-1;j++){
                if (m[i][j]==0){
                    banyakzero += 1;
                }
            }
            if(banyakzero==kol-1){
                solvable = false;
                break;
            }
        }
        if(!solvable)
        {
            System.out.println("Sistem persamaan linear tidak ada solusi.");
            solution = null;
        } else{
            // solusi hanya dalam bentuk angka
            if (brs == kol-1){
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
                boolean[] variabel = new boolean[kol-1];
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
                    x[i] = "0.0";
                }

                for (int i=0; i<(kol-1); i++)
                {
                    if(variabel[i])
                    {
                        x[i] = ("x" + (i+1));
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
                                variabelk[i] = m[i][k];
                            }
                            else
                            {
                                variabelk[i] = m[i][k];
                                for(int j=i+1;j<brs-1;j++)
                                {
                                    variabelk[i] += (m[i][j] * variabelk[j]);
                                }
                            }
                            x[i] += (" + " + Double.toString(variabelk[i]) + " " + x[k]);
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
        for(int i=brs-1;i>0;i--)
        {
            for(int i1=i-1;i1>=0;i1--)
            {
                double konstanta = m[i1][i];
                m[i1][i] -= konstanta*m[i][i];
                m[i1][kol-1] -= konstanta*m[i][kol-1];
            }
        }
        return m;
    }
    
}
