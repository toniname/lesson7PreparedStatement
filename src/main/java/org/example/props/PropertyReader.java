package org.example.props;
import java.util.Collections;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final Logger logger = Logger.getLogger(PropertyReader.class.getName());

    private static Properties loadProperties() {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                logger.info("Error loading properties file");
                return (Properties) Collections.emptyMap();
            }

            prop.load(input);
            return prop;
        } catch (IOException ex) {
            logger.severe("Error loading properties: " + ex.getMessage());
            return (Properties) Collections.emptyList();
        }
    }

    public static String getConnectionUrlForMysql() {
        Properties prop = loadProperties();

        if (prop != null) {
            return "jdbc:mysql://" +
                    prop.getProperty("mysql.db.host") +
                    ":" +
                    prop.getProperty("mysql.db.port") +
                    "/" +
                    prop.getProperty("mysql.db.database") +
                    "?allowPublicKeyRetrieval=true&useSSL=false";
        }
        return null;
    }

    public static String getUserForMysql() {
        Properties prop = loadProperties();

        if (prop != null) {
            return prop.getProperty("mysql.db.username");
        }
        return null;
    }
}
