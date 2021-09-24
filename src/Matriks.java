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
}
