# Logger Rate Limiter

## Problem Statement
**LeetCode Problem 359**: Logger Rate Limiter (Easy)

Design a logger system that receive stream of messages along with its timestamps. Each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

### Examples
```
Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true;

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;
  (last printed at timestamp 1, only 2 seconds passed)

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;
  (last printed at timestamp 2, only 6 seconds passed)

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;
  (last printed at timestamp 1, only 9 seconds passed)

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
  (last printed at timestamp 1, 10 seconds have passed)
```

## Key Insights
1. **Rate Limiting**: Each message has a cooldown period of 10 seconds
2. **HashMap Storage**: Store message -> last printed timestamp
3. **Time Check**: Current time - last printed time >= 10 seconds
4. **Update on Print**: Update timestamp when message is printed
5. **No Cleanup Needed**: Problem doesn't require removing old entries

## Algorithm Steps
```
1. Initialize HashMap to store message -> timestamp
2. For each shouldPrintMessage(timestamp, message):
   a. Check if message exists in map:
      - If yes, check if (timestamp - lastTimestamp) >= 10
      - If < 10, return false (too soon)
   b. If message not in map OR cooldown passed:
      - Update map with current timestamp
      - Return true
```

## Complexity Analysis
- **Time Complexity**: O(1) per call
  - HashMap get/put operations are O(1)
- **Space Complexity**: O(M)
  - M = number of unique messages
  - Map grows with unique messages
  - Note: No automatic cleanup in this implementation

## Visual Representation

### Example Timeline
```
Timeline (seconds):
0    1    2    3    4    5    6    7    8    9    10   11   12
|    |    |    |    |    |    |    |    |    |    |    |    |
     foo  bar  foo                   bar       foo  foo
     T    T    F                     F         F    T

Message: "foo"
Timestamps: 1 (print), 3 (skip), 10 (skip), 11 (print)
     1         3         10        11
     |---------|---------|---------|
     print     2 sec     9 sec     10 sec
               (skip)    (skip)    (print!)

Message: "bar"
Timestamps: 2 (print), 8 (skip)
     2         8
     |---------|
     print     6 sec (skip)
```

### HashMap State Changes
```
Initial: {}

Call 1: shouldPrintMessage(1, "foo")
  map = {}
  "foo" not in map -> print, update
  map = {"foo": 1}
  return true

Call 2: shouldPrintMessage(2, "bar")
  map = {"foo": 1}
  "bar" not in map -> print, update
  map = {"foo": 1, "bar": 2}
  return true

Call 3: shouldPrintMessage(3, "foo")
  map = {"foo": 1, "bar": 2}
  "foo" in map, last timestamp = 1
  3 - 1 = 2 < 10 -> too soon
  return false
  map unchanged

Call 4: shouldPrintMessage(8, "bar")
  map = {"foo": 1, "bar": 2}
  "bar" in map, last timestamp = 2
  8 - 2 = 6 < 10 -> too soon
  return false
  map unchanged

Call 5: shouldPrintMessage(10, "foo")
  map = {"foo": 1, "bar": 2}
  "foo" in map, last timestamp = 1
  10 - 1 = 9 < 10 -> still too soon!
  return false
  map unchanged

Call 6: shouldPrintMessage(11, "foo")
  map = {"foo": 1, "bar": 2}
  "foo" in map, last timestamp = 1
  11 - 1 = 10 >= 10 -> cooldown passed!
  update timestamp
  map = {"foo": 11, "bar": 2}
  return true
```

## Code Walkthrough

### Current Implementation
```java
class Logger {
    Map<String, Integer> map;

    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp,
     * otherwise returns false. If this method returns false, the message will
     * not be printed. The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        // Check if message was printed before
        if (map.containsKey(message)) {
            int ts = map.get(message);

            // Check if cooldown period (10 seconds) has passed
            if (timestamp - ts < 10)
                return false;  // Too soon, don't print
        }

        // Either first time or cooldown passed
        // Update timestamp and allow printing
        map.put(message, timestamp);
        return true;
    }
}
```

### Alternative: Simplified Version
```java
class Logger {
    private Map<String, Integer> lastPrintTime;

    public Logger() {
        lastPrintTime = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        // Get last print time, default to -10 if not found
        // (ensures first message always prints)
        int lastTime = lastPrintTime.getOrDefault(message, timestamp - 10);

        if (timestamp - lastTime >= 10) {
            lastPrintTime.put(message, timestamp);
            return true;
        }

        return false;
    }
}
```

## Edge Cases
1. **First message**: Always print (no previous timestamp)
2. **Exactly 10 seconds**: Should print (>= 10)
3. **Multiple messages**: Each tracked independently
4. **Same message, same timestamp**: Follow cooldown rule
5. **Timestamps out of order**: Implementation assumes increasing timestamps
6. **Very large timestamps**: Integer overflow not an issue for differences

### Edge Case Examples
```
1. First occurrence:
   shouldPrintMessage(1, "hello")
   Output: true

2. Exactly 10 seconds:
   shouldPrintMessage(1, "test")  -> true
   shouldPrintMessage(11, "test") -> true (11 - 1 = 10)

3. Multiple independent messages:
   shouldPrintMessage(1, "a")  -> true
   shouldPrintMessage(2, "b")  -> true
   shouldPrintMessage(3, "c")  -> true
   (all tracked separately)

4. Boundary check:
   shouldPrintMessage(0, "x")  -> true
   shouldPrintMessage(9, "x")  -> false (9 - 0 = 9 < 10)
   shouldPrintMessage(10, "x") -> true (10 - 0 = 10)

5. Timestamp 0:
   shouldPrintMessage(0, "start") -> true
   Valid starting point
```

## Optimization: Memory Cleanup

The current implementation never removes old entries. For long-running systems, we might want cleanup:

```java
class Logger {
    private Map<String, Integer> map;
    private Queue<Pair<String, Integer>> queue;  // Track insertion order

    public Logger() {
        map = new HashMap<>();
        queue = new LinkedList<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        // Clean up old messages (optional optimization)
        while (!queue.isEmpty()) {
            Pair<String, Integer> pair = queue.peek();
            if (timestamp - pair.getValue() >= 10) {
                queue.poll();
                // Only remove if timestamp hasn't been updated
                if (map.get(pair.getKey()) == pair.getValue()) {
                    map.remove(pair.getKey());
                }
            } else {
                break;
            }
        }

        // Check if message can be printed
        if (map.containsKey(message)) {
            int ts = map.get(message);
            if (timestamp - ts < 10)
                return false;
        }

        map.put(message, timestamp);
        queue.offer(new Pair<>(message, timestamp));
        return true;
    }
}
```

## Design Considerations

### When to use this pattern:
- Rate limiting API requests
- Throttling user actions
- Preventing spam
- Debouncing events

### Real-world applications:
- Login attempt limiting
- Email sending throttling
- Search query rate limiting
- API call quotas

## Related Problems
- **Design Hit Counter (LeetCode 362)**: Count hits in time window
- **Logger Rate Limiter II**: Different time windows per message type
- **Design Rate Limiter**: More sophisticated rate limiting
- **Time Based Key-Value Store (LeetCode 981)**: Similar timestamp tracking
- **LRU Cache (LeetCode 146)**: Another design problem with HashMap

## Tags
- Design
- Hash Table
- HashMap
- Rate Limiting
- System Design
- Easy
- Amazon Interview
- Google Interview
