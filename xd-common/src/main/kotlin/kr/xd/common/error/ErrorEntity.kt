package kr.xd.common.error

interface ErrorEntity {
    val code: String
    val httpStatus: Int
    val message: String?
}
