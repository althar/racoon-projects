function validatePhone(text)
{
	var phoneReg = /^([0-9]){11,11}$/;
	return phoneReg.test(text);
}
function validatePassword(text)
{
	var passwordReg = /^([0-9a-zA-Z]){6,40}$/;
	return passwordReg.test(text);
}

function validateEmail(text)
{
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	return emailReg.test(text);
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
