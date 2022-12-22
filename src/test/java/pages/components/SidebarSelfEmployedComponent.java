package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SidebarSelfEmployedComponent {

    /*<div class="notifications gas-scrollbar-inline mb-32" data-v-2647b5ad=""><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002059
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002058
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002057
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002056
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002055
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002053
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002051
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002049
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002048
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002044
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002038
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002037
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002036
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002035
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002034
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002033
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002032
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002031
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002030
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002029
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002028
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002027
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на Ремонт
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002026
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Заказ отменен
            </div></div> <div class="col-md-12" data-v-a02b3682=""><!----></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002026
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002025
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Создан новый тендер на ТО
            </div></div> <div class="col-md-12" data-v-a02b3682=""><div class="d-flex justify-content-end w-100" data-v-a02b3682=""><a href="#" class="small-button bold link-dark-blue mt-md-1" data-v-a02b3682="">
                Открыть
              </a></div></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002008
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Клиент не выбрал вас в качестве исполнителя заказа
            </div></div> <div class="col-md-12" data-v-a02b3682=""><!----></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002024
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Заказ отменен
            </div></div> <div class="col-md-12" data-v-a02b3682=""><!----></div></div></div></div></div></div><div class="item item" data-v-a02b3682="" data-v-2647b5ad=""><div class="w-100" data-v-a02b3682=""><div class="gas-notice-warning short" data-v-a02b3682=""><div class="item-header d-flex justify-content-between w-100 gas-notice-warning__text" data-v-a02b3682=""><div class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title" data-v-a02b3682=""><span class="ic" data-v-a02b3682=""></span> <div class="d-flex flex-wrap text-break" data-v-a02b3682=""><span class="me-1" data-v-a02b3682="">
                Заказ №
              </span> <span data-v-a02b3682="">
                45/22/077/002023
              </span></div></div> <div class="gas-notice-warning__text--close" data-v-a02b3682=""></div></div> <div class="item-flex w-100" data-v-a02b3682=""><div class="row" data-v-a02b3682=""><div class="col-md-12" data-v-a02b3682=""><div class="text w-100 text-left gas-notice-warning__text" data-v-a02b3682="">
              Заказ отменен
            </div></div> <div class="col-md-12" data-v-a02b3682=""><!----></div></div></div></div></div></div></div>*/
    SelenideElement orderFromNotificationCollection = $$(".item item").filterBy(text("45/22/077/002024")).first();
        //$$(By.xpath("//div[@class='item item']")).get(0);
        //$$("[class^=notifications][class*=gas-scrollbar-inline]").//first();

    //method Selenide element  shold be visible
    public void orderFromNotificationCollectionShouldBeVisible() {
        orderFromNotificationCollection.shouldBe(visible);
    }

}
