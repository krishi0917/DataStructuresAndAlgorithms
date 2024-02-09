package LeetcodePrograms.InterviewQuestions.Roblox_BrowserHistory;

import java.util.ArrayList;
import java.util.List;

public class BrowserWithTabs {
        private List<BrowserHistory> tabs;
        private int currentTabIndex;

        public BrowserWithTabs() {
            tabs = new ArrayList<>();
            tabs.add(new BrowserHistory("https://www.example.com"));
            currentTabIndex = 0;
        }

        public void switchTab(int tabIndex) {
            if (tabIndex >= 0 && tabIndex < tabs.size()) {
                currentTabIndex = tabIndex;
            }
        }

        public void newTab(String homepage) {
            tabs.add(new BrowserHistory(homepage));
            currentTabIndex = tabs.size() - 1;
        }

        public void closeTab(int tabIndex) {
            if (tabs.size() > 1 && tabIndex >= 0 && tabIndex < tabs.size()) {
                tabs.remove(tabIndex);
                currentTabIndex = Math.min(tabIndex, tabs.size() - 1);
            }
        }

        public void visit(String url) {
            tabs.get(currentTabIndex).visit(url);
        }

        public String back(int steps) {
            return tabs.get(currentTabIndex).back(steps);
        }

        public String forward(int steps) {
            return tabs.get(currentTabIndex).forward(steps);
        }

        public String getCurrentUrl() {
            return tabs.get(currentTabIndex).getCurrentUrl();
        }

        public static void main(String[] args) {
            BrowserWithTabs browser = new BrowserWithTabs();

            browser.visit("https://www.example.com/page1");
            browser.visit("https://www.example.com/page2");
            browser.newTab("https://www.example.com/tab2_homepage");
            browser.switchTab(1);

            System.out.println("Current URL (Tab 1): " + browser.getCurrentUrl());  // https://www.example.com/tab2_homepage
        }
}
