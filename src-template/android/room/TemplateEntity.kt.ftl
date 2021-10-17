package ${project.packageName}.data.disk.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
* Created by ${project.author} on ${project.time}.
*/
@Entity(tableName = "template", indices = [Index("name")])
data class TemplateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val value: String,
    val time: Long = System.currentTimeMillis()
)