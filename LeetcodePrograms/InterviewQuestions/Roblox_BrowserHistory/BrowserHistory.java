package LeetcodePrograms.InterviewQuestions.Roblox_BrowserHistory;

import java.util.Stack;
/*
Time Complexity:
Visit Method:
Time complexity: O(1)
The visit method involves updating the current URL and clearing the forward history. These operations take constant time.
Back Method:
Time complexity: O(steps)
The back method involves popping URLs from the backStack. The time complexity depends on the number of steps to go backward.
Forward Method:

Time complexity: O(steps)
The forward method involves popping URLs from the forwardStack. The time complexity depends on the number of steps to go forward.
Space Complexity:
Stacks:

Space complexity: O(N), where N is the total number of URLs visited
The space complexity is determined by the size of the backStack and forwardStack, which store the URLs for backward and forward navigation.
Current URL:

Space complexity: O(1)
The currentUrl variable requires constant space.
Overall:
The time complexity for each operation depends on the number of steps specified. The space complexity is proportional to the total number of URLs visited.

In summary:
Time complexity for visit: O(1)
Time complexity for back and forward: O(steps)
Space complexity: O(N), where N is the total number of URLs visite
 */
public class BrowserHistory {
        private Stack<String> backStack;
        private Stack<String> forwardStack;
        private String currentUrl;

        public BrowserHistory(String homepage) {
            backStack = new Stack<>();
            forwardStack = new Stack<>();
            currentUrl = homepage;
        }

        public void visit(String url) {
            backStack.push(currentUrl);
            forwardStack.clear();
            currentUrl = url;
        }

        public String back(int steps) {
            while (steps > 0 && !backStack.isEmpty()) {
                forwardStack.push(currentUrl);
                currentUrl = backStack.pop();
                steps--;
            }
            return currentUrl;
        }

        public String forward(int steps) {
            while (steps > 0 && !forwardStack.isEmpty()) {
                backStack.push(currentUrl);
                currentUrl = forwardStack.pop();
                steps--;
            }
            return currentUrl;
        }

        public String getCurrentUrl() {
            return currentUrl;
        }
}
