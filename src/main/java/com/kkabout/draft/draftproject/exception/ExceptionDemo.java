package com.kkabout.draft.draftproject.exception;

/**
 * @author kangjian03@58.com
 * @date 2020/3/16
 **/
public class ExceptionDemo {
    public static void main(String[] args) {

    }

    public void testChecked() {
        try {
            checked(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUnChecked(int i) {
        unchecked(2);
    }



    public boolean unchecked(int integer) throws RuntimeException {
        return true;
    }



    public boolean checked(int integer) throws Exception {
        return true;
    }
}
