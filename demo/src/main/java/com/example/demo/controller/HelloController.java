package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String Hello(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            return String.format( //thymeleafで定義することでフォームに自動でCSRFトークンの送信ができるようになるが、今回は適応させてない。
                    """
                            <h1>Welcome to Google OAuth Demo!</h1>\
                            <h2>Your Account Information:</h2>\
                            <p><strong>Name:</strong> %s</p>\
                            <p><strong>Email:</strong> %s</p>\
                            <form action="/logout" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit">ログアウト</button>
                            </form>""",
                name != null ? name : "N/A",
                email != null ? email : "N/A"
            );
        }
        return "Hello World";
    }
}
