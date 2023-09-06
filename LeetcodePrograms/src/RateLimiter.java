package LeetcodePrograms.src;

import java.io.*;

/* Envelope Creation Limiter

Given access logs to DocuSign, enforce this rule:
    "a trial user cannot create more than 3 envelopes at any hour in a day".

We would like to store the envelope creation history for the last 24 hours.

At the end of each day the envelope creation history should be overwritten for a user.

Implement 2 functions:
  // check if a user is allowed
  is_allowed(timestamp: number, user_id: string)

  // record user action in an envelope creation log
  record_envelope_creation(timestamp: number, user_id:string)

Rules:
1. timestamp: number with a valid range of 1 to 24
2. user_id: string with a unique user id
3. envelope limit: Max of 3 envelope creations per hour
4. History of envelope creations are stored for 24 hours.

Map<String, int[] 24 element>

Map<String, Map<string, int>>
 - 03-03-2022-1 "mark": 3

 Map <userId, int[size 24]>
 */

public class RateLimiter {
    public static void main(String[]args){

    }
}
