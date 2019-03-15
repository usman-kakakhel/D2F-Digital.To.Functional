/**
 * @author Mian Usman Naeem Kakakhel
 * last edit: 10 May 2018
 */
package application;

import java.io.*;
import java.util.HashMap;

public class FileStorage {
    private File storageFile;
    private D2F filew;

    //constructor
    public FileStorage(File file, D2F a) throws IOException, IllegalArgumentException {
        this.storageFile = file;
        this.filew = a;

//
//        if (storageFile.isDirectory()) {
//            throw new IllegalArgumentException("FileStorage file must not be a directory");
//        }

        //if (storageFile.createNewFile()) {
        //   save();
        //} else {
        // load();
        //}
    }

    /**
     * method to save a .d2f file
     * @throws IOException exception
     */
    public void save() throws IOException {
        ObjectOutputStream oos;
        if (!storageFile.exists())
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(storageFile + ".d2f")));
        else
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(storageFile)));

        oos.writeObject(filew);
        oos.flush();
        oos.close();
    }

    /**
     * method to load a saved file
     * @return the loaded file
     */
    public D2F load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(storageFile)));
            filew = (D2F) ois.readObject();
            ois.close();
            return filew;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
