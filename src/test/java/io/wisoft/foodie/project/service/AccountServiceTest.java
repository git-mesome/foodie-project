package io.wisoft.foodie.project.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AccountServiceTest {
//    @Autowired
//    AccountService accountService;
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Test
//    void join() throws Exception{
//        //Given
//        Account account = new Account();
//        account.setEmail("test@gmail.com");
//
//        //When
//        Long saveId = accountService.signUp(account);
//
//        //Then
//        assertEquals(account,accountRepository.getReferenceById(saveId));
//    }
//
//    @Test
//    void 중복_회원_예외() throws Exception {
//
//        //given
//        String email = "test1@gmail.com";
//
//        Account account1 = new Account();
//        account1.setEmail(email);
//
//        Account account2 = new Account();
//        account2.setEmail(email);
//
//        //when
//        accountService.signUp(account1);
//
//        //then
//        IllegalStateException exception = assertThrows(IllegalStateException.class,
//                ()->accountService.signUp(account2));
//        assertEquals("이미 존재하는 회원입니다",exception.getMessage());
//
//
//    }
}