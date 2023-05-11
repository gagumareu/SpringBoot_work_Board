package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply(){

        IntStream.rangeClosed(1, 300).forEach(i -> {

            long bno = (long) ((Math.random() * (201 - 102)) + 102);

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("reply..... " + i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }

    @Test
    public void readReply1(){

        Optional<Reply> result = replyRepository.findById(1L);

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    @Test
    public void testListByBoard(){

        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(194L).build());

        replyList.forEach(reply -> System.out.println(reply));
    }
}
