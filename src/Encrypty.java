package src;
import javax.crypto.*;
import java.io.*;
import java.util.*;
import java.security.*;

public class Encrypty {

    /**
     * Println wrappers
     */
    public static void pln(Object o) { System.out.println(o.toString());}
    public static void plnpanic(Object o) {pln(o.toString()); System.exit(-1);}


    public static void main(String[] args) {
	
	if (args.length != 4) {
	    pln("USAGE: encrypty <input file> <output file> <IV file> <key file>");
	    System.exit(-1);
	}

	File outputFile = null;
	File inputFile = null;
	File ivFile = null;
	File keyFile = null;
	FileOutputStream fileWriter = null;
	FileOutputStream ivWriter = null;
	FileOutputStream keyWriter = null;
	FileInputStream fileReader = null;

	try { 
	    inputFile = new File(args[0]);
	    outputFile = new File(args[1]);
	    ivFile = new File(args[2]);
	    keyFile = new File(args[3]);
	    fileWriter = new FileOutputStream(outputFile);
	    ivWriter = new FileOutputStream(ivFile);
	    keyWriter = new FileOutputStream(keyFile);
	    fileReader = new FileInputStream(inputFile);
	    
	} catch (Exception e) {
	    pln("Error during initialization");
	    plnpanic(e.getMessage());
	}

	try {
	    KeyGenerator kgen = KeyGenerator.getInstance("AES");
	    kgen.init(128);
	    SecretKey key = kgen.generateKey();
	    
	    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    c.init(Cipher.ENCRYPT_MODE, key);

	    byte[] some_bytes = new byte[fileReader.available()];
	    fileReader.read(some_bytes);
	    fileWriter.write(c.update(some_bytes));
	    fileWriter.write(c.doFinal());

	    ivWriter.write(c.getIV());
	    ivWriter.close();
	    keyWriter.write(key.getEncoded());
	    fileReader.close();
	    fileWriter.close();
	} catch (Exception e) {
	    pln("Error during writing");
	    plnpanic(e.getMessage());
	}
    }
}
