package kr.xd.global.entity

interface ErrorEntity {
    val code: String
    val httpStatus: Int
    val message: String?
}
