package LeetcodePrograms.InterviewQuestions.LinkedinRetainBestCache;

public interface DataSource <K, V extends Rankable> {
    V get (K key);
}
