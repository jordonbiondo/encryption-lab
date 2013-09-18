package src;
import javax.crypto.*;
import java.io.*;
import java.util.*;
import java.security.*;

public class Decrypty {
    
    /**
     * Println wrapper
     */
    public static void pln(Object o) {
	System.out.println(o.toString());
    }
    
    public static void plnpanic(Object o) {
	pln(o);
    }
    
    public static void main(String[] args) {
	
	if (args.length != 4) {
	    pln("USAGE: encrypty <input file> <output file> <init vector file> <key file>");
	    System.exit(-1);
	}
	File outputFile = null;
	File inputFile = null;
	File ivFile = null;
	File keyFile = null;
	FileOutputStream fileWriter = null;
	FileInputStream fileReader = null;
	FileInputStream keyReader = null;
	FileInputStream ivReader = null;

	try { 
	    inputFile = new File(args[0]);
	    outputFile = new File(args[1]);
	    ivFile = new File(args[2]);
	    keyFile = new File(args[3]);
	    fileWriter = new FileOutputStream(outputFile);
	    fileReader = new FileInputStream(inputFile);
	    keyReader = new FileInputStream(keyFile);
	    ivReader = new FileInputStream(ivFile);
	} catch (Exception e) {
	    plnpanic(e);
	}

	try {
	    KeyGenerator kgen = KeyGenerator.getInstance("AES");
	    SecretKey key = kgen.generateKey();
	    
	    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    c.init(Cipher.ENCRYPT_MODE, key);
	    CipherOutputStream cipherWriter = new CipherOutputStream(fileWriter, c);
	    byte[] plainBytes = new byte[(int)inputFile.length()];
	    fileReader.read(plainBytes);
	    cipherWriter.write(plainBytes);
	    cipherWriter.close();
	    fileReader.close();
	    fileWriter.close();
	    
	} catch (Exception e) {
	    plnpanic(e);
	}
    }
}
