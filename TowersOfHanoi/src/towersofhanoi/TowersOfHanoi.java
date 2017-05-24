/******************************************************************************
 *  Compilation:  javac Hanoi.java
 *  Execution:    java Hanoi n
 *  Dependencies: StdDraw.java
 *  
 *  Solves the Towers of Hanoi problem on n discs and displays the
 *  results graphically.
 *
 *
 *  % java Hanoi 6
 *
 ******************************************************************************/
package towersofhanoi;

import java.awt.Color;

/**
 *
 * @author ram bablu
 */
public class TowersOfHanoi {

    /**
     */
    // draw the current state of the Towers of Hanoi game
    public static int SPEED = StdDraw.DEFAULT_DELAY;
    static StringBuilder STEPS;
    public static void draw(int[] pole) {

        int n = pole.length - 1;

        // draw 3 poles
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BROWN);
        StdDraw.setPenRadius(0.02);

        for (int i = 0; i < 3; i++) {
            StdDraw.line(i, 2, i, n+2.5);
            StdDraw.text(i,1,"Peg "+(char)(65+i));
        }
            
        // draw N discs
        int[] discs = new int[3];   // discs[p] = # discs on pole p
		
        StdDraw.line(0-0.5, discs[pole[0]]+2,2+0.5, discs[pole[0]]+2 );
		
        for (int i = n; i >= 1; i--) {
            Color color = Color.getHSBColor(1.0f * i / n, 0.9f, 0.9f);
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius(0.005);   // magic constant 0.35
            double size = 0.5 * i / n;
            int p = pole[i];
            //StdDraw.line(p-size/2, discs[p]+3, p + size/2, discs[p]+3);
            StdDraw.filledRectangle(p,discs[p]+3, size,0.359);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(p, discs[p]+3, "D-"+i);
            ++discs[p];
        }

        StdDraw.show();
        //System.out.println("SPEED :"+SPEED);
        StdDraw.pause(SPEED);
    }

    /**
     *
     * @param n
     * @param from
     * @param temp
     * @param to
     * @param pole
     * @param low
     * @param step
     * @param high
     */
    public static void hanoi(int n, int from, int temp, int to, int[] pole,long low,long step,long high) {
        if (n == 0) return;
        hanoi(n-1, from, to, temp, pole,low,(low+step)/2,step);
        STEPS = STEPS.append("Step ").append(step).append("\t\t:Move disc ").append(n).append(" from PEG ").append((char)(65+from)).append(" to pole ").append((char)(65+to)).append("$");
        pole[n] = to;
        draw(pole);
        hanoi(n-1, temp, from, to, pole, step, (high+step)/2, high );
    }

    public static void hanoi(int n) {
        int[] pole = new int[n+1];       // pole[i] = pole (0-2) that disc i is on
        draw(pole);
        STEPS = new StringBuilder();
        hanoi(n, 0, 1, 2, pole, 0,(long) (Math.pow(2,n) / 2),(long)Math.pow(2, n));
    }
    
    public static void main(String[] args) {
        int n = StdDraw.getDiscNumber();
        StdDraw.getSpeed();
        
        int WIDTH  = 400;                    // width of largest disc
        int HEIGHT = 20;                     // height of each disc

        // set size of window and sale
        
        StdDraw.setCanvasSize((3)*WIDTH, (n+5)*HEIGHT);
        StdDraw.setXscale(-1, 3);
        StdDraw.setYscale(0, n+3);
        StdDraw.enableDoubleBuffering();
	StdDraw.setIcon("/Tower.png");

        // solve the Towers of Hanoi with n discs
        hanoi(n);
        //completeDialog(steps);
        int steps = (int)Math.pow(2,n) - 1;
        StdDraw.completeDialog(steps);
        
    }
    
}
