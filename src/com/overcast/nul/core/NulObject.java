package com.overcast.nul.core;

import java.util.Map;
import java.util.HashMap;

public class NulObject {
    protected String token = null;
    protected NulObject next;
    protected Map<String, NulBlock> receivers = new HashMap<String, NulBlock>();

    public NulObject nextObject() {
        return next;
    }

    public String getToken() {
        return token;
    }

    public NulObject(String token) {
        this.token = token;
    }

    public NulObject send(NulObject nulObject) {
        return next = nulObject;
    }

    public NulObject evaluate() {
        return next.evaluate();
    }

    public void defineReceiver(String signature, NulBlock block){
        receivers.put(signature, block);
    }
}
