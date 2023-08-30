package LeetcodePrograms.InterviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
    Consider a system of User and Feature objects, where a Feature describes the availability of
    an ongoing project or change in functionality. Features may be generally available, limited to
    certain regions, and/or intended for an A/B test. We need to write a deterministic system for
    identifying which Features are active for a given User. We also want to write tests to ensure
    that our system is working as intended each time we run it, given the example User and Feature
    data below.

    Users:

            [
    { "id": 0, "name": "eva",    "location": "US" },
    { "id": 1, "name": "tess",   "location": "US" },
    { "id": 2, "name": "rahool", "location": "CA" },
    { "id": 3, "name": "amanda", "location": "CA" }
        ]

    Features:

    [
    {
        "id": "annual_sale",
            "locations": ["US"],
        "abTest": true,
    },
    {
        "id": "enhanced_comments",
            "abTest": true,
    },
    {
        "id": "canada_promotion",
            "locations": ["CA"],
    }
        ]

## Part 1

    Write a function, `get_user_features(user, features)` which takes a single User object and a list
    of Feature objects and returns a set of the Feature IDs that apply to the given User.

    A User has three properties: an integer `id`, a string `name`, and a 2-letter country code
    string `location`.

    A Feature has three properties: a unique string `id`, an optional array of 2-letter country code
    strings, `locations`, which limits the feature to users with a matching location, and an optional
    boolean, `abTest`, which when set to true will only apply the feature to users with an even user ID.

    If `abTest` or `locations` are absent for a Feature, they have no effect.

    Given the features and users, the following results are expected:

            | User   | Features                            |
            | ------ | ----------------------------------- |
            | eva    | annual_sale, enhanced_comments      |
            | tess   | N/A                                 |
            | rahool | enhanced_comments, canada_promotion |
            | amanda | canada_promotion                    |

## Part 2

    Users may want to opt-in to or opt-out of a feature. Augment `get_user_features` to take into
    account two new properties on `User` objects: `optIn` and `optOut`, both of which are arrays of
    feature IDs to either opt into or opt out of. A user can be opted-in to an A/B test Feature,
    regardless of their ID. A user cannot be given a feature that is not available in their region,
    even if they try to opt-in to it.

            Users 0 (eva),  1 (tess) and 3 (amanda) would like to opt in to the `annual_sale` feature.

            User 2, (rahool), would like to opt out of the `enhanced_comments` and `canada_promotion` features.

            Our new user data is...

    Users:
    { "id": 0, "name": "eva",    "location": "US", "optIn": ["annual_sale"] }
    { "id": 1, "name": "tess",   "location": "US", "optIn": ["annual_sale"] }
    { "id": 2, "name": "rahool", "location": "CA", "optOut": ["enhanced_comments", "canada_promotion"] }
    { "id": 3, "name": "amanda", "location": "CA", "optIn": ["annual_sale"] }

    Given these changes and updates, the following results are now expected:

            | User   | Features                       |
            | ------ | ------------------------------ |
            | eva    | annual_sale, enhanced_comments |
            | tess   | annual_sale                    |
            | rahool | N/A                            |
            | amanda | canada_promotion               |


 */

public class StripeQuestion {
        static class User{
            int userId;
            String userName;
            String userLocation;
            List<String> optIn;
            List<String>optOut;
            boolean isOptIn ;
            public User(int id, String name, String location,boolean isOptIn, List<String> features){
                userId = id;
                userName = name;
                userLocation = location;
                isOptIn = isOptIn;
                if(isOptIn){
                    optIn = features;
                }else {
                    optOut = features;
                }
            }
        }
        static class Features{
            String id;
            List<String> locations;
            boolean abTest;
            public Features(String i, List<String> l , boolean test){
                id = i;
                locations = l;
                abTest = test;
            }
        }
        public List<String> get_user_features(User user, List<Features> features){
            // Map<String,List<String>> resultMap = new HashMap<>();

            boolean isValidLocation = false;
            List<String> result = new ArrayList<>();
            for(Features feature : features){
                if(feature.locations == null ){
                    isValidLocation = true;
                }else if(feature.locations.size()==0){
                    isValidLocation = false;
                }

                else if(feature.locations.contains(user.userLocation)){
                    isValidLocation = true;
                }else {
                    continue;
                }

                if((feature.abTest == true &&user.userId % 2 == 0 ) || user.isOptIn && user.optIn.contains(feature)) {
                    if ( isValidLocation) {
                        result.add(feature.id);
                    }
                }else if(isValidLocation && !user.isOptIn && user.optOut.contains(feature)){
                    continue;
                }
                else if(isValidLocation){
                    result.add(feature.id);
                }
            }
            // resultMap.put(user.userName,result);
            return result;
        }

        public List<String> get_user_featuresOptIn(User user, List<Features> features){
            // Map<String,List<String>> resultMap = new HashMap<>();

            boolean isValidLocation = false;
            List<String> result = new ArrayList<>();
            for(Features feature : features){
                if(feature.locations == null ){
                    isValidLocation = true;
                }else if(feature.locations.size()==0){
                    isValidLocation = false;
                }

                else if(feature.locations.contains(user.userLocation)){
                    isValidLocation = true;
                }else {
                    continue;
                }

                if(feature.abTest == true) {
                    if (user.userId % 2 == 0 && isValidLocation) {
                        result.add(feature.id);
                    }
                }
                else if(isValidLocation){
                    result.add(feature.id);
                }
            }
            // resultMap.put(user.userName,result);
            return result;
        }

        public static void main(String[] args) {
            User user1 = new User(0,"eva","US",true, Arrays.asList("annual_sales"));
            User user2 = new User(1,"tess","US",true, Arrays.asList("annual_sales"));
            User user3 = new User(2,"rahul","CA",false,Arrays.asList("enhanced_comments","canada_prommotions"));
            User user4 = new User(3,"amanda","CA",true, Arrays.asList("annual_sales"));
            Features f1 = new Features("annual_sales",Arrays.asList("US"), true);
            Features f2 = new Features("enhanced_comments",null, true);
            Features f3 = new Features("canada_prommotions",Arrays.asList("CA"), false);
            List<Features> featuresList = Arrays.asList(f1,f2,f3);
            StripeQuestion m = new StripeQuestion();
            //   System.out.println(user1.userName + "  " + m.get_user_features(user1,featuresList));
            System.out.println(user2.userName + "  " + m.get_user_features(user2,featuresList));
            //    System.out.println(user3.userName + "  " + m.get_user_features(user3,featuresList));
            //   System.out.println(user4.userName + "  " + m.get_user_features(user4,featuresList));
        }
    }

