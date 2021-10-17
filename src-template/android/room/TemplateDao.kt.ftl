package ${project.packageName}.data.disk.dao

import androidx.room.*
import ${project.packageName}.data.disk.entity.TemplateEntity

/**
* Created by ${project.author} on ${project.time}.
*/
@Dao
interface TemplateDao {

    @Query("SELECT * FROM template ORDER BY time DESC")
    fun loadAll(): List<TemplateEntity>

    @Query("SELECT * FROM template WHERE name = :name ORDER BY time")
    fun loadByName(name: String): List<TemplateEntity>

    @Insert
    fun insert(entities: List<TemplateEntity>): List<Long>

    @Delete
    fun delete(entities: List<TemplateEntity>): Int

    @Update
    fun update(entities: List<TemplateEntity>): Int
}