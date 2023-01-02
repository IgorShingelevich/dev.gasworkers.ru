package pages.components.sharedComponents.headerComponents;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfileHeaderComponent {

    /**<div data-v-af68ad0e="" data-v-7575453c="" id="gas__content-header" class="container"><div data-v-af68ad0e="" class="logo-wrap"><div data-v-af68ad0e="" class="logo"><a data-v-af68ad0e="" href="/" class="nuxt-link-active"><img data-v-af68ad0e="" src="/_ipx/_/images/logo-blue.svg" alt="Gasworkers"> <span data-v-af68ad0e="">Gasworkers</span></a></div></div> <div data-v-af68ad0e="" class="actions-block"><div data-v-af68ad0e="" class="notifications icon"><!----></div> <div data-v-af68ad0e="" class="messages icon"><!----></div> <div data-v-af68ad0e="" class="profile-wrap"><div data-v-af68ad0e="" class="profile icon"></div> <span data-v-af68ad0e="" class="arrow-down"></span> <div data-v-af68ad0e="" class="profile-menu"><div data-v-af68ad0e="" class="profile-menu__link text-primary">Шингелевич Игорь Сергеевич</div> <hr data-v-af68ad0e=""> <a data-v-af68ad0e="" href="/profile/edit" class="profile-menu__link">
     Профиль
     </a> <a data-v-af68ad0e="" href="/profile/reviews" class="profile-menu__link">
     Мои отзывы
     </a> <!----> <hr data-v-af68ad0e=""> <button data-v-af68ad0e="" class="profile-menu__link">
     Выйти <span data-v-af68ad0e="" class="back-ic"></span></button></div></div></div> <div data-v-af68ad0e="" class="btn-block"><!----> <!----> <button data-v-6d08f792="" data-v-af68ad0e="" class="small btn btn-warning disable-outline">
     Создать заказ
     </button> <!----></div> <button data-v-af68ad0e="" type="button" class="burger"></button></div>*/

SelenideElement
            logoLocator = $("#gas__content-header .logo"),
//          $(".logo-wrap .logo"),

            orderButtonLocator = $(".btn-block .small btn btn-warning disable-outline");


    public ProfileHeaderComponent clickLogo() {
        logoLocator.shouldBe(visible).click();
        return this;
    }

    public ProfileHeaderComponent clickOrderButton() {
        orderButtonLocator.shouldBe(visible).click();
        return this;
    }

}
