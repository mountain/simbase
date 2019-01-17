package com.guokr.simbase.store;

import com.guokr.simbase.events.VectorSetListener;

public interface VectorSet {

    public String type();

    public String key();

    public int size();

    public void clean();

    public long[] ids();

    public long[] expired();

    public boolean contains(long vecid);

    public void remove(long vecid);

    public float[] get(long vecid);

    public void expireAt(long vecid, long expireTime);

    public void expireAll();

    public void add(long vecid, float[] vector);

    public void set(long vecid, float[] vector);

    public void accumulate(long vecid, float[] vector);

    public int[] _get(long vecid);

    public int length(long vecid);

    void _add(long vecid, int[] pairs);

    void _set(long vecid, int[] pairs);

    void _accumulate(long vecid, int[] pairs);

    public void startListening();

    public void stopListening();

    public void addListener(VectorSetListener listener);

    public void rescore(String key, long vecid, float[] vector, Recommendation rec);

    public void rescore(String key, long vecid, int[] vector, Recommendation rec);
}
