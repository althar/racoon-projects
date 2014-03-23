<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title></title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    $("#reboot").click(function () {
        $.ajax({
                    url: "/login?login=AlThar&password=bredok",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Автоизация фасилитатора: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        // Reboot
        $.ajax({
                    url: "/api/reboot",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Перезагрузка: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );

    });
    // Create game
    $("#create-game").click(function () {
        // Login admin
        $.ajax({
                    url: "/login?login=AlThar&password=bredok",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Автоизация фасилитатора: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        // Create game
        var params = "";
        params += "&start_economics_sector_size=90000000";
        params += "&balance_for_all_companies=16000000";
        params += "&value_assets_for_all_companies=120000000";
        params += "&total_capacity=80000";
        params += "&total_depreciation=15";
        params += "&profit_tax=0.20";
        params += "&elasticity_function_type=LINEAR";
        params += "&elasticity_influence_coefficient=0.20";
        params += "&elasticity_decrease=2.0";
        params += "&power_coefficient_max=1.0";
        params += "&power_coefficient_min=0.85";
        params += "&power_loading_coefficient_max=0.95";
        params += "&power_loading_coefficient_min=0.75";
        params += "&value_assets_for_all_companies_coefficient_max=0.7";
        params += "&value_assets_for_all_companies_coefficient_min=0.3";
        params += "&variable_costs_as_a_percentage_of_revenue_coefficient_max=0.45";
        params += "&variable_costs_as_a_percentage_of_revenue_coefficient_min=0.35";
        params += "&constant_costs_as_a_percentage_of_revenue_coefficient_max=0.45";
        params += "&constant_costs_as_a_percentage_of_revenue_coefficient_min=0.35";

        params += "&variable_costs_decrease_coefficient_small_project=0.1";
        params += "&constant_costs_decrease_coefficient_medium_project=0.2";
        params += "&investment_constant_costs_coefficient_medium_project=0.4";
        params += "&current_power_percent_big_project=0.30";
        params += "&exceeds_the_net_book_value_of_fixed_assets_coefficient_big_project=0.2";
        params += "&variable_costs_decrease_coefficient_big_project=0.2";

        $.ajax({
                    url: "/api/create_game?company_count=5&name=" + $("#input-game-name").val() + params,
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Создание игры: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        // Get games
        $.ajax({
                    url: "/api/games",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        $("#game-id").html(data);
                        $("#game-id").val(data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
    });
    // Join game
    $("#join-game").click(function () {
        // Login admin
        $.ajax({
                    url: "/login?login=AlThar&password=bredok",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Автоизация игрока 1: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        // Join game
        $.ajax({
                    url: "/api/join_game?id=" + $("#input-game-id").val(),
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Присоединение к игре игрок 1: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        $.ajax({
                    url: "/login?login=dima&password=1111",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Автоизация игрока 2: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        $.ajax({
                    url: "/api/join_game?id=" + $("#input-game-id").val(),
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Присоединение к игре игрок 2: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
    });
    // Start game
    $("#start-game").click(function () {
        // Login admin
        $.ajax({
                    url: "/login?login=AlThar&password=bredok",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Автоизация фасилитатора: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        // Join game
        $.ajax({
                    url: "/api/start_game?id=" + $("#input-game-id").val(),
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Старт игры: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        // Get games
        $.ajax({
                    url: "/api/games",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Получение данных игры");
                        $("#games").html(data);
                        $("#games").val(data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
    });
    // Phase 1
    $("#phase-1").click(function () {
        var amount = $("#good-amount-1").val();
        var price = $("#good-price-1").val();
        var amount2 = $("#good-amount-2").val();
        var price2 = $("#good-price-2").val();
        $.ajax({
                    url: "/login?login=AlThar&password=bredok",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Автоизация игрока 1: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        $.ajax({
                    url: "/api/set_trade_factors?game_id=" + $("#input-game-id").val(),
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Установка коэффициентов торговли: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        $.ajax({
                    url: "/api/declare_goods?game_id=" + $("#input-game-id").val()+"&value="+amount+"&price="+price,
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Заявление объема товара игрока 1: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        $.ajax({
                    url: "/login?login=dima&password=1111",
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Автоизация игрока 2: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
        $.ajax({
                    url: "/api/declare_goods?game_id=" + $("#input-game-id").val()+"&value="+amount2+"&price="+price2,
                    context: document.body,
                    async: false,
                    success: function (data) {
                        alert("Заявление объема товара игрока 2: " + data);
                    },
                    failure: function (data) {
                        alert("Fail");
                    }
                }
        );
    });
});
</script>
</head>
<body>
<button id="reboot" value="Создать игру">Перезагрузка</button>
<h2>Создание игры</h2>
<input id="input-game-name" type="text" placeholder="Имя игры">
<button id="create-game" value="Создать игру">Создать игру</button>
<br>
<textarea id="game-id" type="text" value="" style="width: 90%; height: 150px;"></textarea>
<h2>Присоединение к игре</h2>
<input id="input-game-id" type="text" placeholder="ID игры">
<button id="join-game" value="Создать игру">Присоединиться к игру</button>
<h2>Запуск игры (рассчет данных)</h2>
<button id="start-game" value="Создать игру">Запуски игры и рассчет данных</button>
<textarea id="games" type="text" value="" style="width: 90%; height: 250px;"></textarea>
<h2>Фаза 1</h2>
<input id="good-amount-1" type="text" placeholder="Объем товара игрока 1">
<input id="good-price-1" type="text" placeholder="Цена товара игрока 1">
<br>
<input id="good-amount-2" type="text" placeholder="Объем товара игрока 2">
<input id="good-price-2" type="text" placeholder="Цена товара игрока 2">
<br>
<button id="phase-1" value="Создать игру">Завершить фазу 1</button>
</body>
</html>