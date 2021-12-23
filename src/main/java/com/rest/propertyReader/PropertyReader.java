package com.rest.propertyReader;

import com.rest.resource.ResourceHelper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public
class PropertyReader implements ConfigReader {

    public static  Properties      prop;
    private static FileInputStream file;


    public
    PropertyReader ( ) {

        try {

            // For WebService
            String filePathWebService = ResourceHelper.getResourcePath ( "/src/main/resources/configFiles/config.properties" );
            file = new FileInputStream ( new File ( filePathWebService ) );

            prop = new Properties ( );
            prop.load ( file );
            }
        catch (Exception e) {
            e.printStackTrace ( );
        }
    }

    public
    String getKey ( ) {
        return prop.getProperty ( "API_KEY" );
    }

}
