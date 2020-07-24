package com.example.omakhelper.aallHelpers;


import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

class AppFlowDatabaseRealmMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();

        if (newVersion == 0) {
            return;
        }

        if (oldVersion == 0) {

            oldVersion++;
        }
    }
}

