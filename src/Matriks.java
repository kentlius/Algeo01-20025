import java.io.File;
import java.io.IOException;
//import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Matriks {
    public int brs;
    public int kol;
    public double m[][];

    public Matriks(int baris, int kolom){
        brs=baris;
        kol=kolom;
        m=new double[baris][kolom];
    }

    public double getElm(int baris1, int kolom1){
        return m[baris1][kolom1];
    }

    public void bacaMatriks() {
        Scanner input = new Scanner(System.in);
        int i,j;
        for(i=0;i<brs;i++)
            for(j=0;j<kol;j++)
                m[i][j]=input.nextDouble();
    }

    public void bacaMatriksReg() {
        Scanner input = new Scanner(System.in);
        for(int i=0;i<kol-1;i++){
            System.out.print("Nilai x"+(i+1)+": ");
            m[0][i]=input.nextDouble();
        }
        System.out.print("Nilai y: ");
        m[0][kol-1] = input.nextDouble();
        input.close();
    }
    
    public void bacaMatriksfile(String text, int baris, int kolom) throws Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("../test/"+text+".txt")));
        while(sc.hasNextLine()) {
           for (int i=0; i<baris; i++) {
              String[] line = sc.nextLine().trim().split(" ");
              for (int j=0; j<kolom; j++) {
                 m[i][j] = Double.parseDouble(line[j]);
              }
           }
        }
     }

    public void saveSplSolution(String[] solution, String filename){
        try{
        File myObj = new File("../test/"+filename+".txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            FileWriter writer = new FileWriter("../test/"+filename+".txt");

            for(int i=0;i<kol-1;i++) {
                writer.write(solution[i] + System.lineSeparator());
            }
            writer.close();
        } else {
            System.out.println("File already exists.");
        }
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
    
    public void saveMatrix(String filename){
        try{
            File myObj = new File("../test/"+filename+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                FileWriter writer = new FileWriter("../test/"+filename+".txt");
    
                for(int i=0;i<brs;i++) {
                    for(int j=0;j<kol;j++)
                    {
                        writer.write(m[i][j] + " ");
                    }
                    writer.write(System.lineSeparator());
                }
                writer.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
        }

    public void saveStringSolution(String solution, String filename){
        try{
        File myObj = new File("../test/"+filename+".txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            FileWriter writer = new FileWriter("../test/"+filename+".txt");
            writer.write(solution);
            writer.close();
        } else {
            System.out.println("File already exists.");
        }
    } catch (IOException e) {
        System.out.println("An error occurred.");
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
    public void salinMatriks(double matriks[][], double temp[][], int n) {
        for (int baris = 0; baris < n; baris++) {
            for (int kolom = 0; kolom < n; kolom++) {
                temp[baris][kolom] = matriks[baris][kolom];
            }
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
    
    public double[][] removeLastRow(double m[][]){    
        double m1[][] = new double[brs-1][kol];
        for (int i=0;i<brs-1;i++)
        {
            for (int j=0; j<kol;j++)
            {
                m1[i][j] = m[i][j];
            }
        }
        brs -= 1;
        return m1;
    }
    
    public double[][] tambahKolom(double m[][]){
        double m1[][] = new double[brs][kol+1];
        return m1;
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
                }
                else
                {
                    for(int i1=i;i1<brs;i1++)
                    {
                        if(m[i1][j]==0)
                        {
                            notZero = true;
                            break;
                        }
                    }
                }
                if(notZero)
                {
                    break;
                }
            }

            // membagi baris agar yang paling depan 1
            for(notzeroj=notzeroi;notzeroj<kol-1;notzeroj++)
            {
                double head = m[notzeroi][notzeroj];
                if(head!=0)
                {
                    for (int j=0;j<kol;j++)
                    {
                        m[i][j] = m[i][j]/head;
                    }
                    break;
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
        // Menghapus baris terakhir jika bukan baris efisien (semuanya bernilai 0)
        for(int i=brs-1;i>=0;i--)
        {
            int banyakzero = 0;
            for(int j=0;j<kol;j++)
            {
                if(m[i][j]==0)
                {
                    banyakzero += 1;
                }
            }
            if(banyakzero == kol)
            {
                m = removeLastRow(m);
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
        char[] var = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
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
                int lastnonv = kol-2;
                for (int i=(brs-1);i>=0;i--)
                {
                    for (int i1=lastnonv-1;i1>=i;i1--)
                    {
                        if(!variabel[i1])
                        {
                            double temp = m[i][kol-1];
                            if(i1<kol-2)
                            {
                                for(int j=(i1+1);j<kol-1;j++)
                                {
                                    if(!variabel[j])
                                    {
                                        temp -= m[i][j]*Double.valueOf(x[j]);
                                    }
                                }
                                x[i1] = Double.toString(temp);
                                lastnonv = i1;
                            }
                            else
                            {
                                x[i1] = Double.toString(temp);
                                lastnonv = i1;
                            }
                            break;
                        }
                    }
                }

                // Menjumlahkan variabel ke dalam jawaban
                for(int k=kol-2;k>=0;k--)
                {
                    if(variabel[k])
                    {
                        double variabelk[] = new double[kol-1];
                        lastnonv = k;
                        for(int i=brs-1;i>=0;i--)
                        {   
                            for(int i1=lastnonv-1;i1>=0;i1--)
                            {
                                
                                if(i == brs-1)
                                {
                                    variabelk[i1] = -1*m[i][k];
                                    if(!variabel[i1])
                                    {
                                        lastnonv = i1;
                                    }
                                } 
                                else
                                {
                                    variabelk[i1] = -1*m[i][k];
                                    for(int j=i1+1;j<k;j++)
                                    {
                                        variabelk[i1] -= (m[i][j] * variabelk[j]);
                                    }
                                    if(!variabel[i1])
                                    {
                                        lastnonv = i1;
                                    }
                                }
                                if(!variabel[i1])
                                {
                                    if(variabelk[i1]>0)
                                    {
                                        x[i1] += (" + " + Double.toString(variabelk[i1]) + "  " + var[k]);  
                                        break;                             
                                    }
                                    if(variabelk[i1]<0)
                                    {
                                        variabelk[i1] *= -1;
                                        x[i1] += (" - " + Double.toString(variabelk[i1]) + "  " + var[k]);
                                        break;
                                    }
                                }     
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
                 System.out.println("x" + (i+1) + " = " + var[i]);
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
                }
                else
                {
                    for(int i1=i;i1<brs;i1++)
                    {
                        if(m[i1][j]==0)
                        {
                            notZero = true;
                            break;
                        }
                    }
                }
                if(notZero)
                {
                    break;
                }
            }

            // membagi baris agar yang paling depan 1
            for(notzeroj=notzeroi;notzeroj<kol-1;notzeroj++)
            {
                double head = m[notzeroi][notzeroj];
                if(head!=0)
                {
                    for (int j=0;j<kol;j++)
                    {
                        m[i][j] = m[i][j]/head;
                    }
                    break;
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
            for(int i1=0;i1<=kol-1;i1++)
            {
                if(m[i][i1]!=0)
                {
                    for(int iZ=i-1;iZ>=0;iZ--)
                    {
                        if(m[iZ][i1]!=0)
                        {
                            for(int i2=i-1;i2>=0;i2--)
                            {
                                double konstanta = m[i2][i1];
                                for(int j=i1;j<kol;j++)
                                {
                                    m[i2][j] -= m[i][j]*konstanta;
                                }
                            }
                            break;   
                        }
                    }
                    break;
                }
            }
        }
        
        // Menghapus baris terakhir jika tidak efisien (semuanya bernilai 0)
        for(int i=brs-1;i>=0;i--)
        {
            int banyakzero = 0;
            for(int j=0;j<kol;j++)
            {
                if(m[i][j]==0)
                {
                    banyakzero += 1;
                }
            }
            if(banyakzero == kol)
            {
                m = removeLastRow(m);
            }
        }
        return m;
    }

    public double[][] polinomMatriks() {
    // Mengubah input polinom ke bentuk matriks untuk diselesaikan
        double m1[][] = new double[brs][kol];
        int n = brs;
        for (int i=kol;i<brs+1;i++)
        {
            m1 = tambahKolom(m1);
            kol += 1;
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                m1[i][j] = Math.pow(m[i][0],j);
            }
            m1[i][n] = m[i][1];
        }
        for (int i=0;i<kol-3;i++)
        {
            m = tambahKolom(m);
        }
        for(int i=0;i<brs;i++)
        {
            for(int j=0;j<kol;j++)
            {
                m[i][j] = m1[i][j];
            }
        }
        return m;
    }
    
    public double solvePolinom(double n){
        String x[] = new String[kol-1];
        int banyakzero = 0;
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
            x = null;
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

            double pengali = n;
            n = 0;
            for(int i = 0; i < x.length; ++i)
            {
                n += Double.valueOf(x[i])*(Math.pow(pengali,i));
            }
        }
        return n;
    }

    public String printPolinomsolution(){
        String x[] = new String[kol-1];
        int banyakzero = 0;
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
            x = null;
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

        }
        String FinalSolution = ("f(x) = "+x[0] +" x^0");
        for(int i = 1; i<x.length; i++)
        {
            FinalSolution += (" + "+x[i]+" x^"+i);
        }
        return FinalSolution;
    }

    static void Kofaktor(double matriks[][], double temp[][], int p, int q, int n) {
        int i = 0, j = 0;
        for (int baris = 0; baris < n; baris++) {
            for (int kolom = 0; kolom < n; kolom++) {
                if (baris != p && kolom != q) {
                    temp[i][j++] = matriks[baris][kolom];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    public double KofaktorDet(double m[][], int n) {
        double determinan = 0;
        if (n == 1) {
            return m[0][0];
        } else if (n == 2) {
            return ((m[0][0] * m[1][1]) - (m[0][1] * m[1][0]));
        } else {
            double temp[][] = new double[n-1][n-1];
            int tanda = 1;
            for (int f = 0; f < n; f++) {
                Kofaktor(m, temp, 0, f, n);
                determinan += tanda * m[0][f] * KofaktorDet(temp,n - 1);
                tanda = -tanda;
            }
            return determinan;
        }
    }

    public double reduksiBaris(double matriks[][], int n) {
        int i = 0, l = 0, index;
        double kurang;
        int jumlah = 0;
        for (int j = 1; j < n; j++) {
            for (int h = j; h < n; h++) {
                index = i + 1;
                while ((matriks[i][l] == 0) && (index < n)) {
                    swapBaris(i, index, matriks);
                    jumlah++;
                    index++;
                }
                if (index == n) {
                    continue;
                }
                kurang = matriks[h][l] / matriks[i][l];
                for (int k = l; k < n; k++) {
                    matriks[h][k] -= matriks[i][k] * kurang;
                }
            }
            i++;
            l++;
        }
        return jumlah;
    }

    public double ReduksiDet(double matriks[][], int n) {
        double temp[][] = new double[n][n];
        salinMatriks(matriks, temp, n);
        double jumlah = reduksiBaris(matriks, n);
        double detereminan = matriks[0][0];
        for (int i = 1; i < n; i++) {
            detereminan *= matriks[i][i];
        }
        salinMatriks(temp, matriks, n);
        if (jumlah % 2 == 0) {
            return detereminan;
        } else {
            return -detereminan;
        }
    }

    public static void gaussPersegi(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
                a[index[i]][j] = pj;
 
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

    public double[][] inverseMatriksGauss() {
        //identitas matriks
        int n = brs;
        double x[][] = new double[n][n];
        double temp[][]=new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            temp[i][i] = 1;
        
        gaussPersegi(m, index);
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    temp[index[j]][k]
                    	    -= m[index[j]][i]*temp[index[i]][k];
 
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = temp[index[n-1]][i]/m[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = temp[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= m[index[j]][k]*x[k][i];
                }
                x[j][i] /= m[index[j]][j];
            }
        }
        m=x;
        return m;
    }

    public String splInverse(){
        String solution=("");
        double a[][]= new double[brs][brs];
        double b[] = new double[brs];
        int i,j,k;

        for(i=0;i<brs;i++)
            for(j=0;j<brs;j++)
                a[i][j]=m[i][j];
        
        for(i=0;i<brs;i++)
            b[i] = m[i][kol-1];

        int n = brs;
        double x[][] = new double[n][n];
        double temp[][]=new double[n][n];
        int index[] = new int[n];
        for (i=0; i<n; ++i) 
            temp[i][i] = 1;
        
        gaussPersegi(a, index);
        for (i=0; i<n-1; ++i)
            for (j=i+1; j<n; ++j)
                for (k=0; k<n; ++k)
                    temp[index[j]][k]
                            -= a[index[j]][i]*temp[index[i]][k];
    
        for (i=0; i<n; ++i) 
        {
            x[n-1][i] = temp[index[n-1]][i]/a[index[n-1]][n-1];
            for (j=n-2; j>=0; --j) 
            {
                x[j][i] = temp[index[j]][i];
                for (k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }

        double c[]= new double[n];
        for (i=0;i<n;i++)
        {
            for(j=0;j<n;j++)
            {
                c[i] += x[i][j] * b[j];
            }
        }
        
        
        for(i = 0; i<n; i++)
        {
            solution+=("x" + i + "=" + c[i] + " ");
        }

        return solution;
    }

    public String[] splKramer(){
        int n = brs;
        String x[] = new String[n];
        double temp[][] = new double[n][n];
        for (int kramer=0;kramer<n;kramer++)
        {
            for(int i =0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    temp[i][j] = m[i][j];
                }
            }
            double det = ReduksiDet(temp, n);
            for(int i1=0;i1<brs;i1++)
            {
                temp[i1][kramer] = m[i1][kol-1];
            }
            x[kramer] = ("x"+(kramer+1)+" = "+Double.toString((ReduksiDet(temp, n))/det));
            System.out.println(x[kramer]);
        }
        return x;
    }

    public double[][] Regresi(int perubah, int persamaan, Matriks m1)
    {
        double temp;
        m[0][0] = persamaan;

        for(int i=0;i<brs-1;i++){
            for(int j=0;j<kol-2;j++)
            {
                temp = 0;
                for(int k=0;k<persamaan;k++)
                {
                    temp += m1.getElm(k, i)*m1.getElm(k,j);
                }
                m[i+1][j+1] = temp;
            }
        }

        for(int i=0;i<perubah;i++)
        {
            for(int j=0;j<persamaan;j++)
            {
                m[i+1][0] += m1.getElm(j, i);
                m[0][i+1] += m1.getElm(j, i);
            }
        }

        for(int i=1;i<brs;i++){
            temp =0 ;
            for(int j=0;j<persamaan;j++)
            {
                temp += m1.getElm(j, perubah)*m1.getElm(j,i-1);
            }
            m[i][kol-1] = temp;
        }
        return m;
    }

    public String printRegresisolution(){
        String x[] = new String[kol-1];
        int banyakzero = 0;
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
            x = null;
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

        }
        String FinalSolution = ("y = "+x[0]);
        for(int i = 1; i<x.length; i++)
        {
            FinalSolution += (" + "+x[i]+" x"+i);
        }
        FinalSolution += " + ϵ";
        return FinalSolution;
    }

    public double solveRegresi(double[] n){
        String x[] = new String[kol-1];
        int banyakzero = 0;
        double FinalSolution;
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
            x = null;
            FinalSolution = 0;
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

            double hasilakhir = Double.valueOf(x[0]);
            for(int i = 1; i < x.length; ++i)
            {
                hasilakhir += Double.valueOf(x[i])*(n[i-1]);
            }
            FinalSolution = hasilakhir;
        }
        return FinalSolution;
    }
}    