/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configrationfileparser;

import sender.JSONSender;
import configrationfileitems.Table;
import configrationfileitems.ForgeinKeyInformation;
import configrationfileitems.Field;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import javafx.print.Collation;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import jdk.nashorn.internal.scripts.JS;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Bcc
 */
public class ConfigrationFileParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            File fXmlFile = new File("F://Config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            
            JSONObject sendingJSON=new JSONObject();
            // the queue 
            ArrayList<Table> tables=new ArrayList<>();
            
            NodeList database=doc.getElementsByTagName("database");
            
            NodeList nodeList = doc.getElementsByTagName("tables");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println(nodeList.item(i).getNodeName());
                NodeList tablesListNode = node.getChildNodes();
                for (int j = 1; j < tablesListNode.getLength(); j += 2) {
                    //table
                    Node tableNode = tablesListNode.item(j);
                    System.out.println("\t" + tableNode.getNodeName());
                    String tableName=tableNode.getNodeName();
                    NodeList fieldsListNode = tableNode.getChildNodes();
                    ArrayList<Field> fields=new ArrayList<Field>();
                    ArrayList<ForgeinKeyInformation> forgeinKeys=new ArrayList<ForgeinKeyInformation>();
                    for (int k = 1; k < fieldsListNode.getLength(); k += 2) {
                        Node field = fieldsListNode.item(k);
                        String fieldName = field.getNodeName();
                        System.out.println("\t\t" + fieldName);
                        if ("fields".equals(fieldName)) {
                            NodeList columnsListNode = field.getChildNodes();
                            for (int l = 1; l < columnsListNode.getLength(); l += 2) {
                                Node column = columnsListNode.item(l);
                                if (column.getNodeType() == Node.ELEMENT_NODE) {
                                    Element element = (Element) column;
                                    fields.add(new Field(element.getNodeName(), element.getAttribute("type")));
                                }
                            }
                        } else if ("forgeinkeys".equals(fieldName)) {
                            NodeList forgeinKeyListNode = field.getChildNodes();
                            for (int l = 1; l < forgeinKeyListNode.getLength(); l += 2) {
                                Node forgeinKey = forgeinKeyListNode.item(l);
                                NodeList forgeinKeyTables=forgeinKey.getChildNodes();
                                for (int m = 1; m < forgeinKeyListNode.getLength(); m+=2) {
                                    String forgeinKeyTableName=forgeinKeyListNode.item(m).getNodeName();
                                    NodeList forgeinKeyTable=forgeinKeyListNode.item(m).getChildNodes();
                                    String columnBasedTableName=forgeinKeyTable.item(1).getNodeName();
                                    String forgeinKeyColumnName=forgeinKeyTable.item(3).getNodeName();
                                    forgeinKeys.add(new ForgeinKeyInformation(forgeinKeyTableName, columnBasedTableName, forgeinKeyColumnName));
                                }
                            }
                        }
                    }
                    tables.add(new Table(tableName, fields, forgeinKeys));
                }
            }
            System.out.println("******************");
            
            System.out.println("*********start json object*********");
            JSONArray tableArray=new JSONArray();          
            for (Table table : tables) {
                
            JSONArray tableJSON=new JSONArray();
                JSONObject tableName=new JSONObject();
                tableName.put("name", table.getTableName());
                JSONArray fieldsJSON=new JSONArray();
                ArrayList<Field> fields=table.getFields();
                for (Field field : fields) {
                    JSONObject fieldName=new JSONObject();
                    fieldName.put("fieldName",field.getFieldName());
                    JSONObject fieldType=new JSONObject();
                    fieldType.put("fieldType",field.getFieldType());
                    fieldsJSON.put(fieldName);
                    fieldsJSON.put(fieldType);
                }
                JSONArray forgeinKeyJSON=new JSONArray();
                ArrayList<ForgeinKeyInformation> forgeinKeys=table.getForgeinKeys();
                for (ForgeinKeyInformation  forgeinKey: forgeinKeys) {
                    JSONObject columnBasedTableName=new JSONObject();
                    columnBasedTableName.put("columnBasedTableName",forgeinKey.getColumnBasedTableName());
                    JSONObject forgeinKeyTableName=new JSONObject();
                    forgeinKeyTableName.put("forgeinKeyTableName",forgeinKey.getForgeinKeyTableName());
                    JSONObject forgeinKeyColumnName=new JSONObject();
                    forgeinKeyColumnName.put("forgeinKeyColumnName",forgeinKey.getForgeinKeyColumnName());
                    forgeinKeyJSON.put(columnBasedTableName);
                    forgeinKeyJSON.put(forgeinKeyTableName);
                    forgeinKeyJSON.put(forgeinKeyColumnName);
                }
                tableJSON.put(tableName);
                tableJSON.put(fieldsJSON);
                tableJSON.put(forgeinKeyJSON);
                tableArray.put(tableJSON);
            }
                
            
            JSONObject tablesJSON=new JSONObject();
            //get database info
            tablesJSON.put("dbName", doc.getDocumentElement().getAttribute("name"));
            tablesJSON.put("dbPassword", doc.getDocumentElement().getAttribute("password"));
            tablesJSON.put("tables", tableArray);
            System.out.println(tablesJSON.toString());
            JSONSender sender=new JSONSender();
            sender.parseConfigFileJSON(tablesJSON.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
