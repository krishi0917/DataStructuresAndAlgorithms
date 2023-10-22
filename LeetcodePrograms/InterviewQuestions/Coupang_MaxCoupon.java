package LeetcodePrograms.InterviewQuestions;

// Find competitor’s discount.
// Class CompetitorCoupon
// {
//   String competitorSite;  //AMAZON, EBAY, WALMART etc.
//   String couponType;   // TypeA, TypeB, TypeC etc,.
//   Integer couponAmount; //1000, 2000, etc,.
// }
/*
class CompetitiorCoupon
{
    Sting amazon
    couponType Type A
    couponAmount 100
}
{
    Sting amazon
    couponType Type A
    couponAmount 200
}
// Total discount amount for a given competitor = Max(TypeA) + Max(TypeB) + Max(TypeC)  = total
*/
import java.util.*;

public class Coupang_MaxCoupon {

    Map<String, Map<String, Integer>> competitorToCouponMaps;

    static class Result {
        String compSite;
        int totalDiscount;

        public Result(String cSite, int tDiscount) {
            compSite = cSite;
            totalDiscount = tDiscount;
        }
    }

    static class CompetitiorCoupon {
        String competitorSite;  //AMAZON, EBAY, WALMART etc.

        String couponType;   // TypeA, TypeB, TypeC etc,.

        Integer couponAmount; //1000, 2000, etc,.
    }

    public Coupang_MaxCoupon() {
        competitorToCouponMaps = new HashMap<>();
    }

    public Result findCompetitorCoupon(List<CompetitiorCoupon> competitiorCouponList, int N) {
        if (competitiorCouponList == null || competitiorCouponList.size() == 0) {
            return null;
        }

        for (CompetitiorCoupon competitiorCoupon : competitiorCouponList) {
            String currentCompetitorSite = competitiorCoupon.competitorSite;
            String currentCouponType = competitiorCoupon.couponType;
            int currentCouponAmount = competitiorCoupon.couponAmount;
            if (competitorToCouponMaps.containsKey(currentCompetitorSite)) {
                Map<String, Integer> typeToCouponMap = competitorToCouponMaps.get(currentCompetitorSite);
                if (typeToCouponMap.containsKey(currentCouponType)) {
                    int prevAmount = typeToCouponMap.get(currentCouponType);
                    if (currentCouponAmount > prevAmount) {
                        typeToCouponMap.put(currentCompetitorSite, currentCouponAmount);
                        competitorToCouponMaps.put(currentCompetitorSite, typeToCouponMap);
                    }
                } else {
                    Map<String, Integer> temptypeToCouponMap = new HashMap<>();
                    temptypeToCouponMap.put(currentCouponType, currentCouponAmount);
                    competitorToCouponMaps.put(currentCompetitorSite, temptypeToCouponMap);
                }
            } else {
                Map<String, Integer> temptypeToCouponMap = new HashMap<>();
                temptypeToCouponMap.put(currentCouponType, currentCouponAmount);
                competitorToCouponMaps.put(currentCompetitorSite, temptypeToCouponMap);
            }
        }

        String currentProduct = "";
        int discountAmount = -1;
        Result res = null;
        PriorityQueue<Result> pq = new PriorityQueue<Result>(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                Integer i1 = o1.totalDiscount;
                Integer i2 = o2.totalDiscount;
                return i2 - i1;
            }
        });

        for (Map.Entry<String, Map<String, Integer>> entry : competitorToCouponMaps.entrySet()) { // n products
            String prod = entry.getKey();
            Map<String, Integer> tempTypeToCouponMap = entry.getValue();
            int currentSum = 0;
            for (Map.Entry<String, Integer> tempEntry : tempTypeToCouponMap.entrySet()) {//  m types.  n*m
                currentSum += tempEntry.getValue();
            }

            // n* m
            pq.add(new Result(prod, currentSum)); // n * mlogm
            if (currentSum > discountAmount) {
                res = new Result(prod, currentSum);
            }

        }
        return res;
    }
}