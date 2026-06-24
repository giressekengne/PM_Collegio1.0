package it.collegio.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static final int committenteId;

    static {
        Properties props = new Properties();
        try (InputStream in = AppConfig.class.getResourceAsStream("/it/collegio/config/config.properties")) {
            if (in == null) throw new RuntimeException("config.properties non trovato nel classpath");
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Errore caricamento config.properties", e);
        }
        String raw = props.getProperty("config.Committente", "1").trim();
        committenteId = Integer.parseInt(raw);
    }

    private AppConfig() {}

    public static int getCommittenteId() {
        return committenteId;
    }
}
