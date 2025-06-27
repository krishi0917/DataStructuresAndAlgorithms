package LeetcodePrograms.src.ObjectMapperTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class test {
    static String json = "{\n" +
            "    \"campaigns\":\n" +
            "    [\n" +
            "        {\n" +
            "            \"campaignId\": \"8c67781a-0ea8-4505-8a3b-d05e86c7b197\",\n" +
            "            \"campaignSegmentId\": \"171a3983-1fef-42f1-aa42-6132d90426cf\",\n" +
            "            \"module\": \"sidekick\",\n" +
            "            \"image\": \"ipcSplashScreenIamStandardSelfService_1920x576\",\n" +
            "            \"headerText\": \"IPC-4961 Header\",\n" +
            "            \"bodyText\": \"IPC-4961 Body\",\n" +
            "            \"action\": \"{\\\"url\\\":\\\"https://players.brightcove.net/6118377982001/ZhVNgbpMt_default/index.html?videoId=6195321657001\\\",\\\"type\\\":\\\"VIDEO\\\",\\\"closeAction\\\":\\\"Dismissed\\\",\\\"promoCode\\\":\\\"\\\",\\\"config\\\":\\\"\\\",\\\"magentoPath\\\":\\\"\\\"}\",\n" +
            "            \"actionText\": \"IPC-4961 CTA\",\n" +
            "            \"actions\": [\n" +
            "                {\n" +
            "                    \"actionText\": \"IPC-4961 CTA\",\n" +
            "                    \"action\": \"{\\\"url\\\":\\\"https://players.brightcove.net/6118377982001/ZhVNgbpMt_default/index.html?videoId=6195321657001\\\",\\\"type\\\":\\\"VIDEO\\\",\\\"closeAction\\\":\\\"Dismissed\\\",\\\"promoCode\\\":\\\"\\\",\\\"config\\\":\\\"\\\",\\\"magentoPath\\\":\\\"\\\"}\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"action\": \"{\\\"type\\\":\\\"\\\",\\\"url\\\":\\\"\\\"}\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "\n" +
            "        {\n" +
            "            \"campaignId\": \"d74ba294-d37e-4775-ab16-e1b1615e124d\",\n" +
            "            \"campaignSegmentId\": \"423b956a-7d12-4997-ba49-f289bf70e4ed\",\n" +
            "            \"module\": \"sidekick\",\n" +
            "            \"image\": \"postSendingFlying\",\n" +
            "            \"headerText\": \"Envelope sent!\",\n" +
            "            \"messageText\": \"Buy now and save 20%\",\n" +
            "            \"bodyText\": \"Offer ends soon. Redeem savings with promo code: ContactSales\",\n" +
            "            \"action\": \"{\\\"url\\\":\\\"contactSales\\\",\\\"type\\\":\\\"INPRODUCT\\\",\\\"contactSalesConfig\\\":{\\\"feature\\\":\\\"PQLs_from_IAM_IPC_campaigns\\\",\\\"offerName\\\":\\\"CONUS_US_25Q1_ContactUsProduct\\\",\\\"offer\\\":\\\"7018Z000002qfL3QAI\\\",\\\"productOfInterest\\\":\\\"SMS\\\"},\\\"closeAction\\\":\\\"Dismissed\\\"}\",\n" +
            "            \"actionText\": \"Contact Sales\",\n" +
            "            \"actions\": [\n" +
            "            {\n" +
            "                \"actionText\": \"Contact Sales\",\n" +
            "                \"action\": \"{\\\"url\\\":\\\"contactSales\\\",\\\"type\\\":\\\"INPRODUCT\\\",\\\"contactSalesConfig\\\":{\\\"feature\\\":\\\"PQLs_from_IAM_IPC_campaigns\\\",\\\"offerName\\\":\\\"CONUS_US_25Q1_ContactUsProduct\\\",\\\"offer\\\":\\\"7018Z000002qfL3QAI\\\",\\\"productOfInterest\\\":\\\"SMS\\\"},\\\"closeAction\\\":\\\"Dismissed\\\"}\"\n" +
            "            }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    class Campaign {
        public String campaignId;
        public String campaignSegmentId;
        public String module;
        public String image;
        public String headerText;
        public String messageText;
        public String bodyText;
        public String actionText;

        public String action; // stringified JSON, will parse into ActionDetail
        public List<TestClass3.ActionWrapper> actions;

        public TestClass3.ActionDetail parsedAction; // Populated manually after parsing
    }
    class CampaignList{
        public List<Campaign> campaigns;
    }
    public static void main(String []args) throws Exception{
        ObjectMapper mapper = new ObjectMapper();

//            CampaignList campaignList = mapper.readValue(json,CampaignList.class);
//            List<Campaign> = campaignList.campaignList.get(0)
//
//
//                List<Campaign> campaigns = mapper.readValue(json,CampaignList.class);

    }
}
