package com.overcast.nul.parser;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import com.overcast.nul.core.NulObject;

import java.io.StringReader;
import java.io.IOException;

public class NulParserTest {
    @Test
    public void shouldBuildAnObjectChainFromASimpleExpression() throws IOException {
        NulObject context = new NulParser().parse(new StringReader("1 + 2"));
        NulObject tokenOne = context.nextObject();
        NulObject tokenTwo = tokenOne.nextObject();
        NulObject tokenThree = tokenTwo.nextObject();
        assertThat(tokenOne.getToken(), is(equalTo("1")));
        assertThat(tokenTwo.getToken(), is(equalTo("+")));
        assertThat(tokenThree.getToken(), is(equalTo("2")));
    }

    @Test
    public void regexpNumberMatchesNumbers(){
        assertThat(NulParser.REGEX_NUMBER.matcher("0").matches(), is(equalTo(true)));
        assertThat(NulParser.REGEX_NUMBER.matcher("123").matches(), is(equalTo(true)));

        assertThat(NulParser.REGEX_NUMBER.matcher("a123").matches(), is(equalTo(false)));
        assertThat(NulParser.REGEX_NUMBER.matcher("abcd").matches(), is(equalTo(false)));
    }

    @Test
    public void regexpNumberMatchesWhitespaces(){
        assertThat(NulParser.REGEX_WHITESPACES.matcher(" ").matches(), is(equalTo(true)));
        assertThat(NulParser.REGEX_WHITESPACES.matcher("  ").matches(), is(equalTo(true)));
        assertThat(NulParser.REGEX_WHITESPACES.matcher("   ").matches(), is(equalTo(true)));

        assertThat(NulParser.REGEX_WHITESPACES.matcher(" a123").matches(), is(equalTo(false)));
        assertThat(NulParser.REGEX_WHITESPACES.matcher("a ").matches(), is(equalTo(false)));
        assertThat(NulParser.REGEX_WHITESPACES.matcher("a a").matches(), is(equalTo(false)));
    }
}
