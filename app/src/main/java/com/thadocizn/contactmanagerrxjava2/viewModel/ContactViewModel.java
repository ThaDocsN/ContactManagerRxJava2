package com.thadocizn.contactmanagerrxjava2.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.thadocizn.contactmanagerrxjava2.model.Contact;
import com.thadocizn.contactmanagerrxjava2.repostitories.ContactRepository;

import java.util.List;

/**
 * Created by charles on 11,June,2019
 */
public class ContactViewModel extends AndroidViewModel {

    private ContactRepository repo;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repo = new ContactRepository(application);
    }

    public LiveData<List<Contact>> getAllContacts(){
        return repo.getLiveData();
    }

    public void createContact(String name, String email){

        repo.createContact(name, email);
    }

    public void updateContact(Contact contact){

        repo.updateContact(contact);
    }

    public void deleteContact(Contact contact){
        repo.deleteContact(contact);
    }

    public void clear(){
        repo.clear();
    }
}
