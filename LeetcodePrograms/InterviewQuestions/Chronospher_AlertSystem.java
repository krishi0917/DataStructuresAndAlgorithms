package LeetcodePrograms.InterviewQuestions;

/*
        Overview:

        The exercise requires you to develop an alert execution engine that executes alerts at the specified interval
        checks and sends notifications if alerts fire. Some engine basics:

        - The alert engine is coded against an alerts client. We provide an alerts API interface and a corresponding fake client implementation below.
        - One alert execution cycle involves:
        - Making an API call (query) to query the value of the metric
        - Comparing the return value against Critical thresholds
        - Determining the state of the alert
        - Make a Notify API call if the alert is in CRITICAL state
        - Make a Resolve API call if the alert is transitioning to PASS state
        - Alert can be in different states based on the current value of the query and the thresholds
        - It is considered PASS if value <= critical threshold
        - It is considered CRITICAL if value > critical threshold

        Part I: Basic Alert Execution with Interval

        Build an alert execution engine that:
        - Queries for alerts using the query_alerts API and execute them at the specified interval
        - Alerts will not change over time, so only need to be loaded once at start
        - The basic alert engine will send notifications whenever it sees a value that exceeds the critical threshold.
        '''
        import time
        # Alert structures that are used by the alerts client.
class Alert:
        def __init__(self, name, query, interval_secs, critical_value, critical_message):
        self.name = name
        self.query = query
        self.interval_secs = interval_secs
        self.critical = Threshold(critical_value, critical_message)

class Threshold:
        def __init__(self, critical_value, critical_message):
        self.value = critical_value
        self.message = critical_message

        # Fake implementation of alerts client that you are free to change to help test the program.
class Client:
        def __init__(self):
        pass

        def query_alerts(self):
        # alerts = [Alert("test-alert", "test-query", 5, 10, "critical message")] Interviewer gave just this one, I added 2 more
        alerts = [Alert("high-latency", "latency_ms{service=foo}", 5, 100, "latency is too high"),
        Alert("p90-latency", "latency_ms{service=foo}", 1, 100, "latency is too high"),
        Alert("some_alert", "latency_ms{service=foo}", 2, 100, "latency is too high")]
        return alerts

        def notify(self, alertname, message):
        print("notifying alert", alertname, message)

        def resolve(self, alertname):
        print("resolving alert", alertname)

        def query(self, query):
        return 111  # this could change over time in real life


        def main():
        pass


        if __name__ == "__main__":
        main()
        The hint I received

        # t0 - value is 110 -> notify
        # t5 - value is 1234 -> notify
        # t10 - value is 45 -> resolve
        # t15 - value is 62 -> [do nothing]

        # compare val to alerts[0].threshold and may call notify or resolve

        Part II: Introduce Repeat Interval

Add support for repeat intervals, so that if an alert is continuously firing it will only re-notify after the repeat interval.

Part III: Introduce Warning Threshold

Incorporate using the warn threshold in the alerting engine - now an alert can go between states PASS <-> WARN <-> CRITICAL <-> PASS.
*/
public class Chronospher_AlertSystem {
public static void main(String []args) {

    }
}

