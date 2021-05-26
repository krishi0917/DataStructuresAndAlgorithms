package LeetcodePrograms;
// Indeed Question
public class MatrixImageQuestion {
    /*
Imagine we have an image. We'll represent this image as a simple 2D array where every pixel is a 1 or a 0. The image you get is known to have a single
rectangle of 0s on a background of 1s.

Write a function that takes in the image and returns one of the following representations of the rectangle of 0's: top-left coordinate and
bottom-right coordinate OR top-left coordinate, width, and height.

image1 = [
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 0, 0, 0, 1],
  [1, 1, 1, 0, 0, 0, 1],
  [1, 1, 1, 1, 1, 1, 1],
]

Sample output variations (only one is necessary):

findRectangle(image1) =>
  x: 3, y: 2, width: 3, height: 2
  2,3 3,5 -- row,column of the top-left and bottom-right corners

Other test cases:

image2 = [
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 0],
]

findRectangle(image2) =>
  x: 6, y: 4, width: 1, height: 1
  4,6 4,6

image3 = [
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 0, 0],
  [1, 1, 1, 1, 1, 0, 0],
]

findRectangle(image3) =>
  x: 5, y: 3, width: 2, height: 2
  3,5 4,6

image4 = [
  [0, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
]

findRectangle(image4) =>
  x: 0, y: 0, width: 1, height: 1
  0,0 0,0

image5 = [
  [0],
]

findRectangle(image5) =>
  x: 0, y: 0, width: 1, height: 1
  0,0 0,0

n: number of rows in the input image
m: number of columns in the input image


*/

    static int[] result = new int[4];
    static class Dimension{
        int x1 , y1 ,x2,y2;
//        Dimension(){
//            x1=0;y1=0;x2=0;y2=0;
//        }
        Dimension(int x1 , int y1 , int x2 , int y2){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }

        int getX1() {
            return x1;
        }

        void setX1(final int x1) {
            this.x1 = x1;
        }

        int getY1() {
            return y1;
        }

        void setY1(final int y1) {
            this.y1 = y1;
        }

        int getX2() {
            return x2;
        }

        void setX2(final int x2) {
            this.x2 = x2;
        }

        int getY2() {
            return y2;
        }

        void setY2(final int y2) {
            this.y2 = y2;
        }
    }
    public static void crawlSurrounding(int[][] rect, int i, int j , Dimension d) {
        int defaultJ = j;
        while (j < rect[0].length) {
            if (rect[i][j] == 0) {
                result[3] = j;
                d.setX2(j);
                j++;

            } else
                break;
        }
        j = defaultJ;
        while (i < rect.length) {
            if (rect[i][j] == 0) {
                result[2] = i;
                d.setY2(i);
                i++;

            } else
                break;
        }
    }

    public static void findRectangle(int[][] rect) {
        Dimension d = new Dimension(0,0,0,0);
        for (int i = 0; i < rect.length; i++) {
            for (int j = 0; j < rect[0].length; j++) {
                if (rect[i][j] == 0) {
                    result[0] = i;
                    result [1] = j;
                    d.setX1(i);
                    d.setY1(j);
                    crawlSurrounding(rect, i, j , d);
                    return;
                }
            }
        }
    }

    public static void main(String[] argv) {

        int[][] image1 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };

        int[][] image2 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0}
        };

        int[][] image3 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 0, 0}
        };

        int[][] image4 = {
                {0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };

        int[][] image5 = {
                {0}
        };


        findRectangle(image1);
//        System.out.println("x1=" + result[0]+ " x2=" + result[1] + " y1=" +  result[2] +  " y2= " + result[3]);
//        findRectangle(image2);
//        System.out.println("x1=" + result[0]+ " x2=" + result[1] + " y1=" +  result[2] +  " y2= " + result[3]);

//        findRectangle(image3);
//        System.out.println("x1=" + result[0]+ " x2=" + result[1] + " y1=" +  result[2] +  " y2= " + result[3]);
//        findRectangle(image4);
//        System.out.println("x1=" + result[0]+ " x2=" + result[1] + " y1=" +  result[2] +  " y2= " + result[3]);
//        findRectangle(image5);
//        System.out.println("x1=" + result[0]+ " x2=" + result[1] + " y1=" +  result[2] +  " y2= " + result[3]);

    }




}