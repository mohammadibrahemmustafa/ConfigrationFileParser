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
public class Field {
    // name of the column
    private String fieldName;
    
    // type of the column
    private String fieldType;
    @Override
    public String toString() {
        return "Filed:[" + "fieldName:" + fieldName + ",fieldType:" + fieldType + ']';
    }
    
    public Field(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
