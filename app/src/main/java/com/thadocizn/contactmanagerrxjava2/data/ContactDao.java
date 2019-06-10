package com.thadocizn.contactmanagerrxjava2.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.thadocizn.contactmanagerrxjava2.model.Contact;

import java.util.List;

/**
 * Created by charles on 10,June,2019
 */
@Dao
public interface ContactDao {

    @Insert
    public  long addContact(Contact contact);

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);

    @Query("select * from contacts")
    public List<Contact> getContacts();

    @Query("select * from contacts where contact_id ==:contactId")
    public Contact getContact(long contactId);

}
