package ${project.packageName}.data.model

/**
 * Created by ${project.author} on ${project.time}.
 */
sealed class XResult<out T> {

    data class Success<out T>(val value: T) : XResult<T>()

    data class Failure(val throwable: Throwable?) : XResult<Nothing>()

    companion object {

        val Invalid = Failure(NullPointerException())
    }
}