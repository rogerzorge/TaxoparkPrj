package com.epam.taxopark.userexceptions;

/**
 * Created by Yahor_Faliazhynski on 11/20/2015.
 */
public class CustomArrayIndexOutofBoundsException extends ArrayIndexOutOfBoundsException {

    public CustomArrayIndexOutofBoundsException() {
    }

    public CustomArrayIndexOutofBoundsException(int index) {
        super(index);
    }

    public CustomArrayIndexOutofBoundsException(String s) {
        super(s);
    }

}
