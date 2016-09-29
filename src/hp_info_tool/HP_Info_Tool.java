/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */    
package hp_info_tool;
//import com.healthmarketscience.jackcess.*;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ans
 */
public class HP_Info_Tool {
    List<File> getFiles(String directory, String fileType) {
        List<File> files = new ArrayList<>();
        File dir = new File(directory);
        for (File file : dir.listFiles()) {
            if (file.getName().toLowerCase().endsWith(("." + fileType))) {
                files.add(file);
            }
        }
        return files;
    }   
    
    List<String> getResults(File file, String queryVal) throws IOException{
        //http://jackcess.sourceforge.net/cookbook.html#Reading_a_Table
        Database db;
        List<String> resultArrayList = new ArrayList<>();
        //File file = new File("/Users/ans/Documents/tbl_6J_2G_BOM.mdb");
        db = DatabaseBuilder.open(file);
        for( String tabelName : db.getTableNames() ) {
            //System.out.println(tabelName + " >> Datensätze: " + db.getTable(tabelName).getRowCount());
            Table table = db.getTable(tabelName);
            /*for(Column col: db.getTable(tabelName).getColumns()){
                System.out.println(col.getName());
            }*/
            for(Row row : table) {
                //System.out.println("Look ma, a row: " + row);
                String curString = row.get("PRODUCT_NUMBER") + "\t\t" + row.get("SEGMENT") + "\t" + row.get("PRODUCT_PLATFORM")  + "\t\t" + row.get("PRODUCT_DESCRIPTION");
                String val = row.get("PRODUCT_NUMBER").toString();
                if(resultArrayList.isEmpty() ) {
                    resultArrayList.add("PRODUCT_NUMBER" + "\t" + "SEGMENT" + "\t" + "PRODUCT_PLATFORM"  + "\t" + "PRODUCT_DESCRIPTION");  
                }
                if((val.contains(queryVal) && !resultArrayList.contains(curString)) ){
                    //System.out.println(curString);
                    resultArrayList.add(curString);   
                }
            }
        }
        db.close();
        
        if (resultArrayList.size() == 1) resultArrayList.clear();
        return resultArrayList;
    }
}