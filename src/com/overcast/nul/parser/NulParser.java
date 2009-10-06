package com.overcast.nul.parser;

import com.overcast.nul.core.NulObject;
import com.overcast.nul.core.NulInteger;

import java.io.Reader;
import java.io.IOException;
import java.util.regex.Pattern;

public class NulParser {
    public final static Pattern REGEX_NUMBER = Pattern.compile("[0-9]+");
    public static final Pattern REGEX_WHITESPACES = Pattern.compile("\\s+");;
    private NulObject context = new NulObject("");

    public NulParser() {
    }

    public NulObject parse(Reader reader) throws IOException {
        StringBuffer tokenBuffer = new StringBuffer();
        String token = null;
        boolean done = false;
        NulObject current = context;
        while (!done) {
            int ch = reader.read();

            switch (ch) {
                case -1:
                    done = true;
                case ' ':
                    token = tokenBuffer.toString();
                    tokenBuffer = new StringBuffer();
                    NulObject object =  null;
                    if(REGEX_NUMBER.matcher(token).matches()) {
                        object = new NulInteger(token);
                    }
                    else{
                        object = new NulObject(token);
                    }
                    current = current.send(object);
                    break;
                default:
                    char character = (char) ch;
                    tokenBuffer.append(character);
            }
        }
        return context;
    }
}
