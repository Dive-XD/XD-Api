package kr.xd.common.exception

import kr.xd.common.entity.ErrorEntity

class BusinessException(
    val errorEntity: ErrorEntity
) : RuntimeException(errorEntity.message)
