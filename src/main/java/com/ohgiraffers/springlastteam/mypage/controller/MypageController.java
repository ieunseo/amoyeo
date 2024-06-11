package com.ohgiraffers.springlastteam.mypage.controller;

import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.entity.Likes;
import com.ohgiraffers.springlastteam.entity.RequireBuy;
import com.ohgiraffers.springlastteam.entity.Users;

import com.ohgiraffers.springlastteam.gonggu.repository.UserRepository;
import com.ohgiraffers.springlastteam.mypage.repository.MyPageBuyingUserRepository;
import com.ohgiraffers.springlastteam.mypage.repository.MyPageGroupBuyingRepository;
import com.ohgiraffers.springlastteam.mypage.repository.MyPageLikeRepository;
import com.ohgiraffers.springlastteam.mypage.repository.MyPageRequireBuyRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MypageController {

    @Autowired
    @Qualifier("myPageRequireBuyRepository")
    private MyPageRequireBuyRepository requireBuyRepository;

    @Autowired
    @Qualifier("myPageBuyingUserRepository")
    private MyPageBuyingUserRepository buyingUserRepository;

    @Autowired
    @Qualifier("myPageGroupBuyingRepository")
    private MyPageGroupBuyingRepository groupBuyingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("mypageLikeRepository")
    private MyPageLikeRepository likesRepository;

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "mypage/mypage";
    }

    @PostMapping("/mypage/update")
    public String updateUser(
            HttpSession session,
            @RequestParam(required = false) String currentPassword,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) String confirmPassword,
            @RequestParam String userPhone,
            @RequestParam String userApartment,
            @RequestParam String userApartNum,
            @ModelAttribute Users updatedUser,
            Model model) {

        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // 전화번호 형식 검증
        String phonePattern = "^010-\\d{4}-\\d{4}$";
        if (!userPhone.matches(phonePattern)) {
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", "올바른 전화번호 형식이 아닙니다. 010-1234-5678 형식으로 입력해주세요.");
            return "mypage/mypage";
        }

        // 비밀번호 변경 로직
        if (currentPassword != null && !currentPassword.isEmpty()) {
            if (!user.getUserPwd().equals(currentPassword)) {
                model.addAttribute("user", user);
                model.addAttribute("errorMessage", "현재 비밀번호가 일치하지 않습니다.");
                return "mypage/mypage";
            }
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("user", user);
                model.addAttribute("errorMessage", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
                return "mypage/mypage";
            }
            user.setUserPwd(newPassword);
        }

        // 전화번호 업데이트
        user.setUserPhone(userPhone);

        // 아파트 이름과 상세 주소 업데이트
        user.setUserApartment(userApartment);
        user.setUserApartNum(userApartNum);
        userRepository.save(user);

        return "redirect:/mypage";
    }
    /* 쓸데없는 일이 되어버린 구매내역 */
// 쓸데없는 일이 되어버린 구매내역을 다시 활성화
    @GetMapping("/purchashistory")
    public String getPurchaseHistory(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        int userNo = user.getUserNo();
        List<BuyingUser> transactions = buyingUserRepository.findById_UserNo_UserNo(userNo);
        model.addAttribute("transactions", transactions);
        model.addAttribute("profileName", user.getUserName());

        // 추가: 판매자 이름 및 총 가격 설정
        List<String> sellerNames = transactions.stream()
                .map(transaction -> transaction.getId().getBuyingNo().getUser().getUserName())
                .collect(Collectors.toList());
        List<Integer> totalPrices = transactions.stream()
                .map(transaction -> transaction.getBuyingQuantity() * Integer.parseInt(String.valueOf(transaction.getId().getBuyingNo().getBuyingPrice())))
                .collect(Collectors.toList());
        model.addAttribute("sellerNames", sellerNames);
        model.addAttribute("totalPrices", totalPrices);

        return "mypage/purchashistory";
    }

    @PostMapping("/mypage/delete")
    public ResponseEntity<String> deleteUser(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        try {
            userRepository.deleteById(user.getUserNo());
            session.invalidate();
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 중 오류가 발생했습니다.");
        }
    }



    @GetMapping("/mypage/myposts")
    public String getMyPosts(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        int userNo = user.getUserNo();
        List<RequireBuy> myPosts = requireBuyRepository.findByUser_UserNo(userNo);
        model.addAttribute("myPosts", myPosts);
        model.addAttribute("profileName", user.getUserName());
        return "mypage/mywritelist";
    }


    @GetMapping("/mypage/mylikes")
    public String getMyLikes(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        int userNo = user.getUserNo();
        List<Likes> likes = likesRepository.findByUser_UserNo(userNo);
        List<RequireBuy> myPosts = likes.stream()
                .map(Likes::getRequireBuy)
                .collect(Collectors.toList());
        model.addAttribute("myPosts", myPosts);
        model.addAttribute("profileName", user.getUserName());
        return "mypage/likelist";
    }

    @PostMapping("/mypage/mylikes/delete")
    public String deleteMyLike(HttpSession session, @RequestParam int postId, Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // 해당 사용자의 좋아요 항목을 찾기
        Likes like = likesRepository.findByUser_UserNoAndRequireBuy_RequireNo(user.getUserNo(), postId);
        if (like == null) {
            model.addAttribute("errorMessage", "해당 항목을 찾을 수 없거나 권한이 없습니다.");
            return "mypage/likelist";  // 에러 메시지를 표시하기 위해 좋아요 목록 페이지로 리디렉션
        }

        // 좋아요 항목 삭제
        likesRepository.delete(like);
        return "redirect:/mypage/mylikes";
    }
    @GetMapping("/mypage/mylikes/details")
    public String getLikeDetails(@RequestParam int requireNo, HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // 좋아요 누른 게시물의 상세 정보 가져오기
        RequireBuy requireBuy = requireBuyRepository.findById(requireNo).orElse(null);
        if (requireBuy == null) {
            return "redirect:/mypage/mylikes";
        }

        model.addAttribute("requireBuy", requireBuy);
        return "mypage/requirebuydetails"; // 상세 페이지로 이동
    }


}
