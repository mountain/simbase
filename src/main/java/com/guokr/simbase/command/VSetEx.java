package com.guokr.simbase.command;

import com.guokr.simbase.SimCallback;
import com.guokr.simbase.SimCommand;
import com.guokr.simbase.SimEngine;

public class VSetEx extends SimCommand {

    @Override
    public String signature() {
        return "sllF";
    }

    @Override
    public void invoke(SimEngine engine, String vkey, long vecid, long ttl, float[] distr, SimCallback callback) {
        engine.vsetex(callback, vkey, vecid, ttl, distr);
    }

}
