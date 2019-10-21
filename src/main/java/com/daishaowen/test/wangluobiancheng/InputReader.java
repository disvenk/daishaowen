package com.daishaowen.test.wangluobiancheng;

import java.io.BufferedReader;
import java.io.IOException;

public class InputReader extends Thread {
    BufferedReader br;
    String type;
    boolean isRun = true;

    public void setRun(boolean b){
        this.isRun = b;
    }

    InputReader(BufferedReader br, String type){
        this.br = br;
        this.type = type;
    }

    public void run() {
        try {
            while(isRun){
                String msg = br.readLine();
                System.out.println(this.type +" : "+ msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
