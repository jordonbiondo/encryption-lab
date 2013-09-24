package src;
import javax.crypto.*;
import javax.crypto.spec.*;
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
	    pln("USAGE: encrypty <input file> <output file> <IV file> <key file>");
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
	    for (File f : new File[]{inputFile, ivFile, keyFile}) {
		if (!f.exists()) {
		    plnpanic(f.toString() + " does not exists");
		}
	    }
	} catch (Exception e) {
	    pln("Error during initialization");
	    plnpanic(e);
	}

	try {
	    
	    byte[] keyBytes = new byte[(int)keyFile.length()];
	    keyReader.read(keyBytes);

	    SecretKey key = new SecretKeySpec(keyBytes, "AES");
	    
	    byte[] ivBytes = new byte[(int)ivFile.length()];
	    ivReader.read(ivBytes);

	    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    c.init(Cipher.DECRYPT_MODE, key, ivSpec);
	    
	    byte[] plainBytes = new byte[(int)inputFile.length()];
	    fileReader.read(plainBytes);
	    fileWriter.write(c.update(plainBytes));
	    fileWriter.write(c.doFinal());

	    fileReader.close();
	    fileWriter.close();
	    
	} catch (Exception e) {
	    plnpanic(e);
	}
    }
}
