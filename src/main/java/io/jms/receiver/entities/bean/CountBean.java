package io.jms.receiver.entities.bean;

import java.io.Serializable;

public class CountBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int count;
    
    /**
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }
    
}
