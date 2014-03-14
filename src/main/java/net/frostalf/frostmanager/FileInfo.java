
package net.frostalf.frostmanager;

import java.io.File;

/**
 *
 * @author Frostalf
 */
public class FileInfo {

    private String name;
    private File file;
    public FileInfo(File file, String name){
        this.file = file;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public File getFile(){
        return this.file;
    }
}
