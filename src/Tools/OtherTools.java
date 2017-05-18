/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class OtherTools {

    private static Matcher matcher;
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String EMAIL_TEST="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
           
    private static Pattern pattern = Pattern.compile(EMAIL_TEST);
    
public static final String DATABASE_COUNTRY_PATH = "./GeoLite2-Country.mmdb";

public static final String DATABASE_CITY_PATH = "./GeoLite2-City.mmdb";
public static final int MY_USER_ID = 42;
public static final String MY_LICENSE_KEY = "license_key";

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    public static void ConditionText(TextField textField, int length) 
    {
    textField.setOnKeyTyped(event -> {
        String string = textField.getText();

        if (string.length() > length) 
        {
            textField.setText(string.substring(0, length));
            textField.positionCaret(string.length());
            
        }
        else 
        {
            if (textField.getText().length()!=length) 
            {
                textField.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
           //     arg0.consume();
            } 
            else 
            {
                textField.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
            }
        
        }
    });
   }
    public static void ConditionText2(TextField textField) 
    {
        textField.setOnKeyTyped(event -> {
        String string = textField.getText();
        if (textField.getText().length()<1) 
            {
                textField.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
           //     arg0.consume();
            } 
            else 
            {
                textField.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
            }
        });
    }
   
    public static void setTextLimit(TextField textField, int length) {
        textField.setOnKeyTyped(event -> {
            String string = textField.getText();

            if (string.length() > length) {
                textField.setText(string.substring(0, length));
                textField.positionCaret(string.length());
            }
        });
    }

    public static boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
    public static InetAddress getIP() throws MalformedURLException, IOException
    {
    URL url = new URL("http://checkip.amazonaws.com/");
    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream())); 
    return InetAddress.getByName(br.readLine());
    }
}
