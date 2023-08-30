package LeetcodePrograms.InterviewQuestions.CloudKitchens;

import java.util.ArrayList;
import java.util.List;

public class Item {
        int itemId;
        ItemType itemType;
        String nameItem;
        double price;
        List<Integer> relatedId;
        public Item(){
            itemId = -1;
            nameItem = null;
            double price = 0.0;
            relatedId = new ArrayList<>();
        }
    }

