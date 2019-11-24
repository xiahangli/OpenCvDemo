package com.example.common.dynamicproxy;

/**
 * @User Xiahangli
 * @Date 2019-11-24  16:40
 * @Email henryatxia@gmail.com
 * @Descrip
 */
public class OperationImpl implements IOperation {


    @Override
    public int add(int a, int b) {
//        for (int i = 0; i < 1000; i++) {//delay
//
//        }
        return a+b;
    }
}
