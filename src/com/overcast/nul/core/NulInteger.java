package com.overcast.nul.core;

public class NulInteger extends NulObject {
    private int value;

    public NulInteger(String token) {
        super(token);
        value = Integer.parseInt(token);
        defineReceiver("+", new NulBlock(){
            public NulObject invoke(NulObject context, NulObject data) {
                return new NulInteger(Integer.toString(((NulInteger)context).value + ((NulInteger)data).value));
            }
        });
    }

    public NulObject evaluate() {
        return receivers.get(next.getToken()).invoke(this, next.nextObject());
    }
}
