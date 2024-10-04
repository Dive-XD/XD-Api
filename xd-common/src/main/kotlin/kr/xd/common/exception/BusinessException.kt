package kr.xd.common.exception

import kr.xd.common.error.ErrorEntity

class BusinessException(
    val errorEntity: ErrorEntity
) : RuntimeException(errorEntity.message)
