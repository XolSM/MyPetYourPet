package com.example.mypetyourpet.configuration;

//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import jakarta.annotation.PostConstruct;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.IOException;
//
//@Configuration
//public class FirebaseConfig {
//
//    @PostConstruct
//    public void init() throws IOException {
//        if (FirebaseApp.getApps().isEmpty()) {
//            var serviceAccount = new ClassPathResource("firebase-service-account.json");
//
//            FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).build();
//            FirebaseApp.initializeApp(options);
//            System.out.println("Firebase service account created");
//        }
//    }
//}
