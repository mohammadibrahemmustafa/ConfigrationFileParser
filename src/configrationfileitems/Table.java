/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configrationfileitems;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Bcc
 */
public class Table {
    private String tableName;
    //the key is 
    private ArrayList<Field> fields=new ArrayList<>();
    private ArrayList<ForgeinKeyInformation> forgeinKeys=new ArrayList<>();

    @Override
    public String toString() {
        return "Table[" + "tableName:" + tableName + ",fields:" + fields + ",forgeinKeys:" + forgeinKeys + ']';
    }

    public Table(String tableName, ArrayList<Field> fields, ArrayList<ForgeinKeyInformation> forgeinKeys) {
        this.tableName = tableName;
        this.fields = fields;
        this.forgeinKeys = forgeinKeys;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<ForgeinKeyInformation> getForgeinKeys() {
        return forgeinKeys;
    }

    public void setForgeinKeys(ArrayList<ForgeinKeyInformation> forgeinKeys) {
        this.forgeinKeys = forgeinKeys;
    }
    

}
