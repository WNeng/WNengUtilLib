package com.android.wneng.retrofit;

import java.io.Serializable;

/**
 * Created by wneng on 16/9/18.
 */

public class BaseModel<T> implements Serializable {


    public boolean error;

    public String msg;

    public T results;


    public boolean success(){
        return !error;
    }

}
