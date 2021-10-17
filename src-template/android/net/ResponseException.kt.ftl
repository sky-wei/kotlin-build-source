package ${project.packageName}.data

/**
* Created by ${project.author} on ${project.time}.
*/
class ResponseException : RuntimeException {

    val code: Int

    constructor(message: String) : this(0, message)

    constructor(code: Int, message: String) : super(message) {
        this.code = code
    }

    constructor(code: Int, message: String, cause: Throwable) : super(message, cause) {
        this.code = code
    }
}