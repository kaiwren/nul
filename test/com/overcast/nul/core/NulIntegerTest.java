package com.overcast.nul.core;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import com.overcast.nul.parser.NulParser;

import java.io.StringReader;
import java.io.IOException;

public class NulIntegerTest {
    @Test
    public void shouldBeAbleToEvaluateASimpleExpression() throws IOException {
        NulObject context = new NulParser().parse(new StringReader("1 + 2"));
        assertThat(context.evaluate().getToken(), is(equalTo("3")));
    }
}
