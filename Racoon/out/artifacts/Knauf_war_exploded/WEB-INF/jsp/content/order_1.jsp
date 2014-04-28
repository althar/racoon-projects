<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="content layout">

    <div class="container section dark-title">

        <div class="boxed-group">
            <h2>Оформление заказа · Адрес</h2>

            <div class="boxed-group-inner">
                <p>Товары: ${basket.get('CartSummary').get('FullSum')} теплуноса</p>

                <h3>Город доставки</h3>

                <form method="post" action="/order/delivery">
                    <table>
                        <tbody>
                        <tr>
                            <td>
                                <label class="radio">
                                    <input type="radio" name="area" value="1" checked=""> Москва
                                </label>
                            </td>
                            <td>&nbsp; &nbsp;</td>
                            <td>
                                <select name="locale[1]">
                                    <option value="2">Москва в пределах МКАД</option>
                                    <option value="35634">Зеленоград</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="radio">
                                    <input type="radio" name="area" value="2"> Санкт-Петербург
                                </label>
                            </td>
                            <td>&nbsp; &nbsp;</td>
                            <td>
                                <select name="locale[2]">
                                    <option value="5911">Санкт-Петербург в пределах КАД</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="radio">
                                    <input type="radio" name="area" value="3"> Россия
                                </label>
                            </td>
                            <td>&nbsp; &nbsp;</td>
                            <td>
                                <select name="locale[3]">
                                    <option value="30435">Абакан</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="radio">
                                    <input type="radio" name="area" value="4"> За пределы России
                                </label>
                            </td>
                            <td>&nbsp; &nbsp;</td>
                            <td>
                                <select name="locale[4]">
                                    <option value="35277">Австралия</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <p><input class="btn btn-blue" type="submit" name="submit" value="Далее..."></p>
                </form>
            </div>
        </div>

    </div>
</div>