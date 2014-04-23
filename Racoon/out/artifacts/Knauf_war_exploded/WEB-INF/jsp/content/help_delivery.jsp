<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <div class="content layout">

        <ul class="breadcrumbs"><li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li><li class="item">Доставка</li></ul>
        <div class="container section dark-title">


            <div class="container white">
                <h1>Доставка</h1>

                <h3>Самовывоз</h3>

                <p>Пункты выдачи заказов — это альтернативный способ получения заказа наряду с курьерской доставкой. Тариф на доставку в пункты выдачи включает в себя стоимость транспортировки груза с центрального склада в Ваш город, а также стоимость хранения в течение 10 дней.</p>

                <p>Самовывоз возможен из пунктов доставки следующих сетей:</p>
                <ul>
                    <li>OZON.RU (Москва, Санкт-Петербург);</li>
                    <li>Мультифото (Москва, Московская область);</li>
                    <li>QIWI Post (Москва, Московская область);</li>
                    <li>Logibox (Москва, Московская область, Санкт-Петербург, Екатеринбург);</li>
                    <li>Яркий Мир (Санкт-Петербург);</li>
                    <li>Kodak Express (Санкт-Петербург);</li>
                    <li>Евросеть (Россия);</li>
                    <li>SPSR Express (Россия, Белоруссия);</li>
                    <li>DHL Express (Казахстан).</li>
                </ul>
                <p>Конкретный пункт выдачи заказов в Вашем городе можно выбрать в процессе оформления заказа.</p>

                <h3>Курьерская доставка</h3>

                <p><b>Доставка в Москве</b></p>

                <p>Доставка осуществляется курьерской службой О-Курьер в течение 1-2 дней с момента перехода заказа на комплектацию. Непосредственный день начала комплектации в указанный срок не входит.</p>

                <p>Заказы могут быть получены с 9:00 до 18:00 ежедневно. Служба доставки работает без выходных. В будние дни Вы можете выбрать пятичасовой интервал времени приезда курьера, например: с 9:00 до 14:00 или с 14:00 до 18:00.</p>

                <p>Получение осуществляется в жилых и офисных помещениях. Время обслуживания курьером одного покупателя составляет 5–15 минут.</p>

                <p>Представитель Курьерской службы свяжется с Вами по телефону, указанному при оформлении заказа, чтобы согласовать дату и время доставки посылки.</p>

                <p>Если въезд на территорию Вашего дома ограничен, Вы можете уточнить марку и номер машины курьера заранее, чтобы подготовить пропуск. Курьер может также выдать заказ на проходной.</p>

                <p>Если Вы не можете предоставить курьеру пропуск на территорию или выйти к проходной самостоятельно, пожалуйста, свяжитесь с Курьерской службой и договоритесь о новом месте и времени получения заказа — это не потребует от Вас дополнительной платы.</p>

                <p>Для получения заказа, оформленного по предоплате, необходимо предъявить любой документ, удостоверяющий личность Получателя. Knauf Insulation и О-Курьер гарантируют конфиденциальность Ваших персональных данных.</p>

                <p><b>Доставка в Московской области</b></p>

                <p>Доставка обычно осуществляется в течение 5-ти рабочих дней с момента перехода заказа на комплектацию. Непосредственный день начала комплектации в указанный срок не входит.</p>

                <p>В момент начала комплектации на странице с Вашими заказами Вы увидите выделенное под заказ отправление, которое будет находиться в статусе «Комплектуется». Это означает, что все нужные товары уже есть на нашем складе, и в течение указанных 5-ти рабочих дней с Вами свяжется представитель курьерской службы, чтобы согласовать время приезда курьера.</p>

                <p>Заказы в область могут быть доставлены по рабочим дням с 9:00 до 18:00.</p>

                <p>На стоимость курьерской доставки влияет удаленность населенного пункта от МКАД. Точная стоимость доставки будет определена в процессе оформления заказа.</p>

                <p><b>Доставка в Санкт-Петербурге</b></p>

                <p>Доставка осуществляется по Санкт-Петербургу в течение 2-3 рабочих дней с момента перехода заказа на комплектацию. Непосредственный день начала комплектации в указанный срок не входит. Возможна доставка в выходные дни.</p>

                <p>Время доставки с 9:00 до 18:00 ежедневно. Заказы доставляются после предварительного звонка. Представитель курьерской службы свяжется с Вами по контактному телефону для согласования даты и времени доставки. Если Вам удобна доставка в выходной день, или в рабочий день после 18:00 (до 21:00), сообщите об этом оператору курьерской службы во время предварительного звонка. Мы постараемся организовать доставку в удобное для Вас время.</p>

                <p>Для получения предоплаченного заказа необходимо предъявить документ, удостоверяющий личность получателя. Knauf Insulation гарантирует конфиденциальность Ваших персональных данных.</p>

                <p><b>Доставка в Ленинградской области</b></p>

                <p>Доставка осуществляется курьерской службой О-Курьер в течение 5 рабочих дней с момента перехода заказа на комплектацию. Непосредственный день начала комплектации в указанный срок не входит.</p>

                <p>Представитель курьерской службы свяжется с Вами для согласования даты и времени доставки по телефону, указанному при оформлении заказа.</p>

                <p>Населенные пункты, в которые осуществляется доставка: Александровская, Всеволжск, Гатчина, Колпино, Коммунар, Красное село, Кронштадт, Левашово, Лисий Нос, Ломоносов, Мариенбург, Металлострой, Ольгино, Павловск, Парголово, Петродворец, Пушкин, Сестрорецк.</p>

                <p>При оформлении заказа в Ленинградскую область, а также в административные центры Санкт-Петербурга (такие как Пушкин, Павловск, Петродворец, Ольгино, Кронштадт, Сестрорецк и т.д.) на странице «Выбор адреса» в разделе «Санкт-Петербург» необходимо осуществить выбор города из выпадающего списка.</p>

                <p>Отсутствие города в списке указывает на то, что курьерская доставка в данный город не выполняется. Для того чтобы воспользоваться другими способами доставки, необходимо в блоке «Россия» выбрать пункт «Другой город».</p>

                <p><b>Курьерская доставка в регионы Российской Федерации</b></p>

                <p>Доставка доступна для большинства крупных городов России в течение 5-16 дней (в зависимости от города) с момента передачи заказа в службу доставки.</p>

                <p>Представитель курьерской службы свяжется с Вами для согласования даты и времени доставки по телефону, указанному при оформлении заказа. В случае отсутствия контактного телефона возможность курьерской доставки утрачивается.</p>

                <p>Заказы могут быть получены в черте доступных для доставки городов по будням, с 9:00 до 18:00. В выходные дни заказы не доставляются.</p>

                <p>Получение осуществляется в жилых и офисных помещениях. Время обслуживания курьером одного покупателя не превышает 15 минут.</p>

                <p>Для получения заказа, оформленного по предоплате, необходимо предъявить любой документ, удостоверяющий личность Получателя. KNAUF Insulation гарантирует конфиденциальность Ваших персональных данных.</p>

                <p><b>Служба доставки SPSR Express</b></p>

                <p>Доставка осуществляется компанией SPSR Express в течение срока, указанного при оформлении заказа. Срок доставки необходимо отсчитывать с момента получения электронного уведомления о передаче заказа в службу доставки.</p>

                <p>Компания SPSR Express оказывает свои услуги более чем в 1300 городах России и Белоруссии, гарантируя точное соблюдение обозначенных сроков, а также высочайшие стандарты безопасности и сохранности отправлений.</p>

                <p>Заказы могут быть доставлены с 9:00 до 18:00 по рабочим дням. В выходные дни доставка не осуществляется.</p>

                <p>Представитель курьерской службы свяжется с Вами для согласования даты и времени доставки по телефону, указанному при оформлении заказа.</p>

                <p>При получении заказа, оформленного по предоплате, необходимо предъявить любой документ, удостоверяющий личность Получателя. KNAUF Insulation и SPSR Express гарантируют конфиденциальность Ваших персональных данных. В адресе курьерской доставки должен быть указан реальный физический адрес получателя. При оформлении курьерской доставки на абонентский ящик, Вам будет предложено забрать его самостоятельно в местном отделении СПСР.</p>

                <p><b>Служба доставки EMS Почта России в города России и за рубеж</b></p>

                <p>Доставка осуществляется компанией EMS Почта России по всей территории России на основе индекса почтового отделения. В небольших населенных пунктах доставка до двери не производится, получателя вызывают на отделение связи для получения отправления.</p>

                <p>Общая стоимость заказа не должна быть выше 9000 теплуносов, а вес – 30,5 кг. Предельные размеры внутренних экспресс-отправлений EMS составляют 1,50 м для одного из измерений, или 3 м для суммы длины и наибольшей окружности, взятой в направлении ином, чем длина. В случае превышения габаритных размеров, принятый к исполнению заказ может быть аннулирован с предложением выбрать другой способ доставки.</p>

                <p>Заказы могут быть получены с 9:00 до 18:00 по будням. Если в это время Получатель находится на работе, указывайте рабочий адрес доставки.</p>

                <p>Внимание! При оформлении заказа обязательно укажите свой почтовый адрес (с индексом) и контактный телефон.</p>

                <p>После того, как Ваш заказ будет отправлен, вы получите уведомительное письмо, в котором будет указан номер отправления, присвоенный EMS.</p>

                <p>Вы можете воспользоваться сервисом <a href="http://www.emspost.ru/ru/" target="_blank">Отслеживание отправлений EMS</a>.</p>

                <p>Сотрудники службы EMS позвонят Вам для согласования времени доставки.</p>

                <p>Все отправления вручаются лично адресату под расписку.</p>

                <p>В случае если курьер не смог осуществить доставку с первого раза по независящим от него причинам, вторая попытка доставить отправление будет предпринята через 5 дней без взимания дополнительной платы. После каждой попытки доставить отправление курьер оставляет письменное извещение.</p>

                <p>Сроки хранения экспресс-отправлений EMS в отделениях почтовой связи составляет 14 суток со дня их поступления. По истечении этого срока заказ будет передан обратно и аннулирован.</p>

                <h3>Почтовая доставка по России и за пределами РФ</h3>

                <p>Доставку осуществляет Федеральное государственное унитарное предприятие «Почта России».</p>

                <p>По рекомендации ФГУП Почта России, косметические товары из раздела «Красота и здоровье» при пересылке упаковываются в отдельные тарифицируемые отправления. Если в Вашем заказе присутствуют товары данной группы, а также товары из других разделов, заказ будет автоматически разделен на 2 отправления.</p>

                <p>Срок доставки обычно составляет от 4 рабочих дней до 6 недель с момента получения электронного уведомления о передаче заказа из магазина в службу доставки и зависит от удаленности региона.</p>

                <p>Вы можете узнать справочную <a href="http://www.russianpost.ru/rp/servise/ru/home/postuslug/termsdelivery/termsdeliv_parcel" target="_blank">информацию о сроках доставки</a>, декларируемую Почтой РФ.</p>

                <p>Для контроля местонахождения вашего отправления, воспользуйтесь <a href="http://www.russianpost.ru/rp/servise/ru/home/postuslug/trackingpo" target="_blank">формой отслеживания</a> отправления на сайте Почты России, или бесплатным телефоном 8 800 200 5888.</p>

                <div class="clear"></div>



            </div>


        </div>
    </div>