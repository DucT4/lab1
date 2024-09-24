/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageFile;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.io.FileWriter;
/**
 *
 * @author ADMIN
 */
public class ManageFile {
    public List<String> load (String fileName)throws Exception{
          File file = new File(fileName);
          if (file.exists()) {
               List<String> allText = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
                 return allText;
          }
          
        return null;
        
    }
    public void save(String fileName, List<String> data) throws Exception{
        File file = new File(fileName);
        if (file.exists()) {
            FileWriter fw = new FileWriter(file);
            for (String line : data) {
                fw.write(line); 
                fw.write("\n");
            }
            fw.close();
        }
    }
   
}
