package com.spellshocked.game.util;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class FireScoreSender {
    private final String TOKEN = "";
    private Firestore firestoreDB;

    public FireScoreSender() {
        try {
            InputStream serviceAccount = new FileInputStream("C:\\folder-name\\token-name.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
//            GoogleCredentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(TOKEN.getBytes()));
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            this.firestoreDB = FirestoreClient.getFirestore();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void testExampleData(){
        try {
            ApiFuture<QuerySnapshot> query = this.firestoreDB.collection("prototype-board").get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                System.out.println("ID: " + document.getId());
                System.out.println("player_id: " + document.getLong("player_id"));
                System.out.println("player_name: " + document.getString("player_name"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
