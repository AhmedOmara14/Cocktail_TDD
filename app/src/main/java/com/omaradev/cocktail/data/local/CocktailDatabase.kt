package com.omaradev.cocktail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omaradev.cocktail.domain.model.Question

@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}
