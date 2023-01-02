package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.components.clientComponents.LastOrderProfileClientComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfileClientPage {
    /**<body>
     <noscript data-n-head="ssr" data-hid="gtm-noscript" data-pbody="true"><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-K4L682B&" height="0" width="0" style="display:none;visibility:hidden" title="gtm"></iframe></noscript><div id="__nuxt"><!----><div id="__layout"><div data-v-2647b5ad="" id="app"><div data-v-2647b5ad="" class="container wrapper"><div data-v-af68ad0e="" data-v-2647b5ad="" id="gas__content-header" class="container"><div data-v-af68ad0e="" class="logo-wrap"><div data-v-af68ad0e="" class="logo"><a data-v-af68ad0e="" href="/" class="nuxt-link-active"><img data-v-af68ad0e="" src="/_ipx/_/images/logo-blue.svg" alt="Gasworkers"> <span data-v-af68ad0e="">Gasworkers</span></a></div></div> <div data-v-af68ad0e="" class="actions-block"><div data-v-af68ad0e="" class="notifications icon"><!----></div> <div data-v-af68ad0e="" class="messages icon"><!----></div> <div data-v-af68ad0e="" class="profile-wrap"><div data-v-af68ad0e="" class="profile icon"></div> <span data-v-af68ad0e="" class="arrow-down"></span> <div data-v-af68ad0e="" class="profile-menu"><div data-v-af68ad0e="" class="profile-menu__link text-primary">Шингелевич Игорь Сергеевич</div> <hr data-v-af68ad0e=""> <a data-v-af68ad0e="" href="/profile/edit" class="profile-menu__link">
     Профиль
     </a> <a data-v-af68ad0e="" href="/profile/reviews" class="profile-menu__link">
     Мои отзывы
     </a> <!----> <hr data-v-af68ad0e=""> <button data-v-af68ad0e="" class="profile-menu__link">
     Выйти <span data-v-af68ad0e="" class="back-ic"></span></button></div></div></div> <div data-v-af68ad0e="" class="btn-block"><!----> <!----> <button data-v-6d08f792="" data-v-af68ad0e="" class="small btn btn-warning disable-outline">
     Создать заказ
     </button> <!----></div> <button data-v-af68ad0e="" type="button" class="burger"></button></div> <div data-v-2647b5ad="" class="sidebar"><div data-v-2647b5ad="" class="sidebar-content"><!----> <ul data-v-2647b5ad="" class="sidebar-nav"><li data-v-2647b5ad="" class="active nuxt-link-active"><div data-v-2647b5ad="" class="link"><svg data-v-2647b5ad="" fill="none" height="31" viewBox="0 0 22 21" width="31" xmlns="http://www.w3.org/2000/svg" style="border: 3px solid transparent; --darkreader-inline-border-top:transparent; --darkreader-inline-border-right:transparent; --darkreader-inline-border-bottom:transparent; --darkreader-inline-border-left:transparent;" data-darkreader-inline-border-top="" data-darkreader-inline-border-right="" data-darkreader-inline-border-bottom="" data-darkreader-inline-border-left=""><path data-v-2647b5ad="" d="M21.0556 9.67788C21.0537 9.49089 20.9779 9.31226 20.8447 9.181L18.8291 7.37631L11.4697 0.813812C11.3408 0.698511 11.1739 0.634766 11.0009 0.634766C10.828 0.634766 10.6611 0.698511 10.5322 0.813812L3.19156 7.37631L1.15719 9.181C1.02483 9.30313 0.94291 9.47032 0.927499 9.64975C0.915644 9.82023 0.965456 9.98929 1.06785 10.1261C1.17025 10.2629 1.31841 10.3584 1.48531 10.3951C1.48531 10.4185 2.89156 10.3951 2.93844 10.3951V20.0888C2.93844 20.2753 3.01252 20.4541 3.14438 20.586C3.27624 20.7179 3.45508 20.7919 3.64156 20.7919H8.39937C8.49366 20.7951 8.58762 20.7792 8.67565 20.7453C8.76368 20.7114 8.84399 20.6601 8.91178 20.5945C8.97957 20.5289 9.03347 20.4503 9.07026 20.3634C9.10706 20.2765 9.12599 20.1832 9.12594 20.0888V14.2576H12.9228V20.0888C12.9228 20.2753 12.9969 20.4541 13.1288 20.586C13.2606 20.7179 13.4395 20.7919 13.6259 20.7919H18.3416C18.528 20.7919 18.7069 20.7179 18.8387 20.586C18.9706 20.4541 19.0447 20.2753 19.0447 20.0888V10.4091C19.1103 10.4091 20.4931 10.4091 20.4978 10.4091C20.6626 10.3737 20.8094 10.2805 20.9117 10.1464C21.0139 10.0124 21.065 9.84623 21.0556 9.67788Z"></path></svg> <span data-v-2647b5ad="">
     Главная
     </span></div></li> <!----> <!----> <!----> <!----> <!----> <!----> <!----> <!----> <li data-v-2647b5ad="" class=""><div data-v-2647b5ad="" class="link"><svg data-v-2647b5ad="" fill="none" height="31" viewBox="0 0 31 31" width="31" xmlns="http://www.w3.org/2000/svg"><path data-v-2647b5ad="" d="M8.70128 26.0853V27.4247C8.70128 27.6712 8.96464 27.8711 9.28951 27.8711H9.87775C10.2026 27.8711 10.466 27.6712 10.466 27.4247V26.0853H8.70128Z"></path> <path data-v-2647b5ad="" d="M16.4777 2.87109H13.8666C13.5967 2.8711 13.3615 3.01041 13.296 3.20904L13.113 3.76395H17.2307L17.0483 3.20904C16.9828 3.01041 16.7475 2.8711 16.4777 2.87109Z"></path> <path data-v-2647b5ad="" d="M19.8777 26.0853V27.4247C19.8777 27.6712 20.1411 27.8711 20.466 27.8711H21.0542C21.3791 27.8711 21.6425 27.6712 21.6425 27.4247V26.0853H19.8777Z"></path> <path data-v-2647b5ad="" d="M21.0542 21.621H25.1719V5.10312C25.1719 4.85656 24.9085 4.65669 24.5836 4.65669H5.76011C5.43524 4.65669 5.17188 4.85656 5.17188 5.10312V21.621H18.7013C19.0262 21.621 19.2895 21.8209 19.2895 22.0675C19.2895 22.314 19.0262 22.5139 18.7013 22.5139H5.17188V24.7461C5.17188 24.9926 5.43524 25.1925 5.76011 25.1925H24.5836C24.9085 25.1925 25.1719 24.9926 25.1719 24.7461V22.5139H21.0542C20.7294 22.5139 20.466 22.314 20.466 22.0675C20.466 21.8209 20.7294 21.621 21.0542 21.621Z"></path></svg>
     Объекты / Оборудование
     </div></li> <li data-v-2647b5ad="" class=""><a data-v-2647b5ad="" class="link"><svg data-v-2647b5ad="" fill="none" height="31" viewBox="0 0 31 31" width="31" xmlns="http://www.w3.org/2000/svg"><path data-v-2647b5ad="" d="M23.9334 2.87109H13.2721V9.90879C13.2721 10.3746 12.884 10.7522 12.4052 10.7522H5.17188V26.6662C5.17188 27.3316 5.72638 27.8711 6.41031 27.8711H23.9334C24.6174 27.8711 25.1719 27.3316 25.1719 26.6662V4.07603C25.1719 3.4106 24.6174 2.87109 23.9334 2.87109ZM19.0522 22.8791H11.9938C11.515 22.8791 11.1269 22.5014 11.1269 22.0356C11.1269 21.5698 11.515 21.1921 11.9938 21.1921H19.0522C19.531 21.1921 19.9191 21.5698 19.9191 22.0356C19.9191 22.5014 19.531 22.8791 19.0522 22.8791ZM19.0522 18.9112H11.9938C11.515 18.9112 11.1269 18.5336 11.1269 18.0678C11.1269 17.602 11.515 17.2243 11.9938 17.2243H19.0522C19.531 17.2243 19.9191 17.602 19.9191 18.0678C19.9191 18.5336 19.531 18.9112 19.0522 18.9112ZM19.0522 14.9435H11.9938C11.515 14.9435 11.1269 14.5659 11.1269 14.1C11.1269 13.6342 11.515 13.2566 11.9938 13.2566H19.0522C19.531 13.2566 19.9191 13.6342 19.9191 14.1C19.9191 14.5659 19.531 14.9435 19.0522 14.9435Z"></path> <path data-v-2647b5ad="" d="M11.5283 2.87109C11.1999 2.87109 10.8849 2.998 10.6526 3.22407L5.53506 8.20303C5.3028 8.429 5.17227 8.73545 5.17227 9.05498V9.06528H11.5388V2.87109H11.5283Z"></path></svg>
     Заказы/Счета
     <span data-v-2647b5ad="" class="arrow-down"></span></a> <ul data-v-2647b5ad="" class="dropdown-menu"><li data-v-2647b5ad="" class=""><div data-v-2647b5ad="" class="link">Список заказов</div></li> <li data-v-2647b5ad="" class=""><div data-v-2647b5ad="" class="link">Список счетов</div></li></ul></li> <!----> <!----> <!----> <li data-v-2647b5ad="" class=""><div data-v-2647b5ad="" class="link"><svg data-v-2647b5ad="" fill="none" height="31" viewBox="0 0 31 31" width="31" xmlns="http://www.w3.org/2000/svg"><path data-v-2647b5ad="" d="M4.86301 12.0307C4.98778 12.4812 5.16093 12.9157 5.37992 13.3233L4.75607 14.152C4.55236 14.4229 4.57528 14.8091 4.80445 15.0532L5.879 16.1849C6.11071 16.429 6.47738 16.4504 6.73456 16.2358L7.51628 15.5841C7.9186 15.8282 8.34639 16.0186 8.79199 16.1527L8.90913 17.2121C8.94732 17.5553 9.22232 17.8128 9.54825 17.8128H11.0684C11.3943 17.8128 11.6693 17.5553 11.7075 17.2121L11.8196 16.1903C12.2983 16.0588 12.7566 15.8631 13.187 15.6083L13.9432 16.2385C14.2004 16.4531 14.5671 16.4289 14.7988 16.1876L15.8733 15.0558C16.105 14.8118 16.1254 14.4256 15.9217 14.1547L15.3335 13.3689C15.578 12.9237 15.7689 12.449 15.8962 11.9529L16.8027 11.8429C17.1287 11.8027 17.3731 11.513 17.3731 11.1698V9.56867C17.3731 9.22539 17.1287 8.93575 16.8027 8.89552L15.909 8.78556C15.7867 8.29478 15.6034 7.82545 15.3692 7.38562L15.9192 6.65346C16.1229 6.38259 16.1 5.9964 15.8708 5.75235L14.7988 4.62327C14.5671 4.37922 14.2004 4.35776 13.9432 4.57232L13.2685 5.13551C12.8305 4.86464 12.3619 4.65814 11.8705 4.51868L11.7687 3.59879C11.7305 3.25551 11.4555 2.99805 11.1295 2.99805H9.60939C9.28346 2.99805 9.00846 3.25551 8.97026 3.59879L8.86841 4.51868C8.36424 4.66082 7.88298 4.87537 7.43483 5.15697L6.73459 4.57232C6.47741 4.35776 6.11074 4.3819 5.87902 4.62327L4.80447 5.75503C4.57276 5.99908 4.55239 6.38527 4.75609 6.65614L5.34175 7.43657C5.10749 7.88177 4.92924 8.35646 4.81211 8.84993L3.87506 8.96257C3.54913 9.00279 3.30469 9.29244 3.30469 9.63572V11.2368C3.30469 11.5801 3.54913 11.8697 3.87506 11.91L4.86301 12.0307ZM10.3707 7.69942C11.7559 7.69942 12.8839 8.8875 12.8839 10.3464C12.8839 11.8054 11.7559 12.9935 10.3707 12.9935C8.98554 12.9935 7.85749 11.8054 7.85749 10.3464C7.85749 8.8875 8.98551 7.69942 10.3707 7.69942Z"></path> <path data-v-2647b5ad="" d="M25.3977 12.4244L24.5956 11.711C24.3487 11.4911 23.9845 11.5018 23.7503 11.7352L23.3072 12.1723C22.9329 11.9819 22.5357 11.8478 22.1257 11.77L22.001 11.1264C21.9373 10.7938 21.6445 10.5632 21.3236 10.59L20.2771 10.6839C19.9562 10.7134 19.7041 10.9896 19.6939 11.3302L19.6736 11.9846C19.2713 12.1375 18.8919 12.3466 18.5455 12.6095L18.0159 12.2394C17.746 12.0516 17.387 12.1053 17.1782 12.3654L16.5009 13.2156C16.292 13.4757 16.3022 13.8592 16.5237 14.106L16.9872 14.6262C16.8267 15.007 16.7122 15.4067 16.646 15.817L15.9839 15.9591C15.6682 16.0262 15.4492 16.3346 15.4747 16.6725L15.5638 17.7748C15.5918 18.1127 15.8541 18.3782 16.1775 18.3889L16.893 18.413C17.0229 18.7724 17.1935 19.113 17.3997 19.4321L16.9999 20.0677C16.8217 20.352 16.8726 20.7302 17.1196 20.9501L17.9217 21.6635C18.1687 21.8834 18.5328 21.8727 18.7671 21.6393L19.2917 21.1217C19.6354 21.2907 19.997 21.4167 20.3687 21.4918L20.5139 22.2481C20.5776 22.5807 20.8704 22.8113 21.1912 22.7845L22.2378 22.6906C22.5586 22.6611 22.8107 22.3849 22.8209 22.0443L22.8438 21.3041C23.2257 21.1619 23.5873 20.9688 23.9209 20.7302L24.4989 21.1325C24.7688 21.3202 25.1278 21.2666 25.3367 21.0064L26.014 20.1616C26.2228 19.9015 26.2126 19.518 25.9911 19.2712L25.5251 18.751C25.6957 18.3701 25.8205 17.9679 25.8917 17.5548L26.5283 17.4181C26.8441 17.351 27.0631 17.0426 27.0376 16.7046L26.9485 15.6024C26.9205 15.2645 26.6582 14.999 26.3348 14.9882L25.6957 14.9668C25.5632 14.5752 25.385 14.2052 25.1635 13.8618L25.5123 13.3094C25.6957 13.0252 25.6447 12.6443 25.3977 12.4244ZM21.4535 18.7965C20.3229 18.8985 19.3247 18.0107 19.2305 16.82C19.1337 15.6293 19.9766 14.5779 21.1071 14.4787C22.2377 14.3768 23.2359 15.2645 23.3301 16.4553C23.4269 17.646 22.584 18.6973 21.4535 18.7965Z"></path> <path data-v-2647b5ad="" d="M8.52341 21.7631C8.20257 21.7979 7.95558 22.0822 7.95303 22.4228L7.94539 23.1013C7.9403 23.4419 8.17965 23.7316 8.50049 23.7745L8.97411 23.8389C9.05304 24.1419 9.16508 24.4316 9.31022 24.7051L9.00466 25.102C8.80096 25.3675 8.81624 25.7484 9.04286 25.9924L9.49356 26.4779C9.72018 26.7219 10.0818 26.7487 10.3389 26.5395L10.7183 26.2311C10.9857 26.3974 11.2683 26.5288 11.5637 26.62L11.6146 27.1349C11.6477 27.4728 11.9176 27.733 12.241 27.7357L12.8853 27.7437C13.2086 27.7491 13.4836 27.497 13.5244 27.159L13.583 26.6709C13.9038 26.5878 14.2094 26.4618 14.4996 26.2955L14.8561 26.5985C15.1082 26.8131 15.4698 26.797 15.7015 26.5583L16.1624 26.0836C16.3941 25.845 16.4196 25.4641 16.221 25.1932L15.9485 24.8204C16.1166 24.5254 16.2464 24.2116 16.3356 23.8818L16.7583 23.8362C17.0791 23.8013 17.3261 23.517 17.3286 23.1764L17.3362 22.4979C17.3414 22.1573 17.102 21.8676 16.7812 21.8247L16.3686 21.7684C16.2897 21.4412 16.1726 21.1248 16.0198 20.8298L16.277 20.4972C16.4807 20.2317 16.4654 19.8509 16.2388 19.6068L15.7881 19.1214C15.5615 18.8773 15.1999 18.8505 14.9427 19.0597L14.6346 19.3091C14.3443 19.1241 14.0337 18.9819 13.7078 18.8854L13.667 18.4643C13.6339 18.1264 13.364 17.8663 13.0406 17.8636L12.3964 17.8556C12.073 17.8502 11.798 18.1023 11.7572 18.4402L11.7063 18.8586C11.3702 18.9498 11.0468 19.0892 10.7464 19.2716L10.4281 18.998C10.176 18.7835 9.81437 18.7996 9.58265 19.0383L9.11922 19.5157C8.88751 19.7543 8.86204 20.1352 9.06066 20.406L9.33311 20.7761C9.17524 21.0712 9.05047 21.3849 8.96899 21.7148L8.52341 21.7631ZM12.6816 20.996C13.6059 21.0068 14.3468 21.8087 14.3367 22.7822C14.3265 23.7557 13.5651 24.5361 12.6408 24.5254C11.7165 24.5147 10.9755 23.7128 10.9857 22.7393C10.9959 21.7658 11.7572 20.9853 12.6816 20.996Z"></path></svg>

     Профиль
     </div></li> <!----></ul> <button data-v-2647b5ad="" type="button" class="close-mobile">
     x
     </button></div> <!----> <div data-v-2647b5ad="" class="support-service"><p data-v-2647b5ad="" class="h4">
     Служба поддержки
     </p> <!----> <a data-v-2647b5ad="" href="tel:8 800 302 89 04" class="support-service__phone">
     8 800 302 89 04
     </a> <a data-v-2647b5ad="" href="mailto:info@gasworkers.ru" class="link-dark-blue">
     info@gasworkers.ru
     </a></div></div> <div data-v-2647b5ad="" class="content"><main data-v-2647b5ad=""><div data-v-2647b5ad="" class="page-content"><div data-v-fa438d6c="" data-v-2647b5ad="" class="w-100 d-flex justify-content-center flex-wrap"><div data-v-fa438d6c="" class="w-100"><!----></div> <!----> <!----></div> <!----> <div data-v-94c7e54a="" data-v-2647b5ad="" class="profile-page"><div data-v-94c7e54a="" class="section"><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div><div data-v-94c7e54a=""><!----></div> <div data-v-94c7e54a="" class="w-100 mb-32"></div> <div data-v-3f344674="" data-v-94c7e54a="" class="profile-card"><div data-v-3f344674="" class="left"><div data-v-3f344674="" class="header"><div data-v-3f344674="" class="title">Шингелевич Игорь Сергеевич</div> <div data-v-3f344674="" class="status"><!----></div></div> <div data-v-3f344674="" class="content"><div data-v-3f344674="" class="since-date">Зарегистрирован с 15 ноября 2022 года</div></div> <div data-v-3f344674="" class="footer"><div data-v-3f344674="" class="rating"><span data-v-3f344674="" class="rating-badge">5.00</span></div> <div data-v-3f344674="" class="reviews">
     0 отзывов
     </div></div></div> <div data-v-3f344674="" class="right"><div data-v-3f344674="" class="profile-image"><span data-v-3f344674="" class="letter">Ш</span> <img data-v-3f344674="" alt="Шингелевич Игорь Сергеевич" src="https://gasworkers.storage.yandexcloud.net/files/VD5vMoIyctbItWx7U6ysiT1f0aPTlvsmgJwQBsry.jpg"></div></div></div> <!----> <!----></div> <div data-v-94c7e54a="" class="section"><div data-v-3c0f5dec="" data-v-94c7e54a="" class="card-wrapper bg-white"><div data-v-3c0f5dec="" class="header"><div data-v-3c0f5dec="" class="title d-flex justify-content-between"><h3 data-v-3c0f5dec="">Информация о последнем заказе</h3> <div data-v-3c0f5dec=""></div> <!----></div></div> <div data-v-3c0f5dec="" class="content"> <div data-v-dd7802ac="" data-v-94c7e54a="" data-v-3c0f5dec=""><div data-v-5fbb1f88="" data-v-dd7802ac="" class="section order"><!----> <div data-v-5fbb1f88="" class="section__row row"><div data-v-5fbb1f88="" class="col-10"><p data-v-5fbb1f88="" class="h5 link-blue text-primary pointer">
     Заказ №45/22/077/001940
     </p></div> <div data-v-5fbb1f88="" class="col-2"><div data-v-5fbb1f88="" class="actions"><button data-v-5fbb1f88="" type="button" class="actions__btn"><span data-v-5fbb1f88=""></span> <span data-v-5fbb1f88=""></span> <span data-v-5fbb1f88=""></span></button> <div data-v-5fbb1f88="" class="actions__slot right"><a data-v-5fbb1f88="" href="/profile/client/orders/1940" class="actions__slot--link">
     Открыть
     </a> <!----> <!----></div></div></div></div> <div data-v-5fbb1f88="" class="section__row row"><div data-v-5fbb1f88="" class="col-md-5"><p data-v-5fbb1f88="" class="h6">Тип заказа</p></div> <div data-v-5fbb1f88="" class="col-md-7"><p data-v-5fbb1f88="" class="text">Ремонт</p></div></div> <div data-v-5fbb1f88="" class="section__row row"><div data-v-5fbb1f88="" class="col-md-5"><p data-v-5fbb1f88="" class="h6">
     Адрес объекта:
     </p></div> <div data-v-5fbb1f88="" class="col-md-7"><p data-v-5fbb1f88="" class="text">Россия, Московская область, Пушкино, микрорайон Чистые Пруды</p></div></div> <div data-v-5fbb1f88="" class="section__row row"><div data-v-5fbb1f88="" class="col-md-5"><p data-v-5fbb1f88="" class="h6">
     Оборудование
     </p></div> <div data-v-5fbb1f88="" class="col-md-7"><div data-v-5fbb1f88="" class="text">
     Газовый котел Chaffoteaux Talia Green Evo System HP 150
     <!----></div></div></div> <hr data-v-5fbb1f88=""> <div data-v-5fbb1f88=""><!----> <!----> <div data-v-5fbb1f88="" class="section__row row"><div data-v-5fbb1f88="" class="col-md-5"><p data-v-5fbb1f88="" class="h6">Дата и время приезда мастера</p></div> <div data-v-5fbb1f88="" class="col-md-7"><p data-v-5fbb1f88="" class="text">
     15/12/2022 11:29:36
     </p></div></div></div> <!----></div></div></div></div></div> <div data-v-94c7e54a="" class="section more-margin"><div data-v-79890a1b="" data-v-94c7e54a="" class="client-objects"><div data-v-79890a1b="" class="wrapper"><div data-v-79890a1b="" class="title">Объекты и оборудование</div> <div data-v-3d1a4f76="" data-v-79890a1b="" dir="ltr" class="slick-slider slick-initialized"><button data-v-21137603="" data-v-3d1a4f76="" type="button" data-role="none" class="slick-arrow slick-prev" style="display: block;">Previous</button><div data-v-3d1a4f76="" class="slick-list"><div data-v-e4caeaf8="" data-v-3d1a4f76="" class="slick-track" style="width: 6016px; opacity: 1; transform: translate3d(-752px, 0px, 0px);"><div data-v-e4caeaf8="" tabindex="-1" data-index="-2" aria-hidden="true" class="slick-slide slick-cloned" style="width: 376px;"><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><div data-v-2558de94="" class="object-image"><img data-v-2558de94="" alt="замок Хогвартс" src="https://gasworkers.storage.yandexcloud.net/files/HK43EZEljGCTsAe34BhuMtk2SNc0F9RGZjxOx5PW.jpg"></div></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     замок Хогвартс
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/275" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/275#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Газгольдер Сделай сам 3
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="-1" aria-hidden="true" class="slick-slide slick-cloned" style="width: 376px;"><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><!----></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     названиеВашегоОбъекта_3
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/270" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/270#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Плита газовая Centek CT-1522
     </div><div data-v-2558de94="" class="equipment-item">
     Плита газовая Centek CT-1521
     </div><div data-v-2558de94="" class="equipment-item">
     Газовый конвектор BaltGaz 11111111111111111111111111111111111111111111111111
     </div><div data-v-2558de94="" class="equipment-item">
     Проточный ёмкостный газовый водонагреватель Gorenje 1111111111111111111111111111111111111111111111111111111111111111111111111111111111
     </div><div data-v-2558de94="" class="equipment-item">
     234 234
     </div><div data-v-2558de94="" class="equipment-item">
     1 1
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="0" aria-hidden="false" class="slick-slide slick-active slick-current" style="outline: none; width: 376px; --darkreader-inline-outline: initial;" data-darkreader-inline-outline=""><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><div data-v-2558de94="" class="object-image"><img data-v-2558de94="" alt="Дом3_Клиента" src="https://gasworkers.storage.yandexcloud.net/files/Vo5qsLLzMxTmXYlST0vqGlzketebC8xAyNIJftEv.png"></div></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     Дом3_Клиента
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/299" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/299#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Газовый котел Chaffoteaux Talia Green Evo System HP 150
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Пушкино, микрорайон Чистые Пруды</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="1" aria-hidden="false" class="slick-slide slick-active" style="outline: none; width: 376px; --darkreader-inline-outline: initial;" data-darkreader-inline-outline=""><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><div data-v-2558de94="" class="object-image"><img data-v-2558de94="" alt="Объект1_Клиента1 комплекс Люберцы Парк" src="https://gasworkers.storage.yandexcloud.net/files/NMJk7SrLEqrMZTVTKYitdGZDBSh0ZAdZYDRuWNjc.png"></div></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     Объект1_Клиента1 комплекс Люберцы Парк
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/289" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/289#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Газовый котел Chaffoteaux Pigma Ultra 25 FF
     </div><div data-v-2558de94="" class="equipment-item">
     Плита газовая BEON BN-554
     </div><div data-v-2558de94="" class="equipment-item">
     11111111111111111111111111111111111111111111111111111111111111111111111
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, жилой комплекс Люберцы Парк</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="2" aria-hidden="true" class="slick-slide" style="outline: none; width: 376px; --darkreader-inline-outline: initial;" data-darkreader-inline-outline=""><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><!----></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     Хогвартс
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/287" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/287#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </div><div data-v-2558de94="" class="equipment-item">
     Плита газовая Darina NGM 811-01
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="3" aria-hidden="true" class="slick-slide" style="outline: none; width: 376px; --darkreader-inline-outline: initial;" data-darkreader-inline-outline=""><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><!----></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     234
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/279" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/279#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <!----> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="4" aria-hidden="true" class="slick-slide" style="outline: none; width: 376px; --darkreader-inline-outline: initial;" data-darkreader-inline-outline=""><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><!----></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     123
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/276" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/276#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <!----> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="5" aria-hidden="true" class="slick-slide" style="outline: none; width: 376px; --darkreader-inline-outline: initial;" data-darkreader-inline-outline=""><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><div data-v-2558de94="" class="object-image"><img data-v-2558de94="" alt="замок Хогвартс" src="https://gasworkers.storage.yandexcloud.net/files/HK43EZEljGCTsAe34BhuMtk2SNc0F9RGZjxOx5PW.jpg"></div></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     замок Хогвартс
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/275" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/275#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Газгольдер Сделай сам 3
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="6" aria-hidden="true" class="slick-slide" style="outline: none; width: 376px; --darkreader-inline-outline: initial;" data-darkreader-inline-outline=""><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><!----></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     названиеВашегоОбъекта_3
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/270" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/270#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Плита газовая Centek CT-1522
     </div><div data-v-2558de94="" class="equipment-item">
     Плита газовая Centek CT-1521
     </div><div data-v-2558de94="" class="equipment-item">
     Газовый конвектор BaltGaz 11111111111111111111111111111111111111111111111111
     </div><div data-v-2558de94="" class="equipment-item">
     Проточный ёмкостный газовый водонагреватель Gorenje 1111111111111111111111111111111111111111111111111111111111111111111111111111111111
     </div><div data-v-2558de94="" class="equipment-item">
     234 234
     </div><div data-v-2558de94="" class="equipment-item">
     1 1
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="7" aria-hidden="true" class="slick-slide slick-cloned" style="width: 376px;"><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><div data-v-2558de94="" class="object-image"><img data-v-2558de94="" alt="Дом3_Клиента" src="https://gasworkers.storage.yandexcloud.net/files/Vo5qsLLzMxTmXYlST0vqGlzketebC8xAyNIJftEv.png"></div></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     Дом3_Клиента
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/299" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/299#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Газовый котел Chaffoteaux Talia Green Evo System HP 150
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Пушкино, микрорайон Чистые Пруды</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="8" aria-hidden="true" class="slick-slide slick-cloned" style="width: 376px;"><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><div data-v-2558de94="" class="object-image"><img data-v-2558de94="" alt="Объект1_Клиента1 комплекс Люберцы Парк" src="https://gasworkers.storage.yandexcloud.net/files/NMJk7SrLEqrMZTVTKYitdGZDBSh0ZAdZYDRuWNjc.png"></div></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     Объект1_Клиента1 комплекс Люберцы Парк
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/289" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/289#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Газовый котел Chaffoteaux Pigma Ultra 25 FF
     </div><div data-v-2558de94="" class="equipment-item">
     Плита газовая BEON BN-554
     </div><div data-v-2558de94="" class="equipment-item">
     11111111111111111111111111111111111111111111111111111111111111111111111
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, жилой комплекс Люберцы Парк</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="9" aria-hidden="true" class="slick-slide slick-cloned" style="width: 376px;"><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><!----></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     Хогвартс
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/287" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/287#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </div><div data-v-2558de94="" class="equipment-item">
     Плита газовая Darina NGM 811-01
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="10" aria-hidden="true" class="slick-slide slick-cloned" style="width: 376px;"><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><!----></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     234
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/279" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/279#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <!----> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="11" aria-hidden="true" class="slick-slide slick-cloned" style="width: 376px;"><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><!----></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     123
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/276" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/276#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <!----> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="12" aria-hidden="true" class="slick-slide slick-cloned" style="width: 376px;"><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><div data-v-2558de94="" class="object-image"><img data-v-2558de94="" alt="замок Хогвартс" src="https://gasworkers.storage.yandexcloud.net/files/HK43EZEljGCTsAe34BhuMtk2SNc0F9RGZjxOx5PW.jpg"></div></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     замок Хогвартс
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/275" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/275#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Газгольдер Сделай сам 3
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div></div><div data-v-e4caeaf8="" tabindex="-1" data-index="13" aria-hidden="true" class="slick-slide slick-cloned" style="width: 376px;"><div data-v-e4caeaf8=""><div data-v-2558de94="" data-v-79890a1b="" class="object" tabindex="-1" data-v-e4caeaf8="" style="width: 100%; display: inline-block;"><div data-v-2558de94="" class="header"><div data-v-2558de94="" class="image"><!----></div> <div data-v-2558de94="" class="title link-blue text-primary pointer">
     названиеВашегоОбъекта_3
     </div> <div data-v-2558de94="" class="actions"><button data-v-2558de94="" type="button" class="actions__btn"><span data-v-2558de94=""></span> <span data-v-2558de94=""></span> <span data-v-2558de94=""></span></button> <div data-v-2558de94="" class="actions__slot right"><a data-v-2558de94="" href="/equipment/270" class="actions__slot--link">
     Перейти
     </a> <a data-v-2558de94="" href="/equipment/270#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-2558de94=""> <button data-v-2558de94="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-2558de94="" class="content"><div data-v-2558de94="" class="title">Оборудование:</div> <div data-v-2558de94="" class="equipment scrollbar"><div data-v-2558de94="" class="equipment-item">
     Плита газовая Centek CT-1522
     </div><div data-v-2558de94="" class="equipment-item">
     Плита газовая Centek CT-1521
     </div><div data-v-2558de94="" class="equipment-item">
     Газовый конвектор BaltGaz 11111111111111111111111111111111111111111111111111
     </div><div data-v-2558de94="" class="equipment-item">
     Проточный ёмкостный газовый водонагреватель Gorenje 1111111111111111111111111111111111111111111111111111111111111111111111111111111111
     </div><div data-v-2558de94="" class="equipment-item">
     234 234
     </div><div data-v-2558de94="" class="equipment-item">
     1 1
     </div></div></div> <div data-v-2558de94="" class="address"><div data-v-2558de94="" class="title">Адрес объекта:</div> <div data-v-2558de94="" class="address-string">Россия, Московская область, Люберцы</div></div></div></div></div></div></div><button data-v-21137603="" data-v-3d1a4f76="" type="button" data-role="none" class="slick-arrow slick-next" style="display: block;">Next</button><ul data-v-3d1a4f76="" class="slick-dots" style="display: block;"><li class="slick-active"><button>1</button></li><li class=""><button>2</button></li><li class=""><button>3</button></li><li class=""><button>4</button></li></ul></div></div></div></div></div></div></main></div></div> <footer data-v-da6a444e="" data-v-2647b5ad="" class="container-lg"><hr data-v-da6a444e=""> <div data-v-da6a444e="" class="container"><div data-v-da6a444e="" class="gas-footer"><div data-v-da6a444e="" class="row"><div data-v-da6a444e="" class="col-lg-5 col-md-12"><p data-v-da6a444e="" class="gas-footer__gray">
     © 2022 Gasworkers group.
     Все права защищены
     </p></div> <div data-v-da6a444e="" class="col-lg-5 col-md-12"><p data-v-da6a444e="" class="gas-footer__text"><span data-v-da6a444e="">
     Служба поддержки
     </span> <a data-v-da6a444e="" href="tel:8 800 302 89 04">8 800 302 89 04</a></p></div></div></div></div></footer> <!----></div></div></div><script>window.__NUXT__=(function(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z){m.title="Gasworkers - Главная";m.description="длжьтпыввфтльждпрфываждтльрафывтлджравыфждлот";m.image=a;return {layout:"primary",data:[{}],fetch:{"HomePage:0":{seo:m,blog:{title:d,slug:n,preview_image:a,description:o,image_alt:d,image_title:d,preview_text:p,image:a,seo_description:d,seo_keyword:d,seo_title:d,is_taggable:q}},"data-v-298625bd:0":{},"data-v-1a3d5e46:0":{linkCollapse:e,blog:{title:d,slug:n,preview_image:a,description:o,image_alt:d,image_title:d,preview_text:p,image:a,seo_description:d,seo_keyword:d,seo_title:d,is_taggable:q}},"data-v-c977c362:0":{},"data-v-018824b2:0":{settings:{arrows:g,autoplay:e,dots:g,draggable:e,infinite:g,slidesToScroll:c,slidesToShow:h,touchMove:g,spaceBetween:30,responsive:[{breakpoint:r,settings:{slidesToShow:f,slidesToScroll:f,initialSlide:f}},{breakpoint:575,settings:{slidesToShow:c,slidesToScroll:c}}]}},"data-v-13ec67ee:0":{},"NewsBlock:0":{},"data-v-55751f42:0":{settings:{arrows:e,autoplay:g,autoplaySpeed:3000,dots:e,draggable:e,infinite:g,slidesToScroll:c,slidesToShow:i,touchMove:g,responsive:[{breakpoint:1500,settings:{slidesToShow:i,slidesToScroll:c}},{breakpoint:1199,settings:{slidesToShow:j,slidesToScroll:c}},{breakpoint:r,settings:{slidesToShow:h,slidesToScroll:c}},{breakpoint:767,settings:{slidesToShow:f,slidesToScroll:c}}]}}},error:a,state:{"add-equipment":{form:{company:a,filial:a,facial_account:s,object:{title:s,images:[]},address_id:a},types:[],brands:[],brandList:[],models:[],lines:[],clientObjectImages:[]},"app-state":{isLoading:e,profileEditTab:"common"},chats:{paginatedChats:[],chats:[],supportChat:a,currentChat:a,messages:[],pagination:a},clientObjects:{objects:[]},companies:{priceLists:[],distributionAgreements:[],companyBrands:[],companyCertificates:[],accountants:[],dispatchers:[],filteredPriceLists:[],masters:[],currentCompany:a,currentCompanyMasters:[]},countries:{list:[]},distributions:{list:[]},equipments:{list:[],forPriceList:[],userPriceRanges:[]},errors:{validations:{}},"gas-auth":{},"invitation-list":{list:[]},masterEquipment:{equipmentList:[],equipments:[],equipmentPagination:{}},masters:{currentMaster:a},notifications:{list:{main:[],sidebar:[],header:[]}},orders:{forMap:[],currentOrder:a,orderForMaster:a,orderList:[],currentMasterOrder:a,lastCompletedMasterOrder:a,masterOrders:[],clientOrders:[],selectedDay:a,brandFilter:e,priceFilterTo:a,priceFilterFrom:a},payments:{payments:[]},resumes:{list:[],invitationList:[],resume:a},reviews:{reviews:[],userType:a,userFullName:a,userId:a},settings:{},subscriptions:{},tools:{currentCity:"Москва",companySlide:[{id:c,photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FNhcYOHYHQGlBeJS6NhqeqROlJDlSRwn89cwo5Jv1.png",alt_text:a,title_text:a,sort_order:b},{id:f,photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FrsDFXaLuZmWF5YtGPVS7d331pJdJ0s0vSjGMaTQ0.png",alt_text:a,title_text:a,sort_order:b},{id:h,photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FPkM0o31ThpVR2ZLBVxObG6B8Yv3bjMGPdjgT3Y0Z.png",alt_text:a,title_text:a,sort_order:b},{id:j,photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FMMxVEe9SF5y7dWPkDXuezkuk8IuGKsEWyrQhSZeH.jpg",alt_text:a,title_text:a,sort_order:b},{id:i,photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FnDzTGts9M25mQbzEi9M37XlnpoR5gsDyxeVV0M8Z.jpg",alt_text:a,title_text:a,sort_order:b},{id:t,photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FxpX4q8d8qU8xI4SuHGW1DnnEsMX5mnI9Nj41JT4x.jpg",alt_text:a,title_text:a,sort_order:b}],orderStep:[{id:c,title:"Регистрация и подача заявки",description:"Просто укажите свой номер телефона или email и опишите проблему с оборудованием.",sort_order:b},{id:f,title:"Описание проблемы и подбор сервисной компании",description:"Заполните необходимые поля и дождитесь предложений сервисных компаний.",sort_order:b},{id:h,title:"Назначение мастера",description:"Подпишите договор и оплатите выезд мастера.",sort_order:b},{id:j,title:"Акты и гарантия",description:"Примите выполненные работы, произведите окончательный расчет и оставьте отзыв.",sort_order:b}],homeReview:[{id:c,name:"Мирослава Уварова",number_points:k,number_reviews:l,who_left:u,text_review:"Утром проснулась и почувствовала, что в доме холодно. Когда зашла в котельную увидела, что газовый настенный котёл не работает. На часах пять утра, куда звонить,",address:"д. Фирсановка",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FerdBmjnw9fVfZrpk24LxQOHTEVhwGgg30zTKdW2A.jpg",sort_order:b,created_at:"Thu, 02 Jun 2022 13:11:46 +0000"},{id:f,name:"Юрий Швец",number_points:k,number_reviews:"2",who_left:u,text_review:"Хотел для начала понять цены, прежде, чем заключать договоры с компаниями. Везде свои нюансы. Либо дорого, либо вообще не работают с моим оборудованием, либо",address:"Голицино",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FrsQvzaOb6p6c9nAui1EEY7yunm2S70SXXJJY2Rbg.jpg",sort_order:b,created_at:"Thu, 02 Jun 2022 13:14:41 +0000"},{id:h,name:v,number_points:"4,5",number_reviews:l,who_left:"Петров Иван",text_review:"Мастер приехал в оговоренное время, все сделал, как нужно, тщательно проверил работу и провел замеры. За что и получил залуженную пятерку. Отдельно хочу",address:"СНТ Счастье, Серпухов",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FELGz8OCOTTzyxBTM9YbItwWsgSn67caRR93dzP3D.jpg",sort_order:b,created_at:"Thu, 02 Jun 2022 13:16:03 +0000"},{id:j,name:"Марина Морозова",number_points:k,number_reviews:"3",who_left:w,text_review:"Александр Иванович, спасибо Вам большое! Все четко, быстро, а главное - основательно. Профессиональный мастер, который знает свое дело и при этом работает чисто и аккуратно. Спасибо, что проинформировали насчет документации и дальнейших действий.",address:"Воскресенск",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002Fkn4uPTqHPVrFlUlDWjwH0Xo7OSzxlHVJkP6NsIgp.jpg",sort_order:b,created_at:"Fri, 02 Sep 2022 09:10:07 +0000"},{id:i,name:"Михаил Васьковский",number_points:k,number_reviews:l,who_left:w,text_review:"Спасибо Александру Ивановичу за проделанную работу.  Мастер тщательно осмотрел все оборудование, дал пару дельных советов по уходу за котлом и плитой. Мы остались очень довольны. И удобно  то, что все документы по ТО хранятся в личном кабинете.",address:"Одинцово",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FGOqatcyY9d33iYwpkX5bCH1lG7lwRfNkCUc3Ohaf.jpg",sort_order:b,created_at:"Fri, 02 Sep 2022 09:11:25 +0000"},{id:t,name:v,number_points:k,number_reviews:l,who_left:"Ковальский Николай",text_review:"Мастер приехал в оговоренное время, все сделал, как нужно, тщательно проверил работу и провел замеры. За что и получил заслуженную пятерку. Отдельно хочу отметить, что сам процесс регистрации оборудования удобный и продуманный. Мастер еще до прихода на объект точно знал, с чем ему предстоит работать. Очень здорово.",address:"Балашиха",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FFUtRLzz3aXMzgjdrcN1r8Y3EDwm8c90eTHhwbaMs.jpg",sort_order:b,created_at:"Fri, 02 Sep 2022 09:12:33 +0000"}],numberIndicator:[{id:c,number:"2902",description:"Довольных клиентов",sort_order:b},{id:f,number:"65",description:"Мастеров в базе Gasworkers",sort_order:b},{id:h,number:"350",description:"Отзывов на сервисе",sort_order:b}],homeDoc:[],news:[{id:j,slug:"gasworkers-pervym-v-rossii-nacal-straxovat-domasnee-gazovoe-oborudovanie",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FQxsnqWwI6WANk54xJzLtBbQJe4mP5cj2t1qdbVtE.jpg",description:"Gasworkers первым в России начал страховать домашнее газовое оборудование",preview_text:"Внутридомовое газовое оборудование частного дома теперь можно застраховать.",full:"\u003Cdiv\u003EВнутридомовое газовое оборудование частного дома теперь можно застраховать. Услуга доступна всем пользователям Gasworkers, которые оформляют договор технического обслуживания на платформе. Страхование газового оборудование позволит получить более выгодную цену на договор технического обслуживания. Объясняется такое ценообразование очень просто: выезд мастера, который включен в договор всегда стоит на порядок дороже самой страховки.&nbsp;\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EНемало важно и то, что все взаимодействие со&nbsp; страховыми компаниями по оформлению страхового возмещения производится автоматически на нашей платформе без Вашего физического участия. Вам не нужно ехать в страховую компанию и доказывать наличие страхового случая. Достаточно снять поломку на видео и отправить его нам на платформе. Возмещение будет производиться&nbsp; в виде фактического ремонта, установки запчастей и выполненных работ.&nbsp;\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EСтрахование газового оборудования – это инструмент для быстрого устранения отдельных неполадок без оплаты. Чтобы оформить договор технического обслуживания со страховкой, зарегистрируйте себя и Ваше оборудование на платформе Gasworkers и оформите заявку в соответствующем разделе личного кабинета.&nbsp;\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003E&nbsp;\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EБелее 15-ти специализированных сервисных компаний Московской области уже присоединились к платформе Gasworkers.&nbsp;\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EМы проводим тщательную проверку каждого подключаемого контрагента. Для нас важно, чтобы компании имели все необходимые разрешительные документы и были внесены в реестр ГЖИ. Компании, зарегистрированные на сервисе, имеют возможность осуществлять ремонтные работы и проводить техническое обслуживание внутридомового газового оборудования клиентов Gasworkers. Полностью автоматизированная система приема и обработки заказов поможет пользователям выбрать компанию-исполнителя по цене, рейтингу и отзывам, что способствует формированию здоровой конкуренции на рынке и исключит любые мошеннические действия, связанные с неправомерным оформлением&nbsp; документации и некачественной работой.&nbsp;\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EЕсли Вы желаете присоединиться к платформе в качестве сервисной компании, Вы можете отправить заявку на почту \u003Ca href=\"mailto:info@gasworkers.ru\"\u003Einfo@gasworkers.ru\u003C\u002Fa\u003E или позвонить по номеру: \u003Ca href=\"tel:8%20800%20302%2089%2004\"\u003E8 800 302 89 04\u003C\u002Fa\u003E&nbsp;\u003C\u002Fdiv\u003E",date:"Thu, 01 Sep 2022 00:00:00 +0000"},{id:i,slug:"kompaniia-gaztex-zapuskaet-onlain-servis-dlia-videokonsultacii-s-masterom",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FG68CH3HqpL8LZlCM122jL5UwZSpNTuNdQJSYmPCY.jpg",description:"Компания ГАЗТЕХ запускает онлайн-сервис для видеоконсультаций с мастером",preview_text:"С сегодняшнего дня на платформе Gasworkers начинает функционировать инновационный сервис онлайн-консультаций.",full:"\u003Cdiv\u003EС сегодняшнего дня на платформе Gasworkers начинает функционировать инновационный сервис онлайн-консультаций. По статистике, около 80% проблем с домашним газовым оборудованием можно решить в дистанционном режиме, следуя подсказкам квалифицированного мастера.\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EБезусловно, речь идет о незначительных поломках, которые можно устранить, не вызывая специалиста на дом. Такие консультации потребуют от Вас подключение к интернету и исправно работающую камеру на смартфоне для того, чтобы мастер по видеосвязи смог подробно осмотреть неисправность и дать полноценное заключение. Если поломка окажется значительной и потребует полноценного ремонта, то Вы здесь же через сервис Gasworkers сможете создать заказ на ремонт. К этому моменту Вы уже будете иметь полноценное представление о том, что именно сломалось и сможете более детально описать проблему в заявке.\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EОформить заказ на онлайн-консультацию с мастером можно прямо сейчас в Вашем личном кабинете на сайте gasworkers.ru.&nbsp;\u003C\u002Fdiv\u003E",date:x},{id:h,slug:"zapusk-servisa-gasworkers",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FdRdenuBIJJjdeSYPPGOAHOjyiOiZ7axoFrHFtuMb.jpg",description:"Запуск сервиса Gasworkers",preview_text:"Компания ГАЗТЕХ объявила о полноценном запуске платформы Gasworkers.\r\n\r\nООО «ГАЗТЕХ» - молодая прогрессивная компания, которая заботится о безопасности и комфорте россиян через внедрение современных технологий в повседневный быт.",full:"\u003Cdiv\u003E\u003Cem\u003EКомпания ГАЗТЕХ объявила о полноценном запуске платформы Gasworkers.\u003Cbr\u003E\u003C\u002Fem\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003E\u003Cem\u003EООО «ГАЗТЕХ» - молодая прогрессивная компания, которая заботится о безопасности и комфорте россиян через внедрение современных технологий в повседневный быт.\u003Cbr\u003E\u003C\u002Fem\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EGasworkers – инновационный сервис для обслуживания домашнего газового оборудования, работа которого построена по принципу биржи. Зарегистрированный пользователь может воспользоваться функциями заказа технического обслуживания и ремонта. Также Gasworkers предлагает новый сервис, не имеющий аналогов на российском рынке – телетехническая консультация, с помощью которой инженер поможет дистанционно&nbsp; устранить мелкие неполадки.&nbsp;\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EПочти год шла подготовка к запуску по-настоящему полезной платформы, избавляющей от лишней бумажной волокиты, штрафов от надзорных органов, а так же от бесконечных поисков «правильных» мастеров и сервисных компаний. Задача Gasworkers -&nbsp; убрать диктат цен газоснабжающих организаций, создать прозрачную и честную конкуренцию за клиента среди сервисных компаний и дать возможность вам самим выбрать своего мастера по его квалификации и цене. Для большего удобства в пользовании платформа дает возможность оставить отзыв и оценить работу сервисной компании и мастера. Эта информация доступна всем пользователям, заказывающим услугу.&nbsp;\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EНаш сервис предельно прост: сломалось, заяви поломку, выбери мастера и прими выполненные работы. Остальное - наша работа.&nbsp;\u003Cbr\u003E\u003Cbr\u003E\u003C\u002Fdiv\u003E\u003Cdiv\u003EВ настоящий момент сервис доступен только для жителей частных домов Московской области. В ближайшей перспективе планируется постепенное&nbsp; подключение и других регионов России.\u003C\u002Fdiv\u003E",date:x},{id:f,slug:"gasworkers-ucastvuet-v-profilnoi-vystavke",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002FE0jQLJZo1RlZHwWssseGpd55Va8ZRWLniJi96OSN.jpg",description:"Gasworkers участвует в профильной выставке",preview_text:"Мы участвуем в выставке и показали всем как надо работать.\r\nУра нам!!!",full:"\u003Cdiv\u003EМы участвуем в выставке и показали всем как надо работать.\u003Cbr\u003EУра нам!!!\u003C\u002Fdiv\u003E",date:y},{id:c,slug:"gazvorkers-nastroili-gaz-v-novom-obieekte",photo:"https:\u002F\u002Fgasworkers.storage.yandexcloud.net\u002Fs33xcTqmoiPfT4lfrTYq9RnauygMuOfVOjGQuAzf.jpg",description:"Газворкерс настроили газ в новом объекте",preview_text:"Мы подключили газ в новом объекте!!\r\nМы подключили газ в новом объекте!!\r\nМы подключили газ в новом объекте!!",full:"\u003Cdiv\u003EМы подключили газ в новом объекте!!\u003Cbr\u003EМы подключили газ в новом объекте!!\u003Cbr\u003EМы подключили газ в новом объекте!!\u003Cbr\u003EМы подключили газ в новом объекте!!\u003Cbr\u003EМы подключили газ в новом объекте!!\u003Cbr\u003EМы подключили газ в новом объекте!!\u003C\u002Fdiv\u003E",date:y}],exchangePage:[],blogList:[],index:m},users:{},utils:{errors:{},urls:{}},i18n:{routeParams:{}},auth:{user:a,loggedIn:e,strategy:"local"}},serverRendered:g,routePath:z,config:{_app:{basePath:z,assetsPath:"\u002F_nuxt\u002F",cdnURL:a}},globalRefs:{},__i18n:{langs:{}}}}(null,999999,1,"Ремонт газового котла",false,2,true,3,5,4,"5","1",{},"remont_gazovogo_kotla","Как и любое другое оборудование, газовые котлы периодически нуждаются в ремонте (обслуживании, если текст про обслуживание) . Это позволяет продлить их срок службы, а также обеспечить безопасное использование. \r\nРемонт газовых котлов может быть как плановым, так и внеплановым при возникновении неисправностей в их работе. В любом случае, выполнить его может только сертифицированный специалист, обладающий необходимым опытом и знаниями.  \r\nЕсть несколько причин заказать ремонт газового котла через сервис Gasworkers:\r\n•\tтолько квалифицированные сервисные службы и мастера, имеющие лицензию на обслуживание и ремонт газового оборудования;\r\n•\tвозможность быстро найти поблизости подходящего мастера по ремонту газовых котлов;\r\n•\tвыполнение работ и их оплата строго по официальному договору, гарантии на ремонтные работы;\r\n•\tотзывчивая служба поддержки, которая поможет решить вопросы, связанные с работой нашей платформы;\r\n•\tэффективное онлайн-взаимодействие с исполнителями, включая заключение договора, прием работ и оплату услуг.\r\nРемонт газовых котлов отопления – обязанность их владельцев, установленная законодательством. Мы поможем быстро найти надежного и опытного исполнителя, который выполнит работы в полном объеме в поставленные сроки. Зарегистрируйтесь на сервисе Gasworkers уже сейчас, чтобы не иметь проблем с газовым оборудованием в будущем.","Как и любое другое оборудование, газовые котлы периодически нуждаются в ремонте (обслуживании, если текст про обслуживание) . Это позволяет продлить их срок службы, а также обеспечить безопасное использование.",0,991,"",6,"Ковальский Николай оставил отзыв","Артем Круковский","мастер Малашин Александр Иванович","Tue, 30 Aug 2022 00:00:00 +0000","Wed, 15 Jun 2022 00:00:00 +0000","\u002F"));</script><script src="/_nuxt/0daa972.js" defer=""></script><script src="/_nuxt/0a9705d.js" defer=""></script><script src="/_nuxt/ab157e8.js" defer=""></script><script src="/_nuxt/a0ba89b.js" defer=""></script><script src="/_nuxt/046d0b3.js" defer=""></script><script src="/_nuxt/16ae785.js" defer=""></script><script src="/_nuxt/a8e7a85.js" defer=""></script>


     <div id="LI7_zALfh" role="status" aria-live="polite" aria-atomic="false"></div><mytubeelement id="myTubeRelayElementToPage" event="preferencesUpdated" data="{&quot;bundle&quot;:{&quot;label_delimitor&quot;:&quot;:&quot;,&quot;percentage&quot;:&quot;%&quot;,&quot;smart_buffer&quot;:&quot;Smart Buffer&quot;,&quot;start_playing_when_buffered&quot;:&quot;Start playing when buffered&quot;,&quot;sound&quot;:&quot;Sound&quot;,&quot;desktop_notification&quot;:&quot;Desktop Notification&quot;,&quot;continuation_on_next_line&quot;:&quot;-&quot;,&quot;loop&quot;:&quot;Loop&quot;,&quot;only_notify&quot;:&quot;Only Notify&quot;,&quot;estimated_time&quot;:&quot;Estimated Time&quot;,&quot;global_preferences&quot;:&quot;Global Preferences&quot;,&quot;no_notification_supported_on_your_browser&quot;:&quot;No notification style supported on your browser version&quot;,&quot;video_buffered&quot;:&quot;Video Buffered&quot;,&quot;buffered&quot;:&quot;Buffered&quot;,&quot;hyphen&quot;:&quot;-&quot;,&quot;buffered_message&quot;:&quot;The video has been buffered as requested and is ready to play.&quot;,&quot;not_supported&quot;:&quot;Not Supported&quot;,&quot;on&quot;:&quot;On&quot;,&quot;off&quot;:&quot;Off&quot;,&quot;click_to_enable_for_this_site&quot;:&quot;Click to enable for this site&quot;,&quot;desktop_notification_denied&quot;:&quot;You have denied permission for desktop notification for this site&quot;,&quot;notification_status_delimitor&quot;:&quot;;&quot;,&quot;error&quot;:&quot;Error&quot;,&quot;adblock_interferance_message&quot;:&quot;Adblock (or similar extension) is known to interfere with SmartVideo. Please add this url to adblock whitelist.&quot;,&quot;calculating&quot;:&quot;Calculating&quot;,&quot;waiting&quot;:&quot;Waiting&quot;,&quot;will_start_buffering_when_initialized&quot;:&quot;Will start buffering when initialized&quot;,&quot;will_start_playing_when_initialized&quot;:&quot;Will start playing when initialized&quot;,&quot;completed&quot;:&quot;Completed&quot;,&quot;buffering_stalled&quot;:&quot;Buffering is stalled. Will stop.&quot;,&quot;stopped&quot;:&quot;Stopped&quot;,&quot;hr&quot;:&quot;Hr&quot;,&quot;min&quot;:&quot;Min&quot;,&quot;sec&quot;:&quot;Sec&quot;,&quot;any_moment&quot;:&quot;Any Moment&quot;,&quot;popup_donate_to&quot;:&quot;Donate to&quot;,&quot;extension_id&quot;:&quot;lnkdbjbjpnpjeciipoaflmpcddinpjjp&quot;},&quot;prefs&quot;:{&quot;desktopNotification&quot;:true,&quot;soundNotification&quot;:true,&quot;logLevel&quot;:0,&quot;enable&quot;:true,&quot;loop&quot;:false,&quot;hidePopup&quot;:false,&quot;autoPlay&quot;:false,&quot;autoBuffer&quot;:false,&quot;autoPlayOnBuffer&quot;:false,&quot;autoPlayOnBufferPercentage&quot;:42,&quot;autoPlayOnSmartBuffer&quot;:true,&quot;quality&quot;:&quot;default&quot;,&quot;fshd&quot;:true,&quot;onlyNotification&quot;:false,&quot;enableFullScreen&quot;:false,&quot;saveBandwidth&quot;:false,&quot;hideAnnotations&quot;:false,&quot;turnOffPagedBuffering&quot;:false}}"></mytubeelement><mytubeelement id="myTubeRelayElementToTab" event="relayPrefs" data="{&quot;loadBundle&quot;:true}"></mytubeelement><div class="mallbery-caa" style="z-index: 2147483647 !important; text-transform: none !important; position: fixed;"></div><div id="jivo-iframe-container" style="opacity: 0; visibility: hidden; width: 0px; height: 0px; overflow: hidden;"><iframe src="" role="presentation" allow="autoplay" title="Jivochat" name="jivo_container" id="jivo_container" frameborder="no"></iframe></div><jdiv style="display: none;"><jdiv class="globalClass_ec9a"><jdiv class="wrap_b9ad _orientationRight_bcba _hoverMenu_dd21 __jivoDesktopButton"><jdiv class="button_a11d" style="background: linear-gradient(95deg, rgb(0, 85, 201) 20%, rgb(24, 185, 212) 80%); --darkreader-inline-bgimage:linear-gradient(95deg, #124283 20%, #328c9c 80%); --darkreader-inline-bgcolor: initial;" data-darkreader-inline-bgimage="" data-darkreader-inline-bgcolor=""><jdiv class="iconWrap_ea30 _showLogo_ff19"><jdiv class="jivoIcon_d62a icons_bd15" style="background-image: url(&quot;data:image/svg+xml,%3Csvg%20width%3D%2210%22%20height%3D%2235%22%20viewBox%3D%220%200%209%2035%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%3Cpath%20fill%3D%22%23fff%22%20d%3D%22M1.31%2034.966H.975c-.295%200-.53-.09-.71-.27C.09%2034.516%200%2034.3%200%2034.05v-2.33c0-.25.097-.465.288-.645.193-.18.42-.27.687-.27H1.3c1.776%200%203.223-1.37%203.238-3.06v-8.94H.975c-.236%200-.458-.09-.665-.27-.206-.18-.31-.395-.31-.645v-2.33c0-.223.104-.43.31-.625.207-.194.43-.292.665-.292h7.019c.266%200%20.494.09.687.27.192.182.29.396.29.646V27.77c0%20.032-.008.058-.01.09-.08%203.937-3.477%207.098-7.65%207.104zM.318%200C-.25%2011.117%208.62%2012.702%208.62%2012.702%208.17.522.318%200%20.318%200z%22%2F%3E%3C%2Fsvg%3E%0A&quot;);"></jdiv><jdiv class="envelopeIcon_ce7a icons_bd15" style="background-image: url(&quot;data:image/svg+xml,%3Csvg%20width%3D%2225%22%20height%3D%2220%22%20viewBox%3D%220%200%2025%2020%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%3Cpath%20fill%3D'%23fff'%20d%3D%22M22.5%205l-10%206.25L2.5%205V2.5l10%206.25%2010-6.25V5zm0-5h-20A2.49%202.49%200%200%200%200%202.5v15A2.5%202.5%200%200%200%202.5%2020h20a2.5%202.5%200%200%200%202.5-2.5v-15A2.5%202.5%200%200%200%2022.5%200z%22%2F%3E%3C%2Fsvg%3E%0A&quot;);"></jdiv></jdiv></jdiv><jdiv class="menuWrap_e98a __hover_d36d"></jdiv></jdiv><jdiv id="jivo-player" class="player_edc3"><audio preload="auto" id="jivo-sound-agent_message"><source src="https://code.jivo.ru/sounds/agent_message.mp3" type="audio/mpeg"><source src="https://code.jivo.ru/sounds/agent_message.ogg" type="audio/ogg; codecs=vorbis"><source src="https://code.jivo.ru/sounds/agent_message.wav" type="audio/wav"></audio><audio preload="auto" id="jivo-sound-notification"><source src="https://code.jivo.ru/sounds/notification.mp3" type="audio/mpeg"><source src="https://code.jivo.ru/sounds/notification.ogg" type="audio/ogg; codecs=vorbis"><source src="https://code.jivo.ru/sounds/notification.wav" type="audio/wav"></audio><audio preload="auto" id="jivo-sound-outgoing_message"><source src="https://code.jivo.ru/sounds/outgoing_message.mp3" type="audio/mpeg"><source src="https://code.jivo.ru/sounds/outgoing_message.ogg" type="audio/ogg; codecs=vorbis"><source src="https://code.jivo.ru/sounds/outgoing_message.wav" type="audio/wav"></audio></jdiv><jdiv id="jcont" style="animation: 300ms cubic-bezier(0.39, 0.24, 0.21, 0.99) 0s 1 normal both running WidgetContainer_CLOSE_WIDGET_f8ac; --jright:30px; --jheight:496px; display: block; position: fixed;"><jdiv class="wrap_e4d0" dir="ltr" id="jivo_action"><jdiv class="closeButton_d0f7" id="jivo_close_button"><jdiv class="closeIcon_b81f" style="background-image: url(&quot;data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2228%22%20height%3D%2228%22%20viewBox%3D%220%200%2028%2028%22%3E%0A%20%20%20%20%3Cg%20fill%3D%22none%22%20fill-rule%3D%22evenodd%22%20transform%3D%22translate(2%202)%22%3E%0A%20%20%20%20%20%20%20%20%3Ccircle%20cx%3D%2212%22%20cy%3D%2212%22%20r%3D%2212%22%20fill%3D%22%23FFF%22%20opacity%3D%221%22%2F%3E%0A%20%20%20%20%20%20%20%20%3Ccircle%20cx%3D%2212%22%20cy%3D%2212%22%20r%3D%2212.75%22%20stroke%3D%22%23222D38%22%20stroke-width%3D%221.5%22%20opacity%3D%221%22%2F%3E%0A%20%20%20%20%20%20%20%20%3Cg%20fill%3D%22%23222D38%22%20opacity%3D%221%22%20transform%3D%22translate(6%206)%22%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Crect%20width%3D%221.611%22%20height%3D%2213.9%22%20x%3D%225.435%22%20y%3D%22-.941%22%20rx%3D%22.806%22%20transform%3D%22rotate(45%206.24%206.01)%22%2F%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%3Crect%20width%3D%221.611%22%20height%3D%2213.9%22%20x%3D%225.435%22%20y%3D%22-.941%22%20rx%3D%22.806%22%20transform%3D%22scale(-1%201)%20rotate(45%200%20-9.058)%22%2F%3E%0A%20%20%20%20%20%20%20%20%3C%2Fg%3E%0A%20%20%20%20%3C%2Fg%3E%0A%3C%2Fsvg%3E%0A&quot;);"></jdiv></jdiv></jdiv><jdiv class="contentWrapper_e5c4 notranslate"></jdiv></jdiv><jdiv class="pseudoHeight_da8a"></jdiv><jdiv class="jivoMouseTrack_cc9f"></jdiv></jdiv></jdiv><div class="mallbery-caa" style="z-index: 2147483647 !important; text-transform: none !important; position: fixed;"></div><div class="mallbery-caa" style="z-index: 2147483647 !important; text-transform: none !important; position: fixed;"></div><div class="mallbery-caa" style="z-index: 2147483647 !important; text-transform: none !important; position: fixed;"></div></body>
     * */

//new exemplar of pages.components.clientComponents.


   /* public class LastOrderProfileClientComponentSubclass   {

        private final String
                LAST_ORDER_CARD_TITLE = "Информация о последнем заказе",
                LAST_ORDER_CARD_SERVICE_TYPE_TITLE = "Тип услуги",
                LAST_ORDER_CARD_OBJECT_ADDRESS_TITLE = "Адрес объекта",
                LAST_ORDER_CARD_OBJECT_EQUIPMENT_TITLE = "Оборудование",
                LAST_ORDER_CARD_OBJECT_DATE_TITLE = "Выбранная дата",
                LAST_ORDER_CARD_OBJECT_TIME_TITLE = "Выбранное время";


        SelenideElement

                lastOrderCardLocator = $(".section .section order"),
                lastOrderCardTitleLocator = $(".section .header .title d-flex justify-content-between"),
                lastOrderCardOrderNumberLinkLocator = $(".section .content .h5 link-blue text-primary pointer"),
                lastOrderCardActionButtonLocator = $(".section .content .actions .actions__btn"),
                lastOrderCardOrderActionOpenLinkLocator = $(".section .content .actions .actions__slot--link"),
                lastOrderCardServiceTypeTitleCollection = $$(".section__row row").findBy(text(LAST_ORDER_CARD_SERVICE_TYPE_TITLE)),
                lastOrderCardObjectAddressTitleLocator = $$(".section__row row").findBy(text(LAST_ORDER_CARD_OBJECT_ADDRESS_TITLE)),
                lastOrderCardObjectEquipmentTitleLocator = $$(".section__row row").findBy(text(LAST_ORDER_CARD_OBJECT_EQUIPMENT_TITLE)),
                lastOrderCardObjectDateTitleLocator = $$(".section__row row").findBy(text(LAST_ORDER_CARD_OBJECT_DATE_TITLE)),
                lastOrderCardObjectTimeTitleLocator = $$(".section__row row").findBy(text(LAST_ORDER_CARD_OBJECT_TIME_TITLE));



        public LastOrderProfileClientComponentSubclass clickLastOrderNumberLink() {
            lastOrderCardOrderNumberLinkLocator.shouldBe(visible).click();
            return this;
        }

        public LastOrderProfileClientComponentSubclass hoverOverLastOrderActionButton() {
            lastOrderCardActionButtonLocator.hover();
            lastOrderCardOrderActionOpenLinkLocator.shouldBe(visible);
            return this;
        }
        public LastOrderProfileClientComponentSubclass clickLastOrderActionButton() {
            lastOrderCardActionButtonLocator.shouldBe(visible).click();
            return this;
        }



    public LastOrderProfileClientComponent openLastOrder() {
        lastOrderCardActionButtonLocator.shouldBe(visible).click();
        lastOrderCardOrderActionOpenLinkLocator.shouldBe(visible).click();
        return this;
    }





    }*/


    private final String OBJECTS_TITLE = "Объекты и оборудование";
    public LastOrderProfileClientComponent lastOrder = new LastOrderProfileClientComponent();


    String ClientName = "Шингелевич Игорь Сергеевич";

// profile card
    SelenideElement
            profileCardNameLocator = $(".profile-card .title"),
            profileCardSinceDateLocator = $(".profile-card .since-date"),
            profileCardRatingLocator = $(".profile-card.rating-badge"),
            profileCardReviewsLocator = $(".profile-card .reviews"),
            profileCardImageLocator = $(".profile-card").$(".profile-image");

//objects
    SelenideElement objectsTitleLocator = $(".client-objects .title"),
            objectsPreviousButtonLocator = $(".client-objects .slick-arrow.slick-prev"),
            objectsNextButtonLocator = $(".client-objects .slick-arrow slick-next");
            ElementsCollection gotoObjectActionCollection = $$(".actions .actions__slot--link");
//            ElementsCollection actionMenuCollection = $$(".actions .actions__slot right"); //not working - menu collection is not visible
            ElementsCollection actionButtonCollection = $$(".actions .actions__btn");












    //  open
    public ProfileClientPage open() {
        Selenide.open("/profile/client");
        profileCardNameLocator.shouldBe(visible);

        return this;
    }


    public ProfileClientPage isOpened() {
        profileCardNameLocator.shouldBe(visible);
        return this;
    }

    //  profile card

    public ProfileClientPage verifyProfileClientName(String clientName) {
        profileCardNameLocator.shouldHave(text(clientName)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyProfileClientSinceDate(String sinceDate) {
        profileCardSinceDateLocator.shouldHave(text(sinceDate)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyProfileClientRating(String rating) {
        profileCardRatingLocator.shouldHave(text(rating)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyProfileClientReviews(String reviews) {
        profileCardReviewsLocator.shouldHave(text(reviews)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyProfileClientImage(String image) {
        profileCardImageLocator.shouldHave(attribute("src", image)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage clickLastOrderNumberLink() {
        this.lastOrder.clickLastOrderNumberLink();
        return this;
    }


    // last order

/*    public ProfileClientPage verifyLastOrderTitle(String title) {
        lastOrderTitleLocator.shouldHave(text(title)).shouldBe(visible);
        return this;
    }*/

    //  objects

    public ProfileClientPage verifyObjectsTitle(String title) {
        objectsTitleLocator.shouldHave(text(title)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyObjectsPreviousButton(String previousButton) {
        objectsPreviousButtonLocator.shouldBe(visible);
        return this;
    }


    public ProfileClientPage verifyObjectsNextButton(String nextButton) {
        objectsNextButtonLocator.shouldBe(visible);
        return this;
    }

    public ProfileClientPage clickObjectsPreviousButton() {
        objectsPreviousButtonLocator.shouldBe(visible).click();
        return this;
    }

    public ProfileClientPage clickObjectsNextButton() {
        objectsNextButtonLocator.shouldBe(visible).click();
        return this;
    }


    public ProfileClientPage goto1thObjectActionButtonLocator() {
        actionButtonCollection.get(3).hover();
//        actionMenuCollection.get(3).shouldBe(visible); //not working - menu collection is not visible
        actionButtonCollection.get(3).click();
        gotoObjectActionCollection.get(3).click();
        return this;
    }








}
