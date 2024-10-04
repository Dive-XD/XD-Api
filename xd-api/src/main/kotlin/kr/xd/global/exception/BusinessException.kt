package kr.xd.global.exception

import kr.xd.global.entity.ErrorEntity

class BusinessException(
    val errorEntity: ErrorEntity
) : RuntimeException(errorEntity.message)
