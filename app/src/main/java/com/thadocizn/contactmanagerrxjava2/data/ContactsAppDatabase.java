package com.thadocizn.contactmanagerrxjava2.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.thadocizn.contactmanagerrxjava2.model.Contact;

/**
 * Created by charles on 10,June,2019
 */
@Database(entities = {Contact.class}, version = 1)
public abstract class ContactsAppDatabase extends RoomDatabase {

    public abstract ContactDao getContactDAO();

}
