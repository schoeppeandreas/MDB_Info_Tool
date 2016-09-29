/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hp_info_tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Properties;


/**
 *
 * @author ans
 */
public class MyProperties {
    Properties prop = new Properties();
    
    public MyProperties() {
        this(Paths.get(".").toAbsolutePath().normalize().toString());
    }
    
    public MyProperties (String path) {
        String filename = "config.properties";
        String filePath = path + File.separatorChar + filename;
        File f = new File(filePath);
        InputStream input = null;
        
        
        try {
            if(!f.exists() && !f.isDirectory()) { 
                //System.out.println("Sorry, unable to find " + filename);
                OutputStream output = new FileOutputStream(filePath);
                prop.setProperty("mdb_path", path + File.separatorChar);
                prop.store(output, null);
            } else {
                input = new FileInputStream(filePath);
                // load a properties file
                prop.load(input);
                // get the property value and print it out
                //System.out.println(prop.getProperty("mdb_path"));
            }
	} catch (IOException ex) {
            ex.printStackTrace();
	} finally {
            if (input != null) {
            	try {
                    input.close();
		} catch (IOException e) {
                    e.printStackTrace();
                    }
		}
	}
    }
    public String getMDB_Path () {
        return prop.getProperty("mdb_path");
    }
    
}



	
