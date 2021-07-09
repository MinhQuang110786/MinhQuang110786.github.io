package com.heaven.bookstore.controller;

import com.heaven.bookstore.domain.*;
import com.heaven.bookstore.domain.security.PasswordResetToken;
import com.heaven.bookstore.domain.security.Role;
import com.heaven.bookstore.domain.security.UserRole;
import com.heaven.bookstore.service.*;
import com.heaven.bookstore.service.impl.UserSecurityService;
import com.heaven.bookstore.utility.MailConstructor;
import com.heaven.bookstore.utility.SecurityUtility;
import com.heaven.bookstore.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
public class HomeController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailConstructor mailConstructor;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private UserBillingService userBillingService;

    @Autowired
    private UserShippingService userShippingService;

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public String index(){
        return "index";
    }



    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("classActiveLogin",true);
        return "myAccount";
    }

    @GetMapping("/hours")
    public String hours(){
        return "hours";
    }

    @GetMapping("/faq")
    public String faq(){
        return "faq";
    }

    @PostMapping ("/forgetPassword")
    public String forgetPassword(HttpServletRequest request,
                                 @ModelAttribute("email") String email,
                                 @ModelAttribute("username") String username,
                                 Model model) throws Exception{

        model.addAttribute("classActiveForgetPassword",true);
        User user = userService.findByEmail(email);
        if(user==null){
            model.addAttribute("emailNotExists",true);
            return "myAccount";
        }
        String password = SecurityUtility.randomPassword();
        String encryptePassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptePassword);


        userService.save(user);
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user,token);
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl,request.getLocale(),token,user,password);
        mailSender.send(newEmail);
        model.addAttribute("forgetPasswordEmailSent",true);
        return "myAccount";
    }

    @PostMapping("/newUser")
    public String newUserPost(HttpServletRequest request,
                              @ModelAttribute("email") String userEmail,
                              @ModelAttribute("username") String username,
                              Model model) throws Exception{
        model.addAttribute("classActiveNewAccount",true);
        model.addAttribute("email",userEmail);
        model.addAttribute("username",username);
        if(userService.findByUsername(username)!=null){
            model.addAttribute("usernameExists",true);
            return "myAccount";
        }
        if(userService.findByEmail(userEmail)!=null){
            model.addAttribute("emailExists",true);
            return "myAccount";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);
        String password = SecurityUtility.randomPassword();
        String encryptePassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptePassword);

        Role role = new Role();
        role.setRoleId(2);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user,role));
        userService.createUser(user,userRoles);
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user,token);
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl,request.getLocale(),token,user,password);
        mailSender.send(email);
        model.addAttribute("emailSent",true);

        return "myAccount";
    }

    @GetMapping("/newUser")
    public String creatNewAccount(Model model, Locale locale, @RequestParam("token") String token){
        PasswordResetToken passToken = userService.getPasswordResetToken(token);

        if(passToken==null){
            String message = "Invalid Token";
            model.addAttribute("message",message);
            return "redirect:/badRequest";
        }

        User user = passToken.getUser();
        String username = user.getUsername();
        UserDetails userDetails = userSecurityService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user",user);
        model.addAttribute("classActiveEdit",true);

        return "myProfile";
    }

    @PostMapping(value="/updateUserInfo")
    public String updateUserInfo(
            @ModelAttribute("user") User user,
            @ModelAttribute("newPassword") String newPassword,
            Model model
    ) throws Exception {
        User currentUser = userService.findById(user.getId());

        if(currentUser == null) {
            throw new Exception ("User not found");
        }

        /*check email already exists*/
        if (userService.findByEmail(user.getEmail())!=null) {
            if(userService.findByEmail(user.getEmail()).getId() != currentUser.getId()) {
                model.addAttribute("emailExists", true);
                return "myProfile";
            }
        }

        /*check username already exists*/
        if (userService.findByUsername(user.getUsername())!=null) {
            if(userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
                model.addAttribute("usernameExists", true);
                return "myProfile";
            }
        }

//		update password
        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")){
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbPassword = currentUser.getPassword();
            if(passwordEncoder.matches(user.getPassword(), dbPassword)){
                currentUser.setPassword(passwordEncoder.encode(newPassword));
            } else {
                model.addAttribute("incorrectPassword", true);

                return "myProfile";
            }
        }

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());

        userService.save(currentUser);

        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUser);
        model.addAttribute("classActiveEdit", true);

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("listOfCreditCards", true);

        UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("orderList", user.getOrderList());

        return "myProfile";
    }

    @GetMapping("/bookshelf")
    public String bookshelf(Model model,Principal principal){
        if(principal!=null){
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList",bookList);
        model.addAttribute("activeAll",true);
        return "bookshelf";
    }

    @GetMapping("/bookDetail")
    public String bookDetail(@RequestParam("id") Long id, Model model, Principal principal){
        if(principal!=null){
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        model.addAttribute("qtyList",qtyList);
        model.addAttribute("qty",1);

        return "bookDetail";
    }

    @GetMapping("/myProfile")
    public String myProfile(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("userPaymentList",user.getUserPaymentList());
        model.addAttribute("userShippingList",user.getUserShippingList());
        model.addAttribute("orderList",user.getOrderList());
        UserShipping userShipping = new UserShipping();
        model.addAttribute("userShipping",userShipping);
        model .addAttribute("listOfCreditCards",true);
        model.addAttribute("listOfShippingAddresses",true);
        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList",stateList);
        model.addAttribute("classActiveEdit",true);
        return "myProfile";
    }



    @GetMapping("/listOfShippingAddresses")
    public String listOfShippingAddress(
            Model model,Principal principal,
            HttpServletRequest request  ){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("userPaymentList",user.getUserPaymentList());
        model.addAttribute("userShippingList",user.getUserShippingList());

        model.addAttribute("orderList",user.getOrderList());
        model.addAttribute("listOfShippingAddresses",true);
        model.addAttribute("classActiveShipping",true);
        model.addAttribute("listOfCreditCards",true);

        return "myProfile";
    }

    @GetMapping("/addNewShippingAddress")
    public String addNewShippingAddress(
            Model model,Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);

        model.addAttribute("addNewShippingAddress",true);
        model.addAttribute("classActiveShipping",true);
        model.addAttribute("listOfCreditCards",true);

        UserShipping userShipping = new UserShipping();

        model.addAttribute("userShipping",userShipping);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList",stateList);
        model.addAttribute("userPaymentList",user.getUserPaymentList());
        model.addAttribute("userShippingList",user.getUserShippingList());
        model.addAttribute("orderList",user.getOrderList());
        return "myProfile";
    }

    @PostMapping("/addNewShippingAddress")
    public String addNewShippingAddress(@ModelAttribute("userShipping") UserShipping userShipping,
                                   Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        userService.updateUserShipping(userShipping,user);
        model.addAttribute("user",user);
        model.addAttribute("userPaymentList",user.getUserPaymentList());
        model.addAttribute("userShippingList",user.getUserShippingList());
        model.addAttribute("listOfShippingAddresses",true);
        model.addAttribute("classActiveShipping",true);
        model.addAttribute("listOfCreditCards",true);
        model.addAttribute("orderList",user.getOrderList());
        return "myProfile";

    }

    @GetMapping("/updateUserShipping")
    public String updateUserShipping(
            @RequestParam("id") Long userShippingId,Principal principal,
            Model model){
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(userShippingId);
        if(user.getId()!=userShipping.getUser().getId()){
            return "badRequestPage";
        }else{
            model.addAttribute("user", user);
            model.addAttribute("userShipping",userShipping);
            model.addAttribute("updateUserShippingInfo",true);
            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);
            model.addAttribute("classActiveShipping",true);
            model.addAttribute("listOfCreditCards", true);
            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());
            model.addAttribute("orderList",user.getOrderList());
            return "myProfile";
        }

    }

    @PostMapping("/updateUserShippingInfo")
    public String updateShippingAddress(
                    @ModelAttribute("userShipping") UserShipping userShipping,
                    Principal principal, Model model, @RequestParam("id") Long shippingAddressId
                ){
        User user = userService.findByUsername(principal.getName());
        UserShipping existUserShipping = userShippingService.findById(shippingAddressId);
        existUserShipping.setUserShippingName(userShipping.getUserShippingName());
        existUserShipping.setUserShippingStreet1(userShipping.getUserShippingStreet1());
        existUserShipping.setUserShippingStreet2(userShipping.getUserShippingStreet2());
        existUserShipping.setUserShippingCity(userShipping.getUserShippingCity());
        existUserShipping.setUserShippingCountry(userShipping.getUserShippingCountry());
        existUserShipping.setUserShippingState(userShipping.getUserShippingState());
        existUserShipping.setUserShippingZipcode(userShipping.getUserShippingZipcode());
        userShippingService.save(existUserShipping);

        userService.updateUserShipping(existUserShipping,user);

        model.addAttribute("user",user);
        model.addAttribute("userPaymentList",user.getUserPaymentList());
        model.addAttribute("userShippingList",user.getUserShippingList());

        model.addAttribute("classActiveShipping",true);
        model.addAttribute("listOfCreditCards",true);
        model.addAttribute("listOfShippingAddresses",true);
        model.addAttribute("orderList",user.getOrderList());
        return "redirect:/myProfile";

    }

    @PostMapping("/setDefaultShippingAddress")
    public String setDefaultShippingAddress(
                    @ModelAttribute("defaultShippingAddressId") Long defaultShippingId,
                    Principal principal, Model model
                ){
        User user = userService.findByUsername(principal.getName());
        userService.setUserDefaultShipping(defaultShippingId,user);
        model.addAttribute("user",user);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList",user.getOrderList());
        return "myProfile";
    }

    @GetMapping("/removeUserShipping")
    public String removeUserShipping(
            @RequestParam("id") Long addressId, Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(addressId);
        if(user.getId()!=userShipping.getUser().getId()){
            return "badRequestPage";
        }else{
            model.addAttribute("user", user);
            userShippingService.removeById(addressId);
            model.addAttribute("classActiveShipping", true);
            model.addAttribute("listOfShippingAddresses", true);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());
            model.addAttribute("orderList",user.getOrderList());
            return "myProfile";
        }
    }




    @GetMapping("/listOfCreditCards")
    public String listOfCreditCards(
            Model model,Principal principal,
            HttpServletRequest request ){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("userPaymentList",user.getUserPaymentList());
        model.addAttribute("userShippingList",user.getUserShippingList());
//        model.addAttribute("orderList",user.orderList());
        model.addAttribute("listOfCreditCards",true);
        model.addAttribute("classActiveBilling",true);
        model.addAttribute("listOfShippingAddresses",true);
        model.addAttribute("orderList",user.getOrderList());
        return "myProfile";
    }



    @GetMapping("/addNewCreditCard")
    public String addNewCreditcard(
            Model model,Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("addNewCreditCard",true);
        model.addAttribute("classActiveBilling",true);
        model.addAttribute("listOfShippingAddresses",true);

        UserBilling userBilling = new UserBilling();
        UserPayment userPayment = new UserPayment();

        model.addAttribute("userBilling",userBilling);
        model.addAttribute("userPayment",userPayment);
        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList",stateList);
        model.addAttribute("userPaymentList",user.getUserPaymentList());
        model.addAttribute("userShippingList",user.getUserShippingList());
        model.addAttribute("orderList",user.getOrderList());

        return "myProfile";
    }

    @PostMapping("/addNewCreditCard")
    public String addNewCreditCard(@ModelAttribute("userPayment") UserPayment userPayment,
                                   @ModelAttribute("userBilling") UserBilling userBilling,
                                   Principal principal, Model model){
            User user = userService.findByUsername(principal.getName());
            userService.updateUserBilling(userBilling,userPayment,user);
            model.addAttribute("user",user);
            model.addAttribute("userPaymentList",user.getUserPaymentList());
            model.addAttribute("userShippingList",user.getUserShippingList());
            model.addAttribute("listOfCreditCards",true);
            model.addAttribute("classActiveBilling",true);
            model.addAttribute("listOfShippingAddresses",true);
            model.addAttribute("orderList",user.getOrderList());
            return "myProfile";

    }

    @GetMapping("/updateCreditCard")
    public String updateCreditCard(
            @RequestParam("id") Long creditCardId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserPayment userPayment = userPaymentService.findById(creditCardId);

        if(user.getId() != userPayment.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);
            UserBilling userBilling = userPayment.getUserBilling();
            model.addAttribute("userPayment", userPayment);
            model.addAttribute("userBilling", userBilling);
            model.addAttribute("updateUserPaymentInfo",true);
            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

//            model.addAttribute("addNewCreditCard", true);
            model.addAttribute("classActiveBilling", true);
            model.addAttribute("listOfShippingAddresses", true);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());
            model.addAttribute("orderList", user.getOrderList());

            return "myProfile";
        }
    }

    @PostMapping("/updateUserPaymentInfo")
    public String updateUserPaymentInfo(@ModelAttribute("userPayment")UserPayment userPayment,
                                         @ModelAttribute("userBilling")UserBilling userBilling,Principal principal,
                                        Model model,@RequestParam("id") Long creditCardId){
        User user = userService.findByUsername(principal.getName());
        UserPayment existUserPayment = userPaymentService.findById(creditCardId);
        existUserPayment.setType(userPayment.getType());
        existUserPayment.setCardName(userPayment.getCardName());
        existUserPayment.setCardNumber(userPayment.getCardNumber());
        existUserPayment.setExpireMonth(userPayment.getExpireMonth());
        existUserPayment.setExpireYear(userPayment.getExpireYear());
        existUserPayment.setCvc(userPayment.getCvc());
        existUserPayment.setHolderName(userPayment.getHolderName());
        userPaymentService.save(existUserPayment);

        UserBilling existBilling = existUserPayment.getUserBilling();
        existBilling.setUserBillingName(userBilling.getUserBillingName());
        existBilling.setUserBillingStreet1(userBilling.getUserBillingStreet1());
        existBilling.setUserBillingStreet2(userBilling.getUserBillingStreet2());
        existBilling.setUserBillingCountry(userBilling.getUserBillingCountry());
        existBilling.setUserBillingCity(userBilling.getUserBillingCity());
        existBilling.setUserBillingState(userBilling.getUserBillingState());
        existBilling.setUserBillingZipcode(userBilling.getUserBillingZipcode());
        userBillingService.save(existBilling);


        userService.updateUserBilling(existBilling,existUserPayment,user);
        model.addAttribute("user",user);
        model.addAttribute("userPaymentList",user.getUserPaymentList());
        model.addAttribute("userShippingList",user.getUserShippingList());
        model.addAttribute("classActiveBilling",true);
        model.addAttribute("listOfCreditCards",true);
        model.addAttribute("listOfShippingAddresses",true);
        model.addAttribute("orderList",user.getOrderList());
        return "redirect:/myProfile";
    }


    @PostMapping("/setDefaultPayment")
    public String setDefaultPayment(
            @ModelAttribute("defaultUserPaymentId") Long defaultPaymentId,
            Principal principal,Model model){
        User user = userService.findByUsername(principal.getName());
        userService.setUserDefaultPayment(defaultPaymentId,user);

        model.addAttribute("user",user);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList",user.getOrderList());
        return "myProfile";

    }

    @GetMapping("/removeCreditCard")
    public String removeCreditCard(
            @RequestParam("id") Long creditCardId,Principal principal,Model model){
        User user = userService.findByUsername(principal.getName());
        UserPayment userPayment = userPaymentService.findById(creditCardId);

        if(user.getId() != userPayment.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);
            userPaymentService.removeById(creditCardId);

            model.addAttribute("listOfCreditCards", true);
            model.addAttribute("classActiveBilling", true);
            model.addAttribute("listOfShippingAddresses", true);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());
            model.addAttribute("orderList",user.getOrderList());
            return "myProfile";
        }
    }



    @GetMapping("/orderDetail")
    public String orderDetail(
            @RequestParam("id") Long orderId,
            Principal principal,Model model
    ){
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.findOne(orderId);

        if(order.getUser().getId()!=user.getId()){
            return "badRequestPage";
        }else{
            List<CartItem> cartItemList = cartItemService.findByOrder(order);
            model.addAttribute("cartItemList",cartItemList);
            model.addAttribute("user", user);
            model.addAttribute("order",order);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());
            model.addAttribute("orderList",user.getOrderList());

            UserShipping userShipping = new UserShipping();
            model.addAttribute("userShipping",userShipping);
            model.addAttribute("updateUserShippingInfo",true);
            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);
            model.addAttribute("listOfShippingAddresses",true);
            model.addAttribute("classActiveOrders",true);
            model.addAttribute("listOfCreditCards", true);
            model.addAttribute("displayOrderDetail",true);
            return "myProfile";
        }
    }




}
