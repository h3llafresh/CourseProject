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
import com.example.courseproject.model.meal.MealDao
import com.example.courseproject.model.meal.MealEntity
import com.example.courseproject.model.number.NumberDao
import com.example.courseproject.model.number.NumberEntity
import com.example.courseproject.model.service.ServiceDao
import com.example.courseproject.model.service.ServiceEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [LoginEntity::class, GuestEntity::class, MealEntity::class, ServiceEntity::class, NumberEntity::class], version = 1, exportSchema = true)
abstract class HotelRoomDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    abstract fun guestDao(): GuestDao

    abstract fun mealDao(): MealDao

    abstract fun serviceDao(): ServiceDao

    abstract fun numberDao(): NumberDao

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
                        populateDatabase(database.loginDao(), database.numberDao())
                    }
                }
            }

            suspend fun populateDatabase(loginDao: LoginDao, numberDao: NumberDao) {
                val login = LoginEntity(0, "admin", "admin", true)
                loginDao.insertLogin(login)
                val hotelNumbers: List<NumberEntity> = listOf(
                    NumberEntity(1, 0, 2, 1, 0, 0, "standard", 50),
                    NumberEntity(2, 1, 2, 1, 1,0, "standard", 100),
                    NumberEntity(3, 0, 3, 2, 0, 0, "standard", 150),
                    NumberEntity(4, 1, 3, 2, 1, 0, "standard", 200),
                    NumberEntity(5, 0, 5, 3, 0, 0, "high", 350),
                    NumberEntity(6, 1, 5, 3, 1, 0, "high", 450),
                    NumberEntity(7, 0, 7, 4, 1, 1, "high", 600),
                    NumberEntity(8, 1, 7, 4, 1,1, "highest", 750),
                    NumberEntity(9, 1, 9, 5, 1, 1, "highest",900),
                    NumberEntity(10, 1, 15, 7, 1,1,"highest", 1800)
                )
                hotelNumbers.forEach { numberDao.insertHotelNumber(it) }
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