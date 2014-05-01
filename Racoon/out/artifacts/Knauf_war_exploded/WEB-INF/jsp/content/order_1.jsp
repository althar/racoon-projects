<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="/js/logic/order.js"></script>
<div class="content layout">

<div class="container section dark-title">

<div class="boxed-group">
<h2>Оформление заказа · Адрес</h2>

<div class="boxed-group-inner">
<p>Товары: ${cart.get('CartSummary').get('FullSum')} теплуноса</p>

<h3>Город доставки</h3>

<form method="post" action="/order/delivery">
<input type="hidden" name="guid" value="${guid}">
<table>
<tbody>
<tr>
    <td>
        <label class="radio">
            <input type="radio" name="area_id" value="1" area_id="1" checked> Москва
        </label>
    </td>
    <td>&nbsp; &nbsp;</td>
    <td>
        <select name="locale[1]" area_id="1" class="address-select">
            <option value="2" selected>Москва в пределах МКАД</option>
            <option value="35634">Зеленоград</option>
        </select>
    </td>
</tr>
<tr>
    <td>
        <label class="radio">
            <input type="radio" name="area_id" value="2" area_id="2"> Санкт-Петербург
        </label>
    </td>
    <td>&nbsp; &nbsp;</td>
    <td>
        <select name="locale[2]" area_id="2" class="address-select">
            <option value="5911">Санкт-Петербург в пределах КАД</option>
            <option value="5920">Александровская</option>
            <option value="5799">Всеволожск</option>
            <option value="5673">Гатчина</option>
            <option value="5928">Колпино</option>
            <option value="5675">Коммунар</option>
            <option value="35627">Красное Село</option>
            <option value="5939">Кронштадт</option>
            <option value="5913">Левашово</option>
            <option value="5937">Лисий Нос</option>
            <option value="5766">Ломоносов</option>
            <option value="5923">Металлострой</option>
            <option value="35629">Ольгино</option>
            <option value="5916">Павловск</option>
            <option value="5914">Парголово</option>
            <option value="5941">Петродворец</option>
            <option value="5915">Пушкин</option>
            <option value="5929">Сестрорецк</option>
        </select>
    </td>
</tr>
<tr>
<td>
    <label class="radio">
        <input type="radio" name="area_id" value="3" area_id="3"> Россия
    </label>
</td>
<td>&nbsp; &nbsp;</td>
<td>
<select name="locale[3]" area_id="3" class="address-select">
<option value="30435">Абакан</option>
<option value="10135">Аксай</option>
<option value="27111">Алапаевск</option>
<option value="7853">Алексин</option>
<option value="18372">Альметьевск</option>
<option value="11320">Анапа</option>
<option value="32401">Ангарск</option>
<option value="5112">Апатиты</option>
<option value="24851">Арзамас</option>
<option value="11116">Армавир</option>
<option value="35060">Артем</option>
<option value="26864">Артемовский</option>
<option value="2495">Архангельск</option>
<option value="17400">Астрахань</option>
<option value="17586">Ахтубинск</option>
<option value="12227">Баксан</option>
<option value="17370">Балаково</option>
<option value="24509">Балахна</option>
<option value="787">Балашиха</option>
<option value="6566">Балтийск</option>
<option value="30544">Барнаул</option>
<option value="10227">Батайск</option>
<option value="9262">Белгород</option>
<option value="38661">Березники</option>
<option value="31219">Бийск</option>
<option value="33468">Благовещенск</option>
<option value="8048">Богородицк</option>
<option value="32579">Бодайбо</option>
<option value="24518">Бор</option>
<option value="7797">Бородинский</option>
<option value="32353">Братск</option>
<option value="6643">Брянск</option>
<option value="23214">Бузулук</option>
<option value="4214">Великий Новгород</option>
<option value="7828">Венев</option>
<option value="27002">Верхняя Пышма</option>
<option value="498">Видное</option>
<option value="32381">Вихоревка</option>
<option value="1331">Вичуга</option>
<option value="34773">Владивосток</option>
<option value="12267">Владикавказ</option>
<option value="23949">Владимир</option>
<option value="15870">Волгоград</option>
<option value="10445">Волгодонск</option>
<option value="16404">Волжский</option>
<option value="1864">Вологда</option>
<option value="3377">Воркута</option>
<option value="14558">Воронеж</option>
<option value="5856">Выборг</option>
<option value="27789">Высокий, Хмао</option>
<option value="3488">Вышний Волочек</option>
<option value="6505">Гвардейск</option>
<option value="11321">Геленджик</option>
<option value="19030">Глазов</option>
<option value="24552">Городец</option>
<option value="11246">Горячий Ключ</option>
<option value="12391">Грозный</option>
<option value="9406">Губкин</option>
<option value="27851">Губкинский</option>
<option value="6515">Гурьевск</option>
<option value="6471">Гусев</option>
<option value="24132">Гусь-Хрустальный</option>
<option value="29156">Далматово</option>
<option value="12919">Дербент</option>
<option value="24339">Дзержинск, Нижегородская Обл.</option>
<option value="14">Дзержинский, Мо</option>
<option value="20250">Димитровград</option>
<option value="319">Дмитров</option>
<option value="317">Долгопрудный</option>
<option value="367">Домодедово</option>
<option value="8018">Донской</option>
<option value="366">Дубна</option>
<option value="11424">Ейск</option>
<option value="26575">Екатеринбург</option>
<option value="34554">Елизово</option>
<option value="22798">Еманжелинск</option>
<option value="11913">Ессентуки</option>
<option value="11905">Железноводск</option>
<option value="31751">Железногорск</option>
<option value="796">Железнодорожный</option>
<option value="45">Жуковский</option>
<option value="20995">Заречный</option>
<option value="6568">Зеленоградск</option>
<option value="10616">Зерноград</option>
<option value="22709">Златоуст</option>
<option value="1185">Иваново</option>
<option value="230">Ивантеевка</option>
<option value="18791">Ижевск</option>
<option value="32105">Иркутск</option>
<option value="22353">Ишимбай</option>
<option value="18559">Йошкар-Ола</option>
<option value="17606">Казань</option>
<option value="6443">Калининград</option>
<option value="7224">Калуга</option>
<option value="26735">Каменск-Уральский</option>
<option value="10648">Каменск-Шахтинский</option>
<option value="16302">Камышин</option>
<option value="12760">Каспийск</option>
<option value="29135">Катайск</option>
<option value="29998">Кемерово</option>
<option value="5339">Кемь</option>
<option value="7994">Кимовск</option>
<option value="3641">Кимры</option>
<option value="5757">Кингисепп</option>
<option value="1442">Кинешма</option>
<option value="7789">Киреевск</option>
<option value="7452">Киров, Калужская Область</option>
<option value="25198">Киров, Кировская Область</option>
<option value="27006">Кировград</option>
<option value="11914">Кисловодск</option>
<option value="412">Климовск</option>
<option value="294">Клин</option>
<option value="27742">Когалым</option>
<option value="79">Коломна</option>
<option value="34342">Комсомольск-На-Амуре</option>
<option value="5269">Кондопога</option>
<option value="22802">Копейск</option>
<option value="22783">Коркино</option>
<option value="188">Королев</option>
<option value="5419">Костомукша</option>
<option value="1477">Кострома</option>
<option value="2918">Котлас</option>
<option value="698">Красногорск</option>
<option value="10687">Краснодар</option>
<option value="31498">Красноярск</option>
<option value="22391">Кумертау</option>
<option value="28776">Курган</option>
<option value="8582">Курск</option>
<option value="32593">Кызыл</option>
<option value="10946">Лабинск</option>
<option value="27787">Лангепас</option>
<option value="27029">Лесной</option>
<option value="15406">Липецк</option>
<option value="318">Лобня</option>
<option value="26475">Лысьва</option>
<option value="4">Люберцы</option>
<option value="7435">Людиново</option>
<option value="34604">Магадан</option>
<option value="22676">Магнитогорск</option>
<option value="13254">Майкоп</option>
<option value="12163">Майский</option>
<option value="6563">Мамоново</option>
<option value="12590">Махачкала</option>
<option value="27788">Мегион</option>
<option value="22649">Мелеуз</option>
<option value="22713">Миасс</option>
<option value="33898">Мирный саха-Якутия</option>
<option value="10981">Мостовской</option>
<option value="27809">Муравленко</option>
<option value="5094">Мурманск</option>
<option value="24315">Муром</option>
<option value="170">Мытищи</option>
<option value="18484">Набережные Челны</option>
<option value="13373">Назрань</option>
<option value="12127">Нальчик</option>
<option value="12199">Нарткала</option>
<option value="3026">Нарьян-Мар</option>
<option value="35086">Находка</option>
<option value="11840">Невинномысск</option>
<option value="27028">Невьянск</option>
<option value="22043">Нефтекамск</option>
<option value="27712">Нефтеюганск</option>
<option value="27768">Нижневартовск</option>
<option value="18408">Нижнекамск</option>
<option value="24336">Нижний Новгород</option>
<option value="26584">Нижний Тагил</option>
<option value="2812">Новодвинск</option>
<option value="30399">Новокузнецк</option>
<option value="21175">Новокуйбышевск</option>
<option value="7980">Новомосковск</option>
<option value="11520">Новороссийск</option>
<option value="27862">Новосибирск</option>
<option value="27005">Новоуральск</option>
<option value="19558">Новочебоксарск</option>
<option value="9991">Новочеркасск</option>
<option value="27793">Новый Уренгой</option>
<option value="445">Ногинск</option>
<option value="31872">Норильск</option>
<option value="27791">Ноябрьск</option>
<option value="27680">Нягань</option>
<option value="7251">Обнинск</option>
<option value="578">Одинцово</option>
<option value="22851">Озерск</option>
<option value="22003">Октябрьский</option>
<option value="5204">Олонец</option>
<option value="29285">Омск</option>
<option value="8125">Орел</option>
<option value="23137">Оренбург</option>
<option value="473">Орехово-Зуево</option>
<option value="23839">Орск</option>
<option value="5488">Отрадное, Ленинградская Обл.</option>
<option value="20481">Пенза</option>
<option value="26642">Первоуральск</option>
<option value="25949">Пермь</option>
<option value="5180">Петрозаводск</option>
<option value="34550">Петропавловск-Камчатский</option>
<option value="3338">Печора</option>
<option value="389">Подольск</option>
<option value="27786">Покачи</option>
<option value="6606">Полесск</option>
<option value="30373">Прокопьевск</option>
<option value="12156">Прохладный</option>
<option value="4593">Псков</option>
<option value="211">Пушкино</option>
<option value="11907">Пятигорск</option>
<option value="27741">Радужный</option>
<option value="15">Раменское</option>
<option value="26859">Реж</option>
<option value="795">Реутов</option>
<option value="927">Ростов</option>
<option value="9796">Ростов-На-Дону</option>
<option value="1155">Рыбинск</option>
<option value="13415">Рязань</option>
<option value="22374">Салават</option>
<option value="27792">Салехард</option>
<option value="20997">Самара</option>
<option value="19560">Саранск</option>
<option value="19182">Сарапул</option>
<option value="16629">Саратов</option>
<option value="24836">Саров</option>
<option value="6581">Светлогорск</option>
<option value="6531">Светлый</option>
<option value="2637">Северодвинск</option>
<option value="232">Сергиев Посад</option>
<option value="27226">Серов</option>
<option value="414">Серпухов</option>
<option value="5943">Смоленск</option>
<option value="22850">Снежинск</option>
<option value="6641">Советск</option>
<option value="7760">Советск, Тульская Обл.</option>
<option value="27702">Советский, Тюменская Область</option>
<option value="514">Совхоз-Комбинат московский</option>
<option value="5784">Сосновый Бор</option>
<option value="3315">Сосногорск</option>
<option value="11533">Сочи</option>
<option value="11555">Ставрополь</option>
<option value="9548">Старый Оскол</option>
<option value="22306">Стерлитамак</option>
<option value="9345">Строитель</option>
<option value="27725">Сургут</option>
<option value="3057">Сыктывкар</option>
<option value="10685">Таганрог</option>
<option value="13962">Тамбов</option>
<option value="3391">Тверь</option>
<option value="21029">Тольятти</option>
<option value="28518">Томск</option>
<option value="5437">Тосно</option>
<option value="22696">Трехгорный</option>
<option value="413">Троицк</option>
<option value="11085">Туапсе</option>
<option value="7643">Тула</option>
<option value="27228">Тюмень</option>
<option value="1059">Углич</option>
<option value="7960">Узловая</option>
<option value="32792">Улан-Удэ</option>
<option value="20024">Ульяновск</option>
<option value="27711">Урай</option>
<option value="34956">Уссурийск</option>
<option value="32571">Усть-Кут</option>
<option value="2463">Устюжна</option>
<option value="21576">Уфа</option>
<option value="3254">Ухта</option>
<option value="34311">Хабаровск</option>
<option value="27638">Ханты-Мансийск</option>
<option value="263">Химки</option>
<option value="262">Хотьково</option>
<option value="10179">Чалтырь</option>
<option value="19198">Чебоксары</option>
<option value="22671">Челябинск</option>
<option value="2386">Череповец</option>
<option value="6488">Черняховск</option>
<option value="429">Чехов</option>
<option value="33086">Чита</option>
<option value="29239">Шадринск</option>
<option value="10021">Шахты</option>
<option value="7787">Щекино</option>
<option value="190">Щелково</option>
<option value="411">Щербинка</option>
<option value="797">Электросталь</option>
<option value="11993">Элиста</option>
<option value="17101">Энгельс</option>
<option value="189">Юбилейный, Московская Область</option>
<option value="35109">Южно-Сахалинск</option>
<option value="33804">Якутск</option>
<option value="6584">Янтарный</option>
<option value="799">Ярославль</option>
<option value="6152">Ярцево</option>
</select>
</td>
</tr>
<tr>
<%--<td>--%>
    <%--<label class="radio">--%>
        <%--<input type="radio" name="area_id" value="4" area_id="4"> За пределы России--%>
    <%--</label>--%>
<%--</td>--%>
<%--<td>&nbsp; &nbsp;</td>--%>
<%--<td>--%>
<%--<select name="locale[4]" area_id="4" class="address-select">--%>
<%--<option value="35277">Австралия</option>--%>
<%--<option value="35280">Австрия</option>--%>
<%--<option value="35291">Азербайджан</option>--%>
<%--<option value="35316">Албания</option>--%>
<%--<option value="35317">Алжир</option>--%>
<%--<option value="35318">Американское Самоа</option>--%>
<%--<option value="35319">Ангилья</option>--%>
<%--<option value="35320">Ангола</option>--%>
<%--<option value="35321">Андорра</option>--%>
<%--<option value="35322">Антигуа и Барбуда</option>--%>
<%--<option value="35276">Аргентина</option>--%>
<%--<option value="35323">Армения</option>--%>
<%--<option value="35324">Аруба</option>--%>
<%--<option value="40664">Афганистан</option>--%>
<%--<option value="35325">Багамы</option>--%>
<%--<option value="35326">Бангладеш</option>--%>
<%--<option value="35327">Барбадос</option>--%>
<%--<option value="35328">Бахрейн</option>--%>
<%--<option value="35292">Беларусь, Республика</option>--%>
<%--<option value="35329">Белиз</option>--%>
<%--<option value="35330">Бельгия</option>--%>
<%--<option value="35331">Бенин</option>--%>
<%--<option value="35332">Бермуды</option>--%>
<%--<option value="35275">Болгария</option>--%>
<%--<option value="35333">Боливия многонациональное государство</option>--%>
<%--<option value="35334">Бонэйр, Синт-Эстатиус и Саба</option>--%>
<%--<option value="35335">Босния и Герцеговина</option>--%>
<%--<option value="35336">Ботсвана</option>--%>
<%--<option value="35337">Бразилия</option>--%>
<%--<option value="35338">Бруней-Даруссалам</option>--%>
<%--<option value="35339">Буркина-Фасо</option>--%>
<%--<option value="35340">Бурунди</option>--%>
<%--<option value="35341">Бутан</option>--%>
<%--<option value="35342">Вануату</option>--%>
<%--<option value="35303">Венгрия</option>--%>
<%--<option value="35343">Венесуэла Боливарианская Республика</option>--%>
<%--<option value="35344">Виргинские острова, Британские</option>--%>
<%--<option value="35345">Виргинские острова, США</option>--%>
<%--<option value="35346">Вьетнам</option>--%>
<%--<option value="35347">Габон</option>--%>
<%--<option value="35348">Гаити</option>--%>
<%--<option value="35349">Гайана</option>--%>
<%--<option value="35350">Гамбия</option>--%>
<%--<option value="35351">Гана</option>--%>
<%--<option value="35352">Гваделупа</option>--%>
<%--<option value="35353">Гватемала</option>--%>
<%--<option value="35355">Гвинея</option>--%>
<%--<option value="35354">Гвинея-Бисау</option>--%>
<%--<option value="35279">Германия</option>--%>
<%--<option value="35356">Гернси</option>--%>
<%--<option value="35357">Гибралтар</option>--%>
<%--<option value="35358">Гондурас</option>--%>
<%--<option value="35310">Гонконг</option>--%>
<%--<option value="35359">Гренада</option>--%>
<%--<option value="35360">Гренландия</option>--%>
<%--<option value="35361">Греция</option>--%>
<%--<option value="35284">Грузия</option>--%>
<%--<option value="35362">Гуам</option>--%>
<%--<option value="35300">Дания</option>--%>
<%--<option value="35363">Джерси</option>--%>
<%--<option value="35364">Джибути</option>--%>
<%--<option value="35365">Доминика</option>--%>
<%--<option value="35366">Доминиканская республика</option>--%>
<%--<option value="35367">Египет</option>--%>
<%--<option value="35368">Замбия</option>--%>
<%--<option value="35370">Зимбабве</option>--%>
<%--<option value="35285">Израиль</option>--%>
<%--<option value="35371">Индия</option>--%>
<%--<option value="35372">Индонезия</option>--%>
<%--<option value="35373">Иордания</option>--%>
<%--<option value="35374">Иран, Исламская республика</option>--%>
<%--<option value="35315">Ирландия</option>--%>
<%--<option value="35375">Исландия</option>--%>
<%--<option value="35274">Испания</option>--%>
<%--<option value="35295">Италия</option>--%>
<%--<option value="35376">Йемен</option>--%>
<%--<option value="35377">Кабо Верде</option>--%>
<%--<option value="35286">Казахстан</option>--%>
<%--<option value="35378">Кайманские острова</option>--%>
<%--<option value="35379">Камбоджа</option>--%>
<%--<option value="35380">Камерун</option>--%>
<%--<option value="35282">Канада</option>--%>
<%--<option value="35382">Катар</option>--%>
<%--<option value="35383">Кения</option>--%>
<%--<option value="35384">Кипр</option>--%>
<%--<option value="35287">Киргизия</option>--%>
<%--<option value="35385">Кирибати, Республика</option>--%>
<%--<option value="35289">Китай</option>--%>
<%--<option value="35386">Колумбия</option>--%>
<%--<option value="35387">Коморы</option>--%>
<%--<option value="35388">Конго</option>--%>
<%--<option value="35389">Конго, Демократическая республика</option>--%>
<%--<option value="35594">Корейская Народно-Демократическая Республика</option>--%>
<%--<option value="35390">Корея, Народно-Демократическая Республика</option>--%>
<%--<option value="35595">Корея, Республика</option>--%>
<%--<option value="35391">Коста-Рика</option>--%>
<%--<option value="35392">Кот д`Ивуар</option>--%>
<%--<option value="35304">Куба</option>--%>
<%--<option value="35393">Кувейт</option>--%>
<%--<option value="35395">Кюрасао</option>--%>
<%--<option value="35396">Лаосская Народно-Демократическая Республика</option>--%>
<%--<option value="35298">Латвия</option>--%>
<%--<option value="35397">Лесото</option>--%>
<%--<option value="35398">Либерия</option>--%>
<%--<option value="35399">Ливан</option>--%>
<%--<option value="35400">Ливия</option>--%>
<%--<option value="35401">Литва</option>--%>
<%--<option value="35402">Лихтенштейн</option>--%>
<%--<option value="35403">Люксембург</option>--%>
<%--<option value="35404">Маврикий</option>--%>
<%--<option value="35405">Мавритания</option>--%>
<%--<option value="35406">Мадагаскар</option>--%>
<%--<option value="40708">Майотта</option>--%>
<%--<option value="35407">Макао</option>--%>
<%--<option value="35409">Малави</option>--%>
<%--<option value="35410">Малайзия</option>--%>
<%--<option value="35411">Мали</option>--%>
<%--<option value="35412">Мальдивы</option>--%>
<%--<option value="35413">Мальта</option>--%>
<%--<option value="35414">Марокко</option>--%>
<%--<option value="35416">Мартиника</option>--%>
<%--<option value="35415">Маршалловы острова</option>--%>
<%--<option value="35314">Мексика</option>--%>
<%--<option value="35417">Мозамбик</option>--%>
<%--<option value="35418">Молдова, Республика</option>--%>
<%--<option value="35419">Монако</option>--%>
<%--<option value="35420">Монголия</option>--%>
<%--<option value="35421">Монтсеррат</option>--%>
<%--<option value="35422">Мьянма</option>--%>
<%--<option value="35423">Намибия</option>--%>
<%--<option value="35424">Науру</option>--%>
<%--<option value="35426">Непал</option>--%>
<%--<option value="35427">Нигер</option>--%>
<%--<option value="35428">Нигерия</option>--%>
<%--<option value="35429">Нидерланды</option>--%>
<%--<option value="35430">Никарагуа</option>--%>
<%--<option value="35431">Ниуэ</option>--%>
<%--<option value="35312">Новая Зеландия</option>--%>
<%--<option value="35432">Новая Каледония</option>--%>
<%--<option value="35301">Норвегия</option>--%>
<%--<option value="35433">Объединенные Арабские Эмираты</option>--%>
<%--<option value="35434">Оман</option>--%>
<%--<option value="35394">Острова Кука</option>--%>
<%--<option value="35435">Пакистан</option>--%>
<%--<option value="35436">Панама</option>--%>
<%--<option value="35437">Папуа-Новая Гвинея</option>--%>
<%--<option value="35438">Парагвай</option>--%>
<%--<option value="35439">Перу</option>--%>
<%--<option value="35273">Польша</option>--%>
<%--<option value="35306">Португалия</option>--%>
<%--<option value="35440">Пуэрто-Рико</option>--%>
<%--<option value="35408">Республика Македония</option>--%>
<%--<option value="35441">Реюньон</option>--%>
<%--<option value="35442">Руанда</option>--%>
<%--<option value="35309">Румыния</option>--%>
<%--<option value="35598">Самоа</option>--%>
<%--<option value="35633">Сан-Марино</option>--%>
<%--<option value="35445">Сан-Томе и Принсипи</option>--%>
<%--<option value="35446">Саудовская Аравия</option>--%>
<%--<option value="35448">Свазиленд</option>--%>
<%--<option value="35443">Северные марианские острова</option>--%>
<%--<option value="35449">Сейшелы</option>--%>
<%--<option value="35450">Сенегал</option>--%>
<%--<option value="35451">Сен-Мартен</option>--%>
<%--<option value="35452">Сент-Винсент и Гренадины</option>--%>
<%--<option value="35453">Сент-Китс и Невис</option>--%>
<%--<option value="35454">Сент-Люсия</option>--%>
<%--<option value="39050">Сербия</option>--%>
<%--<option value="35456">Сингапур</option>--%>
<%--<option value="35457">Сирийская Арабская Республика</option>--%>
<%--<option value="35458">Словакия</option>--%>
<%--<option value="35459">Словения</option>--%>
<%--<option value="35294">Соединенное Королевство</option>--%>
<%--<option value="35272">Соединенные Штаты</option>--%>
<%--<option value="35460">Соломоновы острова</option>--%>
<%--<option value="35461">Сомали</option>--%>
<%--<option value="35462">Судан</option>--%>
<%--<option value="35463">Суринам</option>--%>
<%--<option value="35464">Сьерра-Леоне</option>--%>
<%--<option value="35465">Таджикистан</option>--%>
<%--<option value="35467">Таиланд</option>--%>
<%--<option value="35466">Тайвань</option>--%>
<%--<option value="35469">Танзания, Объединенная Республика</option>--%>
<%--<option value="42769">Тимор-Лесте</option>--%>
<%--<option value="35471">Того</option>--%>
<%--<option value="35472">Тонга</option>--%>
<%--<option value="35473">Тринидад и Тобаго</option>--%>
<%--<option value="35474">Тувалу</option>--%>
<%--<option value="35496">Тунис</option>--%>
<%--<option value="35475">Туркмения</option>--%>
<%--<option value="35476">Турция</option>--%>
<%--<option value="35477">Уганда</option>--%>
<%--<option value="35283">Узбекистан</option>--%>
<%--<option value="35281">Украина</option>--%>
<%--<option value="35478">Уругвай</option>--%>
<%--<option value="35479">Фарерские острова</option>--%>
<%--<option value="35480">Фиджи</option>--%>
<%--<option value="35481">Филиппины</option>--%>
<%--<option value="35482">Финляндия</option>--%>
<%--<option value="35483">Фолклендские острова (Мальвинские)</option>--%>
<%--<option value="35296">Франция</option>--%>
<%--<option value="35484">Французская Гвиана</option>--%>
<%--<option value="35468">Французская полинезия</option>--%>
<%--<option value="35485">Хорватия</option>--%>
<%--<option value="35597">Центрально-Африканская Республика</option>--%>
<%--<option value="35486">Чад</option>--%>
<%--<option value="39748">Черногория</option>--%>
<%--<option value="35293">Чешская Республика</option>--%>
<%--<option value="35487">Чили</option>--%>
<%--<option value="35297">Швейцария</option>--%>
<%--<option value="35302">Швеция</option>--%>
<%--<option value="35488">Шри-Ланка</option>--%>
<%--<option value="35489">Эквадор</option>--%>
<%--<option value="35490">Экваториальная Гвинея</option>--%>
<%--<option value="35444">Эль-Сальвадор</option>--%>
<%--<option value="35491">Эритрея</option>--%>
<%--<option value="35299">Эстония</option>--%>
<%--<option value="35492">Эфиопия</option>--%>
<%--<option value="35495">Южная Африка</option>--%>
<%--<option value="35494">Ямайка</option>--%>
<%--<option value="35278">Япония</option>--%>
<%--</select>--%>
<%--</td>--%>
</tr>
</tbody>
</table>
<p><input class="btn btn-blue" type="submit" value="Далее..."></p>
</form>
</div>
</div>

</div>
</div>