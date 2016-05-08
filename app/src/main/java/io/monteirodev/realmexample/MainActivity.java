package io.monteirodev.realmexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;
import java.util.UUID;

import io.monteirodev.realmexample.models.Task;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Configure Realm for the application
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("examples.realm") // name of the file on filesystem
                .build();
        //Realm.deleteRealm(realmConfiguration); // Deletes the realm,
        // use when you want a clean slate.

        // Make this Realm the default
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getDefaultInstance();

        // create some tasks
        // all realm writes need to be done on realm transaction
        realm.beginTransaction();
        Task t = realm.createObject(Task.class);
        t.setId(UUID.randomUUID().toString());
        // change to Goodbye for query
        // t.setTitle("Hello " + new Date().getTime());
        t.setTitle("Goodbye " + new Date().getTime());
        t.setDescription("This is a description");
        realm.commitTransaction();

        // Get data from realm and show log
        // RealmResults<Task> tasks = realm.allObjects(Task.class);

        // Get with where clause
        RealmResults<Task> tasks = realm.allObjects(Task.class)
                .where()
                .contains("title","Goodbye")
                .findAll();
        for (Task task : tasks) {
            Log.d("Realm", String.format("ID: %s, Title: %s, Desc: %s",
                    task.getId(), task.getTitle(), task.getDescription()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
