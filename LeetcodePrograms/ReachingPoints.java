package LeetcodePrograms;

/**
 * Created by rkhurana on 3/28/19.
 */
//    780. Reaching Points   #Uber #Salesforce
//    A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
//    Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists
//    to transform the point (sx, sy) to (tx, ty). Otherwise, return False.

public class ReachingPoints {
// We can solve this problem recursively from tx and ty.
// In each recursive call, if tx > ty, it should derive from (tx % ty, ty), otherwise, from (tx, ty % tx) because the
// bigger one must come from last transformation from (tx - ty, ty) or (tx, ty - tx) until it is less than the smaller number.
// We only need to care about the situation where (sx, sy) transforms to (sx + n * sy, sy) or (sx, sy + m * sx) in the first
// time because sx > sy is not the result of (sx % sy + m * sy). Hence, if sx > sy, (sx + n * sy) % sy will give a smaller number which is wrong.


        public boolean reachingPoints(int sx, int sy, int tx, int ty) {
            if (sx > tx || sy > ty)
                return false;
            if (sx == tx && (ty - sy) % sx == 0)
                return true;
            if (sy == ty && (tx - sx) % sy == 0)
                return true;
            return reachingPoints(sx, sy, tx % ty, ty % tx);
        }

// I think most people find it difficult to grasp the condition where (sx, sy) transforms into (sx + n * sy) or (sx, sy+m *sx).
// Taking an example makes it much clear. Consider a case where sx, sy is (3,2) with sx > sy
// First few transformations were all (sx+sy, sy) (sx1 + sy, sy) (sx2 + sy, sy) ... this is essentially (sx + n * sy, sy).
// In our example
//
//            (sx,sy)                            (tx,ty)
//            (3,2) --> (5,2)--> (7,2)-->(9,2)-->(11,2) which is (3 + 4 * 2, 2) with n = 4
// If we were to do tx %=ty (11%2) we would get sx = 1 which is not right answer. Hence when sy == ty we need to check if we have
// a condition (tx = sx + n * sy) or (11 = 3 + 4 * 2) this also means (tx-sx) % sy should be 0 or (11-3) % 2 should be 0.
// The same reasoning can be applied when sx == tx

    public static void main(String []args){
        ReachingPoints reachingPoints = new ReachingPoints();
        System.out.println(reachingPoints.reachingPoints(1,1,3,5));
    }


    public boolean ReachingPointsIterative (int sx, int sy, int tx, int ty) {
        if ((tx == sx && sy == ty) ||
                (tx == sx && ty%sx == sy%sx) ||
                (ty == sy && tx%ty == sx%sy)){
            return true;
        }
        if ((tx == sx && ty%sx != sy%sx) ||
                (ty == sy && tx%ty != sx%sy)){
            return false;
        }

        while (tx > 0 && ty > 0) {
            if (tx > ty) {
                tx = tx -ty;
            } else {
                ty= ty-tx;
            }

            if (tx==sx && ty==sy) {
                return true;
            }

            if (tx < sx || ty < sy) {
                return false;
            }
        }
        return false;
    }
}
