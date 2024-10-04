package kr.xd.api.member.presentation

import kr.xd.api.global.presentation.GlobalUri.API_V1
import kr.xd.api.member.presentation.MemberUri.MEMBER
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController {

    @PostMapping(API_V1 + MEMBER)
    fun createMember() {

    }
}