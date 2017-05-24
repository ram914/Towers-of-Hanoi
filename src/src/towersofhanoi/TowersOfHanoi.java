/******************************************************************************
 *  Compilation:  javac TowersOfHanoi.java
 *  Execution:    java TowersOfHanoi
 *  Dependencies: BasicGUI.java
 *  
 *  Solves the Towers of Hanoi problem on n discs and displays the
 *  results graphically.
 *
 *
 *  % java Hanoi
 *
 ******************************************************************************/
package towersofhanoi;

import java.awt.Color;

/**
 *
 * @author ram bablu
 */
public class TowersOfHanoi {

    // draw the current state of the Towers of Hanoi game
    public static int SPEED = BasicGUI.DEFAULT_DELAY;
    static StringBuilder STEPS;
    public static void draw(int[] pole) {

        int n = pole.length - 1;

        // draw 3 poles
        BasicGUI.clear();
        BasicGUI.setPenColor(BasicGUI.BROWN);
        BasicGUI.setPenRadius(0.02);

        for (int i = 0; i < 3; i++) {
            BasicGUI.line(i, 2, i, n+2.5);
            BasicGUI.text(i,1,"Peg "+(char)(65+i));
        }
            
        // draw N discs
        int[] discs = new int[3];   // discs[p] = # discs on pole p
		
        BasicGUI.line(0-0.5, discs[pole[0]]+2,2+0.5, discs[pole[0]]+2 );
		
        for (int i = n; i >= 1; i--) {
            Color color = Color.getHSBColor(1.0f * i / n, 0.9f, 0.9f);
            BasicGUI.setPenColor(color);
            BasicGUI.setPenRadius(0.005);   // magic constant 0.35
            double size = 0.5 * i / n;
            int p = pole[i];
            //BasicGUI.line(p-size/2, discs[p]+3, p + size/2, discs[p]+3);
            BasicGUI.filledRectangle(p,discs[p]+3, size,0.359);
            BasicGUI.setPenColor(BasicGUI.BLACK);
            BasicGUI.text(p, discs[p]+3, "D-"+i);
            ++discs[p];
        }

        BasicGUI.show();
        //System.out.println("SPEED :"+SPEED);
        BasicGUI.pause(SPEED);
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
        STEPS = STEPS.append("Step ").append(step).append("\t\t:Move disc ").append(n).append(" from PEG ").append((char)(65+from)).append(" to PEG ").append((char)(65+to)).append("$");
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
        int n = BasicGUI.getDiscNumber();
        BasicGUI.getSpeed();
        
        int WIDTH  = 400;                    // width of largest disc
        int HEIGHT = 20;                     // height of each disc

        // set size of window and sale
        
        BasicGUI.setCanvasSize((3)*WIDTH, (n+5)*HEIGHT);
        BasicGUI.setXscale(-1, 3);
        BasicGUI.setYscale(0, n+3);
        BasicGUI.enableDoubleBuffering();
	BasicGUI.setIcon("/Tower.png");

        // solve the Towers of Hanoi with n discs
        hanoi(n);
        //completeDialog(steps);
        int steps = (int)Math.pow(2,n) - 1;
        BasicGUI.completeDialog(steps);
        
    }
    
}
