package com.thadocizn.contactmanagerrxjava2.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.thadocizn.contactmanagerrxjava2.model.Contact;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by charles on 10,June,2019
 */
@Dao
public interface ContactDao {

    @Insert
     long addContact(Contact contact);

    @Update
     void updateContact(Contact contact);

    @Delete
     void deleteContact(Contact contact);

    @Query("select * from contacts")
     Flowable<List<Contact>> getContacts();

    @Query("select * from contacts where contact_id ==:contactId")
     Contact getContact(long contactId);

}
