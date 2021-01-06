package ro.poli.sma.proiectsma.components;

import java.util.Random;

public class GameStarter {
    public static int[][] init( int bombnum , final int width , final int height, int x0, int y0){

        Random r = new Random();
        int i,j,imin,jmin,imax,jmax;
        int x0i=x0-1;
        int x0s=x0+1;
        int y0i=y0-1;
        int y0s=y0+1;

        int [][] grid = new int[width][height];
        for(i=0;i<width;i++)
            for(j=0;j<height; j++)
                grid[i][j]=0;

        while( bombnum > 0 ){
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            if( grid[x][y] != -1 && (x<x0i || x>x0s || y<y0i || y>y0s )){
                grid[x][y] = -1;
                bombnum--;
                imin= x>0? x-1: 0;
                jmin= y>0? y-1: 0;
                imax= x+1<width? x+2: width;
                jmax= y+1<height? y+2: height;
                for(i=imin;i<imax;i++)
                    for(j=jmin;j<jmax; j++)
                        if(grid[i][j]>-1) grid[i][j]++;
            }


        }

        return grid;
    }



}
