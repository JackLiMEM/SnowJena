package cn.yueshutong.comoon;

import com.github.onblog.commoon.entity.RateLimiterRule;
import com.github.onblog.commoon.enums.AcquireModel;
import com.github.onblog.commoon.enums.RuleAuthority;
import com.github.onblog.core.config.RateLimiterConfig;
import com.github.onblog.core.limiter.RateLimiter;
import com.github.onblog.core.limiter.RateLimiterFactory;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private Logger logger = LoggerFactory.getLogger(AppTest.class);

//    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        assertTrue(true);
        long f = 1;
        Thread.sleep(f / 10);
        long d = f / 10;
        System.out.println(d);
    }

//    @Test
    public void should() {
        RateLimiterRule rule = new RateLimiterRule();
        rule.setId("Default");
        rule.setAcquireModel(AcquireModel.BLOCKING);
        rule.setRuleAuthority(RuleAuthority.AUTHORITY_BLACK);

        String s = JSON.toJSONString(rule);
        System.out.println(s);
        Object parse = JSON.parseObject(s, RateLimiterRule.class);
        System.out.println(parse);
    }

//    @Test
    public void shoulds() {
        RateLimiterConfig config = RateLimiterConfig.getInstance();
        ScheduledFuture<?> scheduledFuture = config.getScheduledThreadExecutor().scheduleAtFixedRate(() -> {
            System.out.println("one" + LocalDateTime.now());
        }, 0, 1, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture1 = config.getScheduledThreadExecutor().scheduleAtFixedRate(() -> {
            System.out.println("two" + LocalDateTime.now());
        }, 0, 2, TimeUnit.SECONDS);
        try {
            Thread.sleep(1000 * 6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledFuture.cancel(true);
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void tess() throws InterruptedException {
        RateLimiterRule rule = new RateLimiterRule();
        rule.setAcquireModel(AcquireModel.BLOCKING);
        RateLimiter limiter = RateLimiterFactory.of(rule);
        Thread.sleep(1000);
        while (true) {
            if (limiter.tryAcquire()) {

            }
            sayHi();
        }
    }

    private void sayHi() {
        logger.info("hi");
    }
}
