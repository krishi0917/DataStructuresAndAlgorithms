package LeetcodePrograms.InterviewQuestions;
import java.util.*;
/**
 * @author Rishi Khurana
 * Codewriting
 * https://app.codesignal.com/live-interview/7iyhmHizEcNcySpbQ?awwBoard=prod-7iyhmHizEcNcySpbQ
 * Given a stream of incoming "buy" and "sell" orders (as lists of limit price, quantity, and side, like ["155", "3",
 * "buy"]), determine the total quantity (or number of "shares") executed.
 *
 * A "buy" order can be executed if there is a corresponding "sell" order with a price that is less than or equal to
 * the price of the "buy" order.
 * Similarly, a "sell" order can be executed if there is a corresponding "buy" order with a price that is greater
 * than or equal to the price of the "sell" order.
 * It is possible that an order does not execute immediately if it isn't paired to a counterparty. In that case, you
 * should keep track of that order and execute it at a later time when a pairing order is found.
 * You should ensure that orders are filled immediately at the best possible price. That is, an order should be
 * executed when it is processed, if possible. Further, "buy" orders should execute at the lowest possible price and
 * "sell" orders at the highest possible price at the time the order is handled.
 *
 * Note that orders can be partially executed.
 *
 * --- Sample Input ---
 *
 * orders = [
 *   ['150', '5', 'buy'],    # Order A
 *   ['190', '1', 'sell'],   # Order B
 *   ['200', '1', 'sell'],   # Order C
 *   ['100', '9', 'buy'],    # Order D
 *   ['140', '8', 'sell'],   # Order E
 *   ['210', '4', 'buy'],    # Order F
 * ]
 *
 * Sample Output
 * 9
 *
 * [execution time limit] 3 seconds (java)
 *
 * [input] array.array.string orders
 *
 * [output] integer
 *
 *
 * Test 1
 * Input:
 * orders:
 * [["150","5","buy"],
 *  ["190","1","sell"],
 *  ["200","1","sell"],
 *  ["100","9","buy"],
 *  ["140","8","sell"],
 *  ["210","4","buy"]]
 * Expected Output:
 * 9
 *
 * Test 2
 * Input:
 * orders:
 * [["150","10","buy"],
 *  ["165","7","sell"],
 *  ["168","3","buy"],
 *  ["155","5","sell"],
 *  ["166","8","buy"]]
 * Expected Output:
 * 11
 *
 *
 * Test 3
 * Input:
 * orders:
 * [["1","1","buy"],
 *  ["1","1","sell"],
 *  ["4","4","sell"],
 *  ["6","4","buy"],
 *  ["100","50","buy"],
 *  ["99","50","sell"]]
 * Expected Output:
 * 55
 *
 *
 * Test 4
 * Input:
 * orders:
 * [["1000","1","buy"],
 *  ["1000","99","buy"],
 *  ["1001","20","buy"],
 *  ["1000","1","buy"],
 *  ["1000","1","buy"],
 *  ["1010","10","sell"],
 *  ["1010","10","sell"],
 *  ["900","12","sell"],
 *  ["1001","8","sell"]]
 * Expected Output:
 * 20
 *
 *
 * Test 5
 * Input:
 * orders:
 * [["60","27","sell"],
 *  ["61","1","buy"],
 *  ["20","16","buy"],
 *  ["21","3","buy"],
 *  ["62","1","sell"],
 *  ["61","2","sell"]]
 * Expected Output:
 * 1
 *
 * Test 6
 * Input:
 * orders:
 * [["10000","5","sell"],
 *  ["10000","5","sell"],
 *  ["10010","5","buy"],
 *  ["10000","5","buy"],
 *  ["10010","5","buy"],
 *  ["10020","5","sell"],
 *  ["10015","5","sell"],
 *  ["10017","5","buy"],
 *  ["10030","5","buy"],
 *  ["10010","5","sell"]]
 * Expected Output:
 * 25
 *
 * Test 8
 * Input:
 * orders:
 * [["1002","100","sell"],
 *  ["10","99","buy"],
 *  ["28","9","buy"],
 *  ["100","4","sell"],
 *  ["99","76","buy"],
 *  ["111","2000","sell"],
 *  ["1","45","buy"],
 *  ["50","1","buy"],
 *  ["231","48","sell"],
 *  ["31415","3","sell"],
 *  ["85","28","buy"],
 *  ["18","8","buy"],
 *  ["1000","5","sell"]]
 * Expected Output:
 * 0
 *
 * Test 9
 * Input:
 * orders:
 * [["1234","8","sell"],
 *  ["1234","1","buy"],
 *  ["1234","1","buy"],
 *  ["1234","1","buy"],
 *  ["1234","1","buy"],
 *  ["1234","2","sell"],
 *  ["1234","8","sell"],
 *  ["1234","3","buy"]]
 * Expected Output:
 * 7
 *
 * Test 10
 * Input:
 * orders:
 * [["100","5","buy"],
 *  ["200","1","sell"],
 *  ["190","1","sell"],
 *  ["150","9","buy"],
 *  ["140","8","sell"],
 *  ["210","5","buy"]]
 * Expected Output:
 * 10
 *
 * Test 11
 * Input:
 * orders:
 * [["90","1","buy"],
 *  ["100","1","buy"],
 *  ["90","1","sell"],
 *  ["100","1","sell"]]
 * Expected Output:
 * 1
 *
 * Test 12
 * Input:
 * orders:
 * [["90","10","buy"],
 *  ["85","3","sell"],
 *  ["75","10","sell"]]
 * Expected Output:
 * 10
 *
 */

class RobinhoodQuestion {
    class  OrderBook{
        int price;
        int quantity;
        String orderType;
        public OrderBook(int price ,  int quantity , String orderType){
            this.price = price;
            this.quantity = quantity;
            this.orderType = orderType;
        }

    }

    int order_book(String[][] orders) {

        int count = 0;
        Queue<OrderBook> buyOrder = new PriorityQueue<>(new Comparator<OrderBook>() {
            public int compare(OrderBook o1, OrderBook o2) {
                return o2.price - o1.price;
            }
        });

        Queue<OrderBook> sellOrder = new PriorityQueue<>(new Comparator<OrderBook>() {
            public int compare(OrderBook o1, OrderBook o2) {
                return o1.price - o2.price;
            }
        });

        for (int i = 0; i < orders.length; i++) {
            String[] order = orders[i];
            if (order[2].equals("buy")) {
                if (!sellOrder.isEmpty()) {
                    OrderBook soldOrder = sellOrder.poll();
                    if (Integer.valueOf(order[0]) >= soldOrder.price) {
                        //check the quantity
                        if (Integer.valueOf(order[1]) >= soldOrder.quantity) {
                            System.out.println("order on 39 third step " + Integer.valueOf(order[0]) + "  " + Integer
                                    .valueOf(order[1]) + "  " + order[2]);
                            count += soldOrder.quantity;

                            System.out.println("count" + count);

                        } else {
                            count += Integer.valueOf(order[1]);
                            OrderBook refreshedOrder = new OrderBook(soldOrder.price, soldOrder.quantity - count,
                                    soldOrder.orderType);
                            sellOrder.offer(refreshedOrder);
                        }

                    } else {
                        OrderBook newOrder = new OrderBook(Integer.valueOf(order[0]), Integer.valueOf(order[1]),
                                order[2]);
                        buyOrder.offer(newOrder);
                    }
                } else {
                    System.out.println(
                            "order on 52 " + Integer.valueOf(order[0]) + "  " + Integer.valueOf(order[1]) + "  "
                                    + order[2]);
                    OrderBook newOrder = new OrderBook(Integer.valueOf(order[0]), Integer.valueOf(order[1]), order[2]);
                    buyOrder.offer(newOrder);
                }

            } else { //sell
                if (!buyOrder.isEmpty()) {
                    OrderBook boughtOrder = buyOrder.poll();
                    if (Integer.valueOf(order[0]) <= boughtOrder.price) {
                        //check the quantity
                        if (Integer.valueOf(order[1]) >= boughtOrder.quantity) {

                            System.out.println("order on 64 second step " + Integer.valueOf(order[0]) + "  " + Integer
                                    .valueOf(order[1]) + "  " + order[2]);
                            count += boughtOrder.quantity;

                        } else {
                            count += Integer.valueOf(order[1]);
                            OrderBook refreshedOrder = new OrderBook(boughtOrder.price, boughtOrder.quantity - count,
                                    boughtOrder.orderType);
                            sellOrder.offer(refreshedOrder);
                        }

                    } else {
                        OrderBook newOrder = new OrderBook(Integer.valueOf(order[0]), Integer.valueOf(order[1]),
                                order[2]);
                        sellOrder.offer(newOrder);
                    }

                } else {
                    System.out.println(
                            "order on 80 third  step " + Integer.valueOf(order[0]) + "  " + Integer.valueOf(order[1])
                                    + "  " + order[2]);
                    OrderBook newOrder = new OrderBook(Integer.valueOf(order[0]), Integer.valueOf(order[1]), order[2]);
                    sellOrder.offer(newOrder);
                }
            }
        }
        return count;
    }
}
// buy >= sell ...
// if selling , then find highest buy
// if buying , then find lowest sell
// how many items have we executed
// [
//   ['150', '5', 'buy'],    # Order A
//   ['190', '1', 'sell'],   # Order B
//   ['200', '1', 'sell'],   # Order C
//   ['100', '9', 'buy'],    # Order D
//   ['140', '8', 'sell'],   # Order E
//   ['210', '4', 'buy'],    # Order F
// ]