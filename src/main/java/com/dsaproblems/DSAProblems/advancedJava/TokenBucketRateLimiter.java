package com.dsaproblems.DSAProblems.advancedJava;

public class TokenBucketRateLimiter {

    private final int maxTokens;
    private final double refillRatePerSec;
    private double tokens;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter(int maxTokens, double refillRatePerSec) {
        this.maxTokens = maxTokens;
        this.refillRatePerSec = refillRatePerSec;
        this.tokens = maxTokens;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    //checks if a request can be allowed based on the current number of tokens in the bucket. It first refills tokens according to the elapsed time, then:
    //If at least one token is available, it consumes one token and returns true (request allowed).
    //If no tokens are available, it returns false (request denied)
    public synchronized boolean allowRequest() {
        refill();
        if (tokens >= 1) {
            tokens -= 1;
            return true;
        }
        return false;
    }

    //The refill() method calculates how many tokens should be added to the
    // bucket based on the time elapsed since the last refill. It then updates
    // the token count, ensuring it does not exceed the maximum (maxTokens).
    // Finally, it updates the timestamp for the last refill. This keeps the
    // rate limiter accurate over time.
    private void refill() {
        long now = System.currentTimeMillis();
        double tokensToAdd = ((now - lastRefillTimestamp) / 1000.0) * refillRatePerSec;
        if (tokensToAdd > 0) {
            tokens = Math.min(maxTokens, tokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(5, 2); // 5 tokens max, 2 tokens/sec

        for (int i = 1; i <= 20; i++) {
            if (rateLimiter.allowRequest()) {
                System.out.println("Request " + i + " allowed at " + System.currentTimeMillis());
            } else {
                System.out.println("Request " + i + " denied at " + System.currentTimeMillis());
            }
            Thread.sleep(300); // simulate time between requests
        }
    }
}
