package io.jms.sender.bean;

public class Counter {

    private static Counter self = new Counter();
    private static int count = 0;
    
    private Counter(){
    }

    /**
     * 
     * @return
     */
    public static Counter getInstance(){
        return self;
    }

    /**
     * 
     * @return
     */
    public int getCount(){
        synchronized (this) {
            return ++count;
        }
    }
}
