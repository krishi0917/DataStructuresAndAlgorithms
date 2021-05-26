package LeetcodePrograms;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by rkhurana on 3/14/19.
 */
// #Facebook
// Understand how read4() works, Initially, I thought it takes input buf as the parameter. But actually, *buf is just as the name refers,
// it's a buffer char array of size 4. Realize the corner case where buf = "abcdef", n = 5. The last iteration within the while loop gets
// count = 2, while we only need 1 last character. This is why we need to compare "count" with "n - total".
// If the length of buf can be divided by 4, then we need this check if (count == 0) break; to terminate the loop

    //there is a second part to this question .. see that as well on leetcode
public class ReadNCharactersGivenRead4 extends Reader {
    public int read(char[] buf, int n) throws IOException {
        char[] temp = new char[4];
        int total = 0;
        while (total < n) {
            int count = read(temp);

            count = Math.min(count, n - total);
            if (count == 0) break;
            for (int i = 0; i < count; i++) {
                buf[total++] = temp[i];
            }
        }
        return total;
    }


    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }

    public int read2(char[] buf, int n) throws IOException {
        boolean eof = false;      // end of file flag
        int total = 0;            // total bytes have read
        char[] tmp = new char[4]; // temp buffer

        while (!eof && total < n) {
            int count = read(tmp);

            // check if it's the end of the file
            eof = count < 4;

            // get the actual count
            count = Math.min(count, n - total);

            // copy from temp buffer to buf
            for (int i = 0; i < count; i++)
                buf[total++] = tmp[i];
        }

        return total;
    }
}
