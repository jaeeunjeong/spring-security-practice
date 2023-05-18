package com.HIGHERX.controller.verify;

import com.HIGHERX.controller.response.Response;
import com.HIGHERX.controller.response.verify.VerifyAccountResponse;
import com.HIGHERX.controller.response.verify.VerifyCrnResponse;
import com.HIGHERX.controller.response.verify.VerifyNicknameResponse;
import com.HIGHERX.service.verify.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verify")
public class VerifyController {

    private final VerifyService verifyService;

    @GetMapping("/account")
    public Response<VerifyAccountResponse> getAccount(@RequestParam(name = "account", required = true) String account) {
        return Response.success(new VerifyAccountResponse(verifyService.validateAccount(account)));
    }

    @GetMapping("/crn")
    public Response<VerifyCrnResponse> getCrn(@RequestParam(name = "crn", required = true) String crn) {
        return Response.success(new VerifyCrnResponse(verifyService.validateCrn(crn)));
    }

    @GetMapping("/nickname")
    public Response<VerifyNicknameResponse> getNickname(@RequestParam(name = "nickname", required = true) String nickname) {
        return Response.success(new VerifyNicknameResponse(verifyService.validateNickname(nickname)));
    }
}
