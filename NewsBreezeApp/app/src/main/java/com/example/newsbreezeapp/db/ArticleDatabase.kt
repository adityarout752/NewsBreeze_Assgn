package com.example.newsbreezeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsbreezeapp.modelData.Article


//this is database class

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract  class ArticleDatabase :RoomDatabase(){

    abstract fun getArticlesDao(): ArticleDao
    companion object{

        @Volatile //other thread can immediately see when changes instance
        private var instance: ArticleDatabase?=null

        private val Lock=Any()

        operator fun invoke(context: Context)= instance ?: synchronized(Lock){
            instance ?: createDatabse(context).also{
                instance =it
            }
        }

        private fun createDatabse(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}