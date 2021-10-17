package ${project.packageName}.data

/**
* Created by ${project.author} on ${project.time}.
*/
class DataException : RuntimeException {

    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}