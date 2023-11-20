package LeetcodePrograms;
E-mail validation is a widely known problem with several widely known solutions. However, you are working on an advanced spam-detection feature and you don't just need to check that some e-mail is correct, you need to count the number of correct e-mails that can be obtained using the given pattern.

        Assume that a correct e-mail is a string satisfying the following conditions:

        the string has exactly one character '@' in it;
        the string before '@' consists of one or more non-empty groups of lowercase letters from 'a' to 'e' inclusive, separated by '.';
        the string after '@' consists of two or more non-empty groups of lowercase letters from 'a' to 'e' inclusive, separated by '.'.
        For example,

        "a@b.e"
        "abcde.edcba@a.b.c.d.e"
        are correct e-mails, while those listed below are not:

        "@a.a" (no non-empty groups of lowercase letters before '@')
        "a@a" (only one non-empty group of lowercase letters after '@')
        "a.b@c.d@e.e" (more than one '@' character)
        "aa..aa@a.a" (no lowercase letters between two consecutive '.'s)
        "abc@d.e.f" ('f' does not belong to the range ['a'..'e'])
        "abc.@a.a" (no letters between '.' and '@')
        "abc@a.a." (no letters after the last '.')
        Example

        For pattern = "abcd@?bcd.ca", the output should be
        solution(pattern) = 5.

        The correct e-mails that may be obtained from this pattern are:

        "abcd@abcd.ca"
        "abcd@bbcd.ca"
        "abcd@cbcd.ca"
        "abcd@dbcd.ca"
        "abcd@ebcd.ca"
        Input/Output

        [execution time limit] 4 seconds (js)

        [memory limit] 1 GB

        [input] string pattern

        An e-mail pattern consisting of lowercase letters and symbols '?', '.' and '@'.

        Guaranteed constraints:
        5 ≤ pattern.length ≤ 15.

        [output] integer

        The number of correct e-mails that can be obtained by replacing all '?' with lowercase letters, '.' or '@'.

        [JavaScript] Syntax Tips

// Prints help message to the console
// Returns a string
        function helloWorld(name) {
        console.log("This prints to the console when you Run Tests");
        return "Hello, " + name;
        }

        Saved
        JavaScript
        Node.js v18.12.1
        596061625455565758495051525345464748404142434435363738393031323334
        }

        function scheduleResultsCleanup() {
        if (answerCnt.size > 10000)
        answerCnt.clear()

        if (answerCnt.isCleanupScheduled)
        return

        answerCnt.isCleanupScheduled = true

        isQuestionMark
        No results
        CORRECT
        TESTS
        CUSTOM TESTS
        RESULTS
        Tests passed: 11/11.
        Sample tests:
        6/6
        Hidden tests:
        5/5
        Score:
        300/300

        const checkThePat = /^[\?a-e]+([\?\.][\?a-e]+)*[\?@][\?a-e]+([\?\.][\?a-e]+)+$/
        const answerCnt = new Map()
        function solution(sample) {
        if (!checkThePattValidatation(sample))
        return 0

        scheduleResultsCleanup()

        if (sample.includes('@'))
        return checkPosition(sample, sample.indexOf('@'))
        else
        return checkPositionsAll(sample)
        }

        function checkPosition(sample, elementPosition) {
        let partLocal = sample.slice(0, elementPosition)
        let partSecond    = sample.slice(elementPosition + 1)

        return count(partLocal) * domCnt(partSecond)
        }

        function checkPositionsAll(sample) {
        let add = 0

        for (let i = 1; i < sample.length - 3; i++)
        if (isDotted(sample, i))
        add += checkPosition(sample, i)

        return add
        }

        function count(sample, s = 0, e) {
        sample = sample.slice(s, e)

        if (sample.length === 0)
        return 1

        if (answerCnt.has(sample))
        return answerCnt.get(sample)

        return newCnt(sample)
        }

        function newCnt(sample) {
        let sum = count(sample, 1)

        if (isDotted(sample, 1))
        sum += count(sample, 2)

        if (isAQuestion(sample))
        sum *= 5

        answerCnt.set(sample, sum)

        return sum
        }

        function domCnt(dom) {
        return count(dom) - undottedCount(dom)
        }

        function undottedCount(sample) {
        if (sample.includes('.'))
        return 0
        else
        return 5 ** questionMarkCount(sample)
        }

        function questionMarkCount(sample) {
        return sample.split('?').length - 1
        }

        function isDotted(string, index) {
        return isAQuestion(string, index)
        && isValidDotIndex(string, index)
        }

        function isValidDotIndex(string, index) {
        return ['@', '.', undefined].every(
        character => character !== string[index - 1]
        && character !== string[index + 1]
        )
        }

        function checkThePattValidatation(sample) {
        return checkThePat.test(sample)
        }

        function isAQuestion(str, ind = 0) {
        return str[ind] === '?'
        }

        function scheduleResultsCleanup() {
        if (answerCnt.size > 10000)
        answerCnt.clear()

        if (answerCnt.isCleanupScheduled)
        return

        answerCnt.isCleanupScheduled = true

        setTimeout(clearCountResults, 0)
        }

        function clearCountResults() {
        answerCnt.isCleanupScheduled = false

        answerCnt.clear()
        }