package kr.xd.common.entity

interface ErrorEntity {
    val code: String
    val httpStatus: Int
    val message: String?
}
