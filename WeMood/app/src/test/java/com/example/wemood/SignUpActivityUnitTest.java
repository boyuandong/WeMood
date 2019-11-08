package com.example.wemood;

import android.app.Activity;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;


import android.app.Activity;
import android.util.Log;
import android.widget.EditText;


import androidx.annotation.Nullable;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test class for Sign up Activity. All the unit tests are written here.*/

public class SignUpActivityUnitTest {

    /**To get activity object so that we can access method from SignUpActivity*/
    private CollectionReference collectionReference;
    private FirebaseFirestore db;
    SignUpActivity activity = new SignUpActivity();

    /**To test correct format of phone*/

    @Test
    public void TestAddRightPhone(){
        String RightPhone = "5879380321";
        assertTrue(activity.isPhoneValid(RightPhone));
    }

    /**To test wrong format of phone*/
    @Test
    public  void TestAddWrongPhone(){
        String WrongPhone = "134dsad";
        assertFalse(activity.isPhoneValid(WrongPhone));
    }

    /**To test wrong format of password*/
    @Test
    public  void TestAddWrongPassword(){
        String RightPassword = "2313";
        assertFalse(activity.isPasswordValid(RightPassword));
    }

    /**To test Empty password*/

    @Test
    public void TestAddEmptyPassword(){
        String EmptyPassword = "";
        assertFalse(activity.isPasswordValid(EmptyPassword));

    }

    /*@Test
    public void TestCreateAccount(){
        final String name = "Alex";
        String email = "123434354@qq.com";
        String phone = "1234567890";
        String password  = "abcd1234";
        activity.createAccount(name,email,password,phone);


        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {


            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots){

                    boolean found =false;
                    String usernameInDataBase = doc.getId();
                    Log.d("name1",usernameInDataBase);
                    if (usernameInDataBase.equals(name)){
                        found =true;
                        assertTrue(found);
                        Log.d("name2", "found");
                        break;
                    }

                }
            }
        });

    }*/


}






