package ${project.packageName}.data.disk

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ${project.packageName}.data.disk.dao.TemplateDao
import ${project.packageName}.data.disk.entity.TemplateEntity

/**
* Created by ${project.author} on ${project.time}.
*/
@Database(entities = [
    TemplateEntity::class
], version = 1, exportSchema = false)
abstract class ${project.alias}Database : RoomDatabase() {

    abstract fun templateDao(): TemplateDao

    companion object {

        @Volatile private var INSTANCE: ${project.alias}Database? = null

        fun getInstance(context: Context): ${project.alias}Database =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ${project.alias}Database::class.java, "${project.alias}.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()   // 后期需要删除
                .build()
    }
}