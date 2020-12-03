package com.example.courseproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.courseproject.model.guest.GuestDao
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.model.login.LoginDao
import com.example.courseproject.model.login.LoginEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [LoginEntity::class, GuestEntity::class], version = 1, exportSchema = true)
abstract class HotelRoomDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    abstract fun guestDao(): GuestDao

    companion object {

        @Volatile
        private var INSTANCE: HotelRoomDatabase? = null

        private class HotelDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.loginDao())
                    }
                }
            }

            suspend fun populateDatabase(loginDao: LoginDao) {
                var login = LoginEntity(1, "admin", "admin", true)
                loginDao.insertLogin(login)
                login = LoginEntity(2, "user", "user", false)
                loginDao.insertLogin(login)
            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope): HotelRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HotelRoomDatabase::class.java,
                    "hotel_database"
                )
                    .addCallback(HotelDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}