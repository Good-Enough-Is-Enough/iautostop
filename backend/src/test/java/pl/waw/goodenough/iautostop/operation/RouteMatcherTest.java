package pl.waw.goodenough.iautostop.operation;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RouteMatcherTest {

    @Test
    public void matcherTest1True() {
        List<String> route = Arrays.asList("1","2","3","4","5","6");
        RouteMatcher routeMatcher = new RouteMatcher(route, "2", "5");
        Assertions.assertThat(routeMatcher.match()).isTrue();
    }

    @Test
    public void matcherTest2True() {
        List<String> route = Arrays.asList("1","2","3","4","5","6");
        RouteMatcher routeMatcher = new RouteMatcher(route, "2", "6");
        Assertions.assertThat(routeMatcher.match()).isTrue();
    }

    @Test
    public void matcherTest3True() {
        List<String> route = Arrays.asList("1","2","3","4","5","6");
        RouteMatcher routeMatcher = new RouteMatcher(route, "1", "1");
        Assertions.assertThat(routeMatcher.match()).isTrue();
    }

    @Test
    public void matcherTest1False() {
        List<String> route = Arrays.asList("1","2","3","4","5","6");
        RouteMatcher routeMatcher = new RouteMatcher(route, "6", "2");
        Assertions.assertThat(routeMatcher.match()).isFalse();
    }

    @Test
    public void matcherTest2False() {
        List<String> route = Arrays.asList("1","2","3","4","5","6");
        RouteMatcher routeMatcher = new RouteMatcher(route, "3", "14");
        Assertions.assertThat(routeMatcher.match()).isFalse();
    }

    @Test
    public void matcherTest3False() {
        List<String> route = Arrays.asList("1","2","3","4","5","6");
        RouteMatcher routeMatcher = new RouteMatcher(route, "14", "4");
        Assertions.assertThat(routeMatcher.match()).isFalse();
    }


}