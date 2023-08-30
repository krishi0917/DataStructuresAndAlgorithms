package LeetcodePrograms.InterviewQuestions.CloudKitchens;


/*
 * Click `Run` to execute the snippet below!
 */

import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.


 4
ENTREE
Spaghetti
10.95
2
3

1
CATEGORY
Pasta
4
5

2
OPTION
Meatballs
1.00

3
OPTION
Chicken
2.00

5
ENTREE
Lasagna
12.00

6
ENTREE
Caesar Salad
9.75
3

  Line 0: The ID of the item
  Line 1: The item type, either CATEGORY, ENTREE or OPTION
  Line 2: The name of the item
  Line 3: The price of the item for ENTREE and OPTION. Not present for CATEGORY items.

  Any other line: A list of item IDs that are linked to the current item. OPTIONs do not have any linked items.
 */

import java.util.List;
class MainMenu {
    List<Item> items;
    public MainMenu(MenuStreamImpl menuStreamImpl) {
        MenuStreamImpl menustreamImpl = menuStreamImpl;
        items = new ArrayList<>();
        formMenu(menustreamImpl);
    }

    public void formMenu(MenuStreamImpl menustreamImpl) {
        Item item = new Item();
        List<Integer> relatedItems = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            String s = menustreamImpl.nextLine();
            if (s == null) {
                flag= false;
            } else if (s.equals("")) {
                item.relatedId = relatedItems;
                items.add(item);
                item = new Item();
                relatedItems = new ArrayList<>();
            } else {
                if (item.itemId == -1) {
                    item.itemId = Integer.parseInt(s);
                } else if (s.equals("ENTREE")) {
                    item.itemType = ItemType.Entree;
                } else if (s.equals("CATEGORY")) {
                    item.itemType = ItemType.Category;
                } else if (s.equals("OPTION")) {
                    item.itemType = ItemType.Option;
                }else if(item.nameItem == null){
                    item.nameItem = s;
                } else if (item.price == 0.0) {
                    item.price = Double.parseDouble(s);
                } else {
                    relatedItems.add(Integer.parseInt(s));
                }
            }
        }
    }

    public void print() {
        System.out.println("Menu for today");
        for(Item i : items){
            System.out.println ("ItemId " + i.itemId + " ItemType " + i.itemType + " ItemName "+ i.nameItem + " ItemPrice " + i.price + " Toppings " + i.relatedId);
        }
    }

    public static void main(String[] args) {
        MainMenu menu = new MainMenu(new MenuStreamImpl());
        menu.print();
    }
}
