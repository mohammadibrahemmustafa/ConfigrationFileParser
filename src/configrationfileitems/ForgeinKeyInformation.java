/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configrationfileitems;

/**
 *
 * @author Bcc
 */
public class ForgeinKeyInformation {
    // name of the table
    private String forgeinKeyTableName;
    // name of the column in the forgeinKeyTableName 
    private String columnBasedTableName;

    // name of the column in the table 
    private String forgeinKeyColumnName;

    @Override
    public String toString() {
        return "ForgeinKeyInformation:[" + "forgeinKeyTableName:" + forgeinKeyTableName + ",columnBasedTableName:" + columnBasedTableName + ",forgeinKeyColumnName:" + forgeinKeyColumnName + ']';
    }
    public String getForgeinKeyTableName() {
        return forgeinKeyTableName;
    }

    public void setForgeinKeyTableName(String forgeinKeyTableName) {
        this.forgeinKeyTableName = forgeinKeyTableName;
    }

    public String getColumnBasedTableName() {
        return columnBasedTableName;
    }

    public void setColumnBasedTableName(String columnBasedTableName) {
        this.columnBasedTableName = columnBasedTableName;
    }

    public String getForgeinKeyColumnName() {
        return forgeinKeyColumnName;
    }

    public void setForgeinKeyColumnName(String forgeinKeyColumnName) {
        this.forgeinKeyColumnName = forgeinKeyColumnName;
    }

    public ForgeinKeyInformation(String forgeinKeyTableName, String columnBasedTableName, String forgeinKeyColumnName) {
        this.forgeinKeyTableName = forgeinKeyTableName;
        this.columnBasedTableName = columnBasedTableName;
        this.forgeinKeyColumnName = forgeinKeyColumnName;
    }

}
