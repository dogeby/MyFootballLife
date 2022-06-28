package com.example.myfootballlife.data.api

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

/** https://firebase.google.com/docs/database/android/start */
class FirebaseRealtimeDbDao @Inject constructor(
    private val databaseReference: DatabaseReference
){
    inline fun <reified T> setValueEventListener (path: String, crossinline callback: (T) -> Unit){
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.child(path).getValue(T::class.java)?.let { callback(it) }
            }
            override fun onCancelled(error: DatabaseError) { }
        }
        addValueEventListener(listener)
    }

    fun addValueEventListener(listener: ValueEventListener) {
        databaseReference.addValueEventListener(listener)
    }
}