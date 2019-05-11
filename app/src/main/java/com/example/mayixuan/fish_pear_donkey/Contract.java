package com.example.mayixuan.fish_pear_donkey;

import android.content.Context;

/**
 * Created by mayixuan on 2018/3/31.
 */

public class Contract {
    private String contract_name;
    private String name;
    private String content;

    public Contract(String id,String name,String content){
        super();
        this.contract_name=id;
        this.name=name;
        this.content=content;
    }

    public String getId(){
        return  contract_name;
    }

    public void setId(String id){
        this.contract_name=id;

    }

    public String getName(){
        return  name;
    }

    public void setName(String name){
        this.name=name;

    }

    public String getContent(){
        return  content;
    }

    public void setContent(String content){
        this.contract_name=content;

    }

}

