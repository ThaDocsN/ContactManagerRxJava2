package com.thadocizn.contactmanagerrxjava2.repostitories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.widget.Toast;

import com.thadocizn.contactmanagerrxjava2.data.ContactsAppDatabase;
import com.thadocizn.contactmanagerrxjava2.model.Contact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by charles on 11,June,2019
 */
public class ContactRepository {

    private Application application;
    private ContactsAppDatabase database;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<Contact>> liveData = new MutableLiveData<>();
    private long rowReturned;

    public ContactRepository(Application application) {
        this.application = application;
        database = Room.databaseBuilder(application.getApplicationContext(), ContactsAppDatabase.class, "COntactDB").build();

        disposable.add(database.getContactDAO().getContacts()
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Contact>>() {
            @Override
            public void accept(List<Contact> contacts) throws Exception {
                liveData.postValue(contacts);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));
    }

    public MutableLiveData<List<Contact>> getLiveData(){
        return liveData;
    }

    public void createContact(final String name, final String email){

        disposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                rowReturned = database.getContactDAO().addContact(new Contact(name, email, 0));
            }
        }
        ).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableCompletableObserver(){

            @Override
            public void onComplete() {
                Toast.makeText(application.getApplicationContext()," contact added successfully "+rowReturned, Toast.LENGTH_LONG).show();            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(application.getApplicationContext()," error occurred ", Toast.LENGTH_LONG).show();
            }
        }));
    }

    public void updateContact(final Contact contact){

        disposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

                database.getContactDAO().updateContact(contact);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableCompletableObserver(){

            @Override
            public void onComplete() {
                Toast.makeText(application.getApplicationContext()," contact updated successfully ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(application.getApplicationContext()," error occurred ", Toast.LENGTH_LONG).show();

            }
        }));
    }

    public void deleteContact(final Contact contact){

        disposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                database.getContactDAO().deleteContact(contact);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                Toast.makeText(application.getApplicationContext()," contact updated successfully ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(application.getApplicationContext()," error occurred ", Toast.LENGTH_LONG).show();

            }
        }));
    }


}
