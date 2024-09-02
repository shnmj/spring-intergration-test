package org.example.springv3.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springv3.user.User;
import org.example.springv3.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;


    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = User.builder()
                .id(1).username("ssar").password("1234").email("ssar@nate.com").build();
        session.setAttribute("sessionUser", sessionUser);

        BoardResponse.DetailDTO model = boardService.게시물상세보기(sessionUser, id);
        request.setAttribute("model", model);

        return "board/detail";


    }
}