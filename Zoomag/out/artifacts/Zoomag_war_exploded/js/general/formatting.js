function removeDigits(value)
{
    var result = "";
    for(i=0; i<value.length; i++)
    {
        if(isNaN(value[i]*1.0)||value[i]==" ")
        {
            result+=value[i];
        }
    }
    return result;
}
function extractDigits(value)
{
    var result = "";
    for(i=0; i<value.length; i++)
    {
        if(!isNaN(value[i]*1.0))
        {
            result+=value[i];
        }
    }
    return result;
}
function formatPrice(value)
{
    var head = "";
    var body = "";
    var tail = "";
    var val_str = new String(value)+"";
    var len = val_str.length*1;
    if(len>6)
    {
        head = val_str.substr(0, len-6);
        body = val_str.substr(len-6, 3);
        tail = val_str.substr(len-3, 3);
        return head+"&nbsp;"+body+"&nbsp;"+tail;
    }
    else if(len>3)
    {
        head = val_str.substr(0, len-3);
        tail = val_str.substr(len-3, 3);
        return head+"&nbsp;"+tail;
    }
    else
    {
        return value+"";
    }
}
function formatGoods(count)
{
    var last_two_digits = 0;
    var last_one_digit = 0;

    if(count>9)
    {
        last_two_digits = (count+"").substr(0,2)*1;
    }
    else
    {
        last_two_digits = (count+"").substr(0,1)*1;
    }
    last_one_digit = (count+"").substr(-1)*1;

    if(last_two_digits<=20)
    {
        if(last_two_digits==1)
        {
            return "товар";
        }
        if(last_two_digits>1&&last_two_digits<5)
        {
            return "товара";
        }
        return "товаров";
    }
    if(last_two_digits>20)
    {
        if(last_one_digit==1)
        {
            return "товар";
        }
        if(last_one_digit>1&&last_one_digit<5)
        {
            return "товара";
        }
        return "товаров";
    }
}
function validateNumber(text)
{
    var reg = /^([0-9]){1,10}$/;
    return reg.test(text);
}
function validateNotEmpty(text)
{
    return text.length>0;
//    var reg = /^(.*){1,100000000}$/;
//    return reg.test(text);
}
function validateEmail(text)
{
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    return emailReg.test(text);
}
function validatePassword(text)
{
    var passwordReg = /^([0-9a-zA-Z]){6,40}$/;
    return passwordReg.test(text);
}
function validatePhone(text)
{
    var phoneReg = /^(.*){10,10}$/;
    return phoneReg.test(text);
}
function validateQiwiPhone(text)
{
    var phoneReg = /^([0-9]){10,10}$/;
    return phoneReg.test(text);
}
function validateAmount(text)
{
    var amountReg = /^([0-9]*){1,6}$/;
    var is_int = amountReg.test(text);
    if(is_int)
    {
        return text*1>=1;
    }
    return false;
}
function validateAddress(text)
{
    var addressReg = /^([0-9a-zA-Z\.\sа-яА-Я\#\*]){15,540}$/;
    return addressReg.test(text);
}
function urlEncode(url)
{
    var text = encodeURI(url);
    text = text.replace(/#/g,"%23");
    text = text.replace(/&/g,"%26");
    text = text.replace(/\?/g,"%3F");
    text = text.replace(/\=/g,"%3D");
    text = text.replace(/\//g,"%2F");
    text = text.replace(/\./g,"%2E");
    text = text.replace(/\,/g,"%2C");




    return text;
}
function htmlDecode(text)
{
    var res = text;
    res = res.replace(/</g,"&lt;");
    res = res.replace(/>/g,"&gt;");

    return res;
}
var time_to_show_messgaebox = 0;