package com.example.todolist_roomdatabasexintent.room

import androidx.room.*

@Dao
interface ListDao {
    @Insert
    suspend fun addList(note:Note)

    @Update
    suspend fun updateList(note:Note)

    @Delete
    suspend fun deleteList(note:Note)

    @Query("SELECT * FROM note")
    suspend fun getLists():List<Note>

    @Query("SELECT * FROM note WHERE id=:note_id")
    suspend fun getList(note_id : Int): List<Note>
}