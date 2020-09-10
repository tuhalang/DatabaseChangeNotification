/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuhalang.database;

import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.dcn.QueryChangeDescription;

/**
 *
 * @author tuhalang
 */
public class DCNDemoListener implements DatabaseChangeListener {

    DBChangeNotification demo;

    DCNDemoListener(DBChangeNotification dem) {
        demo = dem;
    }

    @Override
    public void onDatabaseChangeNotification(DatabaseChangeEvent dce) {
        Thread t = Thread.currentThread();
        System.out.println("DCNDemoListener: got an event (" + this + " running on thread " + t + ")");
        QueryChangeDescription[] qcds = dce.getQueryChangeDescription();
        for(QueryChangeDescription qcd : qcds){
            System.out.println(qcd.getTableChangeDescription()[0].getRowChangeDescription()[0].getRowOperation());
            System.out.println(qcd.getTableChangeDescription()[0].getRowChangeDescription()[0].getRowid().stringValue());
        }
        synchronized (demo) {
            demo.notify();
        }
    }

}
