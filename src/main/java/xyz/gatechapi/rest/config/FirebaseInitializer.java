package xyz.gatechapi.rest.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() throws IOException {
        FirebaseApp.initializeApp(
                new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .setDatabaseUrl(String.format("https://%s.firebaseio.com/", System.getenv("GOOGLE_CLOUD_PROJECT")))
                        .build()
        );
    }
}
