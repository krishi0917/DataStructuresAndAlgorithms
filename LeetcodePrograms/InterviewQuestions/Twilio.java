package LeetcodePrograms.InterviewQuestions;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/*

OFX is a protocol used by financial institutions to transfer financial transaction data. You can see an example of an OFX file below.

To process this file, one method is to build a custom parser to read and understand OFX. This is a lot of work. What if we were to instead convert this OFX file into a valid XML file and then use existing XML parsers to understand this file?

Task: Build a OFX to XML program that will take in the below input and output valid XML.

Hint: Use a data structure that keeps the Element node name and the value together in a single object

Function Description

Complete the function convertOFX2XML in the editor below. The function must return XML as string.

convertOFX2XML has the following parameter(s):

    oxfDocument:  OFX Document as string

Input Format For Custom Testing
The first line contains an OFX file as string.

Sample Case 0
Sample Input For Custom Testing

<OFX>
    <Transactions>
        <Transaction>
            <Amount>53
            <DatePosted>20201105000000</DatePosted>
        </Transaction>
    </Transactions>
</OFX>


TC 0
input
<OFX><Amount>35</OFX>
Your Output (stdout)
</OFX><Amount><OFX>
Expected Output
<OFX><Amount>35</Amount></OFX>

TC 1
input
<OFX></OFX>
Your Output (stdout)
</OFX><OFX>
Expected Output
<OFX></OFX>

Tc 2
<OFX><Transactions><Transaction><Amount>53</Amount><DatePosted>20201105000000</DatePosted></Transaction></Transactions></OFX>
Your Output (stdout)

Download
</OFX></Transactions></Transaction></DatePosted><DatePosted></Amount><Amount><Transaction><Transactions><OFX>
Expected Output
<OFX><Transactions><Transaction><Amount>53</Amount><DatePosted>20201105000000</DatePosted></Transaction></Transactions></OFX>

TC 3

Input (stdin)
<OFX><Transactions><Transaction><Amount>53<DatePosted>20201105000000</DatePosted></Transaction></Transactions></OFX>
Your Output (stdout)
</OFX></Transactions></Transaction></DatePosted><DatePosted><Amount><Transaction><Transactions><OFX>
Expected Output
<OFX><Transactions><Transaction><Amount>53</Amount><DatePosted>20201105000000</DatePosted></Transaction></Transactions></OFX>

Tc 4
g Answer
Input (stdin)
<OFX><SIGNONMSGSRSV1><SONRS><STATUS><CODE>0<SEVERITY>INFO</STATUS><DTSERVER>20210131191708.300<LANGUAGE>ENG<FI><ORG>ChaseBank<FID>2000</FI></SONRS></SIGNONMSGSRSV1><BANKMSGSRSV1><STMTTRNRS><TRNUID>1<STATUS><CODE>0<SEVERITY>INFO</STATUS><STMTRS><CURDEF>USD<BANKACCTFROM><BANKID>USA<ACCTID>*****123456<ACCTTYPE>CHECKING</BANKACCTFROM><BANKTRANLIST><DTSTART>20180131<DTEND>20210131<STMTTRN><TRNTYPE>CREDIT<DTPOSTED>20201231000000.000[-08:PST]<TRNAMT>5.00<FITID>1234567890<NAME>DIVIDEND<MEMO>DIVIDEND</STMTTRN><STMTTRN><TRNTYPE>DEBIT<DTPOSTED>20201124000000.000[-08:PST]<TRNAMT>-20.00<FITID>1234567890<NAME>Safeway<MEMO>SafewaySanFrancisco</STMTTRN></BANKTRANLIST><LEDGERBAL><BALAMT>10000.00</BALAMT><DTASOF>20210131191708.300</DTASOF></LEDGERBAL><AVAILBAL><BALAMT>10000.00</BALAMT><DTASOF>20210131191708.300</DTASOF></AVAILBAL></STMTRS></STMTTRNRS></BANKMSGSRSV1></OFX>
Your Output (stdout)

Download
</OFX></BANKMSGSRSV1></STMTTRNRS></STMTRS></AVAILBAL></DTASOF><DTASOF></BALAMT><BALAMT><AVAILBAL></LEDGERBAL></DTASOF><DTASOF></BALAMT><BALAMT><LEDGERBAL></BANKTRANLIST></STMTTRN><MEMO><NAME><FITID><TRNAMT><DTPOSTED><TRNTYPE><STMTTRN></STMTTRN><MEMO><NAME><FITID><TRNAMT><DTPOSTED><TRNTYPE><STMTTRN><DTEND><DTSTART><BANKTRANLIST></BANKACCTFROM><ACCTTYPE><ACCTID><BANKID><BANKACCTFROM><CURDEF><STMTRS></STATUS><SEVERITY><CODE><STATUS><TRNUID><STMTTRNRS><BANKMSGSRSV1></SIGNONMSGSRSV1></SONRS></FI><FID><ORG><FI><LANGUAGE><DTSERVER></STATUS><SEVERITY><CODE><STATUS><SONRS><SIGNONMSGSRSV1><OFX>
Expected Output
<OFX><SIGNONMSGSRSV1><SONRS><STATUS><CODE>0</CODE><SEVERITY>INFO</SEVERITY></STATUS><DTSERVER>20210131191708.300</DTSERVER><LANGUAGE>ENG</LANGUAGE><FI><ORG>ChaseBank</ORG><FID>2000</FID></FI></SONRS></SIGNONMSGSRSV1><BANKMSGSRSV1><STMTTRNRS><TRNUID>1</TRNUID><STATUS><CODE>0</CODE><SEVERITY>INFO</SEVERITY></STATUS><STMTRS><CURDEF>USD</CURDEF><BANKACCTFROM><BANKID>USA</BANKID><ACCTID>*****123456</ACCTID><ACCTTYPE>CHECKING</ACCTTYPE></BANKACCTFROM><BANKTRANLIST><DTSTART>20180131</DTSTART><DTEND>20210131</DTEND><STMTTRN><TRNTYPE>CREDIT</TRNTYPE><DTPOSTED>20201231000000.000[-08:PST]</DTPOSTED><TRNAMT>5.00</TRNAMT><FITID>1234567890</FITID><NAME>DIVIDEND</NAME><MEMO>DIVIDEND</MEMO></STMTTRN><STMTTRN><TRNTYPE>DEBIT</TRNTYPE><DTPOSTED>20201124000000.000[-08:PST]</DTPOSTED><TRNAMT>-20.00</TRNAMT><FITID>1234567890</FITID><NAME>Safeway</NAME><MEMO>SafewaySanFrancisco</MEMO></STMTTRN></BANKTRANLIST><LEDGERBAL><BALAMT>10000.00</BALAMT><DTASOF>20210131191708.300</DTASOF></LEDGERBAL><AVAILBAL><BALAMT>10000.00</BALAMT><DTASOF>20210131191708.300</DTASOF></AVAILBAL></STMTRS></STMTTRNRS></BANKMSGSRSV1></OFX>
 */
public class Twilio {
    static class XmlParse{
        String tag;
        String value;
        public XmlParse(String t, String v){
            tag = t;
            value = v;
        }
    }
// <OFX><Amount>35</OFX>
    public static String convertDocument(String document){
        int i = 0 ;
        StringBuilder sb = new StringBuilder();
        Stack<XmlParse> stack = new Stack<>();
        while(i < document.length()){
            if(document.charAt(i) == '<'){

                sb = new StringBuilder();
            }else if (document.charAt(i) == '>'){
                stack.push(new XmlParse(sb.toString(),null));
                sb = new StringBuilder();
            }else{
                sb.append(document.charAt(i));
            }
            i++;
        }
        return null;
    }
    public static void main(String []args){

    }
}
