using System.Security.Cryptography;
using System;
using System.Windows.Forms;
using System.IO;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;

namespace FTwoFramework.DB
{
    public class Configurator
    {
        private static RijndaelManaged man = new RijndaelManaged();
        private static ICryptoTransform decryptor;
        private static ICryptoTransform encryptor;
        private static Dictionary<string, string> dict = new Dictionary<string, string>();
        static Configurator()
        {
            byte[] key = new byte[16];
            byte[] vector = new byte[16];
            decryptor = man.CreateDecryptor(key, vector);
            encryptor = man.CreateEncryptor(key, vector);

            dict.Add("on_warehouse", "На складе");
            dict.Add("date_to", "Приход закупки на склад");
            dict.Add("user_name", "Оператора");
            dict.Add("article", "Артикул производителя");
            dict.Add("total_sum", "Сумма");
            dict.Add("total_count", "Общее кол-во");
            dict.Add("time", "Время");
            dict.Add("real_article", "Артикул поставщика");
            dict.Add("action", "Действие");
            dict.Add("multiplier", "Коэф. наценки");
            dict.Add("approved", "Утвержден");
            dict.Add("purchase_price", "Закупочная цена");
            dict.Add("status_id", "Код статуса");
            dict.Add("show_price_decrease", "Понижение цены");
            dict.Add("minimum_clients_clicks", "Минимальное число кликов");
            dict.Add("modified", "Изменен");
            dict.Add("deliver_phone", "Телефон доставки");
            dict.Add("deliver_address", "Адрес доставки");
            dict.Add("user_id", "ID пользователя");
            dict.Add("gender", "пол");
            dict.Add("registered", "Зарегистрирован");
            dict.Add("deliver_type", "Тип доставки");
            dict.Add("changed", "Изменен");
            dict.Add("last_promotion_date", "Дата смены процента бонуса");
            dict.Add("dealer_id", "Номер дилера");
            dict.Add("money", "Деньги");
            dict.Add("profit_percent", "Процент бонуса");
            dict.Add("birth_date", "Дата рождения");
            dict.Add("goods_total_price", "Общая цена товаров");
            dict.Add("deliver_total_price", "Общая цена доставки");
            dict.Add("modifier_name", "Редактировал");
            dict.Add("category", "Категория");
            dict.Add("reserve_time", "Время бронирования");
            dict.Add("start_sell_price", "Стартовая цена");
            dict.Add("show_price_fee", "Стоимость клика");
            dict.Add("minimum", "Минимальный остаток");
            dict.Add("name_for_order_full", "Наименование товара");
            dict.Add("to_purchase_count", "Кол-во");
            dict.Add("total_purchase_price", "Стоимость");

            dict.Add("is_for_orders", "Под заказ");
            dict.Add("accept_time", "Время подтверждения");
            dict.Add("good_count", "Кол-во");
            dict.Add("good_purchase_price", "Цена");
            dict.Add("total_price", "Стоимость");
            dict.Add("goods_price", "Цена товаров");
            dict.Add("list_name", "№");

            dict.Add("deliver_date", "Дата доставки");
            dict.Add("deliver_price", "Цена доставки");
            dict.Add("good_price", "Цена товара");
            dict.Add("good_name", "Наименование товара");
            dict.Add("count", "Кол-во");
            dict.Add("deliver_time", "Время доставки");
            dict.Add("deliver", "Доставка");
            dict.Add("phones", "Телефоны");
            dict.Add("driver", "Водитель");
            dict.Add("total_weight", "Общий вес");

            dict.Add("discount_percent", "Скидка (%)");
            dict.Add("name", "Имя");
            dict.Add("search_count", "Кол-во поисков");
            dict.Add("search_text", "Слово поиска");
            dict.Add("full_name", "Полное название");
            dict.Add("id", "№");
            dict.Add("number", "Номер");
            dict.Add("load_date", "Дата отгрузки");
            dict.Add("foundation", "Основание");
            dict.Add("is_carrier", "Курьер");
            dict.Add("call_date", "Дата звонка");
            dict.Add("organization", "Организация");
            dict.Add("text", "Текст");
            dict.Add("folio_name", "В фолио");
            dict.Add("organization_type", "Тип организации");
            dict.Add("manager", "Менеджер");
            dict.Add("type", "Тип");
            dict.Add("status", "Статус");
            dict.Add("department", "Департамент");
            dict.Add("address", "Адрес");
            dict.Add("activity_directions", "Направления деятельности");
            dict.Add("activity_direction", "Направление деятельности");
            dict.Add("is_phizical", "Физическое лицо");
            dict.Add("web_site", "Веб-сайт");
            dict.Add("date", "Дата");
            dict.Add("email", "EMail");
            dict.Add("description", "Описание/Отчет");
            dict.Add("info_source", "Источник информации");
            dict.Add("phone_number", "Номер телефона");
            dict.Add("phone_number_2", "Номер телефона 2");
            dict.Add("phone2", "Телефон");
            dict.Add("phone3", "Телефон");
            dict.Add("curator_id", "ID Куратора");
            dict.Add("phone_number_type", "Тип номера телефона");
            dict.Add("phone_number_type_2", "Тип номера телефона");
            dict.Add("priority", "Приоритет");
            dict.Add("holding", "Холдинг");
            dict.Add("is_female", "Женщина");
            dict.Add("post", "Должность");
            dict.Add("client_id", "ID Клиента");
            dict.Add("value", "Значение");
            dict.Add("second_name", "Фамилия");
            dict.Add("last_name", "Отчество");
            dict.Add("phone", "Телефон");
            dict.Add("rezy", "Резы");
            dict.Add("more_info", "Дополнительная информация");
            dict.Add("inet_number", "Номер интернет заказа");
            dict.Add("created", "Дата оформления заказа");
            dict.Add("receive_date", "Дата получения товара");
            dict.Add("reason", "Причина смены статуса");
            dict.Add("articul", "Арт.");
            dict.Add("quantity", "Количество");
            dict.Add("delivery_date_market", "Дата доставки на Сервис Маркет");
            dict.Add("is_wait", "Заказчик ждет");
            dict.Add("request", "Запрос");
            dict.Add("delivery_date_store", "Дата доставки на склад КД");
            dict.Add("order_number", "Номер заказа");
            dict.Add("manager_id", "Идентификатор менеджера");
            dict.Add("company_opening_date", "Дата открытия компании");
            dict.Add("company_celebration_date", "День рождения компании");
            dict.Add("is_folio_priority", "Приоритет фолио");
            dict.Add("FIO", "ФИО");
            dict.Add("fio", "ФИО");
            dict.Add("manager_name", "Менеджер");
            dict.Add("curator_name", "Куратор");
            dict.Add("client_name", "Клиент");
            dict.Add("contact_person_name", "Контактное лицо");
            dict.Add("meetings_count", "Общее кол-во встреч");
            dict.Add("closed", "Закрыт");
            dict.Add("role_id", "Роль");
            dict.Add("first_name", "Имя");
            dict.Add("login", "Логин");
            dict.Add("password", "Пароль");

            dict.Add("web_page_url", "url иеб-страницы");
            dict.Add("name_rus", "Наименование на русском");
            dict.Add("name_eng", "Наименование на английском");
            dict.Add("name_for_shop", "Наименование для магазина");
            dict.Add("name_for_order", "Наименование для заказов ");
            dict.Add("weight", "Вес кг.");
            dict.Add("weight_product", "Вес кг.");
            dict.Add("price", "Стоимость");
            dict.Add("overprice_percent", "Наценка %");
            dict.Add("price_discount", "Цена со скидкой");
            dict.Add("price_discount_percent", "Скидка %");
            dict.Add("price_basic", "Базовая цена");
            //dict.Add("description", "Описание");
            dict.Add("description_short", "Короткое описание");
            dict.Add("photo_url", "url фотографии");
            dict.Add("keywords_meta", "Ключенвые слова");
            dict.Add("description_meta", "Описание для сайта");
            dict.Add("page_header", "Заголовок страницы сайта");
            dict.Add("is_hidden", "Пароль");
            dict.Add("price_kg", "Цена за кг.");
            dict.Add("profit", "Прибыль");
            dict.Add("supplier", "Поставщик");
            dict.Add("company", "Компания");
            dict.Add("animal", "Животное");
            dict.Add("food_type", "Тип корма");
            dict.Add("food_type_age", "Возрастная категория");
            dict.Add("food_type_category", "Применение");
            dict.Add("phone_1", "Телефон 1");
            dict.Add("phone_2", "Телефон 2");
            dict.Add("status_name", "Статус");

        }
        public static string fromUpper(string word)
        {
            if (word != null && word.Length > 0)
            {
                return word.Substring(0, 1).ToUpper() + word.Substring(1);
            }
            return word;
        }
        public static object getValueFromDataGrid(DataGridView datagrid, string fieldName)
        {
            if (datagrid.SelectedCells.Count > 0)
            {
                return datagrid.SelectedCells[0].OwningRow.Cells[fieldName].Value;
            }
            return null;
        }
        public static void packToFile(string[] parameters, string file)
        {
            FileStream writer = null;
            try
            {
                FileInfo fil = new FileInfo(file);
                if (!fil.Exists)
                {
                    fil.Directory.Create();
                }

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < parameters.Length; i++)
                {
                    builder.Append(parameters[i]);
                    if (i < parameters.Length - 1)
                    {
                        builder.Append(Environment.NewLine);
                    }
                }
                byte[] pack = Encoding.UTF8.GetBytes(builder.ToString());
                byte[] encrypted = encryptor.TransformFinalBlock(pack, 0, pack.Length);
                File.Delete(file);
                writer = new FileStream(file, FileMode.OpenOrCreate);
                writer.Write(encrypted, 0, encrypted.Length);
            }
            catch (Exception ex)
            {
                writer.Close();
                throw new Exception("Error while packing in file: " + ex.ToString());
            }
            finally
            {
                writer.Close();
            }
        }
        public static string[] unpackFromFile(string file)
        {
            FileStream reader = null;
            try
            {
                reader = new FileStream(file, FileMode.OpenOrCreate);
                byte[] pack = new byte[reader.Length];
                reader.Read(pack, 0, pack.Length);
                byte[] decrypted = decryptor.TransformFinalBlock(pack, 0, pack.Length);
                string[] parameters = Encoding.UTF8.GetString(decrypted).Split(new string[1] { Environment.NewLine }, StringSplitOptions.None);
                reader.Close();
                return parameters;
            }
            catch (Exception ex)
            {
                reader.Close();
                throw new Exception("Error while unpacking from file: " + ex.ToString());
            }
            finally
            {
            }
        }
        private static string translate(string field)
        {
            if (!dict.ContainsKey(field))
            {
                return "";
            }
            return dict[field];

        }
        private static void makeInvisible(string columnName, DataGridView view)
        {
            if (view.Columns["is_suborder"] != null)
            {
                view.Columns["is_suborder"].Visible = false;
            }
        }
        public static void translateToRussian(DataGridView view)
        {
            for (int i = 0; i < view.Columns.Count; i++)
            {
                view.Columns[i].HeaderText = translate(view.Columns[i].Name);
            }
            makeInvisible("is_suborder", view);
            makeInvisible("perent_id", view);


        }
    }
}
